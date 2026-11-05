package aplicacao.gestao.quarto.dto;

import java.util.UUID;

public record QuartoCriado(
        UUID eventId, UUID pedidoId
) {
}
