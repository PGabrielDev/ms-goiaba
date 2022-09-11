package io.github.PGabrielDev.msavaliadordecredito.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartaoAprovado {
    private String nome;
    private String bandeira;
    private BigDecimal limiteAprovador;
}
