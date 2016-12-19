package com.example.ibrah.guild2.Fragmentler;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibrah.guild2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnaSayfa extends Fragment {


    public AnaSayfa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        return inflater.inflate(R.layout.fragment_ana_sayfa, container, false);
    }

}
