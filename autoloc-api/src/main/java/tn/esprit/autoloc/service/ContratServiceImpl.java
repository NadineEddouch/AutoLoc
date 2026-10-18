package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

// Chap 5: Question 20
@Service
public class ContratServiceImpl extends GenericServiceImpl<Contrat, Long, IContratRepository> implements IContratService {

    public ContratServiceImpl(IContratRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Contrat";
    }

    @Override
    protected Long getId(Contrat entity) {
        return entity.getIdContrat();
    }

    @Override
    protected void setFields(Contrat source, Contrat target) {
        target.setDateSignature(source.getDateSignature());
        target.setMontantTotal(source.getMontantTotal());
        target.setValide(source.getValide());
        target.setReservation(source.getReservation());
    }

    @Override
    public boolean check(Contrat contrat, boolean isNew) {
        return contrat.getDateSignature() != null && contrat.getMontantTotal() != null;
    }
}
