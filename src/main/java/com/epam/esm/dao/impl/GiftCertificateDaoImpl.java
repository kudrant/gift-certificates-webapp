package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final static String SAVE_CERTIFICATE =
            "INSERT INTO gift_certificate (name, description, price, duration) " +
                    "VALUES (:name, :description, :price, :duration);";
    private final static String GET_CERTIFICATE_BY_ID = "SELECT * FROM gift_certificate WHERE id = :param;";
    private final static String GET_ALL_CERTIFICATES = "SELECT * FROM gift_certificate;";
    private final static String DELETE_CERTIFICATE = "DELETE FROM gift_certificate WHERE id = :param;";
    private final static String UPDATE_CERTIFICATE_SET = "UPDATE gift_certificate SET ${value} WHERE id=:id;";
    private final static String DELETE_GIFT_CERTIFICATE_TAG_REFERENCE = "DELETE FROM gift_certificate_tag " +
            "WHERE gift_certificate_id=:id;";

    private final static String GET_CERTIFICATES_BY_TAG_NAME =
            "SELECT cert.id, cert.name, cert.description, cert.price, cert.duration, cert.create_date, " +
                    "cert.last_update_date FROM gift_certificate cert " +
                    "JOIN gift_certificate_tag ref ON cert.id = ref.gift_certificate_id " +
                    "JOIN tag ON tag.id = ref.tag_id WHERE tag.name= :tagName ORDER BY :order ;";

    private final TagDao tagDao;
    private final DaoUtil daoUtil;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final GiftCertificateMapper certMapper = new GiftCertificateMapper();

    @Autowired
    public GiftCertificateDaoImpl(TagDao tagDao, DaoUtil daoUtil, SimpleJdbcInsert simpleJdbcInsert) {
        this.tagDao = tagDao;
        this.daoUtil = daoUtil;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public GiftCertificate save(GiftCertificate cert, Set<Tag> tags) {
        saveTags(tags);
        GiftCertificate savedCertificate = saveGiftCertificate(cert);
        saveGiftCertificateTagReference(savedCertificate.getId(), tags);
        return savedCertificate;

    }

    private String fillSqlStringWithValues(String sql, Map<String, Object> fieldsMap) {
        StringBuilder sb = new StringBuilder();
        Set<String> fieldNames = fieldsMap.keySet();
        fieldNames.stream().filter(fieldName -> !fieldName.equals("id"))
                .forEach(fieldName -> sb.append(fieldName).append("=:").append(fieldName).append(", "));
        return sql.replace("${value}", sb.toString().replaceAll(", $", " "));
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate, Set<Tag> tags) {
        Map<String, Object> fieldsMap = new HashMap<>();
        long certId = giftCertificate.getId();
        fieldsMap.put("id", giftCertificate.getId());
        fieldsMap.put("name", giftCertificate.getName());
        fieldsMap.put("description", giftCertificate.getDescription());
        fieldsMap.put("price", giftCertificate.getPrice());
        fieldsMap.put("duration", giftCertificate.getDuration());

        fieldsMap.values().removeIf(Objects::isNull);
        fieldsMap.values().removeIf(value -> value.equals(0.0));
        fieldsMap.values().removeIf(value -> value.equals(0));
        String sql = fillSqlStringWithValues(UPDATE_CERTIFICATE_SET, fieldsMap);
        daoUtil.updateTable(sql, fieldsMap);
        updateGiftCertificateTagReference(certId, tags);

        return get(certId);
    }

    @Override
    public GiftCertificate get(long id) {
        return daoUtil.getEntityBySql(GET_CERTIFICATE_BY_ID, id, certMapper);
    }


    @Override
    public GiftCertificate getById(long id) {
        return daoUtil.getEntityBySql(GET_CERTIFICATE_BY_ID, id, certMapper);
    }

    @Override
    public void delete(long id) {
        daoUtil.updateTable(DELETE_CERTIFICATE, id);
    }

    @Override
    public List<GiftCertificate> list() {
        return daoUtil.getAllEntitiesFromTable(GET_ALL_CERTIFICATES, certMapper);
    }

    private GiftCertificate saveGiftCertificate(GiftCertificate giftCertificate) {
        return getById(daoUtil.updateReturningId(SAVE_CERTIFICATE, giftCertificate));
    }

    private void updateGiftCertificateTagReference(long certId, Set<Tag> tags) {
        saveTags(tags);
        daoUtil.updateTable(DELETE_GIFT_CERTIFICATE_TAG_REFERENCE, Collections.singletonMap("id", certId));
        saveGiftCertificateTagReference(certId, tags);
    }

    private void saveGiftCertificateTagReference(long certId, Set<Tag> tags) {
        for (Tag tag : tags
        ) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("gift_certificate_id", certId);
            parameters.put("tag_id", tag.getId());
            simpleJdbcInsert.execute(parameters);
        }
    }


    private void saveTags(Set<Tag> tags) {
        for (Tag tag : tags
        ) {
            Tag checkTag = tagDao.getTagByName(tag.getName());
            if (checkTag != null)
                tag.setId(checkTag.getId());
            else {
                tag.setId(tagDao.saveTag(tag).getId());
            }
        }
    }


    @Override
    public List<GiftCertificate> getByTagName(String tagName, String orderColumn, boolean descending) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("tagName", tagName);
        paramMap.put("order", orderColumn);

        return daoUtil.getEntitiesBySql(
                getSqlOrderDescending(GET_CERTIFICATES_BY_TAG_NAME, descending),
                paramMap,
                certMapper);
    }


    private String getSqlOrderDescending(String sql, boolean descending) {
        return descending? sql.replace(";", "DESC;"): sql;
    }

}
