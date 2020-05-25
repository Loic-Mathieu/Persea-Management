package be.hers.info.persea.filter.contributor;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.model.courtCase.CourtCase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClientFilter implements Filter<Client> {

    private Long caseId;
    private String caseNumber;

    // Pagination
    private Integer pageNumber;
    private Integer pageSize;

    @Override
    public Predicate[] doFilter(CriteriaBuilder cb, Root<Client> root) {
        Join<Client, CourtCase> courtCaseJoin = root.join("courtCases", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (caseId != null) {
            predicates.add(cb.equal(
                    courtCaseJoin.get("id"),
                    this.caseId
            ));
        }

        if (caseNumber != null) {
            predicates.add(cb.equal(
                    courtCaseJoin.get("caseNumber"),
                    this.caseNumber
            ));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
