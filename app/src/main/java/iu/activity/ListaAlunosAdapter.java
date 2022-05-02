package iu.activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.agenda.Classes.Aluno;
import com.example.agenda.telas.LerInformacoesAlunosAcitivity;
import com.example.agenda.R;
import com.example.agenda.telas.FormularioAlunoActivity;
import com.example.agenda.telas.MainActivity;


import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    FormularioAlunoActivity formulario = new FormularioAlunoActivity();
    private  final Context context;

    public ListaAlunosAdapter(Context context) {
            this.context = context;
        }

    @SuppressWarnings("rawtypes")
    private final List<Aluno> alunos = new ArrayList();

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {


        View viewCriada = criaView(viewGroup);
        Aluno alunoDevolvido = alunos.get(posicao);

        vincula(viewCriada, alunoDevolvido);

        cliqueLongoMenuContexto(viewCriada, alunoDevolvido);

        cliqueCurtoLista(viewCriada,alunoDevolvido);


        return viewCriada;
        }

    private void cliqueLongoMenuContexto(View viewCriada, Aluno alunoDevolvido) {

        viewCriada.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                vincula(viewCriada, alunoDevolvido);
                return false;
            }
        });
    }

    private void cliqueCurtoLista(View viewCriada, Aluno alunoDevolvido) {

          viewCriada.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {

               abreLerInformacoesAluno(alunoDevolvido);
            }
        });
    }

    private void vincula(View viewCriada, Aluno alunoDevolvido) {


        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolvido.getNome());

        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(alunoDevolvido.getTelefone());


    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
    }

    private  void addAll(List<Aluno> alunos) {
            this.alunos.addAll(alunos);
            notifyDataSetChanged();
    }

    public void atualiza(List<Aluno> alunos){
    this.alunos.clear();
    this.addAll(alunos);
    notifyDataSetChanged();
    }


    public void remove(Aluno alunoEscolhido){

        alunos.remove(alunoEscolhido);
        notifyDataSetChanged();
    }

    private void abreLerInformacoesAluno(Aluno alunoEscolhido) {

        Intent informacoesAluno = new Intent(context, LerInformacoesAlunosAcitivity.class);
        informacoesAluno.putExtra(MainActivity.CHAVE_ALUNO, alunoEscolhido);
        context.startActivity(informacoesAluno);

    }
}
