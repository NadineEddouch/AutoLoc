package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

// Chap 5: Question 17 - AgenceServiceImpl basé sur GenericServiceImpl
@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long, IAgenceRepository> implements IAgenceService {

    // Chap 6 : question 25 - injection de VehiculeRepository pour désaffecter les véhicules
    private final VehiculeRepository vehiculeRepository;

    public AgenceServiceImpl(IAgenceRepository repository, VehiculeRepository vehiculeRepository) {
        super(repository);
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    protected String getEntityName() {
        return "Agence";
    }

    @Override
    protected Long getId(Agence entity) {
        return entity.getIdAgence();
    }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setVille(source.getVille());
        target.setAdresse(source.getAdresse());
        target.setTelephone(source.getTelephone());
        target.setVehicules(source.getVehicules());
    }

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return Utils.isNotEmpty(agence.getNom()) && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public boolean hasVehicules(Long id) {
        Agence agence = findById(id);
        return agence.getVehicules() != null && !agence.getVehicules().isEmpty();
    }

    // Chap 6 : question 25 - désaffecter les véhicules avant de supprimer l'agence
    @Override
    public void deleteById(Long id) {
        // Récupérer les véhicules de l'agence
        List<Vehicule> vehicules = vehiculeRepository.findByAgenceIdAgence(id);
        // Désaffecter l'agence de chaque véhicule
        for (Vehicule vehicule : vehicules) {
            vehicule.setAgence(null);
            vehiculeRepository.save(vehicule);
        }
        // Supprimer l'agence
        super.deleteById(id);
    }
}
