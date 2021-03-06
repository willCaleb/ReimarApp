package cursoandroid.com.reimarapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cursoandroid.com.reimarapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrcamentosPendentesFragment extends Fragment implements View.OnClickListener{


    public OrcamentosPendentesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_orcamentos_pendentes, container, false);
        ImageView btnVoltar = v.findViewById(R.id.btn_voltar_orc_pen);

        btnVoltar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (v.getId()){
            case R.id.btn_voltar_orc_pen:
                AdministradorFragment administradorFragment = new AdministradorFragment();

                transaction.replace(R.id.fragment_container, administradorFragment);
                transaction.commit();
        }
    }
}
