package ru.tinkoff.edu.java.bot.configuration;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class RabbitMQConfiguration {


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
    public FanoutExchange deadDirectExchange() {
        return new FanoutExchange(applicationConfig.rabbitExchange() + ".dlx", false, false);
    }

    @Bean
    public Queue deadQueue() {
        return QueueBuilder.nonDurable(applicationConfig.rabbitQueue() + ".dlq").build();
    }

    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadDirectExchange());
    }


    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdate", LinkUpdate.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper());
        return jsonConverter;
    }
}
