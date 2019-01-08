package de.drumcat.riotapichallengefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        primaryStage.setTitle("Riot API Challenge 2018");
        primaryStage.setScene(new Scene(root, 100, 57));
        primaryStage.show();
    }

}
