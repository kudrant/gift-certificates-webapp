package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GiftCertificateDAOImpl implements GiftCertificateDAO {

    private final JdbcTemplate jdbcTemplate;


    public GiftCertificateDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(GiftCertificate cert) {

        String sqlInsertCertificate = "INSERT INTO gift_certificate (name, description, price, duration) VALUES (" +
                "'" + cert.getName() + "', '" +
                cert.getDescription() + "', " +
                cert.getPrice()  + ", " +
                cert.getDuration() + ") RETURNING id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsertCertificate,
                    Statement.RETURN_GENERATED_KEYS);
            return ps;
        }, keyHolder);

        int lastInsertedCertId = keyHolder.getKey().intValue();



        //checking for tags and adding to DB
        if (cert.hasTags()) {
            StringBuilder sqlInsertTag = new StringBuilder("INSERT INTO tag (name) VALUES ");
            StringBuilder sqlCertificateTag = new StringBuilder("INSERT INTO gift_certificate_tag (gift_certificate_id, tag_name) VALUES ");
            for (Tag tag: cert.getTags()
                 ) {
                sqlInsertTag.append("('");
                sqlInsertTag.append(tag.getName());
                sqlInsertTag.append("'), ");

                sqlCertificateTag.append("(");
                sqlCertificateTag.append(lastInsertedCertId);
                sqlCertificateTag.append(", '");
                sqlCertificateTag.append(tag.getName());
                sqlCertificateTag.append("'), ");

            }
            if (sqlInsertTag.length() > 2)
                sqlInsertTag.deleteCharAt(sqlInsertTag.length() - 2);
            sqlInsertTag.append("ON CONFLICT (name) DO NOTHING;");

            if (sqlCertificateTag.length() > 2) {
                sqlCertificateTag.replace(sqlCertificateTag.length() - 2, sqlCertificateTag.length(),";");
            }

            jdbcTemplate.update(sqlInsertTag.toString());
            jdbcTemplate.update(sqlCertificateTag.toString());
        }

        return lastInsertedCertId;

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
