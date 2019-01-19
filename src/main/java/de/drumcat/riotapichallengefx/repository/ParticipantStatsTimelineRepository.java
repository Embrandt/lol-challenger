package de.drumcat.riotapichallengefx.repository;

import de.drumcat.riotapichallengefx.domain.ParticipantStatsTimeline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantStatsTimelineRepository extends CrudRepository<ParticipantStatsTimeline, Long> {
}
