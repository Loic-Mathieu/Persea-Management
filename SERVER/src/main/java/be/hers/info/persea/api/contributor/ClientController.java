package be.hers.info.persea.api.contributor;

import be.hers.info.persea.dao.contributor.ClientDao;
import be.hers.info.persea.dto.contributor.ClientDto;
import be.hers.info.persea.dto.courtCase.CourtCaseDto;
import be.hers.info.persea.filter.contributor.ClientFilter;
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
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/{id:[0-9]+}/cases")
    public ResponseEntity<List<CourtCaseDto>> getCases(@PathVariable long id) {
        Client client = this.clientDao.getById(id);
        List<CourtCaseDto> linkedCases = client.getCourtCases().stream()
                .map(CourtCaseDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(linkedCases, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientDto>> findClients(@RequestParam List<Long> ids) {
        try {
            List<ClientDto> clients = this.clientDao.findByIds(ids).stream()
                    .map(ClientDto::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ClientDto>> getClients(@ModelAttribute ClientFilter filter) {
        List<ClientDto> clients = this.clientDao.find(filter).stream()
                .map(ClientDto::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/size")
    public ResponseEntity<Long> getClientsNumber(@ModelAttribute ClientFilter filter) {
        try {
            long size = this.clientDao.getSize(filter);
            return new ResponseEntity<>(size, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
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
