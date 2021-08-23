package com.epam.esm.dao.impl;


import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public class TagDaoImpl implements TagDao {

    private final static String SAVE_TAG = "INSERT INTO tag (name) VALUES (:name) ON CONFLICT (name) DO NOTHING;";
    private final static String GET_TAG_ID_BY_NAME = "SELECT * FROM tag WHERE name = :param;";
    private final static String GET_TAG_BY_ID = "SELECT * FROM tag WHERE id = :param;";
    private final static String GET_ALL_TAGS = "SELECT * FROM tag;";
    private final static String DELETE_TAG = "DELETE FROM tag WHERE id = :param;";
    private final static String GET_CERTIFICATE_TAGS = "SELECT tag.id, tag.name FROM tag " +
            "JOIN gift_certificate_tag ref ON tag.id = ref.tag_id " +
            "JOIN gift_certificate ON gift_certificate.id = ref.gift_certificate_id " +
            "WHERE gift_certificate.id = :id;";

    private final DaoUtil daoUtil;
    private static final TagMapper tagMapper = new TagMapper();

    @Autowired
    public TagDaoImpl(DaoUtil daoUtil) {
        this.daoUtil = daoUtil;
    }

    @Override
    public void saveTags(Set<Tag> tags) {
        for (Tag tag : tags
        ) {
            saveTag(tag);
        }
    }

    @Override
    @Transactional
    public Tag saveTag(Tag tag) {
        return getTag(daoUtil.updateReturningId(SAVE_TAG, tag));
    }

    @Override
    public Tag getTag(Long id) {
        return daoUtil.getEntityBySql(GET_TAG_BY_ID, id, tagMapper);
    }

    @Override
    public Tag getTagByName(String tagName) {
        return daoUtil.getSingleResultBySql(GET_TAG_ID_BY_NAME, tagName, tagMapper);
    }

    @Override
    public List<Tag> listTags() {
        return daoUtil.getAllEntitiesFromTable(GET_ALL_TAGS, tagMapper);
    }

    @Override
    public void deleteTag(long id) {
        daoUtil.updateTable(DELETE_TAG, id);
    }

    @Override
    public List<Tag> getCertificateTags(GiftCertificate cert) {
        return daoUtil.getReferencedEntities(GET_CERTIFICATE_TAGS, cert, tagMapper);
    }

}
