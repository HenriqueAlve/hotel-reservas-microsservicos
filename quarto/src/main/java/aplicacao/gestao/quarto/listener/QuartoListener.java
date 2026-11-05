package aplicacao.gestao.quarto.listener;

import aplicacao.gestao.quarto.dto.QuartoCriado;
import aplicacao.gestao.quarto.dto.ReservaCriada;
import aplicacao.gestao.quarto.infra.Evento;
import aplicacao.gestao.quarto.service.QuartoService;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuartoListener {

    private final QuartoService service;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "reserva.criada")
    public void reservaCriada(String json) throws Exception{
        service.bloquearReserva(mapper.readValue(json, ReservaCriada.class));
    }
}
