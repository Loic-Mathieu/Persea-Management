package be.hers.info.persea.dao.address;

import be.hers.info.persea.model.address.Address;
import be.hers.info.persea.model.contibutor.Client;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component(value = "daoAddress")
public class AddressDaoImpl implements AddressDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void addOne(Address newElement) {

    }

    @Override
    public void addAll(List<Address> newElements) {

    }

    @Override
    public Address getById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Address> cq = cb.createQuery(Address.class);

        Root<Address> addressRoot = cq.from(Address.class);
        cq.where(cb.equal(addressRoot.get("id"), id));

        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, Address newElement) {

    }
}
