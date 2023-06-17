package com.example.mobileapp.model;

public class Cart {
    private int ProductPicture;
    private String ProductName;
    private int Quantity;
    private int Price;

    public Cart(){}

    public Cart(int productPicture, String productName, int quantity, int price) {
        ProductPicture = productPicture;
        ProductName = productName;
        Quantity = quantity;
        Price = price;
    }

    public int getProductPicture() {
        return ProductPicture;
    }

    public void setProductPicture(int productPicture) {
        ProductPicture = productPicture;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}