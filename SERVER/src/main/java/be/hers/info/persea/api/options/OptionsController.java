package be.hers.info.persea.api.options;

import be.hers.info.persea.dao.options.OptionDao;
import be.hers.info.persea.dto.options.AppOptions;
import be.hers.info.persea.dto.options.TagOptions;
import be.hers.info.persea.request.options.SaveAppOptions;
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
    private final OptionDao optionDao;

    public OptionsController(OptionService optionService, OptionDao optionDao) {
        this.optionService = optionService;
        this.optionDao = optionDao;
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

    @GetMapping("/app")
    public ResponseEntity<AppOptions> getAppInfos() {
        try {
            AppOptions appOptions = new AppOptions(this.optionDao.getOptions());
            return new ResponseEntity<>(appOptions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/app")
    public ResponseEntity<Boolean> saveAppInfos(@RequestBody SaveAppOptions body) {
        try {
            this.optionService.saveAppOptions(body);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
