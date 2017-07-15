package com.darkcode.ruf_012.Paciente;

import android.os.Bundle;

import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by NativoLink on 21/1/17.
 */
public class VistaRegPaciente extends Fragment {


    EditText et_nombre,et_sexo,et_edad,et_direccion,et_telefono,et_ocupacion,et_direccion_ocu,et_telefono_ocu,et_allegado;

    Spinner spSexo,spEstadoCivil;
    String txt_nombre,txt_edad,txt_direccion,txt_telefono,txt_ocupacion,txt_direc_ocu,txt_tel_ocu,txt_sexo,txt_est_civil;
    private String[] arraySpinner,arraySpinnerE;
    Button btnReg;

    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.reg_paciente, container,false);

            et_nombre        =(EditText) rootView.findViewById(R.id.etNombre);
            et_edad          = (EditText) rootView.findViewById(R.id.etEdad);
            et_direccion     = (EditText) rootView.findViewById(R.id.etDireccion);
            et_telefono      = (EditText) rootView.findViewById(R.id.etTelefono);
            spEstadoCivil    = (Spinner) rootView.findViewById(R.id.spEstadoCivil);
            et_ocupacion     = (EditText) rootView.findViewById(R.id.etOcupacion);
            et_direccion_ocu = (EditText)rootView.findViewById(R.id.etDireccionOcu);
            et_telefono_ocu  = (EditText) rootView.findViewById(R.id.etTelefonoOcu);
            et_nombre.setText(txt_nombre);


            this.arraySpinner = new String[] {
                    "M", "F"
            };
            spSexo = (Spinner)rootView.findViewById(R.id.spSexo);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, arraySpinner);
            spSexo.setAdapter(adapter);
            spSexo.setSelection(1);


            this.arraySpinnerE = new String[] {
                    "Soltero/a","Casado/a","Viudo/a"
            };
            ArrayAdapter<String> adapterEstado = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_spinner_item, arraySpinnerE);
            spEstadoCivil.setAdapter(adapterEstado);
            spEstadoCivil.setSelection(1);

        btnReg = (Button)rootView.findViewById(R.id.btnRegistrar);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_nombre    = et_nombre.getText().toString();
                txt_edad      = et_edad.getText().toString();
                txt_direccion = et_direccion.getText().toString();
                txt_telefono  = et_telefono.getText().toString();
                txt_ocupacion = et_ocupacion.getText().toString();
                txt_direc_ocu = et_direccion_ocu.getText().toString();
                txt_tel_ocu   = et_telefono_ocu.getText().toString();
                txt_sexo      = spSexo.getSelectedItem().toString();
                txt_est_civil = spEstadoCivil.getSelectedItem().toString();

                PacienteService servicio = restadpter.create(PacienteService.class);
                servicio.regPaciente( txt_nombre, txt_direccion,txt_telefono,txt_edad,txt_sexo,txt_est_civil,txt_ocupacion,txt_direc_ocu,txt_tel_ocu, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        Toast.makeText(getContext(), "..." + s + "...", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(),"ERROR :"+error+"...",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });




//        }

        return rootView;

    }





    @Override
    public void onSaveInstanceState(Bundle outState) {//guardar el texto

        super.onSaveInstanceState(outState);
        outState.putString("nombre", txt_nombre);
    }





    public void setRegPaciente(String nombres,
                             String sexo,
                             int edad,
                             String direccion,
                             String telefono,
                             String ocupacion,
                             String DireccionOcu,
                             String TelefonoOcu,
                             String Allegado){


//        this.textoGuardado =nombre;//almacenar en la variable cualquier cambio, del texto

        et_nombre.setText(nombres);
        et_sexo.setText(sexo);
        et_edad.setText(Integer.toString(edad));
        et_direccion.setText(direccion);
        et_telefono.setText(telefono);
        et_ocupacion.setText(ocupacion);
        et_direccion_ocu.setText(DireccionOcu);
        et_telefono_ocu.setText(TelefonoOcu);

    }

}