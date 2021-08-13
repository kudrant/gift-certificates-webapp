package com.epam.esm.service;

import com.epam.esm.model.Tag;

import java.util.List;

public interface TagService {

    int getTagById(Long id);

    int saveTag(Tag tag);

    int deleteTag(Long id);

    List<Tag> listTags();
}
