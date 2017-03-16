package com.darkcode.ruf_012;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import com.darkcode.ruf_012.Diagrama.VistaRegDiagrama;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 12/3/17.
 */

public class VistaRegConsulta  extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.reg_consulta, container, false);

        Fragment vista_c = new VistaRegDiagrama();
        FragmentTransaction transaction= ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_diagrama, vista_c);
        transaction.addToBackStack(null);
        transaction.commit();


        final ListView lvresult;
        lvresult = (ListView)view.findViewById(R.id.lvTrats);


        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
        TratamientoService servicio = restadpter.create(TratamientoService.class);

        servicio.getTratsDeUnPlan(1, 1, new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
                AdapterTratsConsulta listAdapter = new AdapterTratsConsulta(getContext(), tratamientos);
                lvresult.setAdapter(listAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(),"ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return  view;

    }
}
