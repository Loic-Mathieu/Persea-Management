package be.hers.info.persea.api.contributor;

import be.hers.info.persea.dao.contributor.ClientDao;
import be.hers.info.persea.dto.contributor.ClientDto;
import be.hers.info.persea.model.contibutor.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<ClientDto> getStudents(@PathVariable long id) {
        Client client = this.clientDao.findById(id);
        return new ResponseEntity<>(new ClientDto(client), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> createStudent(RequestBody requestBody) {
        return new ResponseEntity<Long>(0L, HttpStatus.CREATED);
    }
}
