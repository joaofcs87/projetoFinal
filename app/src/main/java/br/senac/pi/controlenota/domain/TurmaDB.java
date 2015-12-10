package br.senac.pi.controlenota.domain;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

/**
 * Created by Aluno on 10/12/2015.
 */
public class TurmaDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    private static final String NOME_BANCO = "controlenotas.sqlite";
    private static final int VERSAO_BANCO = 1;

    public TurmaDB (Context context){
        super(context,NOME_BANCO, null, VERSAO_BANCO);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "Criando a tabela 'turmas'");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS turmas(" +
                "id_turma integer primary key autoincrement," +
                "id_instituicao integer not null," +
                "turma text not null," +
                "FOREIGN KEY(id_instituicao) REFERENCES instituicoes(id_instituicao)" +
                ")");
        Log.d(TAG, "Tabela criada com sucesso!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
