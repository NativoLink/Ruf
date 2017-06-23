package com.darkcode.ruf_012.Diagrama;

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

public class VistaGetDiagrama extends Fragment {
    

    int idPaciente,idConsulta;
    public VistaGetDiagrama(int idPaciente, int idConsulta) {
        getDiagrama( idPaciente,idConsulta);
    }

    boolean estado_o = false;
    //--------- PRIMER CUADRANTE ------
    Diente diente18,diente17,diente16,diente15,diente14,diente13,diente12,diente11;
    Diente diente55,diente54,diente53,diente52,diente51;

    //--------- CUARTO CUADRANTE ------
    Diente diente31,diente32,diente33,diente34,diente35,diente36,diente37,diente38;
    Diente diente75,diente74,diente73,diente72,diente71;

    //--------- SEGUNDO CUADRANTE ------
    Diente diente21,diente22,diente23,diente24,diente25,diente26,diente27,diente28;
    Diente diente61,diente62,diente63,diente64,diente65;

    //--------- CUARTO CUADRANTE ------
    Diente diente48,diente47,diente46,diente45,diente44,diente43,diente42,diente41;
    Diente diente85,diente84,diente83,diente82,diente81;

    ArrayList<Diente> lista = new ArrayList<Diente>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//     View rootView= inflater.inflate(R.layout.reg_paciente, container,false);


        return new MyView(getContext());

    }
    public class MyView extends View {

        public MyView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            //=======================================
            //      * * * PRIMER CUADRANTE * * *
            //=======================================
            float div = (float) (1.2);
            float ejeX = 70/div;
            float X_D=330;
            diente18 = new Diente(getContext(),18,50,ejeX,false);
            diente17 = new Diente(getContext(),17,395/div,ejeX,false);
            diente16 = new Diente(getContext(),16,740/div,ejeX,false);
            diente15 = new Diente(getContext(),15,1085/div,ejeX,false);
            diente14 = new Diente(getContext(),14,1430/div,ejeX,false);
            diente13 = new Diente(getContext(),13,1775/div,ejeX,false);
            diente12 = new Diente(getContext(),12,2120/div,ejeX,false);
            diente11 = new Diente(getContext(),11,2465/div,ejeX,false);

            diente55 = new Diente(getContext(),55,600/div,ejeX+X_D,true);
            diente54 = new Diente(getContext(),54,900/div,ejeX+X_D,true);
            diente53 = new Diente(getContext(),53,1200/div,ejeX+X_D,true);
            diente52 = new Diente(getContext(),52,1500/div,ejeX+X_D,true);
            diente51 = new Diente(getContext(),51,1800/div,ejeX+X_D,true);

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

            //=======================================
            //      * * * SEGUNDO CUADRANTE * * *
            //=======================================
            float ejeX2 = 70/div;
            float X_D2=330;
            diente21 = new Diente(getContext(),21,3110/div,ejeX2,false);
            diente22 = new Diente(getContext(),22,3455/div,ejeX2,false);
            diente23 = new Diente(getContext(),23,3800/div,ejeX2,false);
            diente24 = new Diente(getContext(),24,4145/div,ejeX2,false);
            diente25 = new Diente(getContext(),25,4490/div,ejeX2,false);
            diente26 = new Diente(getContext(),26,4835/div,ejeX2,false);
            diente27 = new Diente(getContext(),27,5180/div,ejeX2,false);
            diente28 = new Diente(getContext(),28,5525/div,ejeX2,false);

            diente61 = new Diente(getContext(),61,3660/div,ejeX+X_D2,true);
            diente62 = new Diente(getContext(),62,3960/div,ejeX+X_D2,true);
            diente63 = new Diente(getContext(),63,4260/div,ejeX+X_D2,true);
            diente64 = new Diente(getContext(),64,4560/div,ejeX+X_D2,true);
            diente65 = new Diente(getContext(),65,4860/div,ejeX+X_D2,true);

            lista.add(diente21);
            lista.add(diente22);
            lista.add(diente23);
            lista.add(diente24);
            lista.add(diente25);
            lista.add(diente26);
            lista.add(diente27);
            lista.add(diente28);

            lista.add(diente61);
            lista.add(diente62);
            lista.add(diente63);
            lista.add(diente64);
            lista.add(diente65);

            //=======================================
            //      * * * TERCER CUADRANTE * * *
            //=======================================
            float ejeX3 = 1000/div;
            diente31 = new Diente(getContext(),31,3110/div,ejeX3+X_D,false);
            diente32 = new Diente(getContext(),32,3455/div,ejeX3+X_D,false);
            diente33 = new Diente(getContext(),33,3800/div,ejeX3+X_D,false);
            diente34 = new Diente(getContext(),34,4145/div,ejeX3+X_D,false);
            diente35 = new Diente(getContext(),35,4490/div,ejeX3+X_D,false);
            diente36 = new Diente(getContext(),36,4835/div,ejeX3+X_D,false);
            diente37 = new Diente(getContext(),37,5180/div,ejeX3+X_D,false);
            diente38 = new Diente(getContext(),38,5525/div,ejeX3+X_D,false);

            diente75 = new Diente(getContext(),75,3660/div,ejeX3,true);
            diente74 = new Diente(getContext(),74,3960/div,ejeX3,true);
            diente73 = new Diente(getContext(),73,4260/div,ejeX3,true);
            diente72 = new Diente(getContext(),72,4560/div,ejeX3,true);
            diente71 = new Diente(getContext(),71,4860/div,ejeX3,true);

            lista.add(diente38);
            lista.add(diente37);
            lista.add(diente36);
            lista.add(diente35);
            lista.add(diente34);
            lista.add(diente33);
            lista.add(diente32);
            lista.add(diente31);

            lista.add(diente75);
            lista.add(diente74);
            lista.add(diente73);
            lista.add(diente72);
            lista.add(diente71);


            //=======================================
            //      * * * CUARTO CUADRANTE * * *
            //=======================================
            float ejeX4 = 1000/div;
            diente48 = new Diente(getContext(),48,50/div,ejeX4+X_D,false);
            diente47 = new Diente(getContext(),47,395/div,ejeX4+X_D,false);
            diente46 = new Diente(getContext(),46,740/div,ejeX4+X_D,false);
            diente45 = new Diente(getContext(),45,1085/div,ejeX4+X_D,false);
            diente44 = new Diente(getContext(),44,1430/div,ejeX4+X_D,false);
            diente43 = new Diente(getContext(),43,1775/div,ejeX4+X_D,false);
            diente42 = new Diente(getContext(),42,2120/div,ejeX4+X_D,false);
            diente41 = new Diente(getContext(),41,2465/div,ejeX4+X_D,false);

            diente85 = new Diente(getContext(),85,600/div,ejeX4,true);
            diente84 = new Diente(getContext(),84,900/div,ejeX4,true);
            diente83 = new Diente(getContext(),83,1200/div,ejeX4,true);
            diente82 = new Diente(getContext(),82,1500/div,ejeX4,true);
            diente81 = new Diente(getContext(),81,1800/div,ejeX4,true);

            lista.add(diente48);
            lista.add(diente47);
            lista.add(diente46);
            lista.add(diente45);
            lista.add(diente44);
            lista.add(diente43);
            lista.add(diente42);
            lista.add(diente41);

            lista.add(diente85);
            lista.add(diente84);
            lista.add(diente83);
            lista.add(diente82);
            lista.add(diente81);

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
                        Log.d("RETORNO","PINTANDO");
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
            //=======================================
            //      * * * PRIMER CUADRANTE * * *
            //=======================================
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
            //=======================================
            //      * * * SEGUNDO CUADRANTE * * *
            //=======================================
            diente21.onDraws(canvas);
            diente22.onDraws(canvas);
            diente23.onDraws(canvas);
            diente24.onDraws(canvas);
            diente25.onDraws(canvas);
            diente26.onDraws(canvas);
            diente27.onDraws(canvas);
            diente28.onDraws(canvas);


            diente61.onDraws(canvas);
            diente62.onDraws(canvas);
            diente63.onDraws(canvas);
            diente64.onDraws(canvas);
            diente65.onDraws(canvas);
            //=======================================
            //      * * * TERCER CUADRANTE * * *
            //=======================================
            diente31.onDraws(canvas);
            diente32.onDraws(canvas);
            diente33.onDraws(canvas);
            diente34.onDraws(canvas);
            diente35.onDraws(canvas);
            diente36.onDraws(canvas);
            diente37.onDraws(canvas);
            diente38.onDraws(canvas);


            diente71.onDraws(canvas);
            diente72.onDraws(canvas);
            diente73.onDraws(canvas);
            diente74.onDraws(canvas);
            diente75.onDraws(canvas);
            //=======================================
            //      * * * CUARTO CUADRANTE * * *
            //=======================================
            diente48.onDraws(canvas);
            diente47.onDraws(canvas);
            diente46.onDraws(canvas);
            diente45.onDraws(canvas);
            diente44.onDraws(canvas);
            diente43.onDraws(canvas);
            diente42.onDraws(canvas);
            diente41.onDraws(canvas);


            diente85.onDraws(canvas);
            diente84.onDraws(canvas);
            diente83.onDraws(canvas);
            diente82.onDraws(canvas);
            diente81.onDraws(canvas);


        }
    }
    public void getDiagrama(int id_paciente,int id_consulta){
        RestAdapter restadpter = new RestAdapter.Builder().setEndpoint("http://linksdominicana.com").build();
        DienteService servicio = restadpter.create(DienteService.class);
        servicio.unDiagrama(id_paciente,id_consulta,new Callback<List<DienteDB>>() {
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

    }
    public  void editDiente(int posicionDiente,String pared,String estado){

        for(Diente p : lista) {

            if(p.getPosDiente() == posicionDiente){
                if(pared.equals("U") || pared.equals("u")){
                    p.cambiarColor(estado,"U");
                }
                if(pared.equals("D") || pared.equals("d")){
                    p.cambiarColor(estado,"D");
                }
                if(pared.equals("L") || pared.equals("l")){
                    p.cambiarColor(estado,"L");
                }
                if(pared.equals("R") || pared.equals("r")){
                    p.cambiarColor(estado,"R");
                }
                if(pared.equals("C") || pared.equals("c")){
                    p.cambiarColor(estado,"C");
                }

            }else{
                Log.v("DienteActual",p.getPosDiente()+">>FALSE>"+estado,null);
            }
        }
    }


}
