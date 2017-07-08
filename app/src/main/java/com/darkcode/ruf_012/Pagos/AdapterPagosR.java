package com.darkcode.ruf_012.Pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by NativoLink on 7/7/17.
 */

public class AdapterPagosR extends ArrayAdapter<PagoR> {

    private Context contexto;
    private List<PagoR> pago;

    public AdapterPagosR(Context context, List<PagoR> pagos) {
        super(context, R.layout.list_pagos_pacientes, pagos);
        contexto = context;
        pago = pagos;

    }
    @Override
    public View getView(final int position, View customView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        customView = inflater.inflate(R.layout.list_pagos_pacientes, parent,false);

        TextView tvIdPago = (TextView)customView.findViewById(R.id.tvIdPago);
        TextView tvFechaPago = (TextView)customView.findViewById(R.id.tvFechaPago);
        TextView tvPago = (TextView)customView.findViewById(R.id.tvPago);
        ImageButton btnDetalleP = (ImageButton)customView.findViewById(R.id.btnDetalleP);

        String idPago = String.valueOf(pago.get(position).getId_pago());
        tvIdPago.setText(idPago);

        tvFechaPago.setText(pago.get(position).getFecha());

        String total = String.valueOf(pago.get(position).getTotal());
        tvPago.setText(total);



        return customView;
    }
}
