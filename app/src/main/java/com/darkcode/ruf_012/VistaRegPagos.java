package com.darkcode.ruf_012;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by usuario on 07/05/2017.
 */

public class VistaRegPagos extends android.support.v4.app.Fragment {
    Bundle bundle;
    public VistaRegPagos(){
        bundle = new Bundle();

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.pagos, container,false);

        return view;
    }


    public void cambiarVista(Fragment vistaObj, String vActual){

        int id_paciente = ((MainActivity)getContext()).getId_pacienteA();
        int ultimo_plan = ((MainActivity)getContext()).getUltimo_plan();

//        bundle.putString("id_doctor", id_doctor);
//        bundle.putString("id_paciente", id_paciente);
//        bundle.putString("ultimo_plan", ultimo_plan);
//        vistaObj.setArguments(bundle);
//        ((MainActivity)getContext()).setId_pacienteA(id_Paciente);
//        ((MainActivity)getContext()).setTotalRegConsulta(0);
//        ((MainActivity)getContext()).setUltimo_plan(ultP);
//
//        FragmentTransaction transaction = ((FragmentActivity)contexto).getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.f_main, vistaObj);
//        transaction.addToBackStack(null);
//        transaction.commit();
//        ((MainActivity)getContext()).hideBtnUnivesal(vActual);
//        ((MainActivity)getContext()).getSupportActionBar().setTitle(vActual);
    }

}
