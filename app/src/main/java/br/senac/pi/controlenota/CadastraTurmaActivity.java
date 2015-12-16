package br.senac.pi.controlenota;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senac.pi.controlenota.domain.Instituicao;
import br.senac.pi.controlenota.domain.InstituicaoDB;
import br.senac.pi.controlenota.domain.TurmaDB;

public class CadastraTurmaActivity extends AppCompatActivity {
    //joaoCod
    private SQLiteDatabase database, dbTurma;
    private InstituicaoDB instituicaoDB;
    private TurmaDB turmaDB;
    private Spinner spinnerInstituicao;
    private String[] campos = {"instituicao"};
    private String[] id_instituicao = {"_id"};
    private String instituicaoSelecionada;
    private int idInstituicaoSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_turma);

        //joaoCod
        instituicaoDB = new InstituicaoDB(this);
        turmaDB = new TurmaDB(this);
        database = instituicaoDB.getReadableDatabase();
        Cursor cursorInstituicao = database.query("instituicoes", campos, null, null, null, null, "instituicao");
        cursorInstituicao.moveToFirst();

        ArrayList<String> nomes = new ArrayList<String>();

        while (!cursorInstituicao.isAfterLast()) {
            nomes.add(cursorInstituicao.getString(cursorInstituicao.getColumnIndexOrThrow("instituicao")));
            cursorInstituicao.moveToNext();
        }
        cursorInstituicao.close();

        spinnerInstituicao = (Spinner) findViewById(R.id.spinnerInstituicao);
        findViewById(R.id.btnCadTurma).setOnClickListener(cadastrarTurma());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerInstituicao.setAdapter(spinnerArrayAdapter);
        //joaoCod
        spinnerInstituicao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                instituicaoSelecionada = adapterView.getItemAtPosition(posicao).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //joaoCod
    private View.OnClickListener cadastrarTurma() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbTurma = turmaDB.getWritableDatabase();
                ContentValues valores = new ContentValues();
                Cursor cursorIdInstituicaoSelecionada = database.query("instituicoes", id_instituicao, "instituicao = '" + instituicaoSelecionada + "'", null, null, null, null);
                cursorIdInstituicaoSelecionada.moveToFirst();
                idInstituicaoSelecionada = Integer.parseInt(cursorIdInstituicaoSelecionada.getString(cursorIdInstituicaoSelecionada.getColumnIndexOrThrow("_id")));
                cursorIdInstituicaoSelecionada.close();
                EditText edtTurma = (EditText) findViewById(R.id.edtTurma);
                valores.put("id_instituicao", idInstituicaoSelecionada);
                valores.put("turma", edtTurma.getText().toString());
                long inserir = dbTurma.insert("turmas", null, valores);
                if (inserir != 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.cad_sucesso), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.cad_erro), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

}
