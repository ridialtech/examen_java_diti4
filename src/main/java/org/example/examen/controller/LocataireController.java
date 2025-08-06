package org.example.examen.controller;

import jakarta.servlet.http.HttpSession;
import org.example.examen.model.Locataire;
import org.example.examen.service.LocataireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/locataires")
public class LocataireController {

    private final LocataireService service;

    public LocataireController(LocataireService service) {
        this.service = service;
    }

    @GetMapping("/inscription")
    public String showInscription(Model model) {
        model.addAttribute("locataire", new Locataire());
        return "locataire-inscription";
    }

    @PostMapping("/inscription")
    public String register(@ModelAttribute Locataire locataire) {
        service.register(locataire);
        return "redirect:/locataires/connexion";
    }

    @GetMapping("/connexion")
    public String showConnexion(Model model) {
        model.addAttribute("locataire", new Locataire());
        return "locataire-connexion";
    }

    @PostMapping("/connexion")
    public String login(@ModelAttribute Locataire locataire, HttpSession session, Model model) {
        return service.login(locataire.getEmail(), locataire.getMotDePasse())
                .map(l -> {
                    session.setAttribute("locataireId", l.getId());
                    return "redirect:/locataires/espace";
                })
                .orElseGet(() -> {
                    model.addAttribute("erreur", true);
                    return "locataire-connexion";
                });
    }

    @GetMapping("/espace")
    public String espace(Model model, HttpSession session) {
        Long locataireId = (Long) session.getAttribute("locataireId");
        if (locataireId == null) {
            return "redirect:/locataires/connexion";
        }
        model.addAttribute("offres", service.listOffres());
        return "locataire-espace";
    }

    @PostMapping("/demandes/{uniteId}")
    public String envoyerDemande(@PathVariable Long uniteId, HttpSession session) {
        Long locataireId = (Long) session.getAttribute("locataireId");
        if (locataireId == null) {
            return "redirect:/locataires/connexion";
        }
        service.envoyerDemande(locataireId, uniteId);
        return "redirect:/locataires/espace";
    }
}
