package com.darkcode.ruf_012.Tratamientos;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.TextView;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NativoLink on 15/7/17.
 */


//ESTA PARTE NO LA VOY A PODER HACER



public class AdapterTratsDePlanEdit  extends ArrayAdapter<Tratamiento> {

    private Context contexto;
    private List<Tratamiento> tratamientos;
    private boolean existe;


    public ArrayList<AdapterTratsConsulta.checkItem> getIte() {
        return ite;
    }

    private ArrayList<AdapterTratsConsulta.checkItem> ite = new ArrayList<AdapterTratsConsulta.checkItem>();
    private  int cantt = 0;
    private  int monto = 0;


    public AdapterTratsDePlanEdit(Context context, List<Tratamiento> tratamients) {
        super(context, R.layout.list_trats_edit, tratamients);
        contexto=context;
        tratamientos = tratamients;
    }

    public static class ViewHolder {
        EditText etCantidad;
        EditText etCosto;
        TextView cbMarcado;
        TextView tvCosto;
        TextView tvCantP;
        TextView tvTotalIndi;
        int ref;
    }



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        contexto = getContext();

//        if (tratamientos.get(position).getId_p_tratamiento() != 0) {
        final AdapterTratsDePlanEdit.ViewHolder holder;
        if (convertView == null) {
            holder = new AdapterTratsDePlanEdit.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(contexto);
            convertView = inflater.inflate(R.layout.list_trats_edit, parent, false);
            holder.etCantidad = (EditText) convertView.findViewById(R.id.etCantidadE);
            holder.etCosto = (EditText) convertView.findViewById(R.id.etMontoE);
            holder.cbMarcado = (TextView) convertView.findViewById(R.id.tvTratE);


            holder.etCantidad.setText(String.valueOf(tratamientos.get(position).getCantidad()));
            holder.etCosto.setText(String.valueOf(tratamientos.get(position).getCosto()));
//            holder.cbMarcado.isChecked();
            convertView.setTag(holder);
//                cant.addTextChangedListener(new MyTextWatcher2(convertView));
        } else {
            holder = (AdapterTratsDePlanEdit.ViewHolder) convertView.getTag();
        }

        final AdapterTratsConsulta.checkItem ckItem = new AdapterTratsConsulta.checkItem(position);
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
                    cantt = Integer.valueOf(s.toString());
                    ckItem.setCantidad(cantt);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);
                }else{
                    cantt = Integer.valueOf(1);
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
                    ckItem.setCosto(monto);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);
                }else{
                    monto = Integer.valueOf(1);
                    ckItem.setCosto(monto);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);
                }
            }
        });





        return convertView;


    }






}

