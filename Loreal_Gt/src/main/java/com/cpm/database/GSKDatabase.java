package com.cpm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.CityMasterGetterSetter;
import com.cpm.GetterSetter.DisplayMasterGetterSetter;
import com.cpm.GetterSetter.EmpSalaryGetterSetter;
import com.cpm.GetterSetter.FreeVisibilityGetterSetter;
import com.cpm.GetterSetter.JourneyPlanPreviousGetterSetter;
import com.cpm.GetterSetter.KYCMasterGetterSetter;
import com.cpm.GetterSetter.MappingPaidVisibilityGetterSetter;
import com.cpm.GetterSetter.MappingPosmGetterSetter;
import com.cpm.GetterSetter.MappingPromotion2GetterSetter;
import com.cpm.GetterSetter.NonPOSMReasonGetterSetter;
import com.cpm.GetterSetter.NonPromotionReasonGetterSetter;
import com.cpm.GetterSetter.ShareShelfInsertHeaderDataGetterSetter;
import com.cpm.GetterSetter.StateMasterGetterSetter;
import com.cpm.GetterSetter.StoreProfileGetterSetter;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.TableBean;
import com.cpm.geotag.GeotaggingBeans;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.AnswerChecklistGetterSetter;
import com.cpm.xmlGetterSetter.AssetChecklistGetterSetter;
import com.cpm.xmlGetterSetter.AssetInsertdataGetterSetter;
import com.cpm.xmlGetterSetter.AssetMasterGetterSetter;
import com.cpm.xmlGetterSetter.BrandGetterSetter;
import com.cpm.xmlGetterSetter.COMPANY_MASTERGetterSetter;
import com.cpm.xmlGetterSetter.COMPETITORGetterSetter;
import com.cpm.xmlGetterSetter.CallsGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.ChecklistGetterSetter;
import com.cpm.xmlGetterSetter.ChecklistInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.ClosingStockInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.ColdroomClosingGetterSetter;
import com.cpm.xmlGetterSetter.CompanyGetterSetter;
import com.cpm.xmlGetterSetter.CompetitionPromotionGetterSetter;
import com.cpm.xmlGetterSetter.DeepFreezerGetterSetter;
import com.cpm.xmlGetterSetter.DeepFreezerTypeGetterSetter;
import com.cpm.xmlGetterSetter.FacingCompetitorGetterSetter;
import com.cpm.xmlGetterSetter.FoodStoreInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.HeaderGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.MappingAssetChecklistGetterSetter;
import com.cpm.xmlGetterSetter.MappingAssetGetterSetter;
import com.cpm.xmlGetterSetter.MappingAvailabilityGetterSetter;
import com.cpm.xmlGetterSetter.MappingPromotionGetterSetter;
import com.cpm.xmlGetterSetter.MappingStatusWindows;
import com.cpm.xmlGetterSetter.MappingStockGetterSetter;
import com.cpm.xmlGetterSetter.MappingWindowChecklistGetterSetter;
import com.cpm.xmlGetterSetter.MiddayStockInsertData;
import com.cpm.xmlGetterSetter.NonWorkingReasonGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingSubReasonGetterSetter;
import com.cpm.xmlGetterSetter.POSMDATAGetterSetter;
import com.cpm.xmlGetterSetter.PerformanceGetterSetter;
import com.cpm.xmlGetterSetter.PromotionInsertDataGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;
import com.cpm.xmlGetterSetter.StockGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;
import com.cpm.xmlGetterSetter.StoreSignAgeGetterSetter;
import com.cpm.xmlGetterSetter.SubCategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.WindowChecklistAnswerGetterSetter;
import com.cpm.xmlGetterSetter.WindowGetterSetter;
import com.cpm.xmlGetterSetter.WindowListGetterSetter;
import com.cpm.xmlGetterSetter.WindowNonReasonGetterSetter;
import com.cpm.xmlGetterSetter.WindowSKUEntryGetterSetter;
import com.cpm.xmlGetterSetter.windowsChildData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GSKDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LOREAL_DATABASE10";
    public static final int DATABASE_VERSION = 2;
    private SQLiteDatabase db;

    // ********************************************Extract
    // Database*********************************************

    // ***********************************END COPY
    // DATABASE************************************************************

    public GSKDatabase(Context completeDownloadActivity) {
        super(completeDownloadActivity, DATABASE_NAME, null, DATABASE_VERSION);
    }// TODO Auto-generated constructor stub }

    public void open() {
        try {
            db = this.getWritableDatabase();
        } catch (Exception e) {
        }
    }

    public void close() {
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        // CREATING TABLE FOR Loreal GT
        ////
        db.execSQL(TableBean.getJCPPrevioustable());
        db.execSQL(TableBean.getjcptable());
        db.execSQL(TableBean.getSkumastertable());
        db.execSQL(TableBean.getBrandtable());
        db.execSQL(TableBean.getMappingavailablitytable());
        db.execSQL(TableBean.getMappingposmtable());
        db.execSQL(TableBean.getMappingavailtable());
        db.execSQL(TableBean.getMappingassettable());
        db.execSQL(TableBean.getAssetmastertable());
        db.execSQL(TableBean.getTable_mappingwindowchecklist());
        db.execSQL(TableBean.getTable_windowchecklistanswer());
        db.execSQL(TableBean.getCategorymastertable());
        db.execSQL(TableBean.getSubCategoryMaster());
        db.execSQL(TableBean.getMappingStock());
        db.execSQL(TableBean.getCategorymastertable());
        db.execSQL(TableBean.getNonworkingtable());
        db.execSQL(TableBean.getNonworkingsubreasontable());
        db.execSQL(TableBean.getWindowNonReasontable());
        db.execSQL(TableBean.getCompanymastertable());
        db.execSQL(TableBean.getMappingPaidVisibilitytable());
        db.execSQL(TableBean.getDisplayMasterTable());
        db.execSQL(TableBean.getPromotiontable());
        db.execSQL(TableBean.getNonPromotionReasonTable());
        db.execSQL(TableBean.getMappingPosmtable());
        db.execSQL(TableBean.getEmpSalarytable());
        db.execSQL(TableBean.getNonPosmReasonTable());
        db.execSQL(TableBean.getStateMasterTable());
        db.execSQL(TableBean.getCityMasterTable());

        ////
        db.execSQL(CommonString1.CREATE_TABLE_COVERAGE_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_STOCK_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_STOCK_PRESENT);
        db.execSQL(CommonString1.CREATE_TABLE_insert_OPENINGHEADER_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_STORE_SIGNAGE);
        db.execSQL(CommonString1.CREATE_TABLE_INSERT_POSM_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_INSERT_COMPETITOR_DATA);
        db.execSQL(CommonString.CREATE_TABLE_STORE_GEOTAGGING);
        db.execSQL(CommonString1.CREATE_TABLE_WINDOWS_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_SKU_ENTRY_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_INSERT_CHECKLIST_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_SHARESHELF_HEADER_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_SHARESHELF_CHILD_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_STOCKCHANNEL2_HEADER_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_STOCKCHANNEL2_CHILD_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_PAID_VISIBILITY_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_FREE_VISIBILITY_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_INSERT_POSM_DATA);
        db.execSQL(CommonString1.CREATE_TABLE_PREVIOUS_COVERAGE_DATA);
        db.execSQL(CommonString.CREATE_TABLE_POSM_DATA_GT);
        db.execSQL(CommonString.CREATE_TABLE_POSM_DATA_2);
        db.execSQL(CommonString1.CREATE_TABLE_STORE_PROFILE);
        db.execSQL(CommonString1.CREATE_TABLE_INSERT_COMPETITOR_DATA_EXIST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        //db.execSQL("DROP TABLE IF EXISTS " + CommonString1.TABLE_COVERAGE_DATA);

        onCreate(db);
    }


    public void deleteSpecificStoreData(String storeid) {

        db.delete(CommonString1.TABLE_COVERAGE_DATA, CommonString1.KEY_STORE_ID + "='" + storeid + "'", null);

        db.delete("STOCK_DATA", CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete("openingHeader_data", CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete("STORE_SIGNAGE", CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete("POSM_data", CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete("POSM_DATA_2", CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete("COMPETITOR_DATA", CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_STORE_GEOTAGGING, CommonString1.KEY_STORE_ID + "='" + storeid + "'", null);

        db.delete("WINDOWS_DATA", CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete("sku_entry_data", CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete("CheckList_DATA", CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

        db.delete(CommonString1.TABLE_SHARESHELF_HEADER_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete(CommonString1.TABLE_SHARESHELF_CHILD_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete(CommonString1.TABLE_STOCKCHANNEL2_HEADER_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete(CommonString1.TABLE_STOCKCHANNEL2_CHILD_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete(CommonString1.TABLE_PAID_VISIBILITY_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete(CommonString1.TABLE_FREE_VISIBILITY_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete(CommonString1.TABLE_INSERT_POSM_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

    }


    public void deleteAllTables() {

        // DELETING TABLES GTGSK


        db.delete(CommonString1.TABLE_COVERAGE_DATA, null, null);


        db.delete("STOCK_DATA", null, null);
        db.delete("openingHeader_data", null, null);
        db.delete("STORE_SIGNAGE", null, null);
        db.delete("POSM_data", null, null);
        db.delete("POSM_DATA_2", null, null);
        db.delete("COMPETITOR_DATA", null, null);
        db.delete("WINDOWS_DATA", null, null);
        db.delete("sku_entry_data", null, null);
        db.delete("CheckList_DATA", null, null);

        db.delete(CommonString.TABLE_STORE_GEOTAGGING, null, null);

        db.delete(CommonString1.TABLE_SHARESHELF_HEADER_DATA, null, null);
        db.delete(CommonString1.TABLE_SHARESHELF_CHILD_DATA, null, null);
        db.delete(CommonString1.TABLE_STOCKCHANNEL2_HEADER_DATA, null, null);
        db.delete(CommonString1.TABLE_STOCKCHANNEL2_CHILD_DATA, null, null);
        db.delete(CommonString1.TABLE_PAID_VISIBILITY_DATA, null, null);
        db.delete(CommonString1.TABLE_FREE_VISIBILITY_DATA, null, null);
        db.delete(CommonString1.TABLE_INSERT_POSM_DATA, null, null);


    }


    public void deleteGeoTag() {
        db.delete(CommonString.TABLE_STORE_GEOTAGGING, null, null);
    }


    public void deleteStockHeaderData() {
        db.delete("openingHeader_data", null, null);
    }


    public void updateGeoTagStatus(String id, String status, double lat,
                                   double longtitude) {

        ContentValues values = new ContentValues();

        try {

            values.put(CommonString1.KEY_STATUS, status);
            values.put(CommonString1.KEY_LATITUDE, Double.toString(lat));
            values.put(CommonString1.KEY_LONGITUDE, Double.toString(longtitude));

            db.update(CommonString1.TABLE_GEO_TAG_MAPPING, values,
                    CommonString1.KEY_STORE_ID + "='" + id + "'", null);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Earned Toggle Data ",
                    ex.toString());
        }
    }


    public void InsertGeoTagData(JCPGetterSetter data) {

        db.delete(CommonString1.TABLE_GEO_TAG_MAPPING, null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getStoreid().size(); i++) {

                values.put(CommonString1.KEY_STORE_ID, data.getStoreid().get(i));
                values.put(CommonString1.KEY_STORE_NAME, data.getStorename()
                        .get(i));
                values.put(CommonString1.storetype_id, data.getStore_typeid()
                        .get(i));
                values.put(CommonString1.KEY_CATEGORY_ID, data.getCATEGORY_ID()
                        .get(i));
                values.put(CommonString1.KEY_LATITUDE, "");
                values.put(CommonString1.KEY_LONGITUDE, "");

                values.put(CommonString1.KEY_STATUS, data.getStatus().get(i));

                db.insert(CommonString1.TABLE_GEO_TAG_MAPPING, null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }

    }

    public long insertShareShelfHeaderData(String store_cd, ArrayList<CategoryMasterGetterSetter> listDataHeader, HashMap<CategoryMasterGetterSetter, List<BrandGetterSetter>> listDataChild) {

        long globalid = 0;
        db.delete(CommonString1.TABLE_SHARESHELF_CHILD_DATA, "store_cd = " + store_cd, null);
        db.delete(CommonString1.TABLE_SHARESHELF_HEADER_DATA, "store_cd = " + store_cd, null);
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();
        try {

            for (int i = 0; i < listDataHeader.size(); i++) {

                values.put(CommonString1.KEY_STORE_CD, store_cd);
                values.put(CommonString1.KEY_CATEGORY_CD, listDataHeader.get(i).getCategory_cd().get(0));
                values.put(CommonString1.KEY_CATEGORY, listDataHeader.get(i).getCategory().get(0));
                values.put(CommonString1.KEY_QUANTITY, listDataHeader.get(i).getQuantity());
                values.put(CommonString1.KEY_IMAGE, listDataHeader.get(i).getImage_name());

                long id = db.insert(CommonString1.TABLE_SHARESHELF_HEADER_DATA, null, values);


                for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                    values2.put(CommonString1.KEY_COMMMON_ID, id);
                    values2.put(CommonString1.KEY_STORE_CD, store_cd);
                    values2.put(CommonString1.KEY_BRAND_CD, listDataChild.get(listDataHeader.get(i)).get(j).getBrand_cd().get(0));
                    values2.put(CommonString1.KEY_BRAND, listDataChild.get(listDataHeader.get(i)).get(j).getBrand().get(0));
                    values2.put(CommonString1.KEY_QUANTITY, listDataChild.get(listDataHeader.get(i)).get(j).getQuantity());

                    globalid = db.insert(CommonString1.TABLE_SHARESHELF_CHILD_DATA, null, values2);
                }
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
            return 0;
        }

        return globalid;
    }


    public long insertStockChannel2Data(String store_cd, ArrayList<BrandGetterSetter> listDataHeader, HashMap<BrandGetterSetter, List<SkuMasterGetterSetter>> listDataChild) {

        long globalid = 0;
        db.delete(CommonString1.TABLE_STOCKCHANNEL2_CHILD_DATA, "store_cd = " + store_cd, null);
        db.delete(CommonString1.TABLE_STOCKCHANNEL2_HEADER_DATA, "store_cd = " + store_cd, null);
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();
        try {

            for (int i = 0; i < listDataHeader.size(); i++) {

                values.put(CommonString1.KEY_STORE_CD, store_cd);
                values.put(CommonString1.KEY_BRAND_CD, listDataHeader.get(i).getBrand_cd().get(0));
                values.put(CommonString1.KEY_BRAND, listDataHeader.get(i).getBrand().get(0));

                long id = db.insert(CommonString1.TABLE_STOCKCHANNEL2_HEADER_DATA, null, values);


                for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                    values2.put(CommonString1.KEY_COMMMON_ID, id);
                    values2.put(CommonString1.KEY_STORE_CD, store_cd);
                    values2.put(CommonString1.KEY_SKU_CD, listDataChild.get(listDataHeader.get(i)).get(j).getSku_cd_str());
                    values2.put(CommonString1.KEY_SKU, listDataChild.get(listDataHeader.get(i)).get(j).getSku_str());
                    values2.put(CommonString1.KEY_QUANTITY, listDataChild.get(listDataHeader.get(i)).get(j).getQuantity());

                    globalid = db.insert(CommonString1.TABLE_STOCKCHANNEL2_CHILD_DATA, null, values2);
                }
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
            return 0;
        }

        return globalid;
    }

    //Store data

    public void insertSkuMasterData(SkuMasterGetterSetter data) {

        db.delete("SKU_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {


            for (int i = 0; i < data.getSku_cd().size(); i++) {


                values.put("SKU_CD", Integer.parseInt(data.getSku_cd().get(i)));
                values.put("SKU", data.getSku().get(i));
                values.put("BRAND_CD", Integer.parseInt(data.getBrand_cd().get(i)));
                values.put("SKU_SEQUENCE", data.getSku_sequence().get(i));

						/*values.put("BRAND", data.getBrand().get(i));

						values.put("CATEGORY_CD", Integer.parseInt(data.getCategory_cd().get(i)));
						values.put("CATEGORY", data.getCategory().get(i));
						//values.put("MRP", data.getMrp().get(i));
						values.put("SKU_SEQUENCE", data.getSku_sequence().get(i));

						values.put("CATEGORY_SEQUENCE", data.getCategory_sequence().get(i));*/

                //values.put("CATEGORY_TYPE", data.getCategory_type().get(i));

                //values.put("PACKING", data.getPacking_size().get(i));

                db.insert("SKU_MASTER", null, values);


            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }

    }


    public long insertPaidVisibilityData(String store_cd, ArrayList<DisplayMasterGetterSetter> listDataHeader) {
        long id = 0;
        db.delete(CommonString1.TABLE_PAID_VISIBILITY_DATA, "STORE_CD = " + store_cd, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < listDataHeader.size(); i++) {

                values.put(CommonString1.KEY_STORE_CD, store_cd);
                values.put(CommonString1.KEY_DISPLAY_CD, listDataHeader.get(i).getDISPLAY_CD().get(0));
                values.put(CommonString1.KEY_DISPLAY, listDataHeader.get(i).getDISPLAY().get(0));
                values.put(CommonString1.KEY_IS_EXIST, listDataHeader.get(i).getExist());
                values.put(CommonString1.KEY__IMAGE, (listDataHeader.get(i).getImage()));
                values.put(CommonString1.KEY_REMARK, (listDataHeader.get(i).getRemark()));

                // brand_cd default is zero or "0"
                values.put(CommonString1.KEY_BRAND_CD,"0");

                id = db.insert(CommonString1.TABLE_PAID_VISIBILITY_DATA, null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
            return 0;
        }

        return id;
    }


    //region Deepak insertFreeVisibilityData
    public long insertFreeVisibilityData(ArrayList<FreeVisibilityGetterSetter> data, String store_cd) {

        long id = 0;
        db.delete(CommonString1.TABLE_FREE_VISIBILITY_DATA, CommonString1.KEY_STORE_CD + " = '" + store_cd + "'", null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.size(); i++) {

                values.put(CommonString1.KEY_STORE_CD, Integer.parseInt(store_cd));
                values.put(CommonString1.KEY_COMPANY_CD, Integer.parseInt(data.get(i).getCompany_cd()));
                values.put(CommonString1.KEY_COMPANY, (data.get(i).getCompany()));
                values.put(CommonString1.KEY_CATEGORY_CD, Integer.parseInt(data.get(i).getCategory_cd()));
                values.put(CommonString1.KEY_CATEGORY, data.get(i).getCategory());
                values.put(CommonString1.KEY_DISPLAY_CD, Integer.parseInt(data.get(i).getDisplay_cd()));
                values.put(CommonString1.KEY_DISPLAY, data.get(i).getDisplay());
                values.put(CommonString1.KEY_QUANTITY, data.get(i).getQuantity());
                values.put(CommonString1.KEY__IMAGE, data.get(i).getImage());

                id = db.insert(CommonString1.TABLE_FREE_VISIBILITY_DATA, null, values);
            }

        } catch (Exception ex) {
            Log.d("Excep  insertFreeVisibilityData",
                    ex.getMessage());
        }
        return id;
    }

    public void deleteFreeVisibilityData(Long id) {

        try {
            db.delete(CommonString1.TABLE_FREE_VISIBILITY_DATA, CommonString1.KEY_ID + " = " + id, null);
        } catch (Exception ex) {
            Log.d("Excep deleteFreeVisibilityData",
                    ex.getMessage());
        }
    }
    //endregion


    //JCP data

    public void insertJCPData(JourneyPlanGetterSetter data) {

        db.delete("JOURNEY_PLAN", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getStore_cd().size(); i++) {

                values.put("STORE_CD", Integer.parseInt(data.getStore_cd().get(i)));
                values.put("EMP_CD", Integer.parseInt(data.getEmp_cd().get(i)));
                values.put("VISIT_DATE", data.getVISIT_DATE().get(i));
                values.put("KEYACCOUNT", data.getKey_account().get(i));
                values.put("STORENAME", data.getStore_name().get(i));
                values.put("CITY", data.getCity().get(i));
                values.put("STORETYPE", data.getStore_type().get(i));
                //values.put("CATEGORY_TYPE", data.getCategory_type().get(i));
                values.put("UPLOAD_STATUS", data.getUploadStatus().get(i));
                values.put("CHECKOUT_STATUS", data.getCheckOutStatus().get(i));
                values.put("STATE_CD", data.getSTATE_CD().get(i));
                values.put("STORETYPE_CD", data.getSTORETYPE_CD().get(i));
                values.put("GEO_TAG", data.getGEO_TAG().get(i));
                values.put("CHANNEL_CD", data.getCHANNEL_CD().get(i));
                values.put("LATTITUDE", data.getLATTITUDE().get(i));
                values.put("LONGITUDE", data.getLONGITUDE().get(i));

                values.put("STORE", data.getSTORE().get(i));
                values.put("ADDRESS", data.getADDRESS().get(i));
                values.put("STORE_CONTACT_NO", data.getSTORE_CONTACT_NO().get(i));
                values.put("STORE_CONTACT_NO2", data.getSTORE_CONTACT_NO2().get(i));
                values.put("CITY_CD", data.getCITY_CD().get(i));
                values.put("STORE_PROFILE_IMAGE", data.getSTORE_PROFILE_IMAGE().get(i));
                values.put("GSTIN_NO", data.getGSTIN_NO().get(i));
                values.put("GSTIN_IMAGE", data.getGSTIN_IMAGE().get(i));
                values.put("KYC_ID", data.getKYC_ID().get(i));
                values.put("RETAILER_NAME", data.getRETAILER_NAME().get(i));

                long id = db.insert("JOURNEY_PLAN", null, values);

            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert JCP Data ",
                    ex.toString());
        }

    }


    public ArrayList<FreeVisibilityGetterSetter> getFreeVisibilityData(String storeid) {

        ArrayList<FreeVisibilityGetterSetter> list = new ArrayList<FreeVisibilityGetterSetter>();
        Cursor dbcursor = null;
        FreeVisibilityGetterSetter freeVisibilityGetterSetter;

        try {
            dbcursor = db.rawQuery("SELECT * from FREE_VISIBILITY_DATA where STORE_CD = '" + storeid + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    freeVisibilityGetterSetter = new FreeVisibilityGetterSetter();

                    freeVisibilityGetterSetter.setId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_ID)));
                    freeVisibilityGetterSetter.setCompany_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_COMPANY_CD)));
                    freeVisibilityGetterSetter.setCompany(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_COMPANY)));
                    freeVisibilityGetterSetter.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_CATEGORY_CD)));
                    freeVisibilityGetterSetter.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_CATEGORY)));
                    freeVisibilityGetterSetter.setDisplay_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_DISPLAY_CD)));
                    freeVisibilityGetterSetter.setDisplay(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_DISPLAY)));
                    freeVisibilityGetterSetter.setQuantity(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_QUANTITY)));
                    freeVisibilityGetterSetter.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY__IMAGE)));

                    list.add(freeVisibilityGetterSetter);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comptetion!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("comptetion---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //WINDOW_STATUS data

    public void insertWINDOW_MASTER(MappingStatusWindows data) {

        db.delete("WINDOW_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getWINDOW_CD().size(); i++) {

                values.put("WINDOW_CD", Integer.parseInt(data.getWINDOW_CD().get(i)));
                values.put("WINDOW", data.getWINDOW().get(i));
                values.put("SKU_HOLD", data.getSKU_HOLD().get(i));
                values.put("PLANOGRAM_IMAGE", data.getPLANOGRAM_IMAGE().get(i));


                db.insert("WINDOW_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Mapping Data ",
                    ex.toString());
        }

    }


    public ArrayList<MappingStatusWindows> getStatuswindows() {

        //Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<MappingStatusWindows> list1 = new ArrayList<MappingStatusWindows>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT  distinct * from WINDOW_STATUS ORDER BY STATUS_CD", null);


            if (dbcursor != null) {


                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingStatusWindows crowndata = new MappingStatusWindows();


                    crowndata.setSTATUS_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STATUS_CD")));


                    crowndata.setSTATUS(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STATUS")));


                    list1.add(crowndata);
                    dbcursor.moveToNext();

                }

                dbcursor.close();
                return list1;
            }

        } catch (Exception e) {
            //Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!", e.toString());
            return list1;
        }

        //Log.d("FetchingStoredat---------------------->Stop<-----------", "-------------------");
        return list1;

    }


    //mapping available data

    public void insertMAPPING_AVAILABILITYData(MappingAvailabilityGetterSetter data) {

        db.delete("MAPPING_AVAILABILITY", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getSTATE_CD().size(); i++) {

                values.put("STATE_CD", Integer.parseInt(data.getSTATE_CD().get(i)));
                values.put("STORETYPE_CD", data.getSTORETYPE_CD().get(i));

                values.put("SKU_CD", data.getSku_cd().get(i));
                values.put("FOCUS", data.getFOCUS().get(i));

                db.insert("MAPPING_AVAILABILITY", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Mapping Data ",
                    ex.toString());
        }

    }


    //POSM_MASTER data

    public void insertPOSMata(POSMDATAGetterSetter data) {

        db.delete("POSM_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getPOSM_CD().size(); i++) {

                values.put("POSM_CD", Integer.parseInt(data.getPOSM_CD().get(i)));
                values.put("POSM", data.getPOSM().get(i));


                db.insert("POSM_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Mapping Data ",
                    ex.toString());
        }

    }


    // Performance Data


    public void insertPerformanceData(PerformanceGetterSetter data) {

        db.delete("MY_PERFORMANCE", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getStore_cd().size(); i++) {

                values.put("STORE_CD", data.getStore_cd().get(i));
                values.put("TARGET", data.getMonthly_target().get(i));
                values.put("SALE", data.getMtd_sales().get(i));
                values.put("ACH", data.getAchievement().get(i));

                db.insert("MY_PERFORMANCE", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Performance Data ",
                    ex.toString());
        }

    }


    // closing cold Data


    public void insertClosingColdData(ColdroomClosingGetterSetter data) {

        db.delete("PREVIOUS_COLD_STOCK", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getStore_cd().size(); i++) {

                values.put("STORE_CD", data.getStore_cd().get(i));
                values.put("SKU_CD", data.getSku_cd().get(i));
                values.put("CLOSING_STOCK_COLD", data.getClosing_cold().get(i));

                db.insert("PREVIOUS_COLD_STOCK", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closing Cold Data ",
                    ex.toString());
        }

    }


//mapping promotion data

    public void insertMappingPromotionData(MappingPromotionGetterSetter data) {

        db.delete("WINDOW_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getWINDOW_CD().size(); i++) {

                values.put("WINDOW_CD", Integer.parseInt(data.getWINDOW_CD().get(i)));
                values.put("WINDOW", data.getWINDOW().get(i));


                //values.put("PID", data.getPid().get(i));
                //values.put("BRAND_CD", data.getBrand_cd().get(i));
                        /*values.put("SKU_CD",Integer.parseInt(data.getSku_cd().get(i)));
                        values.put("BRAND_SEQUENCE", Integer.parseInt(data.getBrand_sequence().get(i)));
						values.put("SKU_SEQUENCE", Integer.parseInt(data.getSku_sequence().get(i)));
						values.put("CATEGORY_TYPE", data.getCategory_type().get(i));*/

                db.insert("WINDOW_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Mapping promotion Data ",
                    ex.toString());
        }

    }


    public ArrayList<MappingPromotionGetterSetter> getwindowsMaster() {

        //Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<MappingPromotionGetterSetter> list1 = new ArrayList<MappingPromotionGetterSetter>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT  distinct * from WINDOW_MASTER ORDER BY WINDOW_CD", null);


            if (dbcursor != null) {


                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingPromotionGetterSetter crowndata = new MappingPromotionGetterSetter();


                    crowndata.setWINDOW_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));


                    crowndata.setWINDOW(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW")));


                    list1.add(crowndata);
                    dbcursor.moveToNext();

                }

                dbcursor.close();
                return list1;
            }

        } catch (Exception e) {
            //Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!", e.toString());
            return list1;
        }

        //Log.d("FetchingStoredat---------------------->Stop<-----------", "-------------------");
        return list1;

    }


//mapping asset data

    public void insertWINDOW_CHECKLISTData(MappingAssetGetterSetter data) {

        db.delete("WINDOW_CHECKLIST", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCHECKLIST_CD().size(); i++) {

                values.put("CHECKLIST_CD", Integer.parseInt(data.getCHECKLIST_CD().get(i)));
                values.put("CHECKLIST", data.getCHECKLIST().get(i));
                values.put("ANSWER_TYPE", data.getANSWER_TYPE().get(i));

                db.insert("WINDOW_CHECKLIST", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Mapping asset Data",
                    ex.toString());
        }

    }


    public ArrayList<MappingAssetGetterSetter> getCheckList() {
        Log.d("Fetching checklist data--------------->Start<------------",
                "------------------");
        ArrayList<MappingAssetGetterSetter> list = new ArrayList<MappingAssetGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT CHECKLIST, CHECKLIST_ID FROM CHECKLIST_MASTER  ", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingAssetGetterSetter sb = new MappingAssetGetterSetter();


                    sb.setCHECKLIST(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST")));

                    sb.setCHECKLIST_ID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST_ID")));

                    sb.setStatus("0");

                    sb.setWindows_cd("");


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching checklist data!!!!!!!!!!!", e.toString());
            return list;
        }

        Log.d("Fetching checklist data---------------------->Stop<-----------", "-------------------");
        return list;
    }



	/*public void InsertCheckListData(ArrayList<MappingAssetGetterSetter> data) {

		//db.delete(CommonString1.TABLE_ASSET_CHECKLIST_INSERT, "ASSET_CD" + "='" + asset_cd + "' AND STORE_CD" + "='" + store_cd + "'", null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.size(); i++) {

				values.put(CommonString1.CHECK_LIST_ID, data.get(i).getChecklist_id());


				values.put(CommonString1.CHECK_LIST_TYPE, data.get(i).getChecklist_type());
				values.put(CommonString1.CHECK_LIST, data.get(i).getChecklist());

				db.insert(CommonString1.TABLE_ASSET_CHECKLIST_INSERT, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Asset checklist insert Data ",
					ex.toString());
		}

	}*/


//deep freezer data

    public void insertDeepFreezerData(DeepFreezerGetterSetter data) {

        db.delete("DEEPFREEZER_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getFid().size(); i++) {

                values.put("FID", Integer.parseInt(data.getFid().get(i)));
                values.put("DEEP_FREEZER", data.getDeep_frrezer().get(i));
                values.put("FREEZER_TYPE", data.getType().get(i));
                values.put("SEQUENCE", Integer.parseInt(data.getSequence().get(i)));

                db.insert("DEEPFREEZER_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert DeepFreezer Data ",
                    ex.toString());
        }

    }

    //delete mapping on NO_DATA
    public void deleteMappingWindowChecklistData() {
        db.delete("MAPPING_WINDOW_CHECKLIST", null, null);
    }
    //Insert MAPPING_WINDOW_CHECKLIST data

    public void insertMappingWindowChecklistData(MappingWindowChecklistGetterSetter data) {

        db.delete("MAPPING_WINDOW_CHECKLIST", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getWINDOW_CD().size(); i++) {

                values.put("WINDOW_CD", Integer.parseInt(data.getWINDOW_CD().get(i)));
                values.put("CHECKLIST_CD", data.getCHECKLIST_CD().get(i));

                db.insert("MAPPING_WINDOW_CHECKLIST", null, values);

            }

        } catch (Exception ex) {
            Log.d("Ex MappingWindowCheckli",
                    ex.toString());
        }

    }
//WINDOW_MAPPING data

    public void insertWINDOW_MAPPINGData(AssetMasterGetterSetter data) {

        db.delete("WINDOW_MAPPING", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getSTATE_CD().size(); i++) {

                values.put("STATE_CD", Integer.parseInt(data.getSTATE_CD().get(i)));
                values.put("STORETYPE_CD", data.getSTORETYPE_CD().get(i));
                values.put("WINDOW_CD", data.getWINDOW_CD().get(i));
                values.put("BRAND_CD", data.getBRAND_CD().get(i));

                db.insert("WINDOW_MAPPING", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Asset Master Data ",
                    ex.toString());
        }

    }


    //COMPANY_MASTER data

    public void insertCOMPANY_MASTERData(COMPANY_MASTERGetterSetter data) {

        db.delete("COMPANY_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCOMPANY_CD().size(); i++) {

                values.put("COMPANY_CD", Integer.parseInt(data.getCOMPANY_CD().get(i)));
                values.put("COMPANY", data.getCOMPANY().get(i));
                values.put("ISCOMPETITOR", data.getISCOMPETITOR().get(i));


                db.insert("COMPANY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Asset Master Data ",
                    ex.toString());
        }

    }

    //COMPANY_MASTER data

    public void insertSUB_CATEGORY_MASTERData(SubCategoryMasterGetterSetter data) {

        db.delete("SUB_CATEGORY_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getSUB_CATEGORY_CD().size(); i++) {

                values.put("SUB_CATEGORY_CD", Integer.parseInt(data.getSUB_CATEGORY_CD().get(i)));
                values.put("SUB_CATEGORY", data.getSUB_CATEGORY().get(i));
                values.put("CATEGORY_CD", Integer.parseInt(data.getCATEGORY_CD().get(i)));
                values.put("SUB_CATEGORY_SEQUENCE", data.getSUB_CATEGORY_SEQUENCE().get(i));

                db.insert("SUB_CATEGORY_MASTER", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert insertSUB_CATEGORY_MASTERData ",
                    ex.toString());
        }

    }


    //Asset master data

    public void insertCompanyMasterData(CompanyGetterSetter data) {

        db.delete("COMPANY_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCompany_cd().size(); i++) {

                values.put("COMPANY_CD", Integer.parseInt(data.getCompany_cd().get(i)));
                values.put("COMPANY", data.getCompany().get(i));

                db.insert("COMPANY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Comapny Master Data ",
                    ex.toString());
        }

    }

    //mapping available data

    public void insertMAPPING_AvailibilityData(MappingAvailabilityGetterSetter data) {

        db.delete("MAPPING_AVAILABILITY", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getSTATE_CD().size(); i++) {

                values.put("STATE_CD", Integer.parseInt(data.getSTATE_CD().get(i)));
                values.put("STORETYPE_CD", data.getSTORETYPE_CD().get(i));

                values.put("SKU_CD", data.getSku_cd().get(i));

                values.put("FOCUS", data.getFOCUS().get(i));

                db.insert("MAPPING_AVAILABILITY", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Mapping Data ",
                    ex.toString());
        }

    }


    //mapping available data

    public void insertMAPPING_PaidVisibilityData(MappingPaidVisibilityGetterSetter data) {

        db.delete("MAPPING_PAID_VISIBILITY", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getSTORE_CD().size(); i++) {

                values.put("STORE_CD", Integer.parseInt(data.getSTORE_CD().get(i)));
                values.put("DISPLAY_CD", Integer.parseInt(data.getDISPLAY_CD().get(i)));
                values.put("BRAND_CD", Integer.parseInt(data.getBRAND_CD().get(i)));

                db.insert("MAPPING_PAID_VISIBILITY", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while insertMAPPING_PaidVisibilityData ",
                    ex.toString());
        }

    }


    //mapping available data

    public void insertMAPPING_STOCKData(MappingStockGetterSetter data) {

        db.delete("MAPPING_STOCK", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getSKU_CD().size(); i++) {

                values.put("SKU_CD", data.getSKU_CD().get(i));

                db.insert("MAPPING_STOCK", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while InsertinsertMAPPING_STOCKData ",
                    ex.toString());
        }

    }


    public void insertBrandMasterData(BrandGetterSetter data) {

        db.delete("BRAND_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getBrand_cd().size(); i++) {

                values.put("BRAND_CD", Integer.parseInt(data.getBrand_cd().get(i)));
                values.put("BRAND", data.getBrand().get(i));
                values.put("BRAND_SEQUENCE", data.getBrand_sequence().get(i));
                values.put("COMPANY_CD", Integer.parseInt(data.getCompany_cd().get(i)));
                values.put("CATEGORY_CD", Integer.parseInt(data.getCategory_cd().get(i)));
                values.put("SUB_CATEGORY_CD", Integer.parseInt(data.getSUB_CATEGORY_CD().get(i)));

                db.insert("BRAND_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Brand Master Data ",
                    ex.toString());
        }

    }


    public void insertDisplayMasterData(DisplayMasterGetterSetter data) {

        db.delete("DISPLAY_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getDISPLAY_CD().size(); i++) {

                values.put("DISPLAY_CD", Integer.parseInt(data.getDISPLAY_CD().get(i)));
                values.put("DISPLAY", data.getDISPLAY().get(i));

                db.insert("DISPLAY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while insertDisplayMasterData Data ",
                    ex.toString());
        }

    }

    //Insert Asset Checklist data
    public void insertAssetChecklistData(AssetChecklistGetterSetter data) {

        db.delete("ASSET_CHECKLIST", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCHECKLIST_ID().size(); i++) {

                values.put("CHECKLIST_ID", Integer.parseInt(data.getCHECKLIST_ID().get(i)));
                values.put("CHECKLIST", data.getCHECKLIST().get(i));
                values.put("CHECKLIST_TYPE", data.getTYPE().get(i));

                db.insert("ASSET_CHECKLIST", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Asset Checklist Data ",
                    ex.toString());
        }

    }


    //Insert Mapping Asset Checklist data
    public void insertMappingAssetChecklistData(MappingAssetChecklistGetterSetter data) {

        db.delete("MAPPING_ASSET_CHECKLIST", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getAsset_cd().size(); i++) {

                values.put("ASSET_CD", Integer.parseInt(data.getAsset_cd().get(i)));
                values.put("CHECKLIST_ID", data.getCheck_list_id().get(i));

                db.insert("MAPPING_ASSET_CHECKLIST", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Mapping Asset Checklist Data ",
                    ex.toString());
        }

    }


    public void deleteWindowChecklistAnswerData() {
        db.delete("WINDOW_CHECKLIST_ANSWER", null, null);
    }


//Insert WINDOW_CHECKLIST_ANSWER data

    public void insertWindowChecklistAnswerData(WindowChecklistAnswerGetterSetter data) {

        db.delete("WINDOW_CHECKLIST_ANSWER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getANSWER_CD().size(); i++) {

                values.put("ANSWER_CD", Integer.parseInt(data.getANSWER_CD().get(i)));
                values.put("ANSWER", data.getANSWER().get(i));
                values.put("CHECKLIST_CD", data.getCHECKLIST_CD().get(i));

                db.insert("WINDOW_CHECKLIST_ANSWER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Exc WindowCheckAns",
                    ex.toString());
        }

    }

//Non Working data

    public void insertNonWorkingReasonData(NonWorkingReasonGetterSetter data) {

        db.delete("NON_WORKING_REASON", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getReason_cd().size(); i++) {

                values.put("REASON_CD", Integer.parseInt(data.getReason_cd().get(i)));
                values.put("REASON", data.getReason().get(i));
                values.put("ENTRY_ALLOW", data.getEntry_allow().get(i));
                values.put("IMAGE_ALLOW", data.getIMAGE_ALLOW().get(i));


                db.insert("NON_WORKING_REASON", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Non Working Data ",
                    ex.toString());
        }

    }

//Non Working data

    public void insertNonWorkingSubReasonData(NonWorkingSubReasonGetterSetter data) {

        db.delete("NON_WORKING_SUB_REASON", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getSUB_REASON_CD().size(); i++) {

                values.put("SUB_REASON_CD", Integer.parseInt(data.getSUB_REASON_CD().get(i)));
                values.put("SUB_REASON", data.getSUB_REASON().get(i));

                db.insert("NON_WORKING_SUB_REASON", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Non Working Sub Reason Data ",
                    ex.toString());
        }

    }


    //Non Working data

    public void insertWindowNonReasonData(WindowNonReasonGetterSetter data) {

        db.delete("WINDOW_NON_REASON", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getWREASON_CD().size(); i++) {

                values.put("WREASON_CD", Integer.parseInt(data.getWREASON_CD().get(i)));
                values.put("WREASON", data.getWREASON().get(i));

                db.insert("WINDOW_NON_REASON", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while WINDOW_NON_REASON Data ",
                    ex.toString());
        }

    }


    public void insertMappingPromotion2Data(MappingPromotion2GetterSetter data) {

        db.delete("MAPPING_PROMOTION", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getID().size(); i++) {

                values.put("ID", Integer.parseInt(data.getID().get(i)));
                values.put("STORE_CD", Integer.parseInt(data.getSTORE_CD().get(i)));
                values.put("PROMO", data.getPROMO().get(i));

                db.insert("MAPPING_PROMOTION", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while WINDOW_NON_REASON Data ",
                    ex.toString());
        }

    }


    public void insertNonPromotionReasonData(NonPromotionReasonGetterSetter data) {

        db.delete("NON_PROMOTION_REASON", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getPREASON_CD().size(); i++) {

                values.put("PREASON_CD", Integer.parseInt(data.getPREASON_CD().get(i)));
                values.put("PREASON", data.getPREASON().get(i));

                db.insert("NON_PROMOTION_REASON", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while NON_PROMOTION_REASON Data ",
                    ex.toString());
        }

    }


//Category Master data

    public void insertCategoryMasterData(CategoryMasterGetterSetter data) {

        db.delete("CATEGORY_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCategory_cd().size(); i++) {

                values.put("CATEGORY_CD", Integer.parseInt(data.getCategory_cd().get(i)));
                values.put("CATEGORY", data.getCategory().get(i));
                values.put("CATEGORY_SEQUENCE", data.getCATEGORY_SEQUENCE().get(i));

                db.insert("CATEGORY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Category master Data ",
                    ex.toString());
        }

    }


    //get JCP Data

    public ArrayList<JourneyPlanGetterSetter> getJCPData(String date) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<JourneyPlanGetterSetter> list = new ArrayList<JourneyPlanGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from JOURNEY_PLAN where VISIT_DATE = '" + date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JourneyPlanGetterSetter sb = new JourneyPlanGetterSetter();

                    sb.setStore_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_CD")));
                    sb.setKey_account(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEYACCOUNT")));
                    sb.setStore_name((dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORENAME"))));
                    sb.setCity((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CITY"))));
                    sb.setSTATE_CD((dbcursor.getString(dbcursor.getColumnIndexOrThrow("STATE_CD"))));
                    sb.setSTORETYPE_CD((dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORETYPE_CD"))));
                    sb.setUploadStatus((dbcursor.getString(dbcursor.getColumnIndexOrThrow("UPLOAD_STATUS"))));
                    sb.setCheckOutStatus((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKOUT_STATUS"))));
                    sb.setVISIT_DATE((dbcursor.getString(dbcursor.getColumnIndexOrThrow("VISIT_DATE"))));
                    sb.setGEO_TAG((dbcursor.getString(dbcursor.getColumnIndexOrThrow("GEO_TAG"))));
                    sb.setCHANNEL_CD((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHANNEL_CD"))));
                    sb.setLATTITUDE((dbcursor.getString(dbcursor.getColumnIndexOrThrow("LATTITUDE"))));
                    sb.setLONGITUDE((dbcursor.getString(dbcursor.getColumnIndexOrThrow("LONGITUDE"))));
                    sb.setSTORE((dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE"))));
                    sb.setRETAILER_NAME((dbcursor.getString(dbcursor.getColumnIndexOrThrow("RETAILER_NAME"))));
                    sb.setSTORE_CONTACT_NO((dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_CONTACT_NO"))));
                    sb.setSTORE_CONTACT_NO2((dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_CONTACT_NO2"))));
                    sb.setSTORE_PROFILE_IMAGE((dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_PROFILE_IMAGE"))));
                    sb.setADDRESS((dbcursor.getString(dbcursor.getColumnIndexOrThrow("ADDRESS"))));
                    sb.setCITY_CD((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CITY_CD"))));
                    sb.setGSTIN_NO((dbcursor.getString(dbcursor.getColumnIndexOrThrow("GSTIN_NO"))));
                    sb.setGSTIN_IMAGE((dbcursor.getString(dbcursor.getColumnIndexOrThrow("GSTIN_IMAGE"))));
                    sb.setKYC_ID((dbcursor.getString(dbcursor.getColumnIndexOrThrow("KYC_ID"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!", e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------", "-------------------");
        return list;

    }


    //get JCP Data

    public ArrayList<CategoryMasterGetterSetter> getCategoryMasterData(boolean forshareshelf) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<CategoryMasterGetterSetter> list = new ArrayList<CategoryMasterGetterSetter>();
        Cursor dbcursor = null;
        try {
            if (forshareshelf) {
                dbcursor = db.rawQuery("SELECT * from CATEGORY_MASTER", null);
            } else {
                dbcursor = db.rawQuery("SELECT -1 as CATEGORY_CD,'Select' as CATEGORY,-1 as CATEGORY_SEQUENCE union SELECT CATEGORY_CD,CATEGORY,CATEGORY_SEQUENCE from CATEGORY_MASTER", null);
            }

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMasterGetterSetter sb = new CategoryMasterGetterSetter();

                    sb.setCategory_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));

                    sb.setCategory(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY")));

                    sb.setCATEGORY_SEQUENCE((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_SEQUENCE"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getCategoryMasterData!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getCategoryMasterData data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //get saved category data
    public ArrayList<CategoryMasterGetterSetter> getSavedCategoryMasterData(String store_cd) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<CategoryMasterGetterSetter> list = new ArrayList<CategoryMasterGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from SHARESHELF_HEADER_DATA WHERE STORE_CD ='" + store_cd + "'"
                    , null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMasterGetterSetter sb = new CategoryMasterGetterSetter();

                    sb.setId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("_id")));
                    sb.setCategory_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));

                    sb.setCategory(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY")));

                    sb.setQuantity((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUANTITY"))));
                    sb.setImage_name((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_IMAGE))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getCategoryMasterData!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getCategoryMasterData data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    // getSavedDisplayDataFromStore data
    public ArrayList<DisplayMasterGetterSetter> getSavedDisplayDataFromStore(String store_cd) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<DisplayMasterGetterSetter> list = new ArrayList<DisplayMasterGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from PAID_VISIBILITY_DATA WHERE STORE_CD ='" + store_cd + "'"
                    , null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    DisplayMasterGetterSetter sb = new DisplayMasterGetterSetter();

                    sb.setDISPLAY_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_CD")));
                    sb.setDISPLAY(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY")));
                    sb.setExist(CommonFunctions.convertInttoBool(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IS_EXIST"))));
                    sb.setImage((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE"))));
                    sb.setRemark((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("KEY_REMARK"))));
                    sb.setBrand_cd((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_BRAND_CD))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getSavedDisplayDataFromStore!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getSavedDisplayDataFromStore data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<BrandGetterSetter> getBrandForChannel2Data() {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<BrandGetterSetter> list = new ArrayList<BrandGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT DISTINCT BR.BRAND_CD, BR.BRAND||'-'||SB.SUB_CATEGORY as BRAND FROM MAPPING_STOCK M INNER JOIN SKU_MASTER SK ON M.SKU_CD = SK.SKU_CD" +
                            " INNER JOIN BRAND_MASTER BR ON SK.BRAND_CD = BR.BRAND_CD" +
                            " INNER JOIN SUB_CATEGORY_MASTER SB ON BR.SUB_CATEGORY_CD = SB.SUB_CATEGORY_CD"
                    , null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandGetterSetter sb = new BrandGetterSetter();

                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getBrandForChannel2Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getBrandForChannel2Data data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<BrandGetterSetter> getSavedBrandForChannel2Data(String store_cd) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<BrandGetterSetter> list = new ArrayList<BrandGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM STOCKCHANNEL2_HEADER_DATA WHERE STORE_CD = '" + store_cd + "'"
                    , null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandGetterSetter sb = new BrandGetterSetter();

                    sb.setId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("_id")));

                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getBrandForChannel2Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getBrandForChannel2Data data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //get getChildDataForHeader Data
    public ArrayList<BrandGetterSetter> getChildDataForHeader(String category_cd) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<BrandGetterSetter> list = new ArrayList<BrandGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT BR.BRAND_CD, BR.BRAND|| '-' ||SB.SUB_CATEGORY AS BRAND FROM BRAND_MASTER BR INNER JOIN SUB_CATEGORY_MASTER SB ON BR.SUB_CATEGORY_CD = SB.SUB_CATEGORY_CD " +
                            "INNER JOIN CATEGORY_MASTER CA ON SB.CATEGORY_CD = CA.CATEGORY_CD" +
                            " WHERE CA.CATEGORY_CD = '" + category_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandGetterSetter sb = new BrandGetterSetter();

                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getChildDataForHeader!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getChildDataForHeader data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //get getChildDataForHeader Data
    public ArrayList<DisplayMasterGetterSetter> getDisplayDataFromStore(String store_cd) {

        ArrayList<DisplayMasterGetterSetter> list = new ArrayList<DisplayMasterGetterSetter>();
        Cursor dbcursor = null;

        try {
            // code comment by neeraj no need of brand and category here
            /*dbcursor = db.rawQuery("SELECT BR.BRAND_CD, M.STORE_CD, M.DISPLAY_CD, D.DISPLAY|| '- ' ||BR.BRAND|| '- ' ||SB.SUB_CATEGORY AS DISPLAY FROM MAPPING_PAID_VISIBILITY M INNER JOIN DISPLAY_MASTER D ON M.DISPLAY_CD = D.DISPLAY_CD " +
                            "INNER JOIN BRAND_MASTER BR ON M.BRAND_CD = BR.BRAND_CD " +
                            "INNER JOIN SUB_CATEGORY_MASTER SB ON BR.SUB_CATEGORY_CD = SB.SUB_CATEGORY_CD " +
                            "WHERE M.STORE_CD = '" + store_cd + "'"
                    , null);*/

        dbcursor = db.rawQuery("SELECT  M.STORE_CD, M.DISPLAY_CD, D.DISPLAY FROM MAPPING_PAID_VISIBILITY M " +
                            "INNER JOIN DISPLAY_MASTER D ON M.DISPLAY_CD = D.DISPLAY_CD " +
                            "WHERE M.STORE_CD = '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    DisplayMasterGetterSetter sb = new DisplayMasterGetterSetter();

                    sb.setDISPLAY_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_CD")));
                    sb.setDISPLAY(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getDisplayDataFromStore!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getChildDataForHeader data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //get getChildDataForHeader Data
    public ArrayList<DisplayMasterGetterSetter> getDisplayMasterData() {

        ArrayList<DisplayMasterGetterSetter> list = new ArrayList<DisplayMasterGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT -1 as DISPLAY_CD,'Select' as DISPLAY union SELECT DISTINCT DISPLAY_CD,DISPLAY FROM DISPLAY_MASTER"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    DisplayMasterGetterSetter sb = new DisplayMasterGetterSetter();

                    sb.setDISPLAY_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_CD")));
                    sb.setDISPLAY(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getDisplayMasterData!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getDisplayMasterData data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //get getSavedChildDataForHeader Data
    public ArrayList<BrandGetterSetter> getSavedChildDataForHeader(String common_id, String store_cd) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<BrandGetterSetter> list = new ArrayList<BrandGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM SHARESHELF_CHILD_DATA WHERE COMMONID ='" + common_id + "' AND STORE_CD ='" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandGetterSetter sb = new BrandGetterSetter();

                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));
                    sb.setQuantity(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUANTITY")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getSavedChildDataForHeader!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getSavedChildDataForHeader data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuMasterGetterSetter> getChildDataForHeaderChannel2(String brandcd) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuMasterGetterSetter> list = new ArrayList<SkuMasterGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT SK.SKU_CD, SK.SKU FROM MAPPING_STOCK M INNER JOIN SKU_MASTER SK ON M.SKU_CD = SK.SKU_CD" +
                            " INNER JOIN BRAND_MASTER BR ON SK.BRAND_CD = BR.BRAND_CD" +
                            " WHERE BR.BRAND_CD = '" + brandcd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuMasterGetterSetter sb = new SkuMasterGetterSetter();

                    sb.setSku_cd_str(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));
                    sb.setSku_str(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getChildDataForHeader!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getChildDataForHeader data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuMasterGetterSetter> getSavedChildDataForHeaderChannel2(String common_id, String store_cd) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuMasterGetterSetter> list = new ArrayList<SkuMasterGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * FROM STOCKCHANNEL2_CHILD_DATA WHERE COMMONID = '" + common_id + "' AND STORE_CD = '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuMasterGetterSetter sb = new SkuMasterGetterSetter();

                    sb.setSku_cd_str(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));
                    sb.setSku_str(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));
                    sb.setQuantity(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUANTITY")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching getChildDataForHeader!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("getChildDataForHeader data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //get JCP Data

    public ArrayList<JourneyPlanGetterSetter> getAllJCPData() {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<JourneyPlanGetterSetter> list = new ArrayList<JourneyPlanGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from JOURNEY_PLAN "
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JourneyPlanGetterSetter sb = new JourneyPlanGetterSetter();

                    sb.setStore_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_CD")));

                    sb.setKey_account(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("KEYACCOUNT")));

                    sb.setStore_name((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORENAME"))));


                    sb.setCity((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CITY"))));


                    sb.setUploadStatus((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("UPLOAD_STATUS"))));
                    sb.setCheckOutStatus((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKOUT_STATUS"))));
                    sb.setVISIT_DATE((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("VISIT_DATE"))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching JCP!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

//get DeepFreezerType Data from Master

    public ArrayList<DeepFreezerTypeGetterSetter> getDFMasterData(String dftype) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<DeepFreezerTypeGetterSetter> list = new ArrayList<DeepFreezerTypeGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from DEEPFREEZER_MASTER where FREEZER_TYPE = '" + dftype + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    DeepFreezerTypeGetterSetter df = new DeepFreezerTypeGetterSetter();


                    df.setDeep_freezer(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DEEP_FREEZER")));
                    df.setFid(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("FID")));
                    df.setFreezer_type(dftype);
                    df.setRemark("");
                    df.setStatus("NO");

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Deepfreezer!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching Deep Freezer data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //Insert Deepfreezer Type Data

    public void insertDeepFreezerTypeData(ArrayList<DeepFreezerTypeGetterSetter> data, String dfType, String store_cd) {

        db.delete(CommonString1.TABLE_DEEPFREEZER_DATA, "FREEZER_TYPE" + "='" + dfType + "'", null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.size(); i++) {

                values.put("FID", Integer.parseInt(data.get(i).getFid()));
                values.put("STORE_CD", store_cd);
                values.put("DEEP_FREEZER", data.get(i).getDeep_freezer());
                values.put("FREEZER_TYPE", data.get(i).getFreezer_type());
                values.put("STATUS", data.get(i).getStatus());
                values.put("REMARK", data.get(i).getRemark());

                db.insert(CommonString1.TABLE_DEEPFREEZER_DATA, null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert DeepFreezer Data ",
                    ex.toString());
        }

    }


    //Insert Facing Competitor Data

    public void insertFscingCompetitorData(String store_cd,
                                           HashMap<FacingCompetitorGetterSetter, List<FacingCompetitorGetterSetter>> data, List<FacingCompetitorGetterSetter> save_listDataHeader) {

        db.delete(CommonString1.TABLE_FACING_COMPETITOR_DATA, "STORE_CD" + "='" + store_cd + "'", null);
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        String category, category_cd;

        try {

            for (int i = 0; i < save_listDataHeader.size(); i++) {

					/*	values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString1.TABLE_INSERT_ASSET_HEADER_DATA,
								null, values);
*/
                category = save_listDataHeader.get(i).getCategory();
                category_cd = save_listDataHeader.get(i).getCategory_cd();


                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values.put("CATEGORY_CD", Integer.parseInt(category_cd));
                    values.put("CATEGORY", category);
                    values.put("FACING", data.get(save_listDataHeader.get(i)).get(j).getMccaindf());
                    values.put("BRAND", data.get(save_listDataHeader.get(i)).get(j).getBrand());
                    values.put("BRAND_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getBrand_cd()));
                    //values.put("STORE_DF", data.get(i).getStoredf());
                    values.put("STORE_CD", store_cd);

                    db.insert(CommonString1.TABLE_FACING_COMPETITOR_DATA, null, values);

                }
            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Facing Competition Data ",
                    ex.toString());
        }

    }

//get DeepFreezerType Data

    public ArrayList<DeepFreezerTypeGetterSetter> getDFTypeData(String dftype, String storecd) {

        Log.d("FetchingDeepFreezerType--------------->Start<------------",
                "------------------");
        ArrayList<DeepFreezerTypeGetterSetter> list = new ArrayList<DeepFreezerTypeGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from DEEPFREEZER_DATA where FREEZER_TYPE = '" + dftype + "'  AND STORE_CD = '" + storecd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    DeepFreezerTypeGetterSetter df = new DeepFreezerTypeGetterSetter();


                    df.setDeep_freezer(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DEEP_FREEZER")));
                    df.setFid(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("FID")));
                    df.setFreezer_type(dftype);
                    df.setRemark(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REMARK")));
                    df.setStatus(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STATUS")));

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Deepfreezer!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //check if table is empty
    public boolean isDeepfreezerDataFilled(String storeId, String dftype) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM DEEPFREEZER_DATA WHERE FREEZER_TYPE = '" + dftype + "'  AND STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


//get DeepFreezerType Data

    public ArrayList<DeepFreezerTypeGetterSetter> getDFTypeUploadData(String store_cd) {

        Log.d("FetchingDeepFreezerType--------------->Start<------------",
                "------------------");
        ArrayList<DeepFreezerTypeGetterSetter> list = new ArrayList<DeepFreezerTypeGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from DEEPFREEZER_DATA where STORE_CD = '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    DeepFreezerTypeGetterSetter df = new DeepFreezerTypeGetterSetter();


                    df.setDeep_freezer(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DEEP_FREEZER")));
                    df.setFid(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("FID")));
                    df.setFreezer_type(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("FREEZER_TYPE")));
                    df.setRemark(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REMARK")));
                    df.setStatus(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STATUS")));

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Deepfreezer!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingJCP data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


//get Facing Competitor Data

    public ArrayList<COMPETITORGetterSetter> getFacingCompetitorData(String store_cd) {

        Log.d("Fetching facing competitor--------------->Start<------------",
                "------------------");
        ArrayList<COMPETITORGetterSetter> list = new ArrayList<COMPETITORGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from FACING_COMPETITOR_DATA where STORE_CD = '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    COMPETITORGetterSetter fc = new COMPETITORGetterSetter();


							/*fc.setCategory(dbcursor.getString(dbcursor
                                    .getColumnIndexOrThrow("CATEGORY")));
							fc.setCategory_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_CD")));
							fc.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));
							fc.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));
							fc.setMccaindf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("FACING")));*/


                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Facing Competitor!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Competitor---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //get Facing Competitor Data Categoty wise

    public ArrayList<FacingCompetitorGetterSetter> getFacingCompetitorCategotywiseData(String store_cd, String category_cd) {

        Log.d("Fetching brand>Start<--",
                "--");
        ArrayList<FacingCompetitorGetterSetter> list = new ArrayList<FacingCompetitorGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from FACING_COMPETITOR_DATA where STORE_CD = '" + store_cd + "' AND CATEGORY_CD" + " = '" + category_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FacingCompetitorGetterSetter fc = new FacingCompetitorGetterSetter();


                    fc.setCategory(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY")));
                    fc.setCategory_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));
                    fc.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));
                    fc.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    fc.setMccaindf(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("FACING")));
                        /*	fc.setStoredf(dbcursor.getString(dbcursor
                                    .getColumnIndexOrThrow("STORE_DF")));*/


                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Facing Competitor!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Competitor---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


//get Facing Competitor Data

    public ArrayList<PerformanceGetterSetter> getPerformrmance(String store_cd) {

        Log.d("Fetching facing competitor--------------->Start<------------",
                "------------------");
        ArrayList<PerformanceGetterSetter> list = new ArrayList<PerformanceGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from MY_PERFORMANCE where STORE_CD = '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    PerformanceGetterSetter fc = new PerformanceGetterSetter();


                    fc.setMonthly_target(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("TARGET")));
                    fc.setMtd_sales(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SALE")));
                    fc.setAchievement(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ACH")));

                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Facing Competitor!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Competitor---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //check if table is empty
    public boolean isCompetitionDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM FACING_COMPETITOR_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    //Stock data

    public ArrayList<StockNewGetterSetter> getStockAvailabilityData(String STATE_CD, String STORE_TYPE_CD) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT BRAND_CD,BRAND FROM openingHeader_data  WHERE STATE_CD ='" + STATE_CD + "' AND STORE_TYPE_CD ='" + STORE_TYPE_CD + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();


                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //Header with cam data

    public ArrayList<StockNewGetterSetter> getHeaderStockImageData(String STATE_CD, String STORE_TYPE_CD) {
        //Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT BR.BRAND_CD, BR.BRAND" +
                    " FROM MAPPING_AVAILABILITY MA INNER JOIN SKU_MASTER SKM ON MA.SKU_CD = SKM.SKU_CD " +
                    "INNER JOIN BRAND_MASTER BR ON SKM.BRAND_CD = BR.BRAND_CD WHERE MA.STATE_CD ='" + STATE_CD + "' AND MA.STORETYPE_CD ='" + STORE_TYPE_CD + "'  ", null);

            //	dbcursor = db.rawQuery("SELECT DISTINCT BRAND_CD, BRAND, CATEGORY_CD, COMPANY_CD, BRAND_SEQUENCE FROM BRAND_MASTER ORDER BY BRAND_SEQUENCE", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();


                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));

					/*sb.setCATEGORY_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));*/


					/*sb.setCOMPANY_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMPANY_CD")));

					sb.setBRAND_SEQUENCE(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND_SEQUENCE")));*/


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            //Log.d("Exception when fetching Header!!!!!!!!!!!", e.toString());
            return list;
        }

        //Log.d("Fetching Header stock---------------------->Stop<-----------", "-------------------");
        return list;
    }


    //Stock data

    public ArrayList<StockNewGetterSetter> getFoodStoreAvailabilityData(String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            String food = "Food";

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT SD.BRAND_CD, SD.BRAND FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE CD.STORE_CD ='" + store_cd + "' AND SD.CATEGORY_TYPE ='" + food + "' ORDER BY CD.BRAND_SEQUENCE"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();

							/*sb.setSku_cd(dbcursor.getString(dbcursor
                                    .getColumnIndexOrThrow("SKU_CD")));*/
                        /*	sb.setSku(dbcursor.getString(dbcursor
                                    .getColumnIndexOrThrow("SKU")));*/

                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));

							/*sb.setCategory_cd(dbcursor.getString(dbcursor
                                    .getColumnIndexOrThrow("CATEGORY_CD")));

							sb.setCategory(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY")));

							sb.setMrp(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MRP")));

							sb.setSku_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU_SEQUENCE")));

							sb.setCategory_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_SEQUENCE")));

							sb.setBrand_seq(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_SEQUENCE")));*/

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //Stock Sku data

    public ArrayList<StockNewGetterSetter> getStockSkuData(String brand_cd, String STATE_CD, String STORE_TYPE_CD) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            //dbcursor = db.rawQuery("SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE CD.STATE_CD= '" +  STATE_CD + "' AND SD.BRAND_CD ='"+brand_cd+"'", null);

            dbcursor = db.rawQuery("SELECT DISTINCT SKM.SKU_CD, SKM.SKU" +
                    " FROM MAPPING_AVAILABILITY MA INNER JOIN SKU_MASTER SKM ON MA.SKU_CD = SKM.SKU_CD " +
                    "INNER JOIN BRAND_MASTER BR ON SKM.BRAND_CD = BR.BRAND_CD WHERE MA.STATE_CD ='" + STATE_CD + "' AND MA.STORETYPE_CD ='" + STORE_TYPE_CD + "' AND BR.BRAND_CD ='" + brand_cd + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

							/*sb.setCategory_type(dbcursor.getString(dbcursor
                                    .getColumnIndexOrThrow("CATEGORY_TYPE")));*/

                    sb.setOpenning_total_stock("");
                    sb.setOpening_facing("");
                    sb.setStock_under45days("");
                    sb.setDate("");
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //Food Stock data

    public ArrayList<FoodStoreInsertDataGetterSetter> getFoodSkuData(String brand_cd, String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<FoodStoreInsertDataGetterSetter> list = new ArrayList<FoodStoreInsertDataGetterSetter>();
        Cursor dbcursor = null;

        try {

            String food = "Food";
            dbcursor = db
                    .rawQuery(
                            "SELECT SD.SKU_CD, SD.SKU, SD.PACKING FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD ='" + brand_cd + "' AND CD.STORE_CD ='" + store_cd + "' AND SD.CATEGORY_TYPE ='" + food + "' ORDER BY CD.SKU_SEQUENCE"
                            , null);

            //	"SELECT  SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD =2 AND SD.CATEGORY_TYPE ='Food' AND CD.STORE_CD = 392 ORDER BY CD.SKU_SEQUENCE"

            //WHERE SD.BRAND_CD ='"+brand_cd+"' AND SD.CATEGORY_TYPE ='"+"Food"+"' AND CD.STORE_CD ='"+store_cd+"'

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FoodStoreInsertDataGetterSetter sb = new FoodStoreInsertDataGetterSetter();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setActual_listed("NO");
                    sb.setAs_per_meccain("");
                    sb.setPacking_size(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PACKING")));
                    sb.setMccain_df("");
                    sb.setStore_df("");
                    sb.setMtd_sales("");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//Stock Sku Closing data

    public ArrayList<StockNewGetterSetter> getStockSkuClosingData(String brand_cd, String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE CD.STORE_CD= '" + store_cd + "' AND SD.BRAND_CD ='" + brand_cd + "' ORDER BY SD.SKU_SEQUENCE"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setClosing_stock("");


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//Stock Midday Sku data

    public ArrayList<StockNewGetterSetter> getStockSkuMiddayData(String brand_cd, String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE CD.STORE_CD= '" + store_cd + "' AND SD.BRAND_CD ='" + brand_cd + "' ORDER BY SD.SKU_SEQUENCE"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setTotal_mid_stock_received("");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<MiddayStockInsertData> getStockSkuMiddayZeroData(String brand_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<MiddayStockInsertData> list = new ArrayList<MiddayStockInsertData>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD ='" + brand_cd + "' ORDER BY CD.SKU_SEQUENCE"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MiddayStockInsertData sb = new MiddayStockInsertData();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setMidday_stock("0");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }

//Promotion brand data


    public ArrayList<windowsChildData> getPromotionBrandData(String store_cd, String STATE_CD, String STORE_TYPE_CD) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<windowsChildData> list = new ArrayList<windowsChildData>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT WM.WINDOW, WM.WINDOW_CD FROM WINDOW_MASTER WM INNER JOIN WINDOW_MAPPING WMM ON WM.WINDOW_CD = WMM.WINDOW_CD WHERE WMM.STORETYPE_CD ='" + STORE_TYPE_CD + "' AND WMM.STATE_CD ='" + STATE_CD + "'   ", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    windowsChildData sb = new windowsChildData();


                    sb.setWINDOW(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW")));

                    sb.setWINDOW_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));


                    sb.setSTATUS("");
                    sb.setSTATUS_CD("");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }

    //WINDOW List data

    public ArrayList<WindowGetterSetter> getWindowListData() {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<WindowGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM WINDOW_MASTER WHERE WINDOW_CD IN(SELECT DISTINCT WINDOW_CD FROM WINDOW_MAPPING)", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    WindowGetterSetter w = new WindowGetterSetter();


                    w.setWindow(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW")));

                    w.setWindow_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));
                    w.setPlanogram(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PLANOGRAM_IMAGE")));

                    list.add(w);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<windowsChildData> getWindowsHeaderData(String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<windowsChildData> list = new ArrayList<windowsChildData>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT WINDOW_CD, WINDOW, KEY_ID FROM openingHeader_Windows_data WHERE STORE_CD ='" + store_cd + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    windowsChildData sb = new windowsChildData();


                    sb.setWINDOW_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));

                    sb.setWINDOW(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW")));

                    sb.setHeaderRefId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("KEY_ID")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching Asset brand---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//Promotion brand data


    public ArrayList<AssetInsertdataGetterSetter> getAssetCategoryData(String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT CATEGORY_CD, CATEGORY FROM CATEGORY_MASTER BD WHERE CATEGORY_CD IN( SELECT DISTINCT CATEGORY_CD FROM MAPPING_ASSET WHERE STORE_CD ='" + store_cd + "' ) "
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();


                    sb.setCategory_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));

                    sb.setCategory(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching Asset brand---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    // get promotion Sku data
    public ArrayList<PromotionInsertDataGetterSetter> getPromotionSkuData(String brand_cd, String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<PromotionInsertDataGetterSetter> list = new ArrayList<PromotionInsertDataGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT CD.PROMOTION, CD.PID FROM MAPPING_PROMOTION CD INNER JOIN BRAND_MASTER SD ON CD.BRAND_CD = SD.BRAND_CD WHERE SD.BRAND_CD ='" + brand_cd + "' AND CD.STORE_CD ='" + store_cd + "'"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    PromotionInsertDataGetterSetter sb = new PromotionInsertDataGetterSetter();


							/*sb.setSku_cd(dbcursor.getString(dbcursor
                                    .getColumnIndexOrThrow("SKU_CD")));

							sb.setSku(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("SKU")));*/

                    sb.setPromotion_txt(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PROMOTION")));
                    sb.setPresent("NO");
                    sb.setRemark("");
                    sb.setPid(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PID")));
                    sb.setImg("");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    /*// get Asset data
                        public ArrayList<PromotionInsertDataGetterSetter> getAssetSkuData(String brand_cd) {
							Log.d("FetchingStoredata--------------->Start<------------",
									"------------------");
							ArrayList<PromotionInsertDataGetterSetter> list = new ArrayList<PromotionInsertDataGetterSetter>();
							Cursor dbcursor = null;

							try {

								dbcursor = db
										.rawQuery(
												"SELECT DISTINCT SD.SKU_CD, SD.SKU, CD.PROMOTION,CD.CATEGORY_TYPE FROM MAPPING_PROMOTION CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD ='"+brand_cd+"' ORDER BY CD.SKU_SEQUENCE"
														, null);


								if (dbcursor != null) {
									dbcursor.moveToFirst();
									while (!dbcursor.isAfterLast()) {
										PromotionInsertDataGetterSetter sb = new PromotionInsertDataGetterSetter();


										sb.setSku_cd(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("SKU_CD")));

										sb.setSku(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("SKU")));

										sb.setPromotion_txt(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("PROMOTION")));
										sb.setPresent("NO");
										sb.setRemark("remark");
										sb.setCategory_type(dbcursor.getString(dbcursor
												.getColumnIndexOrThrow("CATEGORY_TYPE")));

										list.add(sb);
										dbcursor.moveToNext();
									}
									dbcursor.close();
									return list;
								}

							} catch (Exception e) {
								Log.d("Exception when fetching opening stock!!!!!!!!!!!",
										e.toString());
								return list;
							}

							Log.d("Fetching opening stock---------------------->Stop<-----------",
									"-------------------");
							return list;
						}
*/
    // get Asset data
    public ArrayList<AssetInsertdataGetterSetter> getAssetData(String category_cd, String store_cd) {
        Log.d("FetchingAssetdata--------------->Start<------------",
                "------------------");
        ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT AM.ASSET_CD, AM.ASSET, M.CATEGORY_CD FROM MAPPING_ASSET M INNER JOIN ASSET_MASTER AM ON M.ASSET_CD = AM.ASSET_CD WHERE M.STORE_CD ='" + store_cd + "' AND M.CATEGORY_CD ='" + category_cd + "'"
                            , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();


                    sb.setAsset(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ASSET")));

                    sb.setAsset_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ASSET_CD")));


                    sb.setPresent("NO");
                    sb.setRemark("");
                    sb.setImg("");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Asset!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching asset data---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    // get Asset data
    public ArrayList<AssetInsertdataGetterSetter> getAllAssetData() {
        Log.d("FetchingAssetdata--------------->Start<------------",
                "------------------");
        ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM ASSET_MASTER"
                            , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();


                    sb.setAsset(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ASSET")));

                    sb.setAsset_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ASSET_CD")));


                    sb.setPresent("NO");
                    sb.setRemark("");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Asset!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching asset data---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    // getNonWorkingData
    public ArrayList<NonWorkingReasonGetterSetter> getNonWorkingData(boolean isAll) {
        Log.d("FetchingAssetdata--------------->Start<------------",
                "------------------");
        ArrayList<NonWorkingReasonGetterSetter> list = new ArrayList<NonWorkingReasonGetterSetter>();
        Cursor dbcursor = null;

        try {

            if (isAll) {
                dbcursor = db
                        .rawQuery(
                                "SELECT -1 as REASON_CD,'Select' as REASON,-1 as ENTRY_ALLOW,-1 as IMAGE_ALLOW union Select REASON_CD,REASON,ENTRY_ALLOW,IMAGE_ALLOW FROM NON_WORKING_REASON"
                                , null);
            } else {
                dbcursor = db
                        .rawQuery(
                                "select -1 as REASON_CD,'select' as REASON union Select REASON_CD,REASON from NON_WORKING_REASON where REASON in (select distinct REASON from JOURNEY_PLAN_PREV)"
                                , null);
            }


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    NonWorkingReasonGetterSetter sb = new NonWorkingReasonGetterSetter();


                    sb.setReason_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON_CD")));

                    sb.setReason(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON")));
                    if (isAll) {
                        sb.setEntry_allow(dbcursor.getString(dbcursor
                                .getColumnIndexOrThrow("ENTRY_ALLOW")));

                        sb.setIMAGE_ALLOW(dbcursor.getString(dbcursor
                                .getColumnIndexOrThrow("IMAGE_ALLOW")));
                    }
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Non working!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching non working data---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    // getNonWorkingData
    public ArrayList<WindowNonReasonGetterSetter> getWindowNonReasonData() {
        Log.d("FetchingAssetdata--------------->Start<------------",
                "------------------");
        ArrayList<WindowNonReasonGetterSetter> list = new ArrayList<WindowNonReasonGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM WINDOW_NON_REASON"
                            , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    WindowNonReasonGetterSetter sb = new WindowNonReasonGetterSetter();


                    sb.setWREASON_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WREASON_CD")));

                    sb.setWREASON(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WREASON")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Non working!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching non working data---------------------->Stop<-----------",
                "-------------------");
        return list;
    }

    // getNonWorkingSubReasonData data
    public ArrayList<NonWorkingSubReasonGetterSetter> getNonWorkingSubReasonData() {

        ArrayList<NonWorkingSubReasonGetterSetter> list = new ArrayList<NonWorkingSubReasonGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM NON_WORKING_SUB_REASON"
                            , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    NonWorkingSubReasonGetterSetter sb = new NonWorkingSubReasonGetterSetter();

                    sb.setSUB_REASON_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SUB_REASON_CD")));

                    sb.setSUB_REASON(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SUB_REASON")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Non working!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching non working data---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //Insert Opening Stock Data

		/*	public void insertOpeningStockData(StockNewGetterSetter data,String sku_cd) {

				db.delete(CommonString1.TABLE_OPENING_STOCK_DATA, "SKU_CD" + "='" + sku_cd + "'", null);
				ContentValues values = new ContentValues();

				try {


						values.put("SKU_CD", Integer.parseInt(data.getSku_cd()));
						values.put("AS_PER_MECCAN", data.getAs_per_meccain());
						values.put("ACTUAL_LISTED", data.getActual_listed());
						values.put("OPENING_STOCK_COLD_ROOM", data.getOpen_stock_cold_room());
						values.put("OPENING_STOCK_MCCAIN_DF", data.getOpen_stock_mccaindf());
						values.put("TOTAL_FACING_MCCAIN_DF", data.getTotalfacing_mccaindf());
						values.put("OPENING_STOCK_STORE_DF", data.getOpen_stock_store_df());
						values.put("TOTAL_FACING_STORE_DF", data.getTotal_facing_storedf());
						values.put("MATERIAL_WELLNESS", data.getMaterial_wellness());

						db.insert(CommonString1.TABLE_OPENING_STOCK_DATA, null, values);



				} catch (Exception ex) {
					Log.d("Database Exception while Insert OPENING STOCK Data ",
							ex.toString());
				}

			}*/


//Insert Opening Data with Brand

    public void InsertOpeningStocklistData(String storeid, String STATE_CD, String STORE_TYPE_CD,
                                           HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {

                values.put("STORE_CD", storeid);
                values.put("STATE_CD", STATE_CD);
                values.put("STORE_TYPE_CD", STORE_TYPE_CD);

                values.put("BRAND_CD", save_listDataHeader.get(i)
                        .getBrand_cd());
                values.put("BRAND", save_listDataHeader
                        .get(i).getBrand());

                long l = db.insert(CommonString1.TABLE_INSERT_OPENINGHEADER_DATA, null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put("Common_Id", (int) l);
                    values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j).getSku());
                    values1.put("SKU_CD", data.get(save_listDataHeader.get(i)).get(j).getSku_cd());
                    values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_cd());
                    values1.put("BRAND", save_listDataHeader.get(i).getBrand());
                    values1.put("STORE_CD", storeid);
                    values1.put("STATE_CD", STATE_CD);
                    values1.put("STORE_TYPE_CD", STORE_TYPE_CD);


                    values1.put("OPENING_TOTAL_STOCK", data.get(save_listDataHeader.get(i)).get(j).getOpenning_total_stock());
                    values1.put("FACING", data.get(save_listDataHeader.get(i)).get(j).getOpening_facing());
                    values1.put("STOCK_UNDER_DAYS", data.get(save_listDataHeader.get(i)).get(j).getStock_under45days());
                    values1.put("STOCK_UNDER_12", data.get(save_listDataHeader.get(i)).get(j).getStockunder12());
                    values1.put("STOCK_GREATER_13", data.get(save_listDataHeader.get(i)).get(j).getStockgreater13());


                    //values1.put("EXPRIY_DATE", data.get(save_listDataHeader.get(i)).get(j).getDate());

                    db.insert(CommonString1.TABLE_STOCK_DATA, null, values1);

                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }

    //InsertOpeningStocklistData2
    public void InsertOpeningStocklistData2(String storeid, String STATE_CD, String STORE_TYPE_CD,
                                            HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {

                values.put("STORE_CD", storeid);
                values.put("STATE_CD", STATE_CD);
                values.put("STORE_TYPE_CD", STORE_TYPE_CD);

                values.put("BRAND_CD", save_listDataHeader.get(i)
                        .getBrand_cd());
                values.put("BRAND", save_listDataHeader
                        .get(i).getBrand());

                long l = db.insert(CommonString1.TABLE_INSERT_OPENINGHEADER_DATA, null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put("Common_Id", (int) l);
                    values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j).getSku());
                    values1.put("SKU_CD", data.get(save_listDataHeader.get(i)).get(j).getSku_cd());
                    values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_cd());
                    values1.put("BRAND", save_listDataHeader.get(i).getBrand());
                    values1.put("STORE_CD", storeid);
                    values1.put("STATE_CD", STATE_CD);
                    values1.put("STORE_TYPE_CD", STORE_TYPE_CD);
                    values1.put("OPENING_STOCK_PRESENT", data.get(save_listDataHeader.get(i)).get(j).getSkuQuantity());

                    db.insert(CommonString1.TABLE_STOCK_DATA_PRESENT, null, values1);

                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while InsertOpeningStocklistData2",
                    ex.toString());
        }

    }

    //Insert Header Data with img

    public void InsertHeaderOpeningStocklistData(String storeid, String STATE_CD, String STORE_TYPE_CD, String visit_date, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
//		ContentValues values1 = new ContentValues();

        try {

            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {

                values.put("STORE_CD", storeid);

                values.put("STATE_CD", storeid);
                values.put("STORE_TYPE_CD", storeid);


                values.put("BRAND_CD", save_listDataHeader.get(i)
                        .getBrand_cd());
                values.put("BRAND", save_listDataHeader
                        .get(i).getBrand());
                values.put("BRAND_SEQUENCE", save_listDataHeader
                        .get(i).getBRAND_SEQUENCE());
                values.put("CATEGORY_CD", save_listDataHeader
                        .get(i).getCATEGORY_CD());

                values.put("COMPANY_CD()", save_listDataHeader
                        .get(i).getCOMPANY_CD());
                values.put("VISIT_DATE", visit_date);


                long l = db.insert(CommonString1.TABLE_STOCK_IMAGE,
                        null, values);

            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Header Data ",
                    ex.toString());
        }

    }

//Update Opening Data with Brand

    public void UpdateOpeningStocklistData(String storeid,
                                           HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {

        //	ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();

            list = getHeaderStock(storeid);

            db.beginTransaction();

            for (int i = 0; i < list.size(); i++) {

						/*values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString1.TABLE_INSERT_OPENINGHEADER_DATA,
								null, values);*/

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    //values1.put("Common_Id", Integer.parseInt(list.get(i).getKeyId()));
                    //values1.put("STORE_CD", storeid);
                    //values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
                    //		.getSku_cd()));

                    values1.put("OPENING_TOTAL_STOCK", data.get(save_listDataHeader.get(i)).get(j).getOpenning_total_stock());
                    values1.put("FACING", data.get(save_listDataHeader.get(i)).get(j).getOpening_facing());
                    values1.put("STOCK_UNDER_DAYS", data.get(save_listDataHeader.get(i)).get(j).getStock_under45days());

                    values1.put("STOCK_UNDER_12", data.get(save_listDataHeader.get(i)).get(j).getStockunder12());
                    values1.put("STOCK_GREATER_13", data.get(save_listDataHeader.get(i)).get(j).getStockgreater13());

                    //	values1.put("EXPRIY_DATE", data.get(save_listDataHeader.get(i)).get(j).getDate());

                    //db.insert(CommonString1.TABLE_STOCK_DATA, null, values1);
                    db.update(CommonString1.TABLE_STOCK_DATA, values1, "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD " + "='" + Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd()) + "'", null);

                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }

    //-------------------UpdateOpeningStocklistData2------------------
    public void UpdateOpeningStocklistData2(String storeid,
                                            HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values1 = new ContentValues();
        try {

            ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();
            list = getHeaderStock(storeid);
            db.beginTransaction();

            for (int i = 0; i < list.size(); i++) {

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put("OPENING_STOCK_PRESENT", data.get(save_listDataHeader.get(i)).get(j).getSkuQuantity());
                    db.update(CommonString1.TABLE_STOCK_DATA_PRESENT, values1, "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD " + "='" + Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd()) + "'", null);

                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }

    //Update Opening Data with Brand

    public void UpdateHeaderOpeningStocklistData(String storeid, String visit_date, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        //ContentValues values1 = new ContentValues();

        try {


            db.beginTransaction();

            for (int i = 0; i < save_listDataHeader.size(); i++) {

						/*values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());*/

                values.put("IMAGE_STK", save_listDataHeader
                        .get(i).getImg_cam());

                db.update(CommonString1.TABLE_STOCK_IMAGE, values, "STORE_CD" + "='" + storeid + "' AND BRAND_CD " + "='" + Integer.parseInt(save_listDataHeader.get(i).getBrand_cd()) + "' AND VISIT_DATE  ='" + visit_date + "'", null);

            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }


//Insert Closing Data with Brand

    public void InsertClosingStocklistData(String storeid,
                                           HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {

                values.put("STORE_CD", storeid);

                values.put("BRAND_CD", save_listDataHeader.get(i)
                        .getBrand_cd());
                values.put("BRAND", save_listDataHeader
                        .get(i).getBrand());

                long l = db.insert(CommonString1.TABLE_INSERT_OPENINGHEADER_DATA,
                        null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put("Common_Id", (int) l);

                    values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_cd());
                    values1.put("BRAND", save_listDataHeader.get(i).getBrand());

                    values1.put("STORE_CD", storeid);
                    values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j).getSku());
                    values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
                            .getSku_cd()));
                    values1.put("CLOSING_STOCK", data.get(save_listDataHeader.get(i)).get(j).getClosing_stock());

                    db.insert(CommonString1.TABLE_STOCK_DATA, null, values1);

                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }

//Update Closing Data with Brand

    public void UpdateClosingStocklistData(String storeid,
                                           HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {

        //ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();

            list = getHeaderStock(storeid);

            db.beginTransaction();

            for (int i = 0; i < list.size(); i++) {

					/*	values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString1.TABLE_INSERT_OPENINGHEADER_CLOSING_DATA,
								null, values);*/

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

						/*	values1.put("Common_Id", (int)l);
                            values1.put("STORE_CD", storeid);
							values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getSku_cd()));*/
                    values1.put("CLOSING_STOCK", data.get(save_listDataHeader.get(i)).get(j).getClosing_stock());


                    //db.insert(CommonString1.TABLE_STOCK_DATA, null, values1);
                    db.update(CommonString1.TABLE_STOCK_DATA, values1, "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD " + "='" + Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd()) + "'", null);
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }


//Insert Midday Data with Brand

    public void InsertMiddayStocklistData(String storeid,
                                          HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {

                values.put("STORE_CD", storeid);

                values.put("BRAND_CD", save_listDataHeader.get(i)
                        .getBrand_cd());
                values.put("BRAND", save_listDataHeader
                        .get(i).getBrand());

                long l = db.insert(CommonString1.TABLE_INSERT_OPENINGHEADER_DATA,
                        null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put("Common_Id", (int) l);

                    values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_cd());
                    values1.put("BRAND", save_listDataHeader.get(i).getBrand());

                    values1.put("STORE_CD", storeid);
                    values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j).getSku());
                    values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
                            .getSku_cd()));
                    values1.put("MIDDAY_TOTAL_STOCK", data.get(save_listDataHeader.get(i)).get(j).getTotal_mid_stock_received());

                    db.insert(CommonString1.TABLE_STOCK_DATA, null, values1);

                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }

//Update Midday Data with Brand

    public void UpdateMiddayStocklistData(String storeid,
                                          HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {

        //ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();

            list = getHeaderStock(storeid);

            //db.beginTransaction();
            for (int i = 0; i < list.size(); i++) {

						/*values.put("STORE_CD", storeid);

						values.put("BRAND_CD", save_listDataHeader.get(i)
								.getBrand_cd());
						values.put("BRAND", save_listDataHeader
								.get(i).getBrand());

						long l = db.insert(CommonString1.TABLE_INSERT_HEADER_MIDDAY_DATA,
								null, values);*/

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

							/*values1.put("Common_Id", (int)l);
							values1.put("STORE_CD", storeid);
							values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
									.getSku_cd()));*/
                    values1.put("MIDDAY_TOTAL_STOCK", data.get(save_listDataHeader.get(i)).get(j).getTotal_mid_stock_received());

                    //db.insert(CommonString1.TABLE_STOCK_DATA, null, values1);
                    db.update(CommonString1.TABLE_STOCK_DATA, values1, "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD " + "='" + Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd()) + "'", null);

                }
            }
					/*db.setTransactionSuccessful();
					db.endTransaction();*/
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }


//Insert Food Store List data

    public void InsertFoodStorelistData(String storeid,
                                        HashMap<StockNewGetterSetter, List<FoodStoreInsertDataGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {

                values.put("STORE_CD", storeid);

                values.put("BRAND_CD", save_listDataHeader.get(i)
                        .getBrand_cd());
                values.put("BRAND", save_listDataHeader
                        .get(i).getBrand());

                long l = db.insert(CommonString1.TABLE_INSERT_HEADER_FOOD_STORE_DATA,
                        null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put("Common_Id", (int) l);
                    values1.put("STORE_CD", storeid);
                    values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
                            .getSku_cd()));
                    values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j)
                            .getSku());
                    values1.put("ACTUAL_LISTED", data.get(save_listDataHeader.get(i)).get(j).getActual_listed());
                    values1.put("AS_PER_MCCAIN", data.get(save_listDataHeader.get(i)).get(j).getAs_per_meccain());
                    values1.put("MCCAIN_DF", data.get(save_listDataHeader.get(i)).get(j).getMccain_df());
                    values1.put("STORE_DF", data.get(save_listDataHeader.get(i)).get(j).getStore_df());
                    values1.put("PACKING_SIZE", data.get(save_listDataHeader.get(i)).get(j).getPacking_size());
                    values1.put("MTD_SALES", data.get(save_listDataHeader.get(i)).get(j).getMtd_sales());

                    db.insert(CommonString1.TABLE_FOOD_STORE_DATA, null, values1);

                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }

//Insert Promotion Data with Brand

    public void InsertwindowsData(String storeid, String STATE_CD, String STORE_TYPE_CD, String _path,
                                  HashMap<windowsChildData, List<windowsChildData>> data, List<windowsChildData> save_listDataHeader, List<MappingAssetGetterSetter> checklistInsertDataGetterSetters) {

        db.delete(CommonString1.TABLE_INSERT_PROMOTION_HEADER_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

        db.delete(CommonString1.TABLE_PROMOTION_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        db.delete(CommonString1.TABLE_CHECK_LIST_DATA, CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);


        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();

        try {

            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {

                values.put("STORE_CD", storeid);

                values.put("STATE_CD", STATE_CD);
                values.put("STORE_TYPE_CD", STORE_TYPE_CD);

                values.put("WINDOW_CD", save_listDataHeader.get(i).getWINDOW_CD());


                values.put("WINDOW", save_listDataHeader
                        .get(i).getWINDOW());

                long l = db.insert(CommonString1.TABLE_INSERT_PROMOTION_HEADER_DATA,
                        null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put("Common_Id", (int) l);
                    values1.put("STORE_CD", storeid);
                    values1.put("STATE_CD", STATE_CD);
                    values1.put("STORE_TYPE_CD", STORE_TYPE_CD);


                    //values1.put("Backsheet", data.get(save_listDataHeader.get(i)).get(j).getBacksheet());

                    //values1.put("ShelfStrip", data.get(save_listDataHeader.get(i)).get(j).getShelfStrip());
                    //values1.put("POG", data.get(save_listDataHeader.get(i)).get(j).getPOG());

                    values1.put("IMAGE", data.get(save_listDataHeader.get(i)).get(j).getImage());
                    values1.put("STATUS_CD", data.get(save_listDataHeader.get(i)).get(j).getSTATUS_CD());
                    values1.put("REASON", data.get(save_listDataHeader.get(i)).get(j).getRemarks());



							/*values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j)
									.getSku());*/

                    //values1.put("CATEGORY_TYPE", data.get(save_listDataHeader.get(i)).get(j).getCategory_type());
							/*values1.put("REMARK", data.get(save_listDataHeader.get(i)).get(j).getRemark());
							values1.put("PRESENT", data.get(save_listDataHeader.get(i)).get(j).getPresent());*/

                    db.insert(CommonString1.TABLE_PROMOTION_DATA, null, values1);

                }
                for (int k = 0; k < checklistInsertDataGetterSetters.size(); k++) {


                    if (checklistInsertDataGetterSetters.get(k).getWindows_cd().equals(save_listDataHeader.get(i).getWINDOW_CD())) {

                        values2.put("Common_Id", (int) l);
                        values2.put("STORE_CD", storeid);

                        values2.put("Status", checklistInsertDataGetterSetters.get(k).getStatus());
                        values2.put("CHECKLIST", checklistInsertDataGetterSetters.get(k).getCHECKLIST().get(0));
                        values2.put("CHECKLIST_ID", checklistInsertDataGetterSetters.get(k).getCHECKLIST_ID().get(0));

                        values2.put("Windows_cd", checklistInsertDataGetterSetters.get(k).getWindows_cd());


                        db.insert(CommonString1.TABLE_CHECK_LIST_DATA, null, values2);

                    }


                }


            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }


//Insert Asset Data with Brand

    public void InsertAssetData(String storeid,
                                HashMap<AssetInsertdataGetterSetter, List<AssetInsertdataGetterSetter>> data, List<AssetInsertdataGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {

            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {

                values.put("STORE_CD", storeid);

                values.put("CATEGORY_CD", save_listDataHeader.get(i)
                        .getCategory_cd());
                values.put("CATEGORY", save_listDataHeader
                        .get(i).getCategory());

                long l = db.insert(CommonString1.TABLE_INSERT_ASSET_HEADER_DATA,
                        null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put("Common_Id", (int) l);
                    values1.put("STORE_CD", storeid);
                    values1.put("ASSET_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
                            .getAsset_cd()));
                    values1.put("ASSET", data.get(save_listDataHeader.get(i)).get(j).getAsset());
                    values1.put("REMARK", data.get(save_listDataHeader.get(i)).get(j).getRemark());
                    values1.put("PRESENT", data.get(save_listDataHeader.get(i)).get(j).getPresent());
                    values1.put("IMAGE", data.get(save_listDataHeader.get(i)).get(j).getImg());

                    db.insert(CommonString1.TABLE_ASSET_DATA, null, values1);

                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }


    //Get Asset Upload Data

    public ArrayList<AssetInsertdataGetterSetter> getAssetDataFromdatabase(String storeId, String category_cd) {
        Log.d("FetchingAssetuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.ASSET_CD, SD.ASSET, SD.PRESENT, SD.REMARK, SD.IMAGE, CD.CATEGORY_CD, CD.CATEGORY " +
                                    "FROM openingHeader_Asset_data CD INNER JOIN ASSET_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "' AND CD.CATEGORY_CD = '" + category_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();

                    sb.setAsset_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ASSET_CD")));

                    sb.setAsset(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ASSET")));

                    sb.setPresent(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PRESENT")));
                    sb.setRemark(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REMARK")));
                    sb.setImg(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE")));
                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //check if table is empty
    public boolean isPOSMDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM POSM_DATA_2 WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


//Get Asset Upload Data

    public ArrayList<AssetInsertdataGetterSetter> getAssetUpload(String storeId) {
        Log.d("FetchingAssetuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<AssetInsertdataGetterSetter> list = new ArrayList<AssetInsertdataGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.ASSET_CD,SD.ASSET,SD.PRESENT,SD.REMARK, SD.IMAGE,CD.CATEGORY_CD, CD.CATEGORY " +
                                    "FROM openingHeader_Asset_data CD INNER JOIN ASSET_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    AssetInsertdataGetterSetter sb = new AssetInsertdataGetterSetter();

                    sb.setAsset_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ASSET_CD")));

                    sb.setAsset(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ASSET")));
                    sb.setImg(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE")));

                    sb.setPresent(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PRESENT")));
                    sb.setRemark(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REMARK")));
                    sb.setCategory_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //Get Promotion Upload Data

    public ArrayList<windowsChildData> getwindowsUpload(String storeId) {
        Log.d("FetchingPromotionuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<windowsChildData> list = new ArrayList<windowsChildData>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT CD.WINDOW_CD, CD.KEY_ID, SD.STATUS_CD, SD.REASON, SD.IMAGE " +
                                    "FROM openingHeader_Windows_data CD INNER JOIN WINDOWS_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    windowsChildData sb = new windowsChildData();

                    sb.setHeaderRefId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("KEY_ID")));


                    sb.setWINDOW_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));


                    sb.setRemarks(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON")));


                    sb.setSTATUS_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STATUS_CD")));

                    sb.setImage(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<StoreSignAgeGetterSetter> getSTOREDETAILUpload(String storeId) {
        Log.d("FetchingPromotionuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<StoreSignAgeGetterSetter> list = new ArrayList<StoreSignAgeGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * from STORE_FIRST_TIME WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreSignAgeGetterSetter sb = new StoreSignAgeGetterSetter();


                    sb.setSTORE_ID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_CD")));


                    sb.setName(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("NAME")));


                    sb.setContact(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CONTACT")));

                    sb.setAddress(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ADDRESS")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<MappingAssetGetterSetter> getCheckListUpload(String storeId) {
        Log.d("FetchingPromotionuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<MappingAssetGetterSetter> list = new ArrayList<MappingAssetGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT CD.WINDOW_CD, CD.KEY_ID, SD.Status, SD.CHECKLIST_ID " +
                                    "FROM openingHeader_Windows_data CD INNER JOIN CheckList_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingAssetGetterSetter sb = new MappingAssetGetterSetter();

                    sb.setRefId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("KEY_ID")));


                    sb.setWindows_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));


                    sb.setStatus(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("Status")));


                    sb.setCHECKLIST_ID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST_ID")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //Get Promotion Upload Data

    public ArrayList<windowsChildData> getWindowsFromDatabase(String storeId, String key_id) {
        Log.d("FetchingPromotionuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<windowsChildData> list = new ArrayList<windowsChildData>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.REASON, SD.STATUS_CD, SD.IMAGE" +
                                    " FROM openingHeader_Windows_data CD INNER JOIN WINDOWS_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '" + storeId + "' AND SD.Common_Id = '" + key_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    windowsChildData sb = new windowsChildData();

                    sb.setRemarks(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON")));
                    sb.setSTATUS_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STATUS_CD")));

                    sb.setImage(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE")));

							/*sb.setPid(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PID")));
							sb.setPromotion_txt(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PROMOTION")));
							sb.setImg(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("IMAGE")));

							*//*sb.setCategory_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE")));*//*
							sb.setPresent(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PRESENT")));
							sb.setRemark(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("REMARK")));
							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));
							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));*/


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<MappingAssetGetterSetter> getCheckListFromDatabase(String key_id) {
        Log.d("FetchingPromotionuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<MappingAssetGetterSetter> list = new ArrayList<MappingAssetGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM CheckList_DATA WHERE Common_Id= '" + key_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingAssetGetterSetter sb = new MappingAssetGetterSetter();

                    sb.setStatus(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("Status")));
                    sb.setCHECKLIST(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST")));

                    sb.setCHECKLIST_ID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST_ID")));

                    sb.setWindows_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("Windows_cd")));

							/*sb.setPid(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PID")));
							sb.setPromotion_txt(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PROMOTION")));
							sb.setImg(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("IMAGE")));

							*//*sb.setCategory_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE")));*//*
							sb.setPresent(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("PRESENT")));
							sb.setRemark(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("REMARK")));
							sb.setBrand_cd(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND_CD")));
							sb.setBrand(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("BRAND")));*/


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //check if table is empty
    public boolean isWINDOWSDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM WINDOWS_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    public boolean isWINDOWSDatasku(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM WINDOWS_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


//Get Promotion Upload Data

    public ArrayList<FoodStoreInsertDataGetterSetter> getFoodStoreUpload(String storeId) {
        Log.d("FetchingPromotionuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<FoodStoreInsertDataGetterSetter> list = new ArrayList<FoodStoreInsertDataGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.SKU_CD,SD.AS_PER_MCCAIN,SD.ACTUAL_LISTED,SD.MCCAIN_DF,SD.STORE_DF, SD.MTD_SALES, SD.PACKING_SIZE, CD.BRAND_CD,CD.BRAND " +
                                    "FROM openingHeader_FOOD_STORE_data CD INNER JOIN FOOD_STORE_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FoodStoreInsertDataGetterSetter sb = new FoodStoreInsertDataGetterSetter();

                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

							/*sb.setAs_per_meccain(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("AS_PER_MCCAIN")));*/

                    sb.setActual_listed(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ACTUAL_LISTED")));
                    sb.setMccain_df(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MCCAIN_DF")));
                    sb.setStore_df(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_DF")));
                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));
							/*sb.setMtd_sales(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MTD_SALES")));*/

                    if (dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MTD_SALES")) == null || dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MTD_SALES")).equals("")) {

                        sb.setMtd_sales("0");

                    } else {
                        sb.setMtd_sales(dbcursor.getString(dbcursor
                                .getColumnIndexOrThrow("MTD_SALES")));

                    }

                    sb.setPacking_size(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PACKING_SIZE")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingFoodStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }

//Get Promotion Database Data

    public ArrayList<FoodStoreInsertDataGetterSetter> getFoodStoreDataFromDatabase(String storeId, String brand_cd) {
        Log.d("FetchingPromotionuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<FoodStoreInsertDataGetterSetter> list = new ArrayList<FoodStoreInsertDataGetterSetter>();
        Cursor dbcursor = null;
        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.SKU_CD, SD.SKU,SD.AS_PER_MCCAIN,SD.ACTUAL_LISTED,SD.MCCAIN_DF,SD.STORE_DF, SD.MTD_SALES, SD.PACKING_SIZE, CD.BRAND_CD,CD.BRAND " +
                                    "FROM openingHeader_FOOD_STORE_data CD INNER JOIN FOOD_STORE_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "' AND CD.BRAND_CD = '" + brand_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FoodStoreInsertDataGetterSetter sb = new FoodStoreInsertDataGetterSetter();

                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));
                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setAs_per_meccain(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AS_PER_MCCAIN")));

                    sb.setActual_listed(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ACTUAL_LISTED")));
                    sb.setMccain_df(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MCCAIN_DF")));
                    sb.setStore_df(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_DF")));
                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));
                    sb.setMtd_sales(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MTD_SALES")));
                    sb.setPacking_size(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PACKING_SIZE")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingFoodStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //check if table is empty
    public boolean isFoodDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM FOOD_STORE_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


//Get Stock Upload Data

    public ArrayList<StockNewGetterSetter> getStockUpload(String storeId) {
        Log.d("FetchingPromotionuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;
        try {

            dbcursor = db
                    .rawQuery("SELECT * FROM STOCK_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();

                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));


                    sb.setOpenning_total_stock(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("OPENING_TOTAL_STOCK")));

                    sb.setOpening_facing(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("FACING")));

                    sb.setStock_under45days(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STOCK_UNDER_DAYS")));
                    sb.setStockunder12(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STOCK_UNDER_12")));

                    sb.setStockgreater13(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STOCK_GREATER_13")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingFoodStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }

    // ----------------new getStockPresentUpload-------------------
    public ArrayList<StockNewGetterSetter> getStockPresentUpload(String storeId) {
        Log.d("FetchingPromotionuploaddata--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;
        try {

            dbcursor = db
                    .rawQuery("SELECT * FROM STOCK_DATA_PRESENT WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();

                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));
                    sb.setSkuQuantity(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("OPENING_STOCK_PRESENT")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingFoodStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //Get Opening and Midday Stock for validation

    public ArrayList<StockGetterSetter> getOpeningNMiddayStock(String storeId) {
        Log.d("FetchingOpening Midday Stock data--------------->Start<------------",
                "------------------");
        ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT SD.SKU_CD,SD.OPENING_STOCK_COLD_ROOM, SD.OPENING_STOCK_MCCAIN_DF, SD.OPENING_STOCK_STORE_DF, SD.MIDDAY_STOCK " +
                                    "FROM openingHeader_data CD INNER JOIN OPENING_STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id JOIN MIDDAY_STOCK_DATA MD ON CD.KEY_ID=MD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "'", null);

					/*dbcursor = db
							.rawQuery(
									"SELECT DISTINCT SD.SKU_CD FROM openingHeader_Midday_data CD INNER JOIN MIDDAY_STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'  AS A INNER JOIN SELECT DISTINCT SD.SKU_CD FROM openingHeader_data CD INNER JOIN OPENING_STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
											+ storeId + "'  AS B ON A.SKU_CD=B.SKU_CD AND A.BRAND_CD=B.BRAND_CD", null);*/


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockGetterSetter sb = new StockGetterSetter();

                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));
                    sb.setOpen_stock_mccaindf(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("OPENING_STOCK_MCCAIN_DF")));
                    sb.setOpen_stock_cold_room(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("OPENING_STOCK_COLD_ROOM")));
                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));
                    sb.setOpen_stock_store_df(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("OPENING_STOCK_STORE_DF")));
                    sb.setMidday_stock(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MIDDAY_STOCK")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //opening stock

    public boolean checkStock(String STORE_CD) {
        Log.d("FetchingOpening Stock data--------------->Start<------------",
                "------------------");
        ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT SD.SKU_CD FROM openingHeader_data CD INNER JOIN STOCK_DATA_PRESENT SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + STORE_CD + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockGetterSetter sb = new StockGetterSetter();

                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();

                if (list.size() > 0) {
                    return true;
                } else {
                    return false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return false;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return false;
    }


    //opening stock

    public ArrayList<StockGetterSetter> getOpeningStock(String storeId) {
        Log.d("FetchingOpening Stock data--------------->Start<------------",
                "------------------");
        ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.OPENING_STOCK_COLD_ROOM FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockGetterSetter sb = new StockGetterSetter();

                    sb.setOpen_stock_cold_room(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("OPENING_STOCK_COLD_ROOM")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//Header Key data

    public ArrayList<HeaderGetterSetter> getHeaderStock(String storeId) {
        Log.d("FetchingOpening Stock data--------------->Start<------------",
                "------------------");
        ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT KEY_ID FROM openingHeader_data WHERE STORE_CD= '"
                                    + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    HeaderGetterSetter sb = new HeaderGetterSetter();

                    sb.setKeyId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("KEY_ID")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//get opening stock data from database

    public ArrayList<StockNewGetterSetter> getOpeningStockDataFromDatabase(String store_cd, String brand_cd) {
        Log.d("FetchingOpening Stock data--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT SKU_CD,SKU,BRAND_CD,BRAND,OPENING_STOCK_PRESENT from STOCK_DATA_PRESENT  WHERE STORE_CD= '" + store_cd + "' AND BRAND_CD = '" + brand_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setSkuQuantity(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("OPENING_STOCK_PRESENT")));

                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //check if table is empty
    public boolean isOpeningDataFilled(String store_cd) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT OPENING_STOCK_PRESENT FROM STOCK_DATA_PRESENT WHERE STORE_CD= '" + store_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("OPENING_STOCK_PRESENT")).equals("-1")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


//get Opening and Midday stock data for comparison from database

    public ArrayList<StockNewGetterSetter> getOpeningNMiddayStockDataFromDatabase(String storeId, String brand_cd) {
        Log.d("FetchingOpening Stock data--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.SKU_CD, SD.SKU, SD.OPENING_TOTAL_STOCK, SD.MIDDAY_TOTAL_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "' AND CD.BRAND_CD = '" + brand_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setOpenning_total_stock(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("OPENING_TOTAL_STOCK")));

                    sb.setTotal_mid_stock_received(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MIDDAY_TOTAL_STOCK")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//get Closing and Midday stock data for comparison from database

    public ArrayList<StockGetterSetter> getClosingNMiddayStockDataFromDatabase(String storeId, String brand_cd) {
        Log.d("FetchingClosingnMid Stock data--------------->Start<------------",
                "------------------");
        ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.SKU_CD, SD.SKU, SD.COLD_ROOM, SD.MCCAIN_DF, SD.STORE_DF, SD.MIDDAY_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "' AND CD.BRAND_CD = '" + brand_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockGetterSetter sb = new StockGetterSetter();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

						/*	sb.setCategory_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE")));*/

							/*sb.setActual_listed(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("ACTUAL_LISTED")));
							sb.setAs_per_meccain("N");*/
                    sb.setClos_stock_cold_room(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COLD_ROOM")));
                    sb.setClos_stock_meccaindf(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MCCAIN_DF")));
                    sb.setClos_stock_storedf(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_DF")));
                    sb.setMidday_stock(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MIDDAY_STOCK")));
							/*sb.setTotal_facing_storedf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("TOTAL_FACING_STORE_DF")));
							sb.setTotalfacing_mccaindf(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("TOTAL_FACING_MCCAIN_DF")));
							sb.setMaterial_wellness(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("MATERIAL_WELLNESS")));*/


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingClosing midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//get Closing stock data from database

    public ArrayList<StockNewGetterSetter> getClosingStockDataFromDatabase(String storeId, String brand_cd) {
        Log.d("FetchingOpening Stock data--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.SKU_CD, SD.SKU, SD.CLOSING_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "' AND CD.BRAND_CD = '" + brand_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setClosing_stock(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CLOSING_STOCK")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//get Closing stock data from database

    public ArrayList<ColdroomClosingGetterSetter> getClosingColdDataFromDatabase(String storeId, String sku_cd) {
        Log.d("FetchingOpening Stock data--------------->Start<------------",
                "------------------");

        //temp code
        //storeId=451+"";

        ArrayList<ColdroomClosingGetterSetter> list = new ArrayList<ColdroomClosingGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT CLOSING_STOCK_COLD FROM PREVIOUS_COLD_STOCK WHERE STORE_CD= '"
                                    + storeId + "' AND SKU_CD = '" + sku_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ColdroomClosingGetterSetter sb = new ColdroomClosingGetterSetter();


                    sb.setClosing_cold(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CLOSING_STOCK_COLD")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //check if table is empty
    public boolean isClosingDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT CLOSING_STOCK FROM STOCK_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLOSING_STOCK")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;


    }


//get Midday stock data from database

    public ArrayList<StockNewGetterSetter> getMiddayStockDataFromDatabase(String storeId, String brand_cd) {
        Log.d("FetchingOpening Stock data--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.SKU_CD, SD.SKU, SD.MIDDAY_TOTAL_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "' AND CD.BRAND_CD = '" + brand_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

						/*	sb.setCategory_type(dbcursor.getString(dbcursor
									.getColumnIndexOrThrow("CATEGORY_TYPE")));*/

                    sb.setTotal_mid_stock_received(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MIDDAY_TOTAL_STOCK")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //check if table is empty
    public boolean isMiddayDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT MIDDAY_TOTAL_STOCK FROM STOCK_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIDDAY_TOTAL_STOCK")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }

    ///check if table is empty
    public boolean isShareofshelfFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT MIDDAY_TOTAL_STOCK FROM STOCK_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIDDAY_TOTAL_STOCK")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


//Middaystock stock

    public ArrayList<StockGetterSetter> getMiddayStock(String storeId) {
        Log.d("FetchingOpening Stock data--------------->Start<------------",
                "------------------");
        ArrayList<StockGetterSetter> list = new ArrayList<StockGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT SD.MIDDAY_STOCK FROM openingHeader_data CD INNER JOIN STOCK_DATA SD ON CD.KEY_ID=SD.Common_Id WHERE CD.STORE_CD= '"
                                    + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockGetterSetter sb = new StockGetterSetter();

                    sb.setMidday_stock(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MIDDAY_STOCK")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingOPening midday---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//deletecross Stock Data

    public void deleteOpeningStockData(String storeid, String STATE_CD, String STORE_TYPE_CD) {

        try {

            db.delete(CommonString1.TABLE_STOCK_DATA, CommonString1.KEY_STATE_CD + "='" + STATE_CD + "'", null);

            db.delete(CommonString1.TABLE_INSERT_OPENINGHEADER_DATA, CommonString1.KEY_STATE_CD + "='" + STATE_CD + "'", null);

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }

//deletecross Stock Data closing

    public void deleteClosingStockData(String storeid) {

        try {

            db.delete(CommonString1.TABLE_CLOSING_STOCK_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

            db.delete(CommonString1.TABLE_INSERT_OPENINGHEADER_CLOSING_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }


//deletecross Stock Data Midday

    public void deleteMiddayStockData(String storeid) {

        try {

            db.delete(CommonString1.TABLE_MIDDAY_STOCK_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

            db.delete(CommonString1.TABLE_INSERT_HEADER_MIDDAY_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }


//deletecross Stock Data Midday

    public void updateMiddayStockData(String storeid,
                                      HashMap<StockNewGetterSetter, List<MiddayStockInsertData>> data, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {


            db.beginTransaction();
            for (int i = 1; i <= save_listDataHeader.size(); i++) {

                values.put("STORE_CD", storeid);

                values.put("BRAND_CD", save_listDataHeader.get(i)
                        .getBrand_cd());
                values.put("BRAND", save_listDataHeader
                        .get(i).getBrand());

                long l = db.insert(CommonString1.TABLE_INSERT_HEADER_MIDDAY_DATA,
                        null, values);

                //db.update(CommonString1.TABLE_MIDDAY_STOCK_DATA, values1, "KEY_ID" + "='" + i + "'", null);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                    values1.put("Common_Id", (int) l);
                    values1.put("STORE_CD", storeid);
                    values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j)
                            .getSku_cd()));
                    values1.put("MIDDAY_STOCK", data.get(save_listDataHeader.get(i)).get(j).getMidday_stock());

                    db.insert(CommonString1.TABLE_MIDDAY_STOCK_DATA, null, values1);

                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Posm Master Data ",
                    ex.toString());
        }

    }


//deletecross Food Store data

    public void deleteFoodStoreData(String storeid) {

        try {

            db.delete(CommonString1.TABLE_FOOD_STORE_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

            db.delete(CommonString1.TABLE_INSERT_HEADER_FOOD_STORE_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }


//deletecross Promotion Data

    public void deletePromotionData(String storeid) {

        try {

            db.delete(CommonString1.TABLE_PROMOTION_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

            db.delete(CommonString1.TABLE_INSERT_PROMOTION_HEADER_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }


//deletecross Asset Data

    public void deleteAssetData(String storeid) {

        try {

            db.delete(CommonString1.TABLE_ASSET_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

            db.delete(CommonString1.TABLE_INSERT_ASSET_HEADER_DATA,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }


//Insert Closing Stock Data

    public void insertClosingStockData(ClosingStockInsertDataGetterSetter data, String sku_cd) {

        db.delete(CommonString1.TABLE_CLOSING_STOCK_DATA, "SKU_CD" + "='" + sku_cd + "'", null);
        ContentValues values = new ContentValues();

        try {


            values.put("SKU_CD", Integer.parseInt(data.getSku_cd()));
            values.put("COLD_ROOM", data.getClos_stock_cold_room());
            values.put("MCCAIN_DF", data.getMeccaindf());
            values.put("STORE_DF", data.getStoredf());

            db.insert(CommonString1.TABLE_CLOSING_STOCK_DATA, null, values);


        } catch (Exception ex) {
            Log.d("Database Exception while Insert CLOSING STOCK Data ",
                    ex.toString());
        }

    }


//Insert Closing Stock Data

    public void insertMiddayStockData(String midday_stock, String sku_cd) {

        db.delete(CommonString1.TABLE_MIDDAY_STOCK_DATA, "SKU_CD" + "='" + sku_cd + "'", null);
        ContentValues values = new ContentValues();

        try {


            values.put("SKU_CD", Integer.parseInt(sku_cd));
            values.put("MIDDAY_STOCK", midday_stock);


            db.insert(CommonString1.TABLE_MIDDAY_STOCK_DATA, null, values);


        } catch (Exception ex) {
            Log.d("Database Exception while Insert OPENING STOCK Data ",
                    ex.toString());
        }

    }


//get if category type is food

    public boolean isCategoryTypeFood(String brand_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<MiddayStockInsertData> list = new ArrayList<MiddayStockInsertData>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT SD.SKU_CD, SD.SKU FROM MAPPING_AVAILABILITY CD INNER JOIN SKU_MASTER SD ON CD.SKU_CD = SD.SKU_CD WHERE SD.BRAND_CD ='" + brand_cd + "'AND SD.CATEGORY_TYPE ='" + "Food" + "'"
                            , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MiddayStockInsertData sb = new MiddayStockInsertData();


                    sb.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));

                    sb.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setMidday_stock("15");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();


            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return false;
        }

        Log.d("Fetching opening stock---------------------->Stop<-----------",
                "-------------------");
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


//get DeepFreezerType Data

    public ArrayList<COMPETITORGetterSetter> getCOMPETITORData() {

        Log.d("FetchingCategoryType--------------->Start<------------",
                "------------------");
        ArrayList<COMPETITORGetterSetter> list = new ArrayList<COMPETITORGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT DISTINCT * from COMPANY_MASTER WHERE ISCOMPETITOR ==1", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    COMPETITORGetterSetter df = new COMPETITORGetterSetter();

                    df.setCOMPANY_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY_CD")));
                    df.setCOMPANY(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY")));

                    df.setCamara("");
                    df.setCheckbox("");

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Category!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingCategory data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

//category for competition

    public ArrayList<FacingCompetitorGetterSetter> getCategoryCompetionData() {

        Log.d("FetchingCategoryType--------------->Start<------------",
                "------------------");
        ArrayList<FacingCompetitorGetterSetter> list = new ArrayList<FacingCompetitorGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_CD, CATEGORY from CATEGORY_MASTER WHERE CATEGORY_CD IN(SELECT DISTINCT CATEGORY_CD FROM BRAND_MASTER WHERE COMPANY_CD <>" + "'1'" + ")"
                    , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FacingCompetitorGetterSetter df = new FacingCompetitorGetterSetter();


                    df.setCategory_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));
                    df.setCategory(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY")));
                    df.setBrand("");
                    df.setBrand_cd("");
                    df.setMccaindf("");
                    //df.setStoredf("");

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Category!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingCategory data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //category for competition faceup

    public ArrayList<COMPETITORGetterSetter> getCategoryCompetionFaceupData(String store_cd) {

        Log.d("FetchingCategoryType--------------->Start<------------",
                "------------------");
        ArrayList<COMPETITORGetterSetter> list = new ArrayList<COMPETITORGetterSetter>();
        Cursor dbcursor = null;

        try {
			/*dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_CD, CATEGORY from CATEGORY_MASTER WHERE CATEGORY_CD IN(SELECT DISTINCT CATEGORY_CD FROM BRAND_MASTER WHERE COMPANY_CD <>"+"'1'"+")"
					, null);*/

            dbcursor = db.rawQuery("SELECT CATEGORY_CD, CATEGORY FROM CATEGORY_MASTER WHERE CATEGORY_CD IN(SELECT DISTINCT CATEGORY_CD FROM SKU_MASTER WHERE SKU_CD IN( SELECT  SKU_CD FROM MAPPING_AVAILABILITY  WHERE STORE_CD = '" + store_cd + "'))"
                    , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    COMPETITORGetterSetter df = new COMPETITORGetterSetter();


					/*df.setCategory_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY_CD")));
					df.setCategory(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY")));
					df.setBrand("");
					df.setBrand_cd("");
					df.setMccaindf("");
					//df.setStoredf("");*/

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Category!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingCategory data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

//get Brand Data

    public ArrayList<StockNewGetterSetter> getBrandData(String category_id) {

        Log.d("FetchingBrand--------------->Start<------------",
                "------------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT DISTINCT BRAND_CD, BRAND from BRAND_MASTER  WHERE CATEGORY_CD" + " = '" + category_id +
                            "' "
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter df = new StockNewGetterSetter();


                    df.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    df.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Brand!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Brand data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //get Brand Data for competion faceup

    public ArrayList<FacingCompetitorGetterSetter> getBrandCompetitionData(String category_id) {

        Log.d("FetchingBrand-->Start<-",
                "------------------");
        ArrayList<FacingCompetitorGetterSetter> list = new ArrayList<FacingCompetitorGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT DISTINCT BRAND_CD, BRAND from BRAND_MASTER  WHERE CATEGORY_CD" + " = '" + category_id +
                            "' AND COMPANY_CD <> " + "'1'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FacingCompetitorGetterSetter df = new FacingCompetitorGetterSetter();


                    df.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    df.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));
                    df.setMccaindf("");

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception fetch Brand",
                    e.toString());
            return list;
        }

        Log.d("Brand data-->Stop<-",
                "-------------------");
        return list;

    }


//get Company Data

    public ArrayList<CompanyGetterSetter> getCompanyData() {

        Log.d("FetchingCategoryType--------------->Start<------------",
                "------------------");
        ArrayList<CompanyGetterSetter> list = new ArrayList<CompanyGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT -1 as COMPANY_CD,'Select' as COMPANY union SELECT DISTINCT COMPANY_CD,COMPANY from COMPANY_MASTER"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CompanyGetterSetter df = new CompanyGetterSetter();


                    df.setCompany_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMPANY_CD")));
                    df.setCompany(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMPANY")));

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Company!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingCategory data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public int CheckMid(String currdate, String storeid) {

        Cursor dbcursor = null;
        int mid = 0;
        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString1.TABLE_COVERAGE_DATA + "  WHERE "
                    + CommonString1.KEY_VISIT_DATE + " = '" + currdate
                    + "' AND " + CommonString1.KEY_STORE_ID + " ='" + storeid
                    + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                mid = dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_ID));
                dbcursor.close();
            }
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!", e.toString());
        }

        return mid;
    }


    public String getVisiteDateFromCoverage(String storeid) {
        Cursor dbcursor = null;
        String visite_date = "";
        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString1.TABLE_COVERAGE_DATA + "  WHERE "
                    + CommonString1.KEY_STORE_ID + " ='" + storeid
                    + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                visite_date = dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_VISIT_DATE));
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!", e.toString());
        }
        return visite_date;
    }


    public long InsertCoverageData(CoverageBean data) {

        //db.delete(CommonString1.TABLE_COVERAGE_DATA, "STORE_ID" + "='" + data.getStoreId() + "'", null);

        ContentValues values = new ContentValues();

        try {

            values.put(CommonString1.KEY_STORE_ID, data.getStoreId());
            values.put(CommonString1.KEY_USER_ID, data.getUserId());
            values.put(CommonString1.KEY_IN_TIME, data.getInTime());
            values.put(CommonString1.KEY_OUT_TIME, data.getOutTime());
            values.put(CommonString1.KEY_VISIT_DATE, data.getVisitDate());
            values.put(CommonString1.KEY_CHANNEL_CD, data.getChannel_cd());
            values.put(CommonString1.KEY_LATITUDE, data.getLatitude());
            values.put(CommonString1.KEY_LONGITUDE, data.getLongitude());
            values.put(CommonString1.KEY_REASON_ID, data.getReasonid());
            values.put(CommonString1.KEY_REASON, data.getReason());
            values.put(CommonString1.KEY_SUB_REASON_ID, data.getSub_reasonId());
            values.put(CommonString1.KEY_INFORM_TO, data.getInformto());
            values.put(CommonString1.KEY_COVERAGE_STATUS, data.getStatus());
            values.put(CommonString1.KEY_IMAGE, data.getImage());
            values.put(CommonString1.KEY_COVERAGE_REMARK, data.getRemark());
            values.put(CommonString1.KEY_GEO_TAG, data.getGEO_TAG());

            //values.put(CommonString1.KEY_other, data.getOtherreson());

            return db.insert(CommonString1.TABLE_COVERAGE_DATA, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return 0;
    }


    // getCoverageData
    public ArrayList<CoverageBean> getCoverageData(String visitdate) {

        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            if (visitdate == "FromPrevStore") {
                dbcursor = db.rawQuery("SELECT  * from '" + CommonString1.TABLE_PREVIOUS_COVERAGE_DATA + "'", null);
            } else {
                dbcursor = db.rawQuery("SELECT  * from " + CommonString1.TABLE_COVERAGE_DATA + " where " + CommonString1.KEY_VISIT_DATE + "='" + visitdate + "'", null);
            }

            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();

                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_STORE_ID)));
                    sb.setUserId((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_USER_ID))));
                    sb.setInTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_OUT_TIME)))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_VISIT_DATE))))));
                    sb.setChannel_cd((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_CHANNEL_CD))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_LONGITUDE)))));
                    sb.setStatus((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_COVERAGE_STATUS))))));
                    sb.setImage((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_IMAGE))))));
                    sb.setReason((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_REASON))))));
                    sb.setReasonid((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_REASON_ID))))));
                    sb.setSub_reasonId((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_SUB_REASON_ID))))));
                    sb.setInformto((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_INFORM_TO))))));
                    sb.setMID(Integer.parseInt(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_ID))))));
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_COVERAGE_REMARK)) == null) {
                        sb.setRemark("");
                    } else {
                        sb.setRemark((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_COVERAGE_REMARK))))));
                    }

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!", e.toString());
        }
        return list;
    }

    // getCoverageData
    public ArrayList<CoverageBean> getCoverageSpecificData(String store_id) {

        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT  * from "
                            + CommonString1.TABLE_COVERAGE_DATA + " where "
                            + CommonString1.KEY_STORE_ID + "='" + store_id + "'",
                    null);


            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();

                    sb.setUserId((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_USER_ID))));
                    sb.setInTime(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_OUT_TIME)))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_VISIT_DATE))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_LONGITUDE)))));
                    sb.setStatus((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_COVERAGE_STATUS))))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }

        return list;

    }

    //check if table is empty
    public boolean isCoverageDataFilled(String visit_date) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM COVERAGE_DATA " + "where "
                                    + CommonString1.KEY_VISIT_DATE + "<>'" + visit_date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }

    //check if table is empty
    public boolean isCoverageExist(String storecd, String visit_date) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM COVERAGE_DATA " + "where " + CommonString1.KEY_STORE_ID + " = '" + storecd + "' AND " + CommonString1.KEY_VISIT_DATE + " = '" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }
            }
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!", e.toString());
            return filled;
        }
        return filled;
    }


    public void updateCoverageStatus(int mid, String status) {

        try {
            ContentValues values = new ContentValues();
            values.put(CommonString1.KEY_COVERAGE_STATUS, status);

            db.update(CommonString1.TABLE_COVERAGE_DATA, values,
                    CommonString1.KEY_ID + "=" + mid, null);
        } catch (Exception e) {

        }
    }


    public long updateCoverageStoreOutTime(String StoreId, String VisitDate, String status) {
        long id = 0;
        try {
            ContentValues values = new ContentValues();
            //values.put(CommonString1.KEY_OUT_TIME, outtime);
            values.put(CommonString1.KEY_COVERAGE_STATUS, status);
            id = db.update(CommonString1.TABLE_COVERAGE_DATA, values, CommonString1.KEY_STORE_ID + "='" + StoreId + "' AND " + CommonString1.KEY_VISIT_DATE + "='" + VisitDate + "'", null);
        } catch (Exception e) {
            id = 0;
        }
        return id;
    }


    public void updateStoreStatusOnLeave(String storeid, String visitdate,
                                         String status) {

        try {
            ContentValues values = new ContentValues();
            values.put("UPLOAD_STATUS", status);

            db.update("JOURNEY_PLAN", values,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "' AND "
                            + CommonString1.KEY_VISIT_DATE + "='" + visitdate
                            + "'", null);
        } catch (Exception e) {

        }
    }


    public long updateStoreStatusOnCheckout(String storeid, String visitdate, String status) {
        long id = 0;

        try {
            ContentValues values = new ContentValues();
            values.put(CommonString1.KEY_CHECKOUT_STATUS, status);

            id = db.update("JOURNEY_PLAN", values,
                    CommonString1.KEY_STORE_CD + "='" + storeid + "' AND "
                            + CommonString1.KEY_VISIT_DATE + "='" + visitdate
                            + "'", null);
        } catch (Exception e) {
            id = 0;
        }
        return id;
    }


//Insert Calls Data

    public void insertCallsData(String store_cd, String total_calls, String productive_calls) {

        db.delete("CALLS_DATA", "STORE_CD" + "='" + store_cd + "'", null);
        ContentValues values = new ContentValues();

        try {
            values.put("STORE_CD", store_cd);
            values.put("TOTAL_CALLS", total_calls);
            values.put("PRODUCTIVE_CALLS", productive_calls);

            db.insert(CommonString1.TABLE_CALLS_DATA, null, values);


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Calls Data ",
                    ex.toString());
        }

    }


//get Calls Data

    public ArrayList<CallsGetterSetter> getCallsData(String store_cd) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<CallsGetterSetter> list = new ArrayList<CallsGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from CALLS_DATA where STORE_CD = '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CallsGetterSetter fc = new CallsGetterSetter();


                    fc.setStore_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_CD")));
                    fc.setTotal_calls(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("TOTAL_CALLS")));
                    fc.setProductive_calls(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PRODUCTIVE_CALLS")));
                    fc.setKey_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("Key_Id")));

                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Calls data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //check if table is empty
    public boolean isCallsDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM CALLS_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    public void deleteStockRow(int id, String store_cd) {

        try {
            db.delete(CommonString1.TABLE_CALLS_DATA, CommonString1.KEY_STORE_CD + "='" + store_cd + "' AND "
                            + "Key_Id" + "='" + id + "'"
                    , null);
        } catch (Exception e) {

        }

    }


    //Insert Calls Data

	/*public void insertCompetitionPOIData(String store_cd, StoreSignAgeGetterSetter poiGetterSetter) {

		//db.delete("CALLS_DATA", "STORE_CD" + "='" + store_cd + "'", null);
		ContentValues values = new ContentValues();

		try {

			values.put("STORE_CD", store_cd);
			values.put("CATEGORY_CD", poiGetterSetter.getCategory_cd());
			values.put("CATEGORY",poiGetterSetter.getCategory());
			values.put("ASSET_CD", poiGetterSetter.getAsset_cd());
			values.put("ASSET", poiGetterSetter.getAsset());
			values.put("BRAND", poiGetterSetter.getBrand());
			values.put("BRAND_CD",poiGetterSetter.getBrand_cd());
			values.put("REMARK", poiGetterSetter.getRemark());

			db.insert(CommonString1.TABLE_COMPETITION_POI, null, values);


		} catch (Exception ex) {
			Log.d("Database Exception while Insert Calls Data ",
					ex.toString());
		}

	}
*/

    //get Calls Data

    public ArrayList<StoreSignAgeGetterSetter> getCompetitionPOIData(String store_cd) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<StoreSignAgeGetterSetter> list = new ArrayList<StoreSignAgeGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from COMPETITION_POI WHERE STORE_CD= '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreSignAgeGetterSetter fc = new StoreSignAgeGetterSetter();


                    fc.setId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("Key_Id")));
				/*	fc.setCategory_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY_CD")));
					fc.setCategory(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("CATEGORY")));
					fc.setAsset_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET_CD")));
					fc.setAsset(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ASSET")));
					fc.setRemark(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("REMARK")));
					fc.setBrand_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND_CD")));
					fc.setBrand(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("BRAND")));*/

                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void deleteCompetitionPOIRow(int id) {

        try {
            db.delete(CommonString1.TABLE_COMPETITION_POI, "Key_Id " + "='" + id + "'"
                    , null);
        } catch (Exception e) {

        }

    }


    //Insert Calls Data

    public void insertCompetitionPromotionData(CompetitionPromotionGetterSetter poiGetterSetter, String store_cd) {

        //db.delete("CALLS_DATA", "STORE_CD" + "='" + store_cd + "'", null);
        ContentValues values = new ContentValues();

        try {

            values.put("STORE_CD", store_cd);
            values.put("CATEGORY_CD", poiGetterSetter.getCategory_cd());
            values.put("CATEGORY", poiGetterSetter.getCategory());
            values.put("BRAND_CD", poiGetterSetter.getBrand_cd());
            values.put("BRAND", poiGetterSetter.getBrand());
            values.put("REMARK", poiGetterSetter.getRemark());
            values.put("PROMOTION", poiGetterSetter.getPromotion());

            db.insert(CommonString1.TABLE_COMPETITION_PROMOTION, null, values);


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Calls Data ",
                    ex.toString());
        }

    }

    //get Competition Promotion Data

    public ArrayList<windowsChildData> getwindowsData(String store_cd) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<windowsChildData> list = new ArrayList<windowsChildData>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from WINDOWS_DATA WHERE STORE_CD= '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    windowsChildData fc = new windowsChildData();


					/*fc.setBacksheet(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("Backsheet")));
					fc.setShelfStrip(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("ShelfStrip")));*/


                    fc.setImage(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE")));

                    fc.setRemarks(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON")));
                    fc.setSTATUS_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STATUS_CD")));


                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void deleteCompetitionPromotionRow(int id) {

        try {
            db.delete(CommonString1.TABLE_COMPETITION_PROMOTION, "Key_Id " + "='" + id + "'"
                    , null);
        } catch (Exception e) {

        }

    }


//Insert Calls Data

    public void insertStoreSignAgeData(StoreSignAgeGetterSetter SFTGetterSetter, String store_cd) {

        db.delete("STORE_SIGNAGE", "STORE_CD" + "='" + store_cd + "'", null);
        ContentValues values = new ContentValues();

        try {

            values.put("STORE_CD", store_cd);
            values.put("SIGNEXIST", SFTGetterSetter.getExistSpinner());
            values.put("SIGNEXISTCD", SFTGetterSetter.getExistSpinner_CD());

            values.put("WORKING", SFTGetterSetter.getWorkingsppiner());
            values.put("WORKINGCD", SFTGetterSetter.getWorkingsppiner_CD());
            values.put("IMAGE", SFTGetterSetter.getImage());

            db.insert(CommonString1.TABLE_STORE_SIGNAGE, null, values);


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Calls Data ",
                    ex.toString());
        }

    }


    //get Calls Data

    public ArrayList<StoreSignAgeGetterSetter> getSFTData(String store_cd) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<StoreSignAgeGetterSetter> list = new ArrayList<StoreSignAgeGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from STORE_SIGNAGE WHERE STORE_CD= '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreSignAgeGetterSetter fc = new StoreSignAgeGetterSetter();

                    fc.setId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("Key_Id")));
                    fc.setExistSpinner(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SIGNEXIST")));
                    fc.setExistSpinner_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SIGNEXISTCD")));

                    fc.setWorkingsppiner(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WORKING")));
                    fc.setWorkingsppiner_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WORKINGCD")));


                    fc.setImage(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE")));

                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertCheckListData(String store_cd, String user_id, String window_cd,
                                    ArrayList<ChecklistGetterSetter> answered_data, Long commonid) {

//        db.delete(CommonString1.TABLE_WINDOWS_DATA, null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < answered_data.size(); i++) {

                values.put("STORE_CD", store_cd);
                values.put("COMMON_ID", commonid);
                values.put("USER_ID", user_id);
                values.put("WINDOW_CD", window_cd);
                values.put("CHECKLIST_CD", answered_data.get(i).getCHECKLIST_CD());
                values.put("ANSWER_CD", answered_data.get(i).getANSWER_CD());

                db.insert(CommonString1.TABLE_INSERT_CHECKLIST_DATA, null, values);
            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }

    }


    public ArrayList<WindowSKUEntryGetterSetter> getwindowdat(String store_cd) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<WindowSKUEntryGetterSetter> list = new ArrayList<WindowSKUEntryGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from WINDOWS_DATA WHERE STORE_CD= '" + store_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    WindowSKUEntryGetterSetter fc = new WindowSKUEntryGetterSetter();

                    fc.setKeyID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("KEY_ID")));
                    fc.setWindow_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));
                    fc.setWindow_image(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_IMAGE")));

                    fc.setVisit_date(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DATE")));
                    fc.setUsername(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("USER_ID")));

                    fc.setExistOrnot(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("EXISTORNOT")));

                    fc.setReason_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON_ID")));

                    fc.setStore_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_CD")));

                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public boolean getwindowDataForEachWindow(String store_cd, String window_cd) {

        boolean filled = false;
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from WINDOWS_DATA WHERE STORE_CD= '" + store_cd + "' AND WINDOW_CD = '" + window_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }

    public ArrayList<WindowSKUEntryGetterSetter> getwindowDATA1(String store_cd) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<WindowSKUEntryGetterSetter> list = new ArrayList<WindowSKUEntryGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from sku_entry_data WHERE STORE_CD= '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    WindowSKUEntryGetterSetter fc = new WindowSKUEntryGetterSetter();


                    fc.setCategory_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_ID")));
                    fc.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_ID")));

                    fc.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));
                    fc.setWindow_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));


                    fc.setStocksonetosix(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("stocksonetosix")));


                    fc.setSeventotwelve(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("stockseventotwelve")));

                    fc.setStockabovethirteen(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("stockabovethirteen")));

                    fc.setStock(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STOCK")));


                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<ChecklistGetterSetter> getchecklist(String keyid) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<ChecklistGetterSetter> list = new ArrayList<ChecklistGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * from CheckList_DATA WHERE COMMON_ID= '" + keyid + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistGetterSetter fc = new ChecklistGetterSetter();


                    fc.setWINDOW_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));
                    fc.setCHECKLIST_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST_CD")));
                    fc.setCOMMON_ID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMMON_ID")));
                    fc.setANSWER_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER_CD")));

                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }











	/*public void deletePOIRow(int id,String store_cd){

		try{
			db.delete(CommonString1.TABLE_STORE_FIRST_TIME, CommonString1.KEY_STORE_CD + "='" + store_cd + "' AND "
					+ "Key_Id " + "='" + id + "'"
					, null);
		}
		catch(Exception e){

		}

	}*/


    /// get store Status
    public JourneyPlanGetterSetter getStoreStatus(String id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");

        JourneyPlanGetterSetter sb = new JourneyPlanGetterSetter();

        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT  * from  JOURNEY_PLAN"
                    + "  WHERE STORE_CD = '"
                    + id + "'", null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();

                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; i++) {

                    sb.setStore_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_STORE_CD)));

                    sb.setCheckOutStatus((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKOUT_STATUS"))));

                    sb.setUploadStatus(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("UPLOAD_STATUS")));

                    dbcursor.moveToNext();

                }

                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return sb;

    }


//Checklist data

    public ArrayList<ChecklistInsertDataGetterSetter> getCheckListData(String asset_cd) {
        Log.d("Fetching checklist data--------------->Start<------------",
                "------------------");
        ArrayList<ChecklistInsertDataGetterSetter> list = new ArrayList<ChecklistInsertDataGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT DISTINCT AC.CHECKLIST, AC.CHECKLIST_ID,  AC.CHECKLIST_TYPE FROM MAPPING_ASSET_CHECKLIST MA INNER JOIN ASSET_CHECKLIST AC ON MA.CHECKLIST_ID = AC.CHECKLIST_ID WHERE MA.ASSET_CD= '" + asset_cd + "'"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistInsertDataGetterSetter sb = new ChecklistInsertDataGetterSetter();


                    sb.setChecklist(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST")));

                    sb.setChecklist_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST_ID")));

                    sb.setChecklist_type(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST_TYPE")));

                    sb.setChecklist_text("");


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching checklist data!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching checklist data---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //Get Checklist Insert data

    public ArrayList<ChecklistInsertDataGetterSetter> getCheckListInsertData(String asset_cd, String store_cd, String visitdate) {
        Log.d("Fetching checklist data--------------->Start<------------",
                "------------------");
        ArrayList<ChecklistInsertDataGetterSetter> list = new ArrayList<ChecklistInsertDataGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM ASSET_CHECKLIST_INSERT WHERE ASSET_CD = '" + asset_cd + "' AND STORE_CD = '" + store_cd + "' AND VISIT_DATE = '" + visitdate + "'"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistInsertDataGetterSetter sb = new ChecklistInsertDataGetterSetter();


                    sb.setChecklist(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.CHECK_LIST)));

                    sb.setChecklist_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.CHECK_LIST_ID)));

                    sb.setChecklist_type(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.CHECK_LIST_TYPE)));

                    sb.setChecklist_text(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.CHECK_LIST_TEXT)));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching checklist data!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching checklist data---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //dlete specific Checklist Insert data

    public void deleteCheckListInsertData(String asset_cd, String store_cd, String visitdate) {
        Log.d("Fetching checklist data--------------->Start<------------",
                "------------------");

        db.delete("ASSET_CHECKLIST_INSERT", "ASSET_CD = '" + asset_cd + "' AND STORE_CD = '" + store_cd + "' AND VISIT_DATE = '" + visitdate + "'", null);

    }


    //Get All Checklist Insert data

    public ArrayList<ChecklistInsertDataGetterSetter> getCheckListAllInsertData(String store_cd, String visitdate) {
        Log.d("Fetching checklist data--------------->Start<------------",
                "------------------");
        ArrayList<ChecklistInsertDataGetterSetter> list = new ArrayList<ChecklistInsertDataGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM ASSET_CHECKLIST_INSERT WHERE STORE_CD = '" + store_cd + "' AND VISIT_DATE = '" + visitdate + "'"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistInsertDataGetterSetter sb = new ChecklistInsertDataGetterSetter();


                    sb.setChecklist(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.CHECK_LIST)));

                    sb.setChecklist_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.CHECK_LIST_ID)));

                    sb.setChecklist_type(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.CHECK_LIST_TYPE)));

                    if (dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.CHECK_LIST_TYPE)).equalsIgnoreCase("TOGGLE")) {
                        if (dbcursor.getString(dbcursor
                                .getColumnIndexOrThrow(CommonString1.CHECK_LIST_TEXT)).equals("")) {
                            sb.setChecklist_text("NO");
                        } else {
                            sb.setChecklist_text(dbcursor.getString(dbcursor
                                    .getColumnIndexOrThrow(CommonString1.CHECK_LIST_TEXT)));
                        }
                    } else {
                        sb.setChecklist_text(dbcursor.getString(dbcursor
                                .getColumnIndexOrThrow(CommonString1.CHECK_LIST_TEXT)));
                    }


                    sb.setAsset_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.ASSET_CD)));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching All checklist data!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching All checklist data---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


//Get mapping Checklist data

    public ArrayList<MappingAssetChecklistGetterSetter> getMapingCheckListData() {
        Log.d("Fetching mapping checklist data--------------->Start<------------",
                "------------------");
        ArrayList<MappingAssetChecklistGetterSetter> list = new ArrayList<MappingAssetChecklistGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM MAPPING_ASSET_CHECKLIST"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingAssetChecklistGetterSetter sb = new MappingAssetChecklistGetterSetter();

                    sb.setCheck_list_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST_ID")));

                    sb.setAsset_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ASSET_CD")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching mapping checklist data!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching mapping checklist data---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    //Insert Asset Checklist Data

    public void insertAssetCheckListData(ArrayList<ChecklistInsertDataGetterSetter> data, String asset_cd, String visit_date, String store_cd) {

        db.delete(CommonString1.TABLE_ASSET_CHECKLIST_INSERT, "ASSET_CD" + "='" + asset_cd + "' AND STORE_CD" + "='" + store_cd + "'", null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.size(); i++) {

                values.put(CommonString1.CHECK_LIST_ID, data.get(i).getChecklist_id());
                values.put(CommonString1.KEY_STORE_CD, store_cd);
                values.put(CommonString1.ASSET_CD, asset_cd);
                values.put(CommonString1.KEY_VISIT_DATE, visit_date);
                values.put(CommonString1.CHECK_LIST_TEXT, data.get(i).getChecklist_text());
                values.put(CommonString1.CHECK_LIST_TYPE, data.get(i).getChecklist_type());
                values.put(CommonString1.CHECK_LIST, data.get(i).getChecklist());

                db.insert(CommonString1.TABLE_ASSET_CHECKLIST_INSERT, null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Asset checklist insert Data ",
                    ex.toString());
        }

    }

    //----------------------------------------------------


	/*// downloaded data
	public void insertMerchandiserList(MerchndiserGetterSetter data) {

		db.delete("MERCHANDISER_LIST", null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getmerc_id().size(); i++) {

				values.put("MERCHANDISER_ID", data.getmerc_id().get(i));
				values.put("MERCHANDISER", data.getmerc_name().get(i));
				values.put("MER_SCORE", 0);
				values.put("STATUS", "D");


				db.insert("MERCHANDISER_LIST", null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.toString());
		}

	}

	// getStoreData
	public ArrayList<MerchndiserGetterSetter> getMerchandiserList() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<MerchndiserGetterSetter> list = new ArrayList<MerchndiserGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from  MERCHANDISER_LIST",null,null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					MerchndiserGetterSetter sb = new MerchndiserGetterSetter();
					sb.setmerc_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("MERCHANDISER_ID")));
					sb.setmerc_name(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("MERCHANDISER")));
					sb.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STATUS")));


					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	//Store data

	public void insertStoreData(StoreListGetterSetter data) {

		db.delete("MERCHANDISER_STORE_LIST", null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getStoreid().size(); i++) {

				values.put("STORE_CD", data.getStoreid().get(i));
				values.put("STORE_NAME", data.getStorename().get(i));
				values.put("CITY", data.getCity().get(i));
				values.put("MERCHANDISER_ID", data.getMerchandiserId().get(i));

				values.put("CATEGORY", data.getCategory().get(i));
				values.put("STORETYPE", data.getStoreType().get(i));
				values.put("STORE_SCORE", data.getScore().get(i));
				values.put("STATUS", "D");


				db.insert("MERCHANDISER_STORE_LIST", null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.toString());
		}

	}

	public int getTableExistance(){
		Cursor dbcursor = null;
       int count =0;
		try {

			dbcursor = db.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name='MERCHANDISER_LIST'",null);

			if (dbcursor != null) {
			    count = dbcursor.getCount();


				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());

		}
		return count;
	}



	// insert nonworking data

	public void insertnonworkingData(NonWrkingMasterGetterSetter data) {

		db.delete("NON_WORKING_REASON", null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getNonWorkingReasonid().size(); i++) {

				values.put("REASON_ID", data.getNonWorkingReasonid().get(i));
				values.put("REASON", data.getNonWorkingReasonName().get(i));
				values.put("SUB_REASON_ID", data.getSubReason_id().get(i));
				values.put("SUB_REASON", data.getSub_reason().get(i));

				values.put("ENTRY_NOT_ALLOW", data.getEntryAlow().get(i));

				values.put("IMAGE_ALLOW", data.getImageAllow().get(i));
				db.insert("NON_WORKING_REASON", null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.toString());
		}

	}

	public ArrayList<ReasonModel> GetNonWorkingReason() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<ReasonModel> list = new ArrayList<ReasonModel>();
		Cursor dbcursor = null;

		try {

			dbcursor = db.rawQuery("SELECT  * from NON_WORKING_REASON"
					, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ReasonModel sb = new ReasonModel();
					sb.setReasonid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString1.REASON_ID)));
					sb.setReason(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString1.REASON)));
//					sb.setReasonValid(dbcursor.getString(dbcursor
//							.getColumnIndexOrThrow(CommonString1.REASON_VALID)));


					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// getting Non Working Sub Reason

	public ArrayList<ReasonModel> getSubReasonNonWorking(String reasonid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<ReasonModel> list = new ArrayList<ReasonModel>();
		Cursor dbcursor = null;

		try {

			dbcursor = db.rawQuery("SELECT  * from NON_WORKING_REASON "
					 + "  WHERE REASON_ID = '"
							+ reasonid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ReasonModel sb = new ReasonModel();
					sb.setReasonid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString1.REASON_ID)));
					sb.setSub_reasonId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString1.SUB_REASON_ID)));
					sb.setSub_reason(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString1.SUB_REASON)));


					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}
	//insert coverage data
	public long InsertCoverageData(CoverageBean data) {

		ContentValues values = new ContentValues();

		try {

			db.delete(CommonString1.TABLE_COVERAGE_DATA,
					CommonString1.KEY_STORE_ID + "='" + data.getStoreId()
					+ "' and "+ CommonString1.KEY_VISIT_DATE
					+ "='" +data.getVisitDate()+"'", null);

			values.put(CommonString1.KEY_STORE_ID, data.getStoreId());
			values.put(CommonString1.KEY_USER_ID, data.getUserId());
			values.put(CommonString1.KEY_IN_TIME, data.getInTime());
			values.put(CommonString1.KEY_OUT_TIME, data.getOutTime());
			values.put(CommonString1.KEY_VISIT_DATE, data.getVisitDate());
			values.put(CommonString1.KEY_LATITUDE, data.getLatitude());
			values.put(CommonString1.KEY_LONGITUDE, data.getLongitude());
			values.put(CommonString1.KEY_COVERAGE_STATUS, data.getCoveargestatus());
			values.put(CommonString1.KEY_IMAGE_SELFIE, data.getSelfieImage());
			values.put(CommonString1.KEY_MERCHANDISER_ID, data.getMerchanId());

			values.put(CommonString1.KEY_COVERAGE_REMARK, data.getCoverageReamark());
			values.put(CommonString1.KEY_DATA_SOURCE, data.getDataSource());
			values.put(CommonString1.KEY_DEV_REASON, data.getDavId());



	/////////////////////////////////////////////////////////////////////////////////////






			return db.insert(CommonString1.TABLE_COVERAGE_DATA, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Closes Data ",
					ex.toString());
		}
		return 0;
	}

	public ArrayList<StoreListGetterSetter> getStoreMerchandiserWise(String merId) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<StoreListGetterSetter> list = new ArrayList<StoreListGetterSetter>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT STORE_CD, STORE_NAME,STATUS FROM MERCHANDISER_STORE_LIST where MERCHANDISER_ID ='"+ merId +"'",null,null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					StoreListGetterSetter sb = new StoreListGetterSetter();
					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STORE_CD")));
					sb.setStorename(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STORE_NAME")));

					sb.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("STATUS")));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}


	public ArrayList<GATEbEAN> getMerchandiserListforAttendence() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<GATEbEAN> list = new ArrayList<GATEbEAN>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from  MERCHANDISER_LIST",null,null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					GATEbEAN sb = new GATEbEAN();
					sb.setEID(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("MERCHANDISER_ID")));
					sb.setNAME(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow("MERCHANDISER")));
					sb.setAttendanceid("0");
					sb.setAttendance_position(0);

					sb.setPresent("NO");

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.toString());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}


	public long insertGateMeetingCoverage(CoverageBean data) {

		ContentValues values = new ContentValues();

		try {

			db.delete(CommonString1.TABLE_GATE_COVERAGE_DATA,
					CommonString1.KEY_VISIT_DATE + "='" + data.getVisitDate()
					+"'", null);


			values.put(CommonString1.KEY_USER_ID, data.getUserId());
			values.put(CommonString1.KEY_IN_TIME, data.getInTime());
			values.put(CommonString1.KEY_OUT_TIME, data.getOutTime());
			values.put(CommonString1.KEY_VISIT_DATE, data.getVisitDate());
			values.put(CommonString1.KEY_LATITUDE, data.getLatitude());
			values.put(CommonString1.KEY_LONGITUDE, data.getLongitude());
			values.put(CommonString1.KEY_IMAGE, data.getImage());
			values.put(CommonString1.KEY_IMAGE1, data.getImage());
			values.put(CommonString1.KEY_IMAGE2, data.getImage());
			values.put(CommonString1.KEY_MEET, data.getReason());
			values.put(CommonString1.KEY_STATUS, data.getStatus());




			return db.insert(CommonString1.TABLE_GATE_COVERAGE_DATA, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Closes Data ",
					ex.toString());
		}
		return 0;
	}

	// GATE METING

		public void InsertGATe_merlist(ArrayList<GATEbEAN> gate, long l) {

			ContentValues values = new ContentValues();

			for (int i = 0; i < gate.size(); i++) {
				try {

					values.put(CommonString1.KEY_COMMMON_ID, l);
					values.put(CommonString1.Key_NAME, gate.get(i).getNAME());
					values.put(CommonString1.Key_EMPID, gate.get(i).getEID());
					values.put(CommonString1.KEY_PRESENT, gate.get(i).getPresent());


					db.insert(CommonString1.TABLE_GATE_MER_DETAIL, null, values);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
			}

		}

		public void Update_GATe_merlist(ArrayList<GATEbEAN> gate, long l) {

			ContentValues values = new ContentValues();

			for (int i = 0; i < gate.size(); i++) {
				try {

					values.put(CommonString1.KEY_PRESENT, gate.get(i).getPresent());


					db.update(CommonString1.TABLE_GATE_MER_DETAIL, values,
							CommonString1.Key_EMPID + "='" + gate.get(i).getEID()
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
			}

		}
		// Distrbutor List
		public void insertDistributorList(SupervisorGetterSetter data) {

			db.delete("DISTRIBUTOR_LIST", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getSupervisorId().size(); i++) {

					values.put("KEYACCOUNT_CD", data.getSupervisorId().get(i));
					values.put("KEYACCOUNT", data.getSupervisorname().get(i));

					db.insert("DISTRIBUTOR_LIST", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}


		public ArrayList<SupervisorGetterSetter> getDistributorList() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<SupervisorGetterSetter> list = new ArrayList<SupervisorGetterSetter>();
			Cursor dbcursor = null;

			try {

				dbcursor = db.rawQuery("SELECT  * from DISTRIBUTOR_LIST ", null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						SupervisorGetterSetter sb = new SupervisorGetterSetter();
						sb.setSupervisorname(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("KEYACCOUNT")));
						sb.setSupervisorId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("KEYACCOUNT_CD")));

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}


		//update storelist

		public void updateStoreList(String status,String storeid) {

			ContentValues values = new ContentValues();


				try {

					values.put(CommonString1.KEY_STATUS, status);


					db.update("MERCHANDISER_STORE_LIST", values,
							  "STORE_CD ='" + storeid
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}


		}

		// update merchandiser list

		public void updateMerchandiserList(String status,String merId) {

			ContentValues values = new ContentValues();


				try {

					values.put(CommonString1.KEY_STATUS, status);


					db.update("MERCHANDISER_LIST", values,
							  "MERCHANDISER_ID ='" + merId
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}
		}

		// update coverage


		public void updateCoverage(String status,String storeid) {

			ContentValues values = new ContentValues();


				try {

					values.put(CommonString1.KEY_COVERAGE_STATUS, status);


					db.update("COVERAGE_DATA", values,
							  "STORE_ID ='" + storeid
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}


		}

		// update attendence data
		public void updateAttendence(String status,String visitdate) {

			ContentValues values = new ContentValues();


				try {

					values.put(CommonString1.KEY_STATUS, status);


					db.update("ATTENDENCE_DATA", values,
							  "VISIT_DATE ='" + visitdate
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}


		}


		public void updateGateMeeting(String status,String visitdate) {

			ContentValues values = new ContentValues();


				try {

					values.put(CommonString1.KEY_STATUS, status);


					db.update("GATE_COVERAGE_DATA", values,
							  "VISIT_DATE ='" + visitdate
									+ "'", null);

				} catch (Exception ex) {
					Log.d("Database Exception while Insert Closes Data ",
							ex.toString());
				}


		}

		// insert Designation

		public void insertDesignation(DesignationGetterSetter data) {

			db.delete("DESIGNATION", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getDesigId().size(); i++) {

					values.put("DESIGNATION_CD", data.getDesigId().get(i));
					values.put("DESIGNATION", data.getDesig().get(i));

					db.insert("DESIGNATION", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}
		// Merchandiser Journey Plan
		public void insertMerchandiserJourney(JourneyPlanGetterSetter data) {

			db.delete("JOURNEY_PLAN_SUPERVISOR", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getMerchanId().size(); i++) {

					values.put("MERCHANDISER_CD", data.getMerchanId().get(i));
					values.put("VISIT_DATE", data.getVisitdate().get(i));

					db.insert("JOURNEY_PLAN_SUPERVISOR", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}

		public ArrayList<DesignationGetterSetter> getDesignation() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<DesignationGetterSetter> list = new ArrayList<DesignationGetterSetter>();
			Cursor dbcursor = null;

			try {

				dbcursor = db.rawQuery("SELECT  * from DESIGNATION", null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						DesignationGetterSetter sb = new DesignationGetterSetter();
						sb.setDesigId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DESIGNATION_CD")));
						sb.setDesig(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DESIGNATION")));

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}

		public ArrayList<MerchndiserGetterSetter> getjcpMerchandiserList() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<MerchndiserGetterSetter> list = new ArrayList<MerchndiserGetterSetter>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT  * from  MERCHANDISER_LIST INNER JOIN JOURNEY_PLAN_SUPERVISOR ON MERCHANDISER_LIST.MERCHANDISER_ID = JOURNEY_PLAN_SUPERVISOR.MERCHANDISER_CD",

						null,null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						MerchndiserGetterSetter sb = new MerchndiserGetterSetter();
						sb.setmerc_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MERCHANDISER_ID")));
						sb.setmerc_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MERCHANDISER")));
						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STATUS")));


						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}


		public ArrayList<MerchndiserGetterSetter> getRemainMerchandiser() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<MerchndiserGetterSetter> list = new ArrayList<MerchndiserGetterSetter>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT * FROM MERCHANDISER_LIST WHERE MERCHANDISER_ID NOT IN (select MERCHANDISER_CD from JOURNEY_PLAN_SUPERVISOR);",

						null,null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						MerchndiserGetterSetter sb = new MerchndiserGetterSetter();
						sb.setmerc_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MERCHANDISER_ID")));
						sb.setmerc_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MERCHANDISER")));
						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STATUS")));


						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}



		// getCoverageData
		public ArrayList<CoverageBean> getCoverageData(String visitdate) {

			ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
			Cursor dbcursor = null;

			try {


					dbcursor = db.rawQuery("SELECT  * from "
							+ CommonString1.TABLE_COVERAGE_DATA + " where "
							+ CommonString1.KEY_VISIT_DATE + "='" + visitdate + "'",
							null);


				if (dbcursor != null) {

					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
                      CoverageBean sb = new CoverageBean();

						sb.setStoreId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_STORE_ID)));
						sb.setUserId((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_USER_ID))));
						sb.setInTime(((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_IN_TIME)))));
						sb.setOutTime(((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_OUT_TIME)))));
						sb.setVisitDate((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_VISIT_DATE))))));
						sb.setLatitude(((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_LATITUDE)))));
						sb.setLongitude(((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_LONGITUDE)))));


						sb.setSelfieImage((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_IMAGE_SELFIE))))));

						sb.setCoveargestatus((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_COVERAGE_STATUS))))));

						sb.setMerchanId((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_MERCHANDISER_ID))))));


						sb.setCoverageReamark((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_COVERAGE_REMARK))))));

						sb.setDataSource((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_DATA_SOURCE))))));

						sb.setDavId((((dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_DEV_REASON))))));


						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
						e.toString());

			}

			return list;

		}

		/// get store Status
		public StoreBean getStoreStatus(String id) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");

			StoreBean sb = new StoreBean();

			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT  * from  MERCHANDISER_STORE_LIST"
						 + "  WHERE STORE_CD = '"
						+ id + "'", null);

				if (dbcursor != null) {
					int numrows = dbcursor.getCount();

					dbcursor.moveToFirst();
					for (int i = 0; i < numrows; i++) {

						sb.setStoreid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_STORE_CD)));


						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow(CommonString1.KEY_STATUS)));

						dbcursor.moveToNext();

					}

					dbcursor.close();

				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return sb;

		}


		//deviation reason


		public void insertDeviationReason(Deviation_Reason data) {

			db.delete("DEVIATION_REASON", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getdevi_id().size(); i++) {

					values.put("DREASON_ID", Integer.parseInt(data.getdevi_id().get(i)));
					values.put("DREASON", data.getdevi_name().get(i));

					db.insert("DEVIATION_REASON", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}

		// get deviation Reason

		public ArrayList<Deviation_Reason> getDeviationReason() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<Deviation_Reason> list = new ArrayList<Deviation_Reason>();
			Cursor dbcursor = null;

			try {

				dbcursor = db.rawQuery("SELECT  * from DEVIATION_REASON", null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						Deviation_Reason sb = new Deviation_Reason();
						sb.setdevi_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DREASON_ID")));
						sb.setdevi_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DREASON")));

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}


		//insert coverage data
		public long InsertStoreData(CoverageBean data,String mid) {

			ContentValues values = new ContentValues();

			try {

				db.delete(CommonString1.TABLE_STORE_DATA,
						 CommonString1.KEY_STORE_ID
						+ "='" +data.getStoreId()+"'", null);
				values.put(CommonString1.KEY_MID, mid);
				values.put(CommonString1.KEY_STORE_ID, data.getStoreId());

				values.put(CommonString1.KEY_ISSUE, data.getRemarkis());
				values.put(CommonString1.KEY_IMAGE, data.getImage());
				values.put(CommonString1.KEY_IMAGE_1, data.getImage1());
				values.put(CommonString1.KEY_IMAGE_2, data.getImage2());
				values.put(CommonString1.KEY_REMARK, data.getRemark());
				values.put(CommonString1.KEY_STATUS, data.getStatus());

		/////////////////////////////////////////////////////////////////////////////////////

				return db.insert(CommonString1.TABLE_STORE_DATA, null, values);

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Closes Data ",
						ex.toString());
			}
			return 0;
		}


		// get Coverage Store data

		public ArrayList<StoreBean> getCoverageStoreData(String string,String visitdate) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<StoreBean> list = new ArrayList<StoreBean>();
			Cursor dbcursor = null;

			try {
//				dbcursor = db.rawQuery("SELECT  * from  "+ CommonString1.TABLE_STORE_DATA + " where "
//						+ CommonString1.KEY_STORE_ID + " = '" + string + "'",
//						null,null);

	dbcursor = db.rawQuery("SELECT IMAGE1,IMAGE2,IMAGE3,ISSUE,KEY_REMARK,STORE_ID,CATEGORY_ID,CATEGORY,"
						+ "BRAND_ID,BRAND,QUANTITY,REMARK_STORE from " + CommonString1.TABLE_STORE_DATA +" D" +" INNER JOIN "+CommonString1.TABLE_HEADER_DATA
				    	+" H ON D._id=H.COMMONID INNER JOIN "+CommonString1.TABLE_CHILD_DATA +" C ON C.COMMONID=H._id where "
						+ CommonString1.KEY_STORE_ID + " = '" + string + "'",
					    	null,null);




				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						StoreBean sb = new StoreBean();
						sb.setImage1(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE1")));
						sb.setImage2(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE2")));
						sb.setImage3(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE3")));
						sb.setIssue(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("ISSUE")));

						sb.setRemark(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("KEY_REMARK")));
//						sb.setStatus(dbcursor.getString(dbcursor
//								.getColumnIndexOrThrow("STATUS")));
						sb.setStoreid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STORE_ID")));


						sb.setCategoryid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("CATEGORY_ID")));
						sb.setBrandid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("BRAND_ID")));
						sb.setAvail(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("QUANTITY")));
						sb.setRemark_Store(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("REMARK_STORE")));
						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}
		// insert attendence data
		public long insertAttendenceData(AttendenceBean data) {

			ContentValues values = new ContentValues();

			try {

				db.delete(CommonString1.TABLE_ATTENDENCE_DATA, CommonString1.KEY_VISIT_DATE
						+ "='" +data.getVisitDate()+"'", null);

				values.put(CommonString1.KEY_DISTRIBUTOR_ID, data.getDistrbutorId());
				values.put(CommonString1.KEY_USER_ID, data.getUserId());
				values.put(CommonString1.KEY_IN_TIME, data.getIntme());
				values.put(CommonString1.KEY_OUT_TIME, data.getOutTime());
				values.put(CommonString1.KEY_VISIT_DATE, data.getVisitDate());
				values.put(CommonString1.KEY_LATITUDE, data.getLat());
				values.put(CommonString1.KEY_LONGITUDE, data.getLon());
				values.put(CommonString1.KEY_STATUS, data.getStatus());
				values.put(CommonString1.KEY_IMAGE_SELFIE, data.getImage());

				values.put(CommonString1.KEY_REASON, data.getReason());

				return db.insert(CommonString1.TABLE_ATTENDENCE_DATA
						, null, values);

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Closes Data ",
						ex.toString());
			}
			return 0;
		}

	// get Attendence Data

		public ArrayList<AttendenceBean> getAttendenceData(String string,String visitdate) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<AttendenceBean> list = new ArrayList<AttendenceBean>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT  * from  "+ CommonString1.TABLE_ATTENDENCE_DATA + " where "
						+ CommonString1.KEY_USER_ID + " = '" + string + "'"+" AND "+ CommonString1.KEY_VISIT_DATE +"='"+visitdate+"'",

						null,null);


				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {



						AttendenceBean sb = new AttendenceBean();
						sb.setDistrbutorId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("DISTRIBUTOR_ID")));
						sb.setUserId(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("USER_ID")));
						sb.setIntme(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IN_TIME")));
						sb.setOutTime(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("OUT_TIME")));

						sb.setLat(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("LATITUDE")));
						sb.setLon(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("LONGITUDE")));
						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STATUS")));

						sb.setReason(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("REASON")));
						sb.setImage(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE_SELFIE")));
						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}

		// get gate meeting data
		public ArrayList<GATEbEAN> getGateMeetingData(String visitdate) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<GATEbEAN> list = new ArrayList<GATEbEAN>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT  * from  GATE_COVERAGE_DATA INNER JOIN GATE_MER_DETAIL ON GATE_COVERAGE_DATA._id = GATE_MER_DETAIL.COMMONID "+
			" AND "+CommonString1.KEY_VISIT_DATE +"='"+visitdate+"'",

						null,null);

				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						GATEbEAN sb = new GATEbEAN();
						sb.setDate(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("VISIT_DATE")));
						sb.setDesigid(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("MEET")));
						sb.setImage1(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("IMAGE1")));
						sb.setImage2(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("cam1")));
						sb.setImage3(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("cam2")));

						sb.setEID(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("EMPID")));
						sb.setPresent(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("PRESENT")));

						sb.setStatus(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("STATUS")));


						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}


	//insert Secondary Window data


		public void insertSecondaryWindowData(Sup_Window data) {

			db.delete("SUP_PRI_WINDOW", null, null);
			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getCat_id().size(); i++) {

					values.put("CATEGORY_CD", Integer.parseInt(data.getCat_id().get(i)));
					values.put("CATEGORY", data.getCat_name().get(i));
					values.put("BRAND_CD", Integer.parseInt(data.getBrand_id().get(i)));
					values.put("BRAND", data.getBrand_name().get(i));

					db.insert("SUP_PRI_WINDOW", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}


		// get Secondary Window Data



		public ArrayList<SecondaryWindowBean> getSecondaryWindowDataHeader() {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<SecondaryWindowBean> list = new ArrayList<SecondaryWindowBean>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_CD , CATEGORY from SUP_PRI_WINDOW " + " GROUP BY CATEGORY", null);




				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						SecondaryWindowBean sb = new SecondaryWindowBean();
						sb.setCate_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("CATEGORY_CD")));
						sb.setCate_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("CATEGORY")));



						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}


		//insert Performance data


				public void insertSupPerformance(Sup_Performance data) {

					db.delete("SUP_PERFORMANCE", null, null);
					ContentValues values = new ContentValues();

					try {

							values.put("MANDAYS", Integer.parseInt(data.getMandays()));
							values.put("COVERAGE", Integer.parseInt(data.getCoverage()));
							values.put("MERCHANDISED", Integer.parseInt(data.getMerchandised()));
							values.put("COMPLIANCE", Integer.parseInt(data.getCompliance()));

							db.insert("SUP_PERFORMANCE", null, values);


					} catch (Exception ex) {
						Log.d("Database Exception while Insert Store Data ",
								ex.toString());
					}

				}


				//get Performance data
				public ArrayList<Sup_Performance> getPerformanceData() {

					Log.d("FetchingPerformance data--------------->Start<------------",
							"------------------");
					ArrayList<Sup_Performance> list = new ArrayList<Sup_Performance>();
					Cursor dbcursor = null;

					try {
						dbcursor = db.rawQuery("SELECT  * from  SUP_PERFORMANCE",null,null);

						if (dbcursor != null) {
							dbcursor.moveToFirst();
							while (!dbcursor.isAfterLast()) {

								Sup_Performance sb = new Sup_Performance();

								sb.setMandays(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MANDAYS")));
								sb.setCoverage(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("COVERAGE")));
								sb.setMerchandised(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MERCHANDISED")));
								sb.setCompliance(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("COMPLIANCE")));

								list.add(sb);

								dbcursor.moveToNext();
							}
							dbcursor.close();
							return list;
						}

					} catch (Exception e) {
						Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
								e.toString());
						return list;
					}

					Log.d("FetchingStoredat---------------------->Stop<-----------",
							"-------------------");
					return list;

				}



				//insert Merchandiser data


				public void insertSupMerchandiserData(Sup_Merchandiser data) {

					db.delete("SUP_MERCHANDISER_PERFORMANCE", null, null);
					ContentValues values = new ContentValues();

					try {

						for (int i = 0; i < data.getMerchandiser().size(); i++) {

							values.put("MERCHANDISER", data.getMerchandiser().get(i));
							values.put("MANDAYS", Integer.parseInt(data.getMandays().get(i)));
							values.put("COVERAGE", Integer.parseInt(data.getCoverage().get(i)));
							values.put("MERCHANDISED", Integer.parseInt(data.getMerchandised().get(i)));
							values.put("COMPLIANCE", Integer.parseInt(data.getCompliance().get(i)));

							db.insert("SUP_MERCHANDISER_PERFORMANCE", null, values);

						}

					} catch (Exception ex) {
						Log.d("Database Exception while Insert Store Data ",
								ex.toString());
					}

				}


				//get Merchendiserv Performance data
				public ArrayList<Sup_Merchandiser> getMechendiserPerformanceData() {

					Log.d("FetchingPerformance data--------------->Start<------------",
							"------------------");
					ArrayList<Sup_Merchandiser> list = new ArrayList<Sup_Merchandiser>();
					Cursor dbcursor = null;

					try {
						dbcursor = db.rawQuery("SELECT  * from  SUP_MERCHANDISER_PERFORMANCE",null,null);

						if (dbcursor != null) {
							dbcursor.moveToFirst();
							while (!dbcursor.isAfterLast()) {

								Sup_Merchandiser sb = new Sup_Merchandiser();

								sb.setMerchandiser(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MERCHANDISER")));
								sb.setMandays(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MANDAYS")));
								sb.setCoverage(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("COVERAGE")));
								sb.setMerchandised(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("MERCHANDISED")));
								sb.setCompliance(dbcursor.getString(dbcursor
										.getColumnIndexOrThrow("COMPLIANCE")));

								list.add(sb);

								dbcursor.moveToNext();
							}
							dbcursor.close();
							return list;
						}

					} catch (Exception e) {
						Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
								e.toString());
						return list;
					}

					Log.d("FetchingStoredat---------------------->Stop<-----------",
							"-------------------");
					return list;

				}



		// get Child data

		public ArrayList<SecondaryWindowBean> getSecondaryWindowDataChild(String CateId) {

			Log.d("FetchingStoredata--------------->Start<------------",
					"------------------");
			ArrayList<SecondaryWindowBean> list = new ArrayList<SecondaryWindowBean>();
			Cursor dbcursor = null;

			try {
				dbcursor = db.rawQuery("SELECT BRAND_CD, BRAND from SUP_PRI_WINDOW " +
                        " WHERE CATEGORY_CD ="+ CateId, null);



				if (dbcursor != null) {
					dbcursor.moveToFirst();
					while (!dbcursor.isAfterLast()) {
						SecondaryWindowBean sb = new SecondaryWindowBean();
						sb.setBrand_id(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("BRAND_CD")));
						sb.setBrand_name(dbcursor.getString(dbcursor
								.getColumnIndexOrThrow("BRAND")));

						list.add(sb);
						dbcursor.moveToNext();
					}
					dbcursor.close();
					return list;
				}

			} catch (Exception e) {
				Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
						e.toString());
				return list;
			}

			Log.d("FetchingStoredat---------------------->Stop<-----------",
					"-------------------");
			return list;

		}

		public void insertHeaderData(Sup_Window data) {


			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getCat_id().size(); i++) {

					values.put("CATEGORY_ID", data.getCat_id().get(i));
					values.put("CATEGORY", data.getCat_name().get(i));
					values.put("STATUS", Integer.parseInt(data.getBrand_id().get(i)));
					values.put("COMMONID", data.getBrand_name().get(i));

					db.insert("HEADER_DATA", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}

		// insert child data


	public void insertChildData(Sup_Window data) {


			ContentValues values = new ContentValues();

			try {

				for (int i = 0; i < data.getCat_id().size(); i++) {

					values.put("BRAND_CD", data.getCat_id().get(i));
					values.put("BRAND", data.getCat_name().get(i));
					values.put("STATUS", Integer.parseInt(data.getBrand_id().get(i)));
					values.put("COMMONID", data.getBrand_name().get(i));
					values.put("KEY_REMARK", Integer.parseInt(data.getBrand_id().get(i)));
					values.put("QUANTITY", data.getBrand_name().get(i));


					db.insert("CHILD_DATA", null, values);

				}

			} catch (Exception ex) {
				Log.d("Database Exception while Insert Store Data ",
						ex.toString());
			}

		}


	public void InsertWindowData(long mid,
            HashMap<SecondaryWindowBean, List<SecondaryWindowBean>> data,List<SecondaryWindowBean> save_listDataHeader) {

     ContentValues values = new ContentValues();
     ContentValues values1 = new ContentValues();

     try {

            db.beginTransaction();

            for (int i = 0; i < save_listDataHeader.size(); i++) {


                  values.put(CommonString1.KEY_CATEGORY_ID, save_listDataHeader.get(i)
                          .getCate_id());

                  values.put(CommonString1.KEY_CATEGORY, save_listDataHeader.get(i)
                                .getCate_name());
                  values.put("STATUS", "P");

                  values.put("COMMONID", String.valueOf(mid));

                  long l = db.insert(CommonString1.TABLE_HEADER_DATA,
                                null, values);

                  for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {

                         values1.put(CommonString1.KEY_COMMMON_ID, l);
                         values.put("STATUS", "P");
                       values1.put(CommonString1.KEY_BRAND_ID,
                                     data.get(save_listDataHeader.get(i)).get(j)
                                                   .getBrand_id());

                       values1.put(CommonString1.KEY_BRAND,
                               data.get(save_listDataHeader.get(i)).get(j)
                                             .getBrand_name());


                         values1.put(CommonString1.KEY_AVAILABILITY,
                                       data.get(save_listDataHeader.get(i)).get(j)
                                                     .getAvailable());

                         values1.put(CommonString1.KEY_REMARK_STORE,
                                       data.get(save_listDataHeader.get(i)).get(j)
                                                     .getRemark());


                         db.insert(CommonString1.TABLE_CHILD_DATA,
                                       null, values1);

                  }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
     } catch (Exception ex) {
            Log.d("Database Exception while Inserting Asset Data ",
                         ex.toString());
     }

}
	*/

    public ArrayList<GeotaggingBeans> getGeotaggingData(String STORE_ID) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");

        ArrayList<GeotaggingBeans> geodata = new ArrayList<GeotaggingBeans>();

        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT DISTINCT * FROM STORE_GEOTAGGING WHERE STORE_ID= '" + STORE_ID + "' ", null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();

                dbcursor.moveToFirst();
                for (int i = 1; i <= numrows; ++i) {

                    GeotaggingBeans data = new GeotaggingBeans();


                    data.setStoreId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_ID")));

                    data.setLatitude(Double.parseDouble(dbcursor.getString(dbcursor.getColumnIndexOrThrow("LATITUDE"))));

                    data.setLongitude(Double.parseDouble(dbcursor.getString(dbcursor.getColumnIndexOrThrow("LONGITUDE"))));

                    data.setUrl1(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("FRONT_IMAGE")));

                    data.setGEO_TAG(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("GEO_TAG")));


                    geodata.add(data);
                    dbcursor.moveToNext();

                }

                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return geodata;

    }

    public void deletegeotaggingdata() {

        try {
            db.delete(CommonString.TABLE_STORE_GEOTAGGING, null, null);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }


    public boolean updateGeoTagStatus(String id, String status, Double lat,
                                      Double longitude) {
        ContentValues values = new ContentValues();
        values.put("STATUS", status);
        values.put("LATITUDE", Double.toString(lat));
        values.put("LONGITUDE", Double.toString(longitude));
        return db.update(CommonString.TABLE_STORE_DETAILUSER, values,
                "STORE_ID =" + id, null) > 0;

    }


    public void updateOutTime(String status, String StoreId, String VisitDate) {

        try {
            ContentValues values = new ContentValues();


            values.put(CommonString1.KEY_GEO_TAG, status);


            db.update(CommonString1.TABLE_COVERAGE_DATA, values, CommonString1.KEY_STORE_CD + "='" + StoreId + "' AND "
                    + CommonString1.KEY_VISIT_DATE + "='" + VisitDate
                    + "'", null);
        } catch (Exception e) {

        }
    }


    public void InsertStoregeotagging(String storeid, double lat,
                                      double longitude, String path, String status) {

        ContentValues values = new ContentValues();

        try {

            values.put("STORE_ID", storeid);
            values.put("LATITUDE", Double.toString(lat));
            values.put("LONGITUDE", Double.toString(longitude));
            values.put("FRONT_IMAGE", path);
            values.put("GEO_TAG", status);

			/*values.put("INSIDE_IMAGE", path_inside);
			values.put("INSIDE_IMAGE1", path_inside1);*/
            //values.put("STATUS", status);

            db.insert(CommonString.TABLE_STORE_GEOTAGGING, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }

    }


    public long insertPOSM(String store_id, ArrayList<POSMDATAGetterSetter> secPlaceData, ArrayList<POSMDATAGetterSetter> list, String _pathforcheck) {

        db.delete(CommonString.TABLE_POSM_DATA_GT, "STORE_CD" + "='" + store_id + "'", null);
        long id = 0;
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < secPlaceData.size(); i++) {

                values.put("STORE_CD", Integer.parseInt(store_id));
                values.put("IMAGE", secPlaceData.get(i).getImage_Url());
                values.put("POSM", secPlaceData.get(i).getPOSM().get(0));
                values.put("POSM_QTNY", secPlaceData.get(i).getEdText());
                values.put("POSM_CD", Integer.parseInt(secPlaceData.get(i).getPOSM_CD().get(0)));

                id = db.insert(CommonString.TABLE_POSM_DATA_GT, null, values);

            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Facing Competition Data ",
                    ex.toString());
            return 0;
        }
        return id;
    }

    public long insertPOSM2Data(String store_id, ArrayList<POSMDATAGetterSetter> secPlaceData) {

        db.delete(CommonString.TABLE_POSM_DATA_2, "STORE_CD" + "='" + store_id + "'", null);
        long id = 0;
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < secPlaceData.size(); i++) {

                values.put("STORE_CD", Integer.parseInt(store_id));
                values.put("IMAGE", secPlaceData.get(i).getImage_Url());
                values.put("POSM", secPlaceData.get(i).getPOSM().get(0));
                values.put("REASON_CD", secPlaceData.get(i).getReason_CD());
                values.put("REASON", secPlaceData.get(i).getReason());
                values.put("ISEXIST", secPlaceData.get(i).isExist());
                values.put("POSM_CD", Integer.parseInt(secPlaceData.get(i).getPOSM_CD().get(0)));

                id = db.insert(CommonString.TABLE_POSM_DATA_2, null, values);

            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert posm 2 Data ",
                    ex.toString());
            return 0;
        }
        return id;
    }


    public boolean insertCOMPETITORDATA(String store_id, ArrayList<COMPETITORGetterSetter> secPlaceData, ArrayList<COMPETITORGetterSetter> list, String isexist) {

        db.delete(CommonString1.TABLE_INSERT_COMPETITOR_DATA_EXIST, "STORE_CD" + "='" + store_id + "'", null);
        db.delete(CommonString1.TABLE_INSERT_COMPETITOR_DATA, "STORE_CD" + "='" + store_id + "'", null);

        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();
        long commonid2 = 0;
        try {
            values2.put(CommonString1.KEY_STORE_CD, store_id);
            values2.put(CommonString1.KEY_COMPETITOR_EXIST_CD, isexist);

            long commonid = db.insert(CommonString1.TABLE_INSERT_COMPETITOR_DATA_EXIST, null, values2);

            if (isexist.equalsIgnoreCase("1")) {
                for (int i = 0; i < secPlaceData.size(); i++) {

                    values.put(CommonString1.KEY_STORE_CD, Integer.parseInt(store_id));
                    values.put(CommonString1.KEY_COMMMON_ID, (int) commonid);
                    values.put("Image_URL", secPlaceData.get(i).getImage());
                    values.put(CommonString1.KEY_COMPANY_CD, Integer.parseInt(secPlaceData.get(i).getCOMPANY_CD()));
                    values.put(CommonString1.KEY_COMPANY, secPlaceData.get(i).getCOMPANY());
                    values.put(CommonString1.KEY_COMPETITOR_EXIST_CD, secPlaceData.get(i).getSpinnerExists_CD());
                    values.put(CommonString1.KEY_REMARKS, secPlaceData.get(i).getEdText());

                    commonid2 = db.insert(CommonString1.TABLE_INSERT_COMPETITOR_DATA, null, values);
                }
            }

            if (isexist.equalsIgnoreCase("1") && commonid > 0 && commonid2 > 0) {
                return true;
            } else if (isexist.equalsIgnoreCase("0") && commonid > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Facing Competition Data ", ex.toString());
            return false;
        }

    }


    public ArrayList<POSMDATAGetterSetter> getPOSMData(String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<POSMDATAGetterSetter> list = new ArrayList<POSMDATAGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM POSM_DATA_GT WHERE STORE_CD ='" + store_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    POSMDATAGetterSetter sb = new POSMDATAGetterSetter();


                    sb.setPOSM(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM")));

                    sb.setEdText(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM_QTNY")));

                    sb.setPOSM_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM_CD")));

                    sb.setImage_Url(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE")));

                    sb.setID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("MID")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching Asset brand---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<COMPETITORGetterSetter> getCOMPETITORData(String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<COMPETITORGetterSetter> list = new ArrayList<COMPETITORGetterSetter>();
        Cursor dbcursor = null;
        try {
            //dbcursor = db.rawQuery("SELECT * FROM COMPETITOR_DATA WHERE STORE_CD ='" + store_cd + "'", null);
            dbcursor = db.rawQuery("select cde.KEY_ID as KEY_ID,cde.COMPETITOR_EXIST_CD as COMPETITOR_EXIST_CD,IFNULL(cd.COMPANY_CD,'0') as COMPANY_CD,IFNULL(cd.COMPANY,'') as COMPANY,IFNULL(cd.Image_URL,'') as Image_URL,IFNULL(cd.REMARKS,'') as REMARKS from COMPETITOR_DATA_EXIST cde left join COMPETITOR_DATA cd on cde.KEY_ID = cd.COMMONID where cde.STORE_CD = " + store_cd + "", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    COMPETITORGetterSetter sb = new COMPETITORGetterSetter();

                    sb.setSpinnerExists_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPETITOR_EXIST_CD")));
                    sb.setCOMPANY(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY")));
                    sb.setCOMPANY_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY_CD")));
                    sb.setEdText(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REMARKS")));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Image_URL")));
                    sb.setID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!", e.toString());
            return list;
        }
        Log.d("Fetching Asset brand---------------------->Stop<-----------", "-------------------");
        return list;
    }


    public ArrayList<MappingPaidVisibilityGetterSetter> getMappingPaidVisibilityData(String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<MappingPaidVisibilityGetterSetter> list = new ArrayList<MappingPaidVisibilityGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM MAPPING_PAID_VISIBILITY WHERE STORE_CD ='" + store_cd + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingPaidVisibilityGetterSetter sb = new MappingPaidVisibilityGetterSetter();

                    sb.setDISPLAY_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_CD")));

                    sb.setBRAND_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching Asset brand---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public ArrayList<POSMDATAGetterSetter> getPOSM(String store_cd) {

        //Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<POSMDATAGetterSetter> list1 = new ArrayList<POSMDATAGetterSetter>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT  distinct * from POSM_data ORDER BY IMAGETYPE_CD", null);


            if (dbcursor != null) {


                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    POSMDATAGetterSetter crowndata = new POSMDATAGetterSetter();


                    //crowndata.setIMAGETYPE_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGETYPE_CD")));

                    //crowndata.setIMAGE_TYPE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE_TYPE")));

                    crowndata.setEdText("");

                    crowndata.setImage_Url("");

                    list1.add(crowndata);
                    dbcursor.moveToNext();

                }

                dbcursor.close();
                return list1;
            }

        } catch (Exception e) {

            return list1;
        }


        return list1;

    }


    public ArrayList<POSMDATAGetterSetter> getPOSMDATA(String store_cd, String storeType_cd) {
        //Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<POSMDATAGetterSetter> list1 = new ArrayList<POSMDATAGetterSetter>();
        Cursor dbcursor = null;
        try {
            //dbcursor = db.rawQuery("SELECT  distinct * from POSM_MASTER ORDER BY POSM_CD", null);
            dbcursor = db.rawQuery("select distinct PM.POSM_CD,PM.POSM from MAPPING_POSM_STOREWISE MP  inner join POSM_MASTER PM on MP.POSM_CD= PM.POSM_CD where MP.STORE_CD = '" + store_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    POSMDATAGetterSetter crowndata = new POSMDATAGetterSetter();
                    crowndata.setPOSM_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("POSM_CD")));
                    crowndata.setPOSM(dbcursor.getString(dbcursor.getColumnIndexOrThrow("POSM")));

                    list1.add(crowndata);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list1;
            }

        } catch (Exception e) {

            return list1;
        }
        return list1;

    }


    public ArrayList<POSMDATAGetterSetter> deleteTable(String store_id) {

        db.delete(CommonString1.TABLE_INSERT_POSM_DATA, "STORE_CD" + "='" + store_id + "'", null);
        return null;


    }

    public void remove(String user_id) {
        // String string =String.valueOf(user_id);
        db.execSQL("DELETE FROM POSM_data WHERE KEY_ID = '" + user_id + "'");
    }

    public void RemoveCOM(String user_id) {
        // String string =String.valueOf(user_id);
        db.execSQL("DELETE FROM COMPETITOR_DATA WHERE KEY_ID = '" + user_id + "'");
    }

    public ArrayList<WindowListGetterSetter> getWindowListData(String STATE_CD, String STORE_TYPE_CD) {
        Log.d("FetchWidowdata->Start<-", "-");
        ArrayList<WindowListGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT WM.BRAND_CD, WM.WINDOW_CD, W.WINDOW, W.SKU_HOLD, W.PLANOGRAM_IMAGE" +
                    " FROM WINDOW_MAPPING WM INNER JOIN WINDOW_MASTER W ON WM.WINDOW_CD = W.WINDOW_CD " +
                    " WHERE WM.STATE_CD ='" + STATE_CD + "' AND WM.STORETYPE_CD ='" + STORE_TYPE_CD + "'  ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    WindowListGetterSetter sb = new WindowListGetterSetter();


                    sb.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));

                    sb.setWindow_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW_CD")));

                    sb.setWindow(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("WINDOW")));


                    sb.setSku_hold(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_HOLD")));

                    sb.setPlanogram_image(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PLANOGRAM_IMAGE")));

                    sb.setIslisted(true);
                    sb.setWindow_image("");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exc get windows list!", e.toString());
            return list;
        }

        Log.d("Fetching windows->Stop<", "-");
        return list;
    }


    //get Checklist data for windows

    public ArrayList<ChecklistGetterSetter> getChecklistData(String window_cd) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<ChecklistGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT WC.CHECKLIST_CD, WC.CHECKLIST, WC.ANSWER_TYPE" +
                    " FROM MAPPING_WINDOW_CHECKLIST MWC INNER JOIN WINDOW_CHECKLIST WC ON MWC.CHECKLIST_CD = WC.CHECKLIST_CD " +
                    " WHERE MWC.WINDOW_CD ='" + window_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistGetterSetter ch = new ChecklistGetterSetter();


                    ch.setChecklist(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST")));

                    ch.setChecklist_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST_CD")));

                    ch.setAnswer_type(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER_TYPE")));

                    ch.setAnswer("");

                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exc get windows list!", e.toString());
            return list;
        }

        Log.d("Fetching check->Stop<", "-");
        return list;
    }


    //Get Checklist Answer list for a checklist cd
    public ArrayList<AnswerChecklistGetterSetter> getChecklistAnswerData(String checklist_cd) {
        Log.d("Fetanswerdata->Start<-", "-");
        ArrayList<AnswerChecklistGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT CHECKLIST_CD, ANSWER_CD, ANSWER" +
                    " FROM WINDOW_CHECKLIST_ANSWER " +
                    " WHERE CHECKLIST_CD ='" + checklist_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    AnswerChecklistGetterSetter ch = new AnswerChecklistGetterSetter();

                    ch.setAnswer_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER_CD")));

                    ch.setAnswer(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER")));

                    ch.setChecklist_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CHECKLIST_CD")));

                    //ch.setAnswer("");

                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exc get ans list!", e.toString());
            return list;
        }

        Log.d("Fet checklist->Stop<", "-");
        return list;
    }

//get Category Data


    public ArrayList<CategoryMasterGetterSetter> getCategoryAllData(String brand_cd, String sku_hold, String store_type_cd, String state_cd) {

        Log.d("FetchingCategoryType--------------->Start<------------",
                "------------------");
        ArrayList<CategoryMasterGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            if (!brand_cd.equals("0") && sku_hold.equals("B")) {
                dbcursor = db.rawQuery("SELECT CATEGORY_CD, CATEGORY FROM CATEGORY_MASTER WHERE CATEGORY_CD IN" +
                                "(SELECT DISTINCT CATEGORY_CD FROM BRAND_MASTER WHERE BRAND_CD = '" + brand_cd + "')"
                        , null);
            } else if (brand_cd.equals("0") && sku_hold.equals("F")) {

                dbcursor = db.rawQuery("SELECT CATEGORY_CD, CATEGORY FROM CATEGORY_MASTER WHERE CATEGORY_CD IN" +
                                "(SELECT CATEGORY_CD FROM BRAND_MASTER WHERE BRAND_CD IN " +
                                "(SELECT DISTINCT SK.BRAND_CD FROM MAPPING_AVAILABILITY M INNER JOIN SKU_MASTER SK ON M.SKU_CD = SK.SKU_CD" +
                                " WHERE M.STATE_CD = '" + state_cd + "' AND M.STORETYPE_CD = '" + store_type_cd + "'" + " AND M.FOCUS = 1))"
                        , null);

            } else if (brand_cd.equals("0") && sku_hold.equals("A")) {
                dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_CD, CATEGORY from CATEGORY_MASTER ORDER BY CATEGORY_SEQUENCE "
                        , null);
            }


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMasterGetterSetter df = new CategoryMasterGetterSetter();


                    df.setCategory_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));
                    df.setCategory(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY")));

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Category!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingCategory data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //get Brand Data

    public ArrayList<BrandGetterSetter> getBrandsData(String window_brand_cd, String sku_hold, String category_cd, String state_cd, String store_type_cd) {

        Log.d("FetchingBrand->Start<",
                "-");
        ArrayList<BrandGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            if (!window_brand_cd.equals("0") && sku_hold.equals("B")) {
                dbcursor = db.rawQuery(" SELECT * FROM BRAND_MASTER WHERE CATEGORY_CD = '" + category_cd + "' " +
                        " AND BRAND_CD = '"
                        + window_brand_cd + "' ORDER BY BRAND_SEQUENCE", null);

            } else if (window_brand_cd.equals("0") && sku_hold.equals("F")) {
                dbcursor = db.rawQuery("SELECT * FROM BRAND_MASTER WHERE CATEGORY_CD = '" + category_cd + "' AND BRAND_CD IN(" +
                                "SELECT DISTINCT SK.BRAND_CD FROM MAPPING_AVAILABILITY M INNER JOIN SKU_MASTER SK ON M.SKU_CD = SK.SKU_CD" +
                                " WHERE M.STATE_CD = '" + state_cd + "' AND M.STORETYPE_CD = '" + store_type_cd + "' " +
                                "AND M.FOCUS = '" + 1 + "')"
                        , null);

            } else if (window_brand_cd.equals("0") && sku_hold.equals("A")) {

                dbcursor = db.rawQuery("SELECT * from BRAND_MASTER  WHERE CATEGORY_CD" + " = '" + category_cd +
                                "' ORDER BY BRAND_SEQUENCE "
                        , null);
            }


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandGetterSetter df = new BrandGetterSetter();

                    df.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    df.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));
                    df.setCompany_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));
                    df.setCategory_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));
                    df.setCompany_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMPANY_CD")));

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Brand!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Brand data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    //get SKU Data

    public ArrayList<SkuMasterGetterSetter> getSKUData(String brand_cd, String sku_hold, String state_cd, String store_type_cd) {

        Log.d("FetchingBrand->Start<",
                "-");
        ArrayList<SkuMasterGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            if (sku_hold.equals("F")) {
                dbcursor = db.rawQuery("SELECT SK.BRAND_CD,SK.SKU_CD, SK.SKU FROM MAPPING_AVAILABILITY M INNER JOIN SKU_MASTER SK ON M.SKU_CD = SK.SKU_CD" +
                                " WHERE M.STATE_CD ='" + state_cd + "' AND M.STORETYPE_CD = '" + store_type_cd + "' AND M.FOCUS =1 AND SK.BRAND_CD ='" + brand_cd + "' ORDER BY SK.SKU_SEQUENCE "
                        , null);

            } else {
                dbcursor = db.rawQuery("SELECT * FROM SKU_MASTER WHERE BRAND_CD ='" + brand_cd + "' ORDER BY SKU_SEQUENCE"
                        , null);
            }

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuMasterGetterSetter df = new SkuMasterGetterSetter();


                    df.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    df.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));
                    df.setSku(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Excep fetching SKU!!",
                    e.toString());
            return list;
        }

        Log.d("SKU data---------------------->Stop<-----------",
                "-------------------");
        return list;

    }



/*	public void InsertSKUEntry(String store_cd, String category_id, String brand_id, String sku_id, String window_cd,
							   String stock, String stocksonetosix, String stockseventotwelve, String stockabovethirteen){

//        db.delete(CommonString1.TABLE_WINDOWS_DATA, null, null);
		ContentValues values = new ContentValues();

		try {


			values.put(CommonString1.KEY_STORE_CD, store_cd);
			values.put(CommonString1.KEY_BRAND_ID, brand_id);
			values.put("SKU_CD", sku_id);
			values.put("WINDOW_CD", window_cd);
			values.put("STOCK", stock);
			values.put("stocksonetosix", stocksonetosix);
			values.put("stockseventotwelve", stockseventotwelve);
			values.put("stockabovethirteen", stockabovethirteen);


			db.insert(CommonString1.TABLE_INSERT_SKU_ENTRY, null, values);



		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.toString());
		}

	}*/


    public void InsertSKUEntry(ArrayList<WindowSKUEntryGetterSetter> skuEntryList, String window_cd, String store_cd) {

//        db.delete(CommonString1.TABLE_WINDOWS_DATA, null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < skuEntryList.size(); i++) {

                values.put(CommonString1.KEY_STORE_CD, store_cd);
                values.put(CommonString1.KEY_BRAND_ID, skuEntryList.get(i).getBrand_cd());
                values.put("SKU_CD", skuEntryList.get(i).getSku_cd());
                values.put("WINDOW_CD", window_cd);
                values.put("STOCK", skuEntryList.get(i).getStock());
				/*values.put("stocksonetosix", skuEntryList.get(i).getStocksonetosix());
				values.put("stockseventotwelve", skuEntryList.get(i).getSeventotwelve());
				values.put("stockabovethirteen", skuEntryList.get(i).getStockabovethirteen());
*/
                values.put("stocksonetosix", "0");
                values.put("stockseventotwelve", "0");
                values.put("stockabovethirteen", "0");
                db.insert(CommonString1.TABLE_INSERT_SKU_ENTRY, null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }

    }

    //check if Window sku table is filled
    public boolean isWindowSkuDataFilled(String storeId, String window_cd) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM WINDOWS_DATA WHERE STORE_CD= '" + storeId + "' AND WINDOW_CD ='" + window_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }

    public long InsertWindowsData(String store_cd, String visit_date, String username, String window_cd, boolean existOrnot,
                                  String window_image, String reasonid) {
        long l = 0;
        // db.delete(CommonString1.TABLE_WINDOWS_DATA, "WINDOW_CD ='" + window_cd + "' AND STORE_CD ='"+ store_cd+"'", null);
        ContentValues values = new ContentValues();

        try {

            values.put(CommonString1.KEY_STORE_CD, store_cd);
            values.put("DATE", visit_date);
            values.put(CommonString1.KEY_USER_ID, username);
            values.put("WINDOW_CD", window_cd);
            values.put("WINDOW_IMAGE", window_image);
            values.put("EXISTORNOT", existOrnot);
            values.put("REASON_ID", reasonid);

            l = db.insert(CommonString1.TABLE_WINDOWS_DATA, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }
        return l;
    }


    public ArrayList<ShareShelfInsertHeaderDataGetterSetter> getShareShelfHeaderData(String store_cd) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<ShareShelfInsertHeaderDataGetterSetter> list = new ArrayList<ShareShelfInsertHeaderDataGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT  H.CATEGORY_CD,H.IMAGE1, H.QUANTITY AS CATEGORY_QTY, C.BRAND_CD, C.QUANTITY AS BRAND_QTY FROM SHARESHELF_HEADER_DATA H INNER JOIN SHARESHELF_CHILD_DATA C " +
                            "ON H._ID = C.COMMONID " +
                            "WHERE H.STORE_CD = '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShareShelfInsertHeaderDataGetterSetter fc = new ShareShelfInsertHeaderDataGetterSetter();

                    fc.setCategorycd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_CD")));
                    fc.setCategory_qty(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CATEGORY_QTY")));
                    fc.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    fc.setBrand_qty(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_QTY")));
                    fc.setImage_name(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_IMAGE)));

                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<BrandGetterSetter> getStockAvailabilityForChannel2Data(String store_cd) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<BrandGetterSetter> list = new ArrayList<BrandGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT  H.BRAND_CD, C.SKU_CD, C.QUANTITY AS SKU_QTY FROM STOCKCHANNEL2_HEADER_DATA H INNER JOIN STOCKCHANNEL2_CHILD_DATA C " +
                            "ON H._ID = C.COMMONID " +
                            "WHERE H.STORE_CD = '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandGetterSetter fc = new BrandGetterSetter();

                    fc.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_CD")));
                    fc.setSku_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_CD")));
                    fc.setQuantity(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_QTY")));
                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<DisplayMasterGetterSetter> getPaidVisibilityData(String store_cd) {

        Log.d("Fetching calls--------------->Start<------------",
                "------------------");
        ArrayList<DisplayMasterGetterSetter> list = new ArrayList<DisplayMasterGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * FROM PAID_VISIBILITY_DATA WHERE STORE_CD = '" + store_cd + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    DisplayMasterGetterSetter fc = new DisplayMasterGetterSetter();

                    fc.setDISPLAY_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_DISPLAY_CD)));
                    fc.setExist(CommonFunctions.convertInttoBool(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_IS_EXIST))));
                    fc.setImage(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY__IMAGE)));
                    fc.setRemark(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_REMARK)));
                    fc.setBrand_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_BRAND_CD)));
                    list.add(fc);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching comp poi data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching facing Calls---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //check if table is empty
    public boolean isShareShelfDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM SHARESHELF_HEADER_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_CD")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    //check if table is empty
    public boolean isStockData2Filled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM STOCKCHANNEL2_HEADER_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_CD")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    public boolean isPaidVisibilityFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;
        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM PAID_VISIBILITY_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("DISPLAY_CD")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    public boolean isFreeVisibilityFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;
        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM FREE_VISIBILITY_DATA WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY_CD")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    public void updateCoverage(String status, String storeid) {

        ContentValues values = new ContentValues();


        try {

            values.put(CommonString1.KEY_COVERAGE_STATUS, status);

            db.update("COVERAGE_DATA", values,
                    "STORE_ID ='" + storeid
                            + "'", null);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }


    }

    //get Mapping Promotion Data

    public ArrayList<MappingPromotion2GetterSetter> getMapppingPromotionData(String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<MappingPromotion2GetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM MAPPING_PROMOTION WHERE STORE_CD= '" + store_cd + "' "
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingPromotion2GetterSetter mp = new MappingPromotion2GetterSetter();
                    mp.setID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ID")));

                    mp.setPROMO(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PROMO")));

                    list.add(mp);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Mapping Promotion",
                    e.toString());
            return list;
        }

        Log.d("fetching Mapping Promotion--->Stop<--",
                "-------------------");
        return list;
    }

    //get NON_PROMOTION_REASON Data

    public ArrayList<NonPromotionReasonGetterSetter> getNonPromotionReasonData() {
        Log.d("NON_PROMOTION_REASON--------------->Start<------------",
                "------------------");
        ArrayList<NonPromotionReasonGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM NON_PROMOTION_REASON "
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    NonPromotionReasonGetterSetter mp = new NonPromotionReasonGetterSetter();

                    mp.setPREASON_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PREASON_CD")));

                    mp.setPREASON(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PREASON")));

                    list.add(mp);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching NON_PROMOTION_REASON",
                    e.toString());
            return list;
        }

        Log.d("fetching NON_PROMOTION_REASON--->Stop<--",
                "-------------------");
        return list;
    }

    //insert Promotion data
    public long insertPromotionData(String store_cd, ArrayList<MappingPromotion2GetterSetter> promoData) {
        long id = 0;

        db.delete(CommonString1.TABLE_INSERT_POSM_DATA, "STORE_CD = '" + store_cd + "'", null);

        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < promoData.size(); i++) {

                values.put(CommonString1.KEY_STORE_CD, store_cd);
                values.put("POSM_CD", promoData.get(i).getID().get(0));
                values.put("POSM", promoData.get(i).getPROMO().get(0));
                values.put("REASON_CD", promoData.get(i).getReason_cd());
                values.put("Image_URL", (promoData.get(i).getImage()));
                values.put("REASON", (promoData.get(i).getReason()));
                values.put("REASON", (promoData.get(i).getReason()));
                values.put("IsExist", (promoData.get(i).isExist()));

                id = db.insert(CommonString1.TABLE_INSERT_POSM_DATA, null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Promotion Data ",
                    ex.toString());
            return 0;
        }

        return id;
    }

    //get Promotion Inserted Data

    public ArrayList<MappingPromotion2GetterSetter> getPromotionInsertedData(String store_cd) {
        Log.d("FetchingPromodata--------------->Start<------------",
                "------------------");
        ArrayList<MappingPromotion2GetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM " + CommonString1.TABLE_INSERT_POSM_DATA + " WHERE STORE_CD= '" + store_cd + "'"
                            , null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingPromotion2GetterSetter mp = new MappingPromotion2GetterSetter();
                    mp.setID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM_CD")));

                    mp.setPROMO(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM")));
                    mp.setID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM_CD")));
                    mp.setReason_cd(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON_CD")));
                    mp.setReason(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON")));
                    mp.setImage(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("Image_URL")));

                    mp.setExist(CommonFunctions.convertInttoBool(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IsExist"))));

                    list.add(mp);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Promotion",
                    e.toString());
            return list;
        }

        Log.d("fetching Mapping Promotion--->Stop<--",
                "-------------------");
        return list;
    }


    public ArrayList<POSMDATAGetterSetter> getPOSM2data(String store_cd) {
        Log.d("FetchingPromodata--------------->Start<------------",
                "------------------");
        ArrayList<POSMDATAGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM " + CommonString.TABLE_POSM_DATA_2 + " WHERE STORE_CD= '" + store_cd + "'"
                            , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    POSMDATAGetterSetter mp = new POSMDATAGetterSetter();
                    mp.setPOSM_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM_CD")));
                    mp.setPOSM(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM")));
                    mp.setReason_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON_CD")));
                    mp.setReason(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("REASON")));
                    mp.setImage_Url(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE")));
                    mp.setExist(CommonFunctions.convertInttoBool(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ISEXIST"))));

                    list.add(mp);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Promotion",
                    e.toString());
            return list;
        }

        Log.d("fetching Mapping Promotion--->Stop<--",
                "-------------------");
        return list;
    }

    public boolean isPromotionDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;
        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM POSM_data WHERE STORE_CD= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("POSM_CD")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }

                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    //JCP data

    public void insertJCPPreviousData(JourneyPlanPreviousGetterSetter data) {

        db.delete("JOURNEY_PLAN_PREV", null, null);
        ContentValues values = new ContentValues();

        try {
            for (int i = 0; i < data.getSTORE_CD().size(); i++) {

                values.put("STORE_CD", Integer.parseInt(data.getSTORE_CD().get(i)));
                values.put("VISITDATE", data.getVISITDATE().get(i));
                values.put("KEYACCOUNT", data.getKEYACCOUNT().get(i));
                values.put("STORENAME", data.getSTORENAME().get(i));
                values.put("ADDRESS", data.getADDRESS().get(i));
                values.put("CHANNEL_CD", Integer.parseInt(data.getCHANNEL_CD().get(i)));
                values.put("CITY", data.getCITY().get(i));
                values.put("COVERAGE", data.getCOVERAGE().get(i));
                values.put("REASON", data.getREASON().get(i));

                db.insert("JOURNEY_PLAN_PREV", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert JOURNEY_PLAN_PREV Data ",
                    ex.toString());
        }

    }

    public ArrayList<JourneyPlanPreviousGetterSetter> getPreviousStoreData(String visitDate) {
        ArrayList<JourneyPlanPreviousGetterSetter> list = new ArrayList<JourneyPlanPreviousGetterSetter>();
        Cursor dbcursor = null;
        try {
            if (visitDate == null) {
                dbcursor = db.rawQuery("SELECT * FROM JOURNEY_PLAN_PREV", null);
            } else {
                dbcursor = db.rawQuery("SELECT * FROM JOURNEY_PLAN_PREV WHERE VISITDATE ='" + visitDate + "'", null);
            }

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JourneyPlanPreviousGetterSetter sb = new JourneyPlanPreviousGetterSetter();

                    sb.setSTORE_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_CD")));
                    sb.setVISITDATE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("VISITDATE")));
                    sb.setKEYACCOUNT(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEYACCOUNT")));
                    sb.setSTORENAME(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORENAME")));
                    sb.setADDRESS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ADDRESS")));
                    sb.setCHANNEL_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHANNEL_CD")));
                    sb.setCITY(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CITY")));
                    sb.setCOVERAGE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COVERAGE")));
                    sb.setREASON(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REASON")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!", e.toString());
            return list;
        }
        Log.d("Fetching Asset brand---------------------->Stop<-----------", "-------------------");
        return list;
    }

    public long InsertPreviousCoverageData(CoverageBean data) {

        //db.delete(CommonString1.TABLE_COVERAGE_DATA, "STORE_ID" + "='" + data.getStoreId() + "'", null);
        ContentValues values = new ContentValues();

        try {

            values.put(CommonString1.KEY_STORE_ID, data.getStoreId());
            values.put(CommonString1.KEY_USER_ID, data.getUserId());
            values.put(CommonString1.KEY_IN_TIME, data.getInTime());
            values.put(CommonString1.KEY_OUT_TIME, data.getOutTime());
            values.put(CommonString1.KEY_VISIT_DATE, data.getVisitDate());
            values.put(CommonString1.KEY_CHANNEL_CD, data.getChannel_cd());
            values.put(CommonString1.KEY_LATITUDE, data.getLatitude());
            values.put(CommonString1.KEY_LONGITUDE, data.getLongitude());
            values.put(CommonString1.KEY_REASON_ID, data.getReasonid());
            values.put(CommonString1.KEY_REASON, data.getReason());
            values.put(CommonString1.KEY_SUB_REASON_ID, data.getSub_reasonId());
            values.put(CommonString1.KEY_INFORM_TO, data.getInformto());
            values.put(CommonString1.KEY_COVERAGE_STATUS, data.getStatus());
            values.put(CommonString1.KEY_IMAGE, data.getImage());
            values.put(CommonString1.KEY_COVERAGE_REMARK, data.getRemark());
            values.put(CommonString1.KEY_GEO_TAG, data.getGEO_TAG());

            //values.put(CommonString1.KEY_other, data.getOtherreson());

            return db.insert(CommonString1.TABLE_PREVIOUS_COVERAGE_DATA, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ", ex.toString());
        }
        return 0;
    }

    public void deletePreviousData(String storeid) {

        try {
            db.delete(CommonString1.TABLE_PREVIOUS_COVERAGE_DATA,
                    CommonString1.KEY_STORE_ID + "='" + storeid + "'", null);
            db.delete("JOURNEY_PLAN_PREV",
                    CommonString1.KEY_STORE_CD + "='" + storeid + "'", null);
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }

    public long insertMappingPosmData(MappingPosmGetterSetter data) {

        db.delete("MAPPING_POSM_STOREWISE", null, null);
        ContentValues values = new ContentValues();
        long id = 0;
        try {

            for (int i = 0; i < data.getSTORE_CD().size(); i++) {

                values.put("STORE_CD", Integer.parseInt(data.getSTORE_CD().get(i)));
                values.put("STORETYPE_CD", Integer.parseInt(data.getSTORETYPE_CD().get(i)));
                values.put("POSM_CD", Integer.parseInt(data.getPOSM_CD().get(i)));

                id = db.insert("MAPPING_POSM_STOREWISE", null, values);

            }
        } catch (Exception ex) {
            Log.d("Database Exception while insert MAPPING_POSM Data ",
                    ex.toString());
            return 0;
        }
        return id;
    }

    public long insertEmpSalaryData(EmpSalaryGetterSetter data) {

        db.delete("EMP_SALARY", null, null);
        ContentValues values = new ContentValues();
        long id = 0;
        try {

            values.put("SALARY_MONTH", data.getSALARY_MONTH());
            values.put("SALARY_YEAR", data.getSALARY_YEAR());
            values.put("ECODE", data.getECODE());
            values.put("EMP_NAME", data.getEMP_NAME());
            values.put("PAYMENT_MODE", data.getPAYMENT_MODE());
            values.put("PRESENT_DAYS", data.getPRESENT_DAYS());
            values.put("INCENTIVE", data.getINCENTIVE());
            values.put("NATIONAL_H", data.getNATIONAL_H());
            values.put("TOTAL_EARNING", data.getTOTAL_EARNING());
            values.put("PF", data.getPF());
            values.put("ESI", data.getESI());
            values.put("PT", data.getPT());
            values.put("LWF", data.getLWF());
            values.put("MIS_DEDUCTION", data.getMIS_DEDUCTION());
            values.put("TDS", data.getTDS());
            values.put("TAKE_HOME", data.getTAKE_HOME());

            id = db.insert("EMP_SALARY", null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while insert EMP_SALARY Data ",
                    ex.toString());
            return 0;
        }
        return id;
    }

    public ArrayList<POSMDATAGetterSetter> getPOSMAtGTData(String store_cd) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<POSMDATAGetterSetter> list = new ArrayList<POSMDATAGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM POSM_DATA_GT WHERE STORE_CD ='" + store_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    POSMDATAGetterSetter sb = new POSMDATAGetterSetter();


                    sb.setPOSM(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM")));

                    sb.setEdText(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("EdText")));

                    sb.setPOSM_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("POSM_CD")));


                    sb.setImage_Url(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("Image_URL")));

                    sb.setID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("KEY_ID")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching opening stock!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("Fetching Asset brand---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public void insertNonPosmReasonData(NonPOSMReasonGetterSetter data) {

        db.delete("NON_POSM_REASON", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getPREASON_CD().size(); i++) {

                values.put("PREASON_CD", Integer.parseInt(data.getPREASON_CD().get(i)));
                values.put("PREASON", data.getPREASON().get(i));

                db.insert("NON_POSM_REASON", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while NON_POSM_REASON Data ",
                    ex.toString());
        }

    }


    public ArrayList<NonPOSMReasonGetterSetter> getNonPosmReasonData() {
        //Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<NonPOSMReasonGetterSetter> list1 = new ArrayList<NonPOSMReasonGetterSetter>();
        Cursor dbcursor = null;
        try {
            //dbcursor = db.rawQuery("SELECT  distinct * from POSM_MASTER ORDER BY POSM_CD", null);
            dbcursor = db.rawQuery("Select -1 as PREASON_CD,'Select' as PREASON union Select PREASON_CD,PREASON from NON_POSM_REASON", null);

            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    NonPOSMReasonGetterSetter crowndata = new NonPOSMReasonGetterSetter();

                    crowndata.setPREASON_CD(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PREASON_CD")));

                    crowndata.setPREASON(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("PREASON")));

                    list1.add(crowndata);
                    dbcursor.moveToNext();

                }

                dbcursor.close();
                return list1;
            }

        } catch (Exception e) {

            return list1;
        }


        return list1;
    }


    public ArrayList<EmpSalaryGetterSetter> getEmpSalaryData() {
        ArrayList<EmpSalaryGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM " + CommonString.TABLE_EMP_SALARY + "", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    EmpSalaryGetterSetter mp = new EmpSalaryGetterSetter();
                    mp.setSALARY_MONTH(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SALARY_MONTH")));
                    mp.setSALARY_YEAR(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SALARY_YEAR")));
                    mp.setECODE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ECODE")));
                    mp.setEMP_NAME(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMP_NAME")));
                    mp.setPAYMENT_MODE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PAYMENT_MODE")));
                    mp.setPRESENT_DAYS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PRESENT_DAYS")));
                    mp.setINCENTIVE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("INCENTIVE")));
                    mp.setNATIONAL_H(dbcursor.getString(dbcursor.getColumnIndexOrThrow("NATIONAL_H")));
                    mp.setTOTAL_EARNING(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TOTAL_EARNING")));
                    mp.setPF(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PF")));
                    mp.setESI(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ESI")));
                    mp.setPT(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PT")));
                    mp.setLWF(dbcursor.getString(dbcursor.getColumnIndexOrThrow("LWF")));
                    mp.setMIS_DEDUCTION(dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIS_DEDUCTION")));
                    mp.setTDS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TDS")));
                    mp.setTAKE_HOME(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TAKE_HOME")));

                    list.add(mp);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Promotion",
                    e.toString());
            return list;
        }

        Log.d("fetching Mapping Promotion--->Stop<--",
                "-------------------");
        return list;
    }

    public ArrayList<CoverageBean> getCoveragePreviousData(String visitdate) {

        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;

        try {
            dbcursor = db
                    .rawQuery(
                            "SELECT * FROM COVERAGE_DATA " + "where "
                                    + CommonString1.KEY_VISIT_DATE + "<>'" + visitdate + "'", null);
            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();

                    sb.setStoreId(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_STORE_ID)));
                    sb.setUserId((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_USER_ID))));
                    sb.setInTime(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_OUT_TIME)))));
                    sb.setVisitDate((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_VISIT_DATE))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_LONGITUDE)))));
                    sb.setStatus((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_COVERAGE_STATUS))))));
                    sb.setImage((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_IMAGE))))));

                    sb.setReason((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_REASON))))));
                    sb.setReasonid((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_REASON_ID))))));
                    sb.setSub_reasonId((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_SUB_REASON_ID))))));
                    sb.setInformto((((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_INFORM_TO))))));
                    sb.setMID(Integer.parseInt(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_ID))))));

                    if (dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString1.KEY_COVERAGE_REMARK)) == null) {
                        sb.setRemark("");
                    } else {
                        sb.setRemark((((dbcursor.getString(dbcursor
                                .getColumnIndexOrThrow(CommonString1.KEY_COVERAGE_REMARK))))));
                    }
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public ArrayList<KYCMasterGetterSetter> getKycMasterList() {

        ArrayList<KYCMasterGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            //dbcursor = db.rawQuery("select 0 as KYC_ID,'Select' as KYC union all SELECT KYC_ID,KYC from KYC_MASTER", null);
            dbcursor = db.rawQuery("select 0 as KYC_ID,'Select' as KYC UNION select 1 as KYC_ID,'registered' as KYC UNION select 2 as KYC_ID,'unregistered' as KYC", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    KYCMasterGetterSetter sb = new KYCMasterGetterSetter();

                    sb.setKycId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KYC_ID"))));
                    sb.setKyc(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KYC")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public long insertStoreProfileData(StoreProfileGetterSetter data) {

        db.delete(CommonString1.TABLE_STORE_PROFILE, CommonString1.KEY_STORE_CD + " = " + data.getStoreid(), null);
        ContentValues values = new ContentValues();
        try {

            values.put(CommonString1.KEY_STORE_CD, data.getStoreid());
            values.put(CommonString1.KEY_RETAILER_NAME, data.getRetailerName());
            values.put(CommonString1.KEY_CONTACT, data.getContactNumber());
            values.put(CommonString1.KEY_CONTACT_2, data.getContactNumber2());
            values.put(CommonString1.KEY_POSTAL_ADDRESS, data.getPostalAddress());
            values.put(CommonString1.KEY_CITY_CD, data.getCity_cd());
            values.put(CommonString1.KEY_STATE_CD, data.getState_cd());
            values.put(CommonString1.KEY_STORE_PROFILE_IMG, data.getAddressImg());
            values.put(CommonString1.KEY_KYC_ID, data.getKycId());
            values.put(CommonString1.KEY_GST_NO, data.getGstNo());
            values.put(CommonString1.KEY_GST_IMG, data.getGstImg());

            return db.insert(CommonString1.TABLE_STORE_PROFILE, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while insertStoreProfileData ", ex.toString());
            return 0;
        }

    }

    public StoreProfileGetterSetter getStoreProfileData(String storeId) {

        Cursor dbcursor = null;
        StoreProfileGetterSetter sb = null;
        try {
            dbcursor = db.rawQuery("Select * from " + CommonString1.TABLE_STORE_PROFILE + " where " + CommonString1.KEY_STORE_CD + " = " + storeId + "", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    sb = new StoreProfileGetterSetter();

                    sb.setRetailerName(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_RETAILER_NAME)));
                    sb.setContactNumber(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_CONTACT)));
                    sb.setContactNumber2(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_CONTACT_2)));
                    sb.setPostalAddress(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_POSTAL_ADDRESS)));
                    sb.setCity_cd(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_CITY_CD)));
                    sb.setState_cd(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_STATE_CD)));
                    sb.setAddressImg(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_STORE_PROFILE_IMG)));
                    sb.setKycId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_KYC_ID)));
                    sb.setGstNo(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_GST_NO)));
                    sb.setGstImg(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString1.KEY_GST_IMG)));

                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return sb;
            }
        } catch (Exception e) {
            return sb;
        }
        return sb;
    }

    public ArrayList<CityMasterGetterSetter> getCityMasterList(int state_cd) {

        ArrayList<CityMasterGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("select 0 as CITY_CD,'Select' as CITY union SELECT CITY_CD,CITY from CITY_MASTER WHERE STATE_CD = " + state_cd + "", null);
            //dbcursor = db.rawQuery("select 0 as CITY_ID,'Select' as CITY UNION select 1 as CITY_ID,'CITY_1' as CITY UNION select 2 as CITY_ID,'CITY_2' as CITY", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CityMasterGetterSetter sb = new CityMasterGetterSetter();

                    sb.setCity_cd(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CITY_CD"))));
                    sb.setCity(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CITY")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public ArrayList<StateMasterGetterSetter> getStateMasterList() {
        ArrayList<StateMasterGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("select 0 as STATE_CD,'Select' as STATE union all SELECT STATE_CD,STATE from STATE_MASTER", null);
            //dbcursor = db.rawQuery("select 0 as STATE_CD,'Select' as STATE UNION select 1 as STATE_CD,'STATE_1' as STATE UNION select 2 as STATE_CD,'STATE_2' as STATE", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StateMasterGetterSetter sb = new StateMasterGetterSetter();

                    sb.setState_cd(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STATE_CD"))));
                    sb.setState(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STATE")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public void insertStateMasterData(StateMasterGetterSetter data) {
        db.delete("STATE_MASTER", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getState_cd_list().size(); i++) {

                values.put("STATE_CD", Integer.parseInt(data.getState_cd_list().get(i)));
                values.put("STATE", data.getState_list().get(i));

                db.insert("STATE_MASTER", null, values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert State Master Data ", ex.toString());
        }
    }

    public void insertCityMasterData(CityMasterGetterSetter data) {
        db.delete("CITY_MASTER", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getCity_cd_list().size(); i++) {

                values.put("CITY_CD", Integer.parseInt(data.getCity_cd_list().get(i)));
                values.put("CITY", data.getCity_list().get(i));
                values.put("STATE_CD", Integer.parseInt(data.getState_cd_list().get(i)));

                db.insert("CITY_MASTER", null, values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert City Master Data ", ex.toString());
        }
    }


}
