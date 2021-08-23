package com.epam.esm;


import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {

    GiftCertificate getGiftCertificateById(Long id);

    GiftCertificate saveGiftCertificate(GiftCertificateDto dto);

    GiftCertificate updateGiftCertificate(GiftCertificateDto dto);

    void deleteGiftCertificate(Long id);

    List<GiftCertificate> list();


    List<GiftCertificateDto> search(String tagName, String nameOrDescPart, String orderColumn, boolean descending);
}

