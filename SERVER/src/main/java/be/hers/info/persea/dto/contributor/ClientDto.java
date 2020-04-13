package be.hers.info.persea.dto.contributor;

import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.model.contibutor.Gender;
import lombok.Getter;

@Getter
public class ClientDto {
    public final long id;

    public final String lastName;
    public final String firstName;

    public final String gender;

    public final long address;

    public final String phoneNumber;
    public final String mail;

    public ClientDto(Client client) {
        this.id = client.getId();

        this.lastName = client.getLastName();
        this.firstName = client.getFirstName();

        this.gender = client.getGender().toString();

        this.address = client.getAddress().getId();

        this.phoneNumber = client.getPhoneNumber();
        this.mail = client.getMail();
    }
}
