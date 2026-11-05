package aplicacao.gestao.quarto.infra;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "processed_event")
@Getter
@NoArgsConstructor
public class ProcessedEvent {

    @Id
    private UUID id;
    private Instant processedAt = Instant.now();
    public ProcessedEvent(UUID id) {
        this.id = id;
    }
}
