package com.cpm.GetterSetter;

import java.util.ArrayList;

/**
 * Created by deepakp on 5/5/2017.
 */

public class MappingPosmGetterSetter {

    String table_MappingPosm = "";
    ArrayList<String> STATE_CD = new ArrayList<>();
    ArrayList<String> STORE_CD = new ArrayList<>();
    ArrayList<String> STORETYPE_CD = new ArrayList<>();
    ArrayList<String> POSM_CD = new ArrayList<>();

    public ArrayList<String> getSTORE_CD() {
        return STORE_CD;
    }

    public void setSTORE_CD(String STORE_CD) {
        this.STORE_CD.add(STORE_CD);
    }

    public ArrayList<String> getSTATE_CD() {
        return STATE_CD;
    }

    public void setSTATE_CD(String STATE_CD) {
        this.STATE_CD.add(STATE_CD);
    }

    public ArrayList<String> getSTORETYPE_CD() {
        return STORETYPE_CD;
    }

    public void setSTORETYPE_CD(String STORETYPE_CD) {
        this.STORETYPE_CD.add(STORETYPE_CD);
    }

    public ArrayList<String> getPOSM_CD() {
        return POSM_CD;
    }

    public void setPOSM_CD(String POSM_CD) {
        this.POSM_CD.add(POSM_CD);
    }

    public String getTable_MappingPosm() {
        return table_MappingPosm;
    }

    public void setTable_MappingPosm(String table_MappingPosm) {
        this.table_MappingPosm = table_MappingPosm;
    }
}
