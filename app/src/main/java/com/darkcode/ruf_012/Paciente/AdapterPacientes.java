package com.darkcode.ruf_012.Paciente;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.Diagrama.AdapterDiagrama;
import com.darkcode.ruf_012.Diagrama.Diagrama;
import com.darkcode.ruf_012.Diagrama.DienteService;
import com.darkcode.ruf_012.Diagrama.VistaGetDiagrama;
import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.Pagos.p2ListView;
import com.darkcode.ruf_012.R;
import com.darkcode.ruf_012.Tratamientos.AdapterPlan;
import com.darkcode.ruf_012.Tratamientos.ListRegPlanTratamientos;
import com.darkcode.ruf_012.Tratamientos.Plan;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;
import com.darkcode.ruf_012.Diagrama.VistaRegDiagrama;
import com.darkcode.ruf_012.VistaListConsultasPendientes;
import com.darkcode.ruf_012.VistaRegConsulta;
import com.darkcode.ruf_012.VistaRegPlanTratNew;
import com.darkcode.ruf_012.VistaRegPlanTratamiento;

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
    String id_paciente,nombreP;
    int ultP,id_Paciente;
    String ultimo_plan = "";
    String id_doctor;
    Fragment vista;

    String Titulo_Bar;


    int idps;

    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();

    public void setId_doctor(String id_doctor) {
        this.id_doctor = id_doctor;
    }
    public AdapterPacientes(Context context, List<Paciente> pacients) {
        super(context, R.layout.list_pacientes, pacients);
        contexto=context;
        pacientes = pacients;

    }


    public void getConsultas() {


        PacienteService servicio = restadpter.create(PacienteService.class);

        servicio.getConsultas(1, new Callback<List<Consulta>>() {
            @Override
            public void success(List<Consulta> consultas, Response response) {

                AdapterConsultas modeAdapter = new AdapterConsultas(getContext(),consultas);
                ListView modeList = new ListView(getContext());
                modeList.setAdapter(modeAdapter);
                AlertDialog.Builder  builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Consultas");
                builder.setView(modeList);
                builder.show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_pacientes, parent, false);

        idps = pacientes.get(position).getId_paciente();
        if (idps != 0) {
            TextView nombrePaciente = (TextView) customView.findViewById(R.id.tvNombrePaciente);
            final TextView idPaciente = (TextView) customView.findViewById(R.id.tvIdPaciente);
            TextView edadPaciente = (TextView) customView.findViewById(R.id.tvEdadPaciente);
            TextView telPaciente = (TextView) customView.findViewById(R.id.tvTelefonoPaciente);


            ImageButton btnNuevaConsulta = (ImageButton) customView.findViewById(R.id.btnNuevaConsulta);
            ImageButton btnConsultas = (ImageButton) customView.findViewById(R.id.btnConsultas);
            ImageButton btnPlans = (ImageButton) customView.findViewById(R.id.btnPlanes);
            ImageButton btnDiagramas = (ImageButton) customView.findViewById(R.id.btnDiagramas);
            ImageButton btnExamen = (ImageButton) customView.findViewById(R.id.btnExamen);


            btnConsultas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = idPaciente.getText().toString();
                    setParametros(position);
                    vista = new p2ListView();
                    Titulo_Bar =  ((MainActivity) getContext()).getV_consultas_pagos();
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar);
                }
            });
            btnNuevaConsulta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = idPaciente.getText().toString();
                    setParametros(position);
                    vista = new VistaRegConsulta();
                    Titulo_Bar =  ((MainActivity) getContext()).getV_reg_consulta();
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar);
                }
            });

            btnPlans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
                    final View vi = View.inflate(contexto, R.layout.plan_list, null);

                    final View view = View.inflate(contexto, R.layout.planes_title, null);

                    aBuilder.setCustomTitle(view);
                    aBuilder.setView(vi);
                    AlertDialog dialog = aBuilder.create();
                    dialog.show();


                    ImageButton btnAddPlan = (ImageButton) view.findViewById(R.id.imgBtnAddPlan);
                    btnAddPlan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "ADD PLAN: ", Toast.LENGTH_LONG).show();
                            AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
                            final View vi = View.inflate(contexto, R.layout.reg_plan, null);
                            aBuilder.setTitle("Nuevo Plan de Tratamiento");
//                            aBuilder.setView(vi);
                            aBuilder.setMessage("Esta seguro que desea agregar un nuevo plan?")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            id_paciente = idPaciente.getText().toString();
                                            setParametros(position);
                                            vista = new VistaRegPlanTratNew();
                                            Titulo_Bar = "Nuevo Plan" + id_Paciente;
                                            ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                                            cambiarVista(vista, Titulo_Bar);
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Toast.makeText(getContext(), "NO: ", Toast.LENGTH_LONG).show();
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog dialog = aBuilder.create();
                            dialog.show();
                        }
                    });

                    setParametros(position);


                    TratamientoService servicio = restadpter.create(TratamientoService.class);

                    servicio.getPlanes(id_Paciente, new Callback<List<Plan>>() {
                        @Override
                        public void success(List<Plan> planes, Response response) {
                            ListView lvresult = (ListView) vi.findViewById(R.id.lvPlanes);
                            AdapterPlan listAdapter = new AdapterPlan(getContext(), planes);
                            lvresult.setAdapter(listAdapter);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.v("ERROR", "=>" + error.getMessage());
                        }
                    });
                }
            });
            btnDiagramas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ((MainActivity) getContext()).setId_pacienteA(pacientes.get(position).getId_paciente());
                    ((MainActivity) getContext()).setNOMBRES(pacientes.get(position).getNombre());
                    // - - - - - -  UPDATE VIEW  - - - - -
                    AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
                    final View vi = View.inflate(contexto, R.layout.plan_list, null);

//                    final View view = View.inflate(contexto, R.layout.planes_title, null);

//                    aBuilder.setCustomTitle(view);
                    String nombre_paciente =  ((MainActivity) getContext()).getNOMBRES();
                    aBuilder.setTitle("Historial de Diagramas - "+nombre_paciente+" (ID - "+ ((MainActivity) getContext()).getId_pacienteA()+")");
                    aBuilder.setView(vi);
                    AlertDialog dialog = aBuilder.create();
                    dialog.show();

                    setParametros(position);

                    DienteService servicio = restadpter.create(DienteService.class);

                    servicio.listDiagramaFecha(id_Paciente, new Callback<List<Diagrama>>() {
                        @Override
                        public void success(List<Diagrama> diagrams, Response response) {
                            ListView lvresult = (ListView) vi.findViewById(R.id.lvPlanes);
                            AdapterDiagrama listAdapter = new AdapterDiagrama(getContext(), diagrams);
                            lvresult.setAdapter(listAdapter);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.v("ERROR", "=>" + error.getMessage());
                        }
                    });


                }
            });
            btnExamen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = idPaciente.getText().toString();
                    vista = new VistaRegDiagrama();
                    setParametros(position);
                    Titulo_Bar =  ((MainActivity) getContext()).getV_examen_clinico();
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar);
                }
            });


            int id = pacientes.get(position).getId_paciente();
            String idP = Integer.toString(id);
            idPaciente.setText(idP);

            nombrePaciente.setText(pacientes.get(position).getNombre());

            int edad = pacientes.get(position).getEdad();
            String edadP = Integer.toString(edad);
            edadPaciente.setText("Edad : "+edadP);
             telPaciente.setText("Tel..: "+pacientes.get(position).getTelefono());
        }else{ customView.setVisibility(View.INVISIBLE);}
        return customView;

    }

    private void setParametros(int pos) {
        ultimo_plan = Integer.toString(pacientes.get(pos).getUltimo_plan());
        id_Paciente = pacientes.get(pos).getId_paciente();
        ultP = pacientes.get(pos).getUltimo_plan();
        nombreP =  pacientes.get(pos).getNombre();
    }



    public void cambiarVista(Fragment vistaObj, String vActual){
            bundle.putString("id_doctor", id_doctor);
            bundle.putString("id_paciente", id_paciente);
            bundle.putString("nombre_paciente", nombreP);
            bundle.putString("ultimo_plan", ultimo_plan);
            vistaObj.setArguments(bundle);
            ((MainActivity)getContext()).setId_pacienteA(id_Paciente);
            ((MainActivity)getContext()).setTotalRegConsulta(0);
            ((MainActivity)getContext()).setUltimo_plan(ultP);

            FragmentTransaction transaction = ((FragmentActivity)contexto).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.f_main, vistaObj);
            transaction.addToBackStack(null);
            transaction.commit();
            ((MainActivity)getContext()).hideBtnUnivesal(vActual);
            ((MainActivity)getContext()).getSupportActionBar().setTitle(vActual);
    }

}
