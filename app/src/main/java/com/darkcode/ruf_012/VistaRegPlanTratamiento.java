package com.darkcode.ruf_012;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.Tratamientos.AdapterTratamientos;
import com.darkcode.ruf_012.Tratamientos.ListRegPlanTratamientos;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;



/**
 * Created by NativoLink on 22/2/17.
 */

public class VistaRegPlanTratamiento extends Fragment {

    ListView lvPlaneadas,lvTratamientos;
    View view;

    public VistaRegPlanTratamiento() {

    }


    public class Item2 {
        Drawable ItemDrawable;
        String ItemString;
        int id_tratamiento;
        Item2( String t,int id_t){
//            ItemDrawable = drawable;
            ItemString = t;
            id_tratamiento = id_t;
        }
    }

    static class ViewHolder2 {
        ImageView icon;
        TextView text;
    }

    public class ItemsListAdapter11 extends BaseAdapter {

        private Context context;
        private List<Tratamiento> list;

        ItemsListAdapter11(Context c, List<Tratamiento> l){
            context = c;
            list = l;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            // reuse views
            if (rowView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_tratamientos, null);

                ViewHolder2 viewHolder = new ViewHolder2();
                viewHolder.icon = (ImageView) rowView.findViewById(R.id.imgBtnAdd);
                viewHolder.text = (TextView) rowView.findViewById(R.id.tvNombreTratamiento);
                rowView.setTag(viewHolder);
            }

            ViewHolder2 holder = (ViewHolder2) rowView.getTag();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.icon.setImageDrawable(getContext().getDrawable(R.drawable.plus));
            }
            holder.text.setText(list.get(position).getNombre());

            return rowView;
        }

        public List<Tratamiento> getList(){
            return list;
        }
    }

    public class ItemsListAdapter22  extends BaseAdapter {

        private Context context;
        private List<Tratamiento> list;

        ItemsListAdapter22(Context c, List<Tratamiento> l){
            context = c;
            list = l;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            // reuse views
            if (rowView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_tratamientos_planeados, null);

                ViewHolder2 viewHolder = new ViewHolder2();
                viewHolder.icon = (ImageView) rowView.findViewById(R.id.imgBtnRemove);
                viewHolder.text = (TextView) rowView.findViewById(R.id.tvNombreTP);
                rowView.setTag(viewHolder);
            }

            ViewHolder2 holder = (ViewHolder2) rowView.getTag();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.icon.setImageDrawable(getContext().getDrawable(R.drawable.remove));
            }
            holder.text.setText(list.get(position).getNombre());

            return rowView;
        }

        public List<Tratamiento> getList(){
            return list;
        }
    }

    List<Tratamiento> items1, items2;
    ListView listView1, listView2;
    ItemsListAdapter11 myItemsListAdapter1;
    ItemsListAdapter22 myItemsListAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reg_plan_tratamiento, container, false);
        listView1 = (ListView)view.findViewById(R.id.lvTratamientos);
        listView2 = (ListView)view.findViewById(R.id.lvTPlaneados);

        initItems();
        myItemsListAdapter1 = new ItemsListAdapter11(getContext(), items1);
        myItemsListAdapter2 = new ItemsListAdapter22(getContext(), items2);
        listView1.setAdapter(myItemsListAdapter1);
        listView2.setAdapter(myItemsListAdapter2);

        listView1.setOnItemClickListener(listOnItemClickListener);
        listView2.setOnItemClickListener(listOnItemClickListener);

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Tratamiento selectedItem = (Tratamiento)(parent.getItemAtPosition(position));

                AdapterTratamientos associatedAdapter = (AdapterTratamientos)(parent.getAdapter());
                List<Tratamiento> associatedList = associatedAdapter.getTratamientos();
                Tratamiento associatedItem = associatedList.get(position);
                if(removeItemToList(associatedList, associatedItem)){

                    view.invalidate();
                    associatedAdapter.notifyDataSetChanged();

                    ListRegPlanTratamientos.ItemsListAdapter2 list2Adapter = (ListRegPlanTratamientos.ItemsListAdapter2)(listView2.getAdapter());
                    List<Tratamiento> list2List = list2Adapter.getList();

                    addItemToList(list2List, selectedItem);
                    list2Adapter.notifyDataSetChanged();
                }

                return true;
            }});
        return view;
    }

    AdapterView.OnItemClickListener listOnItemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Toast.makeText(getContext(),
                    ((Tratamiento)(parent.getItemAtPosition(position))).getNombre(),
                    Toast.LENGTH_SHORT).show();
        }

    };

    private void initItems(){
        items1 = new ArrayList<Tratamiento>();
        items2 = new ArrayList<Tratamiento>();


        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
        TratamientoService servicio = restadpter.create(TratamientoService.class);

        servicio.getTratamientos(new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
//                Toast.makeText(getContext(),"TRAT "+tratamientos.size(), Toast.LENGTH_LONG).show();
                ListView lvresult = (ListView)view.findViewById(R.id.lvTratamientos);
                AdapterTratamientos listAdapter = new AdapterTratamientos(getContext(), tratamientos);
                lvresult.setAdapter(listAdapter);
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(),"ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean removeItemToList(List<Tratamiento> l,Tratamiento it){
        boolean result = l.remove(it);
        return result;
    }

    private boolean addItemToList(List<Tratamiento> l,Tratamiento it){
        boolean result = l.add(it);
        return result;
    }

}


