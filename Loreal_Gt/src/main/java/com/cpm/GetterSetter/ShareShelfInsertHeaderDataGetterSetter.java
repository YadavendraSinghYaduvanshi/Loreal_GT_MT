package com.cpm.GetterSetter;

/**
 * Created by yadavendras on 16-03-2017.
 */

public class ShareShelfInsertHeaderDataGetterSetter {

    String store_cd="0";
    String categorycd;
    String category;
    String quantity="";

    String category_qty ="0";
    String brand_cd;
    String brand_qty = "";
    String image_name = "";

    public String getCategory_qty() {
        return category_qty;
    }

    public void setCategory_qty(String category_qty) {
        this.category_qty = category_qty;
    }

    public String getBrand_cd() {
        return brand_cd;
    }

    public void setBrand_cd(String brand_cd) {
        this.brand_cd = brand_cd;
    }

    public String getBrand_qty() {
        return brand_qty;
    }

    public void setBrand_qty(String brand_qty) {
        this.brand_qty = brand_qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id = "0";


    public String getStore_cd() {
        return store_cd;
    }

    public void setStore_cd(String store_cd) {
        this.store_cd = store_cd;
    }

    public String getCategorycd() {
        return categorycd;
    }

    public void setCategorycd(String categorycd) {
        this.categorycd = categorycd;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
}
