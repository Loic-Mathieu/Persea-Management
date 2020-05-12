package be.hers.info.persea.dao;

import be.hers.info.persea.filter.Filter;

import java.util.List;

public interface IDAOFilter<T> {
        List<T> find(Filter<T> filter);
}
