package com.cpm.upload;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlHandler.FailureXMLHandler;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class BulkImageUploadActivity extends AppCompatActivity {

    String errormsg = "", status;

    private FailureGetterSetter failureGetterSetter = null;

    String Path;

    private SharedPreferences preferences;

    String result = "";
    Context context;

    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;

    Data data;

    int factor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_image_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        Path = CommonString1.FILE_PATH_FOR_BULK_UPLOAD;

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        new UploadAsynkTask().execute();
    }

    class UploadAsynkTask extends AsyncTask<Void, Data, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new Dialog(BulkImageUploadActivity.this);
            dialog.setContentView(R.layout.custom_upload);
            dialog.setCancelable(false);
            dialog.show();
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);
        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                UploadImageWithRetrofit uploadRetro = new UploadImageWithRetrofit(context);
                uploadRetro.uploadedFiles = 0;
                data = new Data();

                File f = new File(Path);
                File file[] = f.listFiles();

                int totalfiles = f.listFiles().length;
                if (file.length > 0) {

                    factor = 100 / file.length;

                    if (factor == 0) {
                        factor = 1;
                    }
                    data.value = 0;

                    for (int i = 0; i < file.length; i++) {

                        data.value = data.value + factor;
                        //data.name = "Uploading Images";
                        data.name = uploadRetro.uploadedFiles + " images uploaded out of " + totalfiles;

                        publishProgress(data);

                        if (new File(CommonString1.FILE_PATH_FOR_BULK_UPLOAD + file[i].getName()).exists()) {
                            String folderName = "";
                            if (file[i].getName().contains("NonWorking") || file[i].getName().contains("StoreImage")) {
                                folderName = "StoreImages";
                            } else if (file[i].getName().contains("competitorImage")) {
                                folderName = "CompetitionImages";
                            } else if (file[i].getName().contains("PaidVisibility_")) {
                                folderName = "MTPaidVisibility";
                            } else if (file[i].getName().contains("FREE_VISI")) {
                                folderName = "MTFreeVisibility";
                            } else if (file[i].getName().contains("Posmimage")) {
                                folderName = "PosmImages";
                            } else if (file[i].getName().contains("Promotion_")) {
                                folderName = "MTPromotion";
                            } else if (file[i].getName().contains("SOS_")) {
                                folderName = "MTShareOfShelf";
                            } else if (file[i].getName().contains("SignageImage")) {
                                folderName = "SignageImages";
                            } else if (file[i].getName().contains("WindowsImage") || file[i].getName().contains("WINDOWImage")) {
                                folderName = "WindowImages";
                            } else if (file[i].getName().contains("GeoTag")) {
                                folderName = "GEOStoreImages";
                            } else if (file[i].getName().contains("GST_Img") || file[i].getName().contains("Store_Profile_Img")) {
                                folderName = "StoreProfile";
                            } else {
                                folderName = "BulkImages";
                            }
                            uploadRetro.UploadImage2(file[i].getName(), folderName, CommonString1.FILE_PATH_FOR_BULK_UPLOAD);
                        }

                    }
                }
                if (uploadRetro.uploadedFiles == totalfiles) {
                    result = CommonString1.KEY_SUCCESS;
                } else {
                    result = uploadRetro.uploadedFiles + " images uploaded out of " + totalfiles;
                }

            } catch (Exception e) {
                e.printStackTrace();
                result = e.toString();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            if (s.equals(CommonString1.KEY_SUCCESS)) {
                showDialog("Images Uploaded Successfully");
            } else {
                showDialog(s + " .Please try again.");
            }
        }

        @Override
        protected void onProgressUpdate(Data... values) {
            // TODO Auto-generated method stub

            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);
        }
    }

    class Data {
        int value;
        String name;
    }

    public String UploadImage(String path, String folder_path) throws Exception {

        errormsg = "";
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(Path + path, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeFile(
                Path + path, o2);

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] ba = bao.toByteArray();
        String ba1 = Base64.encodeBytes(ba);

        SoapObject request = new SoapObject(CommonString1.NAMESPACE,
                CommonString1.METHOD_UPLOAD_IMAGE);

        String[] split = path.split("/");
        String path1 = split[split.length - 1];

        request.addProperty("img", ba1);
        request.addProperty("name", path1);
        request.addProperty("FolderName", folder_path);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(
                CommonString1.URL);

        androidHttpTransport
                .call(CommonString1.SOAP_ACTION_UPLOAD_IMAGE,
                        envelope);
        Object result = (Object) envelope.getResponse();

        if (!result.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {

            if (result.toString().equalsIgnoreCase(CommonString1.KEY_FALSE)) {
                return CommonString1.KEY_FALSE;
            }

            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            SAXParser saxP = saxPF.newSAXParser();
            XMLReader xmlR = saxP.getXMLReader();

            // for failure
            FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
            xmlR.setContentHandler(failureXMLHandler);

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(result.toString()));
            xmlR.parse(is);

            failureGetterSetter = failureXMLHandler
                    .getFailureGetterSetter();

            if (failureGetterSetter.getStatus().equalsIgnoreCase(
                    CommonString1.KEY_FAILURE)) {
                errormsg = failureGetterSetter.getErrorMsg();
                return CommonString1.KEY_FAILURE;
            }
        } else {
            new File(Path + path).delete();
           /* SharedPreferences.Editor editor = preferences
                    .edit();
            editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
            editor.commit();*/
        }

        return result.toString();
    }

    public void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BulkImageUploadActivity.this);
        builder.setTitle("Parinaam");
        builder.setMessage(msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
