package br.com.sh.appanalisecredito.listener;


import br.com.sh.appanalisecredito.model.PropostaModel;
import br.com.sh.appanalisecredito.service.AnaliseCreditoService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class PropostaPendenteListener {

    private final AnaliseCreditoService analiseCreditoService;

    public PropostaPendenteListener(AnaliseCreditoService analiseCreditoService) {
        this.analiseCreditoService = analiseCreditoService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendete(PropostaModel proposta) {
        analiseCreditoService.analiseCredito(proposta);

    }
}
