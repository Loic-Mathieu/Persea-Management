package be.hers.info.persea.filter.courtCase;

import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.model.contibutor.Opposition;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourtCaseFilter implements Filter<CourtCase> {

    // User id
    private Long owner;

    private Long mainClientId;
    private List<Long> clientIds;

    private Long mainOppositionId;
    private List<Long> oppositionIds;

    @Override
    public Predicate[] doFilter(CriteriaBuilder cb, Root<CourtCase> root) {
        Join<CourtCase, User> userJoin = root.join("owners");
        Join<CourtCase, Client> clientJoin = root.join("clients", JoinType.LEFT);
        Join<CourtCase, Opposition> oppositionJoin = root.join("oppositions", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (owner != null) {
            predicates.add(cb.equal(
                    userJoin.get("id"),
                    this.owner
            ));
            System.out.println("FILTER " + owner);
        }

        if (mainClientId != null) {
            predicates.add(cb.equal(
                    root.get("mainClientId"),
                    this.mainClientId
            ));
        }

        if (clientIds != null && !clientIds.isEmpty()) {
            predicates.add(clientJoin.get("id").in(clientIds));
        }

        if (mainOppositionId != null) {
            predicates.add(cb.equal(
                    root.get("mainOppositionId"),
                    this.mainOppositionId
            ));
        }

        if (oppositionIds != null && !oppositionIds.isEmpty()) {
            predicates.add(oppositionJoin.get("id").in(oppositionIds));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
