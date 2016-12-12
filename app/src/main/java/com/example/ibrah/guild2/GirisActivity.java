package com.example.ibrah.guild2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GirisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_activty);

        Button girisyap;
        Button kayıtol;

        girisyap= (Button)findViewById(R.id.girisyap);
        kayıtol= (Button)findViewById(R.id.kayıtol);

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
