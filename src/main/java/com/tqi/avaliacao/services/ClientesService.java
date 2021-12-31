package com.tqi.avaliacao.services;

import com.tqi.avaliacao.models.Cliente;
import com.tqi.avaliacao.repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public ClientesService(){

    }

    public List<Cliente> findAll(){
        return clientesRepository.findAll();
    }

    public Optional<Cliente> findById(Integer id){
        return clientesRepository.findById(id);
    }

    public Optional<Cliente> findByEmail(String email){
        return clientesRepository.findByEmail(email);
    }

    public Boolean existsByEmail(String email){
        return clientesRepository.existsByEmail(email);
    }

    public Cliente save(Cliente cliente){
        return clientesRepository.save(cliente);
    }

    public void deleteById(Integer id){
        clientesRepository.deleteById(id);
    }

}
