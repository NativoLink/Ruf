package com.darkcode.ruf_012.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.DeviceList;
import com.darkcode.ruf_012.Doctor.Doctor;
import com.darkcode.ruf_012.Doctor.DoctorService;
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

        TextView tvLogin = (TextView)findViewById(R.id.tvLogo);
        Typeface typeFace= Typeface.createFromAsset(getAssets(),"SignPainter-HouseScript.ttf");
        tvLogin.setTypeface(typeFace);


        user = (EditText)findViewById(R.id.etUserLogin);
        pass = (EditText)findViewById(R.id.etPassLogin);
        iniciar = (Button)findViewById(R.id.btnLogin);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOnline() == false) {
                    Toast.makeText(Login.this, "Sin conexión", Toast.LENGTH_LONG).show();
                }else {

                    name_user = user.getText().toString();
                    pass_user = pass.getText().toString();
                    if ((!name_user.equals("") && !pass_user.equals("")) && (!name_user.equals(" ") && !pass_user.equals(" ")))
                    {
                        pb.setVisibility(View.VISIBLE);
                        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
                        DoctorService servicio = restadpter.create(DoctorService.class);
//                        Intent inten= new Intent(Login.this, DeviceList.class);
//                        startActivity(inten);
                        servicio.postLogin(name_user, pass_user, new Callback<Doctor>() {
                        @Override
                        public void success(Doctor doctor, Response response) {
                            if (doctor.isLogin().equals("true")) {
                                    Intent intent2 = new Intent(Login.this, DeviceList.class);
                                    String idDoctor = String.valueOf(doctor.getId_doctor());
                                    intent2.putExtra("id_doctor", idDoctor);
                                    intent2.putExtra("nombre", doctor.getNombre());
                                    intent2.putExtra("permisos", String.valueOf(doctor.getPermisos()));
                                    pb.setVisibility(View.GONE);
                                    startActivity(intent2);

                                Toast.makeText(Login.this, "Bienvenid@ " + doctor.getNombre(), Toast.LENGTH_LONG).show();

                            }else{
                                pb.setVisibility(View.INVISIBLE);
                                Toast.makeText(Login.this, "Acceso No valido", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(Login.this, "Error en Servidor", Toast.LENGTH_LONG).show();
                            Log.d("ERROR RETROFIT",error.getMessage());
                            pb.setVisibility(View.INVISIBLE);
                        }
                    });
                }else{
                        Toast.makeText(Login.this, "Campos vacíos", Toast.LENGTH_LONG).show();
                        pb.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });


    }
}
