package be.hers.info.persea.api.contributor;

import be.hers.info.persea.dao.contributor.ClientDao;
import be.hers.info.persea.dto.contributor.ClientDto;
import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.request.contributor.CreateClientRequest;
import be.hers.info.persea.service.contributor.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/clients")
public class ClientController {
    private ClientDao clientDao;
    private ClientService clientService;

    @Autowired
    public ClientController(ClientDao clientDao, ClientService clientService) {
        assert clientDao != null;
        assert clientService != null;

        this.clientService = clientService;
        this.clientDao = clientDao;
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<ClientDto> getClient(@PathVariable long id) {
        Client client = this.clientDao.getById(id);
        return new ResponseEntity<>(new ClientDto(client), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ClientDto>> getClients() {
        List<ClientDto> clients = this.clientDao.find(null).stream()
                .map(ClientDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> postClient(HttpServletRequest request,
                                           @RequestBody CreateClientRequest body) {
        long id;
        try {
            id = this.clientService.createClient(body);
        } catch (Exception ex) {
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
