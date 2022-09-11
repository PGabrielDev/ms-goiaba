package io.github.PGabrielDev.mscartoes.utils;

import io.github.PGabrielDev.mscartoes.application.model.CartaoPorClienteResponse;
import io.github.PGabrielDev.mscartoes.domain.ClienteCartao;

public class Utils {

    public static CartaoPorClienteResponse CartaoPorClienteResponse(ClienteCartao clienteCartao){
        return new CartaoPorClienteResponse(
                clienteCartao.getNome(),
                clienteCartao.getCartao().getBandeira().toString(),
                clienteCartao.getLimite()
        );
    }

}
