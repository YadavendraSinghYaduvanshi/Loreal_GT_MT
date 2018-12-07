package com.cpm.GetterSetter;

import java.util.ArrayList;

/**
 * Created by deepakp on 3/22/2017.
 */

public class MappingPromotion2GetterSetter {

    String table_MAPPING_PROMOTION;
    ArrayList<String> ID = new ArrayList<>();
    ArrayList<String> STORE_CD = new ArrayList<>();
    ArrayList<String> PROMO = new ArrayList<>();

    String reason="", image = "",reason_cd="0";

    boolean exist = true;

    public String getTable_MAPPING_PROMOTION() {
        return table_MAPPING_PROMOTION;
    }

    public void setTable_MAPPING_PROMOTION(String table_MAPPING_PROMOTION) {
        this.table_MAPPING_PROMOTION = table_MAPPING_PROMOTION;
    }

    public ArrayList<String> getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.add(ID);
    }

    public ArrayList<String> getSTORE_CD() {
        return STORE_CD;
    }

    public void setSTORE_CD(String STORE_CD) {
        this.STORE_CD.add(STORE_CD);
    }

    public ArrayList<String> getPROMO() {
        return PROMO;
    }

    public void setPROMO(String PROMO) {
        this.PROMO.add(PROMO);
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason_cd() {
        return reason_cd;
    }

    public void setReason_cd(String reason_cd) {
        this.reason_cd = reason_cd;
    }
}
