package Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.aluno.listview.R;

import java.util.ArrayList;
import java.util.List;

import model.PessoaModel;

/**
 * Created by Emerson Santos on 23/07/2018.
 */

public class ListViewAdapter extends BaseAdapter {

    //Itens de exibição / filtrados
    private List<PessoaModel> itens_exibicao;
    //Essa lista contem todos os itens.
    private List<PessoaModel> itens;
    //Utilizado no getView para carregar e construir um item.
    private LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, List<PessoaModel> itens) {
        this.itens = itens;
        this.itens_exibicao = itens;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens_exibicao.size();
    }

    @Override
    public Object getItem(int arg0) {
        return itens_exibicao.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return itens_exibicao.get(arg0).getCodigo();
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ItemHelper itemHelper = new ItemHelper();
        PessoaModel objeto = itens_exibicao.get(arg0);

        if (arg1 == null) {
            arg1 = layoutInflater.inflate(R.layout.activity_item_pessoa, null);
            itemHelper.descricao = (TextView) arg1.findViewById(R.id.textItemNome);
            //itemHelper.valor = (TextView) arg1.findViewById(R.id.textView2);
            arg1.setTag(itemHelper);
        } else {
            itemHelper = (ItemHelper) arg1.getTag();
        }

        itemHelper.descricao.setText("-> " + objeto.getCodigo() + " - " + objeto.getNome());
        //itemHelper.valor.setText(objeto.getValor());

        return arg1;
    }

    private class ItemHelper {

        TextView descricao, valor;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {

        TextView textview1 = (TextView) v.findViewById(R.id.text1);
        TextView textview2 = (TextView) v.findViewById(R.id.text2);

        Log.v("textview1",textview1.getText().toString().trim());
        Log.v("textview2",textview2.getText().toString().trim());

    }

    /** Método responsável pelo filtro. Utilizaremos em um EditText
     *
     * @return
     */
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence filtro) {
                FilterResults results = new FilterResults();
                //se não foi realizado nenhum filtro insere todos os itens.
                if (filtro == null || filtro.length() == 0) {
                    results.count = itens.size();
                    results.values = itens;
                } else {
                    //cria um array para armazenar os objetos filtrados.
                    List<PessoaModel> itens_filtrados = new ArrayList<>();

                    //percorre toda lista verificando se contem a palavra do filtro na descricao do objeto.
                    for (int i = 0; i < itens.size(); i++) {
                        PessoaModel data = itens.get(i);

                        filtro = filtro.toString().toLowerCase();
                        String condicao = data.getNome().toLowerCase();

                        if (condicao.contains(filtro)) {
                            //se conter adiciona na lista de itens filtrados.
                            itens_filtrados.add(data);
                        }
                    }
                    // Define o resultado do filtro na variavel FilterResults
                    results.count = itens_filtrados.size();
                    results.values = itens_filtrados;
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
                itens_exibicao = (List<PessoaModel>) results.values; // Valores filtrados.
                notifyDataSetChanged();  // Notifica a lista de alteração
            }

        };
        return filter;
    }
}
