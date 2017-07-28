package com.darkcode.ruf_012;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class DeviceList extends ActionBarActivity
{
    //Declaramos Los Componentes
    ImageButton btnVinculados,btnSinBT;
    ListView listaDispositivos;
    //Bluetooth
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> dispVinculados;
    public static String EXTRA_ADDRESS = "device_address";

    String id_doctor;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        //=====================| RECIBIMOS VARIABLES DE SESSION | ===================
        id_doctor = getIntent().getStringExtra("id_doctor");
        nombre = getIntent().getStringExtra("nombre");

        //Declaramos nuestros componenetes ralcionandolos con los del layout
        btnVinculados = (ImageButton)findViewById(R.id.button);
        btnSinBT = (ImageButton)findViewById(R.id.btnSinBT);
        listaDispositivos = (ListView)findViewById(R.id.listView);




        //Comprobamos que el dispositivo tiene bluetooth
        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if(myBluetooth == null)
        {
            //Mostramos un mensaje, indicando al usuario que no tiene conexión bluetooth disponible
            Toast.makeText(getApplicationContext(), "Bluetooth no disponible", Toast.LENGTH_LONG).show();

            //finalizamos la aplicación
//            finish();
        }

        else if(!myBluetooth.isEnabled())
        {
                //Preguntamos al usuario si desea encender el bluetooth
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                turnBTon.putExtra("id_doctor",id_doctor);
                turnBTon.putExtra("nombre",nombre);
                startActivityForResult(turnBTon,1);
        }
        btnSinBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DeviceList.this, MainActivity.class);

//            Change the activity.
                i.putExtra(EXTRA_ADDRESS, "sinBT"); //this will be received at ledControl (class) Activity
                i.putExtra("id_doctor",id_doctor);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        btnVinculados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                listaDispositivosvinculados();
            }
        });

    }

    private void listaDispositivosvinculados()
    {
        dispVinculados = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (dispVinculados.size()>0)
        {
            for(BluetoothDevice bt : dispVinculados)
            {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Obtenemos los nombres y direcciones MAC de los disp. vinculados
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No se han encontrado dispositivos vinculados", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        listaDispositivos.setAdapter(adapter);
        listaDispositivos.setOnItemClickListener(myListClickListener);

    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            // Make an intent to start next activity.
            Intent i = new Intent(DeviceList.this, MainActivity.class);

//            Change the activity.
            i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
            i.putExtra("id_doctor",id_doctor);
            i.putExtra("nombre",nombre);
            startActivity(i);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
