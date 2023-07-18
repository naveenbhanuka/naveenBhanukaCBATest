package com.app.naveenbhanuka.repository;

import com.app.naveenbhanuka.api.RetrofitInstance;
import com.app.naveenbhanuka.api.UserInterface;
import com.app.naveenbhanuka.models.ResponseItem;
import com.app.naveenbhanuka.models.User;
import com.google.gson.JsonObject;

import retrofit2.Call;

public class UserRepository {
    private UserInterface userInterface;

    public UserRepository() {
        userInterface = RetrofitInstance.getUserAPI();
    }

    public Call<ResponseItem> getUser(JsonObject credentials) {
        return userInterface.getUser(credentials);
    }
}