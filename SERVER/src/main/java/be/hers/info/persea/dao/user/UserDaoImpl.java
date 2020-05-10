package be.hers.info.persea.dao.user;

import be.hers.info.persea.model.user.User;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component(value = "daoUser")
public class UserDaoImpl implements UserDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void addOne(User newElement) {

    }

    @Override
    public void addAll(List<User> newElements) {

    }

    @Override
    public User getById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> UserRoot = cq.from(User.class);
        cq.where(cb.equal(UserRoot.get("id"), id));

        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, User newElement) {

    }

    @Override
    public int getCasesCount(long userId) {
        return 0;
    }
}
