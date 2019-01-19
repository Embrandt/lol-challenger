package de.drumcat.riotapichallengefx.repository;

import de.drumcat.riotapichallengefx.domain.Challenge;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ChallengeRepositoryTest {

    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    public void testChallengeRepo(){
        Challenge challenge = new Challenge("", "", "", "");
        challenge.setTimeStarted(new Date().getTime());
        challengeRepository.save(challenge);
    }

}