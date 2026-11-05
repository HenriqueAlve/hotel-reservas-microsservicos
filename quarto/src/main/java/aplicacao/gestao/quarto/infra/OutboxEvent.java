package aplicacao.gestao.quarto.infra;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "outbox")
@Getter
@Setter
@NoArgsConstructor
public class OutboxEvent {

    @Id
    private UUID id;
    private String topic;
    private String aggregateId; // vira a chave da mensagem: mesmo pedido, mesma partição, mesma ordem
    @Column(columnDefinition = "text")
    private String payload;
    private boolean published;
    private Instant createdAt = Instant.now();
}
