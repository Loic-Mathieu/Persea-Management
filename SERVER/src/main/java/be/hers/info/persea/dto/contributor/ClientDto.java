package be.hers.info.persea.dto.contributor;

import be.hers.info.persea.model.contibutor.Client;
import lombok.Getter;

@Getter
public class ClientDto {
    private final long id;

    private final String lastName;
    private final String firstName;

    private final int gender;

    private final long address;

    private final String phoneNumber;
    private final String mail;

    public ClientDto(Client client) {
        this.id = client.getId();

        this.lastName = client.getLastName();
        this.firstName = client.getFirstName();

        this.gender = client.getGender().code;

        this.address = client.getAddress().getId();

        this.phoneNumber = client.getPhoneNumber();
        this.mail = client.getMail();
    }
}
