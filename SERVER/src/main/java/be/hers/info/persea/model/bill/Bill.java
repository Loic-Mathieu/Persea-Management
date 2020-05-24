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

    @Column(nullable = false, unique = true)
    private String billNumber;

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
        long totalHours = 0;
        for (TimePeriod timePeriod : timePeriods) {
            totalHours += timePeriod.getDuration().toHours();
        }

        return totalHours;
    }

    public double getTotalPrice() {
        double basePriceTotal = this.getTotalHours() * basePrice;
        double finalPrice = basePriceTotal + (basePriceTotal * rate);
        return Math.round(finalPrice * 100.0) / 100.0; // rounded to get only two digits
    }
}
