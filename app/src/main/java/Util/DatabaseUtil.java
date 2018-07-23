package Util;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseUtil extends SQLiteOpenHelper {
    private static final String nomeBaseDeDados = "SISTEMA.db";
    private  static final int versaoBaseDeDados = 1;
    Context context;

    public DatabaseUtil(Context context){
        super(context, nomeBaseDeDados, null, versaoBaseDeDados);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        StringBuilder stringBuilderCreateTable = new StringBuilder();


        stringBuilderCreateTable.append("CREATE TABLE IF NOT EXISTS tb_pessoa(");
        stringBuilderCreateTable.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTable.append("ds_nome TEXT NOT NULL, ");
        stringBuilderCreateTable.append("ds_endereco TEXT NOT NULL, ");
        stringBuilderCreateTable.append("fl_sexo TEXT NOT NULL, ");
        stringBuilderCreateTable.append("dt_nascimento TEXT NOT NULL, ");
        stringBuilderCreateTable.append("fl_estadoCivil TEXT NOT NULL, ");
        stringBuilderCreateTable.append("fl_ativo INT NOT NULL);");

        db.beginTransaction();
        try {
            db.execSQL(stringBuilderCreateTable.toString());
            db.setTransactionSuccessful();
        }
        catch (SQLException e)
        {
            Toast.makeText(this.context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS tb_pessoa");
        this.onCreate(db);
    }

    public SQLiteDatabase getConexaoDataBase() {
        return this.getWritableDatabase();
    }
}