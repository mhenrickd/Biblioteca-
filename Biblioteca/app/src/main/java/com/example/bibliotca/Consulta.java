package com.example.bibliotca;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Consulta extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listViewLivros;
    private LivroAdapter livroAdapter;
    private ArrayList<Livro> livros;
    private Livro livroEditado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        listViewLivros = (ListView)findViewById(R.id.listView);
        listViewLivros.setOnItemClickListener(this);

        livros = new Livro(this).getUsuarios();
        livroAdapter = new LivroAdapter(this, livros);
        listViewLivros.setAdapter(livroAdapter);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Livro livro = livros.get(position);
        Intent intent = new Intent(this, Cadastro.class);
        intent.putExtra("consulta",livro.getCodigo());
        livroEditado = livro;
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (livroEditado != null){
            livroEditado.carregaUsuarioPeloCodigo(livroEditado.getCodigo());
            if (livroEditado.isExcluir())
                livros.remove(livroEditado);
            livroAdapter.notifyDataSetChanged();
        }
    }
}