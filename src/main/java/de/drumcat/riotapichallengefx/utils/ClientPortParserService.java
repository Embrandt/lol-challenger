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

    InputStream propertyAsStream = getClass().getClassLoader().getResourceAsStream("application.properties");
    String pathProp = getClass().getClassLoader().getResource("application.properties").getPath();
    Properties props = new Properties();
    props.load(propertyAsStream);
    propertyAsStream.close();

    FileOutputStream out = new FileOutputStream(pathProp);
    props.setProperty("rift.explorer.key", key);
    props.setProperty("rift.explorer.port", port);
    props.store(out, null);
    out.close();
}catch(Exception ex){
   return false;
}

        return true;
    }
}
