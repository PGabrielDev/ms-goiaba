package io.github.PGabrielDev.msavaliadordecredito.infra;

import io.github.PGabrielDev.msavaliadordecredito.domain.Cartao;
import io.github.PGabrielDev.msavaliadordecredito.domain.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartaoRequest {

    @GetMapping(params = "cpf")
    List<CartaoCliente> cartoesPorClientes(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    List<Cartao> cartaoPorRenda(@RequestParam("renda") Long renda);
}
