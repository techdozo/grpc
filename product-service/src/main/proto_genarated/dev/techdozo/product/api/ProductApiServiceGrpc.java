package dev.techdozo.product.api;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.31.0)",
    comments = "Source: product.proto")
public final class ProductApiServiceGrpc {

  private ProductApiServiceGrpc() {}

  public static final String SERVICE_NAME = "product.ProductApiService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dev.techdozo.product.api.Product.ProductApiRequest,
      dev.techdozo.product.api.Product.ProductApiResponse> getGetProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getProduct",
      requestType = dev.techdozo.product.api.Product.ProductApiRequest.class,
      responseType = dev.techdozo.product.api.Product.ProductApiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.techdozo.product.api.Product.ProductApiRequest,
      dev.techdozo.product.api.Product.ProductApiResponse> getGetProductMethod() {
    io.grpc.MethodDescriptor<dev.techdozo.product.api.Product.ProductApiRequest, dev.techdozo.product.api.Product.ProductApiResponse> getGetProductMethod;
    if ((getGetProductMethod = ProductApiServiceGrpc.getGetProductMethod) == null) {
      synchronized (ProductApiServiceGrpc.class) {
        if ((getGetProductMethod = ProductApiServiceGrpc.getGetProductMethod) == null) {
          ProductApiServiceGrpc.getGetProductMethod = getGetProductMethod =
              io.grpc.MethodDescriptor.<dev.techdozo.product.api.Product.ProductApiRequest, dev.techdozo.product.api.Product.ProductApiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.techdozo.product.api.Product.ProductApiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.techdozo.product.api.Product.ProductApiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProductApiServiceMethodDescriptorSupplier("getProduct"))
              .build();
        }
      }
    }
    return getGetProductMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProductApiServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductApiServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductApiServiceStub>() {
        @java.lang.Override
        public ProductApiServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductApiServiceStub(channel, callOptions);
        }
      };
    return ProductApiServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProductApiServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductApiServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductApiServiceBlockingStub>() {
        @java.lang.Override
        public ProductApiServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductApiServiceBlockingStub(channel, callOptions);
        }
      };
    return ProductApiServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProductApiServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductApiServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductApiServiceFutureStub>() {
        @java.lang.Override
        public ProductApiServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductApiServiceFutureStub(channel, callOptions);
        }
      };
    return ProductApiServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ProductApiServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getProduct(dev.techdozo.product.api.Product.ProductApiRequest request,
        io.grpc.stub.StreamObserver<dev.techdozo.product.api.Product.ProductApiResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetProductMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetProductMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dev.techdozo.product.api.Product.ProductApiRequest,
                dev.techdozo.product.api.Product.ProductApiResponse>(
                  this, METHODID_GET_PRODUCT)))
          .build();
    }
  }

  /**
   */
  public static final class ProductApiServiceStub extends io.grpc.stub.AbstractAsyncStub<ProductApiServiceStub> {
    private ProductApiServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductApiServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductApiServiceStub(channel, callOptions);
    }

    /**
     */
    public void getProduct(dev.techdozo.product.api.Product.ProductApiRequest request,
        io.grpc.stub.StreamObserver<dev.techdozo.product.api.Product.ProductApiResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetProductMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ProductApiServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ProductApiServiceBlockingStub> {
    private ProductApiServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductApiServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductApiServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dev.techdozo.product.api.Product.ProductApiResponse getProduct(dev.techdozo.product.api.Product.ProductApiRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetProductMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ProductApiServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ProductApiServiceFutureStub> {
    private ProductApiServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductApiServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductApiServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.techdozo.product.api.Product.ProductApiResponse> getProduct(
        dev.techdozo.product.api.Product.ProductApiRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetProductMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PRODUCT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProductApiServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProductApiServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PRODUCT:
          serviceImpl.getProduct((dev.techdozo.product.api.Product.ProductApiRequest) request,
              (io.grpc.stub.StreamObserver<dev.techdozo.product.api.Product.ProductApiResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ProductApiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProductApiServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.techdozo.product.api.Product.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProductApiService");
    }
  }

  private static final class ProductApiServiceFileDescriptorSupplier
      extends ProductApiServiceBaseDescriptorSupplier {
    ProductApiServiceFileDescriptorSupplier() {}
  }

  private static final class ProductApiServiceMethodDescriptorSupplier
      extends ProductApiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProductApiServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProductApiServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProductApiServiceFileDescriptorSupplier())
              .addMethod(getGetProductMethod())
              .build();
        }
      }
    }
    return result;
  }
}
