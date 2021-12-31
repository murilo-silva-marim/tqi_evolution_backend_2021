package com.tqi.avaliacao.controllers;

import com.tqi.avaliacao.custom.editors.CustomFloatEditor;
import com.tqi.avaliacao.custom.editors.CustomIntegerEditor;
import com.tqi.avaliacao.models.Cliente;
import com.tqi.avaliacao.models.Emprestimo;
import com.tqi.avaliacao.services.ClientesService;
import com.tqi.avaliacao.services.EmprestimosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class PortalController {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private EmprestimosService emprestimosService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/solicitarEmprestimo")
    public String solicitarEmprestimo(@ModelAttribute("emprestimo") Emprestimo emprestimo){

        return "solicitarEmprestimo";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/solicitarEmprestimo")
    public String salvarEmprestimo(@ModelAttribute("emprestimo") @Valid Emprestimo emprestimo, Errors errors){
        if(errors.hasErrors()){
            return "solicitarEmprestimo";
        }
        String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Cliente> cliente = clientesService.findByEmail(currentPrincipalName);
        emprestimo.setCliente(cliente.get());
        cliente.get().getEmprestimos().add(emprestimo);
        clientesService.save(cliente.get());
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emprestimos")
    public String emprestimos(Model model){
        String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Cliente> cliente = clientesService.findByEmail(currentPrincipalName);
        model.addAttribute("listaEmprestimos", emprestimosService.findByCliente(cliente.get()));
        return "emprestimos";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emprestimos/{id}")
    public String verEmprestimo(@PathVariable Integer id, Model model){
        Optional<Emprestimo> emprestimo = emprestimosService.findById(id);
        String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(emprestimo.isPresent() && emprestimo.get().getCliente().getEmail().equals(currentPrincipalName)){
            model.addAttribute("emprestimo", emprestimo.get());
            return "detalhamentoEmprestimo";
        }else{
            return "404";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Float.class, new CustomFloatEditor());
        binder.registerCustomEditor(Integer.class, new CustomIntegerEditor());

    }

}