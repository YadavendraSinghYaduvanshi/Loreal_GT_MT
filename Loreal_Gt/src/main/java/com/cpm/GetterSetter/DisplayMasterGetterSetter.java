package com.cpm.GetterSetter;

import java.util.ArrayList;

/**
 * Created by deepakp on 3/22/2017.
 */

public class DisplayMasterGetterSetter {

    String table_DisplayMaster;
    ArrayList<String> DISPLAY_CD = new ArrayList<>();
    ArrayList<String> DISPLAY = new ArrayList<>();

    Boolean isExist = true;
    String Image = "";
    String Remark = "";
    String brand_cd;


    public Boolean getExist() {
        return isExist;
    }

    public void setExist(Boolean exist) {
        isExist = exist;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }


    public String getTable_DisplayMaster() {
        return table_DisplayMaster;
    }

    public void setTable_DisplayMaster(String table_DisplayMaster) {
        this.table_DisplayMaster = table_DisplayMaster;
    }

    public ArrayList<String> getDISPLAY_CD() {
        return DISPLAY_CD;
    }

    public void setDISPLAY_CD(String DISPLAY_CD) {
        this.DISPLAY_CD.add(DISPLAY_CD);
    }

    public ArrayList<String> getDISPLAY() {
        return DISPLAY;
    }

    public void setDISPLAY(String DISPLAY) {
        this.DISPLAY.add(DISPLAY);
    }

    public String getBrand_cd() {
        return brand_cd;
    }

    public void setBrand_cd(String brand_cd) {
        this.brand_cd = brand_cd;
    }
}
