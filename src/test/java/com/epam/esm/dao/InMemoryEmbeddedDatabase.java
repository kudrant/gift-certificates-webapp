package com.epam.esm.dao;


import com.epam.esm.config.SpringMvcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.sql.SQLException;

@SpringJUnitWebConfig(SpringMvcConfig.class)
@ContextConfiguration(classes = {SpringMvcConfig.class}, loader = AnnotationConfigContextLoader.class)
public class InMemoryEmbeddedDatabase {
    private static final String CREATE_GIFT_CERTIFICATE_TABLE = "CREATE TABLE IF NOT EXISTS gift_certificate (" +
            "id SERIAL PRIMARY KEY NOT NULL, " +
            "name CHARACTER VARYING(255) UNIQUE NOT NULL, " +
            "description CHARACTER VARYING(255) NOT NULL DEFAULT 'empty', " +
            "price DOUBLE PRECISION NOT NULL DEFAULT 0.0, " +
            "duration INTEGER NOT NULL DEFAULT 1), " +
            "create_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT clock_timestamp(), " +
            "last_update_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT clock_timestamp(); ";

    private static final String CREATE_TAG_TABLE = "CREATE TABLE IF NOT EXISTS tag (" +
            "id BIGSERIAL PRIMARY KEY NOT NULL, " +
            "name CHARACTER VARYING(255) UNIQUE NOT NULL);";

    private static final String CREATE_GIFT_CERTIFICATE_TAG_TABLE = "CREATE TABLE IF NOT EXISTS gift_certificate_tag (" +
            "tag_id BIGINT, " +
            "gift_certificate_id BIGINT, " +
            "PRIMARY KEY (gift_certificate_id, tag_id), " +
            "CONSTRAINT FK_gift_certificate_id FOREIGN KEY (gift_certificate_id) " +
            "CONSTRAINT FK_tag_id FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE ON UPDATE CASCADE, " +
            "REFERENCES gift_certificate (id) ON DELETE CASCADE ON UPDATE CASCADE);";

    private static final String DROP_GIFT_CERTIFICATE_TABLE = "DROP TABLE gift_certificate;";
    private static final String DROP_TAG_TABLE = "DROP TABLE tag;";
    private static final String DROP_GIFT_CERTIFICATE_TAG_TABLE = "DROP TABLE gift_certificate_tag;";

    private static final String FILL_GIFT_CERTIFICATE_TABLE = "INSERT INTO gift_certificate " +
            "(name, description, price, duration) VALUES " +
            "('EPAM1', 'EPAM1 certificate', 100.25, 10), " +
            "('EPAM2', 'EPAM2 certificate', 15.34, 20), " +
            "('EPAM3', 'EPAM3 certificate', 31.0, 30), " +
            "('EPAM4', 'EPAM4 certificate', 365, 40), " +
            "('EPAM5', 'EPAM5 certificate', 35.5, 50), " +
            "('EPAM6', 'EPAM6 certificate', 47.8, 60), " +
            "('EPAM7', 'EPAM7 certificate', 123.0, 70);";

    private static final String FILL_TAG_TABLE = "INSERT INTO tag (name) VALUES " +
            "('song'), " +
            "('concert'), " +
            "('violin'), " +
            "('anniversary'), " +
            "('summer'), " +
            "('rock');";

    private static final String FILL_GIFT_CERTIFICATE_TAG_TABLE = "INSERT INTO gift_certificate_tag " +
            "(tag_id, gift_certificate_id) VALUES " +
            "(1, 1), " +
            "(1, 2), " +
            "(2, 1), " +
            "(2, 3), " +
            "(3, 4), " +
            "(3, 1), " +
            "(3, 3), " +
            "(4, 5), " +
            "(5, 2), " +
            "(5, 4), " +
            "(6, 3), " +
            "(7, 1);";

    @Autowired
    JdbcTemplate jdbcTemplate;


    private String initSql() {

        StringBuilder sqlSb = new StringBuilder();
        sqlSb.append(CREATE_GIFT_CERTIFICATE_TABLE).append(" ")
                .append(CREATE_TAG_TABLE).append(" ")
                .append(CREATE_GIFT_CERTIFICATE_TAG_TABLE).append(" ")
                .append(FILL_GIFT_CERTIFICATE_TABLE).append(" ")
                .append(FILL_TAG_TABLE).append(" ")
                .append(FILL_GIFT_CERTIFICATE_TAG_TABLE);
        return sqlSb.toString();

//        return String.join(" ",
//                CREATE_TAG_TABLE,
//                CREATE_GIFT_CERTIFICATE_TABLE,
//                CREATE_GIFT_CERTIFICATE_TAG_TABLE,
//                FILL_TAG_TABLE,
//                FILL_GIFT_CERTIFICATE_TABLE,
//                FILL_GIFT_CERTIFICATE_TAG_TABLE);
    }

    void destroy() {
        jdbcTemplate.execute(getDestroySqlScript());
    }

    private String getDestroySqlScript() {
        StringBuilder sqlSb = new StringBuilder();
        sqlSb.append(DROP_GIFT_CERTIFICATE_TAG_TABLE).append(" ")
                .append(DROP_GIFT_CERTIFICATE_TABLE).append(" ")
                .append(DROP_TAG_TABLE).append(" ");
        return sqlSb.toString();

//        return String.join(" ",
//                DROP_TAG_GIFT_CERTIFICATE_TABLE,
//                DROP_TAG_TABLE,
//                DROP_GIFT_CERTIFICATE_TABLE);
    }

    void setUp() throws SQLException {
        jdbcTemplate.execute(initSql());
    }

}
