package com.example.bibliotca;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class Livro {

    private int codigo;
    private String titulo;
    private String autor;
    private String status;
    private boolean excluir;
    private Context context;

    public Livro(Context context){
        this.context = context;
        codigo = -1;
    }

    public int getCodigo() {
        return codigo;
    }
    public String getTitulo() { return titulo; }
    public String getStatus() { return status; }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) { this.autor = autor; }
    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isExcluir() {
        return excluir;
    }
    public void setExcluir(boolean excluir) { this.excluir = excluir; }

    public boolean excluir(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.delete("usuario","codigo = ?",new String[]{String.valueOf(codigo)});

            excluir = true;

            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
    }

    public boolean salvar(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            String sql = "";
            if (codigo == -1){
                sql = "INSERT INTO usuario (nome,email,senha,imagem) VALUES (?,?,?,?)";
            }else{
                sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, imagem = ? WHERE codigo = ?";
            }
            sqLiteDatabase.beginTransaction();
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, titulo);
            sqLiteStatement.bindString(2, autor);
            sqLiteStatement.bindString(3, status);
            if (codigo != -1)
                sqLiteStatement.bindString(5,String.valueOf(codigo));
            sqLiteStatement.executeInsert();
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
    }

    @SuppressLint("Range")
    public ArrayList<Livro> getUsuarios(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<Livro> usuarios = new ArrayList<>();
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("usuario",null,null,null,null,null,null);
            while (cursor.moveToNext()){
                Livro usuario = new Livro(context);
                usuario.codigo = cursor.getInt(cursor.getColumnIndex("codigo"));
                usuario.titulo = cursor.getString(cursor.getColumnIndex("nome"));
                usuario.status = cursor.getString(cursor.getColumnIndex("senha"));
                usuario.setAutor(cursor.getString(cursor.getColumnIndex("email")));
                usuarios.add(usuario);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
        return usuarios;
    }

    @SuppressLint("Range")
    public void carregaUsuarioPeloCodigo(int codigo){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("usuario",null,"codigo = ?",new String[]{String.valueOf(codigo)},null,null,null);
            excluir = true;
            while (cursor.moveToNext()){
                this.codigo = cursor.getInt(cursor.getColumnIndex("codigo"));
                titulo = cursor.getString(cursor.getColumnIndex("nome"));
                status = cursor.getString(cursor.getColumnIndex("senha"));
                setAutor(cursor.getString(cursor.getColumnIndex("email")));
                excluir = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }
    }
}
