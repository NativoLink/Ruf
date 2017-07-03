package com.darkcode.ruf_012.Paciente;

import android.os.Bundle;

import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.darkcode.ruf_012.R;


/**
 * Created by NativoLink on 21/1/17.
 */
public class VistaRegPaciente extends Fragment {


    EditText et_nombre,et_sexo,et_edad,et_direccion,et_telefono,et_estado_civil,et_ocupacion,et_direccion_ocu,et_telefono_ocu,et_allegado;

    Spinner sp_sexo;
    String txt_nombre;
    private String[] arraySpinner;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.reg_paciente, container,false);
//        if(savedInstanceState==null){

//        }else {//restaurar el texto

//            txt_nombre = savedInstanceState.getString("nombre");
            et_nombre=(EditText) rootView.findViewById(R.id.etNombre);
            et_edad= (EditText) rootView.findViewById(R.id.etEdad);
            et_direccion= (EditText) rootView.findViewById(R.id.etDireccion);
            et_telefono= (EditText) rootView.findViewById(R.id.etTelefono);
            et_estado_civil= (EditText) rootView.findViewById(R.id.etEstadoCivil);
            et_ocupacion= (EditText) rootView.findViewById(R.id.etOcupacion);
            et_direccion_ocu= (EditText)rootView.findViewById(R.id.etDireccionOcu);
            et_telefono_ocu= (EditText) rootView.findViewById(R.id.etTelefonoOcu);
            et_nombre.setText(txt_nombre);


            this.arraySpinner = new String[] {
                    "M", "F"
            };
            Spinner sexo = (Spinner)rootView.findViewById(R.id.spSexo);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_spinner_item, arraySpinner);
            sexo.setAdapter(adapter);
            sexo.setSelection(1);




//        }

        return rootView;

    }



//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        et_nombre= (EditText) getActivity().findViewById(R.id.etNombre);
//        et_edad= (EditText) getActivity().findViewById(R.id.etEdad);
//        et_direccion= (EditText) getActivity().findViewById(R.id.etDireccion);
//        et_telefono= (EditText) getActivity().findViewById(R.id.etTelefono);
//        et_estado_civil= (EditText) getActivity().findViewById(R.id.etEstadoCivil);
//        et_ocupacion= (EditText) getActivity().findViewById(R.id.etOcupacion);
//        et_direccion_ocu= (EditText) getActivity().findViewById(R.id.etDireccionOcu);
//        et_telefono_ocu= (EditText) getActivity().findViewById(R.id.etTelefonoOcu);
//
//
//
//        allegado= (EditText) getActivity().findViewById(R.id.etA);
//    }





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