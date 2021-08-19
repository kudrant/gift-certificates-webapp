package com.epam.esm.dao;

class GiftCertificateDAOTest {
//    private DriverManagerDataSource dataSource;
//    private GiftCertificateDao dao;
//
//    @BeforeEach
//    void setupBeforeEach() {
//        dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/GiftCert");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("1Qawsed");
//
//        dao = new GiftCertificateDaoImpl(dataSource);
//    }
//
//
//    @Test
//    void save() {
//        GiftCertificate cert = new GiftCertificate("EPAM", "Junior To Middle Promotion", 10.0, 10);
//
//        Set<Tag> tagSet = new HashSet<>();
//        tagSet.add(new Tag("tag1"));
//        tagSet.add(new Tag("tag2"));
//        tagSet.add(new Tag("tag3"));
//
//        GiftCertificate giftCertificate = dao.save(cert, tagSet);
//
//        assertNotNull(giftCertificate);
//
//    }
//
//    @Test
//    void update() {
//        GiftCertificate cert = new GiftCertificate(5L, "EPAM-Update", "Middle To Senior Promotion", 50.0, 180);
//        int result = dao.update(cert);
//
//        assertTrue(result > 0);
//    }
//
//    @Test
//    void get() {
//        int id = 3;
//        GiftCertificate giftCertificate = dao.get(id);
//
//        if (giftCertificate != null)
//            System.out.println(giftCertificate);
//
//        assertNotNull(giftCertificate);
//
//    }
//
//    @Test
//    void delete() {
//        int result = dao.delete(15);
//        assertTrue(result > 0);
//    }
//
//    @Test
//    void list() {
//        List<GiftCertificate> certList = dao.list();
//        if (!certList.isEmpty())
//            for (GiftCertificate cert: certList
//                 ) {
//                System.out.println(cert);
//            }
//
//        assertFalse(certList.isEmpty());
//    }

}