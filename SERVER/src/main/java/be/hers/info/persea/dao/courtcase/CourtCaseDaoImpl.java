package be.hers.info.persea.dao.courtcase;

import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.model.courtCase.CourtCase;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Component(value = "daoCourtCase")
public class CourtCaseDaoImpl implements CourtCaseDao {
    @PersistenceContext(name = "localDatabase", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void addOne(CourtCase newElement) {

    }

    @Override
    public void addAll(List<CourtCase> newElements) {

    }

    @Override
    public CourtCase getById(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CourtCase> cq = cb.createQuery(CourtCase.class);

        Root<CourtCase> courtCaseRoot = cq.from(CourtCase.class);
        cq.where(cb.equal(courtCaseRoot.get("id"), id));

        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, CourtCase newElement) {

    }

    @Override
    public List<CourtCase> find(Map<String, String> filter) {
        return null;
    }

    @Override
    public List<CourtCase> findByUser() {
        return null;
    }
}
