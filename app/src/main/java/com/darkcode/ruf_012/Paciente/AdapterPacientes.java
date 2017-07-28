package com.darkcode.ruf_012.Paciente;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.Diagrama.AdapterDiagrama;
import com.darkcode.ruf_012.Diagrama.Diagrama;
import com.darkcode.ruf_012.Diagrama.DienteService;
import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.Pagos.VistaPagosR;
import com.darkcode.ruf_012.Pagos.p2ListView;
import com.darkcode.ruf_012.R;
import com.darkcode.ruf_012.Tratamientos.AdapterPlan;
import com.darkcode.ruf_012.Tratamientos.Plan;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;
import com.darkcode.ruf_012.VistaRegConsulta;
import com.darkcode.ruf_012.VistaRegPlan;


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

    private void setChangeUser(int num_id,String nombre){
        ((MainActivity) getContext()).setNOMBRES(nombre);
        ((MainActivity) getContext()).setId_pacienteA(num_id);

    }
    private void UserEfectt(int position){
        idps = pacientes.get(position).getId_paciente();
        ((MainActivity) getContext()).setId_pacienteA(idps);
        nombreP = pacientes.get(position).getNombre();
        int id_u_plan = pacientes.get(position).getUltimo_plan();
        int id_u_consulta = pacientes.get(position).getUltima_consulta();
        setChangeUser(idps,nombreP);
//        Toast.makeText(getContext(), "ID_P =>"+idps+" ID_U_PL =>"+id_u_plan+" ID_U_CON =>"+id_u_consulta, Toast.LENGTH_LONG).show();

    }


    public void getConsultas() {


        PacienteService servicio = restadpter.create(PacienteService.class);

        servicio.getConsultas( ((MainActivity) getContext()).getId_pacienteA(), new Callback<List<Consulta>>() {
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
    public View getView(final int position, View customView, ViewGroup parent) {
        contexto = getContext();

        final ViewHolderPaciente holder;

        idps = pacientes.get(position).getId_paciente();
        nombreP = pacientes.get(position).getNombre();

        if (idps != 0) {
            if (customView == null) {
                holder = new ViewHolderPaciente();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                customView = inflater.inflate(R.layout.list_pacientes, parent, false);
                holder.nombrePaciente = (TextView) customView.findViewById(R.id.tvNombrePaciente);
                holder.idPaciente = (TextView) customView.findViewById(R.id.tvIdPaciente);
                holder.edadPaciente = (TextView) customView.findViewById(R.id.tvEdadPaciente);
                holder.telPaciente = (TextView) customView.findViewById(R.id.tvTelefonoPaciente);

                holder.btnNuevaConsulta = (ImageButton) customView.findViewById(R.id.btnNuevaConsulta);
                holder.btnConsultas = (ImageButton) customView.findViewById(R.id.btnConsultas);
                holder.btnPlans = (ImageButton) customView.findViewById(R.id.btnPlanes);
                holder.btnDiagramas = (ImageButton) customView.findViewById(R.id.btnDiagramas);
                holder.btnExamen = (ImageButton) customView.findViewById(R.id.btnExamen);
                holder. btnPagos = (ImageButton) customView.findViewById(R.id.btnPagos);
                holder. btnHistMedHabis = (ImageButton) customView.findViewById(R.id.btnHabis);

                if(pacientes.get(position).getEstado().equals("activo")){
                    holder.estado = 1;
                }else{
                    holder.estado = 0;
                }

                holder.ultimo_plan = pacientes.get(position).getUltimo_plan();
                holder.ultima_consulta = pacientes.get(position).getUltima_consulta();
                holder.existe_deuda = pacientes.get(position).getExiste_deuda();
                holder.plan_incompleto = pacientes.get(position).getPlan_incompleto();
                holder.existe_pagos = pacientes.get(position).getExisten_pagos();
                customView.setTag(holder);

                colorEstado(holder);
            }else{
                holder = (ViewHolderPaciente) customView.getTag();
            }


            if(holder.ultimo_plan==0){
                holder.btnNuevaConsulta.setVisibility(View.INVISIBLE);
            }

            if(holder.ultima_consulta==0){
                holder.btnDiagramas.setVisibility(View.INVISIBLE);
                holder.btnConsultas.setVisibility(View.INVISIBLE);
            }
            if(holder.existe_pagos.equals("false")){
                holder.btnPagos.setVisibility(View.INVISIBLE);
            }
            if( holder.existe_deuda.equals("false")){
                holder.btnConsultas.setVisibility(View.INVISIBLE);
            }

            if( holder.plan_incompleto.equals("false")){
                holder.btnNuevaConsulta.setVisibility(View.INVISIBLE);
            }


            holder.btnConsultas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = holder.idPaciente.getText().toString();
                    UserEfectt(position);

                    setParametros(position,holder);
                    vista = new p2ListView();
                    Titulo_Bar =  ((MainActivity) getContext()).getV_consultas_pagos();
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar,holder);
                }
            });
            holder.btnNuevaConsulta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = holder.idPaciente.getText().toString();
                    UserEfectt(position);
                    setParametros(position,holder);
                    vista = new VistaRegConsulta();
                    Titulo_Bar =  ((MainActivity) getContext()).getV_reg_consulta();
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar,holder);
                }
            });

            holder.btnPlans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UserEfectt(position);
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
                                            id_paciente = holder.idPaciente.getText().toString();

                                            setParametros(position,holder);
//                                            vista = new VistaRegPlanTratNew();
//                                            vista = new VistaRegPlanTratamiento();
                                            vista = new VistaRegPlan();
                                            Titulo_Bar = ((MainActivity) getContext()).getV_nuevo_plan();
                                            ((MainActivity) getContext()).setNOMBRES(pacientes.get(position).getNombre());
                                            ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                                            cambiarVista(vista, Titulo_Bar,holder);
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

                    UserEfectt(position);
                    setParametros(position,holder);



                    TratamientoService servicio = restadpter.create(TratamientoService.class);

                    servicio.getPlanes(holder.id_Paciente, new Callback<List<Plan>>() {
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
            holder.btnDiagramas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UserEfectt(position);
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


                    setParametros(position,holder);

                    DienteService servicio = restadpter.create(DienteService.class);

                    servicio.listDiagramaFecha(holder.id_Paciente, new Callback<List<Diagrama>>() {
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
            holder.btnExamen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = holder.idPaciente.getText().toString();
                    vista = new VistaRegExamen();

                    UserEfectt(position);
                    setParametros(position,holder);

                    Titulo_Bar =  ((MainActivity) getContext()).getV_examen_clinico();
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar,holder);
                }
            });

            holder.btnPagos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = holder.idPaciente.getText().toString();
                    vista = new VistaPagosR();

                    UserEfectt(position);
                    setParametros(position,holder);

                    Titulo_Bar =  ((MainActivity) getContext()).getV_list_pagos();
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar,holder);
                }
            });

            holder.btnHistMedHabis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = holder.idPaciente.getText().toString();
                    vista = new VistaHistMed();

                    UserEfectt(position);
                    setParametros(position,holder);

                    Titulo_Bar =  ((MainActivity) getContext()).getV_hist_med();
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar,holder);
                }
            });




            int id = pacientes.get(position).getId_paciente();
            String idP = Integer.toString(id);
            holder.idPaciente.setText(idP);

            holder.idPaciente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"Historial Medico", Toast.LENGTH_LONG).show();
                }
            });
            holder.idPaciente.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Toast.makeText(getContext(),"USUARIO DESACTIVADO", Toast.LENGTH_LONG).show();
                    if(pacientes.get(position).getEstado().equals("activo")){
                         holder.estado = 1;
                    }else{
                        holder.estado = 0;
                    }
                    desactivarUser(pacientes.get(position).getId_paciente(),holder.estado,holder);
                    return false;
                }
            });
            holder.nombrePaciente.setText(pacientes.get(position).getNombre());

            int edad = pacientes.get(position).getEdad();
            String edadP = Integer.toString(edad);
            holder.edadPaciente.setText("Edad : "+edadP);
            holder.telPaciente.setText("Tel..: "+pacientes.get(position).getTelefono());
        }else{ customView.setVisibility(View.INVISIBLE);}
        return customView;

    }

    private void setParametros(int pos,ViewHolderPaciente holder) {
        ultimo_plan = Integer.toString(pacientes.get(pos).getUltimo_plan());
        holder.id_Paciente = pacientes.get(pos).getId_paciente();
        holder.ultP = pacientes.get(pos).getUltimo_plan();
        nombreP =  pacientes.get(pos).getNombre();
    }



    public void cambiarVista(Fragment vistaObj, String vActual,ViewHolderPaciente holder){
            bundle.putString("id_doctor", id_doctor);
            bundle.putString("id_paciente", id_paciente);
            bundle.putString("nombre_paciente", nombreP);
            bundle.putString("ultimo_plan", ultimo_plan);
            bundle.putString("id_plan", ultimo_plan);
            vistaObj.setArguments(bundle);
            ((MainActivity)getContext()).setId_pacienteA(holder.id_Paciente);
            ((MainActivity)getContext()).setTotalRegConsulta(0);
            ((MainActivity)getContext()).setUltimo_plan(holder.ultP);

            FragmentTransaction transaction = ((FragmentActivity)contexto).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.f_main, vistaObj);
            transaction.addToBackStack(null);
            transaction.commit();
            ((MainActivity)getContext()).hideBtnUnivesal(vActual);
            ((MainActivity)getContext()).getSupportActionBar().setTitle(vActual);
    }

    public  void desactivarUser(final int id_paciente, int estado_actual, final ViewHolderPaciente holder){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
        String nuevo_estado;
        String msg_dialog;
        if(holder.estado==1){
            holder.estado = 0;
            nuevo_estado = "Inactivo";
            msg_dialog = "Deshabilitar";

        }else{
            holder.estado = 1;
            nuevo_estado = "Activo";
            msg_dialog = "Habilitar";

        }
        aBuilder.setTitle("ESTADO DEL PACIENTE");
//                            aBuilder.setView(vi);
        final String finalNuevo_estado = nuevo_estado;
        aBuilder.setMessage("Desea "+msg_dialog+" este paciente?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        PacienteService servicio = restadpter.create(PacienteService.class);
                        servicio.setEstadoPaciente(id_paciente, holder.estado, new Callback<String>() {
                            @Override
                            public void success(String s, Response response) {
                                Toast.makeText(getContext(), "Paciente:"+ finalNuevo_estado, Toast.LENGTH_LONG).show();
                                colorEstado(holder);
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "NO ", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = aBuilder.create();
        dialog.show();
    }
    public void colorEstado(ViewHolderPaciente holder){
        if(holder.estado ==0){
            String rojo = "#ffd7d7"; // ROJO
            holder.idPaciente.setBackgroundColor( Color.parseColor(rojo));
        }else{
            String verde = "#fdf0f8f3"; // VERDE
            holder.idPaciente.setBackgroundColor( Color.parseColor(verde));
        }
    }

    private class ViewHolderPaciente {
        int ultP,id_Paciente;
        int estado;

        TextView nombrePaciente;
        TextView idPaciente;
        TextView edadPaciente;
        TextView telPaciente;
        ImageButton btnNuevaConsulta ;
        ImageButton btnConsultas;
        ImageButton btnPlans;
        ImageButton btnDiagramas;
        ImageButton btnExamen;

        ImageButton btnPagos;
        ImageButton btnHistMedHabis;


        int ultimo_plan;
        int ultima_consulta;
        String existe_deuda;
        String plan_incompleto,existe_pagos;
    }

}
