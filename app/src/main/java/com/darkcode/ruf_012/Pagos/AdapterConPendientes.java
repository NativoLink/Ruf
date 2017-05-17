package com.darkcode.ruf_012.Pagos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by usuario on 07/05/2017.
 */

public class AdapterConPendientes extends ArrayAdapter {

    private Context contexto;
    private List<ConsultaPendiente> pago;


    public AdapterConPendientes(Context context, List<ConsultaPendiente> pagos) {
        super(context,R.layout.list_consultas_pendientes ,pagos);
        contexto=context;
        pago = pagos;

    }


    @Override
    public int getCount() {
        return pago.size();
    }

    @Override
    public ConsultaPendiente getItem(int position) {
        return pago.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View customView, final ViewGroup parent) {
        final ViewConHolder holder;
        if (customView == null) {
            holder = new ViewConHolder();
            LayoutInflater inflater = LayoutInflater.from(contexto);
            customView = inflater.inflate(R.layout.list_consultas_pendientes, parent, false);

            holder.idConsulta = (TextView) customView.findViewById(R.id.tvid_consulta);
            holder.fecha = (TextView) customView.findViewById(R.id.tvfecha);
            holder.monto = (TextView) customView.findViewById(R.id.tvmonto);
            holder.pendiente = (TextView) customView.findViewById(R.id.tvpendiente);
            holder.estado = (TextView) customView.findViewById(R.id.tvestado);


            holder.btnDetalle = (Button) customView.findViewById(R.id.btndetalle);
            holder.btnSaldar = (Button) customView.findViewById(R.id.btnsaldar);
            holder.btnAbonar = (Button) customView.findViewById(R.id.btnabonar);

            customView.setTag(holder);
        }else{
            holder = (ViewConHolder) customView.getTag();
        }

            holder.btnSaldar.setTag(position);
        final View finalCustomView = customView;
        holder.btnSaldar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    int tag = (Integer)v.getTag();
                    if (tag != (pago.size() - 1)) {
                        pago.remove(tag);
                        AdapterConPendientes.this.notifyDataSetChanged();

                        ((MainActivity)getContext()).AddPago(pago.get(position));

                        AdapterConPendientes adp = new AdapterConPendientes(contexto,((MainActivity)getContext()).getaPago());
                        ((MainActivity)getContext()).setMyAdapter2(adp);
                        AdapterConPendientes.this.notifyDataSetChanged();
                        ((MainActivity)getContext()).getMyAdapter2().notifyDataSetChanged();
                    } else {
                        pago.add(pago.get(position));
                        ((MainActivity)getContext()).AddPago(pago.get(position));

                        ((MainActivity)getContext()).sumConCP();
                        AdapterConPendientes adp = new AdapterConPendientes(contexto,((MainActivity)getContext()).getaPago());
                        ListView listView2 = (ListView) finalCustomView.findViewById(R.id.lvConAPagar);
                        ((MainActivity)getContext()).setMyList2(listView2);
                        ((MainActivity)getContext()).setMyAdapter2(adp);
                        for(ConsultaPendiente cp : ((MainActivity)getContext()).getaPago())
                        {
                            Log.v("ARRAY ADAPTER CP",cp.getId_consulta()+"=>"+cp.getCosto()+"conCP=>"+((MainActivity)getContext()).getContCP());
                        }

                        AdapterConPendientes.this.notifyDataSetChanged();
                        ((MainActivity)getContext()).getMyAdapter2().notifyDataSetChanged();
                    }
                }
            });


        int id = pago.get(position).getId_consulta();
        String idC = Integer.toString(id);

        int costo = pago.get(position).getCosto();
        String costoP = Integer.toString(costo);

        int pendienteP = pago.get(position).getPendiente();
        String pen = Integer.toString(pendienteP);

        holder.fecha.setText(pago.get(position).getFecha());
        holder.monto.setText(costoP);
        holder.pendiente.setText(pen);
        holder.idConsulta.setText(idC);
        holder.estado.setText(pago.get(position).getEstado());


        return customView;

    }

    class ViewConHolder {
        TextView idConsulta;
        TextView fecha;
        TextView monto;
        TextView pendiente;
        TextView estado;

        Button btnDetalle;
        Button btnSaldar;
        Button btnAbonar;
    }


    public List<ConsultaPendiente> getList(){
        return pago;
    }

}
