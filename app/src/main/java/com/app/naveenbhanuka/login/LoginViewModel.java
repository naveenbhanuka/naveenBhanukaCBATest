package com.app.naveenbhanuka.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.naveenbhanuka.models.ResponseItem;
import com.app.naveenbhanuka.repository.UserRepository;
import com.app.naveenbhanuka.utils.SingleLiveEvent;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private SingleLiveEvent<ResponseItem> userLiveData;
    private UserRepository userRepository;

    public LoginViewModel() {
        userLiveData = new SingleLiveEvent<>();
        userRepository = new UserRepository();
    }

    public LiveData<ResponseItem> getUser(JsonObject credentials) {
        Call<ResponseItem> call = userRepository.getUser(credentials);
        call.enqueue(new Callback<ResponseItem>() {
            @Override
            public void onResponse(Call<ResponseItem> call, Response<ResponseItem> response) {
                if (response.isSuccessful()) {
                    userLiveData.postValue(response.body());
                } else {
                    userLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseItem> call, Throwable t) {
                userLiveData.postValue(null);
            }
        });

        return userLiveData;
    }

}
