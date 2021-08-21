package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {


    private static Set<Tag> tags;
    private static List<Tag> tagList;

    @Mock
    private TagDaoImpl tagDao;


    @InjectMocks
    private TagServiceImpl tagService;

    private static void initTags(){
        tags = new HashSet<>();
        tags.add(new Tag(1, "mockTag1"));
        tags.add(new Tag(2, "mockTag2"));

        tagList = new ArrayList<>(tags);
    }

    @BeforeAll
    public static void init() {
        initTags();
    }

    @Test
    void getTagById() {
        when(tagDao.getTag(anyLong())).thenReturn(tagList.get(0));
        assertEquals(tagList.get(0), tagService.getTagById(anyLong()));
        verify(tagDao).getTag(anyLong());
        verifyNoMoreInteractions(tagDao);
    }

    @Test
    void saveTag() {
        when(tagDao.saveTag(any(Tag.class))).thenReturn(any(Tag.class));
        tagService.saveTag(new Tag());

        verify(tagDao).saveTag(new Tag());
        verifyNoMoreInteractions(tagDao);
    }

    @Test
    void deleteTag() {
        tagService.deleteTag(anyLong());

        verify(tagDao).deleteTag(anyLong());
        verifyNoMoreInteractions(tagDao);
    }

    @Test
    void getTags() {
        when(tagDao.listTags()).thenReturn(tagList);

        assertEquals(tagService.getTags(), tagList);
        verify(tagDao).listTags();
        verifyNoMoreInteractions(tagDao);
    }

    @Test
    void getCertificateTags() {
        when(tagDao.getCertificateTags(any(GiftCertificate.class))).thenReturn(tagList);
        assertEquals(tags, tagService.getCertificateTags(new GiftCertificate()));
        verifyNoMoreInteractions(tagDao);
    }
}