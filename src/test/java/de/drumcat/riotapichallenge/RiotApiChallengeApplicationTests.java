package de.drumcat.riotapichallenge;

import de.drumcat.riotapichallengefx.RiotApiChallengeFxApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
		RiotApiChallengeFxApplication.class })
public class RiotApiChallengeApplicationTests {

	@Test
	public void contextLoads() {
	}

}

