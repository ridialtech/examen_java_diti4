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
public class ContratService {

    private final ContratRepository contratRepository;
    private final LocataireRepository locataireRepository;
    private final UniteLocationRepository uniteRepository;

    public ContratService(ContratRepository contratRepository,
                          LocataireRepository locataireRepository,
                          UniteLocationRepository uniteRepository) {
        this.contratRepository = contratRepository;
        this.locataireRepository = locataireRepository;
        this.uniteRepository = uniteRepository;
    }

    public Contrat create(Long locataireId, Long uniteId, Contrat contrat) {
        Locataire locataire = locataireRepository.findById(locataireId).orElseThrow();
        UniteLocation unite = uniteRepository.findById(uniteId).orElseThrow();
        contrat.setLocataire(locataire);
        contrat.setUniteLocation(unite);
        if (contrat.getDateDebut() == null) {
            contrat.setDateDebut(LocalDate.now());
        }
        if (contrat.getDateFin() == null) {
            contrat.setDateFin(contrat.getDateDebut().plusMonths(12));
        }
        if (contrat.getMontant() == 0) {
            contrat.setMontant(unite.getLoyer());
        }
        return contratRepository.save(contrat);
    }

    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    public List<Contrat> findByLocataire(Long locataireId) {
        return contratRepository.findByLocataireId(locataireId);
    }

    public Optional<Contrat> findById(Long id) {
        return contratRepository.findById(id);
    }
}
