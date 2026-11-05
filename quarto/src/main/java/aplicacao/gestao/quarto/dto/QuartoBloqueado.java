package aplicacao.gestao.quarto.dto;

import aplicacao.gestao.quarto.infra.Evento;

import java.math.BigDecimal;
import java.util.UUID;

public record QuartoBloqueado(
        UUID eventoId,
        UUID reservaId,
        BigDecimal total
) implements Evento {
}
