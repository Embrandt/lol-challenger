package de.drumcat.riotapichallengefx.repository;

import de.drumcat.riotapichallengefx.domain.Challenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {
}
