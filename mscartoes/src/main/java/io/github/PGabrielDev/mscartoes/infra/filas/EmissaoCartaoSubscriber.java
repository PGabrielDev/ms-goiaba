package io.github.PGabrielDev.mscartoes.infra.filas;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscriber {

    @RabbitListener(queues = "${mq.queue.emissao-cartao}")
    public void receberSolicitacaoEmissao(String payload){
        System.out.println(payload);
    }
}
