package br.senac.pi.controlenota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PesquisarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        //joaoCod
        findViewById(R.id.btnRelatorioPorInstituicao).setOnClickListener(irRelatorioInstituicao());
        findViewById(R.id.btnRelatorioPorTurma).setOnClickListener(irRelatorioTurma());
        findViewById(R.id.btnRelatorioPorAluno).setOnClickListener(irRelatorioAluno());
        findViewById(R.id.btnRelatorioPorSituacao).setOnClickListener(irRelatorioSituacao());
    }
    //joaoCod
    private View.OnClickListener irRelatorioInstituicao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesquisarActivity.this, RelatorioInstituicaoActivity.class);
                startActivity(intent);
            }
        };
    }

    //joaoCod
    private View.OnClickListener irRelatorioTurma(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesquisarActivity.this, RelatorioTurmaActivity.class);
                startActivity(intent);
            }
        };
    }

    //joaoCod
    private View.OnClickListener irRelatorioAluno(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesquisarActivity.this, RelatorioAlunoActivity.class);
                startActivity(intent);
            }
        };
    }

    //joaoCod
    private View.OnClickListener irRelatorioSituacao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesquisarActivity.this, RelatorioSituacaoActivity.class);
                startActivity(intent);
            }
        };
    }
}