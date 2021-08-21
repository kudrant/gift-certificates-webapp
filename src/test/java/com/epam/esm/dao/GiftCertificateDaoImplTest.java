package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GiftCertificateDaoImplTest extends InMemoryEmbeddedDatabase {

    @Autowired
    private GiftCertificateDao certDao;
    private GiftCertificate certificate;
    private Set<Tag> tags;

    private void mockInit() {
        certificate = mock(GiftCertificate.class);
        when(certificate.getName()).thenReturn("Mock certificate");
        when(certificate.getDescription()).thenReturn("Mock description");
        when(certificate.getPrice()).thenReturn(new Double("23.3"));
        when(certificate.getDuration()).thenReturn(111);
        tags = new HashSet<>();
        tags.add(new Tag(1, "song"));
        //sortTypes = new ArrayList<>();
    }

    @BeforeEach
    void setUp() throws SQLException {
        super.setUp();
        mockInit();
    }

    @AfterEach
    void destroy() {
        super.destroy();
    }

    @Test
    public void save() {
        GiftCertificate savedCertificate = certDao.save(certificate, tags);
        assertEquals(savedCertificate, certDao.getById(8));
//        when(certificate.getName()).thenReturn(null);
//        assertThrows(DataIntegrityViolationException.class, () -> certDao.save(certificate, tags));
//        when(certificate.getName()).thenReturn("EPAM1");
//        assertThrows(DuplicateKeyException.class, () -> certDao.save(certificate, tags));
    }


}