package com.darkcode.ruf_012.Tratamientos;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.Paciente.Paciente;
import com.darkcode.ruf_012.R;
import com.darkcode.ruf_012.VistaRegPlanTratamiento;

import java.util.List;

/**
 * Created by NativoLink on 18/2/17.
 */

public class AdapterTratamientos extends ArrayAdapter<Tratamiento> {

    private Context contexto;
    private List<Tratamiento> tratamientos;
    ListView lvPlaneadas;

    public View getVist() {
        return vist;
    }

    public void setVist(View vist) {
        this.vist = vist;
    }

    public View vist;


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
        contexto = getContext() ;
        LayoutInflater inflater = LayoutInflater.from(contexto);
        final View customView = inflater.inflate(R.layout.list_tratamientos, parent, false);
//        vist = customView;
//        vist.invalidate();
//        customView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lvPlaneadas = (ListView)customView.findViewById(R.id.lvTPlaneados);
//                Toast.makeText(getContext(),"ADAPTER ITEM", Toast.LENGTH_LONG).show();
//
//                Tratamiento selectedItem = (Tratamiento)(tratamientos.get(position));
//
//                        AdapterTratamientos associatedAdapter = (AdapterTratamientos)(tratamientos.getAdapter());
//                        List<Tratamiento> associatedList = associatedAdapter.getTratamientos();
//
//                        Tratamiento associatedItem = associatedList.get(position);
//                        if(removeItemToList(associatedList, associatedItem)){
//
//                            vist.invalidate();
//                            associatedAdapter.notifyDataSetChanged();
//
//                            AdapterTratamientos list2Adapter = (AdapterTratamientos)(lvPlaneadas.getAdapter());
//                            List<Tratamiento> list2List = list2Adapter.getTratamientos();
//
//                            addItemToList(list2List, selectedItem);
//                            list2Adapter.notifyDataSetChanged();
//                        }
//            }
//        });

        TextView nombreT = (TextView) customView.findViewById(R.id.tvNombreTratamiento);

        nombreT.setText(tratamientos.get(position).getNombre());

        return customView;

    }

    private boolean removeItemToList(List<Tratamiento> l, Tratamiento it){
        boolean result = l.remove(it);
        return result;
    }

    private boolean addItemToList(List<Tratamiento> l, Tratamiento it){
        boolean result = l.add(it);
        return result;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

}
