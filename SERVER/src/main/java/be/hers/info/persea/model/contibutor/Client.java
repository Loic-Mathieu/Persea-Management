package be.hers.info.persea.model.contibutor;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Contributor {
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "T_LAWYER_CLIENT",
            joinColumns = @JoinColumn(name = "refClient")
    )
    private List<Lawyer> lawyers;
}
