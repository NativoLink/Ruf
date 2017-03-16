package com.darkcode.ruf_012.Paciente;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.R;

import java.util.List;

/**
 * Created by NativoLink on 8/3/17.
 */

public class AdapterConsultas extends ArrayAdapter<Consulta> {

    private Context contexto;
    private List<Consulta> consults;

    public AdapterConsultas(Context context, List<Consulta> consultas) {
        super(context, R.layout.list_planes,consultas);
        contexto=context;
        consults = consultas;



    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_planes, parent, false);

        TextView idConsulta = (TextView) customView.findViewById(R.id.tvPlan);
        idConsulta.setText(consults.get(position).getEstado());

        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String estado = consults.get(position).getEstado();
                Toast.makeText(getContext(), estado+"<<", Toast.LENGTH_SHORT).show();

            }
        });

        return customView;
    }
}
