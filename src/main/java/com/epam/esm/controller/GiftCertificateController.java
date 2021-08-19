package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.GiftCertificateControllerException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.dto.GiftCertificateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class GiftCertificateController {

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

    @GetMapping("/new")
    public ModelAndView newGiftCertificate(ModelAndView model) {
        GiftCertificate giftCertificate = new GiftCertificate();
        model.addObject("giftCertificate", giftCertificate);
        model.setViewName("gift_cert_form");
        return model;
    }

    @GetMapping ("/{id}")
    @ResponseBody
    public ResponseEntity<GiftCertificate> getGiftCertificate(@PathVariable long id) {
        return new ResponseEntity<>(giftCertificateService.getGiftCertificateById(id), HttpStatus.OK);
    }


    @PostMapping ("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificate addGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {

        try {
            return giftCertificateService.saveGiftCertificate(giftCertificateDto);
        } catch (DuplicateKeyException e) {
            throw new GiftCertificateControllerException("Name is already exist", 40001, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GiftCertificateDto> updateGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {

        GiftCertificate giftCertificate = giftCertificateService.updateGiftCertificate(giftCertificateDto);
        if (giftCertificate ==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        giftCertificateDto.setGiftCertificate(giftCertificate);
        return new ResponseEntity<>(giftCertificateDto, HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteGiftCertificate(@PathVariable long id) {
        giftCertificateService.deleteGiftCertificate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<GiftCertificateDto> search(@RequestParam(defaultValue = "", required = false) String tagName,
                                           @RequestParam(defaultValue = "", required = false) String nameOrDescPart,
                                           @RequestParam(defaultValue = "name", required = false) String orderColumn,
                                           @RequestParam(defaultValue = "false", required = false) boolean descending) {

        return giftCertificateService.search(tagName, nameOrDescPart, orderColumn, descending);
    }


}
