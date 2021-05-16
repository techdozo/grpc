package dev.techdozo.product;

import dev.techdozo.product.api.ProductApiService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ProductServer {

    private int port;
    private final Server server;

    public ProductServer(int port) {
        this.port = port;
        ProductApiService productApiService = new ProductApiService();
        this.server = ServerBuilder.forPort(port).addService(productApiService).build();
    }

    public void start() throws IOException {
        log.info("Starting Server..");

        server.start();

        log.info("Server Started on port {} ", port);

        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread(
                                () -> {
                                    try {
                                        this.stop();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }));
    }

    private void stop() throws InterruptedException {
        log.info("Stopping Server..");

        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutDown() throws InterruptedException {
        if (this.server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ProductServer productServer = new ProductServer(8080);
        productServer.start();
        productServer.blockUntilShutDown();
    }
}
