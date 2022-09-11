package io.github.PGabrielDev.mscartoes.application.model;

import io.github.PGabrielDev.mscartoes.domain.BandeiraCartao;
import io.github.PGabrielDev.mscartoes.domain.Cartao;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class SaveCartaoModel {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao toEntity(){
        return new Cartao(nome,bandeira,renda,limiteBasico);
    }

}
