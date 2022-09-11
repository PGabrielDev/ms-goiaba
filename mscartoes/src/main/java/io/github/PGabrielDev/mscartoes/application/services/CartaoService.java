package io.github.PGabrielDev.mscartoes.application.services;

import io.github.PGabrielDev.mscartoes.domain.Cartao;
import io.github.PGabrielDev.mscartoes.infra.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda){
        var rendaB = BigDecimal.valueOf(renda);
        List<Cartao> cartoes = repository.findByRendaLessThanEqual(rendaB);
        return  cartoes;
    }

}
