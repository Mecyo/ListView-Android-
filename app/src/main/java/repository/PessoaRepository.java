package repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Util.DatabaseUtil;
import model.PessoaModel;

public class PessoaRepository {
    DatabaseUtil databaseUtil;

    public PessoaRepository(Context context){
        databaseUtil = new DatabaseUtil(context);
    }

    public void Salvar(PessoaModel pessoaModel){
        ContentValues contentValues = getPessoaContent(pessoaModel);
        databaseUtil.getConexaoDataBase().insert("tb_pessoa",null,contentValues);
    }

    public void Atualizar(PessoaModel pessoaModel){
        ContentValues contentValues = getPessoaContent(pessoaModel);
        databaseUtil.getConexaoDataBase().update("tb_pessoa", contentValues,
                "id_pessoa=?", new String[]{Integer.toString(pessoaModel.getCodigo())});
    }

    private ContentValues getPessoaContent(PessoaModel pessoaModel){
        ContentValues contentValues = new ContentValues();

        contentValues.put("ds_nome", pessoaModel.getNome());
        contentValues.put("ds_endereco", pessoaModel.getEndereco());
        contentValues.put("fl_sexo", pessoaModel.getSexo());
        contentValues.put("dt_nascimento", pessoaModel.getDataNascimento());
        contentValues.put("fl_estadoCivil", pessoaModel.getEstadoCivil());
        contentValues.put("fl_ativo", pessoaModel.getRegistroAtivo());
        return contentValues;
    }

    public Integer excluir(int codigo){
        return databaseUtil.getConexaoDataBase().delete("tb_pessoa", "id_pessoa = ?",
                new String[]{Integer.toString(codigo)});
    }

    public PessoaModel getPessoaById(int codigo){
        Cursor cursor = databaseUtil.getConexaoDataBase().rawQuery(
                "SELECT * FROM tb_pessoa WHERE id_pessoa = "+codigo,null);
        cursor.moveToFirst();
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_pessoa")));
        pessoaModel.setNome(cursor.getString(cursor.getColumnIndex("ds_nome")));
        pessoaModel.setEndereco(cursor.getString(cursor.getColumnIndex("ds_endereco")));
        pessoaModel.setDataNascimento(cursor.getString(cursor.getColumnIndex("dt_nascimento")));
        pessoaModel.setEstadoCivil(cursor.getString(cursor.getColumnIndex("fl_estadoCivil")));
        pessoaModel.setRegistroAtivo((byte)cursor.getShort(cursor.getColumnIndex("fl_ativo")));

        return pessoaModel;
    }

    public List<PessoaModel> selecionarPessoas(){
        List<PessoaModel> pessoas = new ArrayList<PessoaModel>();

        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT id_pessoa, ");
        stringBuilderQuery.append("ds_nome, ");
        stringBuilderQuery.append("ds_endereco, ");
        stringBuilderQuery.append("fl_sexo, ");
        stringBuilderQuery.append("dt_nascimento, ");
        stringBuilderQuery.append("fl_estadoCivil, ");
        stringBuilderQuery.append("fl_ativo, ");
        stringBuilderQuery.append("FROM tb_pessoa, ");
        stringBuilderQuery.append("ORDER BY ds_nome, ");

        Cursor cursor = databaseUtil.getConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);
        cursor.moveToFirst();

        PessoaModel pessoaModel;
        while (!cursor.isAfterLast()){
            pessoaModel = new PessoaModel(
                    cursor.getInt(cursor.getColumnIndex("id_pessoa")),
                    cursor.getString(cursor.getColumnIndex("ds_nome")),
                    cursor.getString(cursor.getColumnIndex("ds_endereco")),
                    cursor.getString(cursor.getColumnIndex("fl_sexo")),
                    cursor.getString(cursor.getColumnIndex("dt_nascimento")),
                    cursor.getString(cursor.getColumnIndex("fl_estadoCivil")),
                    (byte)cursor.getShort(cursor.getColumnIndex("fl_ativo"))
            );
            pessoas.add(pessoaModel);
            cursor.moveToNext();
        }
        return pessoas;
    }
}
