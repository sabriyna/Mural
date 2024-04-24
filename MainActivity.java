package com.example.login2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText editEmail, editSenha;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnLogin = findViewById(R.id.btnLogin);

        editEmail.requestFocus();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){

                    mensagem("Preencha os campos");

                }else if(!verificarEmail(email)){
                    mensagem("E-mail inválido");
                }
                else if(validaEmailSenha(email, senha)){
                    Intent intent = new Intent(getApplicationContext(), Principal.class);
                    intent.putExtra("enviaEmail", email);
                    startActivity(intent);

                }else {
                    mensagem("Longin Incorreto!");
                }

            }
        });

    }

    private boolean validaEmailSenha(String email, String senha) {
        boolean sucesso=false;

        if(email.equals("Login@dominio.com") && senha.equals("12345")){
            sucesso=true;
        }else if (email.equals("Adm@dominio.com") && senha.equals("adm")){
            sucesso=true;
        }

        return sucesso;
    }

    private boolean verificarEmail(String email) {
        Pattern patterns = Patterns.EMAIL_ADDRESS;
        return patterns.matcher(email).matches();
    }

    private void mensagem(String info){
        Toast.makeText(MainActivity.this, info, Toast.LENGTH_LONG).show();
        editEmail.requestFocus();
    }
}