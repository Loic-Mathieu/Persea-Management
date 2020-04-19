package be.hers.info.persea.model.contibutor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Contributor {
}
