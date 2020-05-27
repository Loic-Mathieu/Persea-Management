package be.hers.info.persea.model.options;

import be.hers.info.persea.model.PerseaAuditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_OPTIONS")
public class Option extends PerseaAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOption", nullable = false)
    private Long id;

    @Column(nullable = false)
    private double basePrice;

    @Column(nullable = false)
    private String leftTagMember;

    @Column(nullable = false)
    private String rightTagMember;

}
