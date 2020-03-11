package be.hers.info.persea.api.contributor;

import be.hers.info.persea.dao.contributor.ClientDao;
import be.hers.info.persea.model.contibutor.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/client")
public class ClientController {
    private ClientDao clientDao;

    @Autowired
    public ClientController(ClientDao clientDao) {
        assert clientDao != null;

        this.clientDao = clientDao;
    }

    @GetMapping("")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<Client> getStudents(@PathVariable long id) {
        Client client = this.clientDao.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
