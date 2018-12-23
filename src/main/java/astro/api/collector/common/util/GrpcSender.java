package astro.api.collector.common.util;

import astro.grpc.clientsdk.ClientSdk;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

// TODO: ClientSdk 인터페이스화 시킵시다.
@Service
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
