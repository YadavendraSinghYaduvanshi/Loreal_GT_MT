package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by yadavendras on 26-08-2016.
 */
public class WindowChecklistAnswerGetterSetter {

    ArrayList<String> ANSWER_CD = new ArrayList<>();
    ArrayList<String> ANSWER = new ArrayList<>();
    ArrayList<String> CHECKLIST_CD = new ArrayList<>();

    String table_window_checklist_answer;

    public ArrayList<String> getANSWER_CD() {
        return ANSWER_CD;
    }

    public void setANSWER_CD(String ANSWER_CD) {
        this.ANSWER_CD.add(ANSWER_CD);
    }

    public ArrayList<String> getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER.add(ANSWER);
    }

    public ArrayList<String> getCHECKLIST_CD() {
        return CHECKLIST_CD;
    }

    public void setCHECKLIST_CD(String CHECKLIST_CD) {
        this.CHECKLIST_CD.add(CHECKLIST_CD);
    }

    public String getTable_window_checklist_answer() {
        return table_window_checklist_answer;
    }

    public void setTable_window_checklist_answer(String table_window_checklist_answer) {
        this.table_window_checklist_answer = table_window_checklist_answer;
    }
}
