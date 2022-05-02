package com.example.agenda.telas;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.Classes.Aluno;
import com.example.agenda.Classes.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.telas.FormularioAlunoActivity;

import org.w3c.dom.Text;

import iu.activity.ListaAlunosAdapter;

public class LerInformacoesAlunosAcitivity extends AppCompatActivity {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno aluno;
    FormularioAlunoActivity formulario = new FormularioAlunoActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ler_informacoes_alunos);
        setTitle("Informaçoes do Aluno");
        Intent dadosMain = getIntent();
        TextView Nomeview = findViewById(R.id.NomeVIew);
        TextView TelefoneView = findViewById(R.id.telefoneView);
        TextView EmailView = findViewById(R.id.EmailView);

        if(dadosMain.hasExtra(formulario.CHAVE_ALUNO)){
            aluno = (Aluno) dadosMain.getSerializableExtra(formulario.CHAVE_ALUNO);

            Nomeview.setText("Nome: "+aluno.getNome());
            TelefoneView.setText("Telefone: "+aluno.getTelefone());
            EmailView.setText("Email: "+aluno.getEmail());

            //Log.i("TAG", "onCreate: "+CHAVE_ALUNO);

        }


    }

}
