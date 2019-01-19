package de.drumcat.riotapichallengefx.utils;

import de.drumcat.riotapichallengefx.RiotApiChallengeFxApplication;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Properties;

import static java.lang.System.out;

public class ClientPortParserService {

    public boolean parseClientLockfile(String path) throws IOException {

        String csvFile = path;
        String line = "";
        String cvsSplitBy = ":";
        String port = "";
        String key = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] client = line.split(cvsSplitBy);
                port = client[2];
                key = Base64.getEncoder().encodeToString(("riot:" + client[3]).getBytes());
            }
            System.setProperty("key", key);
            System.setProperty("port", port);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
