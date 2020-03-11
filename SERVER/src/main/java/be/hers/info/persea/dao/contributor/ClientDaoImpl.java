package be.hers.info.persea.dao.contributor;

import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.model.document.Tag;
import org.springframework.stereotype.Component;

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

    @Override
    public void addOne(Client newElement) {

    }

    @Override
    public void addAll(List<Client> newElements) {

    }

    @Override
    public Client findById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);

        Root<Client> clientRoot = cq.from(Client.class);
        cq.where(cb.equal(clientRoot.get("id"), id));

        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public Client find(Map<String, String> filter) {
        return null;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void put(long id, Client newElement) {

    }
}
