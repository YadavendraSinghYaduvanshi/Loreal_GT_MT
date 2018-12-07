package com.cpm.GetterSetter;

import java.util.ArrayList;

/**
 * Created by deepakp on 5/10/2017.
 */

public class NonPOSMReasonGetterSetter {

    String table_NonPosmReason;
    ArrayList<String> PREASON_CD = new ArrayList<>();
    ArrayList<String> PREASON = new ArrayList<>();

    public String getTable_NonPosmReason() {
        return table_NonPosmReason;
    }

    public void setTable_NonPosmReason(String table_NonPosmReason) {
        this.table_NonPosmReason = table_NonPosmReason;
    }

    public ArrayList<String> getPREASON_CD() {
        return PREASON_CD;
    }

    public void setPREASON_CD(String PREASON_CD) {
        this.PREASON_CD.add(PREASON_CD);
    }

    public ArrayList<String> getPREASON() {
        return PREASON;
    }

    public void setPREASON(String PREASON) {
        this.PREASON.add(PREASON);
    }
}
