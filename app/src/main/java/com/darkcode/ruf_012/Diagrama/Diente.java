package com.darkcode.ruf_012.Diagrama;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.DisplayMetrics;
import android.view.View;

import com.darkcode.ruf_012.R;

/**
 * Created by NativoLink on 3/2/17.
 */

public class Diente extends View {

    Diente myView;
    int PosDiente;
    int cuadrante;
    boolean tipo_diente = false;

    public boolean isTipo_diente() {
        return tipo_diente;
    }

    public void setTipo_diente(boolean tipo_diente) {
        this.tipo_diente = tipo_diente;
    }

    public String getEstadoDB() {
        return estadoDB;
    }

    public void setEstadoDB(String estadoDB) {
        this.estadoDB = estadoDB;
    }

    String estadoDB;

    public int getCuadrante() {
        return cuadrante;
    }

    public void setCuadrante(int cuadrante) {
        this.cuadrante = cuadrante;
    }

    public int getPosDiente() {
        return PosDiente;
    }

    public void setPosDiente(int posDiente) {
        PosDiente = posDiente;
    }

    float x;
    float y;
    float xx 	=  	x;
    float xy 	=	y;
    Paint paint = new Paint(); // pintura o pincel
    Paint paint2 = new Paint(); // pintura o pincel

    Paint paint3 = new Paint(); // pintura o pincel
    Paint paint4 = new Paint(); // pintura o pincel
    Paint paint5 = new Paint(); // pintura o pincel
    Paint paint6 = new Paint(); // pintura o pincel
    Drawable img_up_1;  // UP AREA
    Drawable img_left_1;  // LEFT AREA
    Drawable img_down_1;   // DOWN AREA
    Drawable img_right_1;  // RIGHT AREA
    Drawable img_center_1;  // CENTER AREA


    String Estado_U="vacio";
    String Estado_D="vacio";
    String Estado_C="vacio";
    String Estado_L="vacio";
    String Estado_R="vacio";

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    String area;

    public String getEstado_R() {
        return Estado_R;
    }

    public void setEstado_R(String estado_R) {
        Estado_R = estado_R;
    }

    public String getEstado_D() {
        return Estado_D;
    }

    public void setEstado_D(String estado_D) {
        Estado_D = estado_D;
    }

    public String getEstado_C() {
        return Estado_C;
    }

    public void setEstado_C(String estado_C) {
        Estado_C = estado_C;
    }

    public String getEstado_L() {
        return Estado_L;
    }

    public void setEstado_L(String estado_L) {
        Estado_L = estado_L;
    }

    public String getEstado_U() {
        return Estado_U;
    }

    public void setEstado_U(String estado_U) {
        Estado_U = estado_U;
    }

    Context contexto;

    private Paint myCircle;
    public Diente(Context context,int dientePosicion,float xc, float yc,boolean tipoDiente){
        super(context);
        setTipo_diente(tipoDiente);
        xx= xc;
        xy= yc;
        contexto = context;
        PosDiente = dientePosicion;


        //=======================================
        // 			* * * AREA UP * * *
        // =======================================
        img_up_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
//        img_up_1 = contexto.getResources().getDrawable(R.drawable.add);

        //=======================================
        // 			* * * AREA LEFT * * *
        // =======================================
        img_left_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);


        //=======================================
        // 			* * * AREA DOWN * * *
        // =======================================
        img_down_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);

        //=======================================
        // 			* * * AREA RIGHT * * *
        // =======================================
        img_right_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);

        //=======================================
        // 			* * * AREA CENTER * * *
        // =======================================
        img_center_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);

        initView();


    }

    private void initView(){
        myView = this;

        myCircle = new Paint();
        myCircle.setColor(Color.parseColor("#0187d0"));

        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE); // el tipo de trazado
        paint2.setStyle(Paint.Style.STROKE); // el tipo de trazado

        paint3.setStyle(Paint.Style.FILL); // el tipo de trazado
        paint4.setStyle(Paint.Style.FILL); // el tipo de trazado
        paint5.setStyle(Paint.Style.FILL); // el tipo de trazado
        paint6.setStyle(Paint.Style.FILL); // el tipo de trazado


        // convertimos un color Hexa en un android.graphics.Color
        paint.setColor(Color.parseColor("#000000"));
        paint2.setColor(Color.parseColor("#BF1900"));
        paint3.setColor(Color.parseColor("#BF1900"));

        // convertimos un color Hexa en un android.graphics.Color
        paint.setColor(Color.parseColor("#0187d0"));


    }

    public void cambiarColor(String estado,String pared){

        int color = 0; // CARRIE ,ETC
        if(estado.equals("restaurado")){color = Color.parseColor("#40c4ff");}
        if(estado.equals("careado")){color = Color.parseColor("#d50000");}
        if(estado.equals("ausente")){color = Color.parseColor("#3e2723");}

        PorterDuff.Mode mMode = PorterDuff.Mode.SRC_ATOP;

        if(pared.equals("U")){img_up_1.setColorFilter(color, mMode);       Estado_U = estado;}
        if(pared.equals("D")){img_down_1.setColorFilter(color, mMode);     Estado_D = estado;}
        if(pared.equals("C")){img_center_1.setColorFilter(color, mMode);   Estado_C = estado;}
        if(pared.equals("L")){img_left_1.setColorFilter(color, mMode);     Estado_L = estado;}
        if(pared.equals("R")){img_right_1.setColorFilter(color, mMode);    Estado_R = estado;}
//          img_up_1.setPixel(25,25,Color.parseColor("#f50057")); //  == >> Bitmap
//        myView.setBackgroundColor(red);
    }


    public void onDraws(Canvas canvas) {
        // pintamos un rectangulo negro
        float div = (float) 1.2;
        float cPosXIni  =  50/div;
        float cPosYIni  = 40/div;
        float cWH       = 180/div;
        float cWH_ext   = 230/div;

        float cDPosX   = 110/div;
        float radioInt = 2/div;
        float radioExt = 7/div;
        if(tipo_diente==false) {
            canvas.drawRect(dpToPx(xx), dpToPx(xy), dpToPx(xx + cWH_ext), dpToPx(xy + cWH_ext), paint);

            // pintamos un rectangulo INTERIOR
            canvas.drawRect(dpToPx(xx + cPosXIni), dpToPx(xy + cPosYIni), dpToPx(xx + cWH), dpToPx(xy + cWH), paint);
        }else{
            canvas.drawCircle(dpToPx(xx+ cDPosX), dpToPx(xy + cDPosX), radioExt * (18/div), paint);
            canvas.drawCircle(dpToPx(xx + cDPosX),  dpToPx(xy + cDPosX), radioInt * (17/div), paint);
        }
        Paint font = new Paint();
        font.setARGB(255, 255, 0, 0);
        font.setTextSize(42);
        font.setTypeface(Typeface.SERIF);
        canvas.drawText(""+PosDiente,dpToPx(xx+85), dpToPx(xy-5), font);



//        =======================[ PAREDES DENTALES ]=======================

        //=======================================
        // 			* * * AREA UP * * *
        //=======================================
        if(tipo_diente==false) {img_up_1.setBounds(dpToPx(xx+55),dpToPx(xy+5),dpToPx(xx+cWH), dpToPx(xy+40)); }else{
            img_up_1.setBounds(dpToPx(xx+cPosXIni+40),dpToPx(xy+20),dpToPx(xx+140), dpToPx(xy+140));
        }
        img_up_1.draw(canvas);

        //=======================================
        // 			* * * AREA LEFT * * *
        //=======================================
        if(tipo_diente==false) {img_left_1.setBounds(dpToPx(xx),dpToPx(xy+cPosYIni),dpToPx(xx+55), dpToPx(xy+180));}else{
            img_left_1.setBounds(dpToPx(xx+20),dpToPx(xy+cPosYIni+40),dpToPx(xx+70), dpToPx(xy+150));
        }
        img_left_1.draw(canvas);

        //=======================================
        // 			* * * AREA DOWN * * *
        //=======================================
        if(tipo_diente==false) {img_down_1.setBounds(dpToPx(xx+cPosXIni),dpToPx(xy+cWH),dpToPx(xx+cWH), dpToPx(xy+235/div));}else{
            img_down_1.setBounds(dpToPx(xx+cPosXIni+40),dpToPx(xy+140),dpToPx(xx+140), dpToPx(xy+205));
        }
        img_down_1.draw(canvas);

        //=======================================
        // 			* * * AREA RIGHT * * *
        //=======================================
        if(tipo_diente==false) {img_right_1.setBounds(dpToPx(xx+cWH),dpToPx(xy+cPosYIni),dpToPx(xx+235/div), dpToPx(xy+180));}else{
            img_right_1.setBounds(dpToPx(xx+150),dpToPx(xy+cPosYIni+40),dpToPx(xx+200), dpToPx(xy+150));
        }
        img_right_1.draw(canvas);

        //=======================================
        // 			* * * AREA CENTER * * *
        //=======================================
        if(tipo_diente==false) {
            img_center_1.setBounds(dpToPx(xx + cPosXIni), dpToPx(xy + cPosYIni), dpToPx(xx + cWH), dpToPx(xy + cWH));
            img_center_1.draw(canvas);
        }





        // pintamos un circulo rojo
//			canvas.drawCircle(51, 51, radius, paint);


        //========================================================
        // 	  + + + TRAZADOS DIVISORES DE PAREDES DENTAL + + +
        //========================================================
        Paint paintx = new Paint();
        paintx.setStyle(Paint.Style.FILL);
        paintx.setStrokeWidth(10);


        Path path = new Path();  // MUELA Y DIENTES
        Path path2 = new Path(); // MUELA Y DIENTES
        // *  * MUELAS *  *
        float x1 = 50/div;
        float x2 = 35/div;
        float x3 = 185/div;
        float x4 = 230/div;
        float x5 = 180/div;
        // *  * DIENTES *  *
        float x6 = 50/div;
        float x8 = 20/div;
        float x9 = 170/div;
        float x10 = 205/div;

        if(tipo_diente==false) {
            path.moveTo(dpToPx(xx), dpToPx(xy));
            path.lineTo(dpToPx(xx + x1), dpToPx(xy + x2));
            path.lineTo(dpToPx(xx + x1), dpToPx(xy + x3));
            path.lineTo(dpToPx(xx), dpToPx(xy + x4));

            path2.moveTo(dpToPx(xx + x4), dpToPx(xy));
            path2.lineTo(dpToPx(xx + (x4-x1)), dpToPx(xy + x2));
            path2.lineTo(dpToPx(xx + x5), dpToPx(xy + x3));
            path2.lineTo(dpToPx(xx + x4), dpToPx(xy + x4));
        }else{
            path.moveTo(dpToPx(xx + x6), dpToPx(xy + x8));
            path.lineTo(dpToPx(xx + x9), dpToPx(xy + x10));

            path2.moveTo(dpToPx(xx + x9),  dpToPx(xy + x8));
            path2.lineTo(dpToPx(xx + x6), dpToPx(xy + x10));

        }


        canvas.drawPath(path, paint);
        canvas.drawPath(path2, paint);

    }


    public int dpToPx(float dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_XXXHIGH));
        return px;
    }

}