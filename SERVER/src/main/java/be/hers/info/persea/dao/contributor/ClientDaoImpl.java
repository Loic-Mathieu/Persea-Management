package be.hers.info.persea.dao.contributor;

import be.hers.info.persea.model.contibutor.Client;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.Map;

@Component(value = "daoClient")
public class ClientDaoImpl implements ClientDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    /*  === CRUD === */
    @Override
    @Transactional
    public void addOne(Client newElement) {
        this.em.persist(newElement);
    }

    @Override
    @Transactional
    public void addAll(List<Client> newElements) {
        newElements.forEach(em::persist);
    }

    @Override
    public Client getById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);

        System.out.println("OUI");

        Root<Client> clientRoot = cq.from(Client.class);
        cq.where(cb.equal(clientRoot.get("id"), id));

        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, Client newElement) {

    }

    /*  === FILTERS === */
    @Override
    public List<Client> find(Map<String, String> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> clientRoot = cq.from(Client.class);

        if (filter == null) {
            return em.createQuery(cq).getResultList();
        }

        throw new NotImplementedException("Can't be filtered");
    }
}
