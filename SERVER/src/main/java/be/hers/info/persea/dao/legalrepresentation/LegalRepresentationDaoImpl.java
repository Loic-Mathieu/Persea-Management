package be.hers.info.persea.dao.legalrepresentation;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.filter.representation.RepresentationFilter;
import be.hers.info.persea.model.representation.LegalRepresentation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    public List<LegalRepresentation> find(Filter<LegalRepresentation> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LegalRepresentation> cq = cb.createQuery(LegalRepresentation.class);

        Root<LegalRepresentation> legalRepresentationRoot = cq.from(LegalRepresentation.class);

        cq.where(filter.doFilter(cb, legalRepresentationRoot)).distinct(true);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public long getSize(RepresentationFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<LegalRepresentation> legalRepresentationRoot = cq.from(LegalRepresentation.class);

        cq.where(filter.doFilter(cb, legalRepresentationRoot)).distinct(true);
        return em.createQuery(cq.select(cb.countDistinct(legalRepresentationRoot))).getSingleResult();
    }
}
