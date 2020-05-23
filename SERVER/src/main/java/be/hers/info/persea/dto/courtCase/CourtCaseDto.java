package be.hers.info.persea.dto.courtCase;

import be.hers.info.persea.model.time.TimePeriod;
import be.hers.info.persea.model.user.User;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.util.time.PerseaTime;
import lombok.Getter;

import java.util.ArrayList;
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
        this.id = courtCase.getId();
        this.caseNumber = courtCase.getCaseNumber();
        this.state = courtCase.getStateType().name();

        this.creationDate = courtCase.getCreationDate() == null ? ""
                : PerseaTime.getStandardFormattedDate(courtCase.getCreationDate());
        this.closeDate = courtCase.getCloseDate() == null ? ""
                : PerseaTime.getStandardFormattedDate(courtCase.getCloseDate());
        this.paymentDate = courtCase.getPaymentDate() == null ? ""
                : PerseaTime.getStandardFormattedDate(courtCase.getPaymentDate());

        this.rate = 0;

        this.mainClient = courtCase.getMainClientId();
        this.mainOpposition = courtCase.getMainOppositionId();
        this.mainLawyer = 0;

        this.clients = new ArrayList<>();
        if (courtCase.getClients() != null) {
            courtCase.getClients().forEach(client -> {
                if (client.getId() != courtCase.getMainClientId()) {
                    this.clients.add(client.getId());
                }
            });
        }
        this.oppositions = new ArrayList<>();
        if (courtCase.getOppositions() != null) {
            courtCase.getOppositions().forEach(opposition -> {
                if(opposition.getId() != courtCase.getMainOppositionId()) {
                    this.oppositions.add(opposition.getId());
                }
            });
        }
        this.lawyers = new ArrayList<>();
        // TODO courtCase.getClients().forEach(client -> {this.clients.add(client.getId());});

        this.owners = courtCase.getOwners().stream()
                .map(User::getId)
                .collect(Collectors.toList());
        this.timePeriods = courtCase.getTimePeriods().stream()
                .map(TimePeriod::getId)
                .collect(Collectors.toList());
    }
}
