package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by deepakp on 1/12/2017.
 */

public class WindowNonReasonGetterSetter {

    String windowNonReason_table;

    ArrayList<String> WREASON_CD=new ArrayList<String>();
    ArrayList<String> WREASON=new ArrayList<String>();

    public ArrayList<String> getWREASON() {
        return WREASON;
    }

    public void setWREASON(String WREASON) {
        this.WREASON.add(WREASON);
    }

    public ArrayList<String> getWREASON_CD() {
        return WREASON_CD;
    }

    public void setWREASON_CD(String WREASON_CD) {
        this.WREASON_CD.add(WREASON_CD);
    }

    public String getWindowNonReason_table() {
        return windowNonReason_table;
    }

    public void setWindowNonReason_table(String windowNonReason_table) {
        this.windowNonReason_table = windowNonReason_table;
    }

}
