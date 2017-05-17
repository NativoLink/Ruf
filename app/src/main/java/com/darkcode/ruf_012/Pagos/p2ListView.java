package com.darkcode.ruf_012.Pagos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by NativoLink on 14/5/17.
 */

public class p2ListView extends Fragment {

    ListView listView1, listView2;
    List<ConsultaPendiente> items1, items2;

    AdapterConPendientes myItemsListAdapter1;
    AdapterConPendientes myItemsListAdapter2;
//    AdapterRegPago myItemsListAdapter2;

    public p2ListView() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p2_list_view, container,false);

        listView1 = (ListView)view.findViewById(R.id.lvConPendientes);
        listView2 = (ListView)view.findViewById(R.id.lvConAPagar);


        ((MainActivity)getContext()).setMyList1(listView1);
        ((MainActivity)getContext()).setMyList2(listView2);

        initItems();
//        ((MainActivity)getContext()).setMyAdapter(myItemsListAdapter1);
//        ((MainActivity)getContext()).setMyAdapter2(myItemsListAdapter2);
        myItemsListAdapter1 = new AdapterConPendientes(getContext(), items1);
        myItemsListAdapter2 = new AdapterConPendientes(getContext(),((MainActivity)getContext()).getaPago());






//        ((MainActivity)getContext()).setMyAdapter2( ((MainActivity)getContext()));

        return view;
    }

    private void initItems(){
//        items1 = new ArrayList<ConsultaPendiente>();
//        items2 = new ArrayList<ConsultaPendiente>();


        RestAdapter restadpter = ((MainActivity)getContext()).getRestadpter();
        PagoService servicio = restadpter.create(PagoService.class);
        //PRUEBA
        servicio.getPagos(2, new Callback<List<ConsultaPendiente>>() {
            @Override
            public void success(List<ConsultaPendiente> pagos, Response response) {
                myItemsListAdapter1 = new AdapterConPendientes(getContext(), pagos);
                ((MainActivity)getContext()).setMyAdapter(myItemsListAdapter1);
                listView1.setAdapter(myItemsListAdapter1);
//                ((MainActivity)getContext()).setMyAdapter2(myItemsListAdapter2);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean removeItemToList(List<ConsultaPendiente> l, ConsultaPendiente it){
        boolean result = l.remove(it);
        return result;
    }

    private boolean addItemToList(List<ConsultaPendiente> l, ConsultaPendiente it){
        boolean result = l.add(it);
        return result;
    }


}


