package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagServiceImpl implements TagService {

      private TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }



    public static String getSqlInsertTag(GiftCertificate cert) {
        StringBuilder sqlInsertTag = new StringBuilder("INSERT INTO tag (name) VALUES ");
        for (Tag tag : cert.getTags()
        ) {
            sqlInsertTag.append("('");
            sqlInsertTag.append(tag.getName());
            sqlInsertTag.append("'), ");
        }
        if (sqlInsertTag.length() > 2)
            sqlInsertTag.deleteCharAt(sqlInsertTag.length() - 2);
        sqlInsertTag.append("ON CONFLICT (name) DO NOTHING;");
        return sqlInsertTag.toString();
    }


    public static String getSqlInsertCertificateTag(GiftCertificate cert, long lastInsertedCertId) {
        StringBuilder sqlInsertCertificateTag = new StringBuilder("INSERT INTO gift_certificate_tag (gift_certificate_id, tag_name) VALUES ");
        for (Tag tag : cert.getTags()
        ) {
            sqlInsertCertificateTag.append("(");
            sqlInsertCertificateTag.append(lastInsertedCertId);
            sqlInsertCertificateTag.append(", '");
            sqlInsertCertificateTag.append(tag.getName());
            sqlInsertCertificateTag.append("'), ");

        }
        if (sqlInsertCertificateTag.length() > 2) {
            sqlInsertCertificateTag.replace(sqlInsertCertificateTag.length() - 2, sqlInsertCertificateTag.length(), ";");
        }

        return sqlInsertCertificateTag.toString();
    }

    @Override
    public int getTagById(Long id) {
        return 0;
    }

    @Override
    public int saveTag(Tag tag) {
        return 0;
    }

    @Override
    public int deleteTag(Long id) {
        return 0;
    }

    @Override
    public List<Tag> listTags() {
        return null;
    }
}
