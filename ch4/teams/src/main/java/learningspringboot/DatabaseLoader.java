package learningspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("!production")
public class DatabaseLoader {

    private final TeammateRepository teammateRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public DatabaseLoader(TeammateRepository teammateRepository, TeamRepository teamRepository) {
        this.teammateRepository = teammateRepository;
        this.teamRepository = teamRepository;
    }

    @PostConstruct
    private void initDatabase() {
        Team springBootTeam = new Team("Spring Boot Badgers");
        teamRepository.save(springBootTeam);

        Teammate greg = new Teammate("Greg", "Turniquist");
        greg.setPosition("2nd base");
        greg.setTeam(springBootTeam);
        teammateRepository.save(greg);

        Teammate roy = new Teammate("Roy", "Clarkson");
        roy.setPosition("1st base");
        roy.setTeam(springBootTeam);
        teammateRepository.save(roy);

        Teammate phil = new Teammate("Phil", "Webb");
        phil.setPosition("pitcher");
        phil.setTeam(springBootTeam);
        teammateRepository.save(phil);
    }
}