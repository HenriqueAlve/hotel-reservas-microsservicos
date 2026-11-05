package aplicacao.gestao.reserva.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor

public class OutboxRelay {

    private final OutboxRepository repository;
    private final KafkaTemplate<String, String> kafka;

    @Scheduled(fixedDelay = 500)
    @Transactional
    public void publicar() throws Exception {
        for(var outboxEven : repository.proximos()){
            kafka.send(outboxEven.getTopic(), outboxEven.getAggregateId(), outboxEven.getPayload()).get();
            outboxEven.setPublished(true);
        }
    }

}
