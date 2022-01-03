package com.tqi.avaliacao.dto;

import com.tqi.avaliacao.models.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDto {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private String endereco;
    private Float renda;

    public ClienteDto() {
    }

    public ClienteDto(Integer id, String nome, String email, String cpf, String rg, String endereco, Float renda) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.renda = renda;
    }

    public ClienteDto(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
        this.rg = cliente.getRg();
        this.endereco = cliente.getEndereco();
        this.renda = cliente.getRenda();
    }

    public static List<ClienteDto> convert(List<Cliente> clientes){
        return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
    }

    public static ClienteDto convert(Cliente clientes){
        return new ClienteDto(clientes);
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public Float getRenda() {
        return renda;
    }
}
