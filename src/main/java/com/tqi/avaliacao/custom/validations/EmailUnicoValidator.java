package com.tqi.avaliacao.custom.validations;

import com.tqi.avaliacao.models.Cliente;
import com.tqi.avaliacao.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Optional;

@Configuration
public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, Cliente> {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(EmailUnico constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Cliente clienteValidar, ConstraintValidatorContext constraintValidatorContext) {
        if(request.getMethod().equals("PUT")){
            Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            String id = (String) map.get("id");
            clienteValidar.setId(Integer.parseInt(id));
        }
        Optional<Cliente> clienteBase = clientesService.findByEmail(clienteValidar.getEmail());
        if(clienteBase.isPresent()){
            Cliente cliente = clienteBase.get();
            if(cliente.getId().equals(clienteValidar.getId())){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }

    }
}
