package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;

import java.util.List;

public interface GiftCertificateDAO {

    int save(GiftCertificate certificate);

    int update(GiftCertificate certificate);

    GiftCertificate get(Integer id);

    int delete(Integer id);

    List<GiftCertificate> list();


}
