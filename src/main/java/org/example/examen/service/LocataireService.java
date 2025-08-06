package org.example.examen.service;

import org.example.examen.model.Contrat;
import org.example.examen.model.Locataire;
import org.example.examen.model.UniteLocation;
import org.example.examen.repository.ContratRepository;
import org.example.examen.repository.LocataireRepository;
import org.example.examen.repository.UniteLocationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LocataireService {

    private final LocataireRepository locataireRepository;
    private final UniteLocationRepository uniteRepository;
    private final ContratRepository contratRepository;

    public LocataireService(LocataireRepository locataireRepository,
                            UniteLocationRepository uniteRepository,
                            ContratRepository contratRepository) {
        this.locataireRepository = locataireRepository;
        this.uniteRepository = uniteRepository;
        this.contratRepository = contratRepository;
    }

    public Locataire register(Locataire locataire) {
        return locataireRepository.save(locataire);
    }

    public Optional<Locataire> login(String email, String motDePasse) {
        return locataireRepository.findByEmailAndMotDePasse(email, motDePasse);
    }

    public List<UniteLocation> listOffres() {
        return uniteRepository.findByStatut("disponible");
    }

    public void envoyerDemande(Long locataireId, Long uniteId) {
        Locataire locataire = locataireRepository.findById(locataireId).orElseThrow();
        UniteLocation unite = uniteRepository.findById(uniteId).orElseThrow();
        Contrat contrat = new Contrat();
        contrat.setLocataire(locataire);
        contrat.setUniteLocation(unite);
        contrat.setDateDebut(LocalDate.now());
        contrat.setDateFin(LocalDate.now().plusMonths(12));
        contrat.setMontant(unite.getLoyer());
        contratRepository.save(contrat);
    }
}
