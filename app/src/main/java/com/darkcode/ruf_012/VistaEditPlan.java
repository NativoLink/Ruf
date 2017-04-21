package com.darkcode.ruf_012;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.darkcode.ruf_012.Tratamientos.AdapterTratsDePlan;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 18/4/17.
 */

public class VistaEditPlan extends Fragment {

    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    TratamientoService servicio  = restadpter.create(TratamientoService.class);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.trats_list_edit, container, false);
        final ListView lvresult = (ListView)view.findViewById(R.id.lvTratsE);

        int id_plan = Integer.valueOf(this.getArguments().getString("id_plan"));
        int id_paciente = Integer.valueOf(this.getArguments().getString("id_paciente"));
        servicio.getTratsDeUnPlan(id_paciente, id_plan, new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
                AdapterTratsDePlan listAdapter = new AdapterTratsDePlan(getContext(),tratamientos);
                lvresult.setAdapter(listAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        return view;

    }
}
