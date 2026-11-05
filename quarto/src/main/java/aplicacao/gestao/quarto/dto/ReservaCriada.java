package aplicacao.gestao.quarto.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ReservaCriada(
        UUID eventoId,
        UUID reservaId,
        Long quartoId,
        LocalDate checkIn,
        LocalDate checkOut
) {
}
