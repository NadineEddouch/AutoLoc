package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

// Chap 5: Question 20
@Service
public class EquipementServiceImpl extends GenericServiceImpl<Equipement, Long, IEquipementRepository> implements IEquipementService {

    public EquipementServiceImpl(IEquipementRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Equipement";
    }

    @Override
    protected Long getId(Equipement entity) {
        return entity.getIdEquipement();
    }

    @Override
    protected void setFields(Equipement source, Equipement target) {
        target.setLibelle(source.getLibelle());
        target.setVehicules(source.getVehicules());
    }

    @Override
    public boolean check(Equipement equipement, boolean isNew) {
        return Utils.isNotEmpty(equipement.getLibelle());
    }

    // Chap 9: Question 9 - désaffecter les véhicules avant suppression
    @Override
    public void desaffecterVehicules(Long equipementId) {
        Equipement equipement = findById(equipementId);
        if (equipement.getVehicules() != null && !equipement.getVehicules().isEmpty()) {
            // Nettoyer la relation des deux côtés
            for (tn.esprit.autoloc.domain.Vehicule vehicule : equipement.getVehicules()) {
                if (vehicule.getEquipements() != null) {
                    vehicule.getEquipements().remove(equipement);
                }
            }
            equipement.getVehicules().clear();
            repository.save(equipement);
        }
    }

    // Chap 9: Question 9 - surcharger deleteById pour désaffecter d'abord
    @Override
    public void deleteById(Long id) {
        desaffecterVehicules(id);
        super.deleteById(id);
    }
}
