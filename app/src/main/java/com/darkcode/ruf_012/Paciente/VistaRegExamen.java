package com.darkcode.ruf_012.Paciente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

/**
 * Created by NativoLink on 3/7/17.
 */

public class VistaRegExamen extends Fragment {

    private String[] arraySpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.reg_exa_clinico, container,false);

        TextView tvNombreP = (TextView)rootView.findViewById(R.id.tvNombreP);
        tvNombreP.setText( ((MainActivity) getContext()).getNOMBRES());

        Spinner spLabios = (Spinner)rootView.findViewById(R.id.spLabios);
        Spinner spFistulas = (Spinner)rootView.findViewById(R.id.spFistulas);
        Spinner spMovilidad = (Spinner)rootView.findViewById(R.id.spMovilidad);
        Spinner spCarrillos = (Spinner)rootView.findViewById(R.id.spCarrillos);
        Spinner spPigmentaciones = (Spinner)rootView.findViewById(R.id.spPigmentaciones);
        Spinner spSupuracion = (Spinner)rootView.findViewById(R.id.spSupuracion);



        Spinner spLengua = (Spinner)rootView.findViewById(R.id.spLengua);
        Spinner spMalformaciones = (Spinner)rootView.findViewById(R.id.spMalformaciones);
        Spinner spPisoBoca = (Spinner)rootView.findViewById(R.id.spPisoBoca);
        Spinner spPaladarD = (Spinner)rootView.findViewById(R.id.spPaladarD);
        Spinner spTartaro = (Spinner)rootView.findViewById(R.id.spTartaro);
        Spinner spBruxismo = (Spinner)rootView.findViewById(R.id.spBruxismo);

        Spinner spPaladarB = (Spinner)rootView.findViewById(R.id.spPaladarB);
        Spinner spManchas = (Spinner)rootView.findViewById(R.id.spManchas);
        Spinner spGarganta = (Spinner)rootView.findViewById(R.id.spGarganta);

        this.arraySpinner = new String[] {
                "Anormal", "Normal"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, arraySpinner);
        spLabios.setAdapter(adapter);
        spLabios.setSelection(1);

        spFistulas.setAdapter(adapter);
        spFistulas.setSelection(1);

        spMovilidad.setAdapter(adapter);
        spMovilidad.setSelection(1);

        spCarrillos.setAdapter(adapter);
        spCarrillos.setSelection(1);

        spPigmentaciones.setAdapter(adapter);
        spPigmentaciones.setSelection(1);

        spSupuracion.setAdapter(adapter);
        spSupuracion.setSelection(1);



        spLengua.setAdapter(adapter);
        spLengua.setSelection(1);

        spMalformaciones.setAdapter(adapter);
        spMalformaciones.setSelection(1);

        spPisoBoca.setAdapter(adapter);
        spPisoBoca.setSelection(1);

        spPaladarD.setAdapter(adapter);
        spPaladarD.setSelection(1);

        spTartaro.setAdapter(adapter);
        spTartaro.setSelection(1);

        spBruxismo.setAdapter(adapter);
        spBruxismo.setSelection(1);


        spPaladarB.setAdapter(adapter);
        spPaladarB.setSelection(1);

        spManchas.setAdapter(adapter);
        spManchas.setSelection(1);

        spGarganta.setAdapter(adapter);
        spGarganta.setSelection(1);


        return rootView;
    }
}
