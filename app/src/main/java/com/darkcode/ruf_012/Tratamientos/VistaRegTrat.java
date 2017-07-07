package com.darkcode.ruf_012.Tratamientos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.darkcode.ruf_012.R;
import retrofit.RestAdapter;


/**
 * Created by NativoLink on 6/7/17.
 */

public class VistaRegTrat extends Fragment {

    TextView etNombre,etTipo;
    String stNombre,stTipo;

    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    TratamientoService servicio  = restadpter.create(TratamientoService.class);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.reg_tratamiento, container, false);

//        etNombre = (TextView)view.findViewById(R.id.etNombre);
//        etTipo = (TextView)view.findViewById(R.id.etTipo);
//
//        Button btnRegistrar = (Button)view.findViewById(R.id.btnRegistrar);
//
//        btnRegistrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stNombre = etNombre.getText().toString();
//                stTipo = etUsuario.getText().toString();
//
//
//                if(
//                        (!stNombre.equals("") && !stUsuario.equals("")) && (!stNombre.equals(" ") && !stUsuario.equals(" ")) &&
//                                (!stClave.equals("") && !stDireccion_d.equals("")) && (!stClave.equals(" ") && !stDireccion_d.equals(" ")) &&
//                                (!stTelefono.equals("") && !stCedula.equals("")) && (!stTelefono.equals(" ") && !stCedula.equals(" ")) &&
//                                (!stEspecialidad.equals("") &&!stEspecialidad.equals(" "))
//                        )
//                {
//                    servicio.regTrat(stNombre, stTipo, new Callback<String>() {
//                        @Override
//                        public void success(String s, Response response) {
//                            Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void failure(RetrofitError error) {
//                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }else{
//                    Toast.makeText(getContext(),"Tiene campos vacios", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//
//


        return view;

    }
}