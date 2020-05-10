package be.hers.info.persea.service.contributor;

import be.hers.info.persea.dao.contributor.OppositionDao;
import be.hers.info.persea.request.contributor.CreateOppositionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "serviceOpposition")
public class OppositionServiceImpl implements OppositionService {
    private final OppositionDao oppositionDao;

    @Autowired
    public OppositionServiceImpl(OppositionDao oppositionDao) {
        assert oppositionDao != null;

        this.oppositionDao = oppositionDao;
    }

    @Override
    public long createOpposition(CreateOppositionRequest request) {
        return 0;
    }
}
