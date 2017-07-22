package com.darkcode.ruf_012.Pagos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import java.util.Iterator;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by NativoLink on 14/5/17.
 */

public class p2ListView extends Fragment {

    ListView listView1, listView2;

    AdapterConPendientes myItemsListAdapter1;
    AdapterRegPago myItemsListAdapter2;
    TextView tvNombrePaciente,tvNoRecibo;
    RestAdapter restadpter ;
    PagoService servicio;
    Button regPago;
    EditText etNota;

    public p2ListView() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p2_list_view, container,false);

        ((MainActivity)getContext()).setTotal(0);

        restadpter = ((MainActivity)getContext()).getRestadpter();
        servicio = restadpter.create(PagoService.class);

        listView1 = (ListView)view.findViewById(R.id.lvConPendientes);
        listView2 = (ListView)view.findViewById(R.id.lvConAPagar);
        tvNombrePaciente= (TextView)view.findViewById(R.id.tvNombrePaciente);
        tvNombrePaciente.setText(((MainActivity)getContext()).getNOMBRES());

        tvNoRecibo= (TextView)view.findViewById(R.id.tvnoregistro);
        servicio.getNumRecibo(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                tvNoRecibo.setText(s);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        regPago  = (Button)view.findViewById(R.id.btnregistrar);
        etNota  = (EditText)view.findViewById(R.id.etNota);
        regPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = ((MainActivity)getContext()).getTotal();
                String nota = etNota.getText().toString();
                servicio.regPagos(total, nota, new Callback<String>() {
//                    aPago
                        List<ConsultaPendiente> ite = CalcularPago();
                    @Override
                    public void success(String s, Response response) {
                        int id_consulta;
                        for(int i=0; i< ite.size(); i++) {
                            id_consulta = ite.get(i).getId_consulta();
                            final int pago_texting= ite.get(i).getPagoAbono();
                            final int id_pago  = Integer.parseInt(s);
                            int total =0; ///falta tomar
                            final String tipo = ite.get(i).getTipo();
                            final int id_paciente =  ((MainActivity) getContext()).getId_pacienteA();
                            servicio.regDetallePago(id_consulta, id_paciente, id_pago, pago_texting, ite.get(i).getTipo() ,new Callback<String>() {
                                @Override
                                public void success(String s, Response response) {
                                    Toast.makeText(getContext()," Return "+pago_texting, Toast.LENGTH_LONG).show();
                                    Log.v("PARAMS","id_pago= "+id_pago+"   id_paciente= "+ id_paciente+" PAGO= "+ pago_texting+" TIPO= "+ tipo);
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Toast.makeText(getContext(), "ERROR 2 "+error.getMessage(), Toast.LENGTH_LONG).show();
                                }

                            });
//                            Toast.makeText(getContext(), "Cantidad R => "+ ite.get(i).getCosto(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), "ERROR 1 "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });






        ((MainActivity)getContext()).setMyList1(listView1);
        ((MainActivity)getContext()).setMyList2(listView2);

        initItems();

        myItemsListAdapter2 = new AdapterRegPago(getContext(),((MainActivity)getContext()).getaPago());

        ((MainActivity)getContext()).setMyAdapter2(myItemsListAdapter2);
        ((MainActivity)getContext()).getMyList2().setAdapter(myItemsListAdapter2);
        ((MainActivity)getContext()).getMyAdapter2().notifyDataSetChanged();


        EditText cliente_paga = (EditText)view.findViewById(R.id.etMontoP);

        cliente_paga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if(!s.toString().equals("") && s.toString()!="" && s.toString()!="0") {
//                    int monto = Integer.valueOf(s.toString());
//                    ((MainActivity) getContext()).setMonto_a_pagar(monto);
//                    Toast.makeText(getContext(),"BEFORE", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals("") && s.toString()!="" && s.toString()!="0") {
                    int monto = Integer.valueOf(s.toString());
                    ((MainActivity) getContext()).setMonto_a_pagar(monto);
//                    Toast.makeText(getContext(),"CHANGE", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                if(!s.toString().equals("") && s.toString()!="" && s.toString()!="0") {
//                    int monto = Integer.valueOf(s.toString());
//                    ((MainActivity) getContext()).setMonto_a_pagar(monto);
//                    Toast.makeText(getContext(),"AFTER", Toast.LENGTH_LONG).show();
//                }

            }
        });



        return view;
    }

    private void initItems(){
        ((MainActivity)getContext()).aPago.clear();

        servicio.getPagos(((MainActivity)getContext()).getId_pacienteA(), new Callback<List<ConsultaPendiente>>() {
            @Override
            public void success(List<ConsultaPendiente> pagos, Response response) {
                myItemsListAdapter1 = new AdapterConPendientes(getContext(), pagos);
                ((MainActivity)getContext()).setMyAdapter(myItemsListAdapter1);
                listView1.setAdapter(myItemsListAdapter1);
            }

            @Override
            public void failure(RetrofitError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public List<ConsultaPendiente> CalcularPago(){
        return ((MainActivity)getContext()).getaPago();
    }



}


