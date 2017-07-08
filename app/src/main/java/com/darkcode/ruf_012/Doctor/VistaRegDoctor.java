package com.darkcode.ruf_012.Doctor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.darkcode.ruf_012.R;



import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 6/7/17.
 */

public class VistaRegDoctor extends Fragment {

    TextView etNombre,etUsuario,etClave,etDireccion_d,etTelefono,etCedula,etEspecialidad;
    String stNombre,stUsuario,stClave,stDireccion_d,stTelefono,stCedula,stEspecialidad;

    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    DoctorService servicio  = restadpter.create(DoctorService.class);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.reg_doctor, container, false);

        etNombre = (TextView)view.findViewById(R.id.etNombre);
        etUsuario = (TextView)view.findViewById(R.id.etUsuario);
        etClave = (TextView)view.findViewById(R.id.etClave);
        etDireccion_d = (TextView)view.findViewById(R.id.etDireccion_d);
        etTelefono = (TextView)view.findViewById(R.id.etTelefono);
        etCedula = (TextView)view.findViewById(R.id.etCedula);
        etEspecialidad = (TextView)view.findViewById(R.id.etEspecialidad);

        Button btnRegistrar = (Button)view.findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stNombre = etNombre.getText().toString();
                stUsuario = etUsuario.getText().toString();
                stClave = etClave.getText().toString();
                stDireccion_d = etDireccion_d.getText().toString();
                stTelefono = etTelefono.getText().toString();
                stCedula = etCedula.getText().toString();
                stEspecialidad = etEspecialidad.getText().toString();

                if(
                        (!stNombre.equals("") && !stUsuario.equals("")) && (!stNombre.equals(" ") && !stUsuario.equals(" ")) &&
                        (!stClave.equals("") && !stDireccion_d.equals("")) && (!stClave.equals(" ") && !stDireccion_d.equals(" ")) &&
                        (!stTelefono.equals("") && !stCedula.equals("")) && (!stTelefono.equals(" ") && !stCedula.equals(" ")) &&
                        (!stEspecialidad.equals("") &&!stEspecialidad.equals(" "))
                    )
                {
                    servicio.regDoctor(stNombre, stUsuario, stClave, stDireccion_d, stTelefono, stCedula, stEspecialidad, new Callback<String>() {
                        @Override
                        public void success(String s, Response response) {
                            Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getContext(),"Tiene campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });





        return view;

    }
}