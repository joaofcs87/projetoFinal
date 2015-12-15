package br.senac.pi.controlenota;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senac.pi.controlenota.domain.Instituicao;
import br.senac.pi.controlenota.domain.InstituicaoDB;

public class CadastraTurmaActivity extends AppCompatActivity {
    //joaoCod
    private SQLiteDatabase database;
    private InstituicaoDB instituicaoDB;
    private Spinner spinnerInstituicao;
    private String[] campos = {"instituicao"};
    private String instituicaoSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_turma);

        //joaoCod
        instituicaoDB = new InstituicaoDB(this);
        database = instituicaoDB.getReadableDatabase();
        Cursor cursorInstituicao = database.query("instituicoes", campos, null, null, null, null, "instituicao");
        cursorInstituicao.moveToFirst();

        ArrayList<String> nomes = new ArrayList<String>();

        while (!cursorInstituicao.isAfterLast()) {
            nomes.add(cursorInstituicao.getString(cursorInstituicao.getColumnIndexOrThrow("instituicao")));
            cursorInstituicao.moveToNext();
        }

        spinnerInstituicao = (Spinner) findViewById(R.id.spinnerInstituicao);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerInstituicao.setAdapter(spinnerArrayAdapter);
        //joaoCod
        spinnerInstituicao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                instituicaoSelecionada = adapterView.getItemAtPosition(posicao).toString();
                //Toast.makeText(getApplicationContext(), "Instituicao: " + instituicaoSelecionada, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
