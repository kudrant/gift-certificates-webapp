package com.epam.esm.controller;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class GiftCertificateController {

//    @Autowired
//    private GiftCertificateService giftCertificateService;

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }



    @RequestMapping(value = "/")
    public ModelAndView listGiftCertificates(ModelAndView model) {
        List<GiftCertificate> giftCertificateList = giftCertificateService.list();
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

    @GetMapping ("/get")
    @ResponseBody
    public ResponseEntity<GiftCertificate> getGiftCertificate(@RequestParam("id") long id) {
        return new ResponseEntity<>(giftCertificateService.getGiftCertificateById(id), HttpStatus.OK);
    }


    @PostMapping ("/add")
    public ResponseEntity<Void> addGiftCertificate(@RequestBody GiftCertificate giftCertificate) {

        int result = giftCertificateService.saveGiftCertificate(giftCertificate);
        if (result <= 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping ("/delete")
    public ResponseEntity<Void> deleteGiftCertificate(@RequestParam("id") int id) {
        int result = giftCertificateService.deleteGiftCertificate(id);
        if (result <= 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editGiftCertificate(@RequestBody GiftCertificate giftCertificate) {

        int result = giftCertificateService.updateGiftCertificate(giftCertificate);
        if (result <= 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
