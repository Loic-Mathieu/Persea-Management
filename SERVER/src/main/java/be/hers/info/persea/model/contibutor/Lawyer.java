package be.hers.info.persea.model.contibutor;

import be.hers.info.persea.model.courtCase.CourtCase;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("LAWYER")
public class Lawyer extends Contributor {
    @ManyToMany()
    @JoinTable(
            name = "TJ_LAWYER_CASE",
            joinColumns={@JoinColumn(name="refLawyer", referencedColumnName="idContributor")},
            inverseJoinColumns={@JoinColumn(name="refCase", referencedColumnName="idCase")}
    )
    private List<CourtCase> courtCases;
}
