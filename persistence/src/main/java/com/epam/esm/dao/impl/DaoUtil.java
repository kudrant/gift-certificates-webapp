package com.epam.esm.dao.impl;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class DaoUtil {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DaoUtil(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    <T> void updateTable(String sql, Object param) {
        namedParameterJdbcTemplate.update(sql, Collections.singletonMap("param", param));
    }

    void updateTable(String sql, Map<String, Object> info) {
        namedParameterJdbcTemplate.update(sql, info);
    }

    <T> List<T> getAllEntitiesFromTable(String sql, RowMapper<T> mapper) {
        return namedParameterJdbcTemplate.query(sql, mapper);
    }

    <T> long updateReturningId(String sql, T bean) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(bean);
        String[] keyColumnName = {"id"};

        namedParameterJdbcTemplate.update(sql, namedParameters, keyHolder, keyColumnName);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    <T> T getEntityBySql(String sql, Object param, RowMapper<T> rowMapper) {
        return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("param", param), rowMapper);
    }

    <T> List<T> getReferencedEntities(String sql, GiftCertificate cert, RowMapper<T> mapper) {
        return namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(cert), mapper);
    }

    <T> List<T> getEntitiesBySql(String sql, Map<String, String> param, RowMapper<T> rowMapper) {
        return namedParameterJdbcTemplate.query(sql, param, rowMapper);
    }



}
