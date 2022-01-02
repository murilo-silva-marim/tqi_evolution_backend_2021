package com.tqi.avaliacao.controllers;

import com.tqi.avaliacao.config.security.Role;
import com.tqi.avaliacao.models.Cliente;
import com.tqi.avaliacao.models.Emprestimo;
import com.tqi.avaliacao.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private PasswordEncoder encoder;

    public ClientesController() {

    }

    @GetMapping
    public List<Cliente> getClientes(){

        return clientesService.findAll();

    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody @Valid Cliente cliente){
        cliente.setSenha(encoder.encode(cliente.getSenha()));
        cliente.setRole(Role.USER.getNome());
        cliente.setEmprestimos(new ArrayList<>());
        cliente.setId(null);
        Cliente clienteSalvo = clientesService.save(cliente);
        return new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable(value = "id") Integer id){

        Optional<Cliente> optional = clientesService.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok().body(optional.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable(value = "id") Integer id){
        Optional<Cliente> optional = clientesService.findById(id);
        if(optional.isPresent()){
            clientesService.deleteById(id);
            return ResponseEntity.ok().body(optional.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
