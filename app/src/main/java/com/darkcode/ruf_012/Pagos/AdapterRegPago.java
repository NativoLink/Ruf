package com.darkcode.ruf_012.Pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by usuario on 12/05/2017.
 */



public class AdapterRegPago extends ArrayAdapter<ConsultaPendiente> {


    private Context contexto;
    private List<ConsultaPendiente> pago;
    private EditText monto;

    public AdapterRegPago(Context context, List<ConsultaPendiente> consultasPs) {
        super(context, R.layout.list_consultas_pago, consultasPs);
        contexto = context;
        pago = consultasPs;


    }


    @Override
    public View getView(final int position, View customView, ViewGroup parent) {
        final ViewCPagoHolder holder;

        int idps = pago.get(position).getId_consulta();
        if (customView == null) {
            holder = new ViewCPagoHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            customView = inflater.inflate(R.layout.list_consultas_pago, parent, false);

            holder.idConsulta = (TextView) customView.findViewById(R.id.tvConsulta);
            holder.pago_abono = (TextView) customView.findViewById(R.id.tvPago_o_abono);
            holder.tipo = (TextView) customView.findViewById(R.id.tvTipo);

            holder.btnRemove = (ImageButton) customView.findViewById(R.id.imgBtnRemove);


            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            //    EL MONTO A PAGAR O ABONAR DEBE SER ADD EN LA COMPROBACION DEL BOTTON ADD DE LA LISTA 1
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            int id = pago.get(position).getId_consulta();
            String idC = Integer.toString(id);

            int costo = pago.get(position).getCosto();
            String costoP = Integer.toString(costo);

//            int pendienteP = pago.get(position).getPendiente(); //DE ESTA MANERA FUNCIONA
            int pagoAbono = pago.get(position).getPagoAbono();
            String pagos = Integer.toString(pagoAbono);

            String tipo =  pago.get(position).getTipo();

            holder.idConsulta.setText(idC);
            holder.pago_abono.setText(pagos);
            holder.tipo.setText(tipo);
            holder.pago = costo;

            customView.setTag(holder);
        } else {
            holder = (ViewCPagoHolder) customView.getTag();
        }



        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        //                  SE DEBE HABILITAR EL BOTON DE REMOVER
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        holder.btnRemove.setTag(position);

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getContext()).TotalResta(pago.get(position).getCosto());
                ((MainActivity) getContext()).UpdateTotal();
                ((MainActivity) getContext()).RemovePago(pago.get(position));   // << ? ? ? DEBE REVISAR ESTO PARA QUE PUEDA REMOVER EL CORRECTO
                ((MainActivity) getContext()).MotoPagarSuma(holder.pago);
                ((MainActivity) getContext()).getMyAdapter2().notifyDataSetChanged();
            }
        });

        return customView;

    }


    class ViewCPagoHolder {
        TextView idConsulta;
        TextView pago_abono;
        TextView tipo;
        int pago;

        ImageButton btnRemove;


        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.list_consultas_pago, parent, false);


            final TextView idConsulta = (TextView) customView.findViewById(R.id.tvid_consulta);
            TextView pago_abono = (TextView) customView.findViewById(R.id.tvPago_o_abono);
            TextView tipo = (TextView) customView.findViewById(R.id.tvTipo);


            return customView;

        }
    }

    boolean ComprobacionSaldar(int monto, int p){
        boolean estado;
        if (monto<=p){
            estado= true;

        }
        else {
            estado= false;
        }

        return estado;
    }

}
