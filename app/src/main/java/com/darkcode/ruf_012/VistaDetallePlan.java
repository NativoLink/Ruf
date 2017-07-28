package com.darkcode.ruf_012;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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

    TextView tvCantTratas,tvCostoT;

    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    TratamientoService servicio  = restadpter.create(TratamientoService.class);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.trats_list_detalle, container, false);
        final ListView lvresult = (ListView)view.findViewById(R.id.lvTratsE);

//        int id_plan =  ((MainActivity) getContext()).getId_plan_select();
//        int id_paciente = ((MainActivity) getContext()).getId_pacienteA();

        int id_plan = Integer.valueOf(this.getArguments().getString("id_plan"));
        int id_paciente = Integer.valueOf(this.getArguments().getString("id_paciente"));

//        Toast.makeText(getContext(),"id_plan:"+id_plan+"  id_paciente:"+id_paciente, Toast.LENGTH_LONG).show();

        TextView tvNombreP = (TextView)view.findViewById(R.id.tvNombreP);
        tvNombreP.setText( ((MainActivity) getContext()).getNOMBRES());

        TextView tvIdPlan = (TextView)view.findViewById(R.id.tvIdPlan);
        tvIdPlan.setText("PLAN.no:"+id_plan);

        tvCantTratas = (TextView)view.findViewById(R.id.tvCantTratas);
        tvCostoT = (TextView)view.findViewById(R.id.tvCostoT);


        ImageButton btnNota = (ImageButton)view.findViewById(R.id.btnNota);


        servicio.getTratsDeUnPlanC(id_paciente, id_plan, new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
                AdapterTratsDePlanDetalle listAdapter = new AdapterTratsDePlanDetalle(getContext(),tratamientos);
                lvresult.setAdapter(listAdapter);
                tvCantTratas.setText(String.valueOf( ((MainActivity) getContext()).getCantTras()));
                tvCostoT.setText(String.valueOf( ((MainActivity) getContext()).getCostoTotalDetalleP()));
                ((MainActivity) getContext()).cantTras = 0;
                ((MainActivity) getContext()).costoTotalDetalleP = 0;
            }

            @Override
            public void failure(RetrofitError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

//        tvCantTratas.setText(String.valueOf( ((MainActivity) getContext()).getCantTras()));
//        tvCostoT.setText(String.valueOf( ((MainActivity) getContext()).getCostoTotalDetalleP()));





        return view;

    }
}