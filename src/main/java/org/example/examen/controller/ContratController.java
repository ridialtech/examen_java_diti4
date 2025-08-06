package org.example.examen.controller;

import org.example.examen.model.Contrat;
import org.example.examen.service.ContratService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contrats")
public class ContratController {

    private final ContratService contratService;

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("contrats", contratService.findAll());
        return "contrats";
    }

    @GetMapping("/nouveau")
    public String showForm(Model model) {
        model.addAttribute("contrat", new Contrat());
        return "contrat-form";
    }

    @PostMapping
    public String create(@RequestParam Long locataireId,
                         @RequestParam Long uniteId,
                         @ModelAttribute Contrat contrat) {
        contratService.create(locataireId, uniteId, contrat);
        return "redirect:/contrats";
    }
}
