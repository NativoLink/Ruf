package com.darkcode.ruf_012.Pagos;

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
 * Created by usuario on 07/05/2017.
 */

public class AdapterConPendientes extends ArrayAdapter< ConsultasPendientes> {

    private Context contexto;
    private List<ConsultasPendientes> pago;
    public AdapterConPendientes(Context context, List<ConsultasPendientes> pagos) {
        super(context, R.layout.list_consultas_pendientes, pagos);
        contexto=context;
        pago = pagos;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_consultas_pendientes, parent, false);

        final TextView idConsulta = (TextView) customView.findViewById(R.id.tvid_consulta);
        TextView fecha = (TextView) customView.findViewById(R.id.tvfecha);
        TextView monto = (TextView) customView.findViewById(R.id.tvmonto);
        TextView pendiente = (TextView) customView.findViewById(R.id.tvpendiente);
        TextView estado = (TextView) customView.findViewById(R.id.tvestado);


        Button btnDetalle = (Button) customView.findViewById(R.id.btndetalle);
        Button btnSaldar = (Button) customView.findViewById(R.id.btnsaldar);
        Button btnAbonar = (Button) customView.findViewById(R.id.btnabonar);


        int id = pago.get(position).getId_consulta();
        String idC = Integer.toString(id);
        idConsulta.setText(idC);

        fecha.setText(pago.get(position).getFecha());

        int costo = pago.get(position).getCosto();
        String costoP = Integer.toString(costo);
        monto.setText(costoP);

        int pendienteP = pago.get(position).getPendiente();
        String pen = Integer.toString(pendienteP);
        pendiente.setText(pen);

        estado.setText(pago.get(position).getEstado());


        return customView;

    }

}
