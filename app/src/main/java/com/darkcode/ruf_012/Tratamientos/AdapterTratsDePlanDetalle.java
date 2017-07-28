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
 * Created by NativoLink on 15/7/17.
 */

public class AdapterTratsDePlanDetalle extends ArrayAdapter<Tratamiento> {

    private Context contexto;
    private List<Tratamiento> tratas;

    TextView nombreTrat;
    TextView costo;
    TextView cantidad;
    TextView totalRealiz;

    public AdapterTratsDePlanDetalle(Context context, List<Tratamiento> trats) {
        super(context, R.layout.list_trats_detalle, trats);
        contexto=context;
        tratas = trats;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_trats_detalle, parent, false);

        nombreTrat = (TextView) customView.findViewById(R.id.tvTratE);
        costo = (TextView) customView.findViewById(R.id.tvMontoE);
        cantidad = (TextView) customView.findViewById(R.id.tvCantidadE);
        totalRealiz = (TextView) customView.findViewById(R.id.tvRealizados);

        String nombre = tratas.get(position).getNombre();
        nombreTrat.setText(nombre);
        int cost = tratas.get(position).getCosto();
        String cost_s = Integer.toString(cost);
        costo.setText(cost_s);
        int cant = tratas.get(position).getCantidad();
        String cant_s = Integer.toString(cant);
        cantidad.setText(cant_s);
        int realizado = tratas.get(position).getCant_r();
        String realiz_s = Integer.toString(realizado);
        totalRealiz.setText(realiz_s);

        int totalCostoIndi = cost*cant;


        ((MainActivity) getContext()).setCostoTotalDetalleP(totalCostoIndi);
        ((MainActivity) getContext()).addOneCantTras();



        return customView;
    }
}
