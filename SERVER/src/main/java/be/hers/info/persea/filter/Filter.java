package be.hers.info.persea.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Filter<T> {
    Predicate[] doFilter(CriteriaBuilder criteriaBuilder, Root<T> root);
}
