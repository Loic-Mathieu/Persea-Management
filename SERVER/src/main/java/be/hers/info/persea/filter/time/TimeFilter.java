package be.hers.info.persea.filter.time;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.time.TimePeriod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TimeFilter implements Filter<TimePeriod> {
    private String caseNumber;

    private Boolean billed = false;

    @Override
    public Predicate[] doFilter(CriteriaBuilder criteriaBuilder, Root<TimePeriod> root) {
        Join<TimePeriod, CourtCase> courtCaseJoin = root.join("courtCase");

        List<Predicate> predicates = new ArrayList<>();

        if (caseNumber != null) {
            predicates.add(criteriaBuilder.equal(
                    courtCaseJoin.get("caseNumber"),
                    caseNumber
            ));
        }

        if (billed != null) {
            predicates.add(criteriaBuilder.equal(
                    root.get("billed"),
                    billed
            ));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
