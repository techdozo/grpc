package dev.techdozo.order.client;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import dev.techdozo.order.client.interceptor.GrpcClientRequestInterceptor;
import dev.techdozo.order.context.UserContext;
import dev.techdozo.product.api.ProductServiceGrpc;
import dev.techdozo.product.api.Service.GetProductRequest;
import dev.techdozo.product.api.Service.GetProductResponse;
import io.grpc.ManagedChannelBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UnaryGrpcFutureClient {

  private final String host;
  private final int port;
  private final ExecutorService fixedThreadPool;

  public UnaryGrpcFutureClient(String host, int port) {
    this.host = host;
    this.port = port;
    this.fixedThreadPool = Executors.newFixedThreadPool(2);
  }

  @SneakyThrows
  public void callServer() {

    log.info("Calling Server..");
    var managedChannel =
        ManagedChannelBuilder.forAddress(host, port)
            .intercept(new GrpcClientRequestInterceptor())
            .usePlaintext()
            .build();
    // Create a new future stub
    var productServiceFutureStub = ProductServiceGrpc.newFutureStub(managedChannel);

    var productRequest = GetProductRequest.newBuilder().setProductId("apple-123").build();

    ListenableFuture<GetProductResponse> listenableFuture =
        productServiceFutureStub.getProduct(productRequest);

    listenableFuture.addListener(this::notifyListener, fixedThreadPool);

    Futures.addCallback(listenableFuture, new ProductCallback(), fixedThreadPool);

    Thread.sleep(2000);

    fixedThreadPool.shutdown();
    fixedThreadPool.awaitTermination(2, TimeUnit.SECONDS);
  }

  private void notifyListener() {
    log.info("Notifying downstream operation");
  }

  private static class ProductCallback implements FutureCallback<GetProductResponse> {

    @Override
    public void onSuccess(@NullableDecl GetProductResponse result) {
      log.info("Received product f1 {}", result);
    }

    @Override
    public void onFailure(Throwable error) {
      log.error("Error occurred, reason {}", error.getMessage());
    }
  }

  public static void main(String[] args) {
    // Pseudo code to generate JWT token
    var token = "Bearer " + new Random().nextInt();
    UserContext.setUserContext(token);
    var client = new UnaryGrpcFutureClient("0.0.0.0", 3000);
    client.callServer();
  }
}
