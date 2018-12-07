package com.cpm.upload;

import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

import com.squareup.okhttp.RequestBody;


import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


/**
 * Created by upendrak on 16-05-2017.
 */

public interface PostApi {

    @POST("Uploadimages")
    Call<String> getUploadImage(@Body RequestBody request);

    @POST("GET_StoreLayout_IMAGES")
    Call<String> getStoreImage(@Body RequestBody request);

    @POST("GET_Store_SecondaryWindowImage")
    Call<String> getSecondaryWindowImage(@Body RequestBody request);

    @POST("Upload_StoreGeoTag_IMAGES")
    Call<String> getGeoTagImage(@Body RequestBody request);

}
