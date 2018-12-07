package com.cpm.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialcamera.MaterialCamera;
import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import io.github.memfis19.annca.Annca;
import io.github.memfis19.annca.internal.configuration.AnncaConfiguration;

/**
 * Created by deepakp on 2/16/2017.
 */

public class CommonFunctions {

    public static String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:mmm");
        String cdate = formatter.format(m_cal.getTime());

       /* String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);*/

        return cdate;

    }

    public static String getCurrentTimeHHMMSS() {
        Calendar m_cal = Calendar.getInstance();
        return m_cal.get(Calendar.HOUR_OF_DAY) + ""
                + m_cal.get(Calendar.MINUTE) + "" + m_cal.get(Calendar.SECOND);
    }

    public static void startCameraActivity(Activity activity, String path) {
        String gallery_package = "";
        Uri outputFileUri = null;

        try {
            File file = new File(path);
            outputFileUri = Uri.fromFile(file);

            String defaultCameraPackage = "";
            final PackageManager packageManager = activity.getPackageManager();
            List<ApplicationInfo> list = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
            for (int n = 0; n < list.size(); n++) {
                if ((list.get(n).flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                /*Log.e("TAG", "Installed Applications  : " + list.get(n).loadLabel(packageManager).toString());
                Log.e("TAG", "package name  : " + list.get(n).packageName);*/

                    //temp value in case camera is gallery app above jellybean
                    if (list.get(n).loadLabel(packageManager).toString().equalsIgnoreCase("Gallery")) {
                        gallery_package = list.get(n).packageName;
                    }


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if (list.get(n).loadLabel(packageManager).toString().equalsIgnoreCase("Camera")) {
                            defaultCameraPackage = list.get(n).packageName;
                            break;
                        }
                    } else {
                        if (list.get(n).loadLabel(packageManager).toString().equalsIgnoreCase("Camera")) {
                            defaultCameraPackage = list.get(n).packageName;
                            break;
                        }
                    }
                }
            }

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            intent.setPackage(defaultCameraPackage);
            activity.startActivityForResult(intent, 1);
            //startActivityForResult(intent, position);

        } catch (ActivityNotFoundException e) {
            e.printStackTrace();

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            intent.setPackage(gallery_package);
            activity.startActivityForResult(intent, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean convertInttoBool(String value) {
        return Integer.parseInt(value) > 0;
    }

    public String getCurrentTimeX() {

        Calendar m_cal = Calendar.getInstance();
        int hour = m_cal.get(Calendar.HOUR_OF_DAY);
        int min = m_cal.get(Calendar.MINUTE);

        String intime;

        if (hour == 0) {
            intime = "" + 12 + ":" + min + " AM";
        } else if (hour == 12) {
            intime = "" + 12 + ":" + min + " PM";
        } else {

            if (hour > 12) {
                hour = hour - 12;
                intime = "" + hour + ":" + min + " PM";
            } else {
                intime = "" + hour + ":" + min + " AM";
            }
        }
        return intime;
    }

    public static void startMaterialCameraActivity(Activity activity, String filename, String filepath) {
        MaterialCamera materialCamera =
                new MaterialCamera(activity)
                        .saveDir(CommonString1.FILE_PATH)
                        .filename(filename)
                        .allowRetry(true)
                        .stillShot()
                        .autoSubmit(false)
                        .labelConfirm(R.string.ok);
        materialCamera.start(1);

    }


    public static void startAnncaCameraActivity(Context context, final String path) {
        final AnncaConfiguration.Builder dialogDemo = new AnncaConfiguration.Builder((Activity) context, CommonString1.CAPTURE_MEDIA);
        dialogDemo.setMediaAction(AnncaConfiguration.MEDIA_ACTION_PHOTO);
        dialogDemo.setMediaResultBehaviour(AnncaConfiguration.PREVIEW);
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.view_horizontal_camera);
        dialog.setCancelable(false);
        if (dialog != null && (!dialog.isShowing())) {
            dialog.show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                new Annca(dialogDemo.build()).launchCamera(path);
            }
        }, 3000);
    }

    public static String setMetadataAtImages(JourneyPlanGetterSetter jcpGetSet, String type, String userId) {
        String ss = "Store Name : " + jcpGetSet.getStore_name().get(0) + " | Store Cd : " + jcpGetSet.getStore_cd().get(0) + "\n" + " | Mer.Id : " + userId + " | Image Type : " + type;
        return ss;
    }

    public static String getMetadataAtImagesFromPref(String metadataFromPref, String type) {
        String ss = metadataFromPref + " | Image Type : " + type;
        return ss;
    }

    public static String setMetadataAtImagesAtPref(JourneyPlanGetterSetter jcpGetSet, String userId) {
        String ss = "Store Name : " + jcpGetSet.getStore_name().get(0) + " | Store Cd : " + jcpGetSet.getStore_cd().get(0) + " " + "\n | Mer.Id : " + userId;
        return ss;
    }

    public static Bitmap addMetadataAndTimeStampToImage(Context context, final String path, String metadata) {
        Bitmap bmp1 = BitmapFactory.decodeFile(path);
        View view = LayoutInflater.from(context).inflate(R.layout.preview_image, null);
        view.layout(0, 0, bmp1.getWidth(), bmp1.getHeight());
        Bitmap bmp = getViewBitmap(view, bmp1, path, metadata);
        try {
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(path)));
            return bmp;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return bmp1;
        }
    }

    public static Bitmap getViewBitmap(View view, Bitmap bmp, String path, String metadata) {
        try {
            //Get the dimensions of the view so we can re-layout the view at its current size
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String dateTime = sdf.format(Calendar.getInstance().getTime());
            String copm = dateTime;
            ImageView temp_img = (ImageView) view.findViewById(R.id.temp_img);
            //ImageView temp_map = (ImageView) view.findViewById(R.id.temp_map);
            TextView storeM = (TextView) view.findViewById(R.id.storeM);
            int copleteValue = 0;
            try {
                copm = copm.replaceAll("[- ]", " ");
                String[] items = copm.split(":");
                String seconds = items[2];
                int lastIndex;
                lastIndex = Integer.parseInt(seconds);
                int day = Integer.parseInt("05/22/2018".substring(3, 5));
                int a = Integer.parseInt("10") * Integer.parseInt("40");
                a = a + day;
                lastIndex = lastIndex * 2;
                copleteValue = a + lastIndex;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            /*storeM.setText("Store Name : " + "Kamla Store Okhla" + " " + " | CITY : " + "Delhi" + " | " +
                    " STORE CD : " + "10" + " " + " | MERCHANDISER ID : " + "testmer" + " ");*/
            storeM.setText(metadata);
            //timestamp on image
            Bitmap dest = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas cs = new Canvas(dest);
            cs.drawBitmap(bmp, 0f, 0f, null);

            Paint tPaint = new Paint();
            tPaint.setTextSize(25);
            tPaint.setAlpha(Color.WHITE);
            tPaint.setColor(Color.parseColor("#ff0000"));
            tPaint.setStyle(Paint.Style.FILL);

            //float height_ = tPaint.measureText("yY");
            float height_ = tPaint.measureText(dateTime + "[" + "10" + "] " + String.valueOf(copleteValue));

            Paint paint = new Paint();
            Paint.FontMetrics fm = new Paint.FontMetrics();
            paint.setColor(Color.parseColor("#33FFFFFF"));
            paint.getFontMetrics(fm);
            int margin = 8;
            float value = 65f;
            //cs.drawRect(value - margin, value + fm.top - margin, value + height_ + margin, value + fm.bottom + margin, paint);
            //cs.drawRect(20f, 12f, 156f, 38f, paint);
            cs.drawRect(value - margin, value + fm.top - margin,
                    value + height_ + margin, value + fm.bottom + margin, paint);
            cs.drawText(dateTime + "[" + "10" + "] " + String.valueOf(copleteValue), value, value, tPaint);
            try {
                dest.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(path)));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            bmp = BitmapFactory.decodeFile(path);
            temp_img.setImageBitmap(bmp);
           /* if (mapBitmap != null) {
                temp_map.setImageBitmap(mapBitmap);
            }*/

            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            //Cause the view to re-layout
            view.measure(measuredWidth, measuredHeight);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            //Create a bitmap backed Canvas to draw the view into
            Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            view.draw(c);
            return b;
        } finally {

        }
    }


}
