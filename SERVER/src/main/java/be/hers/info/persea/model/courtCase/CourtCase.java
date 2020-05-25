package be.hers.info.persea.model.courtCase;

import be.hers.info.persea.model.PerseaAuditable;
import be.hers.info.persea.model.representation.LegalRepresentation;
import be.hers.info.persea.model.time.TimePeriod;
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
    @JoinTable(
            name = "TJ_CLIENT_CASE",
            inverseJoinColumns={@JoinColumn(name="refClient", referencedColumnName="idContributor")},
            joinColumns={@JoinColumn(name="refCase", referencedColumnName="idCase")}
    )
    List<Client> clients;

    @Column(nullable = false)
    private long mainOppositionId;
    @ManyToMany()
    @JoinTable(
            name = "TJ_OPPOSITION_CASE",
            inverseJoinColumns={@JoinColumn(name="refOpposition", referencedColumnName="idContributor")},
            joinColumns={@JoinColumn(name="refCase", referencedColumnName="idCase")}
    )
    List<Opposition> oppositions;

    @OneToMany(mappedBy = "courtCase")
    private List<LegalRepresentation> legalRepresentations;

    @OneToMany(mappedBy = "courtCase")
    private List<TimePeriod> timePeriods;

    @ManyToMany
    @JoinTable(
            name = "TJ_USER_CASE",
            inverseJoinColumns={@JoinColumn(name="refUser", referencedColumnName="idUser")},
            joinColumns={@JoinColumn(name="refCase", referencedColumnName="idCase")}
    )
    private List<User> owners;

    public CaseState getState() {
        if (state == null) {
            this.state = CaseStateContext.getInstance().translateState(stateType);
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

    /**
     *
     * @param date
     * @return
     */
    public CaseState nextState(Date date) {
        this.getState().nextState(this, date);
        return this.state;
    }
}
