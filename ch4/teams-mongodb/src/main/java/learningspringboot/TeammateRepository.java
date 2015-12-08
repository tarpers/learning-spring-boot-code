package learningspringboot;

import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface TeammateRepository extends CrudRepository<Teammate, BigInteger> {
}
