package com.example.monprojet;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences("AppKey",0);
        editor = sharedPreferences.edit();
        editor.apply();
    }
    public void setlogin(boolean login){
        editor.putBoolean("KEY_LOGIN",login);
        editor.commit();

    }
    public boolean getlogin(){
        return sharedPreferences.getBoolean("KEY_LOGIN",false);
    }

}
