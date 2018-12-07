package com.cpm.GetterSetter;

import java.util.ArrayList;

public class CityMasterGetterSetter {

    public int getCity_cd() {
        return city_cd;
    }

    public void setCity_cd(int city_cd) {
        this.city_cd = city_cd;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getState_cd() {
        return state_cd;
    }

    public void setState_cd(int state_cd) {
        this.state_cd = state_cd;
    }

    public String getTable_CITY_MASTER() {
        return table_CITY_MASTER;
    }

    public void setTable_CITY_MASTER(String table_CITY_MASTER) {
        this.table_CITY_MASTER = table_CITY_MASTER;
    }

    public ArrayList<String> getCity_list() {
        return city_list;
    }

    public void setCity_list(String city_list) {
        this.city_list.add(city_list);
    }

    public ArrayList<String> getCity_cd_list() {
        return city_cd_list;
    }

    public void setCity_cd_list(String city_cd_list) {
        this.city_cd_list.add(city_cd_list);
    }

    public ArrayList<String> getState_cd_list() {
        return state_cd_list;
    }

    public void setState_cd_list(String state_cd_list) {
        this.state_cd_list.add(state_cd_list);
    }

    private ArrayList<String> city_cd_list = new ArrayList<>();
    private ArrayList<String> state_cd_list = new ArrayList<>();
    private ArrayList<String> city_list = new ArrayList<>();
    private String table_CITY_MASTER;
    private int city_cd;
    private String city;
    private int state_cd;

}
