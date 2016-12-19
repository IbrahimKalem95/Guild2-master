package com.example.ibrah.guild2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    TextView textView ;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_giris_activty);

        loginButton = (LoginButton)findViewById(R.id.fb_login_bn);

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                Intent ıntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(ıntent);

            }

            @Override
            public void onCancel() {
                textView.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
            }
        });

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
