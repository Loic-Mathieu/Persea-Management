package be.hers.info.persea.dao.address;

import be.hers.info.persea.model.address.Address;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void addOne(Address newElement) {
        this.em.persist(newElement);
    }

    @Override
    public void addAll(List<Address> newElements) {
        newElements.forEach(this.em::persist);
    }

    @Override
    @Transactional
    public Address getById(long id) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<Address> cq = cb.createQuery(Address.class);

        Root<Address> addressRoot = cq.from(Address.class);
        cq.where(cb.equal(addressRoot.get("id"), id));

        return this.em.createQuery(cq).getSingleResult();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, Address newElement) {

    }
}
