package com.darkcode.ruf_012.Diagrama;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;

import com.darkcode.ruf_012.R;

/**
 * Created by NativoLink on 3/2/17.
 */

public class Diente extends View {

    Diente myView;
    int PosDiente;

    public int getPosDiente() {
        return PosDiente;
    }

    public void setPosDiente(int posDiente) {
        PosDiente = posDiente;
    }

    int x;
    int y;
    int xx 	=  	x;
    int xy 	=	y;
    Paint paint = new Paint(); // pintura o pincel
    Paint paint2 = new Paint(); // pintura o pincel

    Paint paint3 = new Paint(); // pintura o pincel
    Paint paint4 = new Paint(); // pintura o pincel
    Paint paint5 = new Paint(); // pintura o pincel
    Paint paint6 = new Paint(); // pintura o pincel
    Drawable img_up_1,img_up_2,img_up_3,img_up_4,img_up_5;  // UP AREA
    Drawable img_left_1,img_left_2,img_left_3,img_left_4,img_left_5;  // LEFT AREA
    Drawable img_down_1;
    Drawable img_down_2;
    Drawable img_down_3;
    Drawable img_down_4;
    Drawable img_down_5;  // DOWN AREA
    Drawable img_right_1,img_right_2,img_right_3,img_right_4,img_right_5;  // RIGHT AREA
    Drawable img_center_1,img_center_2,img_center_3,img_center_4,img_center_5;  // CENTER AREA


    String Estado_U="vacio",Estado_D="vacio",Estado_C="vacio",Estado_L="vacio",Estado_R="vacio";

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
    public Diente(Context context,int dientePosicion,int xc, int yc){
        super(context);
        xx= xc;
        xy= yc;
        contexto = context;
        PosDiente = dientePosicion;


        //=======================================
        // 			* * * AREA UP * * *
        // =======================================
        img_up_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_up_2 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_up_3 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_up_4 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_up_5 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);

        //=======================================
        // 			* * * AREA LEFT * * *
        // =======================================
        img_left_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_left_2 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_left_3 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_left_4 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_left_5 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);

        //=======================================
        // 			* * * AREA DOWN * * *
        // =======================================
        img_down_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_down_2 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_down_3 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_down_4 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_down_5 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);

        //=======================================
        // 			* * * AREA RIGHT * * *
        // =======================================
        img_right_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_right_2 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_right_3 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_right_4 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_right_5 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);

        //=======================================
        // 			* * * AREA CENTER * * *
        // =======================================
        img_center_1 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_center_2 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_center_3 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_center_4 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);
        img_center_5 = contexto.getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark);

        initView();


    }

    private void initView(){
        myView = this;

        myCircle = new Paint();
        myCircle.setColor(0xa300ff00);


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
        paint.setColor(Color.parseColor("#FF0000"));


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
        canvas.drawRect(dpToPx(xx),dpToPx(xy), dpToPx(xx+240), dpToPx(xy+240), paint);

        // pintamos un rectangulo INTERIOR
        canvas.drawRect(dpToPx(xx+60),dpToPx(xy+45), dpToPx(xx+180), dpToPx(xy+195),paint2);

        Paint font = new Paint();
        font.setARGB(255, 255, 0, 0);
        font.setTextSize(30);
        font.setTypeface(Typeface.SERIF);
        canvas.drawText(""+PosDiente,dpToPx(xx+95), dpToPx(xy-15), font);



//        =======================[ PAREDES DENTALES ]=======================

        //=======================================
        // 			* * * AREA UP * * *
        //=======================================
        img_up_1.setBounds(dpToPx(xx+55),dpToPx(xy+5),dpToPx(xx+75), dpToPx(xy+40));
        img_up_2.setBounds(dpToPx(xx+80),dpToPx(xy+5),dpToPx(xx+100), dpToPx(xy+40));
        img_up_3.setBounds(dpToPx(xx+105),dpToPx(xy+5),dpToPx(xx+125), dpToPx(xy+40));
        img_up_4.setBounds(dpToPx(xx+130),dpToPx(xy+5),dpToPx(xx+150), dpToPx(xy+40));
        img_up_5.setBounds(dpToPx(xx+155),dpToPx(xy+5),dpToPx(xx+175), dpToPx(xy+40));

        img_up_1.draw(canvas);
        img_up_2.draw(canvas);
        img_up_3.draw(canvas);
        img_up_4.draw(canvas);
        img_up_5.draw(canvas);


        //=======================================
        // 			* * * AREA LEFT * * *
        //=======================================
        img_left_1.setBounds(dpToPx(xx+10),dpToPx(xy+60),dpToPx(xx+55), dpToPx(xy+80));
        img_left_2.setBounds(dpToPx(xx+10),dpToPx(xy+85),dpToPx(xx+55), dpToPx(xy+105));
        img_left_3.setBounds(dpToPx(xx+10),dpToPx(xy+110),dpToPx(xx+55), dpToPx(xy+130));
        img_left_4.setBounds(dpToPx(xx+10),dpToPx(xy+135),dpToPx(xx+55), dpToPx(xy+155));
        img_left_5.setBounds(dpToPx(xx+10),dpToPx(xy+160),dpToPx(xx+55), dpToPx(xy+180));

        img_left_1.draw(canvas);
        img_left_2.draw(canvas);
        img_left_3.draw(canvas);
        img_left_4.draw(canvas);
        img_left_5.draw(canvas);

        //=======================================
        // 			* * * AREA DOWN * * *
        //=======================================
        img_down_1.setBounds(dpToPx(xx+55),dpToPx(xy+200),dpToPx(xx+75), dpToPx(xy+235));
        img_down_2.setBounds(dpToPx(xx+80),dpToPx(xy+200),dpToPx(xx+100), dpToPx(xy+235));
        img_down_3.setBounds(dpToPx(xx+105),dpToPx(xy+200),dpToPx(xx+125), dpToPx(xy+235));
        img_down_4.setBounds(dpToPx(xx+130),dpToPx(xy+200),dpToPx(xx+150), dpToPx(xy+235));
        img_down_5.setBounds(dpToPx(xx+155),dpToPx(xy+200),dpToPx(xx+175), dpToPx(xy+235));

        img_down_1.draw(canvas);
        img_down_2.draw(canvas);
        img_down_3.draw(canvas);
        img_down_4.draw(canvas);
        img_down_5.draw(canvas);

        //=======================================
        // 			* * * AREA RIGHT * * *
        //=======================================
        img_right_1.setBounds(dpToPx(xx+190),dpToPx(xy+60),dpToPx(xx+235), dpToPx(xy+80));
        img_right_2.setBounds(dpToPx(xx+190),dpToPx(xy+85),dpToPx(xx+235), dpToPx(xy+105));
        img_right_3.setBounds(dpToPx(xx+190),dpToPx(xy+110),dpToPx(xx+235), dpToPx(xy+130));
        img_right_4.setBounds(dpToPx(xx+190),dpToPx(xy+135),dpToPx(xx+235), dpToPx(xy+155));
        img_right_5.setBounds(dpToPx(xx+190),dpToPx(xy+160),dpToPx(xx+235), dpToPx(xy+180));

        img_right_1.draw(canvas);
        img_right_2.draw(canvas);
        img_right_3.draw(canvas);
        img_right_4.draw(canvas);
        img_right_5.draw(canvas);


        //=======================================
        // 			* * * AREA CENTER * * *
        //=======================================
        img_center_1.setBounds(dpToPx(xx+60),dpToPx(xy+45),dpToPx(xx+180), dpToPx(xy+195));
        img_center_2.setBounds(dpToPx(xx+190),dpToPx(xy+85),dpToPx(xx+235), dpToPx(xy+105));
        img_center_3.setBounds(dpToPx(xx+190),dpToPx(xy+110),dpToPx(xx+235), dpToPx(xy+130));
        img_center_4.setBounds(dpToPx(xx+190),dpToPx(xy+135),dpToPx(xx+235), dpToPx(xy+155));
        img_center_5.setBounds(dpToPx(xx+190),dpToPx(xy+160),dpToPx(xx+235), dpToPx(xy+180));

        img_center_1.draw(canvas);
//        img_center_2.draw(canvas);
//        img_center_3.draw(canvas);
//        img_center_4.draw(canvas);
//        img_center_5.draw(canvas);



        // pintamos un circulo rojo
//			canvas.drawCircle(51, 51, radius, paint);


        //========================================================
        // 	  + + + TRAZADOS DIVISORES DE PAREDES DENTAL + + +
        //========================================================
        Paint paintx = new Paint();
        paintx.setStyle(Paint.Style.FILL);
        paintx.setStrokeWidth(10);
//        paintx.setColor(Color.BLUE);

        Path path = new Path();
        path.moveTo(dpToPx(xx),xy);
        path.lineTo(dpToPx(xx+60),dpToPx(xy+45));
        path.lineTo(dpToPx(xx+60), dpToPx(xy+195));
        path.lineTo(dpToPx(xx), dpToPx(xy+240));

        canvas.drawPath(path, paint);


        Path path2 = new Path();
        path2.moveTo(dpToPx(xx+240), xy);
        path2.lineTo(dpToPx(xx+180), dpToPx(xy+45)) ;
        path2.lineTo(dpToPx(xx+180), dpToPx(xy+195));
        path2.lineTo(dpToPx(xx+240), dpToPx(xy+240));


        canvas.drawPath(path2, paint);

    }


    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_XXXHIGH));
        return px;
    }

}