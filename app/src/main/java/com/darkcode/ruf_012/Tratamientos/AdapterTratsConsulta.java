package com.darkcode.ruf_012.Tratamientos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by NativoLink on 14/3/17.
 */

public class AdapterTratsConsulta extends ArrayAdapter<Tratamiento> {

    private Context contexto;
    private List<Tratamiento> tratamientos;

    public AdapterTratsConsulta(Context context, List<Tratamiento> tratamients) {
        super(context, R.layout.list_trats_p_consulta, tratamients);
        contexto=context;
        tratamientos = tratamients;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        contexto = getContext() ;
        LayoutInflater inflater = LayoutInflater.from(contexto);
        final View customView = inflater.inflate(R.layout.list_trats_p_consulta, parent, false);


        CheckBox nombreT = (CheckBox) customView.findViewById(R.id.cbNombreTrat);
        TextView costo = (TextView) customView.findViewById(R.id.tvCosto);
        TextView estado = (TextView) customView.findViewById(R.id.tvEstado);

        nombreT.setText(tratamientos.get(position).getNombre());
        costo.setText(tratamientos.get(position).getCosto());

        return customView;

    }
}

