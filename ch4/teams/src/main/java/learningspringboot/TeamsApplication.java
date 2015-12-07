package learningspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TeamsApplication {

    private static final Logger log = LoggerFactory.getLogger(TeamsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TeamsApplication.class, args);
    }

    @Autowired
    TeammateRepository teammateRepository;

    @PostConstruct
    void seeTheRoster() {
        for (Teammate teammate : teammateRepository.findAll()) {
            log.info(teammate.toString());
        }
    }
}
