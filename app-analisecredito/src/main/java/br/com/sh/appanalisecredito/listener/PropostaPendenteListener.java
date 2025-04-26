package br.com.sh.appanalisecredito.listener;


import br.com.sh.appanalisecredito.model.PropostaModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {


    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendete(PropostaModel proposta) {


    }
}
