package be.hers.info.persea.dao.contributor;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.filter.contributor.LawyerFilter;
import be.hers.info.persea.filter.contributor.OppositionFilter;
import be.hers.info.persea.model.contibutor.Lawyer;
import be.hers.info.persea.model.contibutor.Opposition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component(value = "daoLawyer")
public class LawyerDaoImpl implements LawyerDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    @Transactional
    public void addOne(Lawyer newElement) {
        this.em.persist(newElement);
    }

    @Override
    @Transactional
    public void addAll(List<Lawyer> newElements) {
        newElements.forEach(em::persist);
    }

    @Override
    public Lawyer getById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lawyer> cq = cb.createQuery(Lawyer.class);

        Root<Lawyer> lawyerRoot = cq.from(Lawyer.class);
        cq.where(cb.equal(lawyerRoot.get("id"), id));

        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, Lawyer newElement) {

    }

    @Override
    public List<Lawyer> find(Filter<Lawyer> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lawyer> cq = cb.createQuery(Lawyer.class);

        Root<Lawyer> lawyerRoot = cq.from(Lawyer.class);
        cq.where(filter.doFilter(cb, lawyerRoot)).distinct(true);


        LawyerFilter lawyerFilter  = (LawyerFilter) filter;
        if(lawyerFilter.getPageSize() == null || lawyerFilter.getPageNumber() == null) {
            return em.createQuery(cq).getResultList();
        }

        return em.createQuery(cq)
                .setFirstResult(lawyerFilter.getPageNumber() * lawyerFilter.getPageSize())
                .setMaxResults(lawyerFilter.getPageSize())
                .getResultList();
    }

    @Override
    public long getSize(Filter<Lawyer> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Lawyer> lawyerRoot = cq.from(Lawyer.class);
        cq.where(filter.doFilter(cb, lawyerRoot)).distinct(true);

        return em.createQuery(cq.select(cb.countDistinct(lawyerRoot))).getSingleResult();
    }
}
