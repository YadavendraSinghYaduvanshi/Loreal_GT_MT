package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by ashishc on 06-06-2016.
 */
public class MappingStatusWindows {



    ArrayList<String> STATUS_CD = new ArrayList<String>();
    ArrayList<String> STATUS = new ArrayList<String>();
    ArrayList<String> WINDOW_CD = new ArrayList<String>();
    ArrayList<String> WINDOW = new ArrayList<String>();

    public ArrayList<String> getSKU_HOLD() {
        return SKU_HOLD;
    }

    public void setSKU_HOLD(String SKU_HOLD) {
        this.SKU_HOLD.add(SKU_HOLD);
    }

    public ArrayList<String> getWINDOW() {
        return WINDOW;
    }

    public void setWINDOW(String WINDOW) {
        this.WINDOW.add(WINDOW);
    }

    public ArrayList<String> getWINDOW_CD() {
        return WINDOW_CD;
    }

    public void setWINDOW_CD(String WINDOW_CD) {
        this.WINDOW_CD.add(WINDOW_CD);
    }

    ArrayList<String> SKU_HOLD = new ArrayList<String>();

    ArrayList<String> PLANOGRAM_IMAGE = new ArrayList<String>();
    public ArrayList<String> getPLANOGRAM_IMAGE() {
        return PLANOGRAM_IMAGE;
    }

    public void setPLANOGRAM_IMAGE(String PLANOGRAM_IMAGE) {
        this.PLANOGRAM_IMAGE.add(PLANOGRAM_IMAGE);
    }

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



    public String getMapping_status_windows() {
        return mapping_status_windows;
    }

    public void setMapping_status_windows(String mapping_status_windows) {
        this.mapping_status_windows = mapping_status_windows;
    }

    String mapping_status_windows;


}
