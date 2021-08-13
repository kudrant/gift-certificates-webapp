package com.epam.esm.service;


import com.epam.esm.model.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {

    GiftCertificate getGiftCertificateById(Long id);

    int saveGiftCertificate(GiftCertificate cert);

    int updateGiftCertificate(GiftCertificate cert);

    int deleteGiftCertificate(Integer id);

    List<GiftCertificate> list();



}
