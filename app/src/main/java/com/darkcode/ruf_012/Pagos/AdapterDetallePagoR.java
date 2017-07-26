package com.darkcode.ruf_012.Pagos;

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
 * Created by NativoLink on 24/7/17.
 */

public class AdapterDetallePagoR extends ArrayAdapter {


    private Context contexto;
    private List<DetallePagoR> pagos;

    public AdapterDetallePagoR(Context context, List<DetallePagoR> pagosR) {
        super(context, R.layout.list_detalle_pago ,pagosR);
        contexto=context;
        pagos = pagosR;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_detalle_pago, parent, false);


        TextView id_consulta = (TextView)customView.findViewById(R.id.tvid_consulta);
        TextView pago = (TextView)customView.findViewById(R.id.tvpago);
        TextView tipo = (TextView)customView.findViewById(R.id.tvTipo);

//        ((MainActivity)getContext()).setTotalDetallePagosR(pagos.get(position).getTotal());

        id_consulta.setText(String.valueOf(pagos.get(position).getId_consulta()));
        pago.setText(String.valueOf(pagos.get(position).getPago()));
        tipo.setText(pagos.get(position).getTipo());

        return customView;
    }
}
