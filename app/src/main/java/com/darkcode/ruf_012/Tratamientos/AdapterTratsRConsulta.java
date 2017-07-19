package com.darkcode.ruf_012.Tratamientos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by NativoLink on 14/3/17.
 */

public class AdapterTratsRConsulta extends ArrayAdapter<Tratamiento>{

    private Context contexto;
    private List<Tratamiento> tratamientos;
    private boolean existe;

    private  int cantt = 0;
    private  int costoTotal = 0;



    public AdapterTratsRConsulta(Context context, List<Tratamiento> tratamients) {
        super(context, R.layout.list_trats_r_detalle_c, tratamients);
        contexto=context;
        tratamientos = tratamients;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        contexto = getContext();
        final VHolder holder;

        if (tratamientos.get(position).getId_p_tratamiento() != 0) {

        }
        if (convertView == null) {
            holder = new VHolder();
            LayoutInflater inflater = LayoutInflater.from(contexto);
            convertView = inflater.inflate(R.layout.list_trats_r_detalle_c, parent, false);

            holder.tvNombre = (TextView) convertView.findViewById(R.id.tvNombreTrat);
            holder.tvCantidad = (TextView) convertView.findViewById(R.id.tvCantidad);
            holder.tvCosto = (TextView) convertView.findViewById(R.id.tvCosto);
            holder.tvTotalIndi = (TextView) convertView.findViewById(R.id.tvTotalIndi);

            holder.ref = position;
            holder.tvNombre.setText(tratamientos.get(position).getNombre());
            holder.tvCantidad.setText(String.valueOf(tratamientos.get(position).getCantidad()));

            holder.tvCosto.setText(String.valueOf(tratamientos.get(position).getCosto()));


            int cant = tratamientos.get(position).getCantidad();
            int cost = tratamientos.get(position).getCosto();
            int costo_total = cant * cost;
            costoTotal += costo_total;
            ((MainActivity) getContext()).setcTratsRTotal(costoTotal);
            holder.tvTotalIndi.setText(String.valueOf(costo_total));


        } else {
            holder = (VHolder) convertView.getTag();
        }










//        }


        return convertView;


    }

    public static class VHolder {
        TextView tvCantidad;
        TextView tvNombre;
        TextView tvCosto;
        TextView tvTotalIndi;
        int ref;
    }




}

