package be.hers.info.persea.dto.options;

import be.hers.info.persea.model.options.Option;
import lombok.Getter;

@Getter
public class AppOptions {

    private final double basePrice;

    private final String leftTagMember;
    private final String rightTagMember;

    public AppOptions(Option option) {
        this.basePrice = option.getBasePrice();

        this.leftTagMember = option.getLeftTagMember();
        this.rightTagMember = option.getRightTagMember();

    }
}
