package com.epam.esm;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {

    Tag getTagById(Long id);


    Tag saveTag(Tag tag);

    void deleteTag(Long id);

    List<Tag> getTags();

    Set<Tag> getCertificateTags(GiftCertificate cert);
}
