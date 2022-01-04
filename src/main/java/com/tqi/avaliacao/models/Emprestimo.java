package com.tqi.avaliacao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tqi.avaliacao.custom.validations.PrimeiraParcela;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
@Table(name = "emprestimos")
@Data
@NoArgsConstructor
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

    @PrimeiraParcela
    private Date primeira_parcela;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
