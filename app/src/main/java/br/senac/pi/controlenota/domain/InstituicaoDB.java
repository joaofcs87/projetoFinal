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
public class InstituicaoDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    private static final String NOME_BANCO = "controlenotas1.sqlite";
    private static final int VERSAO_BANCO = 2;

    public InstituicaoDB (Context context){

        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a tabela 'instituicoes'");
        db.execSQL("create table if not exists instituicoes(" +
                "_id integer primary key autoincrement," +
                "instituicao text unique" +
                ")");
        Log.d(TAG, "Tabela criada com sucesso!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("ALTER TABLE instituicoes CHANGE id_instituicao _id integer primary key autoincrement");
    }

    //joaoCod
    public List<Instituicao> selectInstituicoes(){
        SQLiteDatabase db = getWritableDatabase();
        try{

            //select * from instituicoes
            Cursor cursor = db.query("instituicoes", null, null, null, null, null, null, null);
            return toList(cursor);

        }finally {

            db.close();

        }
    }

    //joaoCod

    public List<Instituicao> toList (Cursor cursor){

        List<Instituicao> instituicoes = new ArrayList<Instituicao>();
        if(cursor.moveToFirst()){
            do{
                Instituicao instituicao = new Instituicao();
                instituicoes.add(instituicao);
                //recupera os atributos de instituicao
                instituicao.setId(cursor.getLong(cursor.getColumnIndex("id_instituicao")));
                instituicao.setInstituicao(cursor.getString(cursor.getColumnIndex("instituicao")));

            } while(cursor.moveToNext());

        }
        return instituicoes;
    }
}
