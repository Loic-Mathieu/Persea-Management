package be.hers.info.persea.config;

import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class RessourceConfig {
    private static RessourceConfig instance = null;

    @Getter
    private Resource resource = new ClassPathResource("application.properties");

    public static RessourceConfig getInstance() {
        if(instance == null)
            RessourceConfig.instance = new RessourceConfig();

        return RessourceConfig.instance;
    }
}
