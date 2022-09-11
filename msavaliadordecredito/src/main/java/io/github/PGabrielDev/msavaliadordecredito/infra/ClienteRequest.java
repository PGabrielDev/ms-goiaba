package io.github.PGabrielDev.msavaliadordecredito.infra;


import io.github.PGabrielDev.msavaliadordecredito.domain.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteRequest {

    @GetMapping(params = "cpf")
    DadosCliente dadosCliente(@RequestParam("cpf") String cpf);

}
