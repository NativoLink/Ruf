package com.darkcode.ruf_012;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.darkcode.ruf_012.Diagrama.VistaRegDiagrama;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 12/3/17.
 */

public class VistaRegConsulta  extends Fragment {
    Bundle bundle = new Bundle();
    TextView tvCTotalRealizados;

    @Override
    public void onStart() {
        super.onStart();
    }

    ListView lvresult = null;
    boolean state_null = true;
    AdapterTratsConsulta listAdapter = null;

//
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.reg_consulta, container, false);

        Fragment vista_c = new VistaRegDiagrama();
        FragmentTransaction transaction = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_diagrama, vista_c);
        transaction.addToBackStack(null);
        transaction.commit();

        lvresult = (ListView) view.findViewById(R.id.lvTrats);
//        lvresult.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        tvCTotalRealizados = (TextView) view.findViewById(R.id.tvTotalRealizado);


        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
        TratamientoService servicio = restadpter.create(TratamientoService.class);


        //==== PARAMETROS DE CADA PACIENTE  =====
        String nombreP = ((MainActivity) getContext()).getNOMBRES();
        String idPaciente = String.valueOf( ((MainActivity) getContext()).getId_pacienteA() );
        String idUltimoPlan = this.getArguments().getString("ultimo_plan");

        final int id_paciente = Integer.valueOf(idPaciente);
        final int ultimo_plan = Integer.valueOf(idUltimoPlan);


//        Toast.makeText(getContext(), "ID_P => " + id_paciente + " ulP=>" + ultimo_plan, Toast.LENGTH_LONG).show();

        TextView nombrePaciente = (TextView) view.findViewById(R.id.tvNombrePaciente);
        nombrePaciente.setText(nombreP);
        if (listAdapter == null) {
            servicio.getTratsDeUnPlan(id_paciente, ultimo_plan, new Callback<List<Tratamiento>>() {
                @Override
                public void success(List<Tratamiento> tratamientos, Response response) {
                    listAdapter = new AdapterTratsConsulta(getContext(), tratamientos);
                    lvresult.setAdapter(listAdapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getContext(), "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }


        lvresult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Tratamiento trat = (Tratamiento) parent.getItemAtPosition(position);
                Toast.makeText(getContext(),
                        "Clicked on Row: " + trat.getNombre(),
                        Toast.LENGTH_LONG).show();
            }
        });


        setRetainInstance(true);


        ImageButton btnNota = (ImageButton) view.findViewById(R.id.btnNota);
        btnNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotaDialogo().show();
            }
        });



        Timer timer = new Timer();
        try {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int total = ((MainActivity) getContext()).getTotalRegConsulta();
                            tvCTotalRealizados.setText(String.valueOf(total));
                            Log.v("ACTUALIZAR", "TOTAL ->" + total);
                        }
                    });
                }
            }, 0, 1000); // End of your timer code.
        }catch(Exception ex){
            Log.v("EXCEPTION", "TOTAL ->" + ex);
        }


        return view;





    }

    private void runOnUiThread() {

        int total = ((MainActivity) getContext()).getTotalRegConsulta();
        tvCTotalRealizados.setText(String.valueOf(total));
        Log.v("ACTUALIZAR", "TOTAL ->" + total);

    }


    public AlertDialog createNotaDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Agregar Nota")
        .setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acciones
                    }
                })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Acciones
                            }
                        });

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.reg_nota, null);

        builder.setView(v);


        return builder.create();
    }


}

