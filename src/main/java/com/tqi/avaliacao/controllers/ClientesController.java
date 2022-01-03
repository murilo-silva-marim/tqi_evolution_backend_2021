package com.tqi.avaliacao.controllers;

import com.tqi.avaliacao.config.security.Role;
import com.tqi.avaliacao.dto.ClienteDto;
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
    public List<ClienteDto> getClientes(){

        return ClienteDto.convert(clientesService.findAll());

    }

    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody @Valid Cliente cliente){
        cliente.setSenha(encoder.encode(cliente.getSenha()));
        cliente.setRole(Role.USER.getNome());
        cliente.setEmprestimos(new ArrayList<>());
        Cliente clienteSalvo = clientesService.save(cliente);
        return new ResponseEntity<ClienteDto>(ClienteDto.convert(clienteSalvo), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable(value = "id") Integer id,
                                                 @RequestBody @Valid Cliente clienteNovo){
        Optional<Cliente> clienteAntigo = clientesService.findById(id);
        if(clienteAntigo.isPresent()){
            Cliente cliente = clienteAntigo.get();
            cliente.setEmail(clienteNovo.getEmail());
            cliente.setSenha(encoder.encode(clienteNovo.getSenha()));
            cliente.setCpf(clienteNovo.getCpf());
            cliente.setRg(clienteNovo.getRg());
            cliente.setEndereco(clienteNovo.getEndereco());
            cliente.setNome(cliente.getNome());
            cliente.setRenda(clienteNovo.getRenda());
            Cliente clienteSalvo = clientesService.save(cliente);
            return ResponseEntity.ok().body(ClienteDto.convert(clienteSalvo));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable(value = "id") Integer id){

        Optional<Cliente> optional = clientesService.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok().body(ClienteDto.convert(optional.get()));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> deleteCliente(@PathVariable(value = "id") Integer id){
        Optional<Cliente> optional = clientesService.findById(id);
        if(optional.isPresent()){
            clientesService.deleteById(id);
            return ResponseEntity.noContent().build();
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
            if (error.getCode().contains("EmailUnico")) {
                errors.put("email", error.getDefaultMessage());
            }else{
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }
        });
        return errors;
    }

}
