package io.github.PGabrielDev.mscartoes.application.services;

import io.github.PGabrielDev.mscartoes.domain.ClienteCartao;
import io.github.PGabrielDev.mscartoes.infra.CartaoRepository;
import io.github.PGabrielDev.mscartoes.infra.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf) {
        return  repository.findByCpf(cpf);
    }

}
