package aplicacao.gestao.quarto.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Idempotencia {

    private final ProcessedEventRepository repo;
    /** true = primeira vez; false = duplicado, ignore. Chame dentro de uma transação. */
    public boolean primeiraVez(UUID eventId) {
        if (repo.existsById(eventId)) return false;
        repo.save(new ProcessedEvent(eventId));
        return true;
    }

}
