package com.example.coffeeshop.DTO;

public class DetailCart {
    private Integer id;
    private Integer id_cart;
    private Integer id_product;
    private  double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer get_id() {
        return id;
    }

    public void set_id(Integer id) {
        this.id = id;
    }

    private Integer quantity;

    public DetailCart(Integer id, Integer id_cart, Integer id_product, Integer quantity) {
        this.id = id;
        this.id_cart = id_cart;
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public DetailCart() {
    }



    public Integer get_id_cart() {
        return id_cart;
    }

    public void set_id_cart(Integer id_cart) {
        this.id_cart = id_cart;
    }

    public Integer get_id_product() {
        return id_product;
    }

    public void set_id_product(Integer id_product) {
        this.id_product = id_product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
