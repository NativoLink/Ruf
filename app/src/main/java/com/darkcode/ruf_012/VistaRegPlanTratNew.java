package com.darkcode.ruf_012;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import com.darkcode.ruf_012.Pagos.AdapterRegPago;
import com.darkcode.ruf_012.Tratamientos.AdapterTratamientos;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsParaPlan;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 8/6/17.
 */



// - - - - - - - - - - - - - - - | ESTE NO SE VA A USAR |

public class VistaRegPlanTratNew extends Fragment {

    ListView listView1, listView2;

    AdapterTratamientos myItemsListAdapter1;
    AdapterTratsParaPlan myItemsListAdapter2;

    public VistaRegPlanTratNew() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reg_plan_tratamiento, container,false);

        listView1 = (ListView)view.findViewById(R.id.lvTratamientos);
        listView2 = (ListView)view.findViewById(R.id.lvTPlaneados);

        listView2.setBackgroundColor(0xFF00FF00);


        ((MainActivity)getContext()).setMyList1(listView1);
        ((MainActivity)getContext()).setMyList2(listView2);

        initItems();

        myItemsListAdapter2 = new AdapterTratsParaPlan(getContext(),((MainActivity)getContext()).getaTras());

        ((MainActivity)getContext()).setMyAdapter22(myItemsListAdapter2);
        ((MainActivity)getContext()).getMyList22().setAdapter(myItemsListAdapter2);
        ((MainActivity)getContext()).getMyAdapter22().notifyDataSetChanged();

        return view;
    }

    private void initItems(){
        ((MainActivity)getContext()).aTras.clear();

        RestAdapter restadpter = ((MainActivity)getContext()).getRestadpter();
        TratamientoService servicio = restadpter.create(TratamientoService.class);
        //PRUEBA
        servicio.getTratamientos(new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
                myItemsListAdapter1 = new AdapterTratamientos(getContext(), tratamientos);
                ((MainActivity)getContext()).setMyAdapter1(myItemsListAdapter1);
                listView1.setAdapter(myItemsListAdapter1);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



}
