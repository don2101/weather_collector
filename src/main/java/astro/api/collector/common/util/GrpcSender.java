package astro.api.collector.common.util;

import astro.grpc.clientsdk.ClientSdk;
import astro.grpc.clientsdk.ClientSdkImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

// TODO: ClientSdk 인터페이스화 시킵시다.
@Service
public class GrpcSender {
    private ClientSdk clientSdk;


    public GrpcSender() {
        clientSdk = ClientSdkImpl.builder()
                                 .host("localhost")
                                 .port(8082)
                                 .build();
        ((ClientSdkImpl) clientSdk).connect();
    }




    public void send(String topic, String message) {
        clientSdk.send(topic, message);
    }
}
