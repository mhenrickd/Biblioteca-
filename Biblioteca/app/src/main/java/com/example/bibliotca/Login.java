package com.example.bibliotca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private TextView txtUser;
    private TextView txtPassord;
    private Button btnLogin;
    private ArrayList<String> users = new ArrayList<String> ();
    private String password = "senha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        users.add("matheus");
        users.add("gabriel");
        users.add("arthur");
        users.add("tiago");
        users.add("marcelo");

        txtPassord = (TextView) findViewById(R.id.txtpassword);
        txtUser = (TextView) findViewById(R.id.txtuser);
        btnLogin = (Button) findViewById(R.id.btnlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                if (users.contains(txtUser.getText().toString()) && txtPassord.getText().toString().equals(password)) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(v.getContext(), "Usuário ou Senha incorretos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}