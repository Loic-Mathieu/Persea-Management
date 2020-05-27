package be.hers.info.persea.dao.options;

import be.hers.info.persea.model.options.Option;

public interface OptionDao {
    /**
     *
     * @return
     */
    Option getOptions();

    /**
     *
     * @param option
     */
    void updateOptions(Option option);
}
