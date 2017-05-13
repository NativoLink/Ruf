package com.darkcode.ruf_012.Pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.darkcode.ruf_012.R;

/**
 * Created by usuario on 12/05/2017.
 */


public class AdapterRegPago extends ArrayAdapter< RegDetallePago> {

    private Context contexto;
    //private List<RegDetallePago> pago;

    public AdapterRegPago(Context context ) {
        super(context, R.layout.list_consultas_pago);
        contexto=context;



    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_consultas_pago, parent, false);

        final TextView idConsulta = (TextView) customView.findViewById(R.id.tvid_consulta);
        TextView pago_abono = (TextView) customView.findViewById(R.id.tvpago_o_abono);



        return customView;
    }
}
