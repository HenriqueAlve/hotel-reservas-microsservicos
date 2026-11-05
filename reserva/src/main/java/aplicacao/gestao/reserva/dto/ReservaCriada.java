package aplicacao.gestao.reserva.dto;

import aplicacao.gestao.reserva.infra.Evento;

import java.time.LocalDate;
import java.util.UUID;

public record ReservaCriada(
        UUID eventoId,
        UUID reservaId,
        Long quartoId,
        LocalDate checkIn,
        LocalDate checkOut
) implements Evento {
}
