package com.epam.esm.impl;

import com.epam.esm.TagService;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag getTagById(Long id) {
        return tagDao.getTag(id);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagDao.deleteTag(id);
    }

    @Override
    public List<Tag> getTags() {
        return tagDao.listTags();
    }

    @Override
    public Set<Tag> getCertificateTags(GiftCertificate cert) {
        return new HashSet<>(tagDao.getCertificateTags(cert));
    }
}
