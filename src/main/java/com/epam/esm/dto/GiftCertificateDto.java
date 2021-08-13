package com.epam.esm.dto;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;

import java.util.List;

public class GiftCertificateDto {
    private GiftCertificate giftCertificate;
    private List<Tag> tags;

    public GiftCertificateDto() {
    }

    public GiftCertificateDto(GiftCertificate giftCertificate, List<Tag> tags) {
        this.giftCertificate = giftCertificate;
        this.tags = tags;
    }

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}


