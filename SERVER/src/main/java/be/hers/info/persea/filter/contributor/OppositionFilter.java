package be.hers.info.persea.filter.contributor;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.contibutor.Opposition;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
public class OppositionFilter implements Filter<Opposition> {

    // Pagination
    private Integer pageNumber;
    private Integer pageSize;

    @Override
    public Predicate[] doFilter(CriteriaBuilder criteriaBuilder, Root<Opposition> root) {
        return new Predicate[0];
    }
}
