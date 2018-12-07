package com.cpm.GetterSetter;

import java.util.ArrayList;

public class StateMasterGetterSetter {

    public int getState_cd() {
        return state_cd;
    }

    public void setState_cd(int state_cd) {
        this.state_cd = state_cd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTable_STATE_MASTER() {
        return table_STATE_MASTER;
    }

    public void setTable_STATE_MASTER(String table_STATE_MASTER) {
        this.table_STATE_MASTER = table_STATE_MASTER;
    }

    private String table_STATE_MASTER;
    private int state_cd;
    private String state;

    public ArrayList<String> getState_list() {
        return state_list;
    }

    public void setState_list(String state_list) {
        this.state_list.add(state_list);
    }

    public ArrayList<String> getState_cd_list() {
        return state_cd_list;
    }

    public void setState_cd_list(String state_cd_list) {
        this.state_cd_list.add(state_cd_list);
    }

    private ArrayList<String> state_cd_list = new ArrayList<>();
    private ArrayList<String> state_list = new ArrayList<>();

}
