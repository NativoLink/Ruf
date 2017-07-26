package com.darkcode.ruf_012.Tratamientos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;
import com.darkcode.ruf_012.VistaDetallePlan;
import com.darkcode.ruf_012.VistaEditPlan;

import java.util.List;

/**
 * Created by NativoLink on 6/3/17.
 */

public class AdapterPlan extends ArrayAdapter<Plan> {
    private List<Plan> plans;
    Fragment vista;
    ImageButton btnDetalle, btnEditar;
    public AdapterPlan(Context context,  List<Plan> planes) {
        super(context, R.layout.list_planes, planes);
        plans= planes;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_planes, parent, false);

        int id = plans.get(position).getId_plan();

            TextView id_plan = (TextView) customView.findViewById(R.id.tvPlan);
            id_plan.setText("ID:" + plans.get(position).getId_plan() + "              Fecha: (" + plans.get(position).getFecha_reg() + ")              [ " + plans.get(position).getEstado()+" ] ");

            btnDetalle = (ImageButton) customView.findViewById(R.id.btnDetallePlan);
            btnEditar = (ImageButton) customView.findViewById(R.id.btnEditarPlan);

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vista = new VistaEditPlan();
                    String Titulo_Bar = "Editar Plan";
                    changeVista( vista,Titulo_Bar,position);
                }
            });



            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vista = new VistaDetallePlan();
                    String Titulo_Bar = "Detalle de Plan";
                    changeVista( vista,Titulo_Bar,position);
                }
            });

        if (id == 0) {
//            customView.setVisibility(View.INVISIBLE);
            id_plan.setText("No tiene ningun plan registrado en el sistema");
            btnEditar.setVisibility(View.INVISIBLE);
            btnDetalle.setVisibility(View.INVISIBLE);
        }
        if(plans.get(position).getEstado()=="completo" || plans.get(position).getEstado().equals("completo")) {
            btnEditar.setVisibility(View.INVISIBLE);
        }




        return customView;
    }

    public void changeVista(Fragment vista, String titulo, int position){
        Bundle arg = new Bundle();
        arg.putString("id_paciente", String.valueOf(plans.get(position).getId_paciente()));
        arg.putString("id_plan", String.valueOf(plans.get(position).getId_plan()));
        ((MainActivity) getContext()).setId_plan_select(plans.get(position).getId_plan());
        String Titulo_Bar = "Detalle de Plan";
        ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
        ((MainActivity) getContext()).cambioVistaU(vista, Titulo_Bar, arg);
    }

}
