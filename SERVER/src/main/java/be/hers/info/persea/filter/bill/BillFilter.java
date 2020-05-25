package be.hers.info.persea.filter.bill;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.bill.Bill;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Setter
public class BillFilter implements Filter<Bill> {

    private Long caseId;

    @Override
    public Predicate[] doFilter(CriteriaBuilder criteriaBuilder, Root<Bill> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (caseId != null) {
            predicates.add(criteriaBuilder.equal(
                    root.get("id"),
                    caseId
            ));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
