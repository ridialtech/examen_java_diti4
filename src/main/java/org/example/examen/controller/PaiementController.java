package org.example.examen.controller;

import org.example.examen.model.Paiement;
import org.example.examen.service.PaiementService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paiements")
public class PaiementController {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @GetMapping("/contrat/{contratId}")
    public String list(@PathVariable Long contratId, Model model) {
        model.addAttribute("paiements", paiementService.findByContrat(contratId));
        model.addAttribute("paiement", new Paiement());
        model.addAttribute("contratId", contratId);
        return "paiements";
    }

    @PostMapping("/contrat/{contratId}")
    public String enregistrer(@PathVariable Long contratId, @ModelAttribute Paiement paiement) {
        paiementService.enregistrer(contratId, paiement);
        return "redirect:/paiements/contrat/" + contratId;
    }

    @GetMapping("/{paiementId}/recu")
    public ResponseEntity<byte[]> recu(@PathVariable Long paiementId) {
        byte[] pdf = paiementService.genererRecuPdf(paiementId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=recu-" + paiementId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
