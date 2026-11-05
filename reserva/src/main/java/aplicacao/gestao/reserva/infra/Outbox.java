package aplicacao.gestao.reserva.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Outbox {

    private final OutboxRepository repository;
    private final ObjectMapper mapper;

    @Transactional(propagation = Propagation.MANDATORY)
    public void registrarEvento(String topic, String aggregateId, Evento evento){
        var registro = new OutboxEvent();
        registro.setId(evento.eventoId());
        registro.setTopic(topic);
        registro.setAggregateId(aggregateId);
        try {
            registro.setPayload(mapper.writeValueAsString(evento));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
        repository.save(registro);
    }
}
