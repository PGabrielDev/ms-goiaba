package io.github.PGabrielDev.mscartoes.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartaoPorClienteResponse {


    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

}
