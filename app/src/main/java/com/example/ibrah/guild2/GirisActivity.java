package com.example.ibrah.guild2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrah.guild2.Kayit.LoginActivity;
import com.example.ibrah.guild2.Kayit.SignUpActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class GirisActivity extends AppCompatActivity {

    LoginButton loginButton;
    TextView txtresult ;
    CallbackManager callbackManager;
    Profile profile;
    int flag = 0;
    int result = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_giris_activty);
        loginButton = (LoginButton)findViewById(R.id.fb_login_bn);
        profile = Profile.getCurrentProfile();
        callbackManager = CallbackManager.Factory.create();
        SharedPreferences temp = getSharedPreferences("flag",0);
        final SharedPreferences.Editor editor = temp.edit();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                editor.putInt("flag",flag+1);
                editor.commit();
                Intent ıntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(ıntent);
            }

            @Override
            public void onCancel() {
               Toast.makeText(getApplicationContext(),"Geri gelindi",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
            }
        });

        result = temp.getInt("flag",0);
        if( result!= 0 )
        {
            Intent ıntent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(ıntent);
        }


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
    public void GirisYap(View view) {
        Intent intentGirisYap = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intentGirisYap);
    }

    public void KayıtOL(View view) {
        Intent intentKayıtOl= new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intentKayıtOl);
    }
}
