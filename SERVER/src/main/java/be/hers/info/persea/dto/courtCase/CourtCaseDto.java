package be.hers.info.persea.dto.courtCase;

import be.hers.info.persea.model.contibutor.Opposition;
import be.hers.info.persea.model.user.User;
import be.hers.info.persea.model.courtCase.CourtCase;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CourtCaseDto {

    private long id;
    private String caseNumber;
    private String state;

    private String creationDate;
    private String closeDate;
    private String paymentDate;

    private double rate;

    private long mainClient;
    private List<Long> clients;
    private long mainOpposition;
    private List<Long> oppositions;
    private long mainLawyer;
    private List<Long> lawyers;

    private List<Long> owners;
    private List<Long> timePeriods;

    // TODO dates

    public CourtCaseDto(CourtCase courtCase) {
        this.caseNumber = courtCase.getCaseNumber();
        this.state = courtCase.getStateType().name();

        this.creationDate = courtCase.getCreationDate() == null ? "" : courtCase.getCreationDate().toString();
        this.closeDate = courtCase.getCloseDate() == null ? "" : courtCase.getCloseDate().toString();
        this.paymentDate = courtCase.getPaymentDate() == null ? "" : courtCase.getPaymentDate().toString();

        this.rate = 0;

        this.mainClient = courtCase.getMainClientId();
        this.mainOpposition = courtCase.getMainOppositionId();
        this.mainLawyer = 0;

        this.clients = new ArrayList<>();
        if (courtCase.getClients() != null) {
            courtCase.getClients().forEach(client -> this.clients.add(client.getId()));
        }
        this.oppositions = new ArrayList<>();
        if (courtCase.getOppositions() != null) {
            courtCase.getOppositions().forEach(opposition -> this.oppositions.add(opposition.getId()));
        }
        this.lawyers = new ArrayList<>();
        // TODO courtCase.getClients().forEach(client -> {this.clients.add(client.getId());});

        this.owners = courtCase.getOwners().stream()
                .map(User::getId)
                .collect(Collectors.toList());
        this.timePeriods = new ArrayList<>();
    }
}
