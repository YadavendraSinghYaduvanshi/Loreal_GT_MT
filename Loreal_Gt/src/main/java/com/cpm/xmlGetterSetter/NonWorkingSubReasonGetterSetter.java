package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by deepakp on 1/11/2017.
 */

public class NonWorkingSubReasonGetterSetter {

    String nonworkingsubreason_table;

    ArrayList<String> SUB_REASON_CD=new ArrayList<String>();
    ArrayList<String> SUB_REASON=new ArrayList<String>();

    public ArrayList<String> getSUB_REASON() {
        return SUB_REASON;
    }

    public void setSUB_REASON(String SUB_REASON) {
        this.SUB_REASON.add(SUB_REASON);
    }

    public ArrayList<String> getSUB_REASON_CD() {
        return SUB_REASON_CD;
    }

    public void setSUB_REASON_CD(String SUB_REASON_CD) {
        this.SUB_REASON_CD.add(SUB_REASON_CD);
    }

    public String getNonworkingsubreason_table() {
        return nonworkingsubreason_table;
    }

    public void setNonworkingsubreason_table(String nonworkingsubreason_table) {
        this.nonworkingsubreason_table = nonworkingsubreason_table;
    }

}
