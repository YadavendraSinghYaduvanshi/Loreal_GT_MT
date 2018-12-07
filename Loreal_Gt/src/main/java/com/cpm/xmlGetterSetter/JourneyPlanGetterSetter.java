package com.cpm.xmlGetterSetter;

import java.io.Serializable;
import java.util.ArrayList;

public class JourneyPlanGetterSetter implements Serializable {

    String table_journey_plan;

    ArrayList<String> store_cd = new ArrayList<String>();
    ArrayList<String> emp_cd = new ArrayList<String>();
    ArrayList<String> VISIT_DATE = new ArrayList<String>();
    ArrayList<String> key_account = new ArrayList<String>();
    ArrayList<String> store_name = new ArrayList<String>();
    ArrayList<String> city = new ArrayList<String>();
    ArrayList<String> store_type = new ArrayList<String>();
    ArrayList<String> category_type = new ArrayList<String>();
    ArrayList<String> uploadStatus = new ArrayList<String>();
    ArrayList<String> checkOutStatus = new ArrayList<String>();
    ArrayList<String> STORE = new ArrayList<String>();
    ArrayList<String> RETAILER_NAME = new ArrayList<String>();
    ArrayList<String> KYC_ID = new ArrayList<String>();
    ArrayList<String> ADDRESS = new ArrayList<String>();
    ArrayList<String> STORE_CONTACT_NO = new ArrayList<String>();
    ArrayList<String> STORE_CONTACT_NO2 = new ArrayList<String>();
    ArrayList<String> CITY_CD = new ArrayList<String>();
    ArrayList<String> STORE_PROFILE_IMAGE = new ArrayList<String>();
    ArrayList<String> GSTIN_NO = new ArrayList<String>();
    ArrayList<String> GSTIN_IMAGE = new ArrayList<String>();
    ArrayList<String> LATTITUDE = new ArrayList<String>();
    ArrayList<String> LONGITUDE = new ArrayList<String>();
    ArrayList<String> STATE_CD = new ArrayList<String>();
    ArrayList<String> GEO_TAG = new ArrayList<String>();
    ArrayList<String> STORETYPE_CD = new ArrayList<String>();
    ArrayList<String> CHANNEL_CD = new ArrayList<String>();
    ArrayList<String> FIRST_VISIT = new ArrayList<String>();

    public ArrayList<String> getSTORE() {
        return STORE;
    }

    public void setSTORE(String STORE) {
        this.STORE.add(STORE);
    }

    public ArrayList<String> getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS.add(ADDRESS);
    }

    public ArrayList<String> getSTORE_CONTACT_NO() {
        return STORE_CONTACT_NO;
    }

    public void setSTORE_CONTACT_NO(String STORE_CONTACT_NO) {
        this.STORE_CONTACT_NO.add(STORE_CONTACT_NO);
    }

    public ArrayList<String> getKYC_ID() {
        return KYC_ID;
    }

    public void setKYC_ID(String KYC_ID) {
        this.KYC_ID.add(KYC_ID);
    }

    public ArrayList<String> getRETAILER_NAME() {
        return RETAILER_NAME;
    }

    public void setRETAILER_NAME(String RETAILER_NAME) {
        this.RETAILER_NAME.add(RETAILER_NAME);
    }

    public ArrayList<String> getCITY_CD() {
        return CITY_CD;
    }

    public void setCITY_CD(String CITY_CD) {
        this.CITY_CD.add(CITY_CD);
    }

    public ArrayList<String> getSTORE_PROFILE_IMAGE() {
        return STORE_PROFILE_IMAGE;
    }

    public void setSTORE_PROFILE_IMAGE(String STORE_PROFILE_IMAGE) {
        this.STORE_PROFILE_IMAGE.add(STORE_PROFILE_IMAGE);
    }

    public ArrayList<String> getSTORE_CONTACT_NO2() {
        return STORE_CONTACT_NO2;
    }

    public void setSTORE_CONTACT_NO2(String STORE_CONTACT_NO2) {
        this.STORE_CONTACT_NO2.add(STORE_CONTACT_NO2);
    }

    public ArrayList<String> getGSTIN_NO() {
        return GSTIN_NO;
    }

    public void setGSTIN_NO(String GSTIN_NO) {
        this.GSTIN_NO.add(GSTIN_NO);
    }

    public ArrayList<String> getGSTIN_IMAGE() {
        return GSTIN_IMAGE;
    }

    public void setGSTIN_IMAGE(String GSTIN_IMAGE) {
        this.GSTIN_IMAGE.add(GSTIN_IMAGE);
    }

    public ArrayList<String> getLATTITUDE() {
        return LATTITUDE;
    }

    public void setLATTITUDE(String LATTITUDE) {
        this.LATTITUDE.add(LATTITUDE);
    }

    public ArrayList<String> getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE.add(LONGITUDE);
    }

    public ArrayList<String> getGEO_TAG() {
        return GEO_TAG;
    }

    public void setGEO_TAG(String GEO_TAG) {
        this.GEO_TAG.add(GEO_TAG);
    }

    public ArrayList<String> getFIRST_VISIT() {
        return FIRST_VISIT;
    }

    public void setFIRST_VISIT(String FIRST_VISIT) {
        this.FIRST_VISIT.add(FIRST_VISIT);
    }

    public ArrayList<String> getSTORETYPE_CD() {
        return STORETYPE_CD;
    }

    public void setSTORETYPE_CD(String STORETYPE_CD) {
        this.STORETYPE_CD.add(STORETYPE_CD);
    }

    public ArrayList<String> getSTATE_CD() {
        return STATE_CD;
    }

    public void setSTATE_CD(String STATE_CD) {
        this.STATE_CD.add(STATE_CD);
    }


    public ArrayList<String> getCheckOutStatus() {
        return checkOutStatus;
    }

    public void setCheckOutStatus(String checkOutStatus) {
        this.checkOutStatus.add(checkOutStatus);
    }

    public ArrayList<String> getVISIT_DATE() {
        return VISIT_DATE;
    }

    public void setVISIT_DATE(String vISIT_DATE) {
        this.VISIT_DATE.add(vISIT_DATE);
    }

    public ArrayList<String> getStore_cd() {
        return store_cd;
    }

    public void setStore_cd(String store_cd) {
        this.store_cd.add(store_cd);
    }

    public ArrayList<String> getEmp_cd() {
        return emp_cd;
    }

    public void setEmp_cd(String emp_cd) {
        this.emp_cd.add(emp_cd);
    }

    public ArrayList<String> getKey_account() {
        return key_account;
    }

    public void setKey_account(String key_account) {
        this.key_account.add(key_account);
    }

    public ArrayList<String> getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name.add(store_name);
    }

    public ArrayList<String> getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city.add(city);
    }

    public ArrayList<String> getStore_type() {
        return store_type;
    }

    public void setStore_type(String store_type) {
        this.store_type.add(store_type);
    }

    public ArrayList<String> getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type.add(category_type);
    }

    public ArrayList<String> getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus.add(uploadStatus);
    }

    public String getTable_journey_plan() {
        return table_journey_plan;
    }

    public void setTable_journey_plan(String table_journey_plan) {
        this.table_journey_plan = table_journey_plan;
    }

    public ArrayList<String> getCHANNEL_CD() {
        return CHANNEL_CD;
    }

    public void setCHANNEL_CD(String CHANNEL_CD) {
        this.CHANNEL_CD.add(CHANNEL_CD);
    }
}
