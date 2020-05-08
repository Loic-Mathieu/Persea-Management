package be.hers.info.persea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {
    "be.hers.info.persea.dao",
    "be.hers.info.persea.service",
})
public class AppContext {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("persea.database");

    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }

    @Bean
    public EntityManager getEntityManager() {
        return this.factory.createEntityManager();
    }

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
