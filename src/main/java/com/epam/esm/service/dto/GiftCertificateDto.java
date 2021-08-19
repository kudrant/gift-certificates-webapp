package com.epam.esm.service.dto;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.Set;

public class GiftCertificateDto {

    private GiftCertificate giftCertificate;
    private Set<Tag> tags;

    public GiftCertificateDto() {
    }

    public GiftCertificateDto(GiftCertificate giftCertificate, Set<Tag> tags) {

        this.giftCertificate = giftCertificate;
        this.tags = tags;
    }

    public GiftCertificateDto(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    public GiftCertificate getGiftCertificate () {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}


