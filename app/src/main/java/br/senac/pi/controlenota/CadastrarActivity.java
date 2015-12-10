package br.senac.pi.controlenota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        //joaoCod
        findViewById(R.id.btnCadastrarInstituicao).setOnClickListener(irCadastrarInstituicao());
        findViewById(R.id.btnCadastrarTurma).setOnClickListener(irCadastrarTurma());
        findViewById(R.id.btnCadastrarAluno).setOnClickListener(irCadastrarAluno());
    }

    //joaoCod
    private View.OnClickListener irCadastrarInstituicao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarActivity.this,CadastraInstituicaoActivity.class);
                startActivity(intent);
            }
        };
    }

    //joaoCod
    private View.OnClickListener irCadastrarTurma(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarActivity.this, CadastraTurmaActivity.class);
                startActivity(intent);
            }
        };
    }

    //joaoCod
    private View.OnClickListener irCadastrarAluno(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarActivity.this, CadastraAlunoActivity.class);
                startActivity(intent);
            }
        };
    }
}
