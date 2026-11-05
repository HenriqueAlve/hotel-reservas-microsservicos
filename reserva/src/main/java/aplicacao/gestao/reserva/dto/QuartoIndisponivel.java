package aplicacao.gestao.reserva.dto;

import aplicacao.gestao.reserva.infra.Evento;

import java.util.UUID;

public record QuartoIndisponivel(
        UUID eventoId,
        UUID reservaId,
        String motivo
) implements Evento {
}
