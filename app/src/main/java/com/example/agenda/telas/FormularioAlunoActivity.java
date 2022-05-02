package com.example.agenda.telas;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.Classes.Aluno;
import com.example.agenda.Classes.AlunoDAO;
import com.example.agenda.R;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    public static final String TITLE_EDITAR = "Editar aluno";
    public static final String CHAVE_ALUNO = "Aluno";
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno aluno;
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_aluno);
        // Campos pros preenchimentos
        inicializacaoCampos();

        //pega os dados da maine repassa pra esta activity
        Intent dadosMain = getIntent();

        if(dadosMain.hasExtra(CHAVE_ALUNO)){
            aluno = (Aluno) dadosMain.getSerializableExtra(CHAVE_ALUNO);
            setTitle(TITLE_EDITAR);
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
            //Log.i("TAG", "onCreate: "+CHAVE_ALUNO);

        }else{
            setTitle(TITULO_APPBAR);
            aluno = new Aluno();
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int ItemSalvarId = item.getItemId();

        if(ItemSalvarId == R.id.act_formulario_aluno_menu_salva){
            finalizaFormulario();
        }

        return super.onOptionsItemSelected(item);
    }

    private void finalizaFormulario() {
        preencheAluno();

        if(aluno.idValido()){
            alunoDAO.edita(aluno);
        }else{
            alunoDAO.salva(aluno);
            Toast.makeText(FormularioAlunoActivity.this,
                    "Salvo"
                    , Toast.LENGTH_SHORT).show();

        }
        finish();
    }

    private void inicializacaoCampos() {
        //Nome
        campoNome = findViewById(R.id.act_main_Fab_novo_aluno_nomeAluno);
        //telefone
        campoTelefone = findViewById(R.id.act_main_Fab_novo_aluno_telefonepreencher);
        //email
        campoEmail = findViewById(R.id.act_main_Fab_novo_aluno_emailpreencher);


    }


    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);

    }
}