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
import com.darkcode.ruf_012.Diagrama.DienteDB;
import com.darkcode.ruf_012.Diagrama.DienteService;
import com.darkcode.ruf_012.Paciente.Paciente;
import com.darkcode.ruf_012.Paciente.PacienteService;
import com.darkcode.ruf_012.Tratamientos.ListRegPlanTratamientos;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 2/2/17.
 */

public class VistaConsultarDiagrama extends Fragment {

    boolean estado_o = false;
    Diente diente,diente2,diente3,diente4,diente5,diente6;
    ArrayList<Diente> lista = new ArrayList<Diente>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//     View rootView= inflater.inflate(R.layout.reg_paciente, container,false);


        return new MyView(getContext());

    }
    public class MyView extends View {

        public MyView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            diente = new Diente(getContext(),18,50,70,false);
            diente2 = new Diente(getContext(),17,350,70,false);
            diente3 = new Diente(getContext(),16,650,70,false);
            diente4 = new Diente(getContext(),15,950,70,false);
            diente5 = new Diente(getContext(),14,650,70,false);
            diente6 = new Diente(getContext(),13,650,70,false);

            lista.add(diente);
            lista.add(diente2);
            lista.add(diente3);
            lista.add(diente4);
            lista.add(diente5);
            lista.add(diente6);

            RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
            DienteService servicio = restadpter.create(DienteService.class);
            servicio.unDiagrama(new Callback<List<DienteDB>>() {
                @Override
                public void success(List<DienteDB> dienteDBs, Response response) {
                    for(int i=0; i<dienteDBs.size(); i++){
                        int posDiente = dienteDBs.get(i).getPosDiente();
                        String pared = dienteDBs.get(i).getArea();
                        String estado = dienteDBs.get(i).getEstadoDB();
                        editDiente(posDiente, pared, estado);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getContext(), "ERROR :" + error + "...", Toast.LENGTH_LONG).show();
                    Log.d("RETORNO",">"+error.getMessage());
                }
            });
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


            diente.onDraws(canvas);
            diente2.onDraws(canvas);
            diente3.onDraws(canvas);
            diente4.onDraws(canvas);
//            diente5.onDraws(canvas);
//            diente6.onDraws(canvas);



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


}
