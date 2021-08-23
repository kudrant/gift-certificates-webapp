package com.epam.esm.dao.mapper;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GiftCertificateMapper implements RowMapper<GiftCertificate> {
@Override
public GiftCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new GiftCertificate(resultSet.getLong("id"),
        resultSet.getString("name"),
        resultSet.getString("description"),
        resultSet.getDouble("price"),
        resultSet.getInt("duration"));
        }

//public class GiftCertificateMapper implements RowMapper<GiftCertificate> {
//    @Override
//    public GiftCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//        return new GiftCertificate(resultSet.getLong("id"),
//                resultSet.getString("name"),
//                resultSet.getString("description"),
//                resultSet.getDouble("price"),
//                resultSet.getInt("duration"),
//                resultSet.getTimestamp("create_date").toLocalDateTime().atZone(ZoneId.of("GMT+5")),
//                resultSet.getTimestamp("last_update_date").toLocalDateTime().atZone(ZoneId.of("GMT+5")));
//    }
}
