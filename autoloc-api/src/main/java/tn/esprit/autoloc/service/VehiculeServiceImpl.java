package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.TarifStrategyProvider;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

// Chap 5: Question 20
// Chap 8: Question 16 - utilisation du pattern Strategy
@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long, IVehiculeRepository> implements IVehiculeService {

    private final TarifStrategyProvider tarifStrategyProvider;

    public VehiculeServiceImpl(IVehiculeRepository repository, TarifStrategyProvider tarifStrategyProvider) {
        super(repository);
        this.tarifStrategyProvider = tarifStrategyProvider;
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
}
