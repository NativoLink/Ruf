package com.darkcode.ruf_012.Doctor;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by NativoLink on 9/7/17.
 */

public class AdapterDoctores extends ArrayAdapter<Doctor> {

    private Context contexto;
    private List<Doctor> doctores;
    TextView tvIdDoctor,tvNombreDoctor,tvEspecialidad;

    public AdapterDoctores(Context context, List<Doctor> doctors) {
        super(context, R.layout.list_doctores, doctors);
        contexto=context;
        doctores = doctors;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.list_doctores, parent, false);

        tvIdDoctor= (TextView)convertView.findViewById(R.id.tvIdDoctor);
        tvNombreDoctor= (TextView)convertView.findViewById(R.id.tvNombreDoctor);
        tvEspecialidad= (TextView)convertView.findViewById(R.id.tvEspecialidad);


        int idDoctor = doctores.get(position).getId_doctor();
        String nombreDoctor = doctores.get(position).getNombre();
        String Especialidad = doctores.get(position).getEspecialidad();

        tvIdDoctor.setText(String.valueOf(idDoctor));
        tvNombreDoctor.setText(nombreDoctor);
        tvEspecialidad.setText(Especialidad);

        return convertView;
    }
}
