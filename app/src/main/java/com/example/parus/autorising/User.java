package com.example.parus.autorising;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Parcelable, Serializable {
    public String first_name,last_name,email,token;
    public User(){

    }
    public User(String first_name, String last_name, String email, String token){
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.token=token;
    }

    protected User(Parcel in) {
        first_name = in.readString();
        last_name = in.readString();
        email = in.readString();
    }

    public User(HashMap<String, String> map){
        this.first_name=map.get("first_name");
        this.last_name=map.get("last_name");
        this.email=map.get("email");
        this.token=map.get("token");
    }

    public static final Creator CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle=new Bundle();
        bundle.putString("first_name",first_name);
        bundle.putString("last_name",last_name);
        bundle.putString("email_name",email);
        dest.writeBundle(bundle);
    }

    public static User parseFromJson(JsonObject jsonObject){
        return new User(
                jsonObject.get("first_name").getAsString(),
                jsonObject.get("last_name").getAsString(),
                jsonObject.get("email").getAsString(),
                jsonObject.get("token").getAsString());
    }


}
