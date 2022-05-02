package com.example.agenda.Classes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
public class AlunoDAO {

    private final static List<Aluno> listaAluno = new ArrayList<>();
    private static int contadorIds=1;

    public void salva(@NonNull Aluno aluno){
        aluno.setId(contadorIds);
        listaAluno.add(aluno);
        atualizaIDs();
    }

    private void atualizaIDs() {
        contadorIds++;
    }

    public void edita(Aluno aluno){

        Aluno alunoEncontrado = buscaAlunoID(aluno);

        if(alunoEncontrado != null){
            int posicaoAluno = listaAluno.indexOf(alunoEncontrado);
            listaAluno.set(posicaoAluno, aluno);

        }
    }

    @Nullable
    private Aluno buscaAlunoID(Aluno aluno) {
        //noinspection unused
        Aluno alunoEncontrado = null;

        for (Aluno a: listaAluno) {
            if(a.getId() == aluno.getId()){
                alunoEncontrado = a;
                return a;
            }
        }
        return null;
    }


    public List<Aluno> todos() {
        return new ArrayList<>(listaAluno);
    }

    public void remover(Aluno alunoEscolhido) {
        Aluno AlunoDevolvido = buscaAlunoID(alunoEscolhido);

        if(AlunoDevolvido != null){
            listaAluno.remove(AlunoDevolvido);
        }

    }
}
