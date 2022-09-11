package io.github.PGabrielDev.msavaliadordecredito.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SituacaoCliente {
    private DadosCliente cliente;
    private List<CartaoCliente> cartoes;
}
