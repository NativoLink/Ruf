package com.darkcode.ruf_012.Paciente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.darkcode.ruf_012.R;

/**
 * Created by NativoLink on 3/7/17.
 */

public class VistaRegExamen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.reg_exa_clinico, container,false);

//        Spinner spLabios = (Spinner)rootView.findViewById(R.id.spLabios);
//        Spinner spFistulas = (Spinner)rootView.findViewById(R.id.spFistulas);
//        Spinner spMovilidad = (Spinner)rootView.findViewById(R.id.spMovilidad);
//        Spinner spCarrillos = (Spinner)rootView.findViewById(R.id.spCarrillos);
//        Spinner spPigmentaciones = (Spinner)rootView.findViewById(R.id.spPigmentaciones);
//        Spinner spSupuracion = (Spinner)rootView.findViewById(R.id.spSupuracion);

        return rootView;
    }
}
