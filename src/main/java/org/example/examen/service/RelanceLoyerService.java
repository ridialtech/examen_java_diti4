package org.example.examen.service;

import org.example.examen.model.Paiement;
import org.example.examen.repository.PaiementRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelanceLoyerService {

    private final PaiementRepository paiementRepository;

    public RelanceLoyerService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void relancerImpayes() {
        List<Paiement> impayes = paiementRepository.findByStatut("en_retard");
        for (Paiement paiement : impayes) {
            System.out.println("Relance pour le paiement en retard " + paiement.getId());
        }
    }
}
