package com.cpm.upload;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Log;
import android.widget.Toast;

import com.cpm.Constants.AlertandMessages;
import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.message.AlertMessage;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by deepakp on 5/22/2017.
 */

public class UploadImageWithRetrofit {

    boolean isvalid;
    RequestBody body1;
    private Retrofit adapter;
    Context context;
    public static int uploadedFiles = 0;
    int status = 0;

    public UploadImageWithRetrofit(Context context) {
        this.context = context;
    }


    int UploadImagesAndData() {
        try {
            uploadedFiles = 0;
            File f = new File(CommonString1.FILE_PATH);
            File file[] = f.listFiles();
            // int totalfiles = f.listFiles().length;
            if (file.length > 0) {

                // data.value = 0;
                for (int i = 0; i < file.length; i++) {

                    //data.value = data.value + factor;
                    //data.name = "Uploading Images";
                    //data.name = uploadedFiles+" images uploaded out of "+totalfiles;

                    if (new File(CommonString1.FILE_PATH + file[i].getName()).exists()) {
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
                        String result = UploadImage2(file[i].getName(), folderName, CommonString1.FILE_PATH);
                        if (result.equalsIgnoreCase(AlertMessage.MESSAGE_SOCKETEXCEPTION)) {
                            throw new IOException();
                        } else if (result.equalsIgnoreCase(CommonString1.KEY_FAILURE)) {
                            throw new Exception();
                        }
                    }

                }
            }

            return uploadedFiles;

        } catch (IOException ex) {
            return -1;
        } catch (Exception e) {
            return -2;
        }

    }


    public String UploadImage2(final String filename, String foldername, String folderPath) {
        try {
            status = 0;
            File originalFile = new File(folderPath + filename);
            final File finalFile = saveBitmapToFileSmaller(originalFile);
            isvalid = false;
            RequestBody photo = RequestBody.create(MediaType.parse("application/octet-stream"), finalFile);

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(20, TimeUnit.SECONDS);
            okHttpClient.setWriteTimeout(20, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(20, TimeUnit.SECONDS);

            body1 = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("file", finalFile.getName(), photo)
                    .addFormDataPart("FolderName", foldername)
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
                    if (response.isSuccess() && response.body().contains(finalFile.getName())) {
                        finalFile.delete();
                        uploadedFiles++;
                        isvalid = true;
                        status = 1;
                    } else {
                        isvalid = true;
                        status = 1;
                        uploadedFiles++;
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    isvalid = true;
                    Toast.makeText(context, finalFile.getName() + " not uploaded", Toast.LENGTH_SHORT).show();
                    if (t instanceof IOException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                        status = -1;
                    }
                }
            });
            while (isvalid == false) {
                synchronized (this) {
                    this.wait(25);
                }
            }
            if (isvalid) {
                synchronized (this) {
                    this.notify();
                }
            }

            if (status == 1) {
                return CommonString1.KEY_SUCCESS;
            } else if (status == -1) {
                return AlertMessage.MESSAGE_SOCKETEXCEPTION;
            } else {
                return CommonString1.KEY_FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonString1.KEY_FAILURE;
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


    public void UploadBackup(final String filename, String foldername, String folderPath) {
        final ProgressDialog pd = new ProgressDialog(context);
        try {
            pd.setCancelable(false);
            pd.setMessage("Uploading Backup");
            pd.show();
            status = 0;
            final File finalFile = new File(folderPath + filename);
            isvalid = false;
            com.squareup.okhttp.RequestBody photo = com.squareup.okhttp.RequestBody.create(com.squareup.okhttp.MediaType.parse("application/octet-stream"), finalFile);
            body1 = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("file", finalFile.getName(), photo)
                    .addFormDataPart("Foldername", foldername)
                    .build();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(20, TimeUnit.SECONDS);
            okHttpClient.setWriteTimeout(20, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(20, TimeUnit.SECONDS);

            //.addNetworkInterceptor(networkInterceptor)

            adapter = new retrofit.Retrofit.Builder()
                    .baseUrl(CommonString1.URL2)
                    .client(okHttpClient)
                    .addConverterFactory(new StringConverterFactory())
                    .build();
            PostApi api = adapter.create(PostApi.class);
            retrofit.Call<String> call = api.getUploadImage(body1);
            call.enqueue(new retrofit.Callback<String>() {

                @Override
                public void onResponse(Response<String> response) {
                    if (response.isSuccess() && response.body().contains(finalFile.getName())) {
                        finalFile.delete();
                        pd.dismiss();
                        AlertandMessages.showToastMsg(context, context.getString(R.string.data_uploaded_successfully));
                    } else {
                        pd.dismiss();
                        AlertandMessages.showAlert((Activity) context, context.getString(R.string.database_not_uploaded), false);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    if (t instanceof IOException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                        pd.dismiss();
                        AlertandMessages.showAlert((Activity) context, CommonString1.MESSAGE_SOCKETEXCEPTION, false);
                    } else {
                        pd.dismiss();
                        AlertandMessages.showAlert((Activity) context, context.getString(R.string.errordatabase_not_uploaded), false);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            pd.dismiss();
            AlertandMessages.showAlert((Activity) context, CommonString1.MESSAGE_EXCEPTION, false);
        }
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


}
