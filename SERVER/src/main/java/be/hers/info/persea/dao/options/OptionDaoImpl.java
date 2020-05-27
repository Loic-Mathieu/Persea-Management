package be.hers.info.persea.dao.options;

import be.hers.info.persea.model.options.Option;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component(value = "daoOption")
public class OptionDaoImpl implements OptionDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public Option getOptions() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Option> cq = cb.createQuery(Option.class);

        Root<Option> rootEntry = cq.from(Option.class);

        return em.createQuery(cq.select(rootEntry))
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void updateOptions(Option option) {
        this.em.merge(option);
    }
}
