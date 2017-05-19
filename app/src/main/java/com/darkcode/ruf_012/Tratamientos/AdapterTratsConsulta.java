package com.darkcode.ruf_012.Tratamientos;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import com.darkcode.ruf_012.Tratamientos.AdapterTratsDePlan.ViewHolder;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by NativoLink on 14/3/17.
 */

public class AdapterTratsConsulta extends ArrayAdapter<Tratamiento>{

    private Context contexto;
    private List<Tratamiento> tratamientos;
    private boolean existe;

    private ArrayList<checkItem> ite = new ArrayList<checkItem>();
    private  int cantt = 0;
    private  int costoTotal = 0;

    public ArrayList<checkItem> getIte() {
        return ite;
    }


    public AdapterTratsConsulta(Context context, List<Tratamiento> tratamients) {
        super(context, R.layout.list_trats_p_consulta, tratamients);
        contexto=context;
        tratamientos = tratamients;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        contexto = getContext();
        final ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(contexto);
                convertView = inflater.inflate(R.layout.list_trats_p_consulta, parent, false);
                holder.etCantidad = (EditText) convertView.findViewById(R.id.etCantidad);
                holder.tvCosto = (TextView) convertView.findViewById(R.id.tvCosto);
                holder.tvTotalIndi = (TextView) convertView.findViewById(R.id.tvTotalIndi);
                holder.cbMarcado = (CheckBox) convertView.findViewById(R.id.cbNombreTrat);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

        final checkItem ckItem = new checkItem(position);
        holder.ref = position;
        holder.cbMarcado.setText(tratamientos.get(position).getNombre());
        holder.tvCosto.setText(String.valueOf(tratamientos.get(position).getCosto()));
        holder.etCantidad.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(!s.toString().equals("") && s.toString()!="" && s.toString()!="0") {
                    cantt = Integer.valueOf(s.toString());
                    ckItem.setCantidad(cantt);


                    int ti = cantt * Integer.valueOf(holder.tvCosto.getText().toString());
                    if(ckItem.isChecado()) {
                        if( ckItem.isChecadoAnteriormente()) {
                            Log.v("TOTAL","TOTAL MENOS onTextChanged=> "+ckItem.getTotalIdi());
                            Log.v("TOTAL","TOTAL MAS  => "+ti);
                            costoTotal = costoTotal - ckItem.getTotalIdi();
                            Log.v("TOTAL","TOTAL G => "+costoTotal+" + "+ti);
                            costoTotal = costoTotal + ti;
                            ckItem.setTotalIdi(ti);
                            holder.tvTotalIndi.setText(String.valueOf(ti));
                        }else{
                            costoTotal = costoTotal - ckItem.getTotalIdi();
                            costoTotal = costoTotal + ti;
                            ckItem.setTotalIdi(ti);
                            holder.tvTotalIndi.setText(String.valueOf(ti));
                        }
                    }else {
                        Log.v("NOTING CHECK 1 and 2","CHECK ="+ckItem.isChecadoAnteriormente());
//                        costoTotal = costoTotal - ckItem.getTotalIdi();

                    }
                    ((MainActivity) getContext()).setTotalRegConsulta(costoTotal);

//                    holder.etCantidad.setText(s.toString());
                    ckItem.setCantidad(cantt);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
//                    ckItem.setPosi(position);
                }else{

                    ckItem.setTotalIdi(cantt * Integer.valueOf(holder.tvCosto.getText().toString()));
                    ckItem.setChecado(true);
                    holder.etCantidad.setText(String.valueOf(cantt));
                    ckItem.setCantidad(cantt);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {
//                if(!s.toString().equals("") && s.toString()!="" && s.toString()!="0") {
//                    cantt = Integer.valueOf(s.toString());
//                    ckItem.setCantidad(cantt);
//
//                    if(ckItem.isChecado()) {
//                        if( ckItem.isChecadoAnteriormente()) {
//                            int ti = cantt * Integer.valueOf(holder.tvCosto.getText().toString());
//                            Log.v("TOTAL","TOTAL MENOS afterTextChanged=> "+ckItem.getTotalIdi());
//                            costoTotal = costoTotal - ckItem.getTotalIdi();
//                            Log.v("TOTAL","TOTAL MAS => "+ti);
//                            Log.v("TOTAL","TOTAL G => "+costoTotal+" + "+ti);
//                            costoTotal = costoTotal + ti;
//                            ckItem.setTotalIdi(cantt * Integer.valueOf(holder.tvCosto.getText().toString()));
//                            holder.tvTotalIndi.setText(String.valueOf(ti));
//                        }else{
//                            int ti = cantt * Integer.valueOf(holder.tvCosto.getText().toString());
//                            costoTotal = costoTotal - ckItem.getTotalIdi();
//                            costoTotal = costoTotal + ti;
//                            ckItem.setTotalIdi(ti);
//                            holder.tvTotalIndi.setText(String.valueOf(ti));
//                        }
//                    }else {
//                        Log.v("NOTING CHECK 1 and 2","CHECK ="+ckItem.isChecadoAnteriormente());
////                        costoTotal = costoTotal - ckItem.getTotalIdi();
//                    }
//                    ((MainActivity) getContext()).setTotalRegConsulta(costoTotal);
//
//                    ckItem.setCantidad(cantt);
//                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
//                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
//                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
//                }else{
//                    if( ckItem.isChecadoAnteriormente()) {
//                        int ti = cantt * Integer.valueOf(holder.tvCosto.getText().toString());
//                        costoTotal = costoTotal + ti;
//                        costoTotal = costoTotal - ckItem.getTotalIdi();
//                        ckItem.setTotalIdi(ti);
//                        holder.tvTotalIndi.setText(String.valueOf(ti));
//                    }
//                    cantt = Integer.valueOf(1);
//                    ckItem.setChecado(true);
//                    ckItem.setCantidad(cantt);
//                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
//                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
//                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
//                    ckItem.setPosi(position);
//                }
            }
        });



        holder.cbMarcado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //position, tratamientos.get(position).getId_tratamiento(), cantt, tratamientos.get(position).getId_p_tratamiento(

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(ckItem.isChecadoAnteriormente()==false){ckItem.setChecadoAnteriormente(true);}else{costoTotal = costoTotal - ckItem.getTotalIdi();}

                    if (holder.etCantidad.getText().toString().equals(" ") ||
//                        holder.etCantidad.getText().toString().equals("") ||
//                        holder.etCantidad.getText().toString().matches("") ||
//                        holder.etCantidad.getText().toString() == "" ||
//                        holder.etCantidad.getText().toString() == null ||
                        holder.etCantidad.getText().toString() == " " ||
                        holder.etCantidad.getText().toString() == "0") {


                        cantt = Integer.valueOf(holder.etCantidad.getText().toString());
//                        holder.etCantidad.setText(""+cantt);

                    } else {
                        holder.etCantidad.setText("1");
//                        cantt = Integer.valueOf(holder.etCantidad.getText().toString());
//                        holder.etCantidad.setText(""+cantt);
                    }
                    int ti = cantt * Integer.valueOf(holder.tvCosto.getText().toString());
                    costoTotal = costoTotal + ti;
                    holder.tvTotalIndi.setText(String.valueOf(ti));
                    ((MainActivity) getContext()).setTotalRegConsulta(costoTotal);


                    ckItem.setChecado(true);

                    ckItem.setCantidad(cantt);
                    ckItem.setId_p_tratamiento(tratamientos.get(position).getId_p_tratamiento());
                    ckItem.setId_tratamiento(tratamientos.get(position).getId_tratamiento());
                    ckItem.setNombreTrat(tratamientos.get(position).getNombre());
                    ckItem.setPosi(position);

                    existe = ite.contains(ckItem);
                    if(existe==false) {
                        ite.add(ckItem);
                    }else{
                        ite.set(position,ckItem);
                    }
                    holder.tvTotalIndi.setText(String.valueOf(ti));
//                    holder.tvTotalIndi.setText(String.valueOf(cantt * Integer.valueOf(holder.tvCosto.getText().toString())));


//                    Log.v("CHECK","Posi=>>"+ckItem.getPosi()+" Cant=>"+ckItem.getCantidad()+" Nomb=>"+ckItem.getNombreTrat());
                    ((MainActivity) getContext()).setIte(getIte());
                } else {
                    Toast.makeText(getContext(), "NO CHECADO", Toast.LENGTH_LONG).show();
                    ckItem.setChecado(false);
                    if (holder.etCantidad.getText().toString().equals(" ") ||
                            holder.etCantidad.getText().toString().equals("") ||
                            holder.etCantidad.getText().toString().matches("") ||
                            holder.etCantidad.getText().toString() == "" ||
                            holder.etCantidad.getText().toString() == null ||
                            holder.etCantidad.getText().toString() == " " ||
                            holder.etCantidad.getText().toString() == "0") {

//                        holder.etCantidad.setText(""+1);
                        cantt = Integer.valueOf(holder.etCantidad.getText().toString());
                        int ti = cantt * Integer.valueOf(holder.tvCosto.getText().toString());
                        costoTotal = costoTotal - ti;
                        holder.tvTotalIndi.setText(String.valueOf(0));

                    } else {
                        cantt = Integer.valueOf(holder.etCantidad.getText().toString());
//                        holder.etCantidad.setText(""+cantt);
                        int ti = cantt * Integer.valueOf(holder.tvCosto.getText().toString());
                        costoTotal = costoTotal - ti;
                        holder.tvTotalIndi.setText(String.valueOf(0));

                    }

                    ((MainActivity) getContext()).setTotalRegConsulta(costoTotal);

                    ckItem.setChecado(false);
                    existe = ite.contains(ckItem);
                    if(existe==false) {
                        ite.add(ckItem);
                    }else{
                        int pos = ite.indexOf(ckItem);
                        ite.set(pos,ckItem);
                    }
//                    Log.v("NOT CHECK","Posi=>>"+ckItem.getPosi()+" Cant=>"+ckItem.getCantidad()+" Nomb=>"+ckItem.getNombreTrat());
                    ((MainActivity) getContext()).setIte(getIte());
                }

            }
        });


//        }


        return convertView;


    }


    public static final class checkItem{
        int costo;
        int posi;
        int id_tratamiento;
        int id_p_tratamiento;
        String nombreTrat;
        String estado;
        int cantidad;
        boolean checado;
        boolean checadoAnteriormente =false;
        int totalIdi;

        public int getTotalIdi() {
            return totalIdi;
        }
        public void setTotalIdi(int totalIdi) {
            this.totalIdi = totalIdi;
        }
        public boolean isChecadoAnteriormente() {
            return checadoAnteriormente;
        }
        public void setChecadoAnteriormente(boolean checadoAnteriormente) {
            this.checadoAnteriormente = checadoAnteriormente;
        }
        public int getCosto() {
            return costo;
        }
        public void setCosto(int monto) {
            this.costo = monto;
        }
        public int getPosi() {
            return posi;
        }
        public void setPosi(int posi) {
            this.posi = posi;
        }
        public boolean isChecado() {
            return checado;
        }
        public void setChecado(boolean checado) {
            this.checado = checado;
        }
        public int getId_p_tratamiento() {
            return id_p_tratamiento;
        }
        public void setId_p_tratamiento(int id_p_tratamiento) {
            this.id_p_tratamiento = id_p_tratamiento;
        }
        public int getCantidad() {
            return cantidad;
        }
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
        public String getEstado() {
            return estado;
        }
        public void setEstado(String estado) {
            this.estado = estado;
        }
        public int getId_tratamiento() {
            return id_tratamiento;
        }
        public void setId_tratamiento(int id_tratamiento) {
            this.id_tratamiento = id_tratamiento;
        }
        public String getNombreTrat() {
            return nombreTrat;
        }
        public void setNombreTrat(String nombreTrat) {
            this.nombreTrat = nombreTrat;
        }
        public checkItem(int posicion){
            setPosi(posicion);
        }
        public checkItem(int posicion, int id_tratamiento,int cantidad,int id_p_tratamiento){
            setId_tratamiento(id_tratamiento);
            setCantidad(cantidad);
            setId_p_tratamiento(id_p_tratamiento);
            setPosi(posicion);
        }
    }


}

