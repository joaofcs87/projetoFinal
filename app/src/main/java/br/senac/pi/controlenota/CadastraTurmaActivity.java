package br.senac.pi.controlenota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CadastraTurmaActivity extends AppCompatActivity {
    private Spinner spn1;
    private List<String> nomes = new ArrayList<String>();
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_turma);

        //joaoCod
        nomes.add("Liceu Piauiense");
        nomes.add("Escola Municipal Machado de Assis");
        nomes.add("Instituto Federal de Educação Tecnológica");
        nomes.add("Unidade Escolar Angelim");

        spn1 = (Spinner) findViewById(R.id.spinnerInstituicao);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn1.setAdapter(spinnerArrayAdapter);


    }
}
