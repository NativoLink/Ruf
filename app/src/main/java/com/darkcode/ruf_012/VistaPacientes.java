package com.darkcode.ruf_012;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.darkcode.ruf_012.Paciente.AdapterPacientes;
import com.darkcode.ruf_012.Paciente.Paciente;
import com.darkcode.ruf_012.Paciente.PacienteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 18/2/17.
 */

public class VistaPacientes extends Fragment {

    private OnFragmentInteractionListener mListener;
    ImageButton buscarPaciente;
    EditText nombrePaciente;
    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    PacienteService servicio = restadpter.create(PacienteService.class);

    public VistaPacientes() {

    }

    int id_doctor;
    String id_d;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_list, container, false);

        try {
            final String id_d = this.getArguments().getString("id_doctor");
            id_doctor = Integer.parseInt(id_d);

        }catch(NumberFormatException ex){
            Toast.makeText(getContext(),"ERROR: "+ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        final ListView lvresult;
        lvresult = (ListView)view.findViewById(R.id.lvTrans);
        buscarPaciente = (ImageButton)view.findViewById(R.id.btnBuscarPaciente);
        nombrePaciente = (EditText)view.findViewById(R.id.etBuscarPaciente);

        buscarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreP = nombrePaciente.getText().toString();
                servicio.getPaciente(nombreP,new Callback<List<Paciente>>() {


                    @Override
                    public void success(List<Paciente> pacientes, Response response) {
                        AdapterPacientes listAdapter = new AdapterPacientes(getContext(), pacientes);
                        listAdapter.setId_doctor(id_d);
                        lvresult.setAdapter(listAdapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(),"ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



        servicio.getPacientes(new Callback<List<Paciente>>() {
            @Override
            public void success(List<Paciente> pacientes, Response response) {
                AdapterPacientes listAdapter = new AdapterPacientes(getContext(), pacientes);
                listAdapter.setId_doctor(id_d);
                lvresult.setAdapter(listAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(),"ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
