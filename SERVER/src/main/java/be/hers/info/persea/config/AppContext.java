package be.hers.info.persea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@ComponentScan(basePackages = {
    "be.hers.info.persea.dao",
    "be.hers.info.persea.service",
})
public class AppContext {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("persea.database");

    /*  ENTITIES    */
    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }

    @Bean
    public EntityManager getEntityManager() {
        return this.factory.createEntityManager();
    }

    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("PLACE_HOLDER_USERNAME");
    }

    /*  MVC     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting-javaconfig").allowedOrigins("*");
            }
        };
    }
}
