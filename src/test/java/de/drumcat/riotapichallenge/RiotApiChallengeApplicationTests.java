package de.drumcat.riotapichallenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
		RiotApiChallengeApplication.class })
public class RiotApiChallengeApplicationTests {

	@Test
	public void contextLoads() {
	}

}

