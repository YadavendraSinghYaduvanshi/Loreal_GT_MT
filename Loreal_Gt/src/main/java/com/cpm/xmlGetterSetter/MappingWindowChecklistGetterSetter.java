package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by yadavendras on 26-08-2016.
 */
public class MappingWindowChecklistGetterSetter {

    ArrayList<String> WINDOW_CD = new ArrayList<>();
    ArrayList<String> CHECKLIST_CD = new ArrayList<>();

    String table_mapping_window_checklist;

    public ArrayList<String> getWINDOW_CD() {
        return WINDOW_CD;
    }

    public void setWINDOW_CD(String WINDOW_CD) {
        this.WINDOW_CD.add(WINDOW_CD);
    }

    public ArrayList<String> getCHECKLIST_CD() {
        return CHECKLIST_CD;
    }

    public void setCHECKLIST_CD(String CHECKLIST_CD) {
        this.CHECKLIST_CD.add(CHECKLIST_CD);
    }

    public String getTable_mapping_window_checklist() {
        return table_mapping_window_checklist;
    }

    public void setTable_mapping_window_checklist(String table_mapping_window_checklist) {
        this.table_mapping_window_checklist = table_mapping_window_checklist;
    }
}
