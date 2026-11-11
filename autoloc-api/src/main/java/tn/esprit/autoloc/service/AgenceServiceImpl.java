package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.io.RapportGeneratorFactory;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

// Chap 5: Question 17 - AgenceServiceImpl basé sur GenericServiceImpl
@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long, IAgenceRepository> implements IAgenceService {

    // Chap 6 : question 25 - injection de VehiculeRepository pour désaffecter les véhicules
    private final VehiculeRepository vehiculeRepository;
    // Chap 8 : question 8 - injection de IVehiculeService pour calculer les tarifs
    private final IVehiculeService vehiculeService;
    // Chap 8 : question 14 - injection de RapportGeneratorFactory pour générer les rapports
    private final RapportGeneratorFactory rapportGeneratorFactory;
    // Chap 9: Question 12 - injection de IEmployeService pour insérer les employés
    private final IGenericService<Employe, Long> employeService;

    public AgenceServiceImpl(IAgenceRepository repository, VehiculeRepository vehiculeRepository, IVehiculeService vehiculeService, RapportGeneratorFactory rapportGeneratorFactory, IGenericService<Employe, Long> employeService) {
        super(repository);
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeService = vehiculeService;
        this.rapportGeneratorFactory = rapportGeneratorFactory;
        this.employeService = employeService;
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

    // Chap 8: Question 14 - méthode simuler utilisant la Factory et le Decorator
    @Override
    public String simuler(Long agenceId, int nombreJours, VehiculeOffre offre) {
        Agence agence = findById(agenceId);
        IRapportGenerator rapportGenerator = rapportGeneratorFactory.createRapportGenerator();
        return rapportGenerator.generer(agence, nombreJours, offre);
    }

    // Chap 9: Question 12 - méthode pour ajouter une liste d'employés à une agence
    // Chap 9: Question 17 - ajout de @Transactional pour garantir l'atomicité
    @Override
    @Transactional
    public Agence ajouterEmployes(Long agenceId, List<Employe> nouveauxEmployes) {
        // 1. Récupérer l'agence
        Agence agence = findById(agenceId);

        // 2. Insérer les nouveaux employés en utilisant le service des employés
        for (Employe employe : nouveauxEmployes) {
            employe.setAgence(agence);
            employeService.create(employe);
        }

        // 3. Mettre à jour le champ effectif avec la taille de la liste des employés
        agence.setEffectif(agence.getEmployes().size());

        // 4. Sauvegarder l'agence mise à jour
        return repository.save(agence);
    }
}
