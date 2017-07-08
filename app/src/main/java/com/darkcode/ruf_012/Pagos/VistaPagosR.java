package com.darkcode.ruf_012.Pagos;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.darkcode.ruf_012.MainActivity;
import com.darkcode.ruf_012.R;

import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NativoLink on 7/7/17.
 */

public class VistaPagosR extends Fragment {

    ListView lvresult;
    AdapterPagosR listAdapter;
    int day,moth,year;
    TextView tvFechaIni,tvFechaFin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.pagos_pacientes, container,false);
        lvresult = (ListView)rootView.findViewById(R.id.lvPagosPacientes);

        Button fecha_ini = (Button)rootView.findViewById(R.id.btnFechaIni);
        Button fecha_fin = (Button)rootView.findViewById(R.id.btnFechaFin);

        TextView paciente = (TextView)rootView.findViewById(R.id.tvPagosR);
        paciente.setText(((MainActivity)getContext()).getNOMBRES());


        tvFechaIni = (TextView)rootView.findViewById(R.id.tvFechaIni);
        tvFechaFin = (TextView)rootView.findViewById(R.id.tvFechaFin);




        fecha_ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                day =c.get(Calendar.DAY_OF_MONTH);
                moth =c.get(Calendar.MONTH);
                year =c.get(Calendar.YEAR);

                DatePickerDialog datepickerD = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvFechaIni.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                    }
                }
                ,day,moth,year);
                datepickerD.show();

            }
        });
        fecha_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                day =c.get(Calendar.DAY_OF_MONTH);
                moth =c.get(Calendar.MONTH);
                year =c.get(Calendar.YEAR);

                DatePickerDialog datepickerD = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvFechaFin.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                    }
                }
                        ,day,moth,year);
                datepickerD.show();

            }
        });

        RestAdapter restadpter = ((MainActivity)getContext()).getRestadpter();;
        PagoService servicio = restadpter.create(PagoService.class);

        servicio.getPagosR(((MainActivity)getContext()).getId_pacienteA(), new Callback<List<PagoR>>() {
            @Override
            public void success(List<PagoR> pagos, Response response) {
                listAdapter = new AdapterPagosR(getContext(), pagos);
                lvresult.setAdapter(listAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        return rootView;
    }
}
