package be.hers.info.persea.model.time;

import be.hers.info.persea.model.PerseaAuditable;
import be.hers.info.persea.model.bill.Bill;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "T_TIME_PERIOD")
public class TimePeriod extends PerseaAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTimePeriod", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeriodType periodType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date stopTime;

    @Column(nullable = false)
    private String description;

    @Column(name = "isBilled", nullable = false)
    private boolean billed;

    // money spend for this period
    private double supplement;

    @ManyToOne
    private Bill relatedBill;

    @ManyToOne
    @JoinColumn(name = "refCourtCase", nullable = false)
    private CourtCase courtCase;

    @ManyToOne
    @JoinColumn(name = "refUser", nullable = false)
    private User owner;

    public TimePeriod() {}

    public Duration getDuration() {
        long diff = Math.abs(stopTime.getTime() - startTime.getTime());
        return Duration.ofMillis(diff);
    }
}
