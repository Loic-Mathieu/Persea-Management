package be.hers.info.persea.dao.contributor;

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

@Component(value = "daoOpposition")
public class OppositionDaoImpl implements OppositionDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    @Transactional
    public void addOne(Opposition newElement) {
        em.persist(newElement);
    }

    @Override
    @Transactional
    public void addAll(List<Opposition> newElements) {
        newElements.forEach(em::persist);
    }

    @Override
    public Opposition getById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Opposition> cq = cb.createQuery(Opposition.class);

        Root<Opposition> oppositionRoot = cq.from(Opposition.class);
        cq.where(cb.equal(oppositionRoot.get("id"), id));

        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, Opposition newElement) {

    }
}
