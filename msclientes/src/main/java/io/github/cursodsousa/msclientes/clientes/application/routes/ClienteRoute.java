package io.github.cursodsousa.msclientes.clientes.application.routes;

import io.github.cursodsousa.msclientes.clientes.application.models.SaveClienteModel;
import io.github.cursodsousa.msclientes.clientes.application.services.ClienteService;
import io.github.cursodsousa.msclientes.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("clientes")
@AllArgsConstructor
@Slf4j
public class ClienteRoute {


    private ClienteService service;


    @GetMapping
    public ResponseEntity<Map<String, String>> status() {
        log.info("Logando na aplicação");
        Map<String, String> map = new HashMap<>();
        map.put("status", "OK");
        return  ResponseEntity.ok(map);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody SaveClienteModel request) {
        Cliente cliente = request.toModel();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }
    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
        var cliente  = service.getbyCPF(cpf);
        if(cliente.isEmpty())
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente.get());
    }

}
