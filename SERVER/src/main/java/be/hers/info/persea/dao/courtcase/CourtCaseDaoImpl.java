package be.hers.info.persea.dao.courtcase;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.filter.courtCase.CourtCaseFilter;
import be.hers.info.persea.model.user.User;
import be.hers.info.persea.model.courtCase.CourtCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Component(value = "daoCourtCase")
public class CourtCaseDaoImpl implements CourtCaseDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    /*  CRUD    */
    @Override
    @Transactional
    public void addOne(CourtCase newElement) {
        em.persist(newElement);
    }

    @Override
    public void addAll(List<CourtCase> newElements) {

    }

    @Override
    public CourtCase getById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CourtCase> cq = cb.createQuery(CourtCase.class);

        Root<CourtCase> courtCaseRoot = cq.from(CourtCase.class);

        cq.where(cb.equal(courtCaseRoot.get("id"), id));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    @Transactional
    public void update(long id, CourtCase newElement) {
        this.em.persist(newElement);
    }

    /*  FILTERS */
    @Override
    public List<CourtCase> find(Filter<CourtCase> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CourtCase> cq = cb.createQuery(CourtCase.class);

        Root<CourtCase> courtCaseRoot = cq.from(CourtCase.class);
        cq.where(filter.doFilter(cb, courtCaseRoot)).distinct(true);

        CourtCaseFilter courtCaseFilter  = (CourtCaseFilter) filter;
        if(courtCaseFilter.getPageSize() == null || courtCaseFilter.getPageNumber() == null) {
            return em.createQuery(cq).getResultList();
        }

        return em.createQuery(cq)
                .setFirstResult(courtCaseFilter.getPageNumber() * courtCaseFilter.getPageSize())
                .setMaxResults(courtCaseFilter.getPageSize())
                .getResultList();
    }

    /*  CUSTOM  */
    @Override
    public long getSize(Filter<CourtCase> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<CourtCase> courtCaseRoot = cq.from(CourtCase.class);

        cq.where(filter.doFilter(cb, courtCaseRoot));
        return em.createQuery(cq.select(cb.countDistinct(courtCaseRoot))).getSingleResult();
    }

    @Override
    public List<CourtCase> findByUser(long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CourtCase> cq = cb.createQuery(CourtCase.class);

        Root<CourtCase> courtCaseRoot = cq.from(CourtCase.class);
        Join<CourtCase, User> courtCaseUserJoin = courtCaseRoot.join("owners");

        cq.where(cb.equal(courtCaseUserJoin.get("id"), userId)).distinct(true);
        return em.createQuery(cq).getResultList();
    }
}
