package be.hers.info.persea.dao.tag;

import be.hers.info.persea.model.document.Tag;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component(value = "daoTag")
public class TagDaoImpl implements TagDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public Tag findByName(String tagName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
        Root<Tag> tagsRoot = cq.from(Tag.class);

        cq.where(cb.equal(tagsRoot.get("name"), tagName));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void addOne(Tag newElement) {
        em.getTransaction().begin();
        em.persist(newElement);
        System.out.println("Added");
        em.getTransaction().commit();
    }

    @Override
    public void addAll(List<Tag> newElements) {

    }

    @Override
    public Tag findById(long id) {
        return null;
    }

    @Transactional
    @Override
    public void remove(long id) {

    }

    @Transactional
    @Override
    public void put(long id, Tag newElement) {

    }
}
