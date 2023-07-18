package com.app.naveenbhanuka.api;

import com.app.naveenbhanuka.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static UserInterface api;

    public static UserInterface getUserAPI() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserInterface.class);
        }
        return api;
    }
}