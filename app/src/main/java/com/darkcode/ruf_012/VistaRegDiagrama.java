package com.darkcode.ruf_012;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by NativoLink on 2/2/17.
 */

public class VistaRegDiagrama extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     View rootView= inflater.inflate(R.layout.reg_paciente, container,false);

        return rootView;

    }
}
