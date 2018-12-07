package com.cpm.GetterSetter;

import java.util.ArrayList;

/**
 * Created by deepakp on 3/22/2017.
 */

public class MappingPaidVisibilityGetterSetter {


    String table_MappingPaidVisibility = "";
    ArrayList<String> STORE_CD = new ArrayList<>();
    ArrayList<String> DISPLAY_CD = new ArrayList<>();
    ArrayList<String> BRAND_CD = new ArrayList<>();

    public String getTable_MappingPaidVisibility() {
        return table_MappingPaidVisibility;
    }

    public void setTable_MappingPaidVisibility(String table_MappingPaidVisibility) {
        this.table_MappingPaidVisibility = table_MappingPaidVisibility;
    }

    public ArrayList<String> getSTORE_CD() {
        return STORE_CD;
    }

    public void setSTORE_CD(String STORE_CD) {
        this.STORE_CD.add(STORE_CD);
    }

    public ArrayList<String> getDISPLAY_CD() {
        return DISPLAY_CD;
    }

    public void setDISPLAY_CD(String DISPLAY_CD) {
        this.DISPLAY_CD.add(DISPLAY_CD);
    }

    public ArrayList<String> getBRAND_CD() {
        return BRAND_CD;
    }

    public void setBRAND_CD(String BRAND_CD) {
        this.BRAND_CD.add(BRAND_CD);
    }
}
