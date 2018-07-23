package com.example.aluno.listview;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

import Util.DatabaseUtil;
import Util.ListViewAdapter;
import model.PessoaModel;
import repository.PessoaRepository;

public class ListarPessoasActivity extends AppCompatActivity {

    ListViewAdapter adapter;
    public Integer selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoas);
        preencherLista();
        //Aqui é aonde adicionamos nosso filtro no edittext.
        EditText editText = (EditText) findViewById(R.id.txtBusca);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //quando o texto é alterado chamamos o filtro.
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final ListView lista = (ListView) findViewById(R.id.listViewPessoas);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selected = ((PessoaModel)adapter.getItem(position)).getCodigo();
            }
        });

    }

    private void preencherLista()
    {
        PessoaRepository pr = new PessoaRepository(this);
        ListView listaPessoas = findViewById(R.id.listViewPessoas);

        adapter = new ListViewAdapter(this, pr.selecionarPessoas(""));
        ListView lista = (ListView) findViewById(R.id.listViewPessoas);
        lista.setAdapter(adapter);

        //ArrayAdapter<PessoaModel> arrayItens = new ArrayAdapter<PessoaModel>(this,android.R.layout.simple_expandable_list_item_1, pr.selecionarPessoas(txtBusca));
        //listaPessoas.setAdapter(arrayItens);
        //listaPessoas.setAdapter(pr.buscar(txtBusca));

    }
}
