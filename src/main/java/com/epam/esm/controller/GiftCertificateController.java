package com.epam.esm.controller;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.model.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveGiftCertificate(@ModelAttribute GiftCertificate giftCertificate) {
        if(giftCertificate.getId() == null)
            giftCertificateDAO.save(giftCertificate);
        else
            giftCertificateDAO.update(giftCertificate);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editGiftCertificate(@RequestParam("id") int id) {
        GiftCertificate giftCertificate = giftCertificateDAO.get(id);
        ModelAndView model = new ModelAndView("gift_cert_form");
        model.addObject("giftCertificate", giftCertificate);
        return model;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteGiftCertificate(@RequestParam("id") int id) {
        giftCertificateDAO.delete(id);
        return new ModelAndView("redirect:/");
    }
}
