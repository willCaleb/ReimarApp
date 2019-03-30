package cursoandroid.com.reimarapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import cursoandroid.com.reimarapp.Classes.Usuario;
import cursoandroid.com.reimarapp.Fragments.AdministradorFragment;
import cursoandroid.com.reimarapp.Fragments.SolicitacaoVisitaFragment;
import cursoandroid.com.reimarapp.Fragments.TecnicoFragment;

import cursoandroid.com.reimarapp.R;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializa o fragmentManager que é usado pra navegar no app através de fragmentos sem que tenha que abrir uma nova activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Recebe as informações passadas pelo intent da classe que enviou
        Intent intent = getIntent();
        String permissao = intent.getStringExtra("permissao");

        //verifica o tipo de permissão e de acordo com o valor define qual vai ser o fragmento dentro do layoutContainer
        if(permissao.equals("t")) {

            TecnicoFragment tecnicoFragment = new TecnicoFragment();

            fragmentTransaction.add(R.id.fragment_container, tecnicoFragment);
            fragmentTransaction.commit();


        }else if(permissao.equals("a")){

            AdministradorFragment administradorFragment = new AdministradorFragment();

            fragmentTransaction.add(R.id.fragment_container, administradorFragment);
            fragmentTransaction.commit();
        }



    }



    public void abrirFragments() {
       /* SolicitacaoVisitaFragment solicitacaoVisitaFragment = new SolicitacaoVisitaFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, solicitacaoVisitaFragment);
        fragmentTransaction.commit();
        //Log.i("Teste", "Botão clicado");*/

       //Toast.makeText(MainActivity.this, "funciona bosta", Toast.LENGTH_SHORT).show();
    }


}
