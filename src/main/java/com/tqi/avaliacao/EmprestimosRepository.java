package com.tqi.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimosRepository extends JpaRepository<Emprestimo, Integer> {
    List<Emprestimo> findByCliente(Cliente cliente);
}
