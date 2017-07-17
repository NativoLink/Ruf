package com.darkcode.ruf_012.Paciente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

/**
 * Created by NativoLink on 3/7/17.
 */

public class VistaRegExamen extends Fragment {

    private String[] arraySpinner;
    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    PacienteService servicio  = restadpter.create(PacienteService.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.reg_exa_clinico, container,false);

        TextView tvNombreP = (TextView)rootView.findViewById(R.id.tvNombreP);
        tvNombreP.setText( ((MainActivity) getContext()).getNOMBRES());

        final Spinner spLabios         = (Spinner)rootView.findViewById(R.id.spLabios);
        final Spinner spFistulas       = (Spinner)rootView.findViewById(R.id.spFistulas);
        final Spinner spMovilidad      = (Spinner)rootView.findViewById(R.id.spMovilidad);
        final Spinner spCarrillos      = (Spinner)rootView.findViewById(R.id.spCarrillos);
        final Spinner spPigmentaciones = (Spinner)rootView.findViewById(R.id.spPigmentaciones);
        final Spinner spSupuracion     = (Spinner)rootView.findViewById(R.id.spSupuracion);

        final Spinner spLengua         = (Spinner)rootView.findViewById(R.id.spLengua);
        final Spinner spMalformaciones = (Spinner)rootView.findViewById(R.id.spMalformaciones);
        final Spinner spPisoBoca       = (Spinner)rootView.findViewById(R.id.spPisoBoca);
        final Spinner spPaladarD       = (Spinner)rootView.findViewById(R.id.spPaladarD);
        final Spinner spTartaro        = (Spinner)rootView.findViewById(R.id.spTartaro);
        final Spinner spBruxismo       = (Spinner)rootView.findViewById(R.id.spBruxismo);

        final Spinner spPaladarB       = (Spinner)rootView.findViewById(R.id.spPaladarB);
        final Spinner spManchas        = (Spinner)rootView.findViewById(R.id.spManchas);
        final Spinner spGarganta       = (Spinner)rootView.findViewById(R.id.spGarganta);

        final Spinner spTumoraciones   = (Spinner)rootView.findViewById(R.id.spTumoraciones);
        final Spinner spBolsasP        = (Spinner)rootView.findViewById(R.id.spBolsaP);

        final EditText etMudados       = (EditText)rootView.findViewById(R.id.etmudados);
        final EditText etSanos         = (EditText)rootView.findViewById(R.id.etsanos);

        final EditText etobsRadiografica = (EditText)rootView.findViewById(R.id.etobsRadiografica);
        final EditText etObservacion = (EditText)rootView.findViewById(R.id.etObservacion);

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

        spTumoraciones.setAdapter(adapter);
        spTumoraciones.setSelection(1);

        spBolsasP.setAdapter(adapter);
        spBolsasP.setSelection(1);


        Button btnRegistrar =  (Button)rootView.findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labios       = spLabios.getSelectedItem().toString();
                String fistulas     = spFistulas.getSelectedItem().toString();
                String carrillos    = spCarrillos.getSelectedItem().toString();
                String pigmentacion = spPigmentaciones.getSelectedItem().toString();
                String supuracion   = spSupuracion.getSelectedItem().toString();
                String movilidad    = spMovilidad.getSelectedItem().toString();
                String lengua       = spLengua.getSelectedItem().toString();
                String malfor       = spMalformaciones.getSelectedItem().toString();
                String pisoB        = spPisoBoca.getSelectedItem().toString();
                String paladarD     = spPaladarD.getSelectedItem().toString();
                String tartaro      = spTartaro.getSelectedItem().toString();
                String bruxi        = spBruxismo.getSelectedItem().toString();
                String paladarB     = spPaladarB.getSelectedItem().toString();
                String manchas      = spManchas.getSelectedItem().toString();
                String garganta     = spGarganta.getSelectedItem().toString();

                String obsR         = etobsRadiografica.getText().toString();
                String obs          =  etObservacion.getText().toString();

                String mudados      = etMudados.getText().toString();
                String sanos        =  etSanos.getText().toString();

                String tumores      = spTumoraciones.getSelectedItem().toString();
                String bolsasP      =  spBolsasP.getSelectedItem().toString();

//                Toast.makeText(getContext(),"Result: "+labios, Toast.LENGTH_LONG).show();

                servicio.regExamenCli(
                        ((MainActivity) getContext()).getId_pacienteA(),
                        labios,
                        carrillos,
                        lengua,
                        pisoB,
                        paladarD,
                        paladarB,
                        garganta,
                        tumores,
                        fistulas,
                        pigmentacion,
                        malfor,
                        bolsasP,
                        tartaro,
                        manchas,
                        movilidad, supuracion,
                        mudados,
                        sanos,
                        bruxi,
                        obsR,
                        obs,
                        new Callback<String>() {
                            @Override
                            public void success(String s, Response response) {
                                Toast.makeText(getContext()," EXAMEN: "+s, Toast.LENGTH_LONG).show();
                                Log.v("EXAMEN CLINICO","EXAMEN ==> "+s);
                            }

                            @Override
                            public void failure(RetrofitError error) {
//                                Toast.makeText(getContext(),"ERROR EXAMEN : "+error, Toast.LENGTH_LONG).show();
                                Log.v("EXAMEN CLINICO","ERROR EXAMEN ==> "+error);
                            }
                        }

                );


            }
        });


        return rootView;
    }
}
