package be.hers.info.persea.service.options;

import be.hers.info.persea.dto.options.TagOptions;
import be.hers.info.persea.request.options.SaveTagRequest;

public interface OptionService {
    /**
     *
     * @return
     */
    TagOptions getTagOptions();

    /**
     *
     * @param request
     */
    void saveTagOptions(SaveTagRequest request);
}
