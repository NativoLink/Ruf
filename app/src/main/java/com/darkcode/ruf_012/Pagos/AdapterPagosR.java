package com.darkcode.ruf_012.Pagos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.Doctor.DoctorService;
import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.Paciente.Especialidad;
import com.darkcode.ruf_012.R;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 7/7/17.
 */

public class AdapterPagosR extends ArrayAdapter<PagoR> {

    private Context contexto;
    private List<PagoR>pagos;
    PagoService servicio;
    RestAdapter restadpter;
    int id_paciente;
    AdapterDetallePagoR listAdapter;

    ListView lvresult;

    public AdapterPagosR(Context context, List<PagoR> pagos) {
        super(context, R.layout.list_pagos_pacientes, pagos);
        contexto = context;
        this.pagos = pagos;

    }
    @Override
    public View getView(final int position, View customView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        customView = inflater.inflate(R.layout.list_pagos_pacientes, parent,false);

        TextView tvIdPago = (TextView)customView.findViewById(R.id.tvIdPago);
        TextView tvFechaPago = (TextView)customView.findViewById(R.id.tvFechaPago);
        TextView tvPago = (TextView)customView.findViewById(R.id.tvPago);
        ImageButton btnDetalleP = (ImageButton)customView.findViewById(R.id.btnDetalleP);

        lvresult = (ListView)customView.findViewById(R.id.lvPagosPacientes);

        String idPago = String.valueOf(pagos.get(position).getId_pago());
        tvIdPago.setText(idPago);

        tvFechaPago.setText(pagos.get(position).getFecha());

        String total = String.valueOf(pagos.get(position).getTotal());
        tvPago.setText(total);

        id_paciente = ((MainActivity)getContext()).getId_pacienteA();


         restadpter = ((MainActivity)getContext()).getRestadpter();
        servicio= restadpter.create(PagoService.class);

        btnDetalleP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                listDEtalleP(position);
               servicio.getDetallePagosR(
                       id_paciente,
                       pagos.get(position).getId_pago(),
                       new Callback<List<DetallePagoR>>() {
                           @Override
                           public void success(List<DetallePagoR> pagoRs, Response response) {
//
                               ((MainActivity) getContext()).detallePagosR("Detalle Pagos",pagoRs,contexto).show();
                               Toast.makeText(getContext(), "KLK", Toast.LENGTH_LONG).show();
                           }

                           @Override
                           public void failure(RetrofitError error) {
                               Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                               Log.v("ERROR","ERROR ==> getDetallePagosR ");
                           }
                       });

            }
        });



        return customView;
    }


    public void listDEtalleP(int position){

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(contexto);
//        builderSingle.setIcon(R.mipmap.ic_launcher);
        builderSingle.setTitle("Pagos de Consultas");

//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(contexto, android.R.layout.list_detalle_pago);

        final AdapterDetallePagoR[] arrayAdapter = {null};

        servicio.getDetallePagosR(
                id_paciente,
                pagos.get(position).getId_pago(),
                new Callback<List<DetallePagoR>>() {
                    @Override
                    public void success(List<DetallePagoR> pagoRs, Response response) {
                        listAdapter = new AdapterDetallePagoR(getContext(), pagoRs);
                        lvresult.setAdapter(listAdapter);
                        arrayAdapter[0] = listAdapter;

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.v("ERROR","ERROR ==> getDetallePagosR ");
                    }
                });

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builderSingle.show();
    }
}
