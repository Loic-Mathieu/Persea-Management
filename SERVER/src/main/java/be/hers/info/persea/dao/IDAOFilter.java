package be.hers.info.persea.dao;

import java.util.List;
import java.util.Map;

public interface IDAOFilter<T> {
        List<T> find(Map<String, String> filter);
}
