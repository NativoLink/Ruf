package com.darkcode.ruf_012;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by usuario on 07/05/2017.
 */

public class VistaConsultasPagadas extends Fragment {
    public VistaConsultasPagadas() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reg_pago, container,false);

        return view;
    }
}
