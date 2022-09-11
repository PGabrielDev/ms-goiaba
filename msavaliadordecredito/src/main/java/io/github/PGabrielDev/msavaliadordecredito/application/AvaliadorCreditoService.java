package io.github.PGabrielDev.msavaliadordecredito.application;

import feign.FeignException;
import io.github.PGabrielDev.msavaliadordecredito.application.exceptions.DadosClienteNotFoundExeption;
import io.github.PGabrielDev.msavaliadordecredito.application.exceptions.ErrorComunicacaoMicroservices;
import io.github.PGabrielDev.msavaliadordecredito.domain.*;
import io.github.PGabrielDev.msavaliadordecredito.infra.CartaoRequest;
import io.github.PGabrielDev.msavaliadordecredito.infra.ClienteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final ClienteRequest clienteRequests;
    private final CartaoRequest cartaoRequest;
    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundExeption, ErrorComunicacaoMicroservices {
        try {
            DadosCliente dadosCliente = clienteRequests.dadosCliente(cpf);
            List<CartaoCliente> cartaoCliente = cartaoRequest.cartoesPorClientes(cpf);
            SituacaoCliente situacaoCliente = SituacaoCliente.builder()
                    .cliente(dadosCliente)
                    .cartoes(cartaoCliente)
                    .build();
            return situacaoCliente;
        } catch (FeignException.FeignClientException ex) {
            int status = ex.status();
            if (status == HttpStatus.NOT_FOUND.value())
                throw new DadosClienteNotFoundExeption(cpf);
            throw new ErrorComunicacaoMicroservices(ex.getMessage(), ex.status());
        }
    }

    public RetornoAvaliacaoCreditoDTO realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundExeption, ErrorComunicacaoMicroservices {
        try {
            DadosCliente dadosCliente = clienteRequests.dadosCliente(cpf);
            List<Cartao> cartoesLiberatos = cartaoRequest.cartaoPorRenda(renda);
            var cartoesAprovados= cartoesLiberatos.stream().map(c -> {
                var fator = Double.valueOf(dadosCliente.getIdade()) / 15;
                BigDecimal limiteBasico = c.getLimiteBasico();
                BigDecimal limiteLiberado =  limiteBasico.multiply(BigDecimal.valueOf(fator));
                return new CartaoAprovado(c.getNome(), c.getBandeira(),limiteLiberado);
            }).collect(Collectors.toList());
            return new RetornoAvaliacaoCreditoDTO(cartoesAprovados);


        } catch (FeignException.FeignClientException ex) {
            int status = ex.status();
            if (status == HttpStatus.NOT_FOUND.value())
                throw new DadosClienteNotFoundExeption(cpf);
            throw new ErrorComunicacaoMicroservices(ex.getMessage(), ex.status());
        }
    }
}
