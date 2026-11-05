package aplicacao.gestao.reserva.model;



import aplicacao.gestao.reserva.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
public class Reserva {

    @Id @GeneratedValue
    private UUID id;

    private  String hospedeId;
    private Long quartoId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDENTE;
    private Instant createdAt = Instant.now();

}
