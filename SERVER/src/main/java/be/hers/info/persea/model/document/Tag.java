package be.hers.info.persea.model.document;

import be.hers.info.persea.model.PerseaAuditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_TAG")
public class Tag extends PerseaAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTag", nullable = false)
    private Long id;

    @Column(name = "tagName", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tagProprety", nullable = false)
    private PerseaProperty property;

    public Tag() {}

    public Tag(String name, PerseaProperty property) {
        this.name = name;
        this.property = property;
    }
}
