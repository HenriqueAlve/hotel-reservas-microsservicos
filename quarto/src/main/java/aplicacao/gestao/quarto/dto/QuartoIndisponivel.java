package aplicacao.gestao.quarto.dto;

import aplicacao.gestao.quarto.infra.Evento;

import java.util.UUID;

public record QuartoIndisponivel(
        UUID eventoId,
        UUID reservaId,
        String motivo

) implements Evento {
}
