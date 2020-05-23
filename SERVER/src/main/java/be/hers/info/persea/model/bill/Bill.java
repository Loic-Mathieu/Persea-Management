package be.hers.info.persea.model.bill;

import be.hers.info.persea.model.PerseaAuditable;
import be.hers.info.persea.model.time.TimePeriod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "T_BILL")
public class Bill extends PerseaAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBill", nullable = false)
    private Long id;

    @Column(name = "isPaid", nullable = false)
    private boolean paid;

    @Column(nullable = false)
    private long refClient;

    @OneToMany
    private List<TimePeriod> timePeriods;

    @Column(nullable = false)
    private double basePrice;

    @Column(nullable = false)
    private float rate;

    public Bill() {}

    public long getTotalHours() {
        return this.timePeriods.stream()
                .map(timePeriod -> timePeriod.getDuration().toHours())
                .mapToLong(Long::longValue)
                .sum();
    }

    public double getTotalPrice() {
        double basePriceTotal = this.getTotalHours() * basePrice;
        return basePriceTotal + (basePriceTotal * rate);
    }
}
