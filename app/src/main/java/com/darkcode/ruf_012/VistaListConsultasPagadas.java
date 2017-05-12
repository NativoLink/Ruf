package com.darkcode.ruf_012;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darkcode.ruf_012.Pagos.AdapterRegPago;

/**
 * Created by usuario on 12/05/2017.
 */

public class VistaListConsultasPagadas extends Fragment{
    AdapterRegPago listAdapter= null;

    public VistaListConsultasPagadas() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
