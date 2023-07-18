package com.app.naveenbhanuka.view_profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.naveenbhanuka.databinding.ActivityViewProfileBinding;
import com.app.naveenbhanuka.models.User;
import com.app.naveenbhanuka.utils.Constants;
import com.google.gson.Gson;

public class ViewProfileActivity extends AppCompatActivity {

    private ActivityViewProfileBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setData();
    }

    private void setData() {

        User user = getUserFromSharedPreferences(ViewProfileActivity.this);
        binding.tvId.setText(user.getId());
        binding.tvEmail.setText(user.getEmail());
        binding.tvName.setText(user.getName());
        binding.tvDob.setText(user.getDob());
        binding.tvGender.setText(user.getGender());
        binding.tvCompany.setText(user.getCompany());
        binding.tvPosition.setText(user.getPosition());

    }

    public User getUserFromSharedPreferences(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_OBJECT, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString(Constants.USER_JSON, null);
        return gson.fromJson(userJson, User.class);
    }
}
