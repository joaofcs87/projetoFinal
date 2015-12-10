package br.senac.pi.controlenota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //joaoCod
        findViewById(R.id.btnCadastrar).setOnClickListener(intentCadastrar());
        findViewById(R.id.btnPesquisar).setOnClickListener(intentPesquisar());
        findViewById(R.id.btnLancarNotas).setOnClickListener(irLancarNotas());

    }


    //joaoCod
    private View.OnClickListener intentCadastrar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CadastrarActivity.class);
                startActivity(intent);
            }
        };
    }

    //joaoCod
    private View.OnClickListener intentPesquisar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PesquisarActivity.class);
                startActivity(intent);
            }
        };
    }

    //joaoCod
    private View.OnClickListener irLancarNotas(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LancaNotasActivity.class);
                startActivity(intent);
            }
        };
    }
}