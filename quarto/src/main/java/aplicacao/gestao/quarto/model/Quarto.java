package aplicacao.gestao.quarto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "quarto")
@Getter
@Setter
@NoArgsConstructor
public class Quarto {

    @Id @GeneratedValue
    private Long id;
    private String numero;
    private String tipo;
    private BigDecimal diaria;

}
