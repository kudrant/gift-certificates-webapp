package com.epam.esm.controller;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.model.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
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
        model. setViewName("gift_cert_form");
        return model;
    }

    @PostMapping ("/add")
    public ResponseEntity<Void> addGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        System.out.println(giftCertificate);
        giftCertificateDAO.save(giftCertificate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveGiftCertificate(@ModelAttribute GiftCertificate giftCertificate) {
        if(giftCertificate.getId() == null)
            giftCertificateDAO.save(giftCertificate);
        else
            giftCertificateDAO.update(giftCertificate);

        return new ModelAndView("redirect:/");
    }

    //@GetMapping

    @ResponseBody

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public GiftCertificate editGiftCertificate(@RequestParam("id") int id) {
        GiftCertificate giftCertificate = giftCertificateDAO.get(id);
        ModelAndView model = new ModelAndView("gift_cert_form");
        model.addObject("giftCertificate", giftCertificate);
        return giftCertificate;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteGiftCertificate(@RequestParam("id") int id) {
        giftCertificateDAO.delete(id);
        return new ModelAndView("redirect:/");
    }
}
