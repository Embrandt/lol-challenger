package de.drumcat.riotapichallengefx.repository;

import de.drumcat.riotapichallengefx.domain.SummonerDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonerDtoRepository extends CrudRepository<SummonerDto, Long> {
}
