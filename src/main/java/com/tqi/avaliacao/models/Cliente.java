package com.tqi.avaliacao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tqi.avaliacao.custom.validations.EmailUnico;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@EmailUnico
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @SequenceGenerator(name="clientes_id_seq",
            sequenceName="clientes_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="clientes_id_seq")
    private Integer id;

    private String nome;

    @Email
    @NotBlank
    private String email;

    @CPF
    private String cpf;

    private String rg;

    private String endereco;

    private Float renda;

    @NotBlank
    private String senha;

    private String role;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

}
