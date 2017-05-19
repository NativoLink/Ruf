package com.darkcode.ruf_012;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.Tratamientos.AdapterTratamientos;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta;
import com.darkcode.ruf_012.Tratamientos.ListRegPlanTratamientos;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta.*;


/**
 * Created by NativoLink on 22/2/17.
 */


public class VistaRegPlanTratamiento extends Fragment {

    ArrayList<AdapterTratsConsulta.checkItem> callback_trats;
    View view;

    int id_plan = 0;

    public VistaRegPlanTratamiento() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.trast_list, container, false);

        Button btnGuardar = (Button)view.findViewById(R.id.btnGuardarPlan);
        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
        final TratamientoService servicio = restadpter.create(TratamientoService.class);
        final ArrayList<?>[] checks = new ArrayList<?>[1];

        servicio.getTratamientos(new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
//                Toast.makeText(ListRegPlanTratamientos.this,"TRAT "+tratamientos.size(), Toast.LENGTH_LONG).show();
                ListView lvresult = (ListView)view.findViewById(R.id.lvTrats);
                AdapterTratamientos listAdapter = new AdapterTratamientos(getContext(), tratamientos);
                lvresult.setAdapter(listAdapter);


            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(),"ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback_trats = ((MainActivity) getContext()).getItemRegPlan();

                int id_paciente = 1; // ESTA VARIABLE NO LA TENEMOS RECIVIDA AUN
                servicio.regPlan(id_paciente, "La descripcion tampoco esta", new Callback<Integer>() {
                    @Override
                    public void success(Integer integer, Response response) {
                        id_plan = integer;
                        Toast.makeText(getContext(),"Plan: "+integer, Toast.LENGTH_LONG).show();
                        for(int i=0; i< callback_trats.size(); i++) {
                            Log.v("callbacks -->","Posi =>> "+callback_trats.get(i).getPosi()+" Nomb =>>"+callback_trats.get(i).getNombreTrat()+" Monto =>>"+callback_trats.get(i).getCosto()+" Cant =>>"+callback_trats.get(i).getCantidad());
                            int plan = id_plan;
                            int id_trat = callback_trats.get(i).getId_tratamiento();
                            int cant = callback_trats.get(i).getCantidad();
                            int costo = callback_trats.get(i).getCosto();
                            servicio.regTratsDeUnPlan(plan, id_trat, cant, costo, "Descripcion", new Callback<String>() {
                                @Override
                                public void success(String s, Response response) {
                                    Toast.makeText(getContext(),"Result: "+s.toString(), Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Toast.makeText(getContext(),"ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(),"ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        return view;
    }

}


