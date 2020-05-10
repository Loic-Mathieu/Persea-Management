package be.hers.info.persea.model.representation;

import be.hers.info.persea.model.PerseaAuditable;
import be.hers.info.persea.model.courtCase.CourtCase;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity(name = "PERSEA_REPRESENTATION")
@Table(name = "T_REPRESENTATION")
public class LegalRepresentation extends PerseaAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRepresentation", nullable = false)
    private Long id;

    @Column(name = "eventSubject", nullable = false)
    private String subject;

    @Column(name = "eventLocation", nullable = false)
    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "eventDate", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "refCase", nullable = false)
    private CourtCase courtCase;

    /*  CONSTRUCTORS    */
    public LegalRepresentation() {}

    public LegalRepresentation(String subject, String emplacement, Date date) {
        this.subject = subject;
        this.location = emplacement;
        this.date = date;
    }
}
