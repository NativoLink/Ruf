package com.darkcode.ruf_012.Tratamientos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by NativoLink on 6/3/17.
 */

public class AdapterPlan extends ArrayAdapter<Plan> {
    private List<Plan> plans;
    public AdapterPlan(Context context,  List<Plan> planes) {
        super(context, R.layout.list_planes, planes);
        plans= planes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_planes, parent, false);

        TextView id_plan = (TextView)customView.findViewById(R.id.tvPlan);
        id_plan.setText(plans.get(position).getId_paciente()+" ID ");

        Button btnDetalle,btnEditar;
        btnDetalle = (Button)customView.findViewById(R.id.btnDetallePlan);
        btnEditar = (Button)customView.findViewById(R.id.btnEditarPlan);


        return customView;
    }
}
