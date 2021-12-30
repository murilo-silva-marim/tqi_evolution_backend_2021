package com.tqi.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByEmail(String email);
    Boolean existsByEmail(String email);
}
