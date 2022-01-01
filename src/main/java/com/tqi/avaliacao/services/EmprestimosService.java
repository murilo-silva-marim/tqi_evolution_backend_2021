package com.tqi.avaliacao.services;

import com.tqi.avaliacao.models.Cliente;
import com.tqi.avaliacao.models.Emprestimo;
import com.tqi.avaliacao.repositories.EmprestimosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimosService {

    @Autowired
    private EmprestimosRepository emprestimosRepository;

    public EmprestimosService(){

    }

    public List<Emprestimo> findByCliente(Cliente cliente){
        return emprestimosRepository.findByCliente(cliente);
    }

    public Optional<Emprestimo> findById(Integer id){
        return emprestimosRepository.findById(id);
    }

    public Emprestimo save(Emprestimo emprestimo){
        return emprestimosRepository.save(emprestimo);
    }

}
