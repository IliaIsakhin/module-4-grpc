package com.epam.edu.grpc.client.service;

import com.epam.edu.grpc.proto.MessageServiceGrpc;
import com.epam.edu.grpc.proto.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    private final static Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private final MessageServiceGrpc.MessageServiceBlockingStub messageServiceStub;

    public MessageSender(MessageServiceGrpc.MessageServiceBlockingStub messageServiceStub) {
        this.messageServiceStub = messageServiceStub;
    }

    @Scheduled(cron = "*/3 * * * * *")
    public void sendMessage() {
        var randomRequest = Types.Request.newBuilder()
                .setMessage("Hello world!")
                .setTime(System.currentTimeMillis())
                .build();
        
        logger.info("Request {} sent", randomRequest);
        
        var response = messageServiceStub.send(randomRequest);
        
        logger.info("Received response {}", response);
    }

}
