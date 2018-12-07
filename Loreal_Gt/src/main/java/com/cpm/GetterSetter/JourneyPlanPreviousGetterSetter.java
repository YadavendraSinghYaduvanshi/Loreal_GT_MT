package com.cpm.GetterSetter;

import java.util.ArrayList;

/**
 * Created by deepakp on 4/10/2017.
 */

public class JourneyPlanPreviousGetterSetter {

    String table_JOURNEY_PLAN_PREV;
    ArrayList<String> STORE_CD = new ArrayList<>();
    ArrayList<String> VISITDATE = new ArrayList<>();
    ArrayList<String> KEYACCOUNT = new ArrayList<>();
    ArrayList<String> STORENAME = new ArrayList<>();
    ArrayList<String> ADDRESS = new ArrayList<>();
    ArrayList<String> CITY = new ArrayList<>();
    ArrayList<String> COVERAGE = new ArrayList<>();
    ArrayList<String> REASON = new ArrayList<>();
    ArrayList<String> STORETYPE = new ArrayList<>();
    String REASON_CD = "";
    String REASON_STR = "";
    ArrayList<String> CHANNEL_CD = new ArrayList<>();

    public ArrayList<String> getCHANNEL_CD() {
        return CHANNEL_CD;
    }

    public void setCHANNEL_CD(String CHANNEL_CD) {
        this.CHANNEL_CD.add(CHANNEL_CD);
    }

    public ArrayList<String> getSTORETYPE() {
        return STORETYPE;
    }

    public void setSTORETYPE(String STORETYPE) {
        this.STORETYPE.add(STORETYPE);
    }

    public String getREASON_STR() {
        return REASON_STR;
    }

    public void setREASON_STR(String REASON_STR) {
        this.REASON_STR = REASON_STR;
    }

    public String getREASON_CD() {
        return REASON_CD;
    }

    public void setREASON_CD(String REASON_CD) {
        this.REASON_CD = REASON_CD;
    }


    public String getTable_JOURNEY_PLAN_PREV() {
        return table_JOURNEY_PLAN_PREV;
    }

    public void setTable_JOURNEY_PLAN_PREV(String table_JOURNEY_PLAN_PREV) {
        this.table_JOURNEY_PLAN_PREV = table_JOURNEY_PLAN_PREV;
    }

    public ArrayList<String> getSTORE_CD() {
        return STORE_CD;
    }

    public void setSTORE_CD(String STORE_CD) {
        this.STORE_CD.add(STORE_CD);
    }

    public ArrayList<String> getVISITDATE() {
        return VISITDATE;
    }

    public void setVISITDATE(String VISITDATE) {
        this.VISITDATE.add(VISITDATE);
    }

    public ArrayList<String> getKEYACCOUNT() {
        return KEYACCOUNT;
    }

    public void setKEYACCOUNT(String KEYACCOUNT) {
        this.KEYACCOUNT.add(KEYACCOUNT);
    }

    public ArrayList<String> getSTORENAME() {
        return STORENAME;
    }

    public void setSTORENAME(String STORENAME) {
        this.STORENAME.add(STORENAME);
    }

    public ArrayList<String> getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS.add(ADDRESS);
    }

    public ArrayList<String> getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY.add(CITY);
    }

    public ArrayList<String> getCOVERAGE() {
        return COVERAGE;
    }

    public void setCOVERAGE(String COVERAGE) {
        this.COVERAGE.add(COVERAGE);
    }

    public ArrayList<String> getREASON() {
        return REASON;
    }

    public void setREASON(String REASON) {
        this.REASON.add(REASON);
    }
}
