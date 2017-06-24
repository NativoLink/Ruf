package com.darkcode.ruf_012.Diagrama;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;
import com.darkcode.ruf_012.VistaHistDiagrama;

import java.util.List;

/**
 * Created by NativoLink on 18/6/17.
 */



public class AdapterDiagrama  extends ArrayAdapter<Diagrama> {

    List<Diagrama> diagramas;
    Fragment vista;

    public AdapterDiagrama( Context context, List<Diagrama> diagramas) {
        super(context, R.layout.list_diagrama, diagramas);
        this.diagramas= diagramas;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_diagrama, parent, false);
        TextView fecha = (TextView)view.findViewById(R.id.tvFechaDiagrma);
        ImageButton detalle = (ImageButton)view.findViewById(R.id.btnDetalle);

       if(diagramas.get(position).getId_consulta()==0){
           detalle.setVisibility(View.INVISIBLE);
       }

        fecha.setText("#Consulta("+diagramas.get(position).getId_consulta()+")   -   "+ diagramas.get(position).getFecha_reg());
        detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_paciente = ((MainActivity) getContext()).getId_pacienteA();
//                vista = new VistaGetDiagrama(id_paciente, diagramas.get(position).getId_consulta());
                vista = new VistaHistDiagrama(id_paciente, diagramas.get(position).getId_consulta());
                String Titulo_Bar = "Odontodiagrama " + id_paciente;
                ((MainActivity) getContext()).setVistaActual(Titulo_Bar);
                ((MainActivity) getContext()).cambioVista(vista, Titulo_Bar);
            }
        });
        return view;
    }
}
