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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.Pagos.ConsultaPendiente;
import com.darkcode.ruf_012.R;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta.checkItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by NativoLink on 14/3/17.
 */

public class AdapterTratamientos extends ArrayAdapter<Tratamiento>{

    private Context contexto;
    private List<Tratamiento> tratamientos;
    List<Tratamiento> tagsUse;
    private boolean existe;




    public AdapterTratamientos(Context context, List<Tratamiento> tratamients) {
        super(context, R.layout.list_tratamientos, tratamients);
        contexto=context;
        tratamientos = tratamients;
    }

    @Override
    public int getCount() {
        return tratamientos.size();
    }

    @Override
    public Tratamiento getItem(int position) {
        return tratamientos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        contexto = getContext();

             final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(contexto);
                convertView = inflater.inflate(R.layout.list_tratamientos, parent, false);
                holder.tvNombreTratamiento = (TextView) convertView.findViewById(R.id.tvNombreTratamiento);
                holder.imgBtnAdd = (ImageButton)convertView.findViewById(R.id.imgBtnAdd);

                holder.tvNombreTratamiento.setText( tratamientos.get(position).getNombre());
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

        holder.ref = position;
        holder.imgBtnAdd.setTag(position);
        holder.imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                    int tag = (Integer)v.getTag();
                Tratamiento tag = tratamientos.get(position);

                tagsUse = ((MainActivity) getContext()).getaTras();
                if (estaEnArray(tag, tagsUse)) {
                    Toast.makeText(getContext(), "Ya se encuentra añadido =>"+tag, Toast.LENGTH_LONG).show();
                } else {
                    ((MainActivity) getContext()).AddTras(tratamientos.get(position)); // AGREGA UN NUEVO ELEMENTO A List<?>
                    ((MainActivity) getContext()).getMyAdapter22().notifyDataSetChanged(); // ACTUALIZA EL ADAPTER SEGUN SU List<?>
                    Toast.makeText(getContext(), "Add", Toast.LENGTH_LONG).show();
                }
            }
        });

        final checkItem ckItem = new checkItem(position);








        return convertView;


    }

    public boolean estaEnArray(Tratamiento cp, List<Tratamiento> permitidos){
        boolean existe = false;
        for (Iterator<Tratamiento> i = permitidos.iterator(); i.hasNext();) {
//               Log.v("CP","ITEM => "+item);
            Tratamiento item = i.next();
            if(item.id_p_tratamiento == cp.id_p_tratamiento){
                Log.v("CP","EXITE CP => "+cp.id_p_tratamiento + " COMO ITEM => "+item.id_p_tratamiento);
                existe = true;
            }
        }
        return existe;

    }


    private class ViewHolder {
        TextView tvNombreTratamiento;
        ImageButton imgBtnAdd;
        int ref;
    }






}

