package io.github.PGabrielDev.mscartoes.application.routes;

import io.github.PGabrielDev.mscartoes.application.model.CartaoPorClienteResponse;
import io.github.PGabrielDev.mscartoes.application.model.SaveCartaoModel;
import io.github.PGabrielDev.mscartoes.application.services.CartaoService;
import io.github.PGabrielDev.mscartoes.application.services.ClienteCartaoService;
import io.github.PGabrielDev.mscartoes.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesRoutes {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public ResponseEntity<Map<String, String>> status() {
        Map<String, String> map = new HashMap<>();
        map.put("status", "OK");
        return  ResponseEntity.ok(map);
    }

    @GetMapping(params = "renda")
    public ResponseEntity dadosCartao(@RequestParam("renda") Long renda) {
        var cartoes  = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoes);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody SaveCartaoModel request){
        System.out.println(request.getNome());
        var cartao = cartaoService.save(request.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartao);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoPorClienteResponse>> dadosCartao(@RequestParam("cpf") String cpf) {
        var clientesCartoes  = clienteCartaoService.listCartoesByCpf(cpf);
        var resultlist= clientesCartoes
                .stream()
                .map(clienteCartao -> Utils.CartaoPorClienteResponse(clienteCartao))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultlist);
    }


}
