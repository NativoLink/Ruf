package com.darkcode.ruf_012.Login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.darkcode.ruf_012.DeviceList;
import com.darkcode.ruf_012.Paciente.Paciente;
import com.darkcode.ruf_012.Paciente.PacienteService;
import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 11/2/16.
 */
public class Login extends AppCompatActivity {

    EditText user;
    EditText pass;
    Button iniciar;
    String name_user;
    String pass_user;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final ProgressBar pb = (ProgressBar)findViewById(R.id.pbHeaderProgress);
        pb.setVisibility(View.INVISIBLE);



        user = (EditText)findViewById(R.id.etUserLogin);
        pass = (EditText)findViewById(R.id.etPassLogin);
        iniciar = (Button)findViewById(R.id.btnLogin);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (isOnline() == false) {
//                    Toast.makeText(Login.this, "Sin conexion", Toast.LENGTH_LONG).show();
//                }else {

                    name_user = user.getText().toString();
                    pass_user = pass.getText().toString();
                    if ((!name_user.equals("") && !pass_user.equals("")) && (!name_user.equals(" ") && !pass_user.equals(" ")))
                    {
                        pb.setVisibility(View.VISIBLE);
//                        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
//                        PacienteService servicio = restadpter.create(PacienteService.class);
                        Intent inten= new Intent(Login.this, DeviceList.class);
                        startActivity(inten);
//                        servicio.postLogin(name_user, pass_user, new Callback<Paciente>() {
//                        @Override
//                        public void success(Paciente cliente, Response response) {
//                            if (cliente.isLogin().equals("true")) {
//
//
//                                if (cliente.getUsertype() == 1) {
//
//
//                                    Intent intent2 = new Intent(Login.this, MainActivity.class);
//                                    String idCliente = String.valueOf(cliente.getId_cliente());
//                                    intent2.putExtra("id_cliente", idCliente);
//                                    intent2.putExtra("nombre", cliente.getNombre());
//                                    String tel = String.valueOf(cliente.getTelefono());
//                                    intent2.putExtra("telefono", tel);
//                                    intent2.putExtra("correo", cliente.getCorreo());
//                                    intent2.putExtra("apellido", cliente.getApellido());
//                                    String saldo = String.valueOf(cliente.getSaldo());
//                                    intent2.putExtra("saldo", saldo);
//                                    pb.setVisibility(View.GONE);
//                                    startActivity(intent2);
//                                }
//                                if (cliente.getUsertype() == 2) {
//                                    Intent intent = new Intent(Login.this, MainActivity.class);
//
//                                    String idCliente = String.valueOf(cliente.getId_cliente());
//                                    intent.putExtra("id_vendedor", idCliente);
//                                    intent.putExtra("nombre", cliente.getNombre());
//                                    intent.putExtra("correo", cliente.getCorreo());
//                                    intent.putExtra("apellido", cliente.getApellido());
//                                    pb.setVisibility(View.GONE);
//                                    String saldo = String.valueOf(cliente.getSaldo());
//                                    intent.putExtra("saldo", saldo);
//                                    startActivity(intent);
//                                }
//                                Toast.makeText(Login.this, "Bienvenid@ " + cliente.getNombre(), Toast.LENGTH_LONG).show();
//
//                            }else{
//                                pb.setVisibility(View.INVISIBLE);
//                                Toast.makeText(Login.this, "Acceso No valido", Toast.LENGTH_LONG).show();
//                            }

//                        }
//
//                        @Override
//                        public void failure(RetrofitError error) {
//                            Toast.makeText(Login.this, "Error en Servidor", Toast.LENGTH_LONG).show();
//                            pb.setVisibility(View.INVISIBLE);
//                        }
//                    });
                }else{
                        Toast.makeText(Login.this, "Campos vacíos", Toast.LENGTH_LONG).show();
                        pb.setVisibility(View.INVISIBLE);
                    }
//                }
            }
        });


    }
}
