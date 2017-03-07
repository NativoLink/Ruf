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
        private List<Item2> list;

        ItemsListAdapter11(Context c, List<Item2> l){
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
            holder.text.setText(list.get(position).ItemString);

            return rowView;
        }

        public List<Item2> getList(){
            return list;
        }
    }

    public class ItemsListAdapter22  extends BaseAdapter {

        private Context context;
        private List<Item2> list;

        ItemsListAdapter22(Context c, List<Item2> l){
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
            holder.text.setText(list.get(position).ItemString);

            return rowView;
        }

        public List<Item2> getList(){
            return list;
        }
    }

    List<Item2> items1, items2;
    ListView listView1, listView2;
    ItemsListAdapter11 myItemsListAdapter1;
    ItemsListAdapter22 myItemsListAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reg_plan_tratamiento, container, false);
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
                Item2 selectedItem = (Item2)(parent.getItemAtPosition(position));

                ItemsListAdapter11 associatedAdapter = (ItemsListAdapter11)(parent.getAdapter());
                List<Item2> associatedList = associatedAdapter.getList();
                Item2 associatedItem = associatedList.get(position);
                if(removeItemToList(associatedList, associatedItem)){

                    view.invalidate();
                    associatedAdapter.notifyDataSetChanged();

                    ItemsListAdapter22 list2Adapter = (ItemsListAdapter22)(listView2.getAdapter());
                    List<Item2> list2List = list2Adapter.getList();

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
                    ((Item2)(parent.getItemAtPosition(position))).ItemString,
                    Toast.LENGTH_SHORT).show();
        }

    };

    private void initItems(){
        items1 = new ArrayList<Item2>();
        items2 = new ArrayList<Item2>();


        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
        TratamientoService servicio = restadpter.create(TratamientoService.class);

        servicio.getTratamientos(new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
//                Toast.makeText(ListRegPlanTratamientos.this,"TRAT "+tratamientos.size(), Toast.LENGTH_LONG).show();
//                Log.i( "TRAT", "I "+tratamientos.size());
                for(int i=0; i<tratamientos.size(); i++){
                    String s = tratamientos.get(i).getNombre()+" ("+tratamientos.get(i).getTipo()+")";
                    int idT =  tratamientos.get(i).getId_tratamiento();
                    Item2 item = new Item2(s,idT);
                    items1.add(item);
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(),"ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean removeItemToList(List<Item2> l,Item2 it){
        boolean result = l.remove(it);
        return result;
    }

    private boolean addItemToList(List<Item2> l,Item2 it){
        boolean result = l.add(it);
        return result;
    }

}


