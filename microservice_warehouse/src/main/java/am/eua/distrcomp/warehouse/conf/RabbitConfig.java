package am.eua.distrcomp.warehouse.conf;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.port}")
    private String port;

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.password}")
    private String password;

    @Bean
    public CachingConnectionFactory connectionFactory() throws Exception {
        // Create a trust-all SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] { new InsecureTrustManager() }, new SecureRandom());

        // Create an SSLConnectionSocketFactory from this context
        SSLConnectionSocketFactory socketFactory =
                new SSLConnectionSocketFactory(sslContext, (hostname, session) -> true);

        // Let Spring Boot build the default connection factory, then customize SSL
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(Integer.parseInt(port));
        factory.setUsername(username);
        factory.setPassword(password);

        // Force SSL
        factory.getRabbitConnectionFactory().useSslProtocol(sslContext);
        // Optional: disable hostname verification
//        factory.getRabbitConnectionFactory().enableHostnameVerification();


        return factory;
    }

    // Trust manager that does not validate certs at all
    private static class InsecureTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) { }
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) { }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

//    // 1) Direct exchange for sending requests to the Warehouse
//    @Bean
//    public DirectExchange warehouseExchange() {
//        return new DirectExchange("warehouseExchange");
//    }


}
