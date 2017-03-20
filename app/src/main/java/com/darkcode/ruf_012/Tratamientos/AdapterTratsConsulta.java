package com.darkcode.ruf_012.Tratamientos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by NativoLink on 14/3/17.
 */

public class AdapterTratsConsulta extends ArrayAdapter<Tratamiento>{

    private Context contexto;
    private List<Tratamiento> tratamientos;
    private ArrayList<checkItem> ite = new ArrayList<checkItem>();

    public AdapterTratsConsulta(Context context, List<Tratamiento> tratamients) {
        super(context, R.layout.list_trats_p_consulta, tratamients);
        contexto=context;
        tratamientos = tratamients;
    }



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        contexto = getContext() ;
        LayoutInflater inflater = LayoutInflater.from(contexto);
        final View customView = inflater.inflate(R.layout.list_trats_p_consulta, parent, false);



        TextView costo = (TextView) customView.findViewById(R.id.tvCosto);
        TextView estado = (TextView) customView.findViewById(R.id.tvEstado);
        final CheckBox nombreT = (CheckBox) customView.findViewById(R.id.cbNombreTrat);
        nombreT.setText(tratamientos.get(position).getNombre());
        costo.setText(tratamientos.get(position).getCosto());

        nombreT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ite.add(new checkItem(position,tratamientos.get(position).getId_tratamiento()));
                }else{
                    ite.remove(position);
                }
//                for(int i=0; i< ite.size(); i++) {
                    ((MainActivity)getContext()).setIte(ite);
//                }
            }
        });

//        nombreT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"2 => Checked: "+nombreT.getText()+" "+nombreT.getTag(), Toast.LENGTH_SHORT).show();
//            }
//        });



        return customView;

    }


    public class checkItem{
        int id_tratamiento;
        String nombreTrat;
        String estado;
        int cantidad;

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

        public checkItem(int posicion, int id_p_tratamiento){
            setId_tratamiento(id_p_tratamiento);
        }
    }



}

