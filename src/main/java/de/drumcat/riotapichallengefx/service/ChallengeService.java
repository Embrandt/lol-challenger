package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.domain.Challenge;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.drumcat.riotapichallengefx.utils.PropertiesLoader.loadProperties;

@Service
public class ChallengeService {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test"); //name of persistence unit here
    EntityManager entityManager = factory.createEntityManager();

    public Map<String, Challenge> getChallengesFromDB() {
        //Find all entries in challenge database
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Challenge> cq = cb.createQuery(Challenge.class);
        Root<Challenge> rootEntry = cq.from(Challenge.class);
        CriteriaQuery<Challenge> all = cq.select(rootEntry);
        TypedQuery<Challenge> allQuery = entityManager.createQuery(all);
        List<Challenge> resultList = allQuery.getResultList();

        Map<String, Challenge> challengeMap = new HashMap<>();
        if (resultList != null && !resultList.isEmpty()) {
            for (Challenge challenge : resultList) {
                challengeMap.put(challenge.getOpponentName(), challenge);
            }
        }
        return challengeMap;
    }

    public void persistChallenge(Challenge challenge){
        entityManager.getTransaction().begin();
        entityManager.persist(challenge);
        entityManager.getTransaction().commit();
    }


}