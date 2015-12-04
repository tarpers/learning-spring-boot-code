package learningspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableScheduling
public class NetworkManagerApplication {

    private static final String MAILBOX = "events";

    @Autowired
    private JmsProperties properties;

    @Bean
    MessageListenerAdapter adapter(NetworkEventConsumer consumer) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(consumer);
        adapter.setDefaultListenerMethod("process");
        return adapter;
    }

    @Bean
    SimpleMessageListenerContainer container(MessageListenerAdapter adapter, ConnectionFactory factory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setMessageListener(adapter);
        container.setConnectionFactory(factory);
        container.setPubSubDomain(this.properties.isPubSubDomain());
        container.setDestinationName(MAILBOX);
        return container;
    }

    @Bean
    NetworkEventSimulator simulator(JmsTemplate jmsTemplate, CounterService counterService) {
        return new NetworkEventSimulator(jmsTemplate, MAILBOX, counterService);
    }

    public static void main(String[] args) {
        SpringApplication.run(NetworkManagerApplication.class, args);
    }
}
