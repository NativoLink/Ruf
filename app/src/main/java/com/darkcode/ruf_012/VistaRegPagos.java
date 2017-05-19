package com.darkcode.ruf_012;

import android.os.Bundle;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.pagos, container,false);
        Fragment vistaL = new VistaListConsultasPendientes();
        Fragment vistaR = new VistaConsultasPagadas();
        changeViews( vistaL,R.id.fconsultas_pendientes);
        changeViews( vistaR,R.id.freg_pago);
        return view;
    }


    public void changeViews(Fragment vistaObj,Integer f_fragment){

        FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(f_fragment, vistaObj);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}
