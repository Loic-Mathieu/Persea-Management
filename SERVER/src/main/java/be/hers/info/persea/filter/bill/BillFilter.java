package be.hers.info.persea.filter.bill;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.bill.Bill;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.time.TimePeriod;
import lombok.Setter;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Setter
public class BillFilter implements Filter<Bill> {

    private Long caseId;

    @Override
    public Predicate[] doFilter(CriteriaBuilder criteriaBuilder, Root<Bill> root) {
        List<Predicate> predicates = new ArrayList<>();

        Join<Bill, TimePeriod> timePeriodJoin = root.join("timePeriods", JoinType.LEFT);

        if (caseId != null) {
            Join<TimePeriod, CourtCase> courtCaseJoin = timePeriodJoin.join("courtCase", JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(
                    courtCaseJoin.get("id"),
                    caseId
            ));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
