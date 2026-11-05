package aplicacao.gestao.quarto.service;

import aplicacao.gestao.quarto.dto.QuartoBloqueado;
import aplicacao.gestao.quarto.dto.QuartoIndisponivel;
import aplicacao.gestao.quarto.dto.ReservaCriada;
import aplicacao.gestao.quarto.infra.Idempotencia;
import aplicacao.gestao.quarto.infra.Outbox;
import aplicacao.gestao.quarto.model.Bloqueio;
import aplicacao.gestao.quarto.model.Quarto;
import aplicacao.gestao.quarto.repository.BloqueioRepository;
import aplicacao.gestao.quarto.repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuartoService {


    private final QuartoRepository repository;
    private final BloqueioRepository bloqueioRepository;
    private final Outbox outbox;
    private final Idempotencia idempotencia;


    public Quarto criar(Quarto camposDadosParaCriar) {


        camposDadosParaCriar.setTipo(camposDadosParaCriar.getTipo());
        camposDadosParaCriar.setDiaria(camposDadosParaCriar.getDiaria());
        camposDadosParaCriar.setNumero(camposDadosParaCriar.getNumero());

        return  repository.save(camposDadosParaCriar);


    }

    @Transactional
    public void bloquearReserva(ReservaCriada reservaCriada){
        if(!idempotencia.primeiraVez(reservaCriada.eventoId())){
            return;
        }
        var buscarQuarto = repository.buscarComLock(reservaCriada.quartoId());
        if(buscarQuarto.isEmpty()){
            outbox.registrarEvento("quarto.indisponivel", reservaCriada.reservaId().toString(), new QuartoIndisponivel(
                    UUID.randomUUID(), reservaCriada.reservaId(), "Quarto não encontrado"

            )
            );

            return;
        }
        var quarto = buscarQuarto.get();
        boolean ocupado =  bloqueioRepository.existeSobreposicao(reservaCriada.quartoId(), reservaCriada.checkIn(),reservaCriada.checkOut());
        if(!ocupado){
            long noites = ChronoUnit.DAYS.between(reservaCriada.checkIn(), reservaCriada.checkOut());
            BigDecimal total = quarto.getDiaria().multiply(BigDecimal.valueOf(noites));
            var bloqueio = new Bloqueio();
            bloqueio.setQuarto(quarto);
            bloqueio.setReservaId(reservaCriada.reservaId());
            bloqueio.setCheckIn(reservaCriada.checkIn());
            bloqueio.setCheckOut(reservaCriada.checkOut());
            bloqueioRepository.save(bloqueio);
            outbox.registrarEvento("quarto.bloqueado", reservaCriada.reservaId().toString(),
                    new QuartoBloqueado(UUID.randomUUID(), reservaCriada.reservaId(), total));
        }else{
            outbox.registrarEvento("quarto.indisponivel", reservaCriada.reservaId().toString(), new QuartoIndisponivel(
                    UUID.randomUUID(), reservaCriada.reservaId(), "Período ocupado"

            ));
        }
    }

}
