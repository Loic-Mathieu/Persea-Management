package be.hers.info.persea.model.contibutor;

import be.hers.info.persea.model.courtCase.CourtCase;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("OPPOSITION")
public class Opposition extends Contributor {
    @ManyToMany()
    @JoinTable(
            name = "TJ_OPPOSITION_CASE",
            joinColumns={@JoinColumn(name="refOpposition", referencedColumnName="idContributor")},
            inverseJoinColumns={@JoinColumn(name="refCase", referencedColumnName="idCase")}
    )
    private List<CourtCase> courtCases;
}
