package com.darkcode.ruf_012;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.darkcode.ruf_012.Tratamientos.AdapterTratsDePlanDetalle;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 15/7/17.
 */

public class VistaDetallePlan  extends Fragment {

    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    TratamientoService servicio  = restadpter.create(TratamientoService.class);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.trats_list_detalle, container, false);
        final ListView lvresult = (ListView)view.findViewById(R.id.lvTratsE);

        int id_plan = Integer.valueOf(this.getArguments().getString("id_plan"));
        int id_paciente = Integer.valueOf(this.getArguments().getString("id_paciente"));

        TextView tvNombreP = (TextView)view.findViewById(R.id.tvNombreP);
        tvNombreP.setText( ((MainActivity) getContext()).getNOMBRES());

        servicio.getTratsDeUnPlan(id_paciente, id_plan, new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
                AdapterTratsDePlanDetalle listAdapter = new AdapterTratsDePlanDetalle(getContext(),tratamientos);
                lvresult.setAdapter(listAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }
}