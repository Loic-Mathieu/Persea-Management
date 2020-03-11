package be.hers.info.persea.model.courtCase;

import be.hers.info.persea.model.LegalRepresentation;
import be.hers.info.persea.model.courtCase.caseState.CaseState;
import be.hers.info.persea.model.courtCase.caseState.CaseStateContext;
import be.hers.info.persea.model.courtCase.caseState.CaseStateType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "T_CASE")
public class CourtCase {
    @Id
    @Column(name = "caseNumber", nullable = false)
    private String caseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "caseState", nullable = false)
    private CaseStateType stateType;
    @Transient
    private CaseState state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closeDate")
    private Date closeDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "paymentDate")
    private Date paymentDate;

    @OneToMany(mappedBy = "courtCase")
    List<LegalRepresentation> legalRepresentations;

    public CaseState getCaseState() {
        if (state == null) {
            this.state = CaseStateContext.translateState(stateType);
        }

        return state;
    }

    public CourtCase() {}

    public CourtCase(String caseNumber) {
        this.caseNumber = caseNumber;
        this.stateType = CaseStateType.Open;

        this.creationDate = Calendar.getInstance().getTime();
    }
}
