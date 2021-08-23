package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return tagService.getTags();
    }

    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag save(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable long id) {
        return new ResponseEntity<>(tagService.getTagById(id), HttpStatus.OK);
    }


    @DeleteMapping("/tags/{id}")
    public void delete(@PathVariable long id) {
        tagService.deleteTag(id);
    }

}
