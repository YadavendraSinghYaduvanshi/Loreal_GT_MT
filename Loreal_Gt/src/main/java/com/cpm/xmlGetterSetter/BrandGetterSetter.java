package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by yadavendras on 29-01-2016.
 */
public class BrandGetterSetter {

    public ArrayList<String> getBrand_cd() {
        return brand_cd;
    }

    public void setBrand_cd(String brand_cd) {
        this.brand_cd.add(brand_cd);
    }

    public ArrayList<String> getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.add(brand);
    }

    String brand_master_table;

    ArrayList<String> brand_cd = new ArrayList<String>();
    ArrayList<String> brand = new ArrayList<String>();
    ArrayList<String> brand_sequence = new ArrayList<String>();
    String quantity = "";

    public String getSku_cd() {
        return sku_cd;
    }

    public void setSku_cd(String sku_cd) {
        this.sku_cd = sku_cd;
    }

    String sku_cd = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id = "";

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    ArrayList<String> company_cd = new ArrayList<String>();
    ArrayList<String> category_cd = new ArrayList<String>();

    ArrayList<String> SUB_CATEGORY_CD = new ArrayList<String>();

    public String getBrand_master_table() {
        return brand_master_table;
    }

    public void setBrand_master_table(String brand_master_table) {
        this.brand_master_table = brand_master_table;
    }

    public ArrayList<String> getBrand_sequence() {
        return brand_sequence;
    }

    public void setBrand_sequence(String brand_sequence) {
        this.brand_sequence.add(brand_sequence);
    }

    public void setBrand_cd(ArrayList<String> brand_cd) {
        this.brand_cd = brand_cd;
    }

    public ArrayList<String> getCompany_cd() {
        return company_cd;
    }

    public void setCompany_cd(String company_cd) {
        this.company_cd.add(company_cd);
    }

    public ArrayList<String> getCategory_cd() {
        return category_cd;
    }

    public void setCategory_cd(String category_cd) {
        this.category_cd.add(category_cd);
    }

    public ArrayList<String> getSUB_CATEGORY_CD() {
        return SUB_CATEGORY_CD;
    }

    public void setSUB_CATEGORY_CD(String SUB_CATEGORY_CD) {
        this.SUB_CATEGORY_CD.add(SUB_CATEGORY_CD);
    }
}
