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
                holder.tvNombreTratamiento = (TextView) convertView.findViewById(R.id.tvNombre);
                holder.tvTipo = (TextView)convertView.findViewById(R.id.tvTipo);

                holder.tvNombreTratamiento.setText(tratamientos.get(position).getNombre());
                holder.tvTipo.setText(tratamientos.get(position).getTipo());
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }






        return convertView;


    }



    private class ViewHolder {
        TextView tvNombreTratamiento;
        TextView tvTipo;
    }






}

