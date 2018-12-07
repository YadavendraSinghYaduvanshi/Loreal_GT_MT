package com.cpm.GetterSetter;

public class StoreProfileGetterSetter {
    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public int getKycId() {
        return kycId;
    }

    public void setKycId(int kycId) {
        this.kycId = kycId;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }


    public String getGstImg() {
        return gstImg;
    }

    public void setGstImg(String gstImg) {
        this.gstImg = gstImg;
    }


    public String getContactNumber2() {
        return contactNumber2;
    }

    public void setContactNumber2(String contactNumber2) {
        this.contactNumber2 = contactNumber2;
    }

    public int getCity_cd() {
        return city_cd;
    }

    public void setCity_cd(int city_cd) {
        this.city_cd = city_cd;
    }

    public int getState_cd() {
        return state_cd;
    }

    public void setState_cd(int state_cd) {
        this.state_cd = state_cd;
    }

    public String getAddressImg() {
        return addressImg;
    }

    public void setAddressImg(String addressImg) {
        this.addressImg = addressImg;
    }

    private String addressImg;
    private int state_cd;
    private int city_cd;
    private String gstImg;
    private int storeid;
    private String retailerName;
    private String contactNumber;
    private String contactNumber2;
    private String postalAddress;
    private int kycId;
    private String gstNo;

}
