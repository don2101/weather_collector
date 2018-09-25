package astro.api.collector.common;

import astro.grpc.clientsdk.ClientSdk;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcSender {
    private ClientSdk clientSdk;


    public GrpcSender() {
        clientSdk = new ClientSdk.ClientSdkBuilder().setHost("localhost")
                                                    .setPort(8082)
                                                    .build();
    }

    public void send(String topic, String message) {
        clientSdk.send(topic, message);
    }
}
