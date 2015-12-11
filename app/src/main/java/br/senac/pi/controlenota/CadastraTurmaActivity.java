package br.senac.pi.controlenota;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.senac.pi.controlenota.domain.Instituicao;
import br.senac.pi.controlenota.domain.InstituicaoDB;

public class CadastraTurmaActivity extends AppCompatActivity {
    //joaoCod
    private SQLiteDatabase database;
    private InstituicaoDB instituicaoDB;
    private Instituicao instituicao;
    private Spinner spn1;
    //private List<String> nomes = new ArrayList<String>();
    //private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_turma);

        //joaoCod
        instituicaoDB = new InstituicaoDB(this);
        instituicao = new Instituicao();
        List<Instituicao> listaResultado = instituicaoDB.selectInstituicoes();
        /*nomes.add("Liceu Piauiense");
        nomes.add("Escola Municipal Machado de Assis");
        nomes.add("Instituto Federal de Educação Tecnológica");
        nomes.add("Unidade Escolar Angelim");
        */
        spn1 = (Spinner) findViewById(R.id.spinnerInstituicao);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, listaResultado);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn1.setAdapter(spinnerArrayAdapter);


    }
}
