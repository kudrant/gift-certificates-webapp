package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Set;

public interface GiftCertificateDao {

    GiftCertificate save(GiftCertificate cert, Set<Tag> tags);

    GiftCertificate update(GiftCertificate cert, Set<Tag> tags);

    GiftCertificate get(long id);

    void delete(long id);

    List<GiftCertificate> list();

    GiftCertificate getById(long id);

    List<GiftCertificate> getByTagName(String tagName, String orderColumn, boolean descending);

}
