package com.example.bibliotca;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonConsultar;
    private Button buttonIncluir;
    private TextView txtFrases;
    private ArrayList<String> frases = new ArrayList<String> ();
    private Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frases.add(" 'Só se vê bem com o coração, o essencial é invisível aos olhos'.\nO Pequeno Príncipe, Antoine de Saint-Exupéry");
        frases.add(" 'Quando acordei hoje de manhã, eu sabia quem eu era, mas acho que já mudei muitas vezes desde então.'\nAlice no País das maravilhas, Lewis Carroll");
        frases.add(" 'As coisas têm vida própria. Tudo é questão de despertar a sua alma'.\n Cem Anos de Solidão, Gabriel García Márquez");
        frases.add(" 'Tudo depende do tipo de lente que você utiliza para ver as coisas'.\nO Mundo de Sofia, Jostein Gaarder");
        frases.add(" 'Quando os pés estão corretos, todo o resto nos acompanha'.\nAs Crônicas de Nárnia: O leão, a feiticeira e o guarda-roupa, C. S. Lewis");

        buttonConsultar = (Button)findViewById(R.id.btnConsultaUsuario);
        buttonIncluir = (Button)findViewById(R.id.btnIncluiUsuario);
        txtFrases = (TextView)findViewById(R.id.txtfrases);

        int index = r.nextInt(frases.size());
        txtFrases.setText(frases.get(index));
        buttonIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }
        });

        buttonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Consulta.class);
                startActivity(intent);
            }
        });
    }
}