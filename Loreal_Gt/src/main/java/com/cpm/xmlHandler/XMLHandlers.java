package com.cpm.xmlHandler;

import com.cpm.GetterSetter.CityMasterGetterSetter;
import com.cpm.GetterSetter.DisplayMasterGetterSetter;
import com.cpm.GetterSetter.EmpSalaryGetterSetter;
import com.cpm.GetterSetter.ImageXMLGetterSetter;
import com.cpm.GetterSetter.JourneyPlanPreviousGetterSetter;
import com.cpm.GetterSetter.MappingPaidVisibilityGetterSetter;
import com.cpm.GetterSetter.MappingPosmGetterSetter;
import com.cpm.GetterSetter.MappingPromotion2GetterSetter;
import com.cpm.GetterSetter.NonPOSMReasonGetterSetter;
import com.cpm.GetterSetter.NonPromotionReasonGetterSetter;
import com.cpm.GetterSetter.NoticeurlGetterSetter;
import com.cpm.GetterSetter.ServerTimeGetterSetter;
import com.cpm.GetterSetter.StateMasterGetterSetter;
import com.cpm.xmlGetterSetter.AssetChecklistGetterSetter;
import com.cpm.xmlGetterSetter.AssetMasterGetterSetter;
import com.cpm.xmlGetterSetter.BrandGetterSetter;
import com.cpm.xmlGetterSetter.COMPANY_MASTERGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.ColdroomClosingGetterSetter;
import com.cpm.xmlGetterSetter.ComplianceByMappingGetterSetter;
import com.cpm.xmlGetterSetter.ComplianceGetterSetter;
import com.cpm.xmlGetterSetter.DeepFreezerGetterSetter;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.LoginGetterSetter;
import com.cpm.xmlGetterSetter.MappingAssetChecklistGetterSetter;
import com.cpm.xmlGetterSetter.MappingAssetGetterSetter;
import com.cpm.xmlGetterSetter.MappingAvailabilityGetterSetter;
import com.cpm.xmlGetterSetter.MappingStatusWindows;
import com.cpm.xmlGetterSetter.MappingStockGetterSetter;
import com.cpm.xmlGetterSetter.MappingWindowChecklistGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingReasonGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingSubReasonGetterSetter;
import com.cpm.xmlGetterSetter.NonWrkingMasterGetterSetter;
import com.cpm.xmlGetterSetter.POSMDATAGetterSetter;
import com.cpm.xmlGetterSetter.PerformanceGetterSetter;
import com.cpm.xmlGetterSetter.PosmGetterSetter;
import com.cpm.xmlGetterSetter.PromotionalMasterGettersetter;
import com.cpm.xmlGetterSetter.QuestionGetterSetter;
import com.cpm.xmlGetterSetter.SkuMappingGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;
import com.cpm.xmlGetterSetter.SubCategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.WindowChecklistAnswerGetterSetter;
import com.cpm.xmlGetterSetter.WindowNonReasonGetterSetter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class XMLHandlers {

    // LOGIN XML HANDLER
    public static LoginGetterSetter loginXMLHandler(XmlPullParser xpp,
                                                    int eventType) {
        LoginGetterSetter lgs = new LoginGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("RESULT")) {
                        lgs.setResult(xpp.nextText());
                    }
                    if (xpp.getName().equals("APP_VERSION")) {
                        lgs.setVERSION(xpp.nextText());
                    }
                    if (xpp.getName().equals("APP_PATH")) {
                        lgs.setPATH(xpp.nextText());
                    }
                    if (xpp.getName().equals("CURRENTDATE")) {
                        lgs.setDATE(xpp.nextText());
                    }

                    if (xpp.getName().equals("RIGHTNAME")) {
                        lgs.setRIGHTNAME(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lgs;
    }

    // FAILURE XML HANDLER
    public static FailureGetterSetter failureXMLHandler(XmlPullParser xpp,
                                                        int eventType) {
        FailureGetterSetter failureGetterSetter = new FailureGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("STATUS")) {
                        failureGetterSetter.setStatus(xpp.nextText());
                    }
                    if (xpp.getName().equals("ERRORMSG")) {
                        failureGetterSetter.setErrorMsg(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return failureGetterSetter;
    }

    // JCP XML HANDLER
    public static JourneyPlanGetterSetter JCPXMLHandler(XmlPullParser xpp, int eventType) {
        JourneyPlanGetterSetter jcpGetterSetter = new JourneyPlanGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("META_DATA")) {
                        jcpGetterSetter.setTable_journey_plan(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORE_CD")) {
                        jcpGetterSetter.setStore_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("EMP_CD")) {
                        jcpGetterSetter.setEmp_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("VISIT_DATE")) {
                        jcpGetterSetter.setVISIT_DATE(xpp.nextText());
                    }
                    if (xpp.getName().equals("KEYACCOUNT")) {
                        jcpGetterSetter.setKey_account(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORENAME")) {
                        jcpGetterSetter.setStore_name(xpp.nextText());
                    }
                    if (xpp.getName().equals("CITY")) {
                        jcpGetterSetter.setCity(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORETYPE")) {
                        jcpGetterSetter.setStore_type(xpp.nextText());
                    }
                    if (xpp.getName().equals("UPLOAD_STATUS")) {
                        jcpGetterSetter.setUploadStatus(xpp.nextText());
                    }
                    /*if (xpp.getName().equals("CATEGORY_TYPE")) {
                        jcpGetterSetter.setCategory_type(xpp.nextText());
					}*/
                    if (xpp.getName().equals("CHECKOUT_STATUS")) {
                        jcpGetterSetter.setCheckOutStatus(xpp.nextText());
                    }

                    if (xpp.getName().equals("STATE_CD")) {
                        jcpGetterSetter.setSTATE_CD(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORETYPE_CD")) {
                        jcpGetterSetter.setSTORETYPE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("GEO_TAG")) {
                        jcpGetterSetter.setGEO_TAG(xpp.nextText());
                    }
                    /*if (xpp.getName().equals("FIRST_VISIT")) {
                        jcpGetterSetter.setFIRST_VISIT(xpp.nextText());
					}*/
                    if (xpp.getName().equals("CHANNEL_CD")) {
                        jcpGetterSetter.setCHANNEL_CD(xpp.nextText());
                    }

                    if (xpp.getName().equals("LATTITUDE")) {
                        jcpGetterSetter.setLATTITUDE(xpp.nextText());
                    }
                    if (xpp.getName().equals("LONGITUDE")) {
                        jcpGetterSetter.setLONGITUDE(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE")) {
                        jcpGetterSetter.setSTORE(xpp.nextText());
                    }
                    if (xpp.getName().equals("ADDRESS")) {
                        jcpGetterSetter.setADDRESS(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE_CONTACT_NO")) {
                        jcpGetterSetter.setSTORE_CONTACT_NO(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE_CONTACT_NO2")) {
                        jcpGetterSetter.setSTORE_CONTACT_NO2(xpp.nextText());
                    }
                    if (xpp.getName().equals("CITY_CD")) {
                        jcpGetterSetter.setCITY_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE_PROFILE_IMAGE")) {
                        jcpGetterSetter.setSTORE_PROFILE_IMAGE(xpp.nextText());
                    }
                    if (xpp.getName().equals("GSTIN_NO")) {
                        jcpGetterSetter.setGSTIN_NO(xpp.nextText());
                    }
                    if (xpp.getName().equals("GSTIN_IMAGE")) {
                        jcpGetterSetter.setGSTIN_IMAGE(xpp.nextText());
                    }
                    if (xpp.getName().equals("KYC_ID")) {
                        jcpGetterSetter.setKYC_ID(xpp.nextText());
                    }
                    if (xpp.getName().equals("RETAILER_NAME")) {
                        jcpGetterSetter.setRETAILER_NAME(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jcpGetterSetter;
    }


    // JCP XML HANDLER
    public static JourneyPlanPreviousGetterSetter JCPPreviousXML(XmlPullParser xpp, int eventType) {
        JourneyPlanPreviousGetterSetter jcpGetterSetter = new JourneyPlanPreviousGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("META_DATA")) {
                        jcpGetterSetter.setTable_JOURNEY_PLAN_PREV(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORE_CD")) {
                        jcpGetterSetter.setSTORE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("VISITDATE")) {
                        jcpGetterSetter.setVISITDATE(xpp.nextText());
                    }
                    if (xpp.getName().equals("KEYACCOUNT")) {
                        jcpGetterSetter.setKEYACCOUNT(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORENAME")) {
                        jcpGetterSetter.setSTORENAME(xpp.nextText());
                    }
                    if (xpp.getName().equals("ADDRESS")) {
                        jcpGetterSetter.setADDRESS(xpp.nextText());
                    }
                    if (xpp.getName().equals("CHANNEL_CD")) {
                        jcpGetterSetter.setCHANNEL_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("CITY")) {
                        jcpGetterSetter.setCITY(xpp.nextText());
                    }
                    if (xpp.getName().equals("COVERAGE")) {
                        jcpGetterSetter.setCOVERAGE(xpp.nextText());
                    }
                    if (xpp.getName().equals("REASON")) {
                        jcpGetterSetter.setREASON(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jcpGetterSetter;
    }


    //Question Data

    public static QuestionGetterSetter QuestionXMLHandler(XmlPullParser xpp, int eventType) {
        QuestionGetterSetter qnsGetterSetter = new QuestionGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("META_DATA")) {
                        qnsGetterSetter.setTable_question_today(xpp.nextText());
                    }

                    if (xpp.getName().equals("QUESTION_ID")) {
                        qnsGetterSetter.setQuestion_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("QUESTION")) {
                        qnsGetterSetter.setQuestion(xpp.nextText());
                    }
                    if (xpp.getName().equals("ANSWER_ID")) {
                        qnsGetterSetter.setAnswer_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("ANSWER")) {
                        qnsGetterSetter.setAnswer(xpp.nextText());
                    }
                    if (xpp.getName().equals("RIGHT_ANSWER")) {
                        qnsGetterSetter.setRight_answer(xpp.nextText());
                    }
                    if (xpp.getName().equals("STATUS")) {
                        qnsGetterSetter.setStatus(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return qnsGetterSetter;
    }

    public static JCPGetterSetter LayoutmasterHandler(XmlPullParser xpp,
                                                      int eventType) {
        JCPGetterSetter pgs = new JCPGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("STORE_LAYOUT")) {
                        pgs.setStore_layout(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pgs;
    }

    public static JCPGetterSetter SizemasterHandler(XmlPullParser xpp,
                                                    int eventType) {
        JCPGetterSetter pgs = new JCPGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("STORE_SIZE")) {
                        pgs.setStore_size(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pgs;
    }

    public static ComplianceByMappingGetterSetter PlanogramMapping(
            XmlPullParser xpp, int eventType) {
        ComplianceByMappingGetterSetter Mappingcompliance = new ComplianceByMappingGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("PROMOTION_CD")) {
                        Mappingcompliance.setMappingCompliancePromotion(xpp
                                .nextText());
                    }
                    if (xpp.getName().equals("REGION_CD")) {
                        Mappingcompliance.setRegion_id(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORETYPE_CD")) {
                        Mappingcompliance.setStore_typeid(xpp.nextText());
                    }

                    if (xpp.getName().equals("CATEGORY_CD")) {
                        Mappingcompliance.setCATEGORY_ID(xpp.nextText());
                    }

                    if (xpp.getName().equals("BRAND_CATEGORY_CD")) {
                        Mappingcompliance.setBRAND_CATEGORY_ID(xpp.nextText());
                    }

                    if (xpp.getName().equals("PLANOGRAM_IMAGE")) {
                        Mappingcompliance.setPlanogram_image(xpp.nextText());
                    }
                    if (xpp.getName().equals("IMAGE_PATH")) {
                        Mappingcompliance.setPath(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Mappingcompliance;

    }

    public static PromotionalMasterGettersetter PromotionalmasterHandler(
            XmlPullParser xpp, int eventType) {
        PromotionalMasterGettersetter pgs = new PromotionalMasterGettersetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("PROMOTION_CD")) {
                        pgs.setpromotionid(xpp.nextText());
                    }
                    if (xpp.getName().equals("PROMOTION")) {
                        pgs.setpromotionname(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pgs;
    }

    public static SkuMasterGetterSetter SkuMaster(XmlPullParser xpp,
                                                  int eventType) {
        SkuMasterGetterSetter skumaster = new SkuMasterGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("SKU_CD")) {
                        skumaster.setSku_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU")) {
                        skumaster.setSku(xpp.nextText());
                    }
                    if (xpp.getName().equals("BRAND_CD")) {
                        skumaster.setBrand_cd(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return skumaster;
    }

    // non working reason handler

    public static NonWrkingMasterGetterSetter nonwrking(XmlPullParser xpp,
                                                        int eventType) {
        NonWrkingMasterGetterSetter non_wrking_reason = new NonWrkingMasterGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("REASON_ID")) {
                        non_wrking_reason
                                .setNon_WorkingReasonId(xpp.nextText());
                    }
                    if (xpp.getName().equals("REASON")) {
                        non_wrking_reason.setNon_WorkingReasonName(xpp
                                .nextText());
                    }
                    if (xpp.getName().equals("IMAGE_ALLOW")) {
                        non_wrking_reason.setNon_WorkingReasonValid(xpp
                                .nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return non_wrking_reason;

    }


    public static NonWrkingMasterGetterSetter nonwrkingSubReason(XmlPullParser xpp,
                                                                 int eventType) {
        NonWrkingMasterGetterSetter non_wrking_reason = new NonWrkingMasterGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("SUB_REASON_ID")) {
                        non_wrking_reason
                                .setSubReason_id(xpp.nextText());
                    }
                    if (xpp.getName().equals("REASON_ID")) {
                        non_wrking_reason.setNon_WorkingReasonId(xpp
                                .nextText());
                    }
                    if (xpp.getName().equals("SUB_REASON")) {
                        non_wrking_reason.setSub_reason(xpp
                                .nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return non_wrking_reason;

    }

    public static SkuMappingGetterSetter sku_mapping(XmlPullParser xpp,
                                                     int eventType) {
        SkuMappingGetterSetter non_wrking_reason = new SkuMappingGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("SKU_CD")) {
                        non_wrking_reason.setSku_id(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORETYPE_CD")) {
                        non_wrking_reason.setStoretype_id(xpp.nextText());
                    }
                    if (xpp.getName().equals("REGION_CD")) {
                        non_wrking_reason.setREGION_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU_SEQUENCE")) {
                        non_wrking_reason.setSKU_SEQUENCE(xpp.nextText());
                    }
                    if (xpp.getName().equals("CORE_SKU")) {
                        non_wrking_reason.setCore_sku(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return non_wrking_reason;

    }

    // complianceXML handler

    public static ComplianceGetterSetter ComplianceXMLHandler(
            XmlPullParser xpp, int eventType) {
        ComplianceGetterSetter compliance = new ComplianceGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("COMPLIANCE_CD")) {
                        compliance.setComplianceID(xpp.nextText());
                    }
                    if (xpp.getName().equals("COMPLIANCE")) {
                        compliance.setComplianceName(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return compliance;

    }

    // XML handler for mapping compliance

    public static ComplianceByMappingGetterSetter ComplianceByMapping(
            XmlPullParser xpp, int eventType) {
        ComplianceByMappingGetterSetter Mappingcompliance = new ComplianceByMappingGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("COMPLIANCE_CD")) {
                        Mappingcompliance
                                .setMappingComplianceId(xpp.nextText());
                    }
                    if (xpp.getName().equals("PROMOTION_CD")) {
                        Mappingcompliance.setMappingCompliancePromotion(xpp
                                .nextText());
                    }
                    if (xpp.getName().equals("REGION_CD")) {
                        Mappingcompliance.setRegion_id(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORETYPE_CD")) {
                        Mappingcompliance.setStore_typeid(xpp.nextText());
                    }

                    if (xpp.getName().equals("CATEGORY_CD")) {
                        Mappingcompliance.setCATEGORY_ID(xpp.nextText());
                    }

                    if (xpp.getName().equals("BRAND_CATEGORY_CD")) {
                        Mappingcompliance.setBRAND_CATEGORY_ID(xpp.nextText());
                    }
                    if (xpp.getName().equals("BRAND_CATEGORY")) {
                        Mappingcompliance.setBRAND_CATEGORY(xpp.nextText());
                    }
                    if (xpp.getName().equals("MONTH_HERO")) {
                        Mappingcompliance.setMONTH_HERO(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Mappingcompliance;

    }

    public static ComplianceByMappingGetterSetter ComplianceByMappingSpecial(
            XmlPullParser xpp, int eventType) {
        ComplianceByMappingGetterSetter Mappingcompliance = new ComplianceByMappingGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("COMPLIANCE_CD")) {
                        Mappingcompliance
                                .setMappingComplianceId(xpp.nextText());
                    }
                    if (xpp.getName().equals("PROMOTION_CD")) {
                        Mappingcompliance.setMappingCompliancePromotion(xpp
                                .nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Mappingcompliance;

    }

    // Posm Master
    // XML handler for mapping compliance

    public static PosmGetterSetter Posm(XmlPullParser xpp, int eventType) {
        PosmGetterSetter Mappingcompliance = new PosmGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("POSM_CD")) {
                        Mappingcompliance.setPosmId(xpp.nextText());
                    }
                    if (xpp.getName().equals("POSM")) {
                        Mappingcompliance.setPosm(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Mappingcompliance;

    }

    public static JCPGetterSetter GeoTagXMLHandler(XmlPullParser xpp,
                                                   int eventType) {
        JCPGetterSetter jcpGetterSetter = new JCPGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("STORE_CD")) {
                        jcpGetterSetter.setStoreid(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORENAME")) {
                        jcpGetterSetter.setStorename(xpp.nextText());
                    }
                    if (xpp.getName().equals("KEYACCOUNT")) {
                        jcpGetterSetter.setKey_account(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORETYPE")) {
                        jcpGetterSetter.setStore_typeid(xpp.nextText());
                    }
                    if (xpp.getName().equals("CATEGORY")) {
                        jcpGetterSetter.setCATEGORY_ID(xpp.nextText());
                    }
                    if (xpp.getName().equals("GEO_TAG_STATUS")) {
                        jcpGetterSetter.setStatus(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jcpGetterSetter;
    }


    public static SkuMasterGetterSetter storeListXML(XmlPullParser xpp,
                                                     int eventType) {
        SkuMasterGetterSetter skumaster = new SkuMasterGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        skumaster.setSku_master_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU_CD")) {
                        skumaster.setSku_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU")) {
                        skumaster.setSku(xpp.nextText());
                    }
                    if (xpp.getName().equals("BRAND_CD")) {
                        skumaster.setBrand_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU_SEQUENCE")) {
                        skumaster.setSku_sequence(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return skumaster;
    }


    public static MappingStatusWindows mappingavailXML(XmlPullParser xpp,
                                                       int eventType) {
        MappingStatusWindows mappingavail = new MappingStatusWindows();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        mappingavail.setMapping_status_windows(xpp.nextText());
                    }
                    if (xpp.getName().equals("WINDOW_CD")) {
                        mappingavail.setWINDOW_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("WINDOW")) {
                        mappingavail.setWINDOW(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU_HOLD")) {
                        mappingavail.setSKU_HOLD(xpp.nextText());
                    }
                    if (xpp.getName().equals("PLANOGRAM_IMAGE")) {
                        mappingavail.setPLANOGRAM_IMAGE(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mappingavail;
    }


    public static MappingAvailabilityGetterSetter mappingAvailabiltylXML(XmlPullParser xpp,
                                                                         int eventType) {
        MappingAvailabilityGetterSetter mappingavail = new MappingAvailabilityGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        mappingavail.setMapping_availability_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("STATE_CD")) {
                        mappingavail.setSTATE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORETYPE_CD")) {
                        mappingavail.setSTORETYPE_CD(xpp.nextText());

                    }

                    if (xpp.getName().equals("SKU_CD")) {
                        mappingavail.setSku_cd(xpp.nextText());

                    }
                    if (xpp.getName().equals("FOCUS")) {
                        mappingavail.setFOCUS(xpp.nextText());

                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mappingavail;
    }

    //MAPPING_PAID_VISIBILITY data
    public static MappingPaidVisibilityGetterSetter mappingPaidVisibilityXML(XmlPullParser xpp,
                                                                             int eventType) {
        MappingPaidVisibilityGetterSetter mappingavail = new MappingPaidVisibilityGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        mappingavail.setTable_MappingPaidVisibility(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE_CD")) {
                        mappingavail.setSTORE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("DISPLAY_CD")) {
                        mappingavail.setDISPLAY_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("BRAND_CD")) {
                        mappingavail.setBRAND_CD(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mappingavail;
    }


    public static POSMDATAGetterSetter mappingpromotXML(XmlPullParser xpp,
                                                        int eventType) {
        POSMDATAGetterSetter mappingpromo = new POSMDATAGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        mappingpromo.setMapping_POSM_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("POSM_CD")) {
                        mappingpromo.setPOSM_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("POSM")) {
                        mappingpromo.setPOSM(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mappingpromo;
    }

    public static MappingPosmGetterSetter mappingPOSMXML(XmlPullParser xpp,
                                                         int eventType) {
        MappingPosmGetterSetter mappingpromo = new MappingPosmGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        mappingpromo.setTable_MappingPosm(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE_CD")) {
                        mappingpromo.setSTORE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORETYPE_CD")) {
                        mappingpromo.setSTORETYPE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("POSM_CD")) {
                        mappingpromo.setPOSM_CD(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mappingpromo;
    }


    public static EmpSalaryGetterSetter empSalaryXML(XmlPullParser xpp,
                                                     int eventType) {
        EmpSalaryGetterSetter mappingpromo = new EmpSalaryGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        mappingpromo.setTable_EMP_SALARY(xpp.nextText());
                    }
                    if (xpp.getName().equals("SALARY_MONTH")) {
                        mappingpromo.setSALARY_MONTH(xpp.nextText());
                    }
                    if (xpp.getName().equals("SALARY_YEAR")) {
                        mappingpromo.setSALARY_YEAR(xpp.nextText());
                    }
                    if (xpp.getName().equals("ECODE")) {
                        mappingpromo.setECODE(xpp.nextText());
                    }
                    if (xpp.getName().equals("EMP_NAME")) {
                        mappingpromo.setEMP_NAME(xpp.nextText());
                    }
                    if (xpp.getName().equals("PAYMENT_MODE")) {
                        mappingpromo.setPAYMENT_MODE(xpp.nextText());
                    }
                    if (xpp.getName().equals("PRESENT_DAYS")) {
                        mappingpromo.setPRESENT_DAYS(xpp.nextText());
                    }
                    if (xpp.getName().equals("INCENTIVE")) {
                        mappingpromo.setINCENTIVE(xpp.nextText());
                    }
                    if (xpp.getName().equals("NATIONAL_H")) {
                        mappingpromo.setNATIONAL_H(xpp.nextText());
                    }
                    if (xpp.getName().equals("TOTAL_EARNING")) {
                        mappingpromo.setTOTAL_EARNING(xpp.nextText());
                    }
                    if (xpp.getName().equals("PF")) {
                        mappingpromo.setPF(xpp.nextText());
                    }
                    if (xpp.getName().equals("ESI")) {
                        mappingpromo.setESI(xpp.nextText());
                    }
                    if (xpp.getName().equals("PT")) {
                        mappingpromo.setPT(xpp.nextText());
                    }
                    if (xpp.getName().equals("LWF")) {
                        mappingpromo.setLWF(xpp.nextText());
                    }
                    if (xpp.getName().equals("MIS_DEDUCTION")) {
                        mappingpromo.setMIS_DEDUCTION(xpp.nextText());
                    }
                    if (xpp.getName().equals("TDS")) {
                        mappingpromo.setTDS(xpp.nextText());
                    }
                    if (xpp.getName().equals("TAKE_HOME")) {
                        mappingpromo.setTAKE_HOME(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mappingpromo;
    }


    public static MappingAssetGetterSetter mappingassetXML(XmlPullParser xpp,
                                                           int eventType) {
        MappingAssetGetterSetter mappingasset = new MappingAssetGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        mappingasset.setMapping_asset_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("CHECKLIST_CD")) {
                        mappingasset.setCHECKLIST_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("CHECKLIST")) {
                        mappingasset.setCHECKLIST(xpp.nextText());
                    }
                    if (xpp.getName().equals("ANSWER_TYPE")) {
                        mappingasset.setANSWER_TYPE(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mappingasset;
    }


    public static DeepFreezerGetterSetter deepfreezXML(XmlPullParser xpp,
                                                       int eventType) {
        DeepFreezerGetterSetter deepfreez = new DeepFreezerGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        deepfreez.setDeepfreeztable(xpp.nextText());
                    }
                    if (xpp.getName().equals("FID")) {
                        deepfreez.setFid(xpp.nextText());
                    }
                    if (xpp.getName().equals("DEEP_FREEZER")) {
                        deepfreez.setDeep_frrezer(xpp.nextText());
                    }
                    if (xpp.getName().equals("FREEZER_TYPE")) {
                        deepfreez.setType(xpp.nextText());
                    }
                    if (xpp.getName().equals("SEQUENCE")) {
                        deepfreez.setSequence(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return deepfreez;
    }


    public static AssetMasterGetterSetter assetMasterXML(XmlPullParser xpp,
                                                         int eventType) {
        AssetMasterGetterSetter assetmaster = new AssetMasterGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        assetmaster.setAsset_master_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("STATE_CD")) {
                        assetmaster.setSTATE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORETYPE_CD")) {
                        assetmaster.setSTORETYPE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("WINDOW_CD")) {
                        assetmaster.setWINDOW_CD(xpp.nextText());
                    }

                    if (xpp.getName().equals("BRAND_CD")) {
                        assetmaster.setBRAND_CD(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return assetmaster;
    }

    public static MappingWindowChecklistGetterSetter mappingWindowChecklistXML(XmlPullParser xpp,
                                                                               int eventType) {
        MappingWindowChecklistGetterSetter windowchecklist = new MappingWindowChecklistGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        windowchecklist.setTable_mapping_window_checklist(xpp.nextText());
                    }
                    if (xpp.getName().equals("WINDOW_CD")) {
                        windowchecklist.setWINDOW_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("CHECKLIST_CD")) {
                        windowchecklist.setCHECKLIST_CD(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return windowchecklist;
    }


    //WINDOW_CHECKLIST_ANSWER
    public static WindowChecklistAnswerGetterSetter WindowChecklistAnswerXML(XmlPullParser xpp,
                                                                             int eventType) {
        WindowChecklistAnswerGetterSetter windowchecklistans = new WindowChecklistAnswerGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        windowchecklistans.setTable_window_checklist_answer(xpp.nextText());
                    }
                    if (xpp.getName().equals("ANSWER_CD")) {
                        windowchecklistans.setANSWER_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("ANSWER")) {
                        windowchecklistans.setANSWER(xpp.nextText());
                    }
                    if (xpp.getName().equals("CHECKLIST_CD")) {
                        windowchecklistans.setCHECKLIST_CD(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return windowchecklistans;
    }


    public static COMPANY_MASTERGetterSetter comapnyMasterXML(XmlPullParser xpp,
                                                              int eventType) {
        COMPANY_MASTERGetterSetter assetmaster = new COMPANY_MASTERGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        assetmaster.setCompany_master_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("COMPANY_CD")) {
                        assetmaster.setCOMPANY_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("COMPANY")) {
                        assetmaster.setCOMPANY(xpp.nextText());
                    }

                    if (xpp.getName().equals("ISCOMPETITOR")) {
                        assetmaster.setISCOMPETITOR(xpp.nextText());
                    }


                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return assetmaster;
    }


    //Brand master
    public static BrandGetterSetter brandMasterXML(XmlPullParser xpp,
                                                   int eventType) {
        BrandGetterSetter brandmaster = new BrandGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        brandmaster.setBrand_master_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("BRAND_CD")) {
                        brandmaster.setBrand_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("BRAND")) {
                        brandmaster.setBrand(xpp.nextText());
                    }
                    if (xpp.getName().equals("BRAND_SEQUENCE")) {
                        brandmaster.setBrand_sequence(xpp.nextText());
                    }

                    if (xpp.getName().equals("COMPANY_CD")) {
                        brandmaster.setCompany_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("CATEGORY_CD")) {
                        brandmaster.setCategory_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("SUB_CATEGORY_CD")) {
                        brandmaster.setSUB_CATEGORY_CD(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return brandmaster;
    }


    //display master
    public static DisplayMasterGetterSetter displayMasterXML(XmlPullParser xpp,
                                                             int eventType) {
        DisplayMasterGetterSetter brandmaster = new DisplayMasterGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        brandmaster.setTable_DisplayMaster(xpp.nextText());
                    }
                    if (xpp.getName().equals("DISPLAY_CD")) {
                        brandmaster.setDISPLAY_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("DISPLAY")) {
                        brandmaster.setDISPLAY(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return brandmaster;
    }

    //display master
    public static MappingPromotion2GetterSetter mappingPromotionXML(XmlPullParser xpp,
                                                                    int eventType) {
        MappingPromotion2GetterSetter brandmaster = new MappingPromotion2GetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        brandmaster.setTable_MAPPING_PROMOTION(xpp.nextText());
                    }
                    if (xpp.getName().equals("ID")) {
                        brandmaster.setID(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE_CD")) {
                        brandmaster.setSTORE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("PROMO")) {
                        brandmaster.setPROMO(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return brandmaster;
    }

    //display master
    public static NonPromotionReasonGetterSetter nonPromotionReasonXML(XmlPullParser xpp,
                                                                       int eventType) {
        NonPromotionReasonGetterSetter brandmaster = new NonPromotionReasonGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        brandmaster.setTable_NonPromotionReason(xpp.nextText());
                    }
                    if (xpp.getName().equals("PREASON_CD")) {
                        brandmaster.setPREASON_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("PREASON")) {
                        brandmaster.setPREASON(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return brandmaster;
    }


    //Sub category master
    public static SubCategoryMasterGetterSetter subCategoryMasterXML(XmlPullParser xpp,
                                                                     int eventType) {
        SubCategoryMasterGetterSetter brandmaster = new SubCategoryMasterGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        brandmaster.setTable_SUB_CATEGORY_MASTER(xpp.nextText());
                    }
                    if (xpp.getName().equals("SUB_CATEGORY_CD")) {
                        brandmaster.setSUB_CATEGORY_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("SUB_CATEGORY")) {
                        brandmaster.setSUB_CATEGORY(xpp.nextText());
                    }

                    if (xpp.getName().equals("CATEGORY_CD")) {
                        brandmaster.setCATEGORY_CD(xpp.nextText());
                    }

                    if (xpp.getName().equals("SUB_CATEGORY_SEQUENCE")) {
                        brandmaster.setSUB_CATEGORY_SEQUENCE(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return brandmaster;
    }


    //MAPPING_STOCK
    public static MappingStockGetterSetter mappingStockXML(XmlPullParser xpp,
                                                           int eventType) {
        MappingStockGetterSetter brandmaster = new MappingStockGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        brandmaster.setTable_MAPPING_STOCK(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU_CD")) {
                        brandmaster.setSKU_CD(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return brandmaster;
    }

    //Asset Checklist
    public static AssetChecklistGetterSetter assetChecklistXMLHandler(XmlPullParser xpp,
                                                                      int eventType) {
        AssetChecklistGetterSetter assetChecklistGetterSetter = new AssetChecklistGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        assetChecklistGetterSetter.setAssetchecklist_insert_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("CHECKLIST_ID")) {
                        assetChecklistGetterSetter.setCHECKLIST_ID(xpp.nextText());
                    }
                    if (xpp.getName().equals("CHECKLIST")) {
                        assetChecklistGetterSetter.setCHECKLIST(xpp.nextText());
                    }
                    if (xpp.getName().equals("CHECKLIST_TYPE")) {
                        assetChecklistGetterSetter.setTYPE(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return assetChecklistGetterSetter;
    }

    //Mapping Asset Checklist
    public static MappingAssetChecklistGetterSetter mappingAssetChecklistXMLHandler(XmlPullParser xpp,
                                                                                    int eventType) {
        MappingAssetChecklistGetterSetter mappingAssetChecklistGetterSetter = new MappingAssetChecklistGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        mappingAssetChecklistGetterSetter.setMapping_asset_checklist_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("ASSET_CD")) {
                        mappingAssetChecklistGetterSetter.setAsset_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("CHECKLIST_ID")) {
                        mappingAssetChecklistGetterSetter.setCheck_list_id(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mappingAssetChecklistGetterSetter;
    }

    //Performance

    public static PerformanceGetterSetter performanceXML(XmlPullParser xpp,
                                                         int eventType) {
        PerformanceGetterSetter performanceGetterSetter = new PerformanceGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        performanceGetterSetter.setPerformance_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE_CD")) {
                        performanceGetterSetter.setStore_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("TARGET")) {
                        performanceGetterSetter.setMonthly_target(xpp.nextText());
                    }

                    if (xpp.getName().equals("SALE")) {
                        performanceGetterSetter.setMtd_sales(xpp.nextText());
                    }

                    if (xpp.getName().equals("ACH")) {
                        performanceGetterSetter.setAchievement(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return performanceGetterSetter;
    }


    //Closing Cold

    public static ColdroomClosingGetterSetter closincoldXML(XmlPullParser xpp,
                                                            int eventType) {
        ColdroomClosingGetterSetter closingcoldGetterSetter = new ColdroomClosingGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        closingcoldGetterSetter.setClosing_cold_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORE_CD")) {
                        closingcoldGetterSetter.setStore_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("SKU_CD")) {
                        closingcoldGetterSetter.setSku_cd(xpp.nextText());
                    }

                    if (xpp.getName().equals("CLOSING_STOCK_COLD")) {
                        closingcoldGetterSetter.setClosing_cold(xpp.nextText());
                    }


                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return closingcoldGetterSetter;
    }


    public static NonWorkingReasonGetterSetter nonWorkinReasonXML(XmlPullParser xpp,
                                                                  int eventType) {
        NonWorkingReasonGetterSetter nonworking = new NonWorkingReasonGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        nonworking.setNonworking_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("REASON_CD")) {
                        nonworking.setReason_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("REASON")) {
                        nonworking.setReason(xpp.nextText());
                    }
                    if (xpp.getName().equals("ENTRY_ALLOW")) {
                        nonworking.setEntry_allow(xpp.nextText());
                    }

                    if (xpp.getName().equals("IMAGE_ALLOW")) {
                        nonworking.setIMAGE_ALLOW(xpp.nextText());
                    }


                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nonworking;
    }

    //----------Non working Sub Reason
    public static NonWorkingSubReasonGetterSetter nonWorkinSubReasonXML(XmlPullParser xpp,
                                                                        int eventType) {
        NonWorkingSubReasonGetterSetter nonworking = new NonWorkingSubReasonGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        nonworking.setNonworkingsubreason_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("SUB_REASON_CD")) {
                        nonworking.setSUB_REASON_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("SUB_REASON")) {
                        nonworking.setSUB_REASON(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nonworking;
    }


    //----------Non working Sub Reason
    public static WindowNonReasonGetterSetter windowNonReasonXML(XmlPullParser xpp,
                                                                 int eventType) {
        WindowNonReasonGetterSetter nonworking = new WindowNonReasonGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        nonworking.setWindowNonReason_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("WREASON_CD")) {
                        nonworking.setWREASON_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("WREASON")) {
                        nonworking.setWREASON(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nonworking;
    }

    public static CategoryMasterGetterSetter categoryMasterXML(XmlPullParser xpp,
                                                               int eventType) {
        CategoryMasterGetterSetter category = new CategoryMasterGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        category.setCategory_master_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("CATEGORY_CD")) {
                        category.setCategory_cd(xpp.nextText());
                    }
                    if (xpp.getName().equals("CATEGORY")) {
                        category.setCategory(xpp.nextText());
                    }
                    if (xpp.getName().equals("CATEGORY_SEQUENCE")) {
                        category.setCATEGORY_SEQUENCE(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return category;
    }

	
	/*public static StoreListGetterSetter storeStruct(XmlPullParser xpp,
			int eventType) {
		StoreListGetterSetter skumaster = new StoreListGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {

					if (xpp.getName().equals("META_DATA")) {
						skumaster.setStoreTable(xpp.nextText());
					}
			
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skumaster;
	}

	
	
	//data
	
	public static NonWrkingMasterGetterSetter nonworkingData(XmlPullParser xpp, int eventType) {
		NonWrkingMasterGetterSetter jcpGetterSetter = new NonWrkingMasterGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setNonworkingtable(xpp.nextText());
					}
					
					if (xpp.getName().equals("REASON_ID")) {
						jcpGetterSetter.setNon_WorkingReasonId(xpp.nextText());
					}
					if (xpp.getName().equals("REASON")) {
						jcpGetterSetter.setNon_WorkingReasonName(xpp.nextText());
					}
					if (xpp.getName().equals("SUB_REASON_ID")) {
						jcpGetterSetter.setSubReason_id(xpp.nextText());
					}
					if (xpp.getName().equals("SUB_REASON")) {
						jcpGetterSetter.setSub_reason(xpp.nextText());
					}
					
					if (xpp.getName().equals("IMAGE_ALLOW")) {
						jcpGetterSetter.setImageAllow(xpp.nextText());
					}
					if (xpp.getName().equals("ENTRY_NOT_ALLOW")) {
						jcpGetterSetter.setEntryAlow(xpp.nextText());
					}
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	 
	// supervior download
	
	
	public static SupervisorGetterSetter distributorName(XmlPullParser xpp, int eventType) {
		SupervisorGetterSetter jcpGetterSetter = new SupervisorGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setSupervisortable(xpp.nextText());
					}
					
					if (xpp.getName().equals("KEYACCOUNT_CD")) {
						jcpGetterSetter.setSupervisorId(xpp.nextText());
					}
					if (xpp.getName().equals("KEYACCOUNT")) {
						jcpGetterSetter.setSupervisorname(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	// supervisor journey
	
	public static JourneyPlanGetterSetter supervisorJourney(XmlPullParser xpp, int eventType) {
		JourneyPlanGetterSetter jcpGetterSetter = new JourneyPlanGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setTablemerchJourney(xpp.nextText());
					}
					
					if (xpp.getName().equals("MERCHANDISER_CD")) {
						jcpGetterSetter.setMerchanId(xpp.nextText());
					}
					if (xpp.getName().equals("VISIT_DATE")) {
						jcpGetterSetter.setVisitdate(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	//designation
	
	public static DesignationGetterSetter designation(XmlPullParser xpp, int eventType) {
		DesignationGetterSetter jcpGetterSetter = new DesignationGetterSetter();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setTabledesig(xpp.nextText());
					}
					
					if (xpp.getName().equals("DESIGNATION_CD")) {
						jcpGetterSetter.setDesigId(xpp.nextText());
					}
					if (xpp.getName().equals("DESIGNATION")) {
						jcpGetterSetter.setDesig(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	
	public static Deviation_Reason deviationReason(XmlPullParser xpp, int eventType) {
		Deviation_Reason jcpGetterSetter = new Deviation_Reason();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.settabledeviation(xpp.nextText());
					}
					
					if (xpp.getName().equals("DREASON_ID")) {
						jcpGetterSetter.setdevi_id(xpp.nextText());
					}
					if (xpp.getName().equals("DREASON")) {
						jcpGetterSetter.setdevi_name(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	
	public static Sup_Window supWindow(XmlPullParser xpp, int eventType) {
		Sup_Window jcpGetterSetter = new Sup_Window();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setSupWindowTable(xpp.nextText());
					}
					
					if (xpp.getName().equals("CATEGORY_CD")) {
						jcpGetterSetter.setCat_id(xpp.nextText());
					}
					if (xpp.getName().equals("CATEGORY")) {
						jcpGetterSetter.setCat_name(xpp.nextText());
					}
					
					if (xpp.getName().equals("BRAND_CD")) {
						jcpGetterSetter.setBrand_id(xpp.nextText());
					}
					if (xpp.getName().equals("BRAND")) {
						jcpGetterSetter.setBrand_name(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	
	public static Sup_Performance supPerformance(XmlPullParser xpp, int eventType) {
		Sup_Performance jcpGetterSetter = new Sup_Performance();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setSupPerformanceTable(xpp.nextText());
					}
					
					if (xpp.getName().equals("MANDAYS")) {
						jcpGetterSetter.setMandays(xpp.nextText());
					}
					if (xpp.getName().equals("COVERAGE")) {
						jcpGetterSetter.setCoverage(xpp.nextText());
					}
					
					if (xpp.getName().equals("MERCHANDISED")) {
						jcpGetterSetter.setMerchandised(xpp.nextText());
					}
					if (xpp.getName().equals("COMPLIANCE")) {
						jcpGetterSetter.setCompliance(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}
	
	public static Sup_Merchandiser supMerchandise(XmlPullParser xpp, int eventType) {
		Sup_Merchandiser jcpGetterSetter = new Sup_Merchandiser();

		try {
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("META_DATA")) {
						jcpGetterSetter.setSupMerchandiserTable(xpp.nextText());
					}
					
					if (xpp.getName().equals("MERCHANDISER")) {
						jcpGetterSetter.setMerchandiser(xpp.nextText());
					}
					
					if (xpp.getName().equals("MANDAYS")) {
						jcpGetterSetter.setMandays(xpp.nextText());
					}
					if (xpp.getName().equals("COVERAGE")) {
						jcpGetterSetter.setCoverage(xpp.nextText());
					}
					
					if (xpp.getName().equals("MERCHANDISED")) {
						jcpGetterSetter.setMerchandised(xpp.nextText());
					}
					if (xpp.getName().equals("COMPLIANCE")) {
						jcpGetterSetter.setCompliance(xpp.nextText());
					}
				
				}
				xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jcpGetterSetter;
	}*/

    // JCP XML HANDLER
    public static JourneyPlanPreviousGetterSetter JCPFutureXML(XmlPullParser xpp, int eventType) {
        JourneyPlanPreviousGetterSetter jcpGetterSetter = new JourneyPlanPreviousGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("META_DATA")) {
                        jcpGetterSetter.setTable_JOURNEY_PLAN_PREV(xpp.nextText());
                    }

                    if (xpp.getName().equals("STORE_CD")) {
                        jcpGetterSetter.setSTORE_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("VISITDATE")) {
                        jcpGetterSetter.setVISITDATE(xpp.nextText());
                    }
                    if (xpp.getName().equals("KEYACCOUNT")) {
                        jcpGetterSetter.setKEYACCOUNT(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORENAME")) {
                        jcpGetterSetter.setSTORENAME(xpp.nextText());
                    }
                    if (xpp.getName().equals("CITY")) {
                        jcpGetterSetter.setCITY(xpp.nextText());
                    }
                    if (xpp.getName().equals("STORETYPE")) {
                        jcpGetterSetter.setSTORETYPE(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jcpGetterSetter;
    }

    //nonPosmReasonXML
    public static NonPOSMReasonGetterSetter nonPosmReasonXML(XmlPullParser xpp,
                                                             int eventType) {
        NonPOSMReasonGetterSetter brandmaster = new NonPOSMReasonGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        brandmaster.setTable_NonPosmReason(xpp.nextText());
                    }
                    if (xpp.getName().equals("PREASON_CD")) {
                        brandmaster.setPREASON_CD(xpp.nextText());
                    }
                    if (xpp.getName().equals("PREASON")) {
                        brandmaster.setPREASON(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return brandmaster;
    }

    // JCP XML HANDLER
    public static ServerTimeGetterSetter ServerTimeXML(XmlPullParser xpp, int eventType) {
        ServerTimeGetterSetter serverGetterSetter = new ServerTimeGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("META_DATA")) {
                        serverGetterSetter.setTable_SERVER_TIME(xpp.nextText());
                    }
                    if (xpp.getName().equals("FIRST_STORE_LOGIN")) {
                        serverGetterSetter.setFIRST_STORE_LOGIN(xpp.nextText());
                    }
                    if (xpp.getName().equals("CUR_TIME")) {
                        serverGetterSetter.setCUR_TIME(xpp.nextText());
                    }
                    if (xpp.getName().equals("TOT_HOUR")) {
                        serverGetterSetter.setTOT_HOUR(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return serverGetterSetter;
    }


    public static ImageXMLGetterSetter imageXML(XmlPullParser xpp, int eventType) {
        ImageXMLGetterSetter imageXMLGetSet = new ImageXMLGetterSetter();
        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("string")) {
                        imageXMLGetSet.setString(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return imageXMLGetSet;
    }


    public static StateMasterGetterSetter stateMasterXML(XmlPullParser xpp, int eventType) {
        StateMasterGetterSetter stateMasterGetSet = new StateMasterGetterSetter();
        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        stateMasterGetSet.setTable_STATE_MASTER(xpp.nextText());
                    }
                    if (xpp.getName().equals("STATE_CD")) {
                        stateMasterGetSet.setState_cd_list(xpp.nextText());
                    }
                    if (xpp.getName().equals("STATE")) {
                        stateMasterGetSet.setState_list(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stateMasterGetSet;
    }

    public static CityMasterGetterSetter cityMasterXML(XmlPullParser xpp, int eventType) {
        CityMasterGetterSetter cityMasterGetSet = new CityMasterGetterSetter();
        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {

                    if (xpp.getName().equals("META_DATA")) {
                        cityMasterGetSet.setTable_CITY_MASTER(xpp.nextText());
                    }
                    if (xpp.getName().equals("CITY_CD")) {
                        cityMasterGetSet.setCity_cd_list(xpp.nextText());
                    }
                    if (xpp.getName().equals("CITY")) {
                        cityMasterGetSet.setCity_list(xpp.nextText());
                    }
                    if (xpp.getName().equals("STATE_CD")) {
                        cityMasterGetSet.setState_cd_list(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cityMasterGetSet;
    }


    public static NoticeurlGetterSetter NoticeUrlXMLHandler(XmlPullParser xpp, int eventType) {
        NoticeurlGetterSetter qnsGetterSetter = new NoticeurlGetterSetter();
        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("META_DATA")) {
                        qnsGetterSetter.setNoticeurl_table(xpp.nextText());
                    }
                    if (xpp.getName().equals("NOTICE_BOARD")) {
                        qnsGetterSetter.setNOTICE_URL(xpp.nextText());
                    }
                    if (xpp.getName().equals("QUIZ_URL")) {
                        qnsGetterSetter.setQUIZ_URL(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return qnsGetterSetter;
    }


}
