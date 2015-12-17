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

public class LancaNotasActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private ConexaoDB conexaoDB;
    private Spinner spinnerAluno;
    private String[] dadosAluno = {"aluno"};
    private String[] idAluno = {"_id"};
    private String alunoSelecionado;
    private int idAlunoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanca_notas);

        //joaoCod
        conexaoDB = new ConexaoDB(this);
        database = conexaoDB.getWritableDatabase();
        Cursor cursorAluno = database.query("alunos", dadosAluno, null, null, null, null, "aluno");
        cursorAluno.moveToFirst();

        ArrayList<String> nomes = new ArrayList<String>();

        while (!cursorAluno.isAfterLast()) {
            nomes.add(cursorAluno.getString(cursorAluno.getColumnIndexOrThrow("aluno")));
            cursorAluno.moveToNext();
        }
        cursorAluno.close();

        spinnerAluno = (Spinner) findViewById(R.id.spinnerAluno);
        findViewById(R.id.btnLancaNota).setOnClickListener(lancarNotas());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerAluno.setAdapter(spinnerArrayAdapter);
        //joaoCod
        spinnerAluno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                alunoSelecionado = adapterView.getItemAtPosition(posicao).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //joaoCode
    private View.OnClickListener lancarNotas() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ContentValues dadosNota = new ContentValues();
                Cursor cursorIdAluno = database.query("alunos", idAluno, "aluno = '" + alunoSelecionado + "'", null, null, null, null);
                cursorIdAluno.moveToFirst();
                idAlunoSelecionado = Integer.parseInt(cursorIdAluno.getString(cursorIdAluno.getColumnIndexOrThrow("_id")));
                cursorIdAluno.close();
                EditText edtPrimeiraNota = (EditText) findViewById(R.id.edtPrimeiraNota);
                EditText edtSegundaNota = (EditText) findViewById(R.id.edtSegundaNota);
                EditText edtTerceiraNota = (EditText) findViewById(R.id.edtTerceiraNota);
                dadosNota.put("id_aluno", idAlunoSelecionado);
                dadosNota.put("nota1", Double.parseDouble(edtPrimeiraNota.getText().toString()));
                dadosNota.put("nota2", Double.parseDouble(edtSegundaNota.getText().toString()));
                dadosNota.put("nota3", Double.parseDouble(edtTerceiraNota.getText().toString()));
                long inserir = database.insert("notas", null, dadosNota);
                if (inserir != 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.cad_sucesso), Toast.LENGTH_LONG).show();
                    edtPrimeiraNota.setText("");
                    edtSegundaNota.setText("");
                    edtTerceiraNota.setText("");
                    edtPrimeiraNota.requestFocus();

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.cad_erro), Toast.LENGTH_LONG).show();
                }
            }
        };

    }
}
