package com.darkcode.ruf_012.Tratamientos;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta.checkItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by NativoLink on 14/3/17.
 */

public class AdapterTratsDePlan extends ArrayAdapter<Tratamiento>{

    private Context contexto;
    private List<Tratamiento> tratamientos;
    private boolean existe;


    public ArrayList<checkItem> getIte() {
        return ite;
    }

    private ArrayList<checkItem> ite = new ArrayList<checkItem>();
    private  int cantt = 0;
    private  int monto = 0;


    public AdapterTratsDePlan(Context context, List<Tratamiento> tratamients) {
        super(context, R.layout.list_trats_edit, tratamients);
        contexto=context;
        tratamientos = tratamients;
        ((MainActivity) getContext()).setCantidad_trat_marcado(0);
        ((MainActivity) getContext()).setCosto_total_general(0);
    }

    public static class ViewHolder {
        EditText etCantidad;
        EditText etCosto;
        CheckBox cbMarcado;
        TextView tvTotalIndi,tvCosto,tvCantP;
        int ref;

        boolean checado =false;

        int canti_indi,costo_indi;
        int calculo;
    }

    int cantidad_trat_marcado = 0;


    @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
        contexto = getContext();

//        if (tratamientos.get(position).getId_p_tratamiento() != 0) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(contexto);
            convertView = inflater.inflate(R.layout.list_trats_reg_p, parent, false);
            holder.etCantidad = (EditText) convertView.findViewById(R.id.etCantidadE);
            holder.etCosto = (EditText) convertView.findViewById(R.id.etMontoE);
            holder.cbMarcado = (CheckBox) convertView.findViewById(R.id.cbTratE);
            holder.tvTotalIndi = (TextView) convertView.findViewById(R.id.tvTotalIndi);


            holder.etCantidad.setText(String.valueOf(tratamientos.get(position).getCantidad()));
            holder.etCosto.setText(String.valueOf(tratamientos.get(position).getCosto()));
//            holder.cbMarcado.isChecked();
            convertView.setTag(holder);
//                cant.addTextChangedListener(new MyTextWatcher2(convertView));
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final checkItem ckItem = new checkItem(position);
        holder.ref = position;
        holder.cbMarcado.setText(tratamientos.get(position).getNombre());
        holder.etCantidad.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals("")) {

                    cantt = Integer.valueOf(s.toString());


                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("") && s.toString()!="" && s.toString()!="0") {
                    int cantt = Integer.valueOf(s.toString());
                    holder.canti_indi = cantt;
                    if( holder.costo_indi==0){  holder.costo_indi=1;holder.etCosto.setText("1");}
                    int valor_anterior =  Integer.valueOf(holder.tvTotalIndi.getText().toString());
                    int calculo = holder.costo_indi * holder.canti_indi;

                    if(holder.checado) {
                        ((MainActivity) getContext()).addCosto_total_general(calculo);
                        ((MainActivity) getContext()).rmvCosto_total_general(valor_anterior);
                    }
                    holder.calculo = holder.costo_indi * holder.canti_indi;
                    holder.tvTotalIndi.setText(String.valueOf(calculo));



                    ckItem.setChecado(true);
                    ckItem.setCantidad(cantt);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);

                }else{
                    int cantt = Integer.valueOf(1);
                    holder.canti_indi = cantt;
                    if( holder.costo_indi==0){  holder.costo_indi=1;holder.etCosto.setText("1");}
                    int valor_anterior =  Integer.valueOf(holder.tvTotalIndi.getText().toString());
                    int calculo = holder.costo_indi * holder.canti_indi;

                    if(holder.checado) {
                        ((MainActivity) getContext()).addCosto_total_general(calculo);
                        ((MainActivity) getContext()).rmvCosto_total_general(valor_anterior);
                    }
                    holder.calculo = holder.costo_indi * holder.canti_indi;
                    holder.tvTotalIndi.setText(String.valueOf(calculo));
                    ckItem.setChecado(true);
                    ckItem.setCantidad(cantt);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);
                }
            }
        });

        holder.etCosto.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals("")) {

                    monto = Integer.valueOf(s.toString());

                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("") && s.toString()!="" && s.toString()!="0") {
                    monto = Integer.valueOf(s.toString());
                    holder.costo_indi = monto;


                    if (holder.canti_indi == 0) {
                        holder.canti_indi = 1;
                        holder.etCantidad.setText("1");
                    }
                    int valor_anterior = Integer.valueOf(holder.tvTotalIndi.getText().toString());
                    int calculo = holder.costo_indi * holder.canti_indi;
                    if(holder.checado) {
                        ((MainActivity) getContext()).addCosto_total_general(calculo);
                        ((MainActivity) getContext()).rmvCosto_total_general(valor_anterior);
                    }
                    holder.calculo = holder.costo_indi * holder.canti_indi;
                    holder.tvTotalIndi.setText(String.valueOf(calculo));

                    ckItem.setChecado(true);
                    ckItem.setCosto(monto);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);
                }else{
                    monto = Integer.valueOf(1);
                    holder.costo_indi = monto;
                    if (holder.canti_indi == 0) {
                        holder.canti_indi = 1;
                        holder.etCantidad.setText("1");
                    }
                    int valor_anterior = Integer.valueOf(holder.tvTotalIndi.getText().toString());
                    int calculo = holder.costo_indi * holder.canti_indi;
                    if(holder.checado) {
                        ((MainActivity) getContext()).addCosto_total_general(calculo);
                        ((MainActivity) getContext()).rmvCosto_total_general(valor_anterior);
                    }

                    holder.calculo = holder.costo_indi * holder.canti_indi;
                    holder.tvTotalIndi.setText(String.valueOf(calculo));
                    ckItem.setChecado(true);
                    ckItem.setCosto(monto);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);
                }
            }
        });

        holder.cbMarcado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //position, tratamientos.get(position).getId_tratamiento(), cantt, tratamientos.get(position).getId_p_tratamiento(

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.checado=true;
                    if (holder.etCantidad.getText().toString().equals(" ") ||
                            holder.etCantidad.getText().toString().equals("") ||
                            holder.etCantidad.getText().toString().matches("") ||
                            holder.etCantidad.getText().toString() == "" ||
                            holder.etCantidad.getText().toString() == null ||
                            holder.etCantidad.getText().toString() == " " ||
                            holder.etCantidad.getText().toString() == "0") {

                        holder.etCantidad.setText("1");
                        cantt = Integer.valueOf(holder.etCantidad.getText().toString());

                    } else {
                        cantt = Integer.valueOf(holder.etCantidad.getText().toString());
                    }
                    ckItem.setChecado(true);
                    ckItem.setCantidad(cantt);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);

                    cantidad_trat_marcado++;
                    ((MainActivity) getContext()).setCantidad_trat_marcado(cantidad_trat_marcado);
                    existe = ite.contains(ckItem);
                    Log.v("CHECK","ADD TRAT =>> cantidad_trat_marcado=>"+cantidad_trat_marcado);

                    int calculo = holder.costo_indi * holder.canti_indi;
                    ((MainActivity) getContext()).addCosto_total_general(calculo);

                    if(existe==false) {
                        ite.add(ckItem);

                    }else{
//                        cantidad_trat_marcado--;
//                        Log.v("CHECK","REMOVE TRAT =>> cantidad_trat_marcado=>"+cantidad_trat_marcado);
                        ite.set(position,ckItem);
                    }

//                    Log.v("CHECK","Posi=>>"+ckItem.getPosi()+" Cant=>"+ckItem.getCantidad()+" Nomb=>"+ckItem.getNombreTrat());
                    ((MainActivity) getContext()).setItemRegPlan(getIte());
//                    Log.v("CHECK","ADD TRAT =>> cantidad_trat_marcado=>"+cantidad_trat_marcado);
                } else {
                    holder.checado=false;
                    ckItem.setChecado(false);
                    existe = ite.contains(ckItem);
                    if(existe==false) {
                        ite.add(ckItem);
//                        cantidad_trat_marcado++;
//                        Log.v("CHECK","A®DD TRAT =>> cantidad_trat_marcado=>"+cantidad_trat_marcado);
                    }else{
                        ite.set(position,ckItem);


                    }
                    Log.v("NOT CHECK","Posi=>>"+ckItem.getPosi()+" Cant=>"+ckItem.getCantidad()+" Nomb=>"+ckItem.getNombreTrat());
                    ((MainActivity) getContext()).setItemRegPlan(getIte());
                    cantidad_trat_marcado--;
                    ((MainActivity) getContext()).setCantidad_trat_marcado(cantidad_trat_marcado);
                    Log.v("CHECK","REMOVE TRAT =>> cantidad_trat_marcado=>"+cantidad_trat_marcado);
                    int valor_anterior =  Integer.valueOf(holder.tvTotalIndi.getText().toString());
                    ((MainActivity) getContext()).rmvCosto_total_general(valor_anterior);

                }

            }
        });



        return convertView;


    }






}

