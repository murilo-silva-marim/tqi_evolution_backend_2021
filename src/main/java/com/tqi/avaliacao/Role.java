package com.tqi.avaliacao;

public enum Role {

    USER("USER");

    private String nome;

    private Role(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }
}
