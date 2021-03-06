package com.darkcode.ruf_012;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import com.darkcode.ruf_012.Diagrama.DienteService;
import com.darkcode.ruf_012.Diagrama.VistaRegDiagrama;
import com.darkcode.ruf_012.Doctor.DoctorService;
import com.darkcode.ruf_012.Doctor.VistaDoctores;
import com.darkcode.ruf_012.Doctor.VistaRegDoctor;
import com.darkcode.ruf_012.Login.Login;
import com.darkcode.ruf_012.Paciente.Especialidad;
import com.darkcode.ruf_012.Paciente.PacienteService;
import com.darkcode.ruf_012.Paciente.VistaRegPaciente;
import com.darkcode.ruf_012.Pagos.AdapterConPendientes;
import com.darkcode.ruf_012.Pagos.AdapterDetallePagoR;
import com.darkcode.ruf_012.Pagos.AdapterRegPago;
import com.darkcode.ruf_012.Pagos.ConsultaPendiente;
import com.darkcode.ruf_012.Pagos.DetallePagoR;
import com.darkcode.ruf_012.Tratamientos.AdapterTratamientos;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsConsulta;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsDConsulta;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsParaPlan;
import com.darkcode.ruf_012.Tratamientos.AdapterTratsRConsulta;
import com.darkcode.ruf_012.Tratamientos.Tratamiento;
import com.darkcode.ruf_012.Tratamientos.TratamientoService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Runnable,Communicator{

    public int intent_actual = 0 ;
    public int intent_anterior = 0;
    int temp =0;
    int actual =0;
    int cont = 0;
    Thread t,t1;
    int  escucha;
    Object vistaA;
    int TotalRegConsulta = 0;
    String nota; // <- - - NOTA PARA REGCONSULTA
    String nota_plan; // <- - - NOTA PARA REGPLAN

    public String getNota_plan() {
        return nota_plan;
    }
    public void setNota_plan(String nota_plan) {
        this.nota_plan = nota_plan;
    }



    public String getNota() {
        if(nota==null){
            nota= "N/A";
        }else{
            nota= nota+" ";
        }
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
//      ==========================================================
//      |      VARIABLES PARA CONSULTAR DIAGRAMA HISTORIAL       |
//      ==========================================================

    int cTratsRTotal;
    public int getcTratsRTotal() {
//        Toast.makeText(this, "cTratsRTotal ==>"+cTratsRTotal, Toast.LENGTH_LONG).show();
        return cTratsRTotal;
    }
    public void setcTratsRTotal(int cTratsRTotal) {
        this.cTratsRTotal = cTratsRTotal;
//        Toast.makeText(this, "cTratsRTotal ==>"+cTratsRTotal, Toast.LENGTH_LONG).show();
    }



    // - - - DOCTOR PARA EDITAR
    int id_doctor_edit;

    public int getId_doctor_edit() {
        return id_doctor_edit;
    }
    public void setId_doctor_edit(int id_doctor_edit) {
        this.id_doctor_edit = id_doctor_edit;
    }




    // - - - VARIABLES PARA DETALLE DE PLAN
    public int costoTotalDetalleP;
    public int cantTras;

    public int getCostoTotalDetalleP() {
        return costoTotalDetalleP;
    }

    public void setCostoTotalDetalleP(int costoTotal) {
        this.costoTotalDetalleP = this.costoTotalDetalleP + costoTotal;
    }

    public int getCantTras() {
        return cantTras;
    }

    public void addOneCantTras() {
        this.cantTras = this.cantTras + 1;
    }




    //    - - - - - - - - - - - - - - - - - - - -
//    |   * * *  LISTADO DE VISTAS  * * *   |
//    - - - - - - - - - - - - - - - - - - - -
    String v_reg_consulta = "Nueva Consulta";
    String v_list_pacientes = "Listado de Pacientes";
    String v_consultas_pagos = "Consultas y Pagos";
    String v_examen_clinico = "Examen Clinico";
    String v_reg_paciente = "Nuevo Paciente";

    String v_nuevo_plan = "Nuevo Plan";
    String v_list_pagos = "Listado de Pagos";
    String v_reg_habitos = "Registro Habito de Higuiene";
    String v_reg_tratamiento = "Nuevo Tratatamiento";
    String v_reg_doctor = "Nuevo Doctor";
    String v_hist_med = "Historia Médica";

    public String getV_reg_doctor() {
        return v_reg_doctor;
    }
    public String getV_reg_paciente() {
        return v_reg_paciente;
    }
    public String getV_hist_med() {
        return v_hist_med;
    }
    public String getV_reg_tratamiento() {
        return v_reg_tratamiento;
    }
    public String getV_nuevo_plan() {
        return v_nuevo_plan;
    }
    public String getV_list_pagos() {
        return v_list_pagos;
    }
    public String getV_reg_habitos() {
        return v_reg_habitos;
    }
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
    public void setTotal(int total) {
        this.total = total;
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


    public int getCantidad_trat_marcado() {
        return cantidad_trat_marcado;
    }

    public void setCantidad_trat_marcado(int cantidad_trat_marcado) {
        this.cantidad_trat_marcado = cantidad_trat_marcado;
    }

    public int getCosto_total_general() {
        return costo_total_general;
    }

    public void rmvCosto_total_general(int costo_indi) {
        this.costo_total_general = this.costo_total_general - costo_indi;
    }

    public void addCosto_total_general(int costo_indi) {
        this.costo_total_general = this.costo_total_general + costo_indi;
    }

    public void setCosto_total_general(int costo_total_general) {
        this.costo_total_general = costo_total_general;
    }

    //     >> DETALLE DE NEW PLAN <<
    int cantidad_trat_marcado;
    int costo_total_general;

    List<ConsultaPendiente> tagsUse;




    // ------------ VARIABLES PARA REG-TRATAMEINTOS ------| END | ---

    FloatingActionButton btnUniversal;

    public RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
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
//        if(vistaAct==v_reg_consulta || vistaAct==getV_reg_paciente()){
        if(vistaAct==v_reg_consulta){
            btnUniversal.show();
        }else{
            btnUniversal.hide();
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

    public int getId_plan_select() {
        return id_plan_select;
    }

    public void setId_plan_select(int id_plan_select) {
        this.id_plan_select = id_plan_select;
    }



    public void addTotalDetallePagosR(int totalPagado) {
        this.totalDetallePagosR = totalPagado;
    }
    public int getTotalDetallePagosR() {
        return totalDetallePagosR;
    }
    public void setTotalDetallePagosR(int totalDetallePagosR) {
        this.totalDetallePagosR = totalDetallePagosR;
    }

    //===========================================================
//  * * * VARIABLES DEL DETALLE PAGOS REALIZADO  * * *
    int totalDetallePagosR = 0;

//===========================================================
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
    int id_plan_select;




    //================================================
//  * * * VARIABLES DEL DOCTOR * * *
    String id_doctor;
    String nombre;
    int permisos;

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }



    public int getPermisos() {
        return permisos;
    }


    public String getId_doctor() {
        return id_doctor;
    }
    public String getNombre() {
        return nombre;
    }

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


        TextView tvPerfil = (TextView)findViewById(R.id.tvPerfil);
        tvPerfil.setText(nombre);
        tvPerfil.setBackgroundColor(Color.argb(50, 0, 42, 105));

        try {
            setPermisos(Integer.valueOf(getIntent().getStringExtra("permisos")));


            if (getPermisos() == 999) {

            } else {
                hideItem();
            }
        }catch (NumberFormatException ex){
            Log.v("Permisos","Permisos MainActivity => CRASH por : "+ex.getMessage());
        }





        mHandler = new Handler();
        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS); //recivimos la mac address obtenida en la actividad anterior

        if(address.equals("sinBT")){
            Log.v("sin BT","Recibido:"+address);
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
                Snackbar.make(view, " - LISTO PARA ESCUCHAR - ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if(init==false) {
//                    permitirEscuchar();
                    try {
                        beginListenForData();
                    }catch(Exception ex){

                    }
//                    Escuchar();
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
        cambioVistaU(vista,getV_list_pacientes(),bundle);
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
            Log.v("ButtonPress","ButtonPress => TRUE");
        }else{
//            actual = escucha;
            state = false;
            Log.v("ButtonPress","ButtonPress => FALSE");
        }
//        }
        return state;
    }


//    =================================
//    |   CAPTAR PULSO DEL PEDAL       |
//    =================================
    private void permitirEscuchar()
    {

//        Toast.makeText(getApplicationContext(), "permitirEscuchar", Toast.LENGTH_LONG).show();
        Log.v("permitirEscuchar", "permitirEscuchar ==>> funct"+ actual);
        if (btSocket!=null)
        {

            try
            {
                btSocket.getOutputStream().write("TF".toString().getBytes());
                escucha = btSocket.getInputStream().available();
//                Toast.makeText(getApplicationContext(), "RUNNIG SOCKET btSocket != NULL", Toast.LENGTH_LONG).show();
                Log.v("RUNNIG ==> btSocket", " ==> escucha ->>"+escucha);
//                btSocket.getInputStream().read();
                beginListenForData();
//                escucha = btSocket.getInputStream().read(); //ESTE FUNCIONA PARO SUMA DE 3 EN 3
                Log.v("NOT RUNNIG ==> btSocket", " ==> NOT CONTINUE");
//                start();
//
//
            }
            catch (IOException e)
            {
                msg("Error >"+e.getMessage());
//                Toast.makeText(getApplicationContext(), "ERROR IOException btSocket", Toast.LENGTH_LONG).show();
                Log.v("NOT RUNNIG ==> btSocket",  "ERROR IOException btSocket");
            }
        }else{
//            Toast.makeText(getApplicationContext(), " ==> ERROR btSocket == NULL", Toast.LENGTH_LONG).show();
            Log.v("ERROR ==> btSocket", " ==> btSocket == NULL");
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
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS ,"99999999");
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS ,"99999999");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 100);

        if(intent_anterior<intent_actual) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent_anterior++;
        }else{
            intent_actual++;
        }
        //Iniciamos la actividad dentro de un Try en caso sucediera un error.
        try {
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, 1);
//            finishActivity(1);
//            finish();
        } catch (ActivityNotFoundException a) {
            Toast.makeText(this, "Tu dispositivo no soporta el reconocimiento de voz", Toast.LENGTH_LONG).show();
            Log.v("ERROR-2 BT ","BT ==>"+a);
        }

    }
    private void msg(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        Log.v("CONECTADO BT ","BT ==>"+s);
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
            Log.v("ERROR printStackTrace",  "ERROR ==> "+e.getMessage() );
        }
        mmInputStream = tmpIn;
        Message msg = mHandler.obtainMessage(444);

        while(t1 == t){

            try{
                t1.sleep(800);

                escucha = btSocket.getInputStream().available();
                if(ButtonPress()){
//                    bytes = mmInStream.read(buffer);
                    bytes = mmInputStream.available();
                    mHandler.obtainMessage(2, bytes, -1, buffer)
                            .sendToTarget();
                    Log.v("PULSADO", cont + "<<== -> " + bytes);
                    Escuchar();

                }else{
                    bytes = mmInputStream.available();
                    Log.v("Sin Pulsar", cont + "<<=="+ bytes+" | ECUCHA ==> "+escucha);
                }
                cont++;

            }
            catch(InterruptedException e){
                Log.v("ERROR-3 BT","==> BT");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                Log.v("ERROR-4 BT","==> BT");
                break;
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
                    Log.v("btSocket"," btSocket ==> RUNNING doInBackground ");
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    btNombre = myBluetooth.getName();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//conectamos al dispositivo y chequeamos si esta disponible
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                    Log.v("btSocket", "btSocket.connect() ==> btNombre :"+myBluetooth.getName());
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;
                Log.v("doInBackground ERROR","ERROR doInBackground "+e.getMessage());
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
        if (resultcode == Activity.RESULT_OK && datos!=null)
        {
            ArrayList<String> text=datos.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            Toast.makeText(this,text.get(0),Toast.LENGTH_LONG).show();
            interpretar(text.get(0).toString(),vistaA);


//            Toast.makeText(this,"==>"+ RecognizerIntent.RESULT_NO_MATCH,Toast.LENGTH_LONG).show();


        }
//        if(resultcode != Activity.RESULT_OK){
//            Toast.makeText(this,"NO ENTENDI ->"+RecognizerIntent.RESULT_NO_MATCH,Toast.LENGTH_LONG).show();
//            onBackPressed();
//        }
        if(resultcode == Activity.RESULT_CANCELED){
            try {
                datos.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                datos.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                datos.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }catch (NullPointerException ex){
                Log.v("ERROR NullPointerEx","onActivityResult NullPointerException ==>> FLAG_ACTIVITY_CLEAR_TOP");
            }
        }

    }

    @Override
    public void onBackPressed() {

    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
////            regEspecialidad("Registrar Especialidad").show();
//            listEspeci();
//
//
//            return true;
//        }
//
//        if (id == R.id.reset_db) {
//            PacienteService servicio = restadpter.create(PacienteService.class);
//            servicio.resetDB(new Callback<String>() {
//                @Override
//                public void success(String s, Response response) {
//                    Log.v("ResetDB","ResetDB "+s);
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                    Log.v("ResetDB ERROR","ERROR ResetDB "+error.getMessage());
//                }
//            });
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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
            vistaActual = getV_reg_paciente();
            vista = new VistaRegPaciente();
            trans= true;
        } else if (id == R.id.list_tratamiento) {
            listTratamientos("Listado de Tratamientos");
        } else if (id == R.id.list_paciente) {
            vistaActual = getV_list_pacientes();
            vista = new VistaPacientes();
            trans= true;
        } else if (id == R.id.nav_reg_doctor) {
            vistaActual = getV_reg_doctor();
            vista = new VistaRegDoctor();
            trans= true;
        } else if (id == R.id.nav_reg_trat) {
            regTrat("Registrar Tratamiento").show();
        } else if (id == R.id.nav_reg_espec) {
            regEspec("Registrar Especialidad").show();
        } else if (id == R.id.nav_list_espec) {
            listEspeci();
        } else if (id == R.id.nav_list_doct) {
            vistaActual = "Listado de Doctores";
            vista = new VistaDoctores();
            trans= true;
        } else if (id == R.id.nav_send) {
            Intent intent  = new Intent(getApplicationContext(),Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }



//        =========================================
//        |    MANEJADOR DE FRAGMENT PRINCIPAL    |
//        =========================================
        if(trans){
            Bundle bundle = new Bundle();

            //PARAMS PARA ENVIAR A FRAGMENTS
            bundle.putString("id_doctor", id_doctor);
            bundle.putString("nombre", nombre);
            bundle.putString("tipo", "registrar");
            vista.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.f_main, vista);
            vistaA = vista;
//            String titulo_Bar = item.getTitle().toString();
//            vistaActual= titulo_Bar;
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
//            Toast.makeText(getApplicationContext(), v_reg_consulta+" => INTERPRETAR ==>> "+vistaActual, Toast.LENGTH_SHORT).show();
            if(comandos.equals("guardar") || comandos.equals("Guardar") || comandos.equals("guarda")) {

                DienteService servicio = restadpter.create(DienteService.class);
                for(int i=0; i< ite.size(); i++) {
                    try {
                        servicio.regConsulta(
                                id_pacienteA,
                                ite.get(i).getId_p_tratamiento(),
                                getUltimo_plan(),
                                ite.get(i).getEstado(),
                                getNota(),
                                ite.get(i).getCantidad(),
                                new Callback<String>() {
                                    @Override
                                    public void success(String s, Response response) {
                                        Toast.makeText(getApplicationContext(), "..." +s+"<<...", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
//                                Toast.makeText(getApplicationContext(),"ERROR :"+error+"...",Toast.LENGTH_LONG).show();
                                    }
                                }
                        ); Thread.sleep(100);
                    }catch(InterruptedException e){}
//                    Toast.makeText(getApplicationContext(), "Cantidad R => "+ ite.get(i).getCantidad(), Toast.LENGTH_LONG).show();
                }
                guardarDiagrama(id_pacienteA,ultimo_plan); // => id_paciente , id_plan
            }else{
                try{
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
                    else  if(split[1].equals("Central")){pared = "C";}

                    editDiente(pos_diente, pared, estado_pared);
                }catch(NumberFormatException ex){ // handle your exception
                }
            }
        }
        if(vistaActual==getV_nuevo_plan()){
            DienteService servicio = restadpter.create(DienteService.class);
            for(int i=0; i< ite.size(); i++) {
                try {
                    servicio.regConsulta(
                            id_pacienteA,
                            ite.get(i).getId_p_tratamiento(),
                            getUltimo_plan(),
                            ite.get(i).getEstado(),
                            getNota(),
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
//                Toast.makeText(getApplicationContext(), "Cantidad R => "+ ite.get(i).getCantidad(), Toast.LENGTH_LONG).show();
            }

        }

// ----------------------------------------[ VISTA REG. PACIENTE ]----------------------------------------
//        1.REGISTRAR PACIENTE (PRIMERA PRUEBA)
        if((comandos.equals("registrar paciente")) || (comandos.equals( "agregar nuevo paciente"))
                || (comandos.equals("nuevo paciente")) || (comandos.equals("registar un nuevo paciente"))
                || (comandos.equals("nuevo registro de paciente")))
        {
            Fragment vista2 = new VistaRegPaciente();
//            Toast.makeText(this,"..."+vistaActual+"...",Toast.LENGTH_LONG).show();
            cambioVista(vista2, v_reg_paciente);

        }

        if(vistaActual==v_reg_paciente){
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
                if (split[0].equals("edad"))
                {if(i>0){
                    EDAD = Integer.valueOf(edad.append(" "+split[i]).toString());
                }}
                regPacienteComm(NOMBRES, SEXO, EDAD, DIRECCION, TELEFONO, OCUPACION,DIRECCION_OCU,TELEFONO_OCU,ALLEGADO);
            }
            if(comandos.equals("guardar registro") || comandos.equals("guardar") || comandos.equals("confirmar registro")){
                PacienteService servicio = restadpter.create(PacienteService.class);
                servicio.regPaciente( NOMBRES, DIRECCION,TELEFONO,EDAD, new Callback<String>() {
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


    public AlertDialog regTrat(String titulo){


        final TratamientoService servicio = restadpter.create(TratamientoService.class);
        String[] arraySpinner;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_trat2, null);

        final Spinner spTipo;
        arraySpinner = new String[] {
                "simple", "limpieza","restauracion"
        };
        spTipo = (Spinner)v.findViewById(R.id.spTipo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        spTipo.setAdapter(adapter);
        spTipo.setSelection(1);

        TextView title =  (TextView)v.findViewById(R.id.tvTitle);
        title.setText(titulo);
        builder.setPositiveButton(R.string.registrar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText newTrat =  (EditText)v.findViewById(R.id.edNewTrat);
                String tipo  = spTipo.getSelectedItem().toString();
                servicio.regTrat(newTrat.getText().toString(), tipo, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        Log.v("Reg","REG: TRAT: "+s);
                        Toast.makeText(getApplicationContext(), "Registrado", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v("Reg ERROR","REG: TRAT: "+error.getMessage());
                    }
                });
            }
        })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.setView(v);
        return builder.create();
    }

    public AlertDialog regEspec(String titulo){


        final DoctorService  servicio = restadpter.create(DoctorService .class);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_trat, null);

        TextView title =  (TextView)v.findViewById(R.id.tvTitle);
        title.setText(titulo);
        EditText newTrat0 =  (EditText)v.findViewById(R.id.edNewTrat);
        newTrat0.setHint("Nueva Especialidad");
        builder.setPositiveButton(R.string.registrar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText newTrat =  (EditText)v.findViewById(R.id.edNewTrat);
                servicio.regEspecialidad(newTrat.getText().toString(), new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        Log.v("Reg","REG: ESPEC: "+s);
                        Toast.makeText(getApplicationContext(), "Registrado", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v("Reg ERROR","REG: ESPEC: "+error.getMessage());
                    }
                });
            }
        })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.setView(v);
        return builder.create();
    }




    public AlertDialog regAbono(String titulo, final int position, final List<ConsultaPendiente> pago, final Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_abono, null);


        TextView title =  (TextView)v.findViewById(R.id.tvTitle);
        title.setText(titulo);
        builder.setPositiveButton(R.string.registrar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText newTrat =  (EditText)v.findViewById(R.id.edNewAbono);
                int valor_a_abonar = Integer.valueOf(newTrat.getText().toString());


                final ConsultaPendiente tag = pago.get(position);
                tagsUse = getaPago();
                if (estaEnArray(tag, tagsUse)) {
                    Toast.makeText(getApplicationContext(), "Ya se encuentra añadido", Toast.LENGTH_LONG).show();
                } else {
                    pago.get(position).setCosto(valor_a_abonar);
                    Abonar(position, valor_a_abonar,pago, context);
                }
            }
        })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.setView(v);
        AlertDialog dialog = builder.create();
        return dialog;
    }



    public AlertDialog detallePagosR(String titulo, final List<DetallePagoR> pagoR,Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.diag_detalle_pago, null);


        TextView title =  (TextView)v.findViewById(R.id.tvTitle);
        title.setText(titulo);
        ((MainActivity)context).setTotalDetallePagosR(0);



        ListView lvresult =  (ListView)v.findViewById(R.id.lvdetallePago);
        AdapterDetallePagoR listAdapter = new AdapterDetallePagoR(getApplicationContext(), pagoR);
        for (DetallePagoR pago :pagoR) {
            ((MainActivity)context).addTotalDetallePagosR(pago.getPago());
        }
        lvresult.setAdapter(listAdapter);

        TextView  totalDetallePagosR =  (TextView)v.findViewById(R.id.tvTotal);
        totalDetallePagosR.setText(String.valueOf(getTotalDetallePagosR()));

        builder.setView(v);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    public AlertDialog detalleConsultas(String titulo, final List<Tratamiento> trats,Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.diag_detalle_pago, null);


        TextView title =  (TextView)v.findViewById(R.id.tvTitle);
        title.setText(titulo);
        ((MainActivity)context).setTotalDetallePagosR(0);


        TextView trat =  (TextView)v.findViewById(R.id.tv1);
        trat.setText("Tratamiento");


        TextView cantidad =  (TextView)v.findViewById(R.id.tv2);
        cantidad.setText("Cantidad");

        TextView costo =  (TextView)v.findViewById(R.id.tv3);
        costo.setText("Costo");



        ListView lvresult =  (ListView)v.findViewById(R.id.lvdetallePago);
        AdapterTratsDConsulta listAdapter = new AdapterTratsDConsulta(getApplicationContext(), trats);
        for (Tratamiento tratameinto :trats) {
            int costo_indi = tratameinto.getCosto() * tratameinto.getCant_r();
            ((MainActivity)context).addTotalDetallePagosR(costo_indi);
            Log.v("TOTAL INDI","TOTAL INDI >>"+tratameinto.getCosto()+"*"+tratameinto.getCant_r());
        }
        lvresult.setAdapter(listAdapter);

        TextView  totalDetallePagosR =  (TextView)v.findViewById(R.id.tvTotal);
        totalDetallePagosR.setText(String.valueOf(getTotalDetallePagosR()));

        builder.setView(v);
        AlertDialog dialog = builder.create();
        return dialog;
    }


    public void Abonar(int position,int valor_a_abonar,List<ConsultaPendiente> pago,Context context){
        if(valor_a_abonar > 0) {
            String tipo ="abono";

            ConsultaPendiente ObjetoPago = new ConsultaPendiente();
            ObjetoPago.setId_consulta(pago.get(position).getId_consulta());
            ObjetoPago.setTipo(tipo);
            Log.v("TIPO","TIPO >>"+tipo);

            ObjetoPago.setPagoAbono(valor_a_abonar);
            ((MainActivity) context).AddPago(ObjetoPago); // AGREGA UN NUEVO ELEMENTO A List<?>
            ((MainActivity) context).getMyAdapter2().notifyDataSetChanged(); // ACTUALIZA EL ADAPTER SEGUN SU List<?>
            ((MainActivity) context).TotalSuma(valor_a_abonar);
            ((MainActivity) context).UpdateTotal();
            Toast.makeText(context, "Monto disponible = "+((MainActivity) context).getMonto_a_pagar(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Monto disponible = "+((MainActivity) context).getMonto_a_pagar(), Toast.LENGTH_SHORT).show();
        }
    }


    public void listEspeci(){

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
//        builderSingle.setIcon(R.mipmap.ic_launcher);
        builderSingle.setTitle("Listado de Especialidades");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1);

        DoctorService servicio = restadpter.create(DoctorService.class);
        List<Especialidad> especialids;
        servicio.getEspecialidaddes(new Callback<List<Especialidad>>() {
            @Override
            public void success(List<Especialidad> especialidads, Response response) {
                for(Especialidad item : especialidads){
                    arrayAdapter.add(item.getNombre());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "ERROR ESPECIALIDADES", Toast.LENGTH_SHORT).show();
            }
        });

//        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builderSingle.show();
    }

    public void listTratamientos(String titulo){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.diag_detalle_pago, null);


        TextView title =  (TextView)v.findViewById(R.id.tvTitle);
        title.setText(titulo);

        TextView trat =  (TextView)v.findViewById(R.id.tv1);
        trat.setText("Nombre");


        TextView cantidad =  (TextView)v.findViewById(R.id.tv2);
        cantidad.setText("Tipo");


        View myView = v.findViewById(R.id.trTotal);
        ViewGroup parent = (ViewGroup) myView.getParent();
        parent.removeView(myView);


        View myView2 = v.findViewById(R.id.tv3);
        ViewGroup parent2 = (ViewGroup) myView2.getParent();
        parent2.removeView(myView2);



        TratamientoService servicio = restadpter.create(TratamientoService.class);
        servicio.getTratamientos(new Callback<List<Tratamiento>>() {
            @Override
            public void success(List<Tratamiento> tratamientos, Response response) {
                ListView lvresult =  (ListView)v.findViewById(R.id.lvdetallePago);
                AdapterTratamientos listAdapter = new AdapterTratamientos(getApplicationContext(), tratamientos);
                lvresult.setAdapter(listAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("ERROR listTratamientos","listTratamientos => "+error.getMessage());
            }
        });

        builder.setView(v);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean estaEnArray(ConsultaPendiente cp, List<ConsultaPendiente> permitidos){
        boolean existe = false;
        for (Iterator<ConsultaPendiente> i = permitidos.iterator(); i.hasNext();) {
//               Log.v("CP","ITEM => "+item);
            ConsultaPendiente item = i.next();
            if(item.getId_consulta() == cp.getId_consulta()){
                Log.v("CP","EXITE CP => "+cp.getId_consulta() + " COMO ITEM => "+item.getId_consulta());
                existe = true;
            }
        }
        return existe;

    }

    //    ============[ OCULTAR MENU SEGUN PRIORIDAD]============
    private void hideItem()
    {
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_reg_doctor).setVisible(false);
        nav_Menu.findItem(R.id.nav_reg_trat).setVisible(false);
//        nav_Menu.findItem(R.id.nav_list_espec).setVisible(false);
        nav_Menu.findItem(R.id.nav_reg_espec).setVisible(false);

        nav_Menu.findItem(R.id.menuDoctores).setVisible(false);
    }

//=============================================================
//=============================================================
//=============================================================

    Thread workerThread;
    boolean stopWorker;
    int readBufferPosition;
    byte[] readBuffer;

    public InputStream mmInputStream;

    void beginListenForData()
    {

        InputStream tmpIn = null;

        try {
            tmpIn = btSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("ERROR printStackTrace",  "ERROR ==> "+e.getMessage() );
        }
        mmInputStream = tmpIn;


        final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character

        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        int atnerior =0;
        workerThread = new Thread(new Runnable()

        {
            public void run()
            {
                Log.v("RUN DATA RECIVED","RUN DATA RECIVED ==> RUN");
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {


                    try
                    {
                        int bytesAvailable = mmInputStream.available();
//                        Log.v("RUN DATA RECIVED","RUN DATA RECIVED ==> bytesAvailable: "+bytesAvailable);
                        if(bytesAvailable == 2)
                        {
                            Escuchar();

                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    Log.v("RUN DATA RECIVED","RUN DATA RECIVED ==> encodedBytes: "+encodedBytes);
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;

                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                            Log.v("BT DATA RECIVED","DATA RECIVED ==>"+data);
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        Log.v("ERROR DATA RECIVED","ERROR DATA RECIVED ==>"+ex);
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }



}

