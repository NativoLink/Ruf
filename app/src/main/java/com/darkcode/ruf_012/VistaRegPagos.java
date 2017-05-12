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

public class VistaRegPagos extends Fragment {
    Bundle bundle;
    public VistaRegPagos(){
        bundle = new Bundle();

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.pagos, container,false);
        Fragment vistaL = new VistaListConsultasPendientes();
        Fragment vistaR = new VistaListConsultasPendientes();
//        changeViews( vistaL,R.id.fconsultas_pendientes);
        return view;
    }


    public void changeViews(Fragment vistaObj,Integer f_fragment){

//        int id_paciente = ((MainActivity)getContext()).getId_pacienteA();
//        int ultimo_plan = ((MainActivity)getContext()).getUltimo_plan();

//        bundle.putString("id_doctor", id_doctor);
//        bundle.putString("id_paciente", id_paciente);
//        bundle.putString("ultimo_plan", ultimo_plan);
//        vistaObj.setArguments(bundle);


        FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(f_fragment, vistaObj);
        transaction.addToBackStack(null);
        transaction.commit();
//        ((MainActivity)getContext()).hideBtnUnivesal(vActual);
//        ((MainActivity)getContext()).getSupportActionBar().setTitle(vActual);
    }

}
