package br.com.sh.appproposta.config.stream;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.plaf.PanelUI;

@Configuration
public class RabbitMQConfiguration {

    public static final String PROPOSTA_PENDENTE_ANALISE_CREDITO = "proposta-pendente.ms-analise-credito";
    public static final String PROPOSTA_PENDENTE_NOTIFICACAO = "proposta-pendente.ms-notificacao";

    public static final String PROPOSTA_CONCLUIDA_MS_PROPOSTA = "proposta-concluida.ms-proposta";
    public static final String PROPOSTA_CONCLUIDA_MS_NOTIFICACAO = "proposta-concluida.ms-notificacao";

    @Value("${rabbitmq.propostapendent.exchange}")
    private String PROPOSTA_PENDENTE_EX;

    @Value("${rabbitmq.propostaconcluida.exchange}")
    private String PROPOSTA_CONCLUIDA_EX;

    public static final String ROUTING_KEY = "proposta";

    private ConnectionFactory connectionFactory;

    public RabbitMQConfiguration(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

//    configura o rabbit admin e listener
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationListener(RabbitAdmin rabbitAdmin) {
        return event -> {
            // Initialize RabbitAdmin after the application is ready
            rabbitAdmin.initialize();
        };
    }

//    congfigura o FanoutExchange
    @Bean
    public FanoutExchange createFanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(PROPOSTA_PENDENTE_EX).build();
    }

    @Bean
    public FanoutExchange createFanoutExchangePropostaConcluida() {
        return ExchangeBuilder.fanoutExchange(PROPOSTA_CONCLUIDA_EX).build();
    }

//    Configura os Bindings
    @Bean
    public Binding createBindingPropostaPendenteMSAnaliseCredito() {
        return BindingBuilder.bind(createQueueOrderPendingMSanalyse())
                .to(createFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding createBindingPropostaPendenteMSnotificacao() {
        return BindingBuilder.bind(createQueueOrderPendingMSnotification())
                .to(createFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding createBindingPropostaConcluidaMSProposta() {
        return BindingBuilder.bind(createQueueOrderCompletedMSproposta())
                .to(createFanoutExchangePropostaConcluida());
    }

    @Bean
    public Binding createBindingPropostaConcluidaMSNotificacao() {
        return BindingBuilder.bind(createQueueOrderCompletedMSnotification())
                .to(createFanoutExchangePropostaConcluida());
    }

//    Cria as flilas
    @Bean
    public Queue createQueueOrderPendingMSanalyse() {
        return QueueBuilder.durable(PROPOSTA_PENDENTE_ANALISE_CREDITO).build();
    }

    @Bean
    public Queue createQueueOrderPendingMSnotification() {
        return QueueBuilder.durable(PROPOSTA_PENDENTE_NOTIFICACAO).build();
    }

    @Bean
    public Queue createQueueOrderCompletedMSproposta() {
        return QueueBuilder.durable(PROPOSTA_CONCLUIDA_MS_PROPOSTA).build();
    }

    @Bean
    public Queue createQueueOrderCompletedMSnotification() {
        return QueueBuilder.durable(PROPOSTA_CONCLUIDA_MS_NOTIFICACAO).build();
    }

//    configura o message converter
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

//    configura o RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

}
