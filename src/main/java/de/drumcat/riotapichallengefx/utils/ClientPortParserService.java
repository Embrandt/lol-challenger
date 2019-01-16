package de.drumcat.riotapichallengefx.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Base64;
import java.util.Properties;

public class ClientPortParserService {

    public boolean parseClientLockfile(String path) throws IOException {

        String csvFile = path;
        String line = "";
        String cvsSplitBy = ":";
        String port = "";
        String key = "";

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] client = line.split(cvsSplitBy);

                port = client[2];
                key = Base64.getEncoder().encodeToString(("riot:"+client[3]).getBytes());
            }
        File oldProperties = new File(getClass().getClassLoader().getResource("application.properties").getFile());
        FileUtils.writeStringToFile(oldProperties, "\n rift.explorer.key=" + key +"\n rift.explorer.port=" + port , true);
//
//        clientProperties.append("rift.explorer.key=" + key);
//        clientProperties.append("rift.explorer.port=" + port);
//        clientProperties.close();

        return true;
    }
}
