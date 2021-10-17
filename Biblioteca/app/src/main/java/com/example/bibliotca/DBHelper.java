package com.example.bibliotca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static String NOME = "atividade02.db";
    private static int VERSAO = 1;

    public DBHelper(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE [usuario] (\n" +
                "[codigo] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "[titulo] varchar(60)  NOT NULL,\n" +
                "[autor] VARCHAR(60)  NOT NULL,\n" +
                "[status] varCHAR(60)  NOT NULL,\n" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
