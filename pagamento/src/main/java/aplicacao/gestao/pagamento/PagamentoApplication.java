package aplicacao.gestao.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // liga o @Scheduled do relay do Outbox
public class PagamentoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PagamentoApplication.class, args);
    }
}
