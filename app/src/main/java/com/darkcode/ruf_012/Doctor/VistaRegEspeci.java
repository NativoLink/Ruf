package com.darkcode.ruf_012.Doctor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darkcode.ruf_012.R;

import retrofit.RestAdapter;

/**
 * Created by NativoLink on 15/7/17.
 */

public class VistaRegEspeci extends Fragment {


    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reg_exa_clinico, container, false);

        return rootView;
    }

}