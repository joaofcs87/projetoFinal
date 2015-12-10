package br.senac.pi.controlenota;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.senac.pi.controlenota.domain.InstituicaoDB;

public class ListaInstituicaoActivity extends AppCompatActivity {

    //joaoCod
    private InstituicaoDB instituicaoDB;
    private SQLiteDatabase db;
    private CursorAdapter dataSource;
    private ListView listView;
    private String[] dados = {"_id", "instituicao"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_instituicao);
        //joaoCod
        instituicaoDB = new InstituicaoDB(this);
        db = instituicaoDB.getWritableDatabase();
        findViewById(R.id.btnVisualizarInstituicao).setOnClickListener(listarInstituicao());
        listView = (ListView) findViewById(R.id.listViewInstituicao);
    }

    //joaoCod
    private View.OnClickListener listarInstituicao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor instituicoes = db.query("instituicoes", dados, null, null, null, null, null);
                if (instituicoes.getCount() > 0){
                    dataSource = new SimpleCursorAdapter(ListaInstituicaoActivity.this, R.layout.item_lista_instituicao, instituicoes, dados, new int[]{R.id.txtIdInstituicao, R.id.txtNomeInstituicao});
                    listView.setAdapter(dataSource);
                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.sem_registros),Toast.LENGTH_LONG).show();
                }
            }
        };
    }
}
