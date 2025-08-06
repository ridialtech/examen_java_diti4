package org.example.examen.service;

import org.example.examen.model.Contrat;
import org.example.examen.model.Paiement;
import org.example.examen.repository.ContratRepository;
import org.example.examen.repository.PaiementRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;

    public PaiementService(PaiementRepository paiementRepository, ContratRepository contratRepository) {
        this.paiementRepository = paiementRepository;
        this.contratRepository = contratRepository;
    }

    public Paiement enregistrer(Long contratId, Paiement paiement) {
        Contrat contrat = contratRepository.findById(contratId).orElseThrow();
        paiement.setContrat(contrat);
        if (paiement.getDatePaiement() == null) {
            paiement.setDatePaiement(LocalDate.now());
        }
        paiement.setStatut("paye");
        return paiementRepository.save(paiement);
    }

    public List<Paiement> findByContrat(Long contratId) {
        return paiementRepository.findByContratId(contratId);
    }

    public Optional<Paiement> findById(Long id) {
        return paiementRepository.findById(id);
    }

    public byte[] genererRecuPdf(Long paiementId) {
        Paiement paiement = paiementRepository.findById(paiementId).orElseThrow();
        String text = "Reçu de paiement\n" +
                "Paiement n° " + paiement.getId() + "\n" +
                "Date: " + paiement.getDatePaiement() + "\n" +
                "Montant: " + paiement.getMontant() + "\n" +
                "Contrat: " + paiement.getContrat().getId();

        String content = "BT /F1 12 Tf 50 800 Td (" + escapePdf(text) + ") Tj ET";

        StringBuilder sb = new StringBuilder();
        sb.append("%PDF-1.4\n");
        int o1 = sb.length();
        sb.append("1 0 obj<< /Type /Catalog /Pages 2 0 R >>endobj\n");
        int o2 = sb.length();
        sb.append("2 0 obj<< /Type /Pages /Kids [3 0 R] /Count 1 >>endobj\n");
        int o3 = sb.length();
        sb.append("3 0 obj<< /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] /Contents 4 0 R /Resources << /Font << /F1 5 0 R >> >> >>endobj\n");
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
}
