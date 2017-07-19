package com.darkcode.ruf_012;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.Diagrama.VistaGetDiagrama;
import com.darkcode.ruf_012.Diagrama.VistaRegDiagrama;
import com.darkcode.ruf_012.Paciente.Consulta;
import com.darkcode.ruf_012.Paciente.Paciente;
import com.darkcode.ruf_012.Paciente.PacienteService;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsRConsulta;
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
 * Created by usuario on 14/06/2017.
 */

public class VistaHistDiagrama extends Fragment{

    int id_paciente, id_consulta;


    // - - - TRATS REALIZADOS - - -
    ListView lvresult = null;
    boolean state_null = true;
    AdapterTratsRConsulta listAdapter = null;

    Bundle bundle = new Bundle();
    TextView tvCTotalRealizados,tvFecha;
    String nota_msg;


    public VistaHistDiagrama (){
//        this.id_paciente = ((MainActivity) getContext()).getId_pacienteA();

    }
    @SuppressLint("ValidFragment")
    public VistaHistDiagrama (int id_paciente, int id_consulta){
        this.id_paciente = id_paciente;
        this.id_consulta = id_consulta;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.get_consulta, container,false);

        Fragment vista_c = new VistaGetDiagrama(id_paciente,id_consulta);
        FragmentTransaction transaction = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_diagrama, vista_c);
        transaction.addToBackStack(null);
        transaction.commit();
        nota_msg=".......";





        // ================= TRATS REALIZADOS =====================

        lvresult = (ListView) view.findViewById(R.id.lvTrats);
        tvCTotalRealizados = (TextView) view.findViewById(R.id.tvTotalRealizado);


        final RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
        TratamientoService servicio = restadpter.create(TratamientoService.class);



        TextView nombrePaciente = (TextView) view.findViewById(R.id.tvNombrePaciente);
        tvFecha = (TextView) view.findViewById(R.id.tvFecha);
        String nombreP = ((MainActivity) getContext()).getNOMBRES();
        nombrePaciente.setText(nombreP);
        if (listAdapter == null) {
            servicio.getDetalleConsulta(id_paciente, id_consulta, new Callback<List<Tratamiento>>() {
                @Override
                public void success(List<Tratamiento> tratamientos, Response response) {
                    listAdapter = new AdapterTratsRConsulta(getContext(), tratamientos);
                    lvresult.setAdapter(listAdapter);

                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getContext(), "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }





//        lvresult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // When clicked, show a toast with the TextView text
//                Tratamiento trat = (Tratamiento) parent.getItemAtPosition(position);
//                Toast.makeText(getContext(),
//                        "Clicked on Row: " + trat.getNombre(),
//                        Toast.LENGTH_LONG).show();
//            }
//        });


        setRetainInstance(true);


        Button btnNota = (Button) view.findViewById(R.id.btnNota);
        btnNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PacienteService service = restadpter.create(PacienteService.class);
                service.unaConsulta(id_paciente, id_consulta, new Callback<Consulta>() {
                    @Override
                    public void success(Consulta consulta, Response response) {
                        tvFecha.setText(consulta.getFecha());
                        nota_msg=consulta.getDescripcion();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
                createNotaDialogo(nota_msg).show();
            }
        });



        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int total = ((MainActivity) getContext()).getcTratsRTotal();
//                        tvCTotalRealizados.setText(String.valueOf(total));
                        Log.v("ACTUALIZAR", "TOTAL ->" + total);
                        tvCTotalRealizados.setText(String.valueOf(((MainActivity) getContext()).getcTratsRTotal()));
                        if (total > 0) {
                            timer.cancel();
                            timer.purge();
                            return;
                        }

                    }
                });
            }
        }, 0, 1050); // End of your timer code.



        return view;
    }

    public AlertDialog createNotaDialogo(String NOTA) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Agregar Nota")
        .setMessage(NOTA);

        return builder.create();
    }
}
