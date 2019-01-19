package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.domain.Challenge;
import org.springframework.stereotype.Service;

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

@Service
public class ChallengeService {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("test"); //name of persistence unit
    // here
    private EntityManager entityManager = factory.createEntityManager();

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

    public void removeChallenge(Challenge challenge) {
        entityManager.getTransaction().begin();
        entityManager.remove(challenge);
        entityManager.getTransaction().commit();
    }



}