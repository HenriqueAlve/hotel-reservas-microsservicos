package aplicacao.gestao.quarto.repository;

import aplicacao.gestao.quarto.model.Bloqueio;
import aplicacao.gestao.quarto.model.Quarto;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {

    @Query("select count(b) > 0 from Bloqueio b " +
            "where b.quarto.id = :quartoId " +
            "and b.checkIn < :checkOut and b.checkOut > :checkIn")
    boolean existeSobreposicao(
            @Param("quartoId")  Long quartoId,
            @Param("checkIn")  LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut

    );


}
