package com.example.coffeeshop.DTO;

public class Cart {
    private Integer id;
    private Integer id_user;
    private Double total_price;
    private String address;
    private int _status;

    public int getStatus() {
        return _status;
    }

    public void setStatus(int status) {
        _status = status;
    }


    public Cart(Integer id, Integer id_user, Double total_price, String address, int status) {
        this.id = id;
        this.id_user = id_user;
        this.total_price = total_price;
        this.address = address;
        _status = status;
    }

    public Cart() {
    }

    public Cart(Integer id, Integer id_user, Double total_price, String address) {
        this.id = id;
        this.id_user = id_user;
        this.total_price = total_price;
        this.address = address;
    }

    public Integer get_id() {
        return id;
    }

    public void set_id(Integer id) {
        this.id = id;
    }

    public Integer get_id_user() {
        return id_user;
    }

    public void set_id_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
