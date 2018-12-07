package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by ashishc on 06-06-2016.
 */
public class IMAGE_TYPEGetterSetter {



    ArrayList<String> STATUS_CD = new ArrayList<String>();
    ArrayList<String> STATUS = new ArrayList<String>();


    public ArrayList<String> getEdText() {
        return EdText;
    }

    public void setEdText(String edText) {
        EdText.add(edText);
    }

    ArrayList<String> EdText = new ArrayList<String>();

    public ArrayList<String> getImage_Url() {
        return Image_Url;
    }

    public void setImage_Url(String image_Url) {
        Image_Url.add(image_Url);
    }

    ArrayList<String> Image_Url = new ArrayList<String>();



    ArrayList<String> IMAGETYPE_CD = new ArrayList<String>();

    public ArrayList<String> getIMAGETYPE_CD() {
        return IMAGETYPE_CD;
    }

    public void setIMAGETYPE_CD(String IMAGETYPE_CD) {
        this.IMAGETYPE_CD.add(IMAGETYPE_CD);
    }

    public ArrayList<String> getIMAGE_TYPE() {
        return IMAGE_TYPE;
    }

    public void setIMAGE_TYPE(String IMAGE_TYPE) {
        this.IMAGE_TYPE.add(IMAGE_TYPE);
    }

    ArrayList<String> IMAGE_TYPE = new ArrayList<String>();




    public ArrayList<String> getSTATUS_CD() {
        return STATUS_CD;
    }

    public void setSTATUS_CD(String STATUS_CD) {
        this.STATUS_CD.add(STATUS_CD);
    }

    public ArrayList<String> getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS.add(STATUS);
    }





    public String getIMAGE_TYPEXML() {
        return IMAGE_TYPEXML;
    }

    public void setIMAGE_TYPEXML(String IMAGE_TYPEXML) {
        this.IMAGE_TYPEXML = IMAGE_TYPEXML;
    }

    String IMAGE_TYPEXML;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    String ID;
}
