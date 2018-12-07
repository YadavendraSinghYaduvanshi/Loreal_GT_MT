package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by ashishc on 29-08-2016.
 */
public class COMPANY_MASTERGetterSetter {

    ArrayList<String> COMPANY_CD=new ArrayList<String>();

    public ArrayList<String> getCOMPANY_CD() {
        return COMPANY_CD;
    }

    public void setCOMPANY_CD(String COMPANY_CD) {
        this.COMPANY_CD.add(COMPANY_CD);
    }

    public ArrayList<String> getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY.add(COMPANY);
    }

    public ArrayList<String> getISCOMPETITOR() {
        return ISCOMPETITOR;
    }

    public void setISCOMPETITOR(String ISCOMPETITOR) {
        this.ISCOMPETITOR.add(ISCOMPETITOR);
    }

    ArrayList<String> COMPANY=new ArrayList<String>();

    ArrayList<String> ISCOMPETITOR=new ArrayList<String>();


    public String getCompany_master_table() {
        return Company_master_table;
    }

    public void setCompany_master_table(String company_master_table) {
        Company_master_table = company_master_table;
    }

    String Company_master_table;






}
