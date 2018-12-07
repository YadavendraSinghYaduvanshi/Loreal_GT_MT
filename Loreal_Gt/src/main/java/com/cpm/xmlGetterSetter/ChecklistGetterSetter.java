package com.cpm.xmlGetterSetter;

/**
 * Created by yadavendras on 26-08-2016.
 */
public class ChecklistGetterSetter {

    String checklist, checklist_cd, answer_type, answer;

    String WINDOW_CD;
    String CHECKLIST_CD;
    String COMMON_ID;

    public String getCOMMON_ID() {
        return COMMON_ID;
    }

    public void setCOMMON_ID(String COMMON_ID) {
        this.COMMON_ID = COMMON_ID;
    }



    public String getWINDOW_CD() {
        return WINDOW_CD;
    }

    public void setWINDOW_CD(String WINDOW_CD) {
        this.WINDOW_CD = WINDOW_CD;
    }

    public String getCHECKLIST_CD() {
        return CHECKLIST_CD;
    }

    public void setCHECKLIST_CD(String CHECKLIST_CD) {
        this.CHECKLIST_CD = CHECKLIST_CD;
    }

    public String getANSWER_CD() {
        return ANSWER_CD;
    }

    public void setANSWER_CD(String ANSWER_CD) {
        this.ANSWER_CD = ANSWER_CD;
    }

    String ANSWER_CD;






    public String getChecklist() {
        return checklist;
    }

    public void setChecklist(String checklist) {
        this.checklist = checklist;
    }

    public String getChecklist_cd() {
        return checklist_cd;
    }

    public void setChecklist_cd(String checklist_cd) {
        this.checklist_cd = checklist_cd;
    }

    public String getAnswer_type() {
        return answer_type;
    }

    public void setAnswer_type(String answer_type) {
        this.answer_type = answer_type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
