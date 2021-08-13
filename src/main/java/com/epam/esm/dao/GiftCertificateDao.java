package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;

import java.util.List;

public interface GiftCertificateDao {

    int save(GiftCertificate certificate);

    int update(GiftCertificate certificate);

    GiftCertificate get(long id);

    int delete(long id);

    List<GiftCertificate> list();


}
