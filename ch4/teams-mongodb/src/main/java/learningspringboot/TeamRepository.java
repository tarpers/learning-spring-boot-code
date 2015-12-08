package learningspringboot;

import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface TeamRepository extends CrudRepository<Team, BigInteger> {
}