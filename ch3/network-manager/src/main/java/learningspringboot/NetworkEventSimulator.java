package learningspringboot;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Random;

public class NetworkEventSimulator {

    final private JmsTemplate jmsTemplate;
    final private String destination;
    final private CounterService counterService;

    public NetworkEventSimulator(JmsTemplate jmsTemplate, String destination, CounterService counterService) {
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
        this.counterService = counterService;
    }

    @Scheduled(fixedRate = 1000L)
    public void simulateActivity() {

        Random random = new Random();

        String hostname;
        switch (random.nextInt(3)) {
            case 0: hostname = "router101"; break;
            case 1: hostname = "multiplex205"; break;
            default: hostname = "switch1143"; break;
        }

        Severity severity;
        switch (random.nextInt(4)) {
            case 0: severity = Severity.UP; break;
            case 1: severity = Severity.DEGRADED; break;
            case 2: severity = Severity.JEOPARDY; break;
            default: severity = Severity.DOWN; break;
        }

        Alarm event = new Alarm(hostname, LocalDateTime.now(), severity);
        jmsTemplate.convertAndSend(destination, event);

        counterService.increment("messages.total.produced");
        counterService.increment("messages." + event.getHostname() + ".produced");
    }
}
