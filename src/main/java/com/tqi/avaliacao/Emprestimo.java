package com.tqi.avaliacao;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @SequenceGenerator(name="emprestimos_id_seq",
            sequenceName="emprestimos_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="emprestimos_id_seq")
    private Integer id;

    @NotNull
    @Min(value = 1)
    private Float valor;

    @NotNull
    @Max(value = 60L)
    private Integer quantidade_parcelas;

    @PrimeiraParcelaValid
    private Date primeira_parcela;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Emprestimo(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getQuantidade_parcelas() {
        return quantidade_parcelas;
    }

    public void setQuantidade_parcelas(Integer quantidade_parcelas) {
        this.quantidade_parcelas = quantidade_parcelas;
    }

    public Date getPrimeira_parcela() {
        return primeira_parcela;
    }

    public void setPrimeira_parcela(Date primeira_parcela) {
        this.primeira_parcela = primeira_parcela;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", valor=" + valor +
                ", quantidade_parcelas=" + quantidade_parcelas +
                ", primeira_parcela=" + primeira_parcela +
                ", cliente=" + cliente +
                '}';
    }
}
