package com.epam.esm.controller;

import com.epam.esm.TagService;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftCertificateControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    private final TagService tagService;


    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public List<Tag> getAllTags() {
        try {
            return tagService.getTags();
        } catch (EmptyResultDataAccessException e) {
            throw new GiftCertificateControllerException("Tag is not found", 40401, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/tags/{id}")
    public Tag getTag(@PathVariable long id) {
        try {
            return tagService.getTagById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new GiftCertificateControllerException("Tag is not found", 40401, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag save(@RequestBody Tag tag) {
        try {
            return tagService.saveTag(tag);
        } catch (DuplicateKeyException ex) {
            throw new GiftCertificateControllerException("Name is already exist", 40001, HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException ex) {
            throw new GiftCertificateControllerException("Tag have no name", 40002, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/tags/{id}")
    public void delete(@PathVariable long id) {
        tagService.deleteTag(id);
    }

}
