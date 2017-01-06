package com.example.ibrah.guild2.Fragmentler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibrah.guild2.LoginDataBaseAdapter;
import com.example.ibrah.guild2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    public ProfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_profil, container, false);
    }



}
