package com.darkcode.ruf_012.Tratamientos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by NativoLink on 25/7/17.
 */

public class AdapterTratsDConsulta extends ArrayAdapter<Tratamiento> {

    private Context contexto;
    private List<Tratamiento> tratamientos;
    private boolean existe;

    private  int cantt = 0;
    private  int costoTotal = 0;



    public AdapterTratsDConsulta(Context context, List<Tratamiento> tratamients) {
        super(context, R.layout.list_detalle_pago, tratamients);
        contexto=context;
        tratamientos = tratamients;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        contexto = getContext();
        final AdapterTratsRConsulta.VHolder holder;

        if (tratamientos.get(position).getId_p_tratamiento() != 0) {

        }
        if (convertView == null) {
            holder = new AdapterTratsRConsulta.VHolder();
            LayoutInflater inflater = LayoutInflater.from(contexto);
            convertView = inflater.inflate(R.layout.list_detalle_pago, parent, false);

            holder.tvNombre = (TextView) convertView.findViewById(R.id.tvid_consulta);
            holder.tvCantidad = (TextView) convertView.findViewById(R.id.tvpago);
            holder.tvCosto = (TextView) convertView.findViewById(R.id.tvTipo);


            holder.ref = position;
            holder.tvNombre.setText(tratamientos.get(position).getNombre());
            holder.tvCantidad.setText(String.valueOf(tratamientos.get(position).getCantidad()));
            holder.tvCosto.setText(String.valueOf(tratamientos.get(position).getCosto()));



        } else {
            holder = (AdapterTratsRConsulta.VHolder) convertView.getTag();
        }



        return convertView;


    }




}
