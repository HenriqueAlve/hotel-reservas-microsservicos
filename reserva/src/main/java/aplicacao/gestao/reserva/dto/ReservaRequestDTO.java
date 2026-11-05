package aplicacao.gestao.reserva.dto;

import java.time.LocalDate;

public record ReservaRequestDTO(
        String hospedeId,
         Long quartoId,
         LocalDate checkIn,
         LocalDate checkOut
) {
}
