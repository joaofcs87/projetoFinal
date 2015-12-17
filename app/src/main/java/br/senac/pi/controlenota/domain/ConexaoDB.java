package br.senac.pi.controlenota.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 09/12/2015.
 */
public class ConexaoDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    private static final String NOME_BANCO = "controlenotasdb.sqlite";
    private static final int VERSAO_BANCO = 3;

    public ConexaoDB(Context context){

        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando tabelas do banco de dados");
        db.execSQL("create table if not exists instituicoes(" +
                "_id integer primary key autoincrement," +
                "instituicao text not null" +
                ");" +
                "create table if not exists turmas(" +
                "_id integer primary key autoincrement," +
                "id_instituicao integer not null," +
                "turma text not null," +
                "foreign key(id_instituicao) references instituicoes (_id)" +
                ");" +
                "create table if not exists alunos(" +
                "_id integer primary key autoincrement," +
                "matricula integer not null," +
                "id_instituicao integer not null," +
                "id_turma integer not null," +
                "aluno text not null," +
                "foreign key (id_instituicao) references instituicoes(_id)," +
                "foreign key (id_turma) references turmas (_id)" +
                ");" +
                "create table if not exists notas(" +
                "_id integer primary key autoincrement," +
                "id_aluno integer not null," +
                "nota1 decimal," +
                "nota2 decimal," +
                "nota3 decimal," +
                "foreign key (id_aluno) references alunos (_id)" +
                ");");
        Log.d(TAG, "Tabelas criada com sucesso!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("ALTER TABLE instituicoes CHANGE id_instituicao _id integer primary key autoincrement");
    }


}
