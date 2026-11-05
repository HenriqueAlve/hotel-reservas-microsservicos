package aplicacao.gestao.reserva.repository;

import aplicacao.gestao.reserva.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
}
