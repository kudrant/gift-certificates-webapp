package com.epam.esm.dao;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;

import java.util.List;

public interface TagDao {

    int saveTag(Tag tag);

    Tag getTag(Integer id);

    int deleteTag(Integer id);

    List<Tag> listTags();
}
