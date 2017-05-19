package com.darkcode.ruf_012;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.darkcode.ruf_012.Pagos.AdapterConPendientes;
import com.darkcode.ruf_012.Pagos.ConsultaPendiente;
import com.darkcode.ruf_012.Pagos.PagoService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by usuario on 07/05/2017.
 */

public class VistaListConsultasPendientes extends Fragment{
    AdapterConPendientes listAdapter= null;
    public VistaListConsultasPendientes() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.consultas_pendientes, container,false);
        final ListView lvresult = (ListView)view.findViewById(R.id.lvConPendientes);
        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
        PagoService servicio = restadpter.create(PagoService.class);
        //PRUEBA
        servicio.getPagos(2, new Callback<List<ConsultaPendiente>>() {
            @Override
            public void success(List<ConsultaPendiente> pagos, Response response) {
                listAdapter = new AdapterConPendientes(getContext(), pagos);
                lvresult.setAdapter(listAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();



            }
        });

        return view;
    }
}
