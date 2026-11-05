package aplicacao.gestao.reserva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // liga o @Scheduled do relay do Outbox
public class ReservaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservaApplication.class, args);
    }
}
