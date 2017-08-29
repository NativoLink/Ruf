package com.darkcode.ruf_012.Doctor;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.Paciente.Especialidad;
import com.darkcode.ruf_012.R;


import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.darkcode.ruf_012.R.id.spEspecialidad;

/**
 * Created by NativoLink on 6/7/17.
 */

public class VistaRegDoctor extends Fragment {

    EditText etNombre,etUsuario,etClave,etClaveC,etDireccion_d,etTelefono,etCedula;
    Spinner spEspecialidad,spSexo;
    String stNombre,stUsuario,stClave,stDireccion_d,stTelefono,stCedula,stEspecialidad, stSexo;
    Button btnRegistrar;

    private String[] arraySpinner;
    int posi = 0;
    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    DoctorService servicio  = restadpter.create(DoctorService.class);

    int id_doctor;
    String tipo;
    int especialidad_edit;


    public VistaRegDoctor() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        try{
            tipo = this.getArguments().getString("tipo");
        }catch(Exception ex){

        }

        View view = inflater.inflate(R.layout.reg_doctor, container, false);



        etNombre       = (EditText)view.findViewById(R.id.etNombre);
        etUsuario      = (EditText)view.findViewById(R.id.etUsuario);
        etClave        = (EditText)view.findViewById(R.id.etClave);
        etClaveC       = (EditText)view.findViewById(R.id.etClaveC);
        etDireccion_d  = (EditText)view.findViewById(R.id.etDireccion_d);
        etTelefono     = (EditText)view.findViewById(R.id.etTelefono);
        etCedula       = (EditText)view.findViewById(R.id.etCedula);
        spEspecialidad = (Spinner)view.findViewById(R.id.spEspecialidad);

        this.arraySpinner = new String[] {
                "M", "F"
        };
        spSexo = (Spinner)view.findViewById(R.id.spSexo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        spSexo.setAdapter(adapter);
        spSexo.setSelection(0);

        servicio.getEspecialidaddes(new Callback<List<Especialidad>>() {
            @Override
            public void success(List<Especialidad> especialidads, Response response) {
                arraySpinner = new  String[especialidads.size()];
                for(Especialidad str : especialidads)
                {
                    arraySpinner[posi] = str.getNombre();
//                    Toast.makeText(getContext()," ESPEC: "+str.getNombre(), Toast.LENGTH_LONG).show();
                    posi++;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        R.layout.spinner_item, arraySpinner);
                spEspecialidad.setAdapter(adapter);
                spEspecialidad.setSelection(0);

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext()," ERROR ESPEC: "+error, Toast.LENGTH_LONG).show();
            }
        });

        spEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                int msupplier =spEspecialidad.getSelectedItemPosition();
                Log.v("Selected item : ","ITEM ==>"+msupplier);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        btnRegistrar = (Button) view.findViewById(R.id.btnRegistrar);
        if(tipo=="editar") {

            id_doctor = ((MainActivity) getContext()).getId_doctor_edit();
            btnRegistrar.setText("Actaulizar");

//            if (etClave.getText().equals(etClaveC.getText())){

                servicio.getDoctor(id_doctor, new Callback<Doctor>() {
                    @Override
                    public void success(Doctor doctor, Response response) {
                        try {
                            etNombre.setText(doctor.getNombre().toString());
                            etUsuario.setText(doctor.getUsuario().toString());
                            etClave.setText(doctor.getClave());
                            etClaveC.setText(doctor.getClave());
                            etDireccion_d.setText(doctor.getDireccion().toString());
                            etTelefono.setText(String.valueOf(doctor.getTelefono()));
                            etCedula.setText(String.valueOf(doctor.getCedula()));
                            stEspecialidad = spEspecialidad.getSelectedItem().toString();
                            int position = 0;
                            while (!stEspecialidad.equals(doctor.getEspecialidad())) {
                                stEspecialidad = spEspecialidad.getSelectedItem().toString();
                                spEspecialidad.setSelection(position);
                                position++;
                            }
                            Toast.makeText(getContext(), "Spinner->" + stEspecialidad + " getDoctor->" + doctor.getEspecialidad(), Toast.LENGTH_LONG).show();
                            stSexo = spSexo.getSelectedItem().toString();
                            if (doctor.getTelefono().equals("m") || doctor.getTelefono().equals("M")) {
                                spSexo.setSelection(0);
                            } else {
                                spSexo.setSelection(1);
                            }
                        } catch (NullPointerException ex) {
                            Log.v("SUCCESS DATA.NULL ", "ERROR NULL =>VistaRegDoctor" + ex.getMessage());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v("NO REG", "ERROR SQL->" + error.getMessage());
                    }
                });
//            }else{
//                Toast.makeText(getContext(), "Las claves no son iguales", Toast.LENGTH_LONG).show();
//            }

            btnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getForm();
                    servicio.editDoctor(id_doctor, stNombre, stUsuario, stClave, stDireccion_d, stTelefono, stCedula, especialidad_edit,stSexo, new Callback<String>() {
                        @Override
                        public void success(String s, Response response) {
                            Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.v("NO REG", "ERROR SQL->" + error.getMessage());
                        }
                    });
                }
            });
        }else {


                btnRegistrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        getForm();
                        int especialidad = spEspecialidad.getSelectedItemPosition() + 1;
                        stSexo = spSexo.getSelectedItem().toString();

                        if (
                                (!stNombre.equals("") && !stUsuario.equals("")) && (!stNombre.equals(" ") && !stUsuario.equals(" ")) &&
                                        (!stClave.equals("") && !stDireccion_d.equals("")) && (!stClave.equals(" ") && !stDireccion_d.equals(" ")) &&
                                        (!stTelefono.equals("") && !stCedula.equals("")) && (!stTelefono.equals(" ") && !stCedula.equals(" ")) &&
                                        (!stEspecialidad.equals("") && !stEspecialidad.equals(" "))
                                ) {
                            if (etClave.getText().equals(etClaveC.getText())){
                            servicio.regDoctor(stNombre, stUsuario, stClave, stDireccion_d, stTelefono, stCedula, especialidad, stSexo, new Callback<String>() {
                                @Override
                                public void success(String s, Response response) {
                                    Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();

                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.v("NO REG", "ERROR SQL->" + error.getMessage());
                                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                            }else{
                                Toast.makeText(getContext(), "Las claves no son iguales", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Tiene campos vacios", Toast.LENGTH_LONG).show();
                        }


                    }
                });

        }







        return view;

    }

    public void getForm(){
        stNombre = etNombre.getText().toString();
        stUsuario = etUsuario.getText().toString();
        stClave = etClave.getText().toString();
        stDireccion_d = etDireccion_d.getText().toString();
        stTelefono = etTelefono.getText().toString();
        stCedula = etCedula.getText().toString();
        stEspecialidad = spEspecialidad.getSelectedItem().toString();
    }


}