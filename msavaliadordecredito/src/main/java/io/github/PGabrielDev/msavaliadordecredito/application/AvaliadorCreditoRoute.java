package io.github.PGabrielDev.msavaliadordecredito.application;

import io.github.PGabrielDev.msavaliadordecredito.application.exceptions.DadosClienteNotFoundExeption;
import io.github.PGabrielDev.msavaliadordecredito.application.exceptions.ErrorComunicacaoMicroservices;
import io.github.PGabrielDev.msavaliadordecredito.domain.DadosAvaliacao;
import io.github.PGabrielDev.msavaliadordecredito.domain.DadosCliente;
import io.github.PGabrielDev.msavaliadordecredito.domain.RetornoAvaliacaoCreditoDTO;
import io.github.PGabrielDev.msavaliadordecredito.domain.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoRoute {

    private final AvaliadorCreditoService service;
    @GetMapping(  "/check")
    public ResponseEntity<Map<String, String>> status() {
        Map<String, String> map = new HashMap<>();
        map.put("status", "OK");
        return  ResponseEntity.ok(map);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<SituacaoCliente> getSituacaoCliente(@RequestParam String cpf){
        try{
            return ResponseEntity.ok(service.obterSituacaoCliente(cpf));
        } catch (DadosClienteNotFoundExeption ex){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (ErrorComunicacaoMicroservices ex) {
            return ResponseEntity.status(HttpStatus.resolve(ex.getStatus())).build();
        }
    }

    @PostMapping
    public ResponseEntity<RetornoAvaliacaoCreditoDTO> getSituacaoCliente(@RequestBody DadosAvaliacao request){
        try{
            return ResponseEntity.ok(service.realizarAvaliacao(request.getCpf(), request.getRenda()));
        } catch (DadosClienteNotFoundExeption ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (ErrorComunicacaoMicroservices ex) {
            return ResponseEntity.status(HttpStatus.resolve(ex.getStatus())).build();
        }
    }

}
