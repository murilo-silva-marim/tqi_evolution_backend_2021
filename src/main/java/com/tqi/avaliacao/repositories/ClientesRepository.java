package com.tqi.avaliacao.repositories;

import com.tqi.avaliacao.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String email);
    Boolean existsByEmail(String email);
}
