package com.darkcode.ruf_012.Tratamientos;

import java.util.ArrayList;
import java.util.List;



import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;


import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ListRegPlanTratamientos extends ActionBarActivity {

    public class Item {
        Drawable ItemDrawable;
        String ItemString;
        Item( String t){
//            ItemDrawable = drawable;
            ItemString = t;
        }
    }

    static class ViewHolder {
        ImageView icon;
        TextView text;
    }

    public class ItemsListAdapter extends BaseAdapter {

        private Context context;
        private List<Item> list;

        ItemsListAdapter(Context c, List<Item> l){
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
                rowView = inflater.inflate(R.layout.row, null);

                ViewHolder viewHolder = new ViewHolder();
                viewHolder.icon = (ImageView) rowView.findViewById(R.id.rowImageView);
                viewHolder.text = (TextView) rowView.findViewById(R.id.rowTextView);
                rowView.setTag(viewHolder);
            }

            ViewHolder holder = (ViewHolder) rowView.getTag();
            holder.icon.setImageDrawable(getDrawable(R.drawable.add));
            holder.text.setText(list.get(position).ItemString);

            return rowView;
        }

        public List<Item> getList(){
            return list;
        }
    }

    public class ItemsListAdapter2 extends BaseAdapter {

        private Context context;
        private List<Item> list;

        ItemsListAdapter2(Context c, List<Item> l){
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
                rowView = inflater.inflate(R.layout.row, null);

                ViewHolder viewHolder = new ViewHolder();
                viewHolder.icon = (ImageView) rowView.findViewById(R.id.rowImageView);
                viewHolder.text = (TextView) rowView.findViewById(R.id.rowTextView);
                rowView.setTag(viewHolder);
            }

            ViewHolder holder = (ViewHolder) rowView.getTag();
            holder.icon.setImageDrawable(getDrawable(R.drawable.remove));
            holder.text.setText(list.get(position).ItemString);

            return rowView;
        }

        public List<Item> getList(){
            return list;
        }
    }

    List<Item> items1, items2;
    ListView listView1, listView2;
    ItemsListAdapter myItemsListAdapter1;
    ItemsListAdapter2 myItemsListAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_plan_tratamiento);
        listView1 = (ListView)findViewById(R.id.lvTratamientos);
        listView2 = (ListView)findViewById(R.id.lvTPlaneados);

        initItems();
        myItemsListAdapter1 = new ItemsListAdapter(this, items1);
        myItemsListAdapter2 = new ItemsListAdapter2(this, items2);
        listView1.setAdapter(myItemsListAdapter1);
        listView2.setAdapter(myItemsListAdapter2);

        listView1.setOnItemClickListener(listOnItemClickListener);
        listView2.setOnItemClickListener(listOnItemClickListener);

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Item selectedItem = (Item)(parent.getItemAtPosition(position));

                ItemsListAdapter associatedAdapter = (ItemsListAdapter)(parent.getAdapter());
                List<Item> associatedList = associatedAdapter.getList();
                Item associatedItem = associatedList.get(position);
                if(removeItemToList(associatedList, associatedItem)){

                    view.invalidate();
                    associatedAdapter.notifyDataSetChanged();

                    ItemsListAdapter2 list2Adapter = (ItemsListAdapter2)(listView2.getAdapter());
                    List<Item> list2List = list2Adapter.getList();

                    addItemToList(list2List, selectedItem);
                    list2Adapter.notifyDataSetChanged();
                }

                return true;
            }});

    }

    OnItemClickListener listOnItemClickListener = new OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Toast.makeText(ListRegPlanTratamientos.this,
                    ((Item)(parent.getItemAtPosition(position))).ItemString,
                    Toast.LENGTH_SHORT).show();
        }

    };

    private void initItems(){
        items1 = new ArrayList<Item>();
        items2 = new ArrayList<Item>();


        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
        TratamientoService servicio = restadpter.create(TratamientoService.class);

        servicio.getTratamientos(new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
                Toast.makeText(ListRegPlanTratamientos.this,"TRAT "+tratamientos.size(), Toast.LENGTH_LONG).show();
//                Log.i( "TRAT", "I "+tratamientos.size());
                for(int i=1; i<tratamientos.size(); i++){
                    String s = tratamientos.get(i).getNombre()+" ("+tratamientos.get(i).getTipo()+")";
                    Item item = new Item(s);
                    items1.add(item);
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),"ERROR: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


//        for(int i=0; i<4; i++){
//            String s = i+"->ALGO";
//            Item item = new Item(s);
//            items1.add(item);
//        }

    }

    private boolean removeItemToList(List<Item> l, Item it){
        boolean result = l.remove(it);
        return result;
    }

    private boolean addItemToList(List<Item> l, Item it){
        boolean result = l.add(it);
        return result;
    }

}