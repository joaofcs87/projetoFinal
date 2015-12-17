package br.senac.pi.controlenota;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import br.senac.pi.controlenota.domain.ConexaoDB;

public class CadastraAlunoActivity extends AppCompatActivity {

    //joaoCod
    private SQLiteDatabase database;
    private ConexaoDB conexaoDB;
    private Spinner spinnerInstituicao, spinnerTurma;
    private String instituicaoSelecionada, turmaSelecionada;
    private int idInstituicaoSelecionada, idTurmaSelecionada;
    private String[] camposInstituicao = {"instituicao"};
    private String[] id_instituicao = {"_id"};
    private String[] camposTurma = {"turma"};
    private String[] idTurma = {"_id"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_aluno);
        //joaoCode
        conexaoDB = new ConexaoDB(this);
        database = conexaoDB.getWritableDatabase();
        Cursor cursorInstituicao = database.query("instituicoes", camposInstituicao, null, null, null, null, "instituicao");
        cursorInstituicao.moveToFirst();

        ArrayList<String> nomes = new ArrayList<String>();

        while (!cursorInstituicao.isAfterLast()) {
            nomes.add(cursorInstituicao.getString(cursorInstituicao.getColumnIndexOrThrow("instituicao")));
            cursorInstituicao.moveToNext();
        }
        cursorInstituicao.close();

        spinnerInstituicao = (Spinner) findViewById(R.id.spinnerInstituicao);
        findViewById(R.id.btnCadAluno).setOnClickListener(cadastrarAluno());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerInstituicao.setAdapter(spinnerArrayAdapter);
        //joaoCode
        spinnerInstituicao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                instituicaoSelecionada = adapterView.getItemAtPosition(posicao).toString();
                //carregar o spinnerTurma aqui
                Cursor cursorIdInstituicaoSelecionada = database.query("instituicoes", id_instituicao, "instituicao = '" + instituicaoSelecionada + "'", null, null, null, null);
                cursorIdInstituicaoSelecionada.moveToFirst();
                idInstituicaoSelecionada = Integer.parseInt(cursorIdInstituicaoSelecionada.getString(cursorIdInstituicaoSelecionada.getColumnIndexOrThrow("_id")));
                Cursor cursorTurma = database.query("turmas", camposTurma, "id_instituicao = '" + idInstituicaoSelecionada + "'", null, null, null, null);
                cursorTurma.moveToFirst();
                ArrayList<String> nomes = new ArrayList<String>();

                while (!cursorTurma.isAfterLast()) {
                    nomes.add(cursorTurma.getString(cursorTurma.getColumnIndexOrThrow("turma")));
                    cursorTurma.moveToNext();
                }
                cursorTurma.close();

                spinnerTurma = (Spinner) findViewById(R.id.spinnerTurna);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CadastraAlunoActivity.this, android.R.layout.simple_spinner_dropdown_item, nomes);
                ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinnerTurma.setAdapter(spinnerArrayAdapter);
                spinnerTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                        turmaSelecionada = adapterView.getItemAtPosition(posicao).toString();

                        Cursor cursorIdTurmaSelecionada = database.query("turmas", idTurma, "turma = '" + turmaSelecionada + "'", null, null, null, null);
                        cursorIdTurmaSelecionada.moveToFirst();
                        idTurmaSelecionada = Integer.parseInt(cursorIdTurmaSelecionada.getString(cursorIdTurmaSelecionada.getColumnIndexOrThrow("_id")));
                        cursorIdTurmaSelecionada.close();
                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }

    //joaoCode
    private View.OnClickListener cadastrarAluno() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtMatriculaAluno = (EditText) findViewById(R.id.edtMatriculaAluno);
                EditText edtNomeAluno = (EditText) findViewById(R.id.edtNomeAluno);
                ContentValues dadosAluno = new ContentValues();

                dadosAluno.put("id_instituicao",idInstituicaoSelecionada);
                dadosAluno.put("id_turma", idTurmaSelecionada);
                dadosAluno.put("matricula", Integer.parseInt(edtMatriculaAluno.getText().toString()));
                dadosAluno.put("aluno", edtNomeAluno.getText().toString());

                long inserir = database.insert("alunos", null, dadosAluno);
                if (inserir != 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.cad_sucesso), Toast.LENGTH_LONG).show();
                    edtMatriculaAluno.setText("");
                    edtNomeAluno.setText("");

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.cad_erro), Toast.LENGTH_LONG).show();
                }

            }
        };
    }
}