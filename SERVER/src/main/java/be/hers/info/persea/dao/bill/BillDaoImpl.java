package be.hers.info.persea.dao.bill;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.bill.Bill;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component(value = "daoBill")
public class BillDaoImpl implements BillDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    @Transactional
    public void addOne(Bill newElement) {
        this.em.persist(newElement);
    }

    @Override
    @Transactional
    public void addAll(List<Bill> newElements) {
        newElements.forEach(em::persist);
    }

    @Override
    public Bill getById(long id) {
        return null;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, Bill newElement) {

    }

    @Override
    public List<Bill> find(Filter<Bill> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bill> cq = cb.createQuery(Bill.class);

        Root<Bill> billRoot = cq.from(Bill.class);

        cq.where(filter.doFilter(cb, billRoot)).distinct(true);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Bill> findByIds(List<Long> ids) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bill> cq = cb.createQuery(Bill.class);

        Root<Bill> billRoot = cq.from(Bill.class);

        cq.where(billRoot.get("id").in(ids));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public long getLastNumber(long clientId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Bill> billRoot = cq.from(Bill.class);

        cq.where(cb.equal(billRoot.get("refClient"), clientId));
        return em.createQuery(cq.select(cb.countDistinct(billRoot))).getSingleResult();
    }
}
