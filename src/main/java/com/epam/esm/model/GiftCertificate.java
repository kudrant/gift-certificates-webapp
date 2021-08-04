package com.epam.esm.model;

import java.util.ArrayList;
import java.util.List;

public class GiftCertificate {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private int duration;
    private List<Tag> tags = new ArrayList<>();

    public GiftCertificate() {

    }

    public GiftCertificate(String name, String description, double price, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public GiftCertificate(Integer id, String name, String description, double price, int duration) {
        this(name, description, price, duration);
        this.id = id;
    }

    public boolean hasTags() {
        return !this.getTags().isEmpty();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "GiftCertificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", tags=" + tags +
                '}';
    }
}
