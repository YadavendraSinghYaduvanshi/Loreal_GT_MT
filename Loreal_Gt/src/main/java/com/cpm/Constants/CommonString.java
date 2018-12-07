package com.cpm.Constants;

public class CommonString {

    public static final String TABLE_EMP_SALARY = "EMP_SALARY";

    public static final String KEY_WINDOW_CD = "WINDOW_CD";

    public static final String KEY_WINDOW = "WINDOW";

    public static final String KEY_EXISTS = "EXISTS";

    public static final String KEY_SKU_HOLD = "SKU_HOLD";

    public static final String KEY_BRAND_CD = "BRAND_CD";

    public static final String KEY_WINDOW_IMAGE = "WINDOW_IMAGE";

    public static final String TABLE_POSM_DATA_GT = "POSM_DATA_GT";
    public static final String TABLE_POSM_DATA_2 = "POSM_DATA_2";

    public static final String CREATE_TABLE_POSM_DATA_GT = "CREATE TABLE IF NOT EXISTS POSM_DATA_GT (MID INTEGER PRIMARY KEY AUTOINCREMENT,STORE_CD INTEGER,IMAGE VARCHAR,POSM VARCHAR,POSM_CD INTEGER,POSM_QTNY INTEGER)";
    public static final String CREATE_TABLE_POSM_DATA_2 = "CREATE TABLE IF NOT EXISTS POSM_DATA_2 (MID INTEGER PRIMARY KEY AUTOINCREMENT,STORE_CD INTEGER,IMAGE VARCHAR,POSM VARCHAR,POSM_CD INTEGER,REASON_CD INTEGER,REASON VARCHAR,ISEXIST Boolean)";
    public static final String TABLE_STORE_GEOTAGGING = "STORE_GEOTAGGING";
    public static final String TABLE_STORE_DETAILUSER = "STORE_USER_DETAIL";
    public static final String TAG_INTENT_DATA = "INTENT_DATA";
    public static final String TAG_CHANNEL_CD = "CHANNEL_CD";
    public static final String TAG_OBJECT = "OBJECT";

    public static final String CREATE_TABLE_STORE_GEOTAGGING = "CREATE TABLE IF NOT EXISTS "
            + TABLE_STORE_GEOTAGGING
            + " ("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "STORE_ID"
            + " VARCHAR,"
            + "LATITUDE"
            + " VARCHAR,"
            + "LONGITUDE"
            + " VARCHAR,"
            + "GEO_TAG"
            + " VARCHAR,"
            + "FRONT_IMAGE" + " VARCHAR)";

    public static final String SOAP_ACTION = "http://tempuri.org/ModelInfo";
    public static final String NAMESPACE = "http://tempuri.org/";
}
