package com.example.ibrah.guild2.Fragmentler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibrah.guild2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArakadasFragment extends Fragment {


    public ArakadasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arakadas, container, false);
    }

}