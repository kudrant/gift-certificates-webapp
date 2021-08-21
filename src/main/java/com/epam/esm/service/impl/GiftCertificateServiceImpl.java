package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.GiftCertificateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {


    private final GiftCertificateDao giftCertificateDao;
    private final TagService tagService;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagService tagService) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagService = tagService;
    }

    @Override
    public GiftCertificate getGiftCertificateById(Long id) {
        return giftCertificateDao.get(id);
    }

    @Override
    public GiftCertificate saveGiftCertificate(GiftCertificateDto dto) {
        GiftCertificate cert = dto.getGiftCertificate();
        Set<Tag> tags = dto.getTags();
        return giftCertificateDao.save(cert, tags);
    }

    @Override
    public GiftCertificate updateGiftCertificate(GiftCertificateDto dto) {
        GiftCertificate cert = dto.getGiftCertificate();
        Set<Tag> tags = dto.getTags();
        return giftCertificateDao.update(cert, tags);
    }

    @Override
    public void deleteGiftCertificate(Long id) {
        giftCertificateDao.delete(id);
    }

    @Override
    public List<GiftCertificate> list() {
        return giftCertificateDao.list();
    }

    @Override
    public List<GiftCertificateDto> search(String tagName, String nameOrDescPart, String orderColumn, boolean descending) {
        List<GiftCertificateDto> certificatesWithTags;
        switch (getSearchType(tagName, nameOrDescPart)) {
            case BY_TAG:
                certificatesWithTags = getGiftCertificateDtoWithCerts(
                        giftCertificateDao.getByTagName(tagName, orderColumn, descending));
                fillGiftCertificateDtoWithTags(certificatesWithTags);
                return certificatesWithTags;
            case BY_NAME_OR_DESCR_PART:
                certificatesWithTags = getGiftCertificateDtoWithCerts(
                        giftCertificateDao.getByNameOrDescrPart(nameOrDescPart, orderColumn, descending));
                fillGiftCertificateDtoWithTags(certificatesWithTags);
                return certificatesWithTags;
            case BY_TAG_AND_NAME_OR_DESCR_PART:
                certificatesWithTags = getGiftCertificateDtoWithCerts(
                        giftCertificateDao.getByTagNameAndNameOrDescrPart(tagName, nameOrDescPart, orderColumn, descending));
                fillGiftCertificateDtoWithTags(certificatesWithTags);
                return certificatesWithTags;
            default:
                certificatesWithTags = getGiftCertificateDtoWithCerts(giftCertificateDao.list());
                fillGiftCertificateDtoWithTags(certificatesWithTags);
                return certificatesWithTags;
        }
    }

    private List<GiftCertificateDto> getGiftCertificateDtoWithCerts(List<GiftCertificate> certList) {
        List<GiftCertificateDto> dtoList = new ArrayList<>();
        certList.forEach(cert -> dtoList.add(new GiftCertificateDto(cert)));
        return dtoList;
    }

    private void fillGiftCertificateDtoWithTags(List<GiftCertificateDto> certDtoList) {
        certDtoList.forEach(dto -> dto.setTags(tagService.getCertificateTags(dto.getGiftCertificate())));
    }

    private boolean isParamExist(String param) {
        return param.trim().length() > 0;
    }

    private SearchType getSearchType(String tagName, String nameOrDescPart) {
        if (isParamExist(tagName) && isParamExist(nameOrDescPart))
            return SearchType.BY_TAG_AND_NAME_OR_DESCR_PART;
        else if (isParamExist(tagName))
            return SearchType.BY_TAG;
        else if (isParamExist(nameOrDescPart))
            return SearchType.BY_NAME_OR_DESCR_PART;
        else return SearchType.ALL;
    }
}
