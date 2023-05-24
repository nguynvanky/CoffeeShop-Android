package com.example.coffeeshop.DTO;

public class Product{
    int id;
    int id_cate;

    public int get_id_cate() {
        return id_cate;
    }

    public void set_id_cate(int id_cate) {
        this.id_cate = id_cate;
    }

    String name;
    Double price;
    String image;
    String description;

    public boolean isFavourite() {
        return Favourite;
    }

    public void setFavourite(boolean favourite) {
        this.Favourite = favourite;
    }

    boolean Favourite;
    public String getDescription() {
        return description;
    }

    public  Product(String name, double price, String id_img, String des, int id,boolean fav, int id_cate)
    {
        name = name;
        price = price;
        image = id_img ;
        description = des;
        this.id  = id;
        this.Favourite = fav;
        this.id_cate = id_cate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product() {
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }
}
