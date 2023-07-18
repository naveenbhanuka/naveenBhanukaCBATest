package com.app.naveenbhanuka.api;

import com.app.naveenbhanuka.models.ResponseItem;
import com.app.naveenbhanuka.models.User;
import com.app.naveenbhanuka.models.UserData;
import com.app.naveenbhanuka.utils.Constants;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserInterface {

    @POST("/")
    Call<ResponseItem> getUser(
            @Body JsonObject credentials
    );

}
