package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by ashishc on 12-08-2016.
 */
public class POSMDATAGetterSetter {


    ArrayList<String> POSM_CD = new ArrayList<>();
    ArrayList<String> POSM = new ArrayList<>();

    String Reason_CD = "0";
    String Reason = "";

    public String getReason_CD() {
        return Reason_CD;
    }

    public void setReason_CD(String reason_CD) {
        Reason_CD = reason_CD;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    boolean isExist = false;

    public String getImage_Url() {
        return Image_Url;
    }

    public void setImage_Url(String image_Url) {
        Image_Url = image_Url;
    }

    String Image_Url="";

    public String getEdText() {
        return EdText;
    }

    public void setEdText(String edText) {
        EdText = edText;
    }

    String EdText;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    String ID;



    public String getMapping_POSM_table() {
        return mapping_POSM_table;
    }

    public void setMapping_POSM_table(String mapping_POSM_table) {
        this.mapping_POSM_table = mapping_POSM_table;
    }

    String mapping_POSM_table;

    public ArrayList<String> getPOSM_CD() {
        return POSM_CD;
    }

    public void setPOSM_CD(String POSM_CD) {
        this.POSM_CD.add(POSM_CD);
    }

    public ArrayList<String> getPOSM() {
        return POSM;
    }

    public void setPOSM(String POSM) {
        this.POSM.add(POSM);
    }

}
