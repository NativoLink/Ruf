package com.darkcode.ruf_012.Paciente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Field;

/**
 * Created by NativoLink on 7/7/17.
 */

public class VistaHistMed extends Fragment {

    private String[] arraySpinner;
    RestAdapter restadpter;
    Spinner spEstadoSalud,spBajoTra,spAlergico,spEnfermedadS,spEnjuague,spHilo;
    String txtEnfermedad,
            txtTratamiento,
            txtmedico,
            txtTipoEnefermedad,
            txtTipoAlergia,
            txtnumcepillar,
            txtREVISION,
            txttratamientoRealizado,
            txt_spEstadoSalud,
            txt_spBajoTra,
            txt_spAlergico,
            txt_spEnfermedadS,
            txt_spEnjuague,
            txt_spHilo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.reg_hist_medica, container,false);
//
//        TextView tvNombreP = (TextView)rootView.findViewById(R.id.tvNombreP);
//        tvNombreP.setText( ((MainActivity) getContext()).getNOMBRES());
//
        spEstadoSalud = (Spinner)rootView.findViewById(R.id.spEstadoSalud);
        spBajoTra = (Spinner)rootView.findViewById(R.id.spBajoTra);
        spAlergico = (Spinner)rootView.findViewById(R.id.spAlergico);
        spEnfermedadS = (Spinner)rootView.findViewById(R.id.spEnfermedadS);
        spEnjuague = (Spinner)rootView.findViewById(R.id.spEnjuague);
        spHilo = (Spinner)rootView.findViewById(R.id.spHilo);


        final EditText  etEnfermedad = (EditText)rootView.findViewById(R.id.etEnfermedad);
        final EditText  etTratamiento = (EditText)rootView.findViewById(R.id.etTratamiento);
        final EditText  etmedico = (EditText)rootView.findViewById(R.id.etmedico);
        final EditText  etTipoAlergia = (EditText)rootView.findViewById(R.id.etTipoAlergia);
        final EditText  etTipoEnefermedad = (EditText)rootView.findViewById(R.id.etTipoEnefermedad);
        final EditText  ettratamientoRealizado = (EditText)rootView.findViewById(R.id.ettratamientoRealizado);

        txtEnfermedad = etEnfermedad.getText().toString();
        txtTratamiento = etTratamiento.getText().toString();
        txtmedico = etmedico.getText().toString();
        txtTipoEnefermedad = etTipoEnefermedad.getText().toString();
        txtTipoAlergia = etTipoAlergia.getText().toString();

        txt_spEstadoSalud = spEstadoSalud.getSelectedItem().toString();
        txt_spBajoTra = spBajoTra.getSelectedItem().toString();
        txt_spAlergico = spAlergico.getSelectedItem().toString();
        txt_spEnfermedadS = spEnfermedadS.getSelectedItem().toString();


        final EditText  etnumcepillar = (EditText)rootView.findViewById(R.id.etnumcepillar);
        txtnumcepillar = etnumcepillar.getText().toString();
        final EditText  etREVISION = (EditText)rootView.findViewById(R.id.etREVISION);
        txtREVISION = etREVISION.getText().toString();
        txt_spEnjuague = spEnjuague.getSelectedItem().toString();
        txt_spHilo = spHilo.getSelectedItem().toString();
        txttratamientoRealizado = ettratamientoRealizado.getText().toString();

        Button btnReg = (Button)rootView.findViewById(R.id.btnRegistrar);

        restadpter =((MainActivity) getContext()).getRestadpter();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PacienteService servicio = restadpter.create(PacienteService.class);
                int id_paciente = ((MainActivity) getContext()).getId_pacienteA();

                servicio.regHistoriaMed(
                        id_paciente,
                        txt_spEstadoSalud,
                        txt_spEnfermedadS,
                        txt_spBajoTra,
                        txtTratamiento,
                        txtmedico,
                        txt_spAlergico,
                        txtTipoAlergia,
                        txt_spEnfermedadS,
                        txtTipoEnefermedad, new Callback<String>() {
                            @Override
                            public void success(String s, Response response) {
                                Log.v("VistaHistMedi","REG =>regHistoriaMed ");
                                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.v("VistaHistMedi","ERROR =>regHistoriaMed ");

                            }
                        }
                );

                servicio.regHabitoHig(
                        id_paciente,
                        txtnumcepillar,
                        txt_spEnjuague,
                        txt_spHilo,
                        txtREVISION,
                        txttratamientoRealizado,
                        new Callback<String>() {
                            @Override
                            public void success(String s, Response response) {
                                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                                Log.v("VistaHistMedi","REG =>regHabitoHig ");
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.v("VistaHistMedi","ERROR =>regHabitoHig ");
                            }
                        }
                );


            }
        });


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