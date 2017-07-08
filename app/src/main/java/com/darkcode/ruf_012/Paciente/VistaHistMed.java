package com.darkcode.ruf_012.Paciente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

/**
 * Created by NativoLink on 7/7/17.
 */

public class VistaHistMed extends Fragment {

    private String[] arraySpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.reg_hist_medica, container,false);
//
//        TextView tvNombreP = (TextView)rootView.findViewById(R.id.tvNombreP);
//        tvNombreP.setText( ((MainActivity) getContext()).getNOMBRES());
//
        Spinner spEstadoSalud = (Spinner)rootView.findViewById(R.id.spEstadoSalud);
        Spinner spBajoTra = (Spinner)rootView.findViewById(R.id.spBajoTra);
        Spinner spAlergico = (Spinner)rootView.findViewById(R.id.spAlergico);
        Spinner spEnfermedadS = (Spinner)rootView.findViewById(R.id.spEnfermedadS);
        Spinner spEnjuague = (Spinner)rootView.findViewById(R.id.spEnjuague);
        Spinner spHilo = (Spinner)rootView.findViewById(R.id.spHilo);


        this.arraySpinner = new String[] {
                "  SI  ", "  NO  "
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item, arraySpinner);
        spEstadoSalud.setAdapter(adapter);
        spEstadoSalud.setSelection(1);

        spBajoTra.setAdapter(adapter);
        spBajoTra.setSelection(1);

        spAlergico.setAdapter(adapter);
        spAlergico.setSelection(1);

        spEnfermedadS.setAdapter(adapter);
        spEnfermedadS.setSelection(1);

        spEnjuague.setAdapter(adapter);
        spEnjuague.setSelection(1);

        spHilo.setAdapter(adapter);
        spHilo.setSelection(1);






        return rootView;
    }
}