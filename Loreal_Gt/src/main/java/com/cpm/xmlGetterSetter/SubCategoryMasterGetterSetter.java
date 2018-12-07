package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by yadavendras on 10-03-2017.
 */

public class SubCategoryMasterGetterSetter {

    String table_SUB_CATEGORY_MASTER;

    ArrayList<String> SUB_CATEGORY_CD = new ArrayList<>();
    ArrayList<String> SUB_CATEGORY = new ArrayList<>();
    ArrayList<String> CATEGORY_CD = new ArrayList<>();
    ArrayList<String> SUB_CATEGORY_SEQUENCE = new ArrayList<>();


    public String getTable_SUB_CATEGORY_MASTER() {
        return table_SUB_CATEGORY_MASTER;
    }

    public void setTable_SUB_CATEGORY_MASTER(String table_SUB_CATEGORY_MASTER) {
        this.table_SUB_CATEGORY_MASTER = table_SUB_CATEGORY_MASTER;
    }

    public ArrayList<String> getSUB_CATEGORY_CD() {
        return SUB_CATEGORY_CD;
    }

    public void setSUB_CATEGORY_CD(String SUB_CATEGORY_CD) {
        this.SUB_CATEGORY_CD.add(SUB_CATEGORY_CD);
    }

    public ArrayList<String> getSUB_CATEGORY() {
        return SUB_CATEGORY;
    }

    public void setSUB_CATEGORY(String SUB_CATEGORY) {
        this.SUB_CATEGORY.add(SUB_CATEGORY);
    }

    public ArrayList<String> getCATEGORY_CD() {
        return CATEGORY_CD;
    }

    public void setCATEGORY_CD(String CATEGORY_CD) {
        this.CATEGORY_CD.add(CATEGORY_CD);
    }

    public ArrayList<String> getSUB_CATEGORY_SEQUENCE() {
        return SUB_CATEGORY_SEQUENCE;
    }

    public void setSUB_CATEGORY_SEQUENCE(String SUB_CATEGORY_SEQUENCE) {
        this.SUB_CATEGORY_SEQUENCE.add(SUB_CATEGORY_SEQUENCE);
    }
}
