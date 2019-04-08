package cursoandroid.com.reimarapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cursoandroid.com.reimarapp.Classes.Usuario;
import cursoandroid.com.reimarapp.Config.ConfiguracaoFirebase;
import cursoandroid.com.reimarapp.R;

public class LoginActivity extends AppCompatActivity {

    private Button botaoLogar;
    private EditText edtEmail;
    private EditText edtSenha;
    private String permissao;
    private CheckBox checkBoxPreferencias;
    private String usuarioLogado;


    private static final String ARQUIVO_PREFERENCIA ="ArqPref";
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final DatabaseReference usuarios = databaseReference.child("usuarios");

        botaoLogar = findViewById(R.id.btn_logar);
        edtEmail = findViewById(R.id.edt_login_email);
        edtSenha = findViewById(R.id.edt_login_senha);
        checkBoxPreferencias = findViewById(R.id.checkBox_preferencias);

        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();

        conferirPreferencias();

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                if(!email.isEmpty() && !senha.isEmpty()) {
                    //Valida o login e abre a MainActivity

                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    firebaseAuth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()
                    ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                abrirTelaPrincipal();
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "Erro ao autenticar o usuário" + task.getException(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }else if(email.isEmpty() || senha.isEmpty()){ //Se um dos campos não forem preenchidos exibe uma mensagem para o usuário e coloca o foco no campo não preenchido
                    Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    if(email.isEmpty()) {
                        edtEmail.requestFocus();}
                    else {
                        edtSenha.requestFocus();}
                }

                //se o checkbox estiver marcado salva preferências do usuário pra não ter que digitar o email novamente
                if(checkBoxPreferencias.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("senha", senha);
                    editor.apply();
                }else{     //caso seja desmarcado o checkbox limpa os registros no arquivo ArqPref
                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                }
            }
        });


    }
    //confere se no arquivo ArqPref tem dados salvos, define o campo email com os dados do arquivo
    //    //e mantém o checkbox ativado se tiverem os dados relevantes salvos
    public void conferirPreferencias(){
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(sharedPreferences.contains("email") && sharedPreferences.contains("senha")){
            edtEmail.setText(sharedPreferences.getString("email", ""));
            checkBoxPreferencias.setChecked(true);
        }
    }

    public void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
}
