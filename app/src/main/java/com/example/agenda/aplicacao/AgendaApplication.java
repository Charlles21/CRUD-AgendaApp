package com.example.agenda.aplicacao;

import android.app.Application;

import com.example.agenda.Classes.Aluno;
import com.example.agenda.Classes.AlunoDAO;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        criaTestes();

    }

    private void criaTestes() {
        AlunoDAO alunodao = new AlunoDAO();
        alunodao.salva(new Aluno("Matheus","21975581386","email"));
        alunodao.salva(new Aluno("Thiago","21973521585","email"));
    }
}
