package com.example.aluno.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import Util.DatabaseUtil;
import repository.PessoaRepository;

public class ItemPessoaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_pessoa);

        ImageButton btnDeletar = (ImageButton)findViewById(R.id.btnDeletar);
        btnDeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PessoaRepository repository = new PessoaRepository(getApplicationContext());
                View selec = getApplicationContext().(R.layout.activity_listar_pessoas);
                repository.excluir();
            Intent intentMainActivity = new Intent(getApplicationContext(),
                    MainActivity.class);
            startActivity(intentMainActivity);
            finish();
            }
        });

        ImageButton btnEditar = (ImageButton)findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });
    }
}
