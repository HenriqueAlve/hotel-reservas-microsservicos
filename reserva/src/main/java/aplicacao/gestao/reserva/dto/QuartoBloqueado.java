package aplicacao.gestao.reserva.dto;

import aplicacao.gestao.reserva.infra.Evento;

import java.math.BigDecimal;
import java.util.UUID;

public record QuartoBloqueado(
        UUID eventoId,
        UUID reservaId,
        BigDecimal total
) implements Evento {
}
