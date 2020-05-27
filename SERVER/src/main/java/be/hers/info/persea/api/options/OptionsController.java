package be.hers.info.persea.api.options;

import be.hers.info.persea.dto.options.TagOptions;
import be.hers.info.persea.request.options.SaveTagRequest;
import be.hers.info.persea.service.options.OptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/options")
@CrossOrigin(origins = "*")
public class OptionsController {

    private final OptionService optionService;

    public OptionsController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping("/tags")
    public ResponseEntity<TagOptions> getTagsInfo() {
        try {
            TagOptions tagOptions = this.optionService.getTagOptions();
            return new ResponseEntity<>(tagOptions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/tags")
    public ResponseEntity<Boolean> saveTagsInfo(@RequestBody SaveTagRequest body) {
        try {
            this.optionService.saveTagOptions(body);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
