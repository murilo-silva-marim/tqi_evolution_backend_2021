package com.tqi.avaliacao.repositories;

import com.tqi.avaliacao.models.Cliente;
import com.tqi.avaliacao.models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimosRepository extends JpaRepository<Emprestimo, Integer> {
    List<Emprestimo> findByCliente(Cliente cliente);
}
