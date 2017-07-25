package com.darkcode.ruf_012.Pagos;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;
import com.darkcode.ruf_012.VistaRegPlan;

import java.util.Iterator;
import java.util.List;



/**
 * Created by usuario on 07/05/2017.
 */

public class AdapterConPendientes extends ArrayAdapter {

    private Context contexto;
    private List<ConsultaPendiente> pago;
    List<ConsultaPendiente> tagsUse;
    int  Monto_u = 0 ;


    int monto_restante;
    int deudaTotal,deudaTotalSaldada = 0;
    int saldado = 0;
    int total_pendiente=0;


    int id,costo,pendienteP ;

    boolean monto_valido = false;


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

        int idps = pago.get(position).getId_consulta();
//        if (idps != 0) {
        if (customView == null) {
            holder = new ViewConHolder();
            LayoutInflater inflater = LayoutInflater.from(contexto);
            customView = inflater.inflate(R.layout.list_consultas_pendientes, parent, false);

            holder.idConsulta = (TextView) customView.findViewById(R.id.tvid_consulta);
            holder.fecha = (TextView) customView.findViewById(R.id.tvfecha);
            holder.monto = (TextView) customView.findViewById(R.id.tvmonto);
            holder.pendiente = (TextView) customView.findViewById(R.id.tvpendiente);
//            holder.estado = (TextView) customView.findViewById(R.id.tvestado);


            holder.btnDetalle = (Button) customView.findViewById(R.id.btndetalle);
            holder.btnSaldar = (Button) customView.findViewById(R.id.btnsaldar);
            holder.btnAbonar = (Button) customView.findViewById(R.id.btnabonar);


            id = pago.get(position).getId_consulta();
            String idC = Integer.toString(id);

             costo = pago.get(position).getCosto();
            String costoP = Integer.toString(costo);

            pendienteP = pago.get(position).getPendiente();
            String pen = Integer.toString(pendienteP);

            holder.fecha.setText(pago.get(position).getFecha());
            holder.monto.setText(costoP);
            holder.pendiente.setText(pen);
            holder.idConsulta.setText(idC);

            customView.setTag(holder);
        } else {
            holder = (ViewConHolder) customView.getTag();
        }

        holder.btnSaldar.setTag(position);



        holder.btnSaldar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ConsultaPendiente tag = pago.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Desea saldar la consulta con ID("+pago.get(position).getId_consulta()+") ?")
                        .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tagsUse = ((MainActivity) getContext()).getaPago();
                                if (estaEnArray(tag, tagsUse)) {
                                    Toast.makeText(getContext(), "Ya se encuentra añadido", Toast.LENGTH_LONG).show();
                                } else {
                                    ProcesarSaldar(position);
                                }
                            }
                        })
                        .setTitle("Confirmar Pago")
                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.btnAbonar.setTag(position);
        holder.btnAbonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Registrar Abono", Toast.LENGTH_SHORT).show();
                ((MainActivity) getContext()).regAbono("Registrar Abono",position,pago,contexto).show();

            }
        });




//        holder.estado.setText(pago.get(position).getEstado());

        if (idps == 0) {
            customView.setVisibility(View.INVISIBLE);
        }

        deudaTotal = deudaTotal + costo;
        total_pendiente += pendienteP;
        deudaTotalSaldada = deudaTotal - total_pendiente;
        ((MainActivity) getContext()).setTvdetotal(deudaTotal);
        ((MainActivity) getContext()).setTvdesaldada(deudaTotalSaldada);
        ((MainActivity) getContext()).UpddateDeuda();

        return customView;

    }

    class ViewConHolder {
        TextView idConsulta;
        TextView fecha;
        TextView monto;
        TextView pendiente;

//        TextView estado;

        Button btnDetalle;
        Button btnSaldar;
        Button btnAbonar;
    }


    public boolean updateDispoSaldar(int position){
        boolean resta;
//        if(((MainActivity) getContext()).getMonto_a_pagar() >= pago.get(position).getCosto()){
//            Toast.makeText(getContext(), "Puede Saldar", Toast.LENGTH_SHORT).show();
//            monto_restante = ((MainActivity) getContext()).getMonto_a_pagar() - pago.get(position).getCosto();
            ((MainActivity) getContext()).MotoPagarResta(pago.get(position).getCosto());
            resta = true;
//        }else{
            resta = false;
            Toast.makeText(getContext(), "Monto insufuciente", Toast.LENGTH_SHORT).show();
//        }
        return resta;
    }


    public void ProcesarSaldar(int position){
        String tipo ="saldada";
//        if(monto_valido == false) {
            monto_valido = true;
//            Toast.makeText(getContext(), "==>"+ ((MainActivity) getContext()).getMonto_a_pagar(), Toast.LENGTH_SHORT).show();
//            monto_restante =  ((MainActivity) getContext()).getMonto_a_pagar();
//            if(updateDispoSaldar(position)) {
                int saldando = pago.get(position).getCosto(); // <- - - - - - -- - - - - - - - -{{ CANTIDA A PAGAR PARA SALDAR O ABONAR }}
                pago.get(position).setPagoAbono(saldando);// SALDANDO EL TOTAL DE ESTA CONSULTA
                pago.get(position).setTipo(tipo); // SALDANDO EL TOTAL DE ESTA CONSULTA
                ((MainActivity) getContext()).AddPago(pago.get(position)); // AGREGA UN NUEVO ELEMENTO A List<?>
                ((MainActivity) getContext()).getMyAdapter2().notifyDataSetChanged(); // ACTUALIZA EL ADAPTER SEGUN SU List<?>
                ((MainActivity) getContext()).TotalSuma(pago.get(position).getCosto());
                ((MainActivity) getContext()).UpdateTotal();
//                Toast.makeText(getContext(), "Monto disponible = "+((MainActivity) getContext()).getMonto_a_pagar(), Toast.LENGTH_SHORT).show();
//            }
//        }else{
//            monto_restante =  ((MainActivity) getContext()).getMonto_a_pagar();
//            if(updateDispoSaldar(position)) {
//                ((MainActivity) getContext()).AddPago(pago.get(position)); // AGREGA UN NUEVO ELEMENTO A List<?>
//                ((MainActivity) getContext()).getMyAdapter2().notifyDataSetChanged(); // ACTUALIZA EL ADAPTER SEGUN SU List<?>
//                ((MainActivity) getContext()).TotalSuma(pago.get(position).getCosto());
//                ((MainActivity) getContext()).UpdateTotal();
//                Toast.makeText(getContext(), "Monto disponible = "+((MainActivity) getContext()).getMonto_a_pagar(), Toast.LENGTH_SHORT).show();
//            }
//        }
    }


//    * * * ESTO NO LO ESTOY USADO PERO LO NECESITO * * *
//    public void Abonar(int position,int valor_a_abonar,List<ConsultaPendiente> pago,Context context){
//        if(valor_a_abonar > 0) {
//            String tipo ="abono";
//            monto_restante =  ((MainActivity) context).getMonto_a_pagar();
//                pago.get(position).setTipo(tipo);
//                pago.get(position).setPagoAbono(valor_a_abonar); // SALDANDO EL TOTAL DE ESTA CONSULTA
//                ((MainActivity) context).AddPago(pago.get(position)); // AGREGA UN NUEVO ELEMENTO A List<?>
//                ((MainActivity) context).getMyAdapter2().notifyDataSetChanged(); // ACTUALIZA EL ADAPTER SEGUN SU List<?>
//                ((MainActivity) context).TotalSuma(valor_a_abonar);
//                ((MainActivity) context).UpdateTotal();
//                Toast.makeText(context, "Monto disponible = "+((MainActivity) context).getMonto_a_pagar(), Toast.LENGTH_SHORT).show();
//        }else{
//                Toast.makeText(context, "Monto disponible = "+((MainActivity) context).getMonto_a_pagar(), Toast.LENGTH_SHORT).show();
//        }
//    }


//    public AlertDialog regAbono(String titulo, final int position, final List<ConsultaPendiente> pago, final Context context, final Activity activity){
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        final View v = View.inflate(contexto, R.layout.dialog_abono, null);
//
//        TextView title =  (TextView)v.findViewById(R.id.tvTitle);
//        title.setText(titulo);
//
//        builder
//                .setPositiveButton(R.string.registrar, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        EditText newTrat =  (EditText)v.findViewById(R.id.edNewAbono);
//                        int valor_a_abonar = Integer.valueOf(newTrat.getText().toString());
//                        Abonar(position, valor_a_abonar,pago,context);
//                    }
//                })
//                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    }
//                });
//
//        builder.setView(v);
//        AlertDialog dialog = builder.create();
//        return dialog;
//    }



    public List<ConsultaPendiente> getList(){
        return pago;
    }

    public boolean estaEnArray(ConsultaPendiente cp, List<ConsultaPendiente> permitidos){
        boolean existe = false;
        for (Iterator<ConsultaPendiente> i = permitidos.iterator(); i.hasNext();) {
//               Log.v("CP","ITEM => "+item);
            ConsultaPendiente item = i.next();
            if(item.id_consulta == cp.id_consulta){
                Log.v("CP","EXITE CP => "+cp.id_consulta + " COMO ITEM => "+item.id_consulta);
                existe = true;
            }
        }
        return existe;

    }

}
