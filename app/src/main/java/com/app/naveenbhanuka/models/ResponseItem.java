package com.app.naveenbhanuka.models;

import java.util.List;

public class ResponseItem {

    private int res_code;
    private String res_desc;
    private User user_data;

    public User getUser_data() {
        return user_data;
    }

    public void setUser_data(User user_data) {
        this.user_data = user_data;
    }


    public int getRes_code() {
        return res_code;
    }

    public void setRes_code(int res_code) {
        this.res_code = res_code;
    }

    public String getRes_desc() {
        return res_desc;
    }

    public void setRes_desc(String res_desc) {
        this.res_desc = res_desc;
    }

}