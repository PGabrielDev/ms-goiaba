package io.github.PGabrielDev.msavaliadordecredito.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCreditoDTO {
    List<CartaoAprovado> cartoes;
}
