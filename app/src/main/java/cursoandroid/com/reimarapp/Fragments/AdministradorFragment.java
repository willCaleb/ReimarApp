package cursoandroid.com.reimarapp.Fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cursoandroid.com.reimarapp.Activities.MainActivity;
import cursoandroid.com.reimarapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdministradorFragment extends Fragment implements View.OnClickListener{

    private Button solicitacaoVisita;
    private Button orcamentosPendentes;
    private Button orcamentosConcluidos;
    private Button orcamentosRecusados;
    private Button emAnalise;
    private Button ordensAbertas;


    public AdministradorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrador, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        solicitacaoVisita = (Button) view.findViewById(R.id.btn_solicitacao_visita);
        orcamentosPendentes = (Button) view.findViewById(R.id.btn_orcamentos_pendentes);
        ordensAbertas = (Button) view.findViewById(R.id.btn_admin_ordens_abertas);
        orcamentosConcluidos = (Button) view.findViewById(R.id.btn_orcamentos_concluidos);
        orcamentosRecusados = (Button) view.findViewById(R.id.btn_orcamentos_recusados);
        emAnalise = view.findViewById(R.id.btn_em_analise);

        solicitacaoVisita.setOnClickListener(this);
        orcamentosPendentes.setOnClickListener(this);
        ordensAbertas.setOnClickListener(this);
        orcamentosConcluidos.setOnClickListener(this);
        orcamentosRecusados.setOnClickListener(this);
        emAnalise.setOnClickListener(this);

    }


    @Override
    public void onClick(View v){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        SolicitacaoVisitaFragment solicitacaoVisitaFragment = new SolicitacaoVisitaFragment();
        OrcamentosPendentesFragment orcamentosPendentesFragment = new OrcamentosPendentesFragment();
        OrdensAbertasFragment ordensAbertasFragment = new OrdensAbertasFragment();
        OrcamentosConcluidosFragment orcamentosConcluidosFragment = new OrcamentosConcluidosFragment();
        OrcamentosRecusadosFragment orcamentosRecusadosFragment = new OrcamentosRecusadosFragment();
        EmAnaliseFragment emAnaliseFragment = new EmAnaliseFragment();

        switch (v.getId()){

            case R.id.btn_solicitacao_visita:
                transaction.replace(R.id.fragment_container, solicitacaoVisitaFragment);
                transaction.commit();
                break;
            case R.id.btn_orcamentos_pendentes:
                transaction.replace(R.id.fragment_container, orcamentosPendentesFragment);
                transaction.commit();
                break;
            case R.id.btn_admin_ordens_abertas:
                transaction.replace(R.id.fragment_container, ordensAbertasFragment);
                transaction.commit();
                break;
            case R.id.btn_orcamentos_concluidos:
                transaction.replace(R.id.fragment_container, orcamentosConcluidosFragment);
                transaction.commit();
                break;
            case R.id.btn_orcamentos_recusados:
                transaction.replace(R.id.fragment_container, orcamentosRecusadosFragment);
                transaction.commit();
                break;
            case R.id.btn_em_analise:
                transaction.replace(R.id.fragment_container, emAnaliseFragment);
                transaction.commit();


        }

    }



}
