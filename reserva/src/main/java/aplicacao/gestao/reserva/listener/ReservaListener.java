package aplicacao.gestao.reserva.listener;

import aplicacao.gestao.reserva.dto.QuartoBloqueado;
import aplicacao.gestao.reserva.dto.QuartoIndisponivel;
import aplicacao.gestao.reserva.service.ReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservaListener {

    private final ReservaService reservaService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "quarto.bloqueado")
    public void quartoReservaBloqueado(String json) throws Exception{
        reservaService.quartoBloqueado(objectMapper.readValue(json, QuartoBloqueado.class));
    }

    @KafkaListener(topics = "quarto.indisponivel")
    public void quartoReservaIndisponivel(String json) throws Exception{
        reservaService.quartoIndisponivel(objectMapper.readValue(json, QuartoIndisponivel.class));
    }

}
