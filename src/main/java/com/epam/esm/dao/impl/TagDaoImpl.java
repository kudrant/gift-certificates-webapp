package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TagDaoImpl implements TagDao {

    private final JdbcTemplate jdbcTemplate;

    public TagDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }




    @Override
    public int saveTag(Tag tag) {
        return 0;
    }

    @Override
    public Tag getTag(Integer id) {
        return null;
    }

    @Override
    public int deleteTag(Integer id) {
        return 0;
    }

    @Override
    public List<Tag> listTags() {
        return null;
    }
}
