package cursoandroid.com.reimarapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import cursoandroid.com.reimarapp.Classes.Usuario;
import cursoandroid.com.reimarapp.R;

public class LoginActivity extends AppCompatActivity {

    private Button botaoLogar;
    private EditText edtEmail;
    private EditText edtSenha;
    private String permissao;
    private CheckBox checkBoxPreferencias;

    private static final String ARQUIVO_PREFERENCIA ="ArqPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botaoLogar = findViewById(R.id.btn_logar);
        edtEmail = findViewById(R.id.edt_login_email);
        edtSenha = findViewById(R.id.edt_login_senha);
        checkBoxPreferencias = findViewById(R.id.checkBox_preferencias);

        conferirPreferencias();

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();

                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                if(!email.isEmpty() && !senha.isEmpty()) {
                    //Valida o login, determina no nível de acesso e envia a permissão através de um intent para a classe principal

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    if (email.equals("admin") && senha.equals("admin")) {
                        setPermissao("a");
                        intent.putExtra("permissao", getPermissao());
                        startActivity(intent);
                        finish();

                    } else if (email.equals("tecnico") && senha.equals("tecnico")) {
                        setPermissao("t");
                        intent.putExtra("permissao", getPermissao());
                        startActivity(intent);
                        finish();
                    }else { //caso os dados inseridos nos campos não sejam compatíveis exibe uma mensagem para o usuário,
                            //limpa os campos e coloca o foco no campo email
                        Toast.makeText(LoginActivity.this, "E-mail e/ou senha inválidos", Toast.LENGTH_SHORT).show();
                        edtEmail.setText("");
                        edtSenha.setText("");
                        edtEmail.requestFocus();
                    }
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
    //e mantém o checkbox ativado se tiverem os dados relevantes salvos
    public void conferirPreferencias(){
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(sharedPreferences.contains("email") && sharedPreferences.contains("senha")){
            edtEmail.setText(sharedPreferences.getString("email", ""));
            checkBoxPreferencias.setChecked(true);
        }
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
}
