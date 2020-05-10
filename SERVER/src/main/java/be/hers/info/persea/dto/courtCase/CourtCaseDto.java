package be.hers.info.persea.dto.courtCase;

import be.hers.info.persea.model.user.User;
import be.hers.info.persea.model.courtCase.CourtCase;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CourtCaseDto {

    private long id;
    private String caseNumber;
    private String state;

    private List<Long> owners;

    // TODO dates

    public CourtCaseDto(CourtCase courtCase) {
        this.caseNumber = courtCase.getCaseNumber();
        this.state = courtCase.getStateType().name();

        this.owners = courtCase.getOwners().stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }
}
