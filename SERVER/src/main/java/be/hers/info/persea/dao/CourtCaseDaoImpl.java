package be.hers.info.persea.dao;

import be.hers.info.persea.model.courtCase.CourtCase;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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
        return null;
    }

    @Override
    public List<CourtCase> getAll() {
        return null;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void put(long id, CourtCase newElement) {

    }

    @Override
    public CourtCase find(Map<String, String> filter) {
        return null;
    }

    @Override
    public List<CourtCase> findByUser() {
        return null;
    }
}
