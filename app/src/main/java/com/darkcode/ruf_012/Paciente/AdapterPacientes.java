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



    public void setId_doctor(String id_doctor) {
        this.id_doctor = id_doctor;
    }
    public AdapterPacientes(Context context, List<Paciente> pacients) {
        super(context, R.layout.list_pacientes, pacients);
        contexto=context;
        pacientes = pacients;

    }


    public void getConsultas() {

        final RestAdapter[] restadpter = {new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build()};
        PacienteService servicio = restadpter[0].create(PacienteService.class);

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

        int idps = pacientes.get(position).getId_paciente();
        if (idps != 0) {
            TextView nombrePaciente = (TextView) customView.findViewById(R.id.tvNombrePaciente);
            final TextView idPaciente = (TextView) customView.findViewById(R.id.tvIdPaciente);
            TextView edadPaciente = (TextView) customView.findViewById(R.id.tvEdadPaciente);
            TextView telPaciente = (TextView) customView.findViewById(R.id.tvTelefonoPaciente);


            Button btnNuevaConsulta = (Button) customView.findViewById(R.id.btnNuevaConsulta);
            Button btnConsultas = (Button) customView.findViewById(R.id.btnConsultas);
            Button btnPlans = (Button) customView.findViewById(R.id.btnPlanes);
            Button btnDiagramas = (Button) customView.findViewById(R.id.btnDiagramas);
            Button btnExamen = (Button) customView.findViewById(R.id.btnExamen);


            btnConsultas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = idPaciente.getText().toString();
                    setParametros(position);
                    vista = new p2ListView();
                    String Titulo_Bar = "Consultas y Pagos";
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
                    String Titulo_Bar = "Diagrama";
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar);
                }
            });

            btnPlans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                id_paciente = idPaciente.getText().toString();
//                vista = new VistaRegDiagrama();
//                cambiarVista(vista);

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
                            aBuilder.setView(vi);
                            aBuilder.setMessage("Click yes to exit!")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            id_paciente = idPaciente.getText().toString();
                                            setParametros(position);
                                            vista = new VistaRegPlanTratamiento();
                                            String Titulo_Bar = "Nuevo Plan" + id_Paciente;
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

                    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
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
                    id_paciente = idPaciente.getText().toString();
                    vista = new VistaGetDiagrama(Integer.valueOf(id_paciente), 5); // ============= 5 = ID Ulti.Consulta
//                setParametros(position);
                    String Titulo_Bar = "Ulti. Odontodiagrama " + id_paciente;
                    ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                    cambiarVista(vista, Titulo_Bar);
                }
            });
            btnExamen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id_paciente = idPaciente.getText().toString();
                    vista = new VistaRegDiagrama();
                    setParametros(position);
                    String Titulo_Bar = "Odontodiagramas";
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
            edadPaciente.setText(edadP);

            telPaciente.setText(pacientes.get(position).getTelefono());
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
