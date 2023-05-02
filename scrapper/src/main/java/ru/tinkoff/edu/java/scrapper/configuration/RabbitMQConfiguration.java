package ru.tinkoff.edu.java.scrapper.configuration;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfiguration {

    @Autowired
    private final ApplicationConfig applicationConfig;

    @Bean
    public DirectExchange direct() {
        return new DirectExchange(applicationConfig.rabbitExchange(), false, false);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.nonDurable(applicationConfig.rabbitQueue())
                .withArgument("x-dead-letter-exchange", applicationConfig.rabbitExchange() + ".dlx").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(direct())
                .with(applicationConfig.rabbitQueue());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
