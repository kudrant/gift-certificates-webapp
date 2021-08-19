package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagDao {

    void saveTags(Set<Tag> tags);

    Tag saveTag(Tag tag);

    Tag getTagByName(String tagName);

    Tag getTag(Long id);

    void deleteTag(long id);

    List<Tag> listTags();

    List<Tag> getCertificateTags(GiftCertificate cert);
}
