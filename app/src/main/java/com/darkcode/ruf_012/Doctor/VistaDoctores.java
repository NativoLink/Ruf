package com.darkcode.ruf_012.Doctor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 9/7/17.
 */

public class VistaDoctores extends Fragment {

    ImageButton buscarPaciente;
    EditText nombrePaciente;
    String id_d;
    int id_doctor;

    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    DoctorService servicio =  restadpter.create(DoctorService.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_list_doctores, container, false);

//        final String id_d = this.getArguments().getString("id_doctor");
        id_d = ((MainActivity) getContext()).getId_doctor();
        try {
            id_doctor  = Integer.parseInt(id_d);
        }catch (NumberFormatException ex){
            Log.v("ERROR NumberFormat"," VistaDoctores => NumberFormat - NULL");
        }


        final ListView lvresult;
        lvresult = (ListView) view.findViewById(R.id.lvTrans);
        buscarPaciente = (ImageButton) view.findViewById(R.id.btnBuscarPaciente);
        nombrePaciente = (EditText) view.findViewById(R.id.etBuscarPaciente);

        buscarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreP = nombrePaciente.getText().toString();
                servicio.getBuscarDoctor(nombreP, new Callback<List<Doctor>>() {


                    @Override
                    public void success(List<Doctor> doctors, Response response) {
                        AdapterDoctores listAdapter = new AdapterDoctores(getContext(), doctors);
                        lvresult.setAdapter(listAdapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        servicio.getDoctores(new Callback<List<Doctor>>() {
            @Override
            public void success(List<Doctor> doctors, Response response) {
                AdapterDoctores listAdapter = new AdapterDoctores(getContext(), doctors);
                lvresult.setAdapter(listAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
