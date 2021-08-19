package com.epam.esm.entity;

public class GiftCertificate {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int duration;


    //private List<Tag> tags = new ArrayList<>();

    public GiftCertificate() {

    }

    public GiftCertificate(String name, String description, double price, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }



    public GiftCertificate(Long id, String name, String description, double price, int duration) {
        this(name, description, price, duration);
        this.id = id;
    }

//    public GiftCertificate(Long id, String name, String description, double price, int duration, List<Tag> tags) {
//        this(id, name, description, price, duration);
//        this.tags = tags;
//    }

//    public GiftCertificate(Long id,
//                           String name,
//                           String description,
//                           double price,
//                           int duration,
//                           ZonedDateTime createDate,
//                           ZonedDateTime lastUpdateDate) {
//        this(id, name, description, price, duration);
//    }

//    public boolean hasTags() {
//        return !this.getTags().isEmpty();
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


    @Override
    public String toString() {
        return "GiftCertificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration + '}';
    }
}
