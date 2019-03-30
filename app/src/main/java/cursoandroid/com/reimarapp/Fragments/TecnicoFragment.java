package cursoandroid.com.reimarapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cursoandroid.com.reimarapp.Activities.MainActivity;
//import cursoandroid.com.reimarapp.Activities.OrdensAbertasActivity;
import cursoandroid.com.reimarapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TecnicoFragment extends Fragment {

    private Button ordenAbertas;
    private Button orcamentosPendentes;

    public TecnicoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tecnico, container, false);

        ordenAbertas = (Button) v.findViewById(R.id.btn_tec_ordens_abertas);

        ordenAbertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getContext().getApplicationContext(), OrdensAbertasActivity.class);
                //startActivity(intent);
            }
        });

        return v;
    }

}
