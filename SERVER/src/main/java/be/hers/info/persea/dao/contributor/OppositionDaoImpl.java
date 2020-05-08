package be.hers.info.persea.dao.contributor;

import be.hers.info.persea.model.contibutor.Opposition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "daoOpposition")
public class OppositionDaoImpl implements OppositionDao {
    @Override
    public void addOne(Opposition newElement) {

    }

    @Override
    public void addAll(List<Opposition> newElements) {

    }

    @Override
    public Opposition getById(long id) {
        return null;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, Opposition newElement) {

    }
}
