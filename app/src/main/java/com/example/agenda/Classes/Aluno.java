package com.example.agenda.Classes;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private  String nome;
    private  String telefone;
    private  String email;
    private int id = 0;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    public Aluno(){

    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean  idValido(){
        return id > 0;
    }

    @NonNull
    @Override
    public String toString() {

        return nome + " - "+telefone;

    }

}
