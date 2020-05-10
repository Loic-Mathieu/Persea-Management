package be.hers.info.persea.model.courtCase;

import be.hers.info.persea.model.PerseaAuditable;
import be.hers.info.persea.model.representation.LegalRepresentation;
import be.hers.info.persea.model.user.User;
import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.model.contibutor.Opposition;
import be.hers.info.persea.model.courtCase.caseState.CaseState;
import be.hers.info.persea.model.courtCase.caseState.CaseStateContext;
import be.hers.info.persea.model.courtCase.caseState.CaseStateKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "T_CASE")
public class CourtCase extends PerseaAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCase", nullable = false)
    private long id;

    @Column(name = "caseNumber", nullable = false, unique = true)
    private String caseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "caseState", nullable = false)
    private CaseStateKey stateType;
    @Transient
    private CaseState state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closeDate")
    private Date closeDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "paymentDate")
    private Date paymentDate;

    @Column(nullable = false)
    private long mainClientId;
    @ManyToMany()
    List<Client> clients;

    @Column(nullable = false)
    private long mainOppositionId;
    @ManyToMany
    List<Opposition> oppositions;

    @OneToMany(mappedBy = "courtCase")
    private List<LegalRepresentation> legalRepresentations;

    @ManyToMany
    private List<User> owners;

    public CaseState getState() {
        if (state == null) {
            this.state = CaseStateContext.translateState(stateType);
        }

        return state;
    }

    public CourtCase() {}

    public CourtCase(String caseNumber) {
        this.caseNumber = caseNumber;
        this.stateType = CaseStateKey.OPEN;

        this.clients = new ArrayList<>();
        this.oppositions = new ArrayList<>();

        this.legalRepresentations = new ArrayList<>();
    }
}
