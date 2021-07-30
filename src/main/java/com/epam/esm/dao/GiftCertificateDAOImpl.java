package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class GiftCertificateDAOImpl implements GiftCertificateDAO {

    private final JdbcTemplate jdbcTemplate;

    public GiftCertificateDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(GiftCertificate cert) {
        String sql = "INSERT INTO gift_certificate(name, description, price, duration) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql, cert.getName(),
                cert.getDescription(),
                cert.getPrice(),
                cert.getDuration());
    }

    @Override
    public int update(GiftCertificate cert) {
        String sql = "UPDATE gift_certificate SET name=?, description=?, price=?, duration=? WHERE id=?";
        return jdbcTemplate.update(sql, cert.getName(),
                cert.getDescription(),
                cert.getPrice(),
                cert.getDuration(),
                cert.getId());
    }

    @Override
    public GiftCertificate get(Integer id) {
        String sql = "SELECT * FROM gift_certificate WHERE id=" + id;

        ResultSetExtractor<GiftCertificate> extractor = resultSet -> {
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int duration = resultSet.getInt("duration");

                return new GiftCertificate(id, name, description, price, duration);
            }
            return null;
        };

        return jdbcTemplate.query(sql, extractor);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM gift_certificate WHERE id=" + id;

        return jdbcTemplate.update(sql);
    }

    @Override
    public List<GiftCertificate> list() {
        String sql = "SELECT * FROM gift_certificate";

        RowMapper<GiftCertificate> rowMapper = (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            int duration = resultSet.getInt("duration");

            return new GiftCertificate(id, name, description, price, duration);
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}
