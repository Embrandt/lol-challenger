package de.drumcat.riotapichallengefx.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ClientPortParserServiceTest {

    @Test
    public void testClientPortParser() throws IOException {
        ClientPortParserService clientPortParserService = new ClientPortParserService();
        boolean lockfileParsed = clientPortParserService.parseClientLockfile("C:\\Riot Games\\League of Legends\\lockfile");

        assertTrue(lockfileParsed);
    }

}
