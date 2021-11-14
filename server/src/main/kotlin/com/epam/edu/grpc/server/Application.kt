package com.epam.edu.grpc.server

import com.epam.edu.grpc.proto.MessageServiceGrpc
import com.epam.edu.grpc.proto.Types
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

fun main() {
    ServerBuilder.forPort(8081)
        .addService(MessageServiceImpl())
        .build()
        .apply { 
            start()
            awaitTermination()
        }
}

class MessageServiceImpl : MessageServiceGrpc.MessageServiceImplBase() {
    
    override fun send(request: Types.Request, responseObserver: StreamObserver<Types.Response>) {
        println("Hello, message ${request.message}!")

        val response = Types.Response.newBuilder()
            .setAnswer(String.format("Answer for: %s", request.message))
            .setTime(System.currentTimeMillis())
            .build()
        
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
    
}
