package com.cpm.GetterSetter;

import java.util.ArrayList;

/**
 * Created by deepakp on 3/23/2017.
 */

public class NonPromotionReasonGetterSetter {
    String table_NonPromotionReason;
    ArrayList<String> PREASON_CD = new ArrayList<>();
    ArrayList<String> PREASON = new ArrayList<>();

    public String getTable_NonPromotionReason() {
        return table_NonPromotionReason;
    }

    public void setTable_NonPromotionReason(String table_NonPromotionReason) {
        this.table_NonPromotionReason = table_NonPromotionReason;
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
