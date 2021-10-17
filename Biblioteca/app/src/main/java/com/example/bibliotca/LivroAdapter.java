package com.example.bibliotca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LivroAdapter extends ArrayAdapter<Livro> {

    private ArrayList<Livro> usuarios;

    public LivroAdapter(@NonNull Context context, @NonNull ArrayList<Livro> usuarios) {
        super(context, 0, usuarios);
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Livro usuario = usuarios.get(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.celula_livro,null);

        TextView textViewNome = (TextView)convertView.findViewById(R.id.textViewTitulo);
        TextView textViewEmail = (TextView)convertView.findViewById(R.id.textViewAutor);
        TextView textViewStatus = (TextView)convertView.findViewById(R.id.textViewStatus);

        textViewNome.setText(usuario.getTitulo().toString());
        textViewEmail.setText(usuario.getAutor().toString());
        textViewStatus.setText(usuario.getStatus().toString());


        return  convertView;
    }
}
