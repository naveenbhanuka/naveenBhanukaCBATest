package com.app.naveenbhanuka.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.naveenbhanuka.MainActivity;
import com.app.naveenbhanuka.R;
import com.app.naveenbhanuka.api.RetrofitInstance;
import com.app.naveenbhanuka.api.UserInterface;
import com.app.naveenbhanuka.databinding.ActivityLoginBinding;
import com.app.naveenbhanuka.home.HomeActivity;
import com.app.naveenbhanuka.models.ResponseItem;
import com.app.naveenbhanuka.models.User;
import com.app.naveenbhanuka.models.UserData;
import com.app.naveenbhanuka.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kotlin.Unit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        initUI();
    }

    private void initUI() {
        binding.btnLogin.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.btn_login:
                    if (isValidForm()) {
                       login();
                    }
            }
        }
    }

    private void login() {
        String userName = binding.etUserName.getText().toString();
        String password = binding.etUserPassword.getText().toString();

        UserData credentials = new UserData();
        credentials.setUsername(userName);
        credentials.setPassword(password);

        Gson gson = new Gson();
        String credentialsJson = gson.toJson(credentials);
        JsonObject jsonObject = gson.fromJson(credentialsJson, JsonObject.class);

        loginViewModel.getUser(jsonObject).observe(this, new Observer<ResponseItem>() {
            @Override
            public void onChanged(ResponseItem responseItem) {
                if (responseItem != null) {
                    User user = responseItem.getUser_data();
                    saveUserToSharedPreferences(LoginActivity.this,user);
                    gotoHome();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void saveUserToSharedPreferences(@NonNull Context context, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_OBJECT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(Constants.USER_JSON, userJson);
        editor.apply();
    }

    private boolean isValidForm() {
        String userName = binding.etUserName.getText().toString();
        if (userName.length() < 2) {
            Toast.makeText(this, getString(R.string.error_user_name_invalid), Toast.LENGTH_SHORT).show();
            return false;
        }
        String password = binding.etUserPassword.getText().toString();
        if (password.length() < 3) {
            Toast.makeText(this, getString(R.string.error_password_length), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void gotoHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }
}
