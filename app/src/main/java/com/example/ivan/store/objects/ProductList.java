package com.example.ivan.store.objects;

/**
 * Created by Ivan on 24.10.2017.
 */

public class ProductList {
    private String id;
    private String name;
    private String price;
    private String imgSrc;

    private String productTypeId;
    private String information;
    private String imgCount;

    public ProductList(String id, String name, String price, String imgSrc, String productTypeId, String information, String imgCount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgSrc = imgSrc;
        this.productTypeId = productTypeId;
        this.information = information;
        this.imgCount = imgCount;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getImgSrc() { return imgSrc; }

    public void setImgSrc(String imgSrc) { this.imgSrc = imgSrc; }
}
