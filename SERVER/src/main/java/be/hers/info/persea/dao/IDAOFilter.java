package be.hers.info.persea.dao;

import java.util.Map;

public interface IDAOFilter<T> {
        T find(Map<String, String> filter);
}
