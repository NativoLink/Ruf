package com.darkcode.ruf_012.Paciente;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by NativoLink on 18/2/17.
 */

public class AdapterPacientes extends ArrayAdapter<Paciente> {

    private Context contexto;
    private List<Paciente> pacientes;
    public AdapterPacientes(Context context, List<Paciente> pacients) {
        super(context, R.layout.list_pacientes, pacients);
        contexto=context;
        pacientes = pacients;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_pacientes, parent, false);

        TextView nombrePaciente = (TextView) customView.findViewById(R.id.tvNombrePaciente);
        TextView idPaciente = (TextView) customView.findViewById(R.id.tvIdPaciente);
        TextView edadPaciente = (TextView) customView.findViewById(R.id.tvEdadPaciente);
        TextView telPaciente = (TextView) customView.findViewById(R.id.tvTelefonoPaciente);

        int id = pacientes.get(position).getId_paciente();
        String idP = Integer.toString(id);
        idPaciente.setText(idP);

        nombrePaciente.setText(pacientes.get(position).getNombre());

        int edad = pacientes.get(position).getEdad();
        String edadP = Integer.toString(edad);
        edadPaciente.setText(edadP);

        telPaciente.setText(pacientes.get(position).getTelefono());

        return customView;

    }

}
