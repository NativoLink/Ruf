package com.darkcode.ruf_012.Tratamientos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by NativoLink on 8/6/17.
 */

public class AdapterTratsParaPlan  extends ArrayAdapter<Tratamiento> {

    private Context contexto;
    private List<Tratamiento> tratamientos;

    public AdapterTratsParaPlan(Context context, List<Tratamiento> tratamients) {
        super(context, R.layout.list_tratamientos_planeados, tratamients);
        contexto=context;
        tratamientos = tratamients;
    }


    @Override
    public View getView(final int position, View customView, ViewGroup parent) {
        final ViewCPagoHolder holder;

        int idps = tratamientos.get(position).getId_p_tratamiento();
        if (customView == null) {
            holder = new ViewCPagoHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            customView = inflater.inflate(R.layout.list_tratamientos_planeados, parent, false);

            holder.nombre = (TextView) customView.findViewById(R.id.tvNombreTratamiento);
            holder.cant = (EditText) customView.findViewById(R.id.etCantidadAP);
            holder.monto = (EditText) customView.findViewById(R.id.etMonto);

//            holder.btnSaldar = (Button) customView.findViewById(R.id.btnabonar);

            customView.setTag(holder);
        } else {
            holder = (ViewCPagoHolder) customView.getTag();
        }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        //                  SE DEBE HABILITAR EL BOTON DE REMOVER
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//        holder.btnSaldar.setTag(position);

//        holder.btnSaldar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//            }
//        });

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        //    EL MONTO A PAGAR O ABONAR DEBE SER ADD EN LA COMPROBACION DEL BOTTON ADD DE LA LISTA 1
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        int id = tratamientos.get(position).getId_p_tratamiento();
        String idC = Integer.toString(id);

        int costo = tratamientos.get(position).getCosto();
        String costoP = Integer.toString(costo);

        holder.nombre.setText(tratamientos.get(position).getNombre());

        return customView;

    }

    class ViewCPagoHolder {
        TextView nombre;
        EditText cant;
        EditText monto;

//        Button btnSaldar;

    }
}
