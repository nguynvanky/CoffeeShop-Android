package com.example.coffeeshop.DTO;

public class Product{
    int _id;
    int _id_cate;

    public int get_id_cate() {
        return _id_cate;
    }

    public void set_id_cate(int _id_cate) {
        this._id_cate = _id_cate;
    }

    String Name;
    Double Price;
    String Image;
    String Description;

    public boolean isFavourite() {
        return Favourite;
    }

    public void setFavourite(boolean favourite) {
        Favourite = favourite;
    }

    boolean Favourite;
    public String getDescription() {
        return Description;
    }

    public  Product(String name, double price, String id_img, String des, int id,boolean fav, int id_cate)
    {
        Name = name;
        Price = price;
        Image = id_img ;
        Description = des;
        this._id  = id;
        this.Favourite = fav;
        this._id_cate = id_cate;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Product() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public Double getPrice() {
        return Price;
    }
}
