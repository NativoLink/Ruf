package com.darkcode.ruf_012;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsDePlan;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 28/6/17.
 */

public class VistaRegPlan  extends Fragment {

    private Button btnReg;
    ArrayList<AdapterTratsConsulta.checkItem> callback_trats;

    int id_plan;
    int id_paciente;
    TratamientoService servicio;



    public VistaRegPlan() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        RestAdapter restadpter =   ((MainActivity) getContext()).getRestadpter();
        servicio  = restadpter.create(TratamientoService.class);

        View view = inflater.inflate(R.layout.trats_list_edit, container, false);
        final ListView lvresult = (ListView)view.findViewById(R.id.lvTratsE);

        id_plan = Integer.valueOf(this.getArguments().getString("id_plan"));
        if(id_plan == 0){id_plan++;}
//        id_paciente = Integer.valueOf(this.getArguments().getString("id_paciente"));
        id_paciente =((MainActivity) getContext()).getId_pacienteA();

        TextView tvNombreP = (TextView)view.findViewById(R.id.tvNombreP);
        tvNombreP.setText( ((MainActivity) getContext()).getNOMBRES());

        btnReg = (Button) view.findViewById(R.id.btnRegPlan)  ;
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback_trats = ((MainActivity) getContext()).getItemRegPlan();

//                int id_paciente = 1; // ESTA VARIABLE NO LA TENEMOS RECIVIDA AUN
                servicio.regPlan(id_paciente,  ((MainActivity) getContext()).getNota_plan(), new Callback<Integer>() {
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
                            servicio.regTratsDeUnPlan(plan, id_trat, cant, costo, new Callback<String>() {
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

        servicio.getTratamientos(new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
                AdapterTratsDePlan listAdapter = new AdapterTratsDePlan(getContext(),tratamientos);
                lvresult.setAdapter(listAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        ImageButton btnNota = (ImageButton) view.findViewById(R.id.btnNota);
        btnNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotaDialogo().show();
            }
        });

        return view;

    }

    public AlertDialog createNotaDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.reg_nota, null);
        builder.setTitle("Agregar Nota")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText note =  (EditText)v.findViewById(R.id.etNota);
                                ((MainActivity) getContext()).setNota_plan(note.getText().toString());
//                        Toast.makeText(getContext(), "NOTA : " + note.getText().toString(), Toast.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Acciones
                            }
                        });



        builder.setView(v);


        return builder.create();
    }
}
