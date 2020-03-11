package be.hers.info.persea.dao.legalrepresentation;

import be.hers.info.persea.model.LegalRepresentation;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

@Component(value = "daoLegalRepresentation")
public class LegalRepresentationDaoImpl implements LegalRepresentationDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private  EntityManager em;

    @Override
    public void addOne(LegalRepresentation newElement) {
        em.getTransaction().begin();
        em.persist(newElement);
        System.out.println("Added" + newElement.getSubject());
        em.getTransaction().commit();
    }

    @Override
    public void addAll(List<LegalRepresentation> newElements) {

    }

    @Override
    public LegalRepresentation findById(long id) {
        try {
            return em.find(LegalRepresentation.class, id);
        } catch (EntityNotFoundException e) {throw e;}
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void put(long id, LegalRepresentation newElement) {

    }

    @Override
    public List<LegalRepresentation> findByCaseNumber(String caseNumber) {
        return null;
    }
}
