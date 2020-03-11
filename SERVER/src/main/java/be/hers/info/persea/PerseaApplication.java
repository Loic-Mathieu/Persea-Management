package be.hers.info.persea;

import be.hers.info.persea.view.PerseaView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PerseaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerseaApplication.class, args);
        // PerseaView.getInstance().setVisible(true);
    }

}
