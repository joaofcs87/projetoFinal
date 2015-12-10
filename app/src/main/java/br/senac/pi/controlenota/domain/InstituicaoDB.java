package br.senac.pi.controlenota.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aluno on 09/12/2015.
 */
public class InstituicaoDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    private static final String NOME_BANCO = "controlenotas.sqlite";
    private static final int VERSAO_BANCO = 1;

    public InstituicaoDB (Context context){

        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a tabela 'instituicoes'");
        db.execSQL("create table if not exists instituicoes(" +
                "id_instituicao integer primary key autoincrement," +
                "instituicao text unique" +
                ")");
        Log.d(TAG, "Tabela criada com sucesso!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
