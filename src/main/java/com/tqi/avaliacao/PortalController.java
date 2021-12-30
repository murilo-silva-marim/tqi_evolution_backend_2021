package com.tqi.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PortalController {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private EmprestimosRepository emprestimosRepository;

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
        Cliente cliente = clientesRepository.findByEmail(currentPrincipalName);
        emprestimo.setCliente(cliente);
        cliente.getEmprestimos().add(emprestimo);
        clientesRepository.save(cliente);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emprestimos")
    public String emprestimos(Model model){
        String currentPrincipalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clientesRepository.findByEmail(currentPrincipalName);
        model.addAttribute("listaEmprestimos", emprestimosRepository.findByCliente(cliente));
        return "emprestimos";
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
