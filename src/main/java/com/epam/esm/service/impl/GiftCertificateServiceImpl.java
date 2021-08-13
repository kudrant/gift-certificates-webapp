package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {


    private GiftCertificateDao giftCertificateDao;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

    @Override
    public GiftCertificate getGiftCertificateById(Long id) {
        return giftCertificateDao.get(id);
    }

    @Override
    public int saveGiftCertificate(GiftCertificate cert)
    {
        return giftCertificateDao.save(cert);
    }

    @Override
    public int deleteGiftCertificate(Integer id)
    {
        return giftCertificateDao.delete(id);
    }

    @Override
    public List<GiftCertificate> list() {
        return giftCertificateDao.list();
    }

    @Override
    public int updateGiftCertificate(GiftCertificate cert)
    {
        return giftCertificateDao.update(cert);
    }

    public List<GiftCertificate> listGiftCertificates()
    {
        return giftCertificateDao.list();
    }

    public static String getSqlInsertCertificate(GiftCertificate cert) {
        return "INSERT INTO gift_certificate (name, description, price, duration) VALUES (" +
                "'" + cert.getName() + "', '" +
                cert.getDescription() + "', " +
                cert.getPrice() + ", " +
                cert.getDuration() + ") RETURNING id;";

    }

}
