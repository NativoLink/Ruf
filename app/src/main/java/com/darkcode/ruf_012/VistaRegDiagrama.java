package com.darkcode.ruf_012;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.darkcode.ruf_012.Diagrama.Diente;
import com.darkcode.ruf_012.Diagrama.DienteService;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 2/2/17.
 */

public class VistaRegDiagrama extends Fragment {

    boolean estado_o = false;
    Diente diente18,diente17,diente16,diente15,diente14,diente13,diente12,diente11;
    Diente diente55,diente54,diente53,diente52,diente51;

    ArrayList<Diente> lista = new ArrayList<Diente>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//     View rootView= inflater.inflate(R.layout.reg_paciente, container,false);


        return new MyView(getContext());

    }
    public class MyView extends View {
        public MyView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            diente18 = new Diente(getContext(),18,50,70,false);
            diente17 = new Diente(getContext(),17,350,70,false);
            diente16 = new Diente(getContext(),16,650,70,false);
            diente15 = new Diente(getContext(),15,950,70,false);
            diente14 = new Diente(getContext(),14,1250,70,false);
            diente13 = new Diente(getContext(),13,1550,70,false);
            diente12 = new Diente(getContext(),12,1850,70,false);
            diente11 = new Diente(getContext(),11,2150,70,false);

            diente55 = new Diente(getContext(),55,950,370,true);
            diente54 = new Diente(getContext(),54,1250,370,true);
            diente53 = new Diente(getContext(),53,1550,370,true);
            diente52 = new Diente(getContext(),52,1850,370,true);
            diente51 = new Diente(getContext(),51,2150,370,true);



            lista.add(diente18);
            lista.add(diente17);
            lista.add(diente16);
            lista.add(diente15);
            lista.add(diente14);
            lista.add(diente13);
            lista.add(diente12);
            lista.add(diente11);


            lista.add(diente55);
            lista.add(diente54);
            lista.add(diente53);
            lista.add(diente52);
            lista.add(diente51);



            new Thread(new Runnable() {
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(500);
                            postInvalidate();
                            if(estado_o==true){
//                              diente.cambiarColor("#f50057","U");
                                estado_o = false;
                                Log.v("CANVAS","TRUE",null);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


        }

        @Override
        protected void onDraw(final Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);


            diente18.onDraws(canvas);
            diente17.onDraws(canvas);
            diente16.onDraws(canvas);
            diente15.onDraws(canvas);
            diente14.onDraws(canvas);
            diente13.onDraws(canvas);
            diente12.onDraws(canvas);
            diente11.onDraws(canvas);


            diente55.onDraws(canvas);
            diente54.onDraws(canvas);
            diente53.onDraws(canvas);
            diente52.onDraws(canvas);
            diente51.onDraws(canvas);


        }
    }
    public  void editDiente(int posicionDiente,String pared,String estado){

        for(Diente p : lista) {

            if(p.getPosDiente() == posicionDiente){
                if(pared.equals("U")){
                    p.cambiarColor(estado,"U");
                }
                if(pared.equals("D")){
                    p.cambiarColor(estado,"D");
                }
                if(pared.equals("L")){
                    p.cambiarColor(estado,"L");
                }
                if(pared.equals("R")){
                    p.cambiarColor(estado,"R");
                }
                if(pared.equals("C")){
                    p.cambiarColor(estado,"C");
                }

            }else{
                Log.v("DienteActual",p.getPosDiente()+">>FALSE>"+estado,null);
            }
        }
    }
    public void guardarDiagrama(int id_paciente){
        for(Diente d : lista) {
            ArrayList<String> pared = new ArrayList<String>();
            int posicion = d.getPosDiente();


            pared.add("U");
            pared.add("D");
            pared.add("C");
            pared.add("R");
            pared.add("L");

            for(String pa : pared) {
                String estado ="vacio";
                if(pa=="U"){   estado = d.getEstado_U();}
                if(pa=="D"){   estado = d.getEstado_D();}
                if(pa=="C"){   estado = d.getEstado_C();}
                if(pa=="R"){   estado = d.getEstado_R();}
                if(pa=="L"){   estado = d.getEstado_L();}

                RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
                DienteService servicio = restadpter.create(DienteService.class);
                servicio.guardarDiente(id_paciente,posicion,pa,estado,new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        Log.d("RETORNO",">>"+s);

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), "ERROR :" + error + "...", Toast.LENGTH_LONG).show();
                        Log.d("RETORNO",">"+error.getMessage());
                    }
                });
            }


        }
    }

}
