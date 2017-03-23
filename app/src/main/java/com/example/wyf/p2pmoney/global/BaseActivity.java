package com.example.wyf.p2pmoney.global;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.wyf.p2pmoney.bean.Login;

import butterknife.ButterKnife;

/**
 * Created by WYF on 2017/3/22.
 */

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initTitle();
        initData();
    }

    public void gotoActivity(Class clazz, Bundle bundle){
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtra("param", bundle);
        }
        startActivity(intent);
    }

    public Login getLogin(){
        Login login = new Login();
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        login.UF_ACC = sp.getString("uf_acc", "");
        login.UF_AVATAR_URL = sp.getString("uf_avatar_url", "");
        login.UF_IS_CERT = sp.getString("uf_is_cert", "");
        login.UF_PHONE = sp.getString("uf_phone", "");
        return login;
    }

    public void saveLogin(Login login){
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("UF_ACC", login.UF_ACC);
        editor.putString("UF_AVATAR_URL", login.UF_AVATAR_URL);
        editor.putString("UF_IS_CERT", login.UF_IS_CERT);
        editor.putString("UF_PHONE", login.UF_PHONE);
        editor.commit();
    }

    public void closeCurrent(){
        AppManager.getInstance().removeCurent();
    }

    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int getLayoutId();
}
