package com.tqi.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    private ClientesRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public ClientesController(ClientesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Cliente> clientes(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente){
        System.out.println(cliente + "----------------------------------------------------------------------------");
        cliente.setSenha(encoder.encode(cliente.getSenha()));
        cliente.setRole(Role.USER.getNome());
        Cliente clienteSalvo = repository.save(cliente);
        return new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> cliente(@PathVariable(value = "id") Integer id){

        Optional<Cliente> optional = repository.findById(id);
        if(optional.isPresent()){
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
