package com.cpm.GetterSetter;

import java.util.ArrayList;

public class KYCMasterGetterSetter {

    public String getKycMaster_table() {
        return kycMaster_table;
    }

    public void setKycMaster_table(String kycMaster_table) {
        this.kycMaster_table = kycMaster_table;
    }

    public ArrayList<Integer> getKycId() {
        return kycId;
    }

    public void setKycId(Integer kycId) {
        this.kycId.add(kycId);
    }

    public ArrayList<String> getKyc() {
        return Kyc;
    }

    public void setKyc(String kyc) {
        Kyc.add(kyc);
    }

    public String kycMaster_table;
    ArrayList<Integer> kycId = new ArrayList<>();
    ArrayList<String> Kyc = new ArrayList<>();
}
