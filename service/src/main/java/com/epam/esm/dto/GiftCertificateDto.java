package com.epam.esm.dto;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificateDto that = (GiftCertificateDto) o;
        return giftCertificate.equals(that.giftCertificate) && tags.equals(that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giftCertificate, tags);
    }
}


