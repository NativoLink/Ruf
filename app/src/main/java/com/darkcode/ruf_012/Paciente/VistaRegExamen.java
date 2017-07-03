package com.darkcode.ruf_012.Paciente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darkcode.ruf_012.R;

/**
 * Created by NativoLink on 3/7/17.
 */

public class VistaRegExamen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.reg_exa_clinico, container,false);

        return rootView;
    }
}
