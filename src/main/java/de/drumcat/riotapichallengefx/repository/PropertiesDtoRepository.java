package de.drumcat.riotapichallengefx.repository;

import de.drumcat.riotapichallengefx.domain.PropertiesDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesDtoRepository extends CrudRepository<PropertiesDto, String> {
}
