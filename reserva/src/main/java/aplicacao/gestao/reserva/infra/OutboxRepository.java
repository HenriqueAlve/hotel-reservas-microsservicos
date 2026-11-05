package aplicacao.gestao.reserva.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OutboxRepository extends JpaRepository<OutboxEvent, UUID> {

    @Query(value = "select * from outbox where published = false "
            + "order by created_at limit 50 for update skip locked", nativeQuery = true)
    List<OutboxEvent> proximos();
}
