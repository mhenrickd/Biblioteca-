package com.example.bibliotca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Cadastro extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText editTextTitulo;
    private EditText editTextAutor;
    private Spinner editTextSenha;
    private Button buttonExcluir;
    private Button buttonSalvar;
    private TextView labelStatus;
    private String text = "";

    private final Livro usuario = new Livro(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextTitulo = (EditText)findViewById(R.id.titulo);
        editTextAutor = (EditText)findViewById(R.id.autor);
        editTextSenha = (Spinner)findViewById(R.id.spinner);
        labelStatus = (TextView) findViewById(R.id.textViewStatus);
        buttonExcluir = (Button)findViewById(R.id.btnExcluir);
        buttonSalvar = (Button)findViewById(R.id.btnSalvar);

        buttonExcluir.setOnClickListener(this);
        buttonSalvar.setOnClickListener(this);
        editTextSenha.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status_spiner, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        editTextSenha.setAdapter(adapter);

        if (getIntent().getExtras() != null){
            setTitle(getString(R.string.TituloEditando));
            int codigo = getIntent().getExtras().getInt("consulta");
            usuario.carregaUsuarioPeloCodigo(codigo);
            editTextTitulo.setText(usuario.getTitulo().toString());
            editTextAutor.setText(usuario.getAutor().toString());
        }else{
            setTitle(getString(R.string.TituloIncluindo));
        }

        buttonExcluir.setEnabled(true);
        if (usuario.getCodigo() == -1)
            buttonExcluir.setEnabled(false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnExcluir:  {
                usuario.excluir();
                finish();
                break;
            }
            case R.id.btnSalvar: {
                boolean valido = true;
                usuario.setTitulo(editTextTitulo.getText().toString().trim());
                usuario.setAutor(editTextAutor.getText().toString().trim());
                usuario.setStatus(text);
//                carregaImagem();

                if (usuario.getTitulo().equals("")){
                    editTextTitulo.setError(getString(R.string.Obrigatório));
                    valido = false;
                }

                if (usuario.getAutor().equals("")){
                    editTextAutor.setError(getString(R.string.Obrigatório));
                    valido = false;
                }
//
                if (usuario.getStatus().equals("")){
                    labelStatus.setTextColor(R.color.red);
                    valido = false;
                }

                if (valido){
                    usuario.salvar();
                    finish();
                }
                break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();
        usuario.setStatus(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}