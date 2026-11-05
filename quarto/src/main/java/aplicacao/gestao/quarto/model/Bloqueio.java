package aplicacao.gestao.quarto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bloqueio")
@Getter
@Setter
@NoArgsConstructor
public class Bloqueio {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Quarto quarto;


    private UUID reservaId;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
