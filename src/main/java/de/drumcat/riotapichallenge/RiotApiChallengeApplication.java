package de.drumcat.riotapichallenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;


@SpringBootApplication
public class RiotApiChallengeApplication {

    private final static Logger logger = LoggerFactory
            .getLogger(RiotApiChallengeApplication.class);

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

	public static void main(String[] args) {
        SpringApplication.run(RiotApiChallengeApplication.class, args);

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
    }

	@Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}

