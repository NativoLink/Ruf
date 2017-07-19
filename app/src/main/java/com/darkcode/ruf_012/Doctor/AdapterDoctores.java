package com.darkcode.ruf_012.Doctor;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.list_doctores, parent, false);

        tvIdDoctor= (TextView)convertView.findViewById(R.id.tvIdDoctor);
        tvNombreDoctor= (TextView)convertView.findViewById(R.id.tvNombreDoctor);
        tvEspecialidad= (TextView)convertView.findViewById(R.id.tvEspecialidad);

        ImageButton btnEditar = (ImageButton) convertView.findViewById(R.id.btnEditar);
        ImageButton btnInfo = (ImageButton) convertView.findViewById(R.id.btnInfo);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View v = View.inflate(contexto, R.layout.dialog_info_doctor, null);
                TextView tvNombreD =  (TextView)v.findViewById(R.id.tvNombreD);
                TextView tvUsuarioD =  (TextView)v.findViewById(R.id.tvUsuarioD);

                TextView tvCedula =  (TextView)v.findViewById(R.id.tvCedulaD);
                TextView tvTelefono =  (TextView)v.findViewById(R.id.tvTelefonoD);

                TextView tvDireccionD =  (TextView)v.findViewById(R.id.tvDireccionD);
                TextView tvEspecialidadD =  (TextView)v.findViewById(R.id.tvEspecialidadD);


                tvNombreD.setText( doctores.get(position).getNombre());
                tvUsuarioD.setText( doctores.get(position).getNombre());

//                tvCedula.setText( doctores.get(position).getCedula());
//                tvTelefono.setText( doctores.get(position).getSexo());

                tvDireccionD.setText( doctores.get(position).getDireccion());
                tvEspecialidadD.setText( doctores.get(position).getEspecialidad());

                TextView title =  (TextView)v.findViewById(R.id.tvTitle);
                title.setText("Información del Doctor");

                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });


        int idDoctor = doctores.get(position).getId_doctor();
        String nombreDoctor = doctores.get(position).getNombre();
        String Especialidad = doctores.get(position).getEspecialidad();

        tvIdDoctor.setText(String.valueOf(idDoctor));
        tvNombreDoctor.setText(nombreDoctor);
        tvEspecialidad.setText(Especialidad);

        return convertView;
    }
}
