package com.cpm.Constants;

import android.os.Environment;

public class CommonString1 {

    public static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/.LorealGT_Images_New/";
    public static final String FILE_PATH_FOR_BULK_UPLOAD = Environment.getExternalStorageDirectory() + "/LorealGT_Images/";
    public static final String OLD_FILE_PATH = Environment.getExternalStorageDirectory() + "/LorealGT_Images_New/";
    public static final String PLS_FILL_DATA = "Please fill the data";
    public static final String CALLS_INVALID_DATA = "Productive Call cannot be greater than Total Calls";
    public static final String ONBACK_ALERT_MESSAGE = "Unsaved data will be lost - Do you want to continue?";
    public static final String DATA_DELETE_ALERT_MESSAGE = "Saved data will be lost - Do you want to continue?";
    public static final String DATA_ALERT = " Network Problem.";
    // preferenec keys

    public static final String KEY_QUESTION_CD = "question_cd";
    public static final String KEY_ANSWER_CD = "answer_cd";
    public static final String KEY_IS_QUIZ_DONE = "is_quiz_done";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_REMEMBER = "remember";
    public static final String KEY_PATH = "path";
    public static final String KEY_VERSION = "version";
    public static final String KEY_LOGIN_DATA = "login_data";
    public static final String METHOD_UPLOAD_XML = "DrUploadXml";
    public static final String MEHTOD_UPLOAD_COVERAGE_STATUS = "UploadCoverage_Status";
    // public static final String KEY_APPVERSION = "1.1";
    public static final String KEY_USER_TYPE = "RIGHTNAME";
    public static final String KEY_YYYYMMDD_DATE = "yyyymmddDate";
    public static final String KEY_DATE = "date";
    public static final String MID = "MID";
    public static final String KEY_P = "P";
    public static final String KEY_D = "D";
    public static final String KEY_U = "U";
    public static final String KEY_C = "Y";
    public static final String KEY_GEO_N = "N";
    public static final String KEY_GEO_Y = "Y";
    public static final String KEY_FROM_STORELIST = "FROM_STORELIST";
    public static final String KEY_CHECK_IN = "I";
    public static final String KEY_INVALID = "INVALID";
    public static final String STORE_STATUS_LEAVE = "L";
    public static final String KEY_VALID = "Valid";
    public static final String KEY_STORE_IN_TIME = "Store_in_time";
    public static final String SOAP_ACTION = "http://tempuri.org/";
    public static final String KEY_MERCHANDISER_ID = "MERCHANDISER_ID";
    public static final String ERROR = " Network Problem ";
    public static final String KEY_SUCCESS_chkout = "Success";
    public static final String KEY_NOTICE_BOARD = "NOTICE_BOARD";
    public static final String KEY_QUIZ_URL = "QUIZ_URL";
    public static final String TABLE_GEO_TAG_MAPPING = "GEO_TAG_MAPPING";


    // webservice constants

    public static final String KEY_SUCCESS = "Success";
    public static final String KEY_FAILURE = "Failure";
    public static final String KEY_FALSE = "False";
    public static final String KEY_CHANGED = "Changed";
    public static final String KEY_NO_DATA = "NoData";
    public static final String KEY_SKU_CD = "SKU_CD";
    public static final String KEY_SKU = "SKU";


    public static final String KEY_IMAGE = "IMAGE1";
    public static final String KEY__IMAGE = "IMAGE";
    public static final String KEY_REMARK = "KEY_REMARK";
    public static final String KEY_COVERAGE_REMARK = "REMARK";
    public static final String TABLE_WINDOWS_DATA = "WINDOWS_DATA";
    public static final String METHOD_UPLOAD_IMAGE = "GetImageWithFolderName";
    public static final String SOAP_ACTION_UPLOAD_IMAGE = "http://tempuri.org/" + METHOD_UPLOAD_IMAGE;
    public static final String TABLE_INSERT_OPENINGHEADER_DATA = "openingHeader_data";


    public static final String CREATE_TABLE_WINDOWS_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_WINDOWS_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "WINDOW_CD"
            + " VARCHAR,"
            + "WINDOW_IMAGE"
            + " VARCHAR,"
            + "DATE"
            + " VARCHAR,"
            + "USER_ID"
            + " VARCHAR,"
            + "REASON_ID"
            + " VARCHAR,"
            + "EXISTORNOT"
            + " VARCHAR,"

            + "STORE_CD" + " VARCHAR)";


    public static final String TABLE_INSERT_CHECKLIST_DATA = "CheckList_DATA";
    public static final String CREATE_TABLE_INSERT_CHECKLIST_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_CHECKLIST_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "WINDOW_CD"
            + " VARCHAR,"
            + "COMMON_ID"
            + " VARCHAR,"
            + "CHECKLIST_CD"
            + " VARCHAR,"
            + "ANSWER_CD"
            + " VARCHAR,"
            + "USER_ID"
            + " VARCHAR,"
            + "STORE_CD" + " VARCHAR)";


    public static final String TABLE_INSERT_SKU_ENTRY = "sku_entry_data";

    public static final String CREATE_TABLE_SKU_ENTRY_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_SKU_ENTRY
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "CATEGORY_ID"
            + " VARCHAR,"
            + "BRAND_ID"
            + " VARCHAR,"
            + "SKU_CD"
            + " VARCHAR,"
            + "WINDOW_CD"
            + " VARCHAR,"
            + "STORE_CD"
            + " VARCHAR,"
            + "stocksonetosix"
            + " VARCHAR,"

            + "stockseventotwelve"
            + " VARCHAR,"

            + "stockabovethirteen"
            + " VARCHAR,"
            + "STOCK" + " VARCHAR)";


    public static final String CREATE_TABLE_insert_OPENINGHEADER_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_OPENINGHEADER_DATA
            + " ("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + "STORE_CD"
            + " VARCHAR,"

            + "STATE_CD"
            + " VARCHAR,"

            + "STORE_TYPE_CD"
            + " VARCHAR,"


            + "BRAND_CD"

            + " VARCHAR,"

            + "BRAND" + " VARCHAR)";

    public static final String TABLE_INSERT_OPENINGHEADER_CLOSING_DATA = "openingHeader_Closing_data";

    public static final String CREATE_TABLE_insert_OPENINGHEADER_CLOSING_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_OPENINGHEADER_CLOSING_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + "STORE_CD"
            + " VARCHAR,"
            + "BRAND_CD"
            + " VARCHAR,"
            + "BRAND" + " VARCHAR)";

    public static final String TABLE_INSERT_PROMOTION_HEADER_DATA = "openingHeader_Windows_data";

    public static final String CREATE_TABLE_insert_HEADER_PROMOTION_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_PROMOTION_HEADER_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + "STORE_CD"
            + " VARCHAR,"

            + "STATE_CD"
            + " VARCHAR,"
            + "STORE_TYPE_CD"
            + " VARCHAR,"

            + "WINDOW_CD"
            + " VARCHAR,"
            + "WINDOW" + " VARCHAR)";

    public static final String TABLE_INSERT_ASSET_HEADER_DATA = "openingHeader_Asset_data";

    public static final String CREATE_TABLE_insert_HEADER_ASSET_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_ASSET_HEADER_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + "STORE_CD"
            + " VARCHAR,"
            + "CATEGORY_CD"
            + " VARCHAR,"
            + "CATEGORY" + " VARCHAR)";


    public static final String TABLE_INSERT_HEADER_MIDDAY_DATA = "openingHeader_Midday_data";

    public static final String CREATE_TABLE_insert_HEADER_MIDDAY_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_HEADER_MIDDAY_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + "STORE_CD"
            + " VARCHAR,"
            + "BRAND_CD"
            + " VARCHAR,"
            + "BRAND" + " VARCHAR)";

    public static final String TABLE_INSERT_HEADER_FOOD_STORE_DATA = "openingHeader_FOOD_STORE_data";

    public static final String CREATE_TABLE_insert_HEADER_FOOD_STORE_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_HEADER_FOOD_STORE_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "STORE_CD"
            + " VARCHAR,"
            + "BRAND_CD"
            + " VARCHAR,"
            + "BRAND" + " VARCHAR)";


    public static final String TABLE_DEEPFREEZER_DATA = "DEEPFREEZER_DATA";
    public static final String CREATE_TABLE_DEEPFREEZER_DATA = "CREATE TABLE IF NOT EXISTS DEEPFREEZER_DATA(FID INTEGER, STORE_CD VARCHAR,DEEP_FREEZER VARCHAR, FREEZER_TYPE VARCHAR, STATUS VARCHAR, REMARK VARCHAR)";

    public static final String TABLE_FACING_COMPETITOR_DATA = "FACING_COMPETITOR_DATA";
    public static final String CREATE_TABLE_FACING_COMPETITOR_DATA = "CREATE TABLE IF NOT EXISTS FACING_COMPETITOR_DATA(KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD INTEGER, CATEGORY_CD INTEGER, CATEGORY VARCHAR, BRAND VARCHAR, BRAND_CD INTEGER, FACING VARCHAR )";

    public static final String TABLE_STOCK_DATA = "STOCK_DATA";
    public static final String CREATE_TABLE_STOCK_DATA = "CREATE TABLE IF NOT EXISTS STOCK_DATA " +
            "(Common_Id INTEGER, SKU_CD VARCHAR,SKU VARCHAR,BRAND_CD VARCHAR," +
            " BRAND VARCHAR,STORE_CD VARCHAR,STATE_CD VARCHAR,STORE_TYPE_CD VARCHAR,OPENING_TOTAL_STOCK INTEGER," +
            " FACING INTEGER,STOCK_UNDER_DAYS INTEGER,STOCK_UNDER_12 INTEGER," +
            " STOCK_GREATER_13 INTEGER)";

    public static final String TABLE_STOCK_DATA_PRESENT = "STOCK_DATA_PRESENT";
    public static final String CREATE_TABLE_STOCK_PRESENT = "CREATE TABLE IF NOT EXISTS STOCK_DATA_PRESENT " +
            "(Common_Id INTEGER, SKU_CD VARCHAR,SKU VARCHAR,BRAND_CD VARCHAR," +
            " BRAND VARCHAR,STORE_CD VARCHAR,STATE_CD VARCHAR,STORE_TYPE_CD VARCHAR,OPENING_STOCK_PRESENT VARCHAR)";

    public static final String TABLE_OPENING_STOCK_DATA = "OPENING_STOCK_DATA";
    public static final String CREATE_TABLE_OPENING_STOCK_DATA = "CREATE TABLE IF NOT EXISTS OPENING_STOCK_DATA(Common_Id INTEGER, SKU_CD INTEGER,STORE_CD VARCHAR, CATEGORY_TYPE VARCHAR, AS_PER_MCCAIN VARCHAR, ACTUAL_LISTED VARCHAR, OPENING_STOCK_COLD_ROOM VARCHAR, OPENING_STOCK_MCCAIN_DF VARCHAR, TOTAL_FACING_MCCAIN_DF VARCHAR, OPENING_STOCK_STORE_DF VARCHAR, TOTAL_FACING_STORE_DF VARCHAR, MATERIAL_WELLNESS VARCHAR)";

    public static final String TABLE_CLOSING_STOCK_DATA = "CLOSING_STOCK_DATA";
    public static final String CREATE_TABLE_CLOSING_STOCK_DATA = "CREATE TABLE IF NOT EXISTS CLOSING_STOCK_DATA(Common_Id INTEGER, SKU_CD INTEGER,STORE_CD VARCHAR, COLD_ROOM VARCHAR, MCCAIN_DF VARCHAR, STORE_DF VARCHAR)";

    public static final String TABLE_MIDDAY_STOCK_DATA = "MIDDAY_STOCK_DATA";
    public static final String CREATE_TABLE_MIDDAY_STOCK_DATA = "CREATE TABLE IF NOT EXISTS MIDDAY_STOCK_DATA(Common_Id INTEGER,SKU_CD INTEGER,STORE_CD VARCHAR, MIDDAY_STOCK VARCHAR)";

    public static final String TABLE_FOOD_STORE_DATA = "FOOD_STORE_DATA";
    public static final String CREATE_TABLE_FOOD_STORE_DATA = "CREATE TABLE IF NOT EXISTS FOOD_STORE_DATA(Common_Id INTEGER,STORE_CD VARCHAR, SKU_CD VARCHAR, SKU VARCHAR,AS_PER_MCCAIN VARCHAR,ACTUAL_LISTED VARCHAR, MCCAIN_DF VARCHAR, STORE_DF VARCHAR, MTD_SALES VARCHAR, PACKING_SIZE VARCHAR)";

    public static final String TABLE_PROMOTION_DATA = "WINDOWS_DATA";

    public static final String TABLE_CHECK_LIST_DATA = "CheckList_DATA";

    public static final String CREATE_TABLE_CHECK_LIST_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CHECK_LIST_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "Common_Id"
            + " VARCHAR,"
            + "Status"
            + " VARCHAR,"
            + "STORE_CD"
            + " VARCHAR,"
            + "CHECKLIST"
            + " VARCHAR,"
            + "CHECKLIST_ID"
            + " VARCHAR,"
            + "Windows_cd" + " VARCHAR)";








	/*public static final String CREATE_TABLE_PROMOTION_DATA = "CREATE TABLE IF NOT EXISTS "
            + "WINDOWS_DATA(STORE_CD INTEGER," +
			"STATE_CD INTEGER,STORE_TYPE_CD VARCHAR, " +

			"Backsheet VARCHAR,ShelfStrip VARCHAR, " +


			"POG VARCHAR, REASON VARCHAR,IMAGE VARCHAR)";*/


    public static final String CREATE_TABLE_PROMOTION_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PROMOTION_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + "STORE_CD"
            + " VARCHAR,"

            + "Common_Id"
            + " VARCHAR,"

            + "STORE_TYPE_CD"
            + " VARCHAR,"

            + "STATE_CD"
            + " VARCHAR,"

            + "Backsheet"
            + " VARCHAR,"

            + "ShelfStrip"
            + " VARCHAR,"

            + "POG"
            + " VARCHAR,"

            + "REASON"
            + " VARCHAR,"

            + "STATUS_CD"
            + " VARCHAR,"
            + "IMAGE" + " VARCHAR)";


    public static final String TABLE_ASSET_DATA = "ASSET_DATA";
    public static final String CREATE_TABLE_ASSET_DATA = "CREATE TABLE IF NOT EXISTS ASSET_DATA(Common_Id INTEGER,ASSET_CD INTEGER,STORE_CD VARCHAR, ASSET VARCHAR, PRESENT VARCHAR, REMARK VARCHAR,IMAGE VARCHAR)";

    public static final String TABLE_CALLS_DATA = "CALLS_DATA";
    public static final String CREATE_TABLE_CALLS_DATA = "CREATE TABLE IF NOT EXISTS CALLS_DATA(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR, TOTAL_CALLS VARCHAR, PRODUCTIVE_CALLS VARCHAR)";

    public static final String TABLE_COMPETITION_POI = "COMPETITION_POI";
    public static final String CREATE_TABLE_COMPETITION_POI = "CREATE TABLE IF NOT EXISTS COMPETITION_POI(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR, CATEGORY_CD VARCHAR, ASSET_CD VARCHAR, CATEGORY VARCHAR, ASSET VARCHAR, BRAND_CD VARCHAR, BRAND VARCHAR, REMARK VARCHAR)";

    public static final String TABLE_STORE_SIGNAGE = "STORE_SIGNAGE";
    public static final String CREATE_TABLE_STORE_SIGNAGE = "CREATE TABLE IF NOT EXISTS STORE_SIGNAGE" +
            "(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR, SIGNEXIST VARCHAR, SIGNEXISTCD VARCHAR,WORKING VARCHAR,WORKINGCD VARCHAR, IMAGE VARCHAR)";

    public static final String TABLE_COMPETITION_PROMOTION = "COMPETITION_PROMOTION";
    public static final String CREATE_TABLE_COMPETITION_PROMOTION = "CREATE TABLE IF NOT EXISTS COMPETITION_PROMOTION(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR, CATEGORY_CD VARCHAR, CATEGORY VARCHAR, BRAND_CD VARCHAR, BRAND VARCHAR, REMARK VARCHAR, PROMOTION VARCHAR)";

    public static final String TABLE_STOCK_IMAGE = "STOCK_IMAGE";
    public static final String CREATE_TABLE_STOCK_IMAGE = "CREATE TABLE IF NOT EXISTS STOCK_IMAGE(Key_Id INTEGER PRIMARY KEY AUTOINCREMENT, STORE_CD VARCHAR,  BRAND_CD VARCHAR, BRAND VARCHAR, IMAGE_STK VARCHAR, VISIT_DATE VARCHAR)";

    // location
    public static final String TABLE_LOCATION_STATUS = "LOCATION_STATUS";
    public static final String KEY_NETWORK_STATUS = "NETWORK_STATUS";
    public static final String KEY_CURRENT_TIME = "CURRENT_TIME";

    public static final String NAMESPACE = "http://tempuri.org/";

    public static final String URL = "http://li.parinaam.in/LoralMerchandising.asmx";
    public static final String URL2 = "http://li.parinaam.in/LoralMerchandising.asmx/";

    public static final String URL_Notice_Board = "http://li.parinaam.in/notice/notice.html";
    //public static final String URL_Notice_Board = "http://google.com";

    //public static final String URL = "http://mccain.parinaam.in/MccainWebService.asmx";
    //public static final String local_URL = "http://10.200.20.133/GSK_GT_SERVICE/GSKWebservice.asmx";

    public static final String METHOD_LOGIN = "UserLoginDetail";
    public static final String SOAP_ACTION_LOGIN = "http://tempuri.org/"
            + METHOD_LOGIN;

    public static final String METHOD_UPLOAD_STORE_STATUS = "InsertUserCurrentLocation";

    public static final String METHOD_UPLOAD_STATUS = "DEVIATION_APPROVAL_SUP_STATUS";


    public static final String SOAP_ACTION_UPLOAD_STORE_STATUS = "http://tempuri.org/"
            + METHOD_UPLOAD_STATUS;

    // public static final String METHOD_NAME_JCP = "DownLoadStoreJcp";
    public static final String METHOD_NAME_JCP = "DownLoadStoreJcp_Special";
    public static final String SOAP_ACTION_JCP = "http://tempuri.org/"
            + METHOD_NAME_JCP;

    public static final String METHOD_NAME_STORE_LAYOUT = "DownLoad_Store_Layout";
    public static final String SOAP_ACTION_STORE_LAYOUT = "http://tempuri.org/"
            + METHOD_NAME_STORE_LAYOUT;

    public static final String METHOD_NAME_STORE_SIZE = "DownLoad_Store_Size";
    public static final String SOAP_ACTION_STORE_SIZE = "http://tempuri.org/"
            + METHOD_NAME_STORE_SIZE;

    public static final String METHOD_NAME_UPLOAD_GEOTAG_IMAGE = "Upload_StoreGeoTag_IMAGES";
    public static final String SOAP_ACTION_UPLOAD_GEOTAG_IMAGE = "http://tempuri.org/"
            + METHOD_NAME_UPLOAD_GEOTAG_IMAGE;

    public static final String METHOD_NAME_PLANOGRAM_IMAGES = "DownLoad_PlanoGramMapping";
    public static final String SOAP_ACTION_PLANOGRAM_IMAGES = "http://tempuri.org/"
            + METHOD_NAME_PLANOGRAM_IMAGES;

    public static final String METHOD_NAME_delete_coverage = "DeleteChekoutAndCoverage";
    public static final String SOAP_ACTION_delete_coverage = "http://tempuri.org/"
            + METHOD_NAME_delete_coverage;

    public static final String METHOD_Checkout_StatusNew = "Upload_Store_ChecOut_Status_V1";
    public static final String SOAP_ACTION_Checkout_StatusNew = "http://tempuri.org/"
            + METHOD_Checkout_StatusNew;


    // String value for promotional master

    public static final String METHOD_NAME_DownLoad_Promotional_Master = "DownLoad_Promotional_Master";
    public static final String SOAP_ACTION_Promotional_Master = "http://tempuri.org/"
            + METHOD_NAME_DownLoad_Promotional_Master;

    // String value for SKU master

    public static final String METHOD_NAME_DOWNLOAD_SKU_MASTER = "DownLoad_SKU_Master";
    public static final String SOAP_ACTION_DOWNLAOD_SKU_MASTER = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_SKU_MASTER;

    // string value for Master

    public static final String METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER = "DownLoad_NonWorkingReason_Master";

    public static final String METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER_subReason = "DownLoad_NonWorkingSubReason_ByReason";

    public static final String SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER;

    public static final String SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER_SUBREASON = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER_subReason;

    public static final String METHOD_NAME_DOWNLOAD_sku_mapping = "DownLoad_SKU_By_Mapping";
    public static final String SOAP_ACTION_DOWNLAOD_sku_mapping = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_sku_mapping;

    // string value for DowloadComplaince

    public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE = "DowloadComplaince";
    public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_COMPLIANCE;

    // STRING VALUE FOR DowloadPromotionWithComplainceByMapping

    public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING = "DowloadPromotionWithComplainceByMapping";
    public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING;

    public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING_SPECIAL = "DownLoad_PROMOTION_COMPLIANCE_MAPPING_SPECIAL";
    public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING_SPECIAL = "http://tempuri.org/"
            + METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING_SPECIAL;

    public static final String METHOD_VERTICAL_MASTER = "DOWLOAD_VERTICALMASTER";
    public static final String SOAP_ACTION_VERTICAL_MASTER = "http://tempuri.org/"
            + METHOD_VERTICAL_MASTER;

    public static final String METHOD_BRAND_MASTER = "DOWLOAD_BRANDMASTER";
    public static final String SOAP_ACTION_BRAND_Master = "http://tempuri.org/"
            + METHOD_BRAND_MASTER;

    public static final String METHOD_VERTICAL_BRAND_MAPPING = "DOWLOAD_VERTICALBRANDMAPPING";
    public static final String SOAP_ACTION_VERTICAL_BRAND_Mapping = "http://tempuri.org/"
            + METHOD_VERTICAL_BRAND_MAPPING;

    public static final String METHOD_VERTICAL_SKU_MAPPING = "SKUBRANDMAPPINGDownload";
    public static final String SOAP_ACTION_VERTICAL_SKU_Mapping = "http://tempuri.org/"
            + METHOD_VERTICAL_SKU_MAPPING;

    public static final String METHOD_CATEGORY_MASTER = "DOWLOAD_CATEGORYMASTER";
    public static final String SOAP_ACTION_CATEGORY_MASTER = "http://tempuri.org/"
            + METHOD_CATEGORY_MASTER;

    public static final String METHOD_CATEGORY_SKU_MAPPING = "CATEGORYSKUMAPPINGDownload";
    public static final String SOAP_ACTION_CATEGORY_SKU_MAPPING = "http://tempuri.org/"
            + METHOD_CATEGORY_SKU_MAPPING;

    public static final String METHOD_CATEGORY_VERTICAL_MAPPING = "CATEGORYVERTICALMAPPINGDownload";
    public static final String SOAP_ACTION_CATEGORY_VERTICAL_MAPPING = "http://tempuri.org/"
            + METHOD_CATEGORY_VERTICAL_MAPPING;

    public static final String METHOD_CATEGORY_POSM_MAPPING = "POSMBRANDMAPPINGDownload";
    public static final String SOAP_ACTION_POSM_MAPPING = "http://tempuri.org/"
            + METHOD_CATEGORY_POSM_MAPPING;

    public static final String METHOD_SKU_MASTER_DOWNLOAD = "SKU_MASTERDownload";
    public static final String SOAP_ACTION_SKU_MASTER = "http://tempuri.org/"
            + METHOD_SKU_MASTER_DOWNLOAD;

    public static final String METHOD_COMPANY_MASTER_DOWNLOAD = "COMPANY_MASTERDownload";
    public static final String SOAP_ACTION_COMPANY_MASTER = "http://tempuri.org/"
            + METHOD_COMPANY_MASTER_DOWNLOAD;

    // Shahab
    public static final String METHOD_NONSKU_REASON = "DOWLOAD_NON_STOCK_REASON_MASTER";
    public static final String SOAP_ACTION_NONSKU_REASON = "http://tempuri.org/"
            + METHOD_NONSKU_REASON;

    public static final String METHOD_SKU_FOCUS_DOWNLOAD = "SKUAVALIBILITY_FOCUS";
    public static final String SOAP_ACTION_SKU_FOCUS = "http://tempuri.org/"
            + METHOD_SKU_FOCUS_DOWNLOAD;

    public static final String METHOD_MAPPING_COMPETITOR = "DOWLOAD_MAPPINGCOMPEPITORBRAND";
    public static final String SOAP_ACTION_MAPPING_COMPETITOR = "http://tempuri.org/"
            + METHOD_MAPPING_COMPETITOR;

    public static final String METHOD_POSM_MASTER_DOWNLOAD = "DOWLOAD_POSMMASTER";
    public static final String SOAP_ACTION_POSM_MASTER_DOWNLOAD = "http://tempuri.org/"
            + METHOD_POSM_MASTER_DOWNLOAD;

    // Upload Coverage
    public static final String METHOD_UPLOAD_DR_STORE_COVERAGE = "UPLOAD_COVERAGENEW";
    public static final String METHOD_UPLOAD_DR_STORE_COVERAGE1 = "UPLOAD_COVERAGENEW1";
    public static final String METHOD_UPLOAD_COVERAGE_REMOVE = "UPLOAD_COVERAGE_REMOVE";
    public static final String METHOD_UPLOAD_DR_STORE_COVERAGE_LOC = "UPLOAD_COVERAGE_SUP";

    public static final String METHOD_UPLOAD_DR_STORE_GEO_Location = "UPLOAD_Geo_location";


    public static final String SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE = "http://tempuri.org/"
            + METHOD_UPLOAD_DR_STORE_COVERAGE_LOC;

    public static final String METHOD_GENERIC_UPLOAD = "DrUploadXml";
    public static final String METHOD_UPLOAD_DR_STORE_DATA = "Upload_Store_Layout_V1";
    public static final String SOAP_ACTION_UPLOAD_DR_STORE_DATA = "http://tempuri.org/"
            + METHOD_UPLOAD_DR_STORE_DATA;

    public static final String SOAP_ACTION_METHOD_GENERIC_UPLOAD = "http://tempuri.org/"
            + METHOD_GENERIC_UPLOAD;

    public static final String METHOD_UPLOAD_DR_RETAILER_INFO = "Upload_DR_STORE_PAYMENT";
    public static final String SOAP_ACTION_UPLOAD_DR_RETAILER_INFO = "http://tempuri.org/"
            + METHOD_UPLOAD_DR_RETAILER_INFO;

    public static final String METHOD_UPLOAD_ASSET = "Upload_Stock_Availiablity_V1";
    public static final String SOAP_ACTION_UPLOAD_ASSET = "http://tempuri.org/"
            + METHOD_UPLOAD_ASSET;

    public static final String METHOD_UPLOAD_SEC_SKU = "Upload_Stock_Availiablity_SECONDARY";
    public static final String SOAP_ACTION_UPLOAD_SEC_SKU = "http://tempuri.org/"
            + METHOD_UPLOAD_SEC_SKU;

    public static final String METHOD_UPLOAD_PCKGE_SKU = "Upload_DR_CORE_SKU_PACKAGING";
    public static final String SOAP_ACTION_UPLOAD_PCKGE_SKU = "http://tempuri.org/"
            + METHOD_UPLOAD_PCKGE_SKU;

    public static final String METHOD_UPLOAD_POSM = "Upload_Posm_Deployed";
    public static final String SOAP_ACTION_UPLOAD_POSM = "http://tempuri.org/"
            + METHOD_UPLOAD_POSM;

    public static final String METHOD_UPLOAD_COMPLIANCE = "Upload_Promotion_WindowExists";
    public static final String SOAP_ACTION_COMPLIANCE = "http://tempuri.org/"
            + METHOD_UPLOAD_COMPLIANCE;

    public static final String METHOD_UPLOAD_COMPLIANCE_SPECIAL = "Upload_Promotion_Special";
    public static final String SOAP_ACTION_COMPLIANCE_SPECIAL = "http://tempuri.org/"
            + METHOD_UPLOAD_COMPLIANCE_SPECIAL;

    public static final String METHOD_NON_WORKING_MASTER = "DOWLOAD_NONWORKINGREGIONMASTER";
    public static final String SOAP_ACTION_NONWORKING = "http://tempuri.org/"
            + METHOD_NON_WORKING_MASTER;

    public static final String METHOD_SET_COVERAGE_STATUS = "Upload_Status";
    public static final String SOAP_ACTION_SET_COVERAGE_STATUS = "http://tempuri.org/"
            + METHOD_SET_COVERAGE_STATUS;

    public static final String METHOD_SET_UPLOAD_GEODATA = "Upload_Store_Geo_Tag";
    public static final String SOAP_ACTION_UPLOAD_GEODATA = "http://tempuri.org/"
            + METHOD_SET_UPLOAD_GEODATA;

    public static final String TABLE_COVERAGE_DATA = "COVERAGE_DATA";
    public static final String TABLE_PREVIOUS_COVERAGE_DATA = "PREVIOUS_COVERAGE_DATA";

    public static final String KEY_ID = "_id";
    public static final String KEY_STORE_ID = "STORE_ID";
    public static final String KEY_BRAND_ID = "BRAND_ID";
    public static final String KEY_BRAND_CD = "BRAND_CD";
    public static final String KEY_BRAND = "BRAND";

    public static final String KEY_CATEGORY = "CATEGORY";
    public static final String KEY_QUANTITY = "QUANTITY";

    public static final String KEY_STORE_NAME = "STORE_NAME";
    public static final String KEY_USER_ID = "USER_ID";
    public static final String KEY_IN_TIME = "IN_TIME";
    public static final String KEY_OUT_TIME = "OUT_TIME";
    public static final String KEY_VISIT_DATE = "VISIT_DATE";
    public static final String KEY_LATITUDE = "LATITUDE";
    public static final String KEY_LONGITUDE = "LONGITUDE";
    public static final String KEY_COVERAGE_STATUS = "Coverage";
    public static final String KEY_REASON_ID = "REASON_ID";
    public static final String KEY_SUB_REASON_ID = "SUB_REASON_ID";
    public static final String KEY_INFORM_TO = "INFORM_TO";
    public static final String KEY_REASON = "REASON";
    public static final String KEY_STATUS = "STATUS";
    public static final String KEY_CHECKOUT_STATUS = "CHECKOUT_STATUS";
    public static final String KEY_CHECK_IN_TIME = "CHECK_IN_TIME";
    public static final String KEY_CHANNEL_CD = "channel_cd";
    public static final String KEY_META_DATA = "META_DATA";
    public static final String KEY_STORE_CD = "STORE_CD";
    public static final String KEY_RETAILER_NAME = "RETAILER_NAME";
    public static final String KEY_CONTACT = "CONTACT";
    public static final String KEY_CONTACT_2 = "CONTACT_2";
    public static final String KEY_COMPANY_CD = "COMPANY_CD";
    public static final String KEY_COMPANY = "COMPANY";
    public static final String KEY_POSTAL_ADDRESS = "POSTAL_ADDRESS";
    public static final String KEY_KYC_ID = "KYC_ID";
    public static final String KEY_DISPLAY_CD = "DISPLAY_CD";
    public static final String KEY_DISPLAY = "DISPLAY";
    public static final String KEY_IS_EXIST = "IS_EXIST";
    public static final String KEY_GST_NO = "GST_NO";
    public static final String KEY_GST_IMG = "GST_IMG";
    public static final String KEY_STORE_TYPE_CD = "STORE_TYPE_CD";
    public static final String KEY_STATE_CD = "STATE_CD";
    public static final String KEY_STORE_PROFILE_IMG = "STORE_PROFILE_IMG";
    public static final String KEY_CITY_CD = "CITY_CD";
    public static final String KEY_REMARKS = "REMARKS";
    public static final String KEY_COMPETITOR_EXIST_CD = "COMPETITOR_EXIST_CD";
    public static final String KEY_GEO_TAG = "GEO_TAG";

    public static final String KEY_STOREVISITED = "STORE_VISITED";
    public static final String KEY_STOREVISITED_STATUS = "STORE_VISITED_STATUS";

    public static final String KEY_FOOD_STORE = "FOOD_STORE";

    public static final String storetype_id = "STORETYPE_ID";
    public static final String KEY_CATEGORY_ID = "CATEGORY_ID";
    public static final String KEY_CATEGORY_CD = "CATEGORY_CD";

    //For Asset Checklist Insert table
    public static final String CHECK_LIST_ID = "CHECK_LIST_ID";
    public static final String CHECK_LIST = "CHECK_LIST";
    public static final String CHECK_LIST_TEXT = "CHECK_LIST_TEXT";
    public static final String CHECK_LIST_TYPE = "CHECK_LIST_TYPE";
    public static final String ASSET_CD = "ASSET_CD";

    public static final String KEY_COMMMON_ID = "COMMONID";

    public static final String TABLE_ASSET_CHECKLIST_INSERT = "CHECKLIST_INSERT";
    public static final String TABLE_STORE_PROFILE = "STORE_PROFILE";

    public static final String TABLE_SHARESHELF_HEADER_DATA = "SHARESHELF_HEADER_DATA";
    public static final String CREATE_TABLE_SHARESHELF_HEADER_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SHARESHELF_HEADER_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_CD
            + " INTEGER,"
            + KEY_CATEGORY_CD
            + " INTEGER,"
            + KEY_CATEGORY
            + " VARCHAR,"
            + KEY_IMAGE
            + " VARCHAR,"
            + KEY_QUANTITY
            + " VARCHAR)";


    public static final String TABLE_STOCKCHANNEL2_HEADER_DATA = "STOCKCHANNEL2_HEADER_DATA";
    public static final String CREATE_TABLE_STOCKCHANNEL2_HEADER_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_STOCKCHANNEL2_HEADER_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_CD
            + " INTEGER,"
            + KEY_BRAND_CD
            + " INTEGER,"
            + KEY_BRAND
            + " VARCHAR)";


    public static final String TABLE_SHARESHELF_CHILD_DATA = "SHARESHELF_CHILD_DATA";
    public static final String CREATE_TABLE_SHARESHELF_CHILD_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SHARESHELF_CHILD_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_COMMMON_ID
            + " INTEGER,"
            + KEY_STORE_CD
            + " INTEGER,"
            + KEY_BRAND_CD
            + " INTEGER,"
            + KEY_BRAND
            + " VARCHAR,"
            + KEY_QUANTITY
            + " VARCHAR)";


    public static final String TABLE_STOCKCHANNEL2_CHILD_DATA = "STOCKCHANNEL2_CHILD_DATA";
    public static final String CREATE_TABLE_STOCKCHANNEL2_CHILD_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_STOCKCHANNEL2_CHILD_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_COMMMON_ID
            + " INTEGER,"
            + KEY_STORE_CD
            + " INTEGER,"
            + KEY_SKU_CD
            + " INTEGER,"
            + KEY_SKU
            + " VARCHAR,"
            + KEY_QUANTITY
            + " VARCHAR)";


    public static final String TABLE_PAID_VISIBILITY_DATA = "PAID_VISIBILITY_DATA";
    public static final String CREATE_TABLE_PAID_VISIBILITY_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PAID_VISIBILITY_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_CD
            + " INTEGER,"
            + KEY_DISPLAY_CD
            + " INTEGER,"
            + KEY_BRAND_CD
            + " INTEGER,"
            + KEY_DISPLAY
            + " VARCHAR,"
            + KEY_IS_EXIST
            + " BOOLEAN,"
            + KEY__IMAGE
            + " VARCHAR,"
            + KEY_REMARK
            + " VARCHAR)";


    public static final String METHOD_Get_DR_CHEQUE_IMAGES = "Upload_StoreCheque_IMAGES";
    public static final String SOAP_ACTION_Get_DR_CHEQUE_IMAGES = "http://tempuri.org/"
            + METHOD_Get_DR_CHEQUE_IMAGES;

    public static final String METHOD_Get_DR_POSM_IMAGES = "GET_PosmDepLoyed_IMAGES";
    public static final String SOAP_ACTION_Get_DR_POSM_IMAGES = "http://tempuri.org/"
            + METHOD_Get_DR_POSM_IMAGES;

    public static final String METHOD_NAME_UNIVERSAL_DOWNLOAD = "Download_Universal";
    public static final String SOAP_ACTION_UNIVERSAL = "http://tempuri.org/"
            + METHOD_NAME_UNIVERSAL_DOWNLOAD;


    public static final String CREATE_TABLE_COVERAGE_DATA = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_COVERAGE_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR,USER_ID VARCHAR, " + KEY_IN_TIME + " VARCHAR,"
            + KEY_OUT_TIME + " VARCHAR," + KEY_VISIT_DATE + " VARCHAR," + KEY_CHANNEL_CD + " VARCHAR,"
            + KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE + " VARCHAR," + KEY_MERCHANDISER_ID + " VARCHAR,"
            + KEY_COVERAGE_STATUS + " VARCHAR," + KEY_IMAGE + " VARCHAR,"
            + KEY_GEO_TAG + " VARCHAR,"
            + KEY_REASON_ID + " VARCHAR," + KEY_SUB_REASON_ID + " VARCHAR," + KEY_INFORM_TO + " VARCHAR," + KEY_COVERAGE_REMARK
            + " VARCHAR," + KEY_REASON + " VARCHAR)";

    public static final String TABLE_INSERT_POSM_DATA = "POSM_data";

    public static final String CREATE_TABLE_INSERT_POSM_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_POSM_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + "POSM_CD"
            + " INTEGER,"
            + "POSM"
            + " VARCHAR,"
            + "REASON_CD"
            + " INTEGER,"
            + "REASON"
            + " VARCHAR,"
            + "Image_URL"
            + " VARCHAR,"
            + "IsExist"
            + " BOOLEAN,"
            + "STORE_CD" + " INTEGER)";

    public static final String TABLE_INSERT_COMPETITOR_DATA = "COMPETITOR_DATA";

    public static final String CREATE_TABLE_INSERT_COMPETITOR_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_COMPETITOR_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CommonString1.KEY_COMMMON_ID
            + " INTEGER,"
            + KEY_COMPETITOR_EXIST_CD
            + " VARCHAR,"
            + KEY_COMPANY_CD
            + " INTEGER,"
            + KEY_COMPANY
            + " VARCHAR,"
            + KEY_REMARKS
            + " VARCHAR,"
            + "Image_URL"
            + " VARCHAR,"
            + KEY_STORE_CD
            + " INTEGER)";


    public static final String TABLE_INSERT_COMPETITOR_DATA_EXIST = "COMPETITOR_DATA_EXIST";

    public static final String CREATE_TABLE_INSERT_COMPETITOR_DATA_EXIST = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_COMPETITOR_DATA_EXIST
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_COMPETITOR_EXIST_CD
            + " INTEGER,"
            + KEY_STORE_CD
            + " INTEGER)";


    //region DEEPAK TABLE FREE VISIBILITY
    public static final String TABLE_FREE_VISIBILITY_DATA = "FREE_VISIBILITY_DATA";
    public static final String CREATE_TABLE_FREE_VISIBILITY_DATA = "CREATE TABLE "
            + TABLE_FREE_VISIBILITY_DATA
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_CD + " INTEGER,"
            + KEY_COMPANY_CD + " INTEGER,"
            + KEY_COMPANY + " VARCHAR,"
            + KEY_CATEGORY_CD + " INTEGER,"
            + KEY_CATEGORY + " VARCHAR,"
            + KEY_DISPLAY_CD + " INTEGER,"
            + KEY_DISPLAY + " VARCHAR,"
            + KEY_QUANTITY + " VARCHAR,"
            + KEY__IMAGE + " VARCHAR"
            + ")";
    //endregion

    public static final String CREATE_TABLE_PREVIOUS_COVERAGE_DATA = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_PREVIOUS_COVERAGE_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " VARCHAR,USER_ID VARCHAR, " + KEY_IN_TIME + " VARCHAR,"
            + KEY_OUT_TIME + " VARCHAR," + KEY_VISIT_DATE + " VARCHAR," + KEY_CHANNEL_CD + " VARCHAR,"
            + KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE + " VARCHAR," + KEY_MERCHANDISER_ID + " VARCHAR,"
            + KEY_COVERAGE_STATUS + " VARCHAR," + KEY_IMAGE + " VARCHAR,"
            + KEY_GEO_TAG + " VARCHAR,"
            + KEY_REASON_ID + " VARCHAR," + KEY_SUB_REASON_ID + " VARCHAR," + KEY_INFORM_TO + " VARCHAR," + KEY_COVERAGE_REMARK
            + " VARCHAR," + KEY_REASON + " VARCHAR)";

    public static final String MESSAGE_EXCEPTION = "Problem Occured : Report The Problem To Parinaam ";
    public static final String MESSAGE_SOCKETEXCEPTION = "Network Communication Failure. Please Check Your Network Connection";

    public static final String CREATE_TABLE_STORE_PROFILE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_STORE_PROFILE
            + " ("
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_CD
            + " INTEGER,"
            + KEY_RETAILER_NAME
            + " VARCHAR,"
            + KEY_CONTACT
            + " VARCHAR,"
            + KEY_CONTACT_2
            + " VARCHAR,"
            + KEY_POSTAL_ADDRESS
            + " VARCHAR,"
            + KEY_CITY_CD
            + " INTEGER,"
            + KEY_STATE_CD
            + " INTEGER,"
            + KEY_STORE_PROFILE_IMG
            + " VARCHAR,"
            + KEY_KYC_ID
            + " INTEGER,"
            + KEY_GST_NO
            + " VARCHAR,"
            + KEY_GST_IMG
            + " VARCHAR)";

    public static final int CAPTURE_MEDIA = 131;
}
