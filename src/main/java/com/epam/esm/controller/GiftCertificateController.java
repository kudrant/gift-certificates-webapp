package com.epam.esm.controller;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.model.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class GiftCertificateController {

    @Autowired
    private GiftCertificateDAO giftCertificateDAO;

    @RequestMapping(value = "/")
    public ModelAndView listGiftCertificates(ModelAndView model) {
        List<GiftCertificate> giftCertificateList = giftCertificateDAO.list();
        model.addObject("giftCertificateList", giftCertificateList);
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newGiftCertificate(ModelAndView model) {
        GiftCertificate giftCertificate = new GiftCertificate();
        model.addObject("giftCertificate", giftCertificate);
        model.setViewName("gift_cert_form");
        return model;
    }

}
