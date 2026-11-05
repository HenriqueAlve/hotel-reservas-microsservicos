package aplicacao.gestao.reserva.service;

import aplicacao.gestao.reserva.dto.QuartoBloqueado;
import aplicacao.gestao.reserva.dto.QuartoIndisponivel;
import aplicacao.gestao.reserva.dto.ReservaCriada;
import aplicacao.gestao.reserva.dto.ReservaRequestDTO;

import aplicacao.gestao.reserva.enums.Status;
import aplicacao.gestao.reserva.infra.Idempotencia;
import aplicacao.gestao.reserva.infra.Outbox;
import aplicacao.gestao.reserva.model.Reserva;
import aplicacao.gestao.reserva.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository repository;
    private final Outbox outbox;
    private final Idempotencia  idempotencia;

    @Transactional
    public Reserva criarReserva(ReservaRequestDTO requestDTO) {
        Reserva reserva = new Reserva();
        reserva.setHospedeId(requestDTO.hospedeId());
        reserva.setQuartoId(requestDTO.quartoId());
        reserva.setCheckIn(requestDTO.checkIn());
        reserva.setCheckOut(requestDTO.checkOut());

        var reservaSalva = repository.save(reserva);
        outbox.registrarEvento(
                "reserva.criada",reservaSalva.getId().toString(),new ReservaCriada(UUID.randomUUID(), reservaSalva.getId(),reservaSalva.getQuartoId(),reservaSalva.getCheckIn(),
                        reservaSalva.getCheckOut())
        );
        return reservaSalva;
    }

    @Transactional
    public void quartoBloqueado(QuartoBloqueado quartoBloqueado) {
        if(!idempotencia.primeiraVez(quartoBloqueado.eventoId())){
            return;
        }
        var reserva = repository.findById(quartoBloqueado.reservaId());
        if(reserva.isEmpty()) return;
        var r = reserva.get();
        if(r.getStatus() != Status.PENDENTE) return;
        r.setTotal(quartoBloqueado.total());
        r.setStatus(Status.CONFIRMADA);
    }

    @Transactional
    public void quartoIndisponivel(QuartoIndisponivel quartoIndisponivel) {
        if(!idempotencia.primeiraVez(quartoIndisponivel.eventoId())){
            return;
        }
        var reserva = repository.findById(quartoIndisponivel.reservaId());
        if (reserva.isEmpty()) return;
        var r = reserva.get();
        if(r.getStatus() != Status.PENDENTE) return;
        r.setStatus(Status.CANCELADA);

    }
}
