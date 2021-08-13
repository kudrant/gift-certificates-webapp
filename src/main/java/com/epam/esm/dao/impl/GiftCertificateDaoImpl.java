package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(GiftCertificate cert) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(GiftCertificateServiceImpl.getSqlInsertCertificate(cert),
                    Statement.RETURN_GENERATED_KEYS);
            return ps;
        }, keyHolder);

        int lastInsertedCertId = Objects.requireNonNull(keyHolder.getKey()).intValue();

        //checking for tags and adding to DB
        if (cert.hasTags()) {
            jdbcTemplate.update(TagServiceImpl.getSqlInsertTag(cert));
            jdbcTemplate.update(TagServiceImpl.getSqlInsertCertificateTag(cert, lastInsertedCertId));
        }

        return lastInsertedCertId;

    }

    public int saveReturningId(GiftCertificate cert) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(GiftCertificateServiceImpl.getSqlInsertCertificate(cert),
                    Statement.RETURN_GENERATED_KEYS);
            return ps;
        }, keyHolder);

        int lastAdded = jdbcTemplate.update(TagServiceImpl.getSqlInsertTag(cert));

        int lastInsertedCertId = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return lastInsertedCertId;

    }



    @Override
    public int update(GiftCertificate cert) {
        String sqlToUpdateCert = "UPDATE gift_certificate SET name=?, description=?, price=?, duration=? WHERE id=?";
        int result = jdbcTemplate.update(sqlToUpdateCert, cert.getName(),
                cert.getDescription(),
                cert.getPrice(),
                cert.getDuration(),
                cert.getId());

        String sqlToDeleteRefs = "DELETE FROM gift_certificate_tag WHERE gift_certificate_id=" + cert.getId();
        jdbcTemplate.update(sqlToDeleteRefs);

        if (cert.hasTags()) {
            jdbcTemplate.update(TagServiceImpl.getSqlInsertTag(cert));
            jdbcTemplate.update(TagServiceImpl.getSqlInsertCertificateTag(cert, cert.getId()));
        }

        return result;
    }

    @Override
    public GiftCertificate get(long id) {
        String sqlLinkedTags = "SELECT * FROM gift_certificate_tag WHERE gift_certificate_id=" + id;
        List<Tag> tags = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlLinkedTags);

        for (Map<String, Object> row : rows) {
            tags.add(new Tag(row.get("tag_name").toString()));
        }

        String sqlCertificate = "SELECT * FROM gift_certificate WHERE id=" + id;
        ResultSetExtractor<GiftCertificate> extractor = resultSet -> {
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int duration = resultSet.getInt("duration");

                return new GiftCertificate(id, name, description, price, duration, tags);
            }
            return null;
        };

        return jdbcTemplate.query(sqlCertificate, extractor);
    }

    @Override
    public int delete(long id) {
        String sqlToDeleteCert = "DELETE FROM gift_certificate WHERE id=" + id;
        return jdbcTemplate.update(sqlToDeleteCert);
    }

    @Override
    public List<GiftCertificate> list() {
        String sql = "SELECT * FROM gift_certificate";

        RowMapper<GiftCertificate> rowMapper = (resultSet, rowNum) -> {
            long id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            int duration = resultSet.getInt("duration");

            return new GiftCertificate(id, name, description, price, duration);
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}
