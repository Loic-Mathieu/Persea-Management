package be.hers.info.persea.dao.legalrepresentation;

import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.representation.LegalRepresentation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import java.util.List;

@Component(value = "daoLegalRepresentation")
public class LegalRepresentationDaoImpl implements LegalRepresentationDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private  EntityManager em;

    @Override
    @Transactional
    public void addOne(LegalRepresentation newElement) {
        em.persist(newElement);
    }

    @Override
    public void addAll(List<LegalRepresentation> newElements) {
        newElements.forEach(this.em::persist);
    }

    @Override
    public LegalRepresentation getById(long id) {
        try {
            return em.find(LegalRepresentation.class, id);
        } catch (EntityNotFoundException e) {throw e;}
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, LegalRepresentation newElement) {

    }

    @Override
    public List<LegalRepresentation> findByCaseNumber(String caseNumber) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LegalRepresentation> cq = cb.createQuery(LegalRepresentation.class);

        Root<LegalRepresentation> legalRepresentationRoot = cq.from(LegalRepresentation.class);
        Join<LegalRepresentation, CourtCase> legalRepresentationCourtCaseJoin =
                legalRepresentationRoot.join("courtCase");

        cq.where(cb.equal(legalRepresentationCourtCaseJoin.get("caseNumber"), caseNumber));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<LegalRepresentation> findByCaseId(long caseId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LegalRepresentation> cq = cb.createQuery(LegalRepresentation.class);

        Root<LegalRepresentation> legalRepresentationRoot = cq.from(LegalRepresentation.class);
        Join<LegalRepresentation, CourtCase> legalRepresentationCourtCaseJoin =
                legalRepresentationRoot.join("courtCase");

        cq.where(cb.equal(legalRepresentationCourtCaseJoin.get("id"), caseId));
        return em.createQuery(cq).getResultList();
    }
}
