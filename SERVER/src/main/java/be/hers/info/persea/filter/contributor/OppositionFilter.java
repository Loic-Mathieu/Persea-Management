package be.hers.info.persea.filter.contributor;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.contibutor.Opposition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OppositionFilter implements Filter<Opposition> {
    @Override
    public Predicate[] doFilter(CriteriaBuilder criteriaBuilder, Root<Opposition> root) {
        return new Predicate[0];
    }
}
