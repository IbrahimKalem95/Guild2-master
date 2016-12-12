package com.example.ibrah.guild2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    public TabLayout tabLayout ;
    ViewPager viewPager;
    ViewPagerAdaptor viewPagerAdaptor;
    FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager());
        viewPagerAdaptor.addFragments(new AnaSayfa(),"Ana Sayfa");
        viewPagerAdaptor.addFragments(new GuildFragment(),"Guild");
        viewPagerAdaptor.addFragments(new ArakadasFragment(),"Arkadaslar");
        viewPagerAdaptor.addFragments(new ProfilFragment(),"Profil");


        viewPager.setAdapter(viewPagerAdaptor);
        tabLayout.setupWithViewPager(viewPager);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Yakındaki yerler bulunuyor",Toast.LENGTH_LONG).show();
                Snackbar.make(view,"Geri dönuldu",Snackbar.LENGTH_LONG).setAction("Action",null).show();

                Intent ıntent = new Intent(MainActivity.this,Check_In.class);
                startActivity(ıntent);
            }
        });
    }
}
