package com.example.agenda.telas;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.Classes.Aluno;
import com.example.agenda.Classes.AlunoDAO;
import com.example.agenda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import iu.activity.ListaAlunosAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String Titulo_ListaAluno = "Lista de Alunos";
    public static final String CHAVE_ALUNO = "Aluno";
    private final AlunoDAO alunodao = new AlunoDAO();
    private ListaAlunosAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(Titulo_ListaAluno); //titulo

            configuraLista();
            ConfiguraNovoAluno();
    }



    @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            super.onCreateContextMenu(menu, v, menuInfo);
            getMenuInflater().inflate(R.menu.actovity_listaalunos_mode_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        excluiAlunoDialog(item);
        //edita aluno no menu de contexto
        editaAlunoContexto(item);
        Log.i("menu", "Item Contexto "+item.getTitle());
        return super.onContextItemSelected(item);
    }

    private void editaAlunoContexto(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.Act_listaAlunos_editar){
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = (Aluno) adapter.getItem(menuInfo.position);
            abreFormularioEditaAluno(alunoEscolhido);

        }
    }

    private void excluiAlunoDialog(@NonNull final MenuItem item) {
        if(item.getItemId() == R.id.Act_listaAlunos_remover){

            new AlertDialog.Builder(this)
                    .setTitle("Removendo Aluno")
                    .setMessage("Tem certerza que deseja remover?")
                    .setPositiveButton("Sim", (dialogInterface, i) -> {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = (Aluno) adapter.getItem(menuInfo.position);
                        adapter.remove(alunoEscolhido);
                        removeAluno(alunoEscolhido);
                    })
                    .setNegativeButton("Nao", null)
                    .show();
        }
    }

    private void ConfiguraNovoAluno() {
        FloatingActionButton BotaoNovoAluno = findViewById(R.id.act_main_Fab_novo_aluno);
        BotaoNovoAluno.setOnClickListener(view -> AbreFormularioALunoActivity());
    }
    //Cria um novo formulario activity
    private void AbreFormularioALunoActivity() {
        startActivity(new Intent(MainActivity.this, FormularioAlunoActivity.class));
    }

    //metodo de pra entrar no onRemume mesmo sem a activity ativada ele continua salvando a informaçao da lista
    @Override
    protected void onResume() {
        super.onResume();
        atualizaListaAlunos();

    }

    private void atualizaListaAlunos() {
        adapter.atualiza(alunodao.todos());
    }

    private void configuraLista() {
        ListView ListaAlunos = findViewById(R.id.act_main_ListaAlunos);

        configuraAdapter(ListaAlunos);
        registerForContextMenu(ListaAlunos);
    }




    private void removeAluno(Aluno alunoEscolhido) {
        alunodao.remover(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }

    private void abreFormularioEditaAluno(Aluno alunoEscolhido) {
            Intent direcionaFormulario = new Intent(MainActivity.this, FormularioAlunoActivity.class);

            direcionaFormulario.putExtra(CHAVE_ALUNO, alunoEscolhido);
            startActivity(direcionaFormulario);
        }



        private void configuraAdapter(@NonNull ListView ListaAlunos) {

            adapter = new ListaAlunosAdapter(this);
            ListaAlunos.setAdapter(this.adapter);
    }
}