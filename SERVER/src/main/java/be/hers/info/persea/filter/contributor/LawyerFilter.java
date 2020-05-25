package be.hers.info.persea.filter.contributor;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.contibutor.Lawyer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
public class LawyerFilter implements Filter<Lawyer> {

    // Pagination
    private Integer pageNumber;
    private Integer pageSize;

    @Override
    public Predicate[] doFilter(CriteriaBuilder criteriaBuilder, Root<Lawyer> root) {
        return new Predicate[0];
    }
}
