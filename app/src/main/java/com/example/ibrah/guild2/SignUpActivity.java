package com.example.ibrah.guild2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class SignUpActivity extends AppCompatActivity  {

    private static final int REQUEST_READ_CONTACTS = 0;



    // UI references.
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mIsimView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmView;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mIsimView = (AutoCompleteTextView)findViewById(R.id.isim);
        mPasswordConfirmView = (EditText)findViewById(R.id.password_confirm);
        mPasswordView = (EditText) findViewById(R.id.password);

    }

    public void SignUp(View view) {

        String userName = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mPasswordConfirmView.getText().toString();

        if(userName.equals("") || password.equals("") || confirmPassword.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Bu Bölge Boş Olamaz", Toast.LENGTH_LONG).show();

        }else if(!password.equals(confirmPassword))
        {
            Toast.makeText(getApplicationContext(), "Şifreler birbiriyle uyuşmuyor", Toast.LENGTH_LONG).show();

        }
        else
        {
            // Save the Data in Database
            loginDataBaseAdapter.insertEntry(userName, password);
            Toast.makeText(getApplicationContext(), "Hesabınız oluşturuldu", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}


