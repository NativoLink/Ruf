package com.darkcode.ruf_012;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.Diagrama.DienteService;
import com.darkcode.ruf_012.Diagrama.VistaGetDiagrama;
import com.darkcode.ruf_012.Diagrama.VistaRegDiagrama;
import com.darkcode.ruf_012.Paciente.PacienteService;
import com.darkcode.ruf_012.Paciente.VistaRegPaciente;
import com.darkcode.ruf_012.Pagos.AdapterConPendientes;
import com.darkcode.ruf_012.Pagos.AdapterRegPago;
import com.darkcode.ruf_012.Pagos.ConsultaPendiente;
import com.darkcode.ruf_012.Pagos.p2ListView;
import com.darkcode.ruf_012.Tratamientos.AdapterTratamientos;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsParaPlan;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Runnable,Communicator{

    TextView name;
    int temp =0;
    int actual =0;
    int cont = 0;
    Thread t,t1;
    int  escucha;
    Object vistaA;
    int TotalRegConsulta = 0;

//    - - - - - - - - - - - - - - - - - - - -
//    |   * * *  LISTADO DE VISTAS  * * *   |
//    - - - - - - - - - - - - - - - - - - - -
    String v_reg_consulta = "Nueva Consulta";
    String v_list_pacientes = "Listado de Pacientes";
    String v_consultas_pagos = "Consultas y Pagos";
    String v_examen_clinico = "Examen Clinico";

    public String getV_examen_clinico() {
        return v_examen_clinico;
    }
    public String getV_consultas_pagos() {
        return v_consultas_pagos;
    }
    public String getV_reg_consulta() {
        return v_reg_consulta;
    }
    public String getV_list_pacientes() {
        return v_list_pacientes;
    }

//      ===============================================
//      |       VARIABLES PARA MANEJAR LISTAS         |
//      ===============================================
    public AdapterConPendientes myAdapter;
    public AdapterRegPago myAdapter2;
    public List<ConsultaPendiente> aPago = new ArrayList<ConsultaPendiente>();

    public ListView myList1;
    public ListView myList2;


    public ListView getMyList2() {
        return myList2;
    }
    public AdapterRegPago getMyAdapter2() {
        return myAdapter2;
    }

    public void setMyAdapter(AdapterConPendientes myAdapter) {
        this.myAdapter = myAdapter;
    }
    public void setMyAdapter2(AdapterRegPago myAdapter2) {
        this.myAdapter2 = myAdapter2;
    }


    public void setMyList1(ListView myList1) {
        this.myList1 = myList1;
    }
    public void setMyList2(ListView myList2) {
        this.myList2 = myList2;
    }



    public void AddPago(ConsultaPendiente Pago) {
        aPago.add(Pago);
    }
    public void RemovePago(ConsultaPendiente Pago) {
        aPago.remove(Pago);
    }
    public List<ConsultaPendiente> getaPago() {
        return aPago;
    }




//      ===============================================
//      |           VARIABLES PARA PAGOS              |
//      ===============================================

    int monto_a_pagar;

    public int  getMonto_a_pagar() {
        return monto_a_pagar;
    }
    public void setMonto_a_pagar(int monto_a_pagar) {
        this.monto_a_pagar = monto_a_pagar;
    }
    public void MotoPagarSuma(int montoRetornado){
        monto_a_pagar = monto_a_pagar + montoRetornado;
    }
    public void MotoPagarResta(int montoRetornado){
        monto_a_pagar = monto_a_pagar - montoRetornado;
    }


    int tvdetotal;
    int tvdesaldada;

    public int getTvdetotal() {
        return tvdetotal;
    }
    public void setTvdetotal(int tvdetotal) {
        this.tvdetotal = tvdetotal;
    }
    public int getTvdesaldada() {
        return tvdesaldada;
    }
    public void setTvdesaldada(int tvdesaldada) {
        this.tvdesaldada = tvdesaldada;
    }


    //    - - - TOTAL EN DEUDA - - - [ LADO IZQUIERDO ]
    public void UpddateDeuda(){
        TextView deuda_total = (TextView)findViewById(R.id.tvdetotal);
        deuda_total.setText(String.valueOf(getTvdetotal()));

        TextView saldado_total = (TextView)findViewById(R.id.tvdesaldada);
        saldado_total.setText(String.valueOf(getTvdesaldada()));
    }
    //    - - - TOTAL A PAGAR - - -  [ LADO DERECHO ]
    int total;
    public int getTotal() {
        return total;
    }
    public void TotalSuma(int total) {
        this.total = this.total+total;
    }
    public void TotalResta(int total) {
        this.total = this.total- total;
    }

//  - - - ACTAULIZAR tv TOTAL - - -
    public void UpdateTotal(){
        TextView pagara_total = (TextView)findViewById(R.id.tvtotal);
        pagara_total.setText(String.valueOf(getTotal()));
    }

//      ===============================================
//      | VARIABLES PARA REG-TRATAMEINTOS EN NEW PLAN |
//      ===============================================

    public AdapterTratamientos myAdapter1;
    public AdapterTratsParaPlan myAdapter22;

    public List<Tratamiento> aTras = new ArrayList<Tratamiento>();

    public ListView myList11;
    public ListView myList22;


    public ListView getMyList22() {
        return myList2;
    }
    public AdapterTratsParaPlan getMyAdapter22() {
        return myAdapter22;
    }

    public void setMyAdapter1(AdapterTratamientos myAdapter) {
        this.myAdapter1 = myAdapter;
    }
    public void setMyAdapter22(AdapterTratsParaPlan myAdapter2) {
        this.myAdapter22 = myAdapter2;
    }


    public void setMyList11(ListView myList1) {
        this.myList11 = myList1;
    }
    public void setMyList22(ListView myList2) {
        this.myList22 = myList2;
    }


    public List<Tratamiento> getaTras() {
        return  aTras;
    }
    public void AddTras(Tratamiento Tras) {
        aTras.add(Tras);
    }


    // ------------ VARIABLES PARA REG-TRATAMEINTOS ------| END | ---

    FloatingActionButton btnUniversal;

    RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
    public RestAdapter getRestadpter() {
        return restadpter;
    }





    public String vistaActual="principal";
    //VARIABLE DE TRATS REALIZADOS
    private ArrayList<AdapterTratsConsulta.checkItem> ite = new ArrayList<AdapterTratsConsulta.checkItem>();
    private ArrayList<AdapterTratsConsulta.checkItem> itemRegPlan = new ArrayList<AdapterTratsConsulta.checkItem>();

    public ArrayList<AdapterTratsConsulta.checkItem> getItemRegPlan() {
        return itemRegPlan;
    }

    public int getTotalRegConsulta() {
        return TotalRegConsulta;
    }
    public void setTotalRegConsulta(int totalRegConsulta) {
        TotalRegConsulta = totalRegConsulta;
    }

    public void hideBtnUnivesal(String vistaAct){
        getSupportActionBar().setTitle( vistaActual);
        if(vistaAct!=v_reg_consulta && vistaAct!="Nuevo Paciente"){
            btnUniversal.hide();
        }else{
            btnUniversal.show();
        }
    }

    public void setIte(ArrayList<AdapterTratsConsulta.checkItem> ite) {
        this.ite = ite;
    }
    public void setItemRegPlan(ArrayList<AdapterTratsConsulta.checkItem> itemRegPlan) {
        this.itemRegPlan = itemRegPlan;
    }

    public String getVistaActual() {
        return vistaActual;
    }

    public void setVistaActual(String vistaActual) {
        this.vistaActual = vistaActual;
    }

    public int getUltimo_plan() {
        return ultimo_plan;
    }

    public void setUltimo_plan(int ultimo_plan) {
        this.ultimo_plan = ultimo_plan;
    }

    public int getId_pacienteA() {
        return id_pacienteA;
    }

    public void setId_pacienteA(int id_pacienteA) {
        this.id_pacienteA = id_pacienteA;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

//================================================
//   * * *  VARIABLES DE PACIENTE  * * *

    String NOMBRES;
    String SEXO;
    String OCUPACION;
    String DIRECCION;
    String TELEFONO;
    String ESTADO_CIVIL;
    String DIRECCION_OCU;
    String TELEFONO_OCU;
    String ALLEGADO;
    int EDAD;
    int id_pacienteA;
    int ultimo_plan;



//================================================
//  * * * VARIABLES DEL DOCTOR * * *
    String id_doctor;
    String nombre;

    private InputStream mmInStream;
    private Handler mHandler;

    String btNombre;
    boolean init= false;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PARAMS RECIBIDOS POR LOGIN
        id_doctor = getIntent().getStringExtra("id_doctor");
        nombre = getIntent().getStringExtra("nombre");

        mHandler = new Handler();
        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS); //recivimos la mac address obtenida en la actividad anterior

        if(address.equals("sinBT")){
            Log.d("sin BT","Recibido:"+address);
        }else {
//        Toast.makeText(this,"ADD: "+address, Toast.LENGTH_LONG).show();
            new ConnectBT().execute(); //Call the class to connect
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnUniversal = (FloatingActionButton) findViewById(R.id.fab);
        btnUniversal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if(init==false) {
//                    permitirEscuchar();
                    Escuchar();
                    init=true;
                }else{ Escuchar();} // PARA PRUEBAS SIN ARDUINO QUITAR LUEGO DE PROBAR
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment vista = new VistaPacientes();
        Bundle bundle = new Bundle();
        bundle.putString("address", address);
        bundle.putString("id_doctor", id_doctor);
        bundle.putString("nombre", nombre);
        cambioVistaU(vista,v_list_pacientes,bundle);
    }


//      ===============================================
//      |   COMPRUEBA SI EL ESTADO DEL BOTON ES NUEVO  |
//      ===============================================
    private boolean ButtonPress(){
        boolean state = false;
//        if(escucha > temp) {
        if(escucha>0){
            temp = escucha;
            state = true;
        }else{
//            actual = escucha;
            state = false;
        }
//        }
        return state;
    }


//    =================================
//    |   CAPTAR PULSO DEL PEDAL        |
//    =================================
    private void permitirEscuchar()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("TF".toString().getBytes());
                escucha = btSocket.getInputStream().available();
                btSocket.getInputStream().read();
//                int  escucha = btSocket.getInputStream().available(); //ESTE FUNCIONA PARO SUMA DE 3 EN 3

                start();


            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

//  =================================
//  |   INSTANCIAMOS EL GOOGLE NOW  |
//  =================================
    private  void Escuchar(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//Especificamos el idioma, en esta ocasión probé con el de Estados Unidos
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-ES");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE);
//        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS ,"99999999999999999999");
//        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS ,"99999999999999999999");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1000);
        //Iniciamos la actividad dentro de un Try en caso sucediera un error.
        try {
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(this, "Tu dispositivo no soporta el reconocimiento de voz", Toast.LENGTH_LONG).show();
        }

    }
    private void msg(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

//    ============================
//         THREAD PRINCIPAL BT
//    ============================
    @Override
    public void run() {
        t1 = Thread.currentThread();
        byte[] buffer = new byte[1024];
        int bytes;
        InputStream tmpIn = null;

        try {
            tmpIn = btSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmInStream = tmpIn;
//        Message msg = mHandler.obtainMessage(444);

        while(t1 == t){

            try{
                t1.sleep(800);

                escucha = btSocket.getInputStream().available();
//                String readMessage = new String(buffer, 0, actual);
                if(ButtonPress()){
                    bytes = mmInStream.read(buffer);
                    mHandler.obtainMessage(2, bytes, -1, buffer)
                            .sendToTarget();
                    Log.d("PULSADO", cont + "<<== -> " + bytes);
                    Escuchar();

                }else{
                    Log.d("Sin Pulsar", cont + "<<=="+ actual);
                }
                cont++;
            }
            catch(InterruptedException e){
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(){
        t = new Thread(this);
        t.start();
    }
    @Override
    public void onStart() {
        super.onStart();
    }

//    ================================================
//        * * * FUNCIONES FRAGMENTADAS * * *
//    ================================================
    @Override
    public void regPacienteComm(String nombre,String sexo,int edad,String direccion,String telefono,String ocupacion, String direccion_ocu, String telefono_ocu,String allegado) {
//        FragmentManager fragmentManager = getFragmentManager();
        VistaRegPaciente fragmentb=(VistaRegPaciente) getSupportFragmentManager().findFragmentById(R.id.f_main);
        fragmentb.setRegPaciente(nombre,sexo,edad,direccion,telefono,ocupacion,direccion_ocu,telefono_ocu,allegado);
    }

    @Override
    public void editDiente(int posicionDiente, String pared, String estado) {
        VistaRegDiagrama fragmentb=(VistaRegDiagrama) getSupportFragmentManager().findFragmentById(R.id.f_diagrama);
        fragmentb.editDiente(posicionDiente,pared,estado);
    }

    @Override
    public void guardarDiagrama(int id_paciente,int ultimo_plan) {
        VistaRegDiagrama fragmentb=(VistaRegDiagrama) getSupportFragmentManager().findFragmentById(R.id.f_diagrama);
        fragmentb.guardarDiagrama(id_paciente,ultimo_plan);
    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true;


        @Override
        protected void onPreExecute()
        {
//            progress = ProgressDialog.show(getApplicationContext(), "Connecting...", "Please wait!!!");
        }

        @Override
        protected Void doInBackground(Void... devices)
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    btNombre = myBluetooth.getName();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//conectamos al dispositivo y chequeamos si esta disponible
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Conexión BT Fallida");
                finish();
            }
            else
            {
                msg("Conectado");
                isBtConnected = true;
            }
//            progress.dismiss();
        }
    }
    public void onActivityResult(int requestcode, int resultcode, Intent datos)
    {

// Si el reconocimiento de voz es correcto almacenamos dentro de un array los datos obtenidos.
//Mostramos en pantalla el texto obtenido de la posición 0.
        if (resultcode== Activity.RESULT_OK && datos!=null)
        {
            ArrayList<String> text=datos.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Toast.makeText(this,text.get(0),Toast.LENGTH_LONG).show();
            interpretar(text.get(0).toString(),vistaA);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment vista = null;
        boolean trans = false;
        if (id == R.id.nav_camera) {
            vistaActual = v_reg_consulta;
            vista = new VistaRegDiagrama();
            trans= true;
        } else if (id == R.id.nav_gallery) {
            vista = new VistaRegPlanTratamiento();
            trans= true;
        } else if (id == R.id.nav_slideshow) {
            vistaActual = v_list_pacientes;
            vista = new VistaPacientes();
            trans= true;
        } else if (id == R.id.nav_manage) {
            vistaActual = "pagos";
            vista = new  VistaRegPagos();
            trans= true;
        } else if (id == R.id.nav_share) {
//            vista = new VistaRegPaciente();
            vista = new VistaGetDiagrama(1,1); // << ? ? ? PRUEBA PARA HISTORIAL
            trans= true;
        } else if (id == R.id.nav_send) {
            vista = new p2ListView();
            trans= true;
        }

//        =========================================
//        |    MANEJADOR DE FRAGMENT PRINCIPAL    |
//        =========================================
        if(trans){
            Bundle bundle = new Bundle();

            //PARAMS PARA ENVIAR A FRAGMENTS
            bundle.putString("id_doctor", id_doctor);
            bundle.putString("nombre", nombre);
            vista.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.f_main, vista);
            vistaA = vista;
            String titulo_Bar = item.getTitle().toString();
            vistaActual= titulo_Bar;
            hideBtnUnivesal(vistaActual);
            transaction.addToBackStack(null);
            transaction.commit();
            item.setCheckable(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    =====================================================================================
//                         * * *  INTERPRETE DE COMANDOS * * *
//    =====================================================================================
    public void interpretar(String comandos,Object vista){

// ----------------------------------------[ VISTA REG. DIAGRAMA ]----------------------------------------
        if(vistaActual==v_reg_consulta){
            if(comandos.equals("guardar") || comandos.equals("Guardar") || comandos.equals("guarda")) {

                DienteService servicio = restadpter.create(DienteService.class);
                for(int i=0; i< ite.size(); i++) {
                    try {
                    servicio.regConsulta(
                        id_pacienteA,
                        ite.get(i).getId_p_tratamiento(),
                        ite.get(i).getEstado(),
                        "FALTA ESTO EN LA APP",
                        ite.get(i).getCantidad(),
                        new Callback<String>() {
                            @Override
                            public void success(String s, Response response) {
                                Toast.makeText(getApplicationContext(), "..." +s+"...", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(getApplicationContext(),"ERROR :"+error+"...",Toast.LENGTH_LONG).show();
                            }
                        }
                    ); Thread.sleep(100);
                    }catch(InterruptedException e){}
                    Toast.makeText(getApplicationContext(), "Cantidad R => "+ ite.get(i).getCantidad(), Toast.LENGTH_LONG).show();
                }
                guardarDiagrama(id_pacienteA,ultimo_plan); // => id_paciente , id_plan
            }else{

                String[] split = comandos.split(" ");
                String pared = "";

                String keywordR = "mesial",keywordL= "distal",
                        keywordR2 = "mecial",keywordL2= "distal",
                        keywordR3 = "marcial" ,keywordL3 = "distal",
                        keywordR4 = "marcial" ,keywordL4 = "distal",
                        keywordR5 = "marcial" ,keywordL5 = "distal";
                    int pos_diente = Integer.parseInt(split[0]);
                String estado_pared = split[2];

                //------------ LEFT ---------------
                if((pos_diente>=11 && pos_diente<=18)
                        || (pos_diente>=51 && pos_diente<=55)
                        || (pos_diente>=41 && pos_diente<=48)
                        || (pos_diente>=81 && pos_diente<=85)){
                    keywordR = "mesial";
                    keywordL = "distal";
                    keywordR2 = "mecial";
                    keywordR3 = "marcial";
                    keywordR4 = "marcial";
                    keywordR5 = "Marcial";
                }

                //------------ RIGHT ---------------
                if((pos_diente>=21 && pos_diente<=28)
                        || (pos_diente>=61 && pos_diente<=65)
                        || (pos_diente>=31 && pos_diente<=38)
                        || (pos_diente>=71 && pos_diente<=75)){
                    keywordR = "distal";
                    keywordL = "mesial";
                    keywordL2 = "macial";
                    keywordL3 = "mercial";
                    keywordL4 = "marcial";
                    keywordL5 = "Marcial";
                }


                //PALABRAS PARECIDAS A DERECHA  O RELACIONADAS
                if(split[1].equals("arriba")){pared = "U";}
                else if(split[1].equals("superior")){pared = "U";}
                else if(split[1].equals("Superior")){pared = "U";}

                //PALABRAS PARECIDAS A ABAJO O RELACIONADAS
                else if(split[1].equals("abajo")){pared = "D";}
                else if(split[1].equals("Abajo")){pared = "D";}
                else if(split[1].equals("inferior")){pared = "D";}
                else if(split[1].equals("Inferior")){pared = "D";}

                //PALABRAS PARECIDAS A IZQUIERDA O RELACIONADAS
                else if(split[1].equals(keywordL)){pared = "L";}
                else if(split[1].equals(keywordL2)){pared = "L";}
                else if(split[1].equals(keywordL3)){pared = "L";}
                else if(split[1].equals(keywordL4)){pared = "L";}
                else if(split[1].equals(keywordL5)){pared = "L";}

                //PALABRAS PARECIDAS A DERECHA O RELACIONADAS
                else if(split[1].equals(keywordR)){pared = "R";}
                else if(split[1].equals(keywordR2)){pared = "R";}
                else if(split[1].equals(keywordR3)){pared = "R";}
                else if(split[1].equals(keywordR4)){pared = "R";}
                else if(split[1].equals(keywordR5)){pared = "R";}

                //PALABRAS PARECIDAS A CENTRO
                else  if(split[1].equals("centro")){pared = "C";}
                else  if(split[1].equals("Centro")){pared = "C";}
                else  if(split[1].equals("centros")){pared = "C";}
                else  if(split[1].equals("Centros")){pared = "C";}

                editDiente(pos_diente, pared, estado_pared);
            }
        }
        if(vistaActual=="Nuevo Plan"){
            DienteService servicio = restadpter.create(DienteService.class);
            for(int i=0; i< ite.size(); i++) {
                try {
                    servicio.regConsulta(
                            id_pacienteA,
                            ite.get(i).getId_p_tratamiento(),
                            ite.get(i).getEstado(),
                            "FALTA ESTO EN LA APP",
                            ite.get(i).getCantidad(),
                            new Callback<String>() {
                                @Override
                                public void success(String s, Response response) {
                                    Toast.makeText(getApplicationContext(), "..." +s+"...", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Toast.makeText(getApplicationContext(),"ERROR :"+error+"...",Toast.LENGTH_LONG).show();
                                }

                            }
                    ); Thread.sleep(100);
                }catch(InterruptedException e){}
                Toast.makeText(getApplicationContext(), "Cantidad R => "+ ite.get(i).getCantidad(), Toast.LENGTH_LONG).show();
            }

        }

// ----------------------------------------[ VISTA REG. PACIENTE ]----------------------------------------
//        1.REGISTRAR PACIENTE (PRIMERA PRUEBA)
        if((comandos.equals("registrar paciente")) || (comandos.equals( "agregar nuevo paciente"))
                || (comandos.equals("nuevo paciente")) || (comandos.equals("registar un nuevo paciente"))
                || (comandos.equals("nuevo registro de paciente")))
        {
            Fragment vista2 = new VistaRegPaciente();
            Toast.makeText(this,"..."+vistaActual+"...",Toast.LENGTH_LONG).show();
            cambioVista(vista2, "Nuevo Paciente");

        }

        if(vistaActual=="Nuevo Paciente"){
            vistaA = vista;
            String[] split = comandos.split(" ");
            final StringBuilder nombre = new StringBuilder();
            StringBuilder direccion = new StringBuilder();
            StringBuilder telefono = new StringBuilder();
            StringBuilder sexo = new StringBuilder();
            StringBuilder edad = new StringBuilder();
            StringBuilder estado_civil = new StringBuilder();

            for (int i = 0; i < split.length; i++) {

                if (split[0].equals("nombre"))      {if(i>0){NOMBRES = nombre.append(" "+split[i]).toString();} }
                if (split[0].equals("dirección"))   {if(i>0){DIRECCION = direccion.append(" "+split[i]).toString();}}
                if (split[0].equals("teléfono"))    {if(i>0){TELEFONO = telefono.append(" "+split[i]).toString();}}
                if (split[0].equals("estado civil")){if(i>0){ESTADO_CIVIL = estado_civil.append(" "+split[i]).toString();}}
                if (split[0].equals("sexo"))        {if(i>0){SEXO = sexo.append(" "+split[i]).toString();}}
                regPacienteComm(NOMBRES, SEXO, 22, DIRECCION, TELEFONO, OCUPACION,DIRECCION_OCU,TELEFONO_OCU,ALLEGADO);
            }
            if(comandos.equals("guardar registro") || comandos.equals("guardar") || comandos.equals("confirmar registro")){
                PacienteService servicio = restadpter.create(PacienteService.class);
                servicio.regPaciente( NOMBRES, DIRECCION,TELEFONO, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        Toast.makeText(getApplicationContext(), "..." + s + "...", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(),"ERROR :"+error+"...",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }





    }


    public void cambioVista(final Fragment vistaObj, final String vActual){
        new Handler().post(new Runnable() {
            public void run() {
                vistaActual = vActual;
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.f_main, vistaObj);
                transaction.addToBackStack(null);
                hideBtnUnivesal(vActual);
                transaction.commit();
            }
        });
    }

    public void cambioVistaU(final Fragment vistaObj, final String vActual,Bundle parametros){
                vistaActual = vActual;
                vistaObj.setArguments(parametros);
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.f_main, vistaObj);
                transaction.addToBackStack(null);
                hideBtnUnivesal(vActual);
                transaction.commit();
     }



}

