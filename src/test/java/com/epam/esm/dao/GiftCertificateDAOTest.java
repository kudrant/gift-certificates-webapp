package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateDAOTest {
    private DriverManagerDataSource dataSource;
    private GiftCertificateDAO dao;

    @BeforeEach
    void setupBeforeEach() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/GiftCert");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1Qawsed");

        dao = new GiftCertificateDAOImpl(dataSource);
    }


    @Test
    void save() {
        GiftCertificate cert = new GiftCertificate("EPAM", "Junior To Middle Promotion", 10.0, 10);

        int result = dao.save(cert);

        assertTrue(result > 0);

    }

    @Test
    void update() {
        GiftCertificate cert = new GiftCertificate(5, "EPAM-Update", "Middle To Senior Promotion", 50.0, 180);
        int result = dao.update(cert);

        assertTrue(result > 0);
    }

    @Test
    void get() {
        int id = 3;
        GiftCertificate giftCertificate = dao.get(id);

        if (giftCertificate != null)
            System.out.println(giftCertificate);

        assertNotNull(giftCertificate);

    }

    @Test
    void delete() {
        int result = dao.delete(15);
        assertTrue(result > 0);
    }

    @Test
    void list() {
        List<GiftCertificate> certList = dao.list();
        if (!certList.isEmpty())
            for (GiftCertificate cert: certList
                 ) {
                System.out.println(cert);
            }

        assertFalse(certList.isEmpty());
    }

}