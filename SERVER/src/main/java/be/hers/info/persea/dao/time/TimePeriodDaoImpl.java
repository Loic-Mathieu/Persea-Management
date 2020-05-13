package be.hers.info.persea.dao.time;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.time.TimePeriod;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TimePeriodDaoImpl implements TimePeriodDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    /*  CRUD    */
    @Override
    @Transactional
    public void addOne(TimePeriod newElement) {
        this.em.persist(newElement);
    }

    @Override
    @Transactional
    public void addAll(List<TimePeriod> newElements) {
        newElements.forEach(this.em::persist);
    }

    @Override
    public TimePeriod getById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TimePeriod> cq = cb.createQuery(TimePeriod.class);

        Root<TimePeriod> timePeriodRoot = cq.from(TimePeriod.class);

        cq.where(cb.equal(timePeriodRoot.get("id"), id));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, TimePeriod newElement) {

    }

    /*  FILTERS */
    @Override
    public List<TimePeriod> find(Filter<TimePeriod> filter) {
        return null;
    }
}
