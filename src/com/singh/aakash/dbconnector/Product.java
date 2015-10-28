package com.singh.aakash.dbconnector;

import java.io.Serializable;

/**
 * Created by aakash on 17-10-2015.
 */
public class Product implements Serializable{
    public static final long serialVersionUID = 42L;
    String category;
    String name;
    String description;
    String price;
    String placeId;

    public Product(String category, String name, String description, String price,String placeId) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.placeId=placeId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  category  + ","+
                name +  ","+
                description +","+
                price
                ;
    }
}
