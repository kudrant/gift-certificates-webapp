package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.GiftCertificateControllerException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.dto.GiftCertificateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificate(@PathVariable long id) {
        try {
            return giftCertificateService.getGiftCertificateById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new GiftCertificateControllerException("Gift Certificate is not found", 40401, HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificate addGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {

        try {
            return giftCertificateService.saveGiftCertificate(giftCertificateDto);
        } catch (DuplicateKeyException e) {
            throw new GiftCertificateControllerException("Name is already exist", 40001, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificate updateGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {

        try {
            return giftCertificateService.updateGiftCertificate(giftCertificateDto);
        } catch (DuplicateKeyException e) {
            throw new GiftCertificateControllerException("Name is already exist", 40001, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteGiftCertificate(@PathVariable long id) {

        try {
            giftCertificateService.deleteGiftCertificate(id);
        }  catch (EmptyResultDataAccessException e) {
            throw new GiftCertificateControllerException("Gift Certificate is not found", 40401, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public List<GiftCertificateDto> search(@RequestParam(defaultValue = "", required = false) String tagName,
                                           @RequestParam(defaultValue = "", required = false) String nameOrDescPart,
                                           @RequestParam(defaultValue = "name", required = false) String orderColumn,
                                           @RequestParam(defaultValue = "false", required = false) boolean descending) {

        try {
            return giftCertificateService.search(tagName, "%" + nameOrDescPart + "%", orderColumn, descending);
        } catch (EmptyResultDataAccessException e) {
            throw new GiftCertificateControllerException("Gift Certificate is not found", 40401, HttpStatus.NOT_FOUND);
        }

    }


}
