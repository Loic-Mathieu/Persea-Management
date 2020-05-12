package be.hers.info.persea.filter.representation;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.representation.LegalRepresentation;

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
public class RepresentationFilter implements Filter<LegalRepresentation> {

    private Long caseId;
    private String caseNumber;

    @Override
    public Predicate[] doFilter(CriteriaBuilder cb, Root<LegalRepresentation> legalRepresentationRoot) {
        Join<LegalRepresentation, CourtCase> legalRepresentationCourtCaseJoin =
                legalRepresentationRoot.join("courtCase");
        List<Predicate> predicates = new ArrayList<>();

        if (this.caseId != null) {
            predicates.add(cb.equal(
                    legalRepresentationCourtCaseJoin.get("id"),
                    this.caseId
            ));
        }

        if (this.caseNumber!= null) {
            predicates.add(cb.equal(
                    legalRepresentationCourtCaseJoin.get("caseNumber"),
                    this.caseNumber
            ));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
