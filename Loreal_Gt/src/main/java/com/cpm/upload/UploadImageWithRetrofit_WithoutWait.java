package com.cpm.upload;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cpm.Constants.AlertandMessages;
import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.ImageStatusGetterSetter;
import com.cpm.GetterSetter.ImageXMLGetterSetter;
import com.cpm.Loreal_GT.LoginActivity;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.xmlHandler.XMLHandlers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by deepakp on 20-03-2018.
 */

public class UploadImageWithRetrofit_WithoutWait {

    boolean isvalid;
    RequestBody body1;
    private Retrofit adapter;
    Context context;
    public static int uploadedFiles = 0;
    int totalFiles;
    ArrayList<String> fileNameList;
    String folderName = "";
    ArrayList<ImageStatusGetterSetter> imageGetSetList;
    XmlPullParserFactory factory;
    XmlPullParser xpp;
    SharedPreferences preferences;
    GSKDatabase database;
    String visit_date, username;
    ProgressDialog progressDialog;

    public UploadImageWithRetrofit_WithoutWait(Context context, ArrayList fileNameList) {
        this.context = context;
        this.fileNameList = fileNameList;
        totalFiles = 0;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        visit_date = preferences.getString(CommonString1.KEY_DATE, "");
        username = preferences.getString(CommonString1.KEY_USERNAME, "");
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Uploading Images");
        progressDialog.setCancelable(false);
    }


    void UploadImagesRecursive() {
        try {
            progressDialog.show();
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            xpp = factory.newPullParser();
            imageGetSetList = new ArrayList<>();
            imageGetSetList.clear();
            uploadedFiles = 0;
            if (fileNameList.size() > 0) {
                totalFiles = fileNameList.size();
                UploadImage2(fileNameList, 0);
            } else {
                totalFiles = 0;
            }
        } catch (Exception e) {
            progressDialog.dismiss();
            e.printStackTrace();
            AlertandMessages.showAlert((Activity) context, "Error : " + e.getMessage(), true);
        }

    }

    public void UploadImagesRecursive_ForOLDPATH() {
        try {
            progressDialog.show();
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            xpp = factory.newPullParser();
            imageGetSetList = new ArrayList<>();
            imageGetSetList.clear();
            uploadedFiles = 0;
            if (fileNameList.size() > 0) {
                totalFiles = fileNameList.size();
                UploadImageRecursiveForOldPath(0);
            } else {
                totalFiles = 0;
            }
        } catch (Exception e) {
            progressDialog.dismiss();
            e.printStackTrace();
        }

    }


    public void UploadImage2(final ArrayList<String> filenameList, int index) {
        try {
            final int[] tempIndex = {index};
            File[] filelist = new File(CommonString1.FILE_PATH).listFiles();
            if (filelist.length > 0 && uploadedFiles != filenameList.size()) {
                if (new File(CommonString1.FILE_PATH + filenameList.get(index)).exists()) {
                    folderName = "";
                    if (filenameList.get(index).contains("NonWorking") || filenameList.get(index).contains("StoreImage")) {
                        folderName = "StoreImages";
                    } else if (filenameList.get(index).contains("competitorImage")) {
                        folderName = "CompetitionImages";
                    } else if (filenameList.get(index).contains("PaidVisibility_")) {
                        folderName = "MTPaidVisibility";
                    } else if (filenameList.get(index).contains("FREE_VISI")) {
                        folderName = "MTFreeVisibility";
                    } else if (filenameList.get(index).contains("Posmimage")) {
                        folderName = "PosmImages";
                    } else if (filenameList.get(index).contains("Promotion_")) {
                        folderName = "MTPromotion";
                    } else if (filenameList.get(index).contains("SOS_")) {
                        folderName = "MTShareOfShelf";
                    } else if (filenameList.get(index).contains("SignageImage")) {
                        folderName = "SignageImages";
                    } else if (filenameList.get(index).contains("WindowsImage") || filenameList.get(index).contains("WINDOWImage")) {
                        folderName = "WindowImages";
                    } else if (filenameList.get(index).contains("GeoTag")) {
                        folderName = "GEOStoreImages";
                    } else if (filenameList.get(index).contains("GST_Img") || filenameList.get(index).contains("Store_Profile_Img")) {
                        folderName = "StoreProfile";
                    } else {
                        folderName = "BulkImages";
                    }


                    File originalFile = new File(CommonString1.FILE_PATH + filenameList.get(index));
                    final File finalFile = saveBitmapToFileSmaller(originalFile);
                    progressDialog.setMessage("Uploading (" + index + "/" + totalFiles + ")");
                    RequestBody photo = RequestBody.create(MediaType.parse("application/octet-stream"), finalFile);
                    OkHttpClient okHttpClient = new OkHttpClient();
                    okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
                    okHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
                    okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);

                    body1 = new MultipartBuilder()
                            .type(MultipartBuilder.FORM)
                            .addFormDataPart("file", finalFile.getName(), photo)
                            .addFormDataPart("FolderName", folderName)
                            .build();

                    adapter = new Retrofit.Builder()
                            .baseUrl(CommonString1.URL2)
                            .client(okHttpClient)
                            .addConverterFactory(new StringConverterFactory())
                            .build();
                    PostApi api = adapter.create(PostApi.class);

                    Call<String> call = api.getUploadImage(body1);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Response<String> response) {
                            int status;
                            ImageStatusGetterSetter imgStatusGetSet;
                            ImageXMLGetterSetter imgXmlGetSet;
                            if (response.body() != null) {
                                try {
                                    xpp.setInput(new StringReader(response.body().toString()));
                                    xpp.next();
                                    int eventType = xpp.getEventType();
                                    imgXmlGetSet = XMLHandlers.imageXML(xpp, eventType);
                                    if (response.isSuccess() && imgXmlGetSet.getString().contains(finalFile.getName())) {
                                        finalFile.delete();
                                        uploadedFiles++;
                                        status = 1;
                                        imgStatusGetSet = new ImageStatusGetterSetter();
                                        imgStatusGetSet.setImageName(finalFile.getName());
                                        imgStatusGetSet.setStatus("U");
                                        imgStatusGetSet.setServerResponse(imgXmlGetSet.getString());
                                    } else {
                                        status = 0;
                                        imgStatusGetSet = new ImageStatusGetterSetter();
                                        imgStatusGetSet.setImageName(finalFile.getName());
                                        imgStatusGetSet.setStatus("N");
                                        imgStatusGetSet.setServerResponse(imgXmlGetSet.getString());
                                    }
                                    if (status == 0) {
                                        imageGetSetList.add(imgStatusGetSet);
                                        if (!((Activity) context).isFinishing()) {
                                            progressDialog.dismiss();
                                            Runnable runnable = new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (totalFiles != uploadedFiles) {
                                                        new ImageStatusAfterUpload(imageGetSetList).execute();
                                                    }
                                                }
                                            };
                                            AlertandMessages.taskRunAlert((Activity) context, "Image not uploaded." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles, runnable);
                                        }
                                    } else {
                                        imageGetSetList.add(imgStatusGetSet);
                                        tempIndex[0]++;
                                        UploadImage2(filenameList, tempIndex[0]);
                                    }

                                } catch (XmlPullParserException e) {
                                    ImageStatusGetterSetter imgStatusEx = new ImageStatusGetterSetter();
                                    imgStatusEx.setImageName(finalFile.getName());
                                    imgStatusEx.setStatus("app-Ex");
                                    imgStatusEx.setServerResponse("XmlPullParserException e");
                                    imageGetSetList.add(imgStatusEx);
                                    e.printStackTrace();
                                    progressDialog.dismiss();
                                    Runnable runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            if (totalFiles != uploadedFiles) {
                                                new ImageStatusAfterUpload(imageGetSetList).execute();
                                            }
                                        }
                                    };
                                    AlertandMessages.taskRunAlert((Activity) context, "XmlPullParserException Error in upload." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles, runnable);
                                } catch (IOException e) {
                                    ImageStatusGetterSetter imgStatusEx = new ImageStatusGetterSetter();
                                    imgStatusEx.setImageName(finalFile.getName());
                                    imgStatusEx.setStatus("app-Ex");
                                    imgStatusEx.setServerResponse("IOException e");
                                    imageGetSetList.add(imgStatusEx);
                                    e.printStackTrace();
                                    progressDialog.dismiss();
                                    Runnable runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            if (totalFiles != uploadedFiles) {
                                                new ImageStatusAfterUpload(imageGetSetList).execute();
                                            }
                                        }
                                    };
                                    AlertandMessages.taskRunAlert((Activity) context, "IOException Error in upload." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles, runnable);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            ImageStatusGetterSetter imgStatusGetSet = new ImageStatusGetterSetter();
                            imgStatusGetSet.setImageName(finalFile.getName());
                            imgStatusGetSet.setStatus("Ex");
                            imgStatusGetSet.setServerResponse(t.getMessage());
                            imageGetSetList.add(imgStatusGetSet);
                            t.printStackTrace();
                            progressDialog.dismiss();
                            if (t instanceof IOException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                                if (!((Activity) context).isFinishing()) {
                                    Runnable runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            if (totalFiles != uploadedFiles) {
                                                new ImageStatusAfterUpload(imageGetSetList).execute();
                                            }
                                        }
                                    };
                                    AlertandMessages.taskRunAlert((Activity) context, "Network Error in upload." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles, runnable);
                                } else {

                                }
                            }
                        }
                    });
                } else {
                    if (!((Activity) context).isFinishing()) {
                        ImageStatusGetterSetter imgStatusGetSet = new ImageStatusGetterSetter();
                        imgStatusGetSet.setImageName(filenameList.get(index));
                        imgStatusGetSet.setStatus("Ex");
                        imgStatusGetSet.setServerResponse("File not exist in folder");
                        imageGetSetList.add(imgStatusGetSet);
                        progressDialog.dismiss();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                if (totalFiles != uploadedFiles) {
                                    new ImageStatusAfterUpload(imageGetSetList).execute();
                                }
                            }
                        };
                        AlertandMessages.taskRunAlert((Activity) context, filenameList.get(index) + " does not Exist", runnable);
                    } else {

                    }
                }
            } else {
                if (uploadedFiles == filenameList.size()) {
                    //new StatusUpload().execute();
                    progressDialog.setMessage("uploading Image status after Upload");
                    new ImageStatusAfterUpload(imageGetSetList).execute();
                }


            }
        } catch (Exception e) {
            progressDialog.dismiss();
            e.printStackTrace();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (totalFiles != uploadedFiles) {
                        new ImageStatusAfterUpload(imageGetSetList).execute();
                    }
                }
            };
            AlertandMessages.taskRunAlert((Activity) context, "Error : " + e.getMessage(), runnable);
        }

    }

    class ImageStatusAfterUpload extends AsyncTask<String, String, String> {

        ArrayList<ImageStatusGetterSetter> imageGetSetList;
        boolean imageData_uploaded;
        String msg = "";

        ImageStatusAfterUpload(ArrayList<ImageStatusGetterSetter> imageGetSetList) {
            this.imageGetSetList = imageGetSetList;
            imageData_uploaded = false;
            msg = "";
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //region COVERAGE_STATUS U
                String final_xml = "";
                for (int i = 0; i < imageGetSetList.size(); i++) {
                    String onXML = "";
                    onXML = "[IMAGE]"
                            + imageGetSetList.get(i).toString()
                            + "[/IMAGE]";

                    final_xml = final_xml + onXML;
                }
                final String sos_xml = "[DATA]" + final_xml + "[/DATA]";
                SoapObject request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                request.addProperty("XMLDATA", sos_xml);
                request.addProperty("KEYS", "IMAGEDATA_AFTER_IMG_UPLOAD");
                request.addProperty("USERNAME", username);
                request.addProperty("MID", "0");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString1.URL, 10000);
                androidHttpTransport.call(CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML, envelope);

                Object result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                    imageData_uploaded = true;
                } else {
                    msg = result.toString();
                }
                //endregion
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                progressDialog.dismiss();
                msg = CommonString1.MESSAGE_SOCKETEXCEPTION;
            } catch (IOException e) {
                progressDialog.dismiss();
                e.printStackTrace();
                msg = CommonString1.MESSAGE_SOCKETEXCEPTION;
            } catch (Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                msg = "Error : " + e.getMessage();
            }
            if (imageData_uploaded) {
                return CommonString1.KEY_SUCCESS;
            } else {
                return msg;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //pd.dismiss();
            if (s.equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                if (totalFiles == uploadedFiles && imageData_uploaded) {
                    // AlertandMessages.showAlert((Activity) context, "Images Data uploaded Successfully", true);
                    progressDialog.setMessage("updating Store status");
                    new StatusUpload().execute();
                } else {
                    progressDialog.dismiss();
                    AlertandMessages.showAlert((Activity) context, "Images Data not uploaded", true);
                }
            } else if (!s.equalsIgnoreCase("")) {
                progressDialog.dismiss();
                AlertandMessages.showAlert((Activity) context, msg, true);
            } else {
                progressDialog.dismiss();
                AlertandMessages.showAlert((Activity) context, CommonString1.MESSAGE_EXCEPTION, true);
            }
        }
    }


    class StatusUpload extends AsyncTask<String, String, String> {
        String msg = "";
        boolean statusUpdated = false;

        @Override
        protected String doInBackground(String... strings) {
            try {
                msg = "";
                statusUpdated = false;
                //region COVERAGE_STATUS U
                database = new GSKDatabase(context);
                database.open();
                ArrayList<CoverageBean> coverageBeanlist = database.getCoverageData(visit_date);
                loop1:
                for (int i = 0; i < coverageBeanlist.size(); i++) {
                    if (coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_D)) {
                        String final_xml = "";
                        String onXML = "";
                        onXML = "[COVERAGE_STATUS][STORE_ID]"
                                + coverageBeanlist.get(i).getStoreId()
                                + "[/STORE_ID]"
                                + "[VISIT_DATE]"
                                + coverageBeanlist.get(i).getVisitDate()
                                + "[/VISIT_DATE]"
                                + "[USER_ID]"
                                + coverageBeanlist.get(i).getUserId()
                                + "[/USER_ID]"
                                + "[STATUS]"
                                + CommonString1.KEY_U
                                + "[/STATUS]"
                                + "[/COVERAGE_STATUS]";

                        final_xml = final_xml + onXML;
                        final String sos_xml2 = "[DATA]" + final_xml + "[/DATA]";
                        SoapObject request1 = new SoapObject(CommonString1.NAMESPACE, CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS);
                        request1.addProperty("onXML", sos_xml2);
                        SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope1.dotNet = true;
                        envelope1.setOutputSoapObject(request1);
                        HttpTransportSE androidHttpTransport1 = new HttpTransportSE(CommonString1.URL, 10000);
                        androidHttpTransport1.call(CommonString1.SOAP_ACTION + CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS, envelope1);

                        Object result1 = (Object) envelope1.getResponse();
                        if (result1.toString().equalsIgnoreCase(
                                CommonString1.KEY_SUCCESS)) {
                            database.open();
                            database.updateCoverageStatus(coverageBeanlist.get(i).getMID(), CommonString1.KEY_U);
                            database.updateStoreStatusOnLeave(coverageBeanlist.get(i).getStoreId(), coverageBeanlist.get(i).getVisitDate(), CommonString1.KEY_U);
                            database.deleteSpecificStoreData(coverageBeanlist.get(i).getStoreId());
                        }

                        if (result1.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                            statusUpdated = true;
                        } else {
                            statusUpdated = false;
                            break loop1;
                        }

                    }

                }
                //endregion
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                progressDialog.dismiss();
                msg = CommonString1.MESSAGE_SOCKETEXCEPTION;
            } catch (IOException e) {
                e.printStackTrace();
                progressDialog.dismiss();
                msg = CommonString1.MESSAGE_SOCKETEXCEPTION;
            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
                msg = e.getMessage();
            }
            if (statusUpdated) {
                return CommonString1.KEY_SUCCESS;
            } else {
                return msg;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                if (totalFiles == uploadedFiles && statusUpdated) {
                    AlertandMessages.showAlert((Activity) context, "All images uploaded Successfully", true);
                } else if (totalFiles == uploadedFiles && !statusUpdated) {
                    AlertandMessages.showAlert((Activity) context, "All images uploaded Successfully, but status not updated", true);
                } else {
                    AlertandMessages.showAlert((Activity) context, "Some images not uploaded", true);
                }
            } else if (!s.equalsIgnoreCase("")) {
                AlertandMessages.showAlert((Activity) context, "Error : " + s, true);
            } else {
                AlertandMessages.showAlert((Activity) context, CommonString1.MESSAGE_EXCEPTION, true);
            }
        }
    }


    public File saveBitmapToFileSmaller(File file) {
        File file2 = file;
        try {
            int inWidth = 0;
            int inHeight = 0;

            InputStream in = new FileInputStream(file2);
            // decode image size (decode metadata only, not the whole image)
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);
            in.close();
            in = null;

            // save width and height
            inWidth = options.outWidth;
            inHeight = options.outHeight;

            // decode full image pre-resized
            in = new FileInputStream(file2);
            options = new BitmapFactory.Options();
            // calc rought re-size (this is no exact resize)
            options.inSampleSize = Math.max(inWidth / 800, inHeight / 500);
            // decode full image
            Bitmap roughBitmap = BitmapFactory.decodeStream(in, null, options);

            // calc exact destination size
            Matrix m = new Matrix();
            RectF inRect = new RectF(0, 0, roughBitmap.getWidth(), roughBitmap.getHeight());
            RectF outRect = new RectF(0, 0, 800, 500);
            m.setRectToRect(inRect, outRect, Matrix.ScaleToFit.CENTER);
            float[] values = new float[9];
            m.getValues(values);
            // resize bitmap
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(roughBitmap, (int) (roughBitmap.getWidth() * values[0]), (int) (roughBitmap.getHeight() * values[4]), true);
            // save image
            FileOutputStream out = new FileOutputStream(file2);
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

        } catch (Exception e) {
            Log.e("Image", e.toString(), e);
            return file2;
        }
        return file;
    }


    class StringConverterFactory implements Converter.Factory {
        private StringConverterFactory() {
        }

        @Override
        public Converter<String> get(Type type) {
            Class<?> cls = (Class<?>) type;
            if (String.class.isAssignableFrom(cls)) {
                return new StringConverter();
            }
            return null;
        }
    }

    private static class StringConverter implements Converter<String> {
        private static final MediaType PLAIN_TEXT = MediaType.parse("text/plain; charset=UTF-8");

        @Override
        public String fromBody(ResponseBody body) throws IOException {
            return new String(body.bytes());
        }

        @Override
        public RequestBody toBody(String value) {
            return RequestBody.create(PLAIN_TEXT, convertToBytes(value));
        }

        private static byte[] convertToBytes(String string) {
            try {
                return string.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void UploadImageRecursiveForOldPath(int index) {
        try {
            final int[] tempIndex = {index};
            File[] filelist = new File(CommonString1.OLD_FILE_PATH).listFiles();
            if (filelist.length > 0 && uploadedFiles != fileNameList.size()) {
                if (new File(CommonString1.OLD_FILE_PATH + fileNameList.get(index)).exists()) {
                    folderName = "";
                    if (fileNameList.get(index).contains("NonWorking") || fileNameList.get(index).contains("StoreImage")) {
                        folderName = "StoreImages";
                    } else if (fileNameList.get(index).contains("competitorImage")) {
                        folderName = "CompetitionImages";
                    } else if (fileNameList.get(index).contains("PaidVisibility_")) {
                        folderName = "MTPaidVisibility";
                    } else if (fileNameList.get(index).contains("FREE_VISI")) {
                        folderName = "MTFreeVisibility";
                    } else if (fileNameList.get(index).contains("Posmimage")) {
                        folderName = "PosmImages";
                    } else if (fileNameList.get(index).contains("Promotion_")) {
                        folderName = "MTPromotion";
                    } else if (fileNameList.get(index).contains("SOS_")) {
                        folderName = "MTShareOfShelf";
                    } else if (fileNameList.get(index).contains("SignageImage")) {
                        folderName = "SignageImages";
                    } else if (fileNameList.get(index).contains("WindowsImage") || fileNameList.get(index).contains("WINDOWImage")) {
                        folderName = "WindowImages";
                    } else if (fileNameList.get(index).contains("GeoTag")) {
                        folderName = "GEOStoreImages";
                    } else if (fileNameList.get(index).contains("GST_Img") || fileNameList.get(index).contains("Store_Profile_Img")) {
                        folderName = "StoreProfile";
                    } else {
                        folderName = "BulkImages";
                    }

                    File originalFile = new File(CommonString1.OLD_FILE_PATH + fileNameList.get(index));
                    final File finalFile = saveBitmapToFileSmaller(originalFile);
                    progressDialog.setMessage("Uploading (" + index + "/" + totalFiles + ")");
                    RequestBody photo = RequestBody.create(MediaType.parse("application/octet-stream"), finalFile);
                    OkHttpClient okHttpClient = new OkHttpClient();
                    okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
                    okHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
                    okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);

                    body1 = new MultipartBuilder()
                            .type(MultipartBuilder.FORM)
                            .addFormDataPart("file", finalFile.getName(), photo)
                            .addFormDataPart("FolderName", folderName)
                            .build();

                    adapter = new Retrofit.Builder()
                            .baseUrl(CommonString1.URL2)
                            .client(okHttpClient)
                            .addConverterFactory(new StringConverterFactory())
                            .build();
                    PostApi api = adapter.create(PostApi.class);

                    Call<String> call = api.getUploadImage(body1);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Response<String> response) {
                            int status;
                            ImageXMLGetterSetter imgXmlGetSet;
                            if (response.body() != null) {
                                try {
                                    xpp.setInput(new StringReader(response.body().toString()));
                                    xpp.next();
                                    int eventType = xpp.getEventType();
                                    imgXmlGetSet = XMLHandlers.imageXML(xpp, eventType);
                                    if (response.isSuccess() && imgXmlGetSet.getString().contains(finalFile.getName())) {
                                        finalFile.delete();
                                        uploadedFiles++;
                                        status = 1;
                                    } else {
                                        status = 0;
                                    }
                                    if (status == 0) {
                                        if (!((Activity) context).isFinishing()) {
                                            progressDialog.dismiss();
                                            AlertandMessages.showToastMsg(context, "Image not uploaded." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles);
                                        }
                                    } else {
                                        tempIndex[0]++;
                                        UploadImageRecursiveForOldPath(tempIndex[0]);
                                    }

                                } catch (XmlPullParserException e) {
                                    e.printStackTrace();
                                    progressDialog.dismiss();
                                    AlertandMessages.showToastMsg(context, "XmlPullParserException Error in upload." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    progressDialog.dismiss();
                                    AlertandMessages.showToastMsg(context, "IOException Error in upload." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            t.printStackTrace();
                            progressDialog.dismiss();
                            if (t instanceof IOException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                                if (!((Activity) context).isFinishing()) {
                                    AlertandMessages.showToastMsg(context, "Network Error in upload." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles);
                                }
                            }
                        }
                    });
                } else {
                    if (!((Activity) context).isFinishing()) {
                        progressDialog.dismiss();
                        AlertandMessages.showToastMsg(context, fileNameList.get(index) + " does not Exist");
                    } else {

                    }
                }
            } else {
                if (uploadedFiles == fileNameList.size()) {
                    //new StatusUpload().execute();
                    progressDialog.dismiss();
                    Intent i = new Intent(((Activity) context), LoginActivity.class);
                    ((Activity) context).startActivity(i);
                    ((Activity) context).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    AlertandMessages.showToastMsg(context, "Old images uploaded successfully");
                }


            }
        } catch (Exception e) {
            progressDialog.dismiss();
            e.printStackTrace();
            AlertandMessages.showToastMsg(context, "Error in image upload");
        }
    }

}
