package com.afollestad.materialcamera.util;

import static com.afollestad.materialcamera.util.Degrees.DEGREES_270;
import static com.afollestad.materialcamera.util.Degrees.DEGREES_90;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.afollestad.materialcamera.ICallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tomiurankar on 06/03/16.
 */
public class ImageUtil {
    /**
     * Saves byte[] array to disk
     *
     * @param input    byte array
     * @param output   path to output file
     * @param callback will always return in originating thread
     */
    public static void saveToDiskAsync(
            final byte[] input, final File output, final ICallback callback) {
        final Handler handler = new Handler();
        new Thread() {
            @Override
            public void run() {
                try {
                    FileOutputStream outputStream = new FileOutputStream(output);
                    outputStream.write(input);
                    outputStream.flush();
                    outputStream.close();

                    handler.post(
                            new Runnable() {
                                @Override
                                public void run() {
                                    callback.done(null);
                                }
                            });
                } catch (final Exception e) {
                    handler.post(
                            new Runnable() {
                                @Override
                                public void run() {
                                    callback.done(e);
                                }
                            });
                }
            }
        }.start();
    }

    /**
     * Rotates the bitmap per their EXIF flag. This is a recursive function that will be called again
     * if the image needs to be downsized more.
     *
     * @param inputFile Expects an JPEG file if corrected orientation wants to be set.
     * @return rotated bitmap or null
     */
    @Nullable
    public static Bitmap getRotatedBitmap(String inputFile, int reqWidth, int reqHeight) {
        final int rotationInDegrees = getExifDegreesFromJpeg(inputFile);

        final BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(inputFile, opts);
        opts.inSampleSize = calculateInSampleSize(opts, reqWidth, reqHeight, rotationInDegrees);
        opts.inJustDecodeBounds = false;

        final Bitmap origBitmap = BitmapFactory.decodeFile(inputFile, opts);

        if (origBitmap == null) return null;

        Matrix matrix = new Matrix();
        matrix.preRotate(rotationInDegrees);
        // we need not check if the rotation is not needed, since the below function will then return the same bitmap. Thus no memory loss occurs.

        return Bitmap.createBitmap(
                origBitmap, 0, 0, origBitmap.getWidth(), origBitmap.getHeight(), matrix, true);
    }


    public static Bitmap getRotatedBitmap2(String inputFile, int reqWidth, int reqHeight) {
        try {
            final int rotationInDegrees = getExifDegreesFromJpeg(inputFile);

            final BitmapFactory.Options opts = new BitmapFactory.Options();
            //opts.inJustDecodeBounds = true;
            //BitmapFactory.decodeFile(inputFile, opts);
            //opts.inSampleSize = calculateInSampleSize(opts, reqWidth, reqHeight, rotationInDegrees);
            opts.inSampleSize = Math.max(reqWidth / 800, reqHeight / 500);
            opts.inJustDecodeBounds = false;
            opts.inMutable = true;
            final Bitmap origBitmap = BitmapFactory.decodeFile(inputFile, opts);

            if (origBitmap == null) return null;

            Matrix matrix = new Matrix();
            matrix.preRotate(rotationInDegrees);
            RectF inRect = new RectF(0, 0, origBitmap.getWidth(), origBitmap.getHeight());
            RectF outRect = new RectF(0, 0, 800, 500);
            matrix.setRectToRect(inRect, outRect, Matrix.ScaleToFit.CENTER);
            float[] values = new float[9];
            matrix.getValues(values);

            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            String dateTime = sdf.format(Calendar.getInstance().getTime());
            // we need not check if the rotation is not needed, since the below function will then return the same bitmap. Thus no memory loss occurs.

            Canvas cs = new Canvas(origBitmap);
            Paint tPaint = new Paint();
            tPaint.setTextSize(30);
            //tPaint.setTextAlign(Paint.Align.RIGHT);
            tPaint.setColor(Color.RED);
            tPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            //cs.drawBitmap(bmp, 0f, 0f, null);
            int full_height = origBitmap.getHeight();
            int full_width = origBitmap.getWidth();
            int cal_height, cal_width;
            if (full_width > full_height) {

                cal_height = full_height - (origBitmap.getHeight() / 10);
                cal_width = (full_width - ((origBitmap.getWidth() / 10) * 3)) - 60;

            } else {
                cal_height = full_width - ((origBitmap.getWidth() / 10) * 3) - 60;
                cal_width = full_height - (origBitmap.getHeight() / 10);
            }

            cs.drawBitmap(origBitmap, 0f, 0f, null);
            float height = tPaint.measureText("yY");
            cs.drawText(dateTime, cal_width, cal_height, tPaint);


            FileOutputStream out = new FileOutputStream(new File(inputFile));
            origBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            return Bitmap.createBitmap(
                    origBitmap, 0, 0, origBitmap.getWidth(), origBitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight, int rotationInDegrees) {

        // Raw height and width of image
        final int height;
        final int width;
        int inSampleSize = 1;

        // Check for rotation
        if (rotationInDegrees == DEGREES_90 || rotationInDegrees == DEGREES_270) {
            width = options.outHeight;
            height = options.outWidth;
        } else {
            height = options.outHeight;
            width = options.outWidth;
        }

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    private static int getExifDegreesFromJpeg(String inputFile) {
        try {
            final ExifInterface exif = new ExifInterface(inputFile);
            final int exifOrientation =
                    exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
                return 90;
            } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
                return 180;
            } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
                return 270;
            }
        } catch (IOException e) {
            Log.e("exif", "Error when trying to get exif data from : " + inputFile, e);
        }
        return 0;
    }
}
