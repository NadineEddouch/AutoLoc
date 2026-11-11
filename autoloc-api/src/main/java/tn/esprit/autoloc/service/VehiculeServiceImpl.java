package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.TarifStrategyProvider;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

// Chap 5: Question 20
// Chap 8: Question 16 - utilisation du pattern Strategy
@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long, IVehiculeRepository> implements IVehiculeService {

    private final TarifStrategyProvider tarifStrategyProvider;
    private final IAgenceService agenceService;
    private final IGenericService<Equipement, Long> equipementService;

    public VehiculeServiceImpl(IVehiculeRepository repository, TarifStrategyProvider tarifStrategyProvider, @Lazy IAgenceService agenceService, IGenericService<Equipement, Long> equipementService) {
        super(repository);
        this.tarifStrategyProvider = tarifStrategyProvider;
        this.agenceService = agenceService;
        this.equipementService = equipementService;
    }

    @Override
    protected String getEntityName() {
        return "Vehicule";
    }

    @Override
    protected Long getId(Vehicule entity) {
        return entity.getIdVehicule();
    }

    @Override
    protected void setFields(Vehicule source, Vehicule target) {
        target.setImmatriculation(source.getImmatriculation());
        target.setMarque(source.getMarque());
        target.setModele(source.getModele());
        target.setCategorie(source.getCategorie());
        target.setTarifJournalier(source.getTarifJournalier());
        target.setStatut(source.getStatut());
        target.setAgence(source.getAgence());
    }

    @Override
    public boolean check(Vehicule vehicule, boolean isNew) {
        return Utils.isNotEmpty(vehicule.getImmatriculation()) && Utils.isNotEmpty(vehicule.getMarque());
    }

    // Chap 8: Question 16 - tarification utilisant le pattern Strategy
    @Override
    public double calculTarif(Long vehiculeId, int nombreJours, VehiculeOffre offre) {
        Vehicule vehicule = findById(vehiculeId);
        double tarifJournalier = vehicule.getTarifJournalier().doubleValue();
        return tarifStrategyProvider.getStrategy(offre).calculer(tarifJournalier, nombreJours);
    }

    // Chap 9: Question 1 - désaffecter un véhicule de son agence (code minimaliste)
    @Override
    public void desaffecter(Long vehiculeId) {
        Vehicule vehicule = findById(vehiculeId);
        vehicule.setAgence(null);
        repository.save(vehicule);
    }

    // Chap 9: Question 4 - affecter une agence à un véhicule
    @Override
    public void affecterAgence(Long vehiculeId, Long agenceId) {
        Vehicule vehicule = findById(vehiculeId);
        Agence agence = agenceService.findById(agenceId);
        vehicule.setAgence(agence);
        repository.save(vehicule);
    }

    // Chap 9: Question 6 - affecter un équipement à un véhicule
    @Override
    public void affecterEquipement(Long vehiculeId, Long equipementId) {
        Vehicule vehicule = findById(vehiculeId);
        Equipement equipement = equipementService.findById(equipementId);
        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new java.util.ArrayList<>());
        }
        vehicule.getEquipements().add(equipement);
        repository.save(vehicule);
    }
}
