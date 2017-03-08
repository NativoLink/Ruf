package com.darkcode.ruf_012.Paciente;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.R;
import com.darkcode.ruf_012.Tratamientos.AdapterPlan;
import com.darkcode.ruf_012.Tratamientos.ListRegPlanTratamientos;
import com.darkcode.ruf_012.Tratamientos.Plan;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;
import com.darkcode.ruf_012.VistaRegDiagrama;
import com.darkcode.ruf_012.VistaRegPaciente;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 18/2/17.
 */

public class AdapterPacientes extends ArrayAdapter<Paciente> {

    private Context contexto;
    private List<Paciente> pacientes;
    Bundle bundle = new Bundle();
    String id_paciente;
    Fragment vista;

    public String getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(String id_doctor) {
        this.id_doctor = id_doctor;
    }

    String id_doctor;
    public AdapterPacientes(Context context, List<Paciente> pacients) {
        super(context, R.layout.list_pacientes, pacients);
        contexto=context;
        pacientes = pacients;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_pacientes, parent, false);

        TextView nombrePaciente = (TextView) customView.findViewById(R.id.tvNombrePaciente);
        final TextView idPaciente = (TextView) customView.findViewById(R.id.tvIdPaciente);
        TextView edadPaciente = (TextView) customView.findViewById(R.id.tvEdadPaciente);
        TextView telPaciente = (TextView) customView.findViewById(R.id.tvTelefonoPaciente);


        Button btnNuevaConsulta = (Button) customView.findViewById(R.id.btnNuevaConsulta);
        Button btnPlans = (Button) customView.findViewById(R.id.btnPlanes);
        Button btnDiagramas = (Button) customView.findViewById(R.id.btnDiagramas);
        Button btnExamen = (Button) customView.findViewById(R.id.btnExamen);



        btnNuevaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_paciente = idPaciente.getText().toString();
                vista = new VistaRegDiagrama();
                cambiarVista(vista);
            }
        });
        btnPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                id_paciente = idPaciente.getText().toString();
//                vista = new VistaRegDiagrama();
//                cambiarVista(vista);

                AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
                final View vi = View.inflate(contexto,R.layout.plan_list,null);
//                aBuilder.setTitle("Planes");

                final View view = View.inflate(contexto, R.layout.planes_title, null);

                aBuilder.setCustomTitle(view);
                aBuilder.setView(vi);
                AlertDialog dialog = aBuilder.create();
                dialog.show();


                ImageButton  btnAddPlan = (ImageButton)view.findViewById(R.id.imgBtnAddPlan);
                btnAddPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"ADD PLAN: ", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
                        final View vi = View.inflate(contexto,R.layout.reg_plan,null);
                        aBuilder.setTitle("Nuevo Plan de Tratamiento");
                        aBuilder.setView(vi);
                        aBuilder.setMessage("Click yes to exit!")
                                .setCancelable(false)
                                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        Intent intent = new Intent(contexto, ListRegPlanTratamientos.class);
//                                      String idDoctor = String.valueOf(doctor.getId_doctor());
//                                      intent.putExtra("id_doctor", idDoctor);
                                        contexto.startActivity(intent);
                                        Toast.makeText(getContext(),"YES: ", Toast.LENGTH_LONG).show();
                                    }
                                })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        Toast.makeText(getContext(),"NO: ", Toast.LENGTH_LONG).show();
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog dialog = aBuilder.create();
                        dialog.show();
                    }
                });

                RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
                TratamientoService servicio = restadpter.create(TratamientoService.class);

                servicio.getPlanes(1, new Callback<List<Plan>>() {
                    @Override
                    public void success(List<Plan> planes, Response response) {
                        ListView lvresult = (ListView)vi.findViewById(R.id.lvPlanes);
                        AdapterPlan listAdapter = new AdapterPlan(getContext(), planes);
                        lvresult.setAdapter(listAdapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });
        btnDiagramas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_paciente = idPaciente.getText().toString();
                vista = new VistaRegDiagrama();
                cambiarVista(vista);
            }
        });
        btnExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_paciente = idPaciente.getText().toString();
                vista = new VistaRegDiagrama();
                cambiarVista(vista);
            }
        });


        int id = pacientes.get(position).getId_paciente();
        String idP = Integer.toString(id);
        idPaciente.setText(idP);

        nombrePaciente.setText(pacientes.get(position).getNombre());

        int edad = pacientes.get(position).getEdad();
        String edadP = Integer.toString(edad);
        edadPaciente.setText(edadP);

        telPaciente.setText(pacientes.get(position).getTelefono());

        return customView;

    }


    public void cambiarVista(Fragment vistaObj){
            bundle.putString("id_doctor", id_doctor);
            bundle.putString("id_paciente", id_paciente);
            FragmentTransaction transaction = ((FragmentActivity)contexto).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.f_main, vistaObj);
            transaction.addToBackStack(null);
            transaction.commit();
    }

}