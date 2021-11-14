package com.epam.edu.grpc.client.config;


import com.epam.edu.grpc.proto.MessageServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProtoServicesConfig {

    @Bean
    public Channel serverChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext()
                .build();
    }
    
    @Bean
    public MessageServiceGrpc.MessageServiceBlockingStub messageServiceStub(Channel channel) {
        return MessageServiceGrpc.newBlockingStub(channel);
    }
}
