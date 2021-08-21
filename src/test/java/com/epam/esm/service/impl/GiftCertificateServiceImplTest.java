package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.dto.GiftCertificateDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {
    private static GiftCertificate certificate;
    private static List<GiftCertificate> certificates;
    private static final String TAG_NAME = "tagName";
    private static final String NAME_DESC_PART = "nameOrDescPart";
    private static final String ORDER_COLUMN = "name";
    private static Set<Tag> tags;


    GiftCertificateDto dto;

    @Mock
    GiftCertificateDaoImpl dao;
    @Mock
    TagServiceImpl tagService;
    @InjectMocks
    GiftCertificateServiceImpl certService;


    private static void initTags() {
        tags = new HashSet<>();
        tags.add(new Tag(1, "mockTag1"));
        tags.add(new Tag(2, "mockTag2"));
    }

    @BeforeAll
    public static void init() {
        initTags();
    }

    private void initCertificate() {
        certificate = new GiftCertificate(1L, "Cert Name", "Cert Description", 25.1, 7);
    }

    private void setDto() {
        dto = new GiftCertificateDto(certificate, tags);
    }

    private void setCertificates() {
        certificates = Collections.singletonList(certificate);
    }

    @BeforeEach
    public void setUp() {
        initCertificate();
        setDto();
        setCertificates();
    }


    @Test
    void getGiftCertificateById() {
        when(dao.get(anyLong())).thenReturn(certificates.get(0));

        assertEquals(certificates.get(0), certService.getGiftCertificateById(anyLong()));
        verify(dao).get(anyLong());
    }

    @Test
    void saveGiftCertificate() {
        when(dao.save(dto.getGiftCertificate(), dto.getTags())).thenReturn(certificate);
        assertEquals(certificate, certService.saveGiftCertificate(dto));
        verify(dao).save(dto.getGiftCertificate(), dto.getTags());
        verifyNoMoreInteractions(dao);
    }

    @Test
    void updateGiftCertificate() {
        when(dao.update(dto.getGiftCertificate(), tags)).thenReturn(certificate);
        assertEquals(certService.updateGiftCertificate(dto), certificate);
        verify(dao).update(certificate, tags);
        verifyNoMoreInteractions(dao);
    }

    @Test
    void deleteGiftCertificate() {
        certService.deleteGiftCertificate(1L);
        verify(dao).delete(1L);
    }

    @Test
    void list() {
        when(dao.list()).thenReturn(certificates);

        assertEquals(certificates, certService.list());
        verify(dao).list();
    }

    @Test
    void search() {
        when(dao.getByTagNameAndNameOrDescrPart(TAG_NAME, NAME_DESC_PART, ORDER_COLUMN, false)).thenReturn(certificates);
        when(tagService.getCertificateTags(any(GiftCertificate.class))).thenReturn(tags);

        assertEquals(Collections.singletonList(new GiftCertificateDto(certificate, tags)),
                certService.search(TAG_NAME, NAME_DESC_PART, ORDER_COLUMN, false));
        verify(dao).getByTagNameAndNameOrDescrPart(TAG_NAME, NAME_DESC_PART, ORDER_COLUMN, false);
        verify(tagService).getCertificateTags(certificate);
    }
}