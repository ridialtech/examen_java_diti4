package org.example.examen.controller;

import org.example.examen.model.Contrat;
import org.example.examen.model.Paiement;
import org.example.examen.model.Utilisateur;
import org.example.examen.repository.ContratRepository;
import org.example.examen.repository.PaiementRepository;
import org.example.examen.service.UtilisateurService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UtilisateurService utilisateurService;
    private final ContratRepository contratRepository;
    private final PaiementRepository paiementRepository;

    public AdminController(UtilisateurService utilisateurService, ContratRepository contratRepository, PaiementRepository paiementRepository) {
        this.utilisateurService = utilisateurService;
        this.contratRepository = contratRepository;
        this.paiementRepository = paiementRepository;
    }

    // Gestion des utilisateurs
    @GetMapping("/utilisateurs")
    public List<Utilisateur> listUtilisateurs() {
        return utilisateurService.findAll();
    }

    @PostMapping("/utilisateurs")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.save(utilisateur);
    }

    @PutMapping("/utilisateurs/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id);
        return utilisateurService.save(utilisateur);
    }

    @DeleteMapping("/utilisateurs/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.delete(id);
    }

    // Tableau de bord
    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        long contratsActifs = contratRepository.findAll().stream()
                .filter(c -> c.getDateFin() == null || c.getDateFin().isAfter(LocalDate.now()))
                .count();
        double revenus = paiementRepository.findByStatut("paye").stream()
                .mapToDouble(Paiement::getMontant).sum();
        long impayes = paiementRepository.findByStatut("en_retard").size();

        Map<String, Object> stats = new HashMap<>();
        stats.put("contratsActifs", contratsActifs);
        stats.put("revenus", revenus);
        stats.put("impayes", impayes);
        return stats;
    }

    // Exportation des rapports
    @GetMapping("/reports/{type}/{format}")
    public ResponseEntity<byte[]> export(@PathVariable String type, @PathVariable String format) {
        String[] headers;
        List<String[]> data;
        String title;
        switch (type) {
            case "contrats" -> {
                title = "Contrats en cours";
                headers = new String[]{"ID", "Début", "Fin", "Montant"};
                data = contratRepository.findAll().stream()
                        .filter(c -> c.getDateFin() == null || c.getDateFin().isAfter(LocalDate.now()))
                        .map(c -> new String[]{String.valueOf(c.getId()), String.valueOf(c.getDateDebut()), String.valueOf(c.getDateFin()), String.valueOf(c.getMontant())})
                        .toList();
            }
            case "revenus" -> {
                title = "Revenus";
                headers = new String[]{"Paiement", "Contrat", "Montant"};
                data = paiementRepository.findByStatut("paye").stream()
                        .map(p -> new String[]{String.valueOf(p.getId()), String.valueOf(p.getContrat().getId()), String.valueOf(p.getMontant())})
                        .toList();
            }
            case "impayes" -> {
                title = "Impayés";
                headers = new String[]{"Paiement", "Contrat", "Montant"};
                data = paiementRepository.findByStatut("en_retard").stream()
                        .map(p -> new String[]{String.valueOf(p.getId()), String.valueOf(p.getContrat().getId()), String.valueOf(p.getMontant())})
                        .toList();
            }
            default -> throw new IllegalArgumentException("Type de rapport inconnu");
        }

        byte[] fichier;
        String filename;
        MediaType mediaType;
        if (format.equalsIgnoreCase("pdf")) {
            fichier = generatePdf(title, headers, data);
            filename = type + ".pdf";
            mediaType = MediaType.APPLICATION_PDF;
        } else if (format.equalsIgnoreCase("excel")) {
            fichier = generateExcel(headers, data);
            filename = type + ".csv";
            mediaType = MediaType.parseMediaType("text/csv");
        } else {
            throw new IllegalArgumentException("Format inconnu");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(mediaType)
                .body(fichier);
    }

    private byte[] generatePdf(String title, String[] headers, List<String[]> data) {
        StringBuilder text = new StringBuilder(title).append("\n");
        text.append(String.join(" | ", headers)).append("\n");
        data.forEach(row -> text.append(String.join(" | ", row)).append("\n"));

        String content = "BT /F1 12 Tf 50 800 Td (" + escapePdf(text.toString()) + ") Tj ET";

        StringBuilder sb = new StringBuilder();
        sb.append("%PDF-1.4\n");
        int o1 = sb.length();
        sb.append("1 0 obj<< /Type /Catalog /Pages 2 0 R >>endobj\n");
        int o2 = sb.length();
        sb.append("2 0 obj<< /Type /Pages /Kids [3 0 R] /Count 1 >>endobj\n");
        int o3 = sb.length();
        sb.append("3 0 obj<< /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] /Contents 4 0 R /Resources << /Font << /F1 5 0 R>> >> >>endobj\n");
        int o4 = sb.length();
        sb.append("4 0 obj<< /Length " + content.length() + " >>stream\n" + content + "\nendstream\nendobj\n");
        int o5 = sb.length();
        sb.append("5 0 obj<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>endobj\n");
        int startXref = sb.length();
        sb.append("xref\n0 6\n0000000000 65535 f \n");
        sb.append(String.format("%010d 00000 n \n", o1));
        sb.append(String.format("%010d 00000 n \n", o2));
        sb.append(String.format("%010d 00000 n \n", o3));
        sb.append(String.format("%010d 00000 n \n", o4));
        sb.append(String.format("%010d 00000 n \n", o5));
        sb.append("trailer<< /Size 6 /Root 1 0 R >>\nstartxref\n");
        sb.append(startXref);
        sb.append("\n%%EOF");
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private String escapePdf(String text) {
        return text.replace("\\", "\\\\").replace("(", "\\(").replace(")", "\\)").replace("\n", "\\n");
    }

    private byte[] generateExcel(String[] headers, List<String[]> data) {
        StringBuilder csv = new StringBuilder();
        csv.append(String.join(",", headers)).append("\n");
        for (String[] row : data) {
            csv.append(String.join(",", row)).append("\n");
        }
        return csv.toString().getBytes(StandardCharsets.UTF_8);
    }
}
