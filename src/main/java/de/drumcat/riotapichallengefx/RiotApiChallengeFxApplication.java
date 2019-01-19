package de.drumcat.riotapichallengefx;

import de.drumcat.riotapichallengefx.domain.PropertiesDto;
import de.drumcat.riotapichallengefx.utils.ClientPortParserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.*;
import java.io.File;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

public class RiotApiChallengeFxApplication extends Application {


    private static final Logger logger = LogManager.getLogger(RiotApiChallengeFxApplication.class);

    static {
        // Abschalten der Verifikation von SLL-Zertifikaten.
        // Notwendig für die Testumgebungen / bei selbstsignierten Zertifikaten.
        javax.net.ssl.HttpsURLConnection
                .setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

                    @Override
                    public boolean verify(String hostname,
                                          javax.net.ssl.SSLSession sslSession) {
                        return true;
                    }
                });
    }

    public static void main(String[] args){

        // Abschalten der Verifikation von SSL-Zertifikaten.
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                // Ausschalten der Zertifikatpruefung
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                // Ausschalten der Zertifikatpruefung
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
            logger.error("SSL Exception", e);
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test"); //name of persistence unit here
        EntityManager entityManager = factory.createEntityManager();
        ClientPortParserService clientPortParserService = new ClientPortParserService();

        File file = null;
        if(entityManager.find(PropertiesDto.class, "lockfilepath") == null) {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Please select lockfile from /Riot Games/League of Legends");
            file = fileChooser.showOpenDialog(primaryStage);
            boolean findLockfilePath = clientPortParserService.parseClientLockfile(file.getAbsolutePath());
            logger.info("lockfile parsed " + findLockfilePath);
            if(findLockfilePath){
                PropertiesDto propertiesDto =  new PropertiesDto();
                propertiesDto.setKey("lockfilepath");
                propertiesDto.setValue(file.getAbsolutePath());
                entityManager.getTransaction().begin();
                entityManager.persist(propertiesDto);
                entityManager.getTransaction().commit();
                logger.info("Transaction committed");
            }else{
                start(primaryStage);
            }
        }else{
            PropertiesDto propertiesDto = entityManager.find(PropertiesDto.class, "lockfilepath");
            String lockFilePath = propertiesDto.getValue();
            boolean lockfileParsed = clientPortParserService.parseClientLockfile(lockFilePath);
            if(!lockfileParsed){
                start(primaryStage);
            }
        }

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        primaryStage.setTitle("Riot API Challenge 2018");
        primaryStage.setScene(new Scene(root, 100, 57));
        primaryStage.show();
    }

}
