package ds.trading.system.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: TradingService.proto")
public final class StockOrderServiceGrpc {

  private StockOrderServiceGrpc() {}

  public static final String SERVICE_NAME = "ds.trading.system.grpc.generated.StockOrderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.trading.system.grpc.generated.StockOrderRequest,
      ds.trading.system.grpc.generated.StockOrderResponse> getStockOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "stockOrder",
      requestType = ds.trading.system.grpc.generated.StockOrderRequest.class,
      responseType = ds.trading.system.grpc.generated.StockOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.trading.system.grpc.generated.StockOrderRequest,
      ds.trading.system.grpc.generated.StockOrderResponse> getStockOrderMethod() {
    io.grpc.MethodDescriptor<ds.trading.system.grpc.generated.StockOrderRequest, ds.trading.system.grpc.generated.StockOrderResponse> getStockOrderMethod;
    if ((getStockOrderMethod = StockOrderServiceGrpc.getStockOrderMethod) == null) {
      synchronized (StockOrderServiceGrpc.class) {
        if ((getStockOrderMethod = StockOrderServiceGrpc.getStockOrderMethod) == null) {
          StockOrderServiceGrpc.getStockOrderMethod = getStockOrderMethod =
              io.grpc.MethodDescriptor.<ds.trading.system.grpc.generated.StockOrderRequest, ds.trading.system.grpc.generated.StockOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "stockOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.trading.system.grpc.generated.StockOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.trading.system.grpc.generated.StockOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StockOrderServiceMethodDescriptorSupplier("stockOrder"))
              .build();
        }
      }
    }
    return getStockOrderMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StockOrderServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StockOrderServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StockOrderServiceStub>() {
        @java.lang.Override
        public StockOrderServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StockOrderServiceStub(channel, callOptions);
        }
      };
    return StockOrderServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StockOrderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StockOrderServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StockOrderServiceBlockingStub>() {
        @java.lang.Override
        public StockOrderServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StockOrderServiceBlockingStub(channel, callOptions);
        }
      };
    return StockOrderServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StockOrderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StockOrderServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StockOrderServiceFutureStub>() {
        @java.lang.Override
        public StockOrderServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StockOrderServiceFutureStub(channel, callOptions);
        }
      };
    return StockOrderServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class StockOrderServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void stockOrder(ds.trading.system.grpc.generated.StockOrderRequest request,
        io.grpc.stub.StreamObserver<ds.trading.system.grpc.generated.StockOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStockOrderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStockOrderMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.trading.system.grpc.generated.StockOrderRequest,
                ds.trading.system.grpc.generated.StockOrderResponse>(
                  this, METHODID_STOCK_ORDER)))
          .build();
    }
  }

  /**
   */
  public static final class StockOrderServiceStub extends io.grpc.stub.AbstractAsyncStub<StockOrderServiceStub> {
    private StockOrderServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StockOrderServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StockOrderServiceStub(channel, callOptions);
    }

    /**
     */
    public void stockOrder(ds.trading.system.grpc.generated.StockOrderRequest request,
        io.grpc.stub.StreamObserver<ds.trading.system.grpc.generated.StockOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStockOrderMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class StockOrderServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<StockOrderServiceBlockingStub> {
    private StockOrderServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StockOrderServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StockOrderServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.trading.system.grpc.generated.StockOrderResponse stockOrder(ds.trading.system.grpc.generated.StockOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStockOrderMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StockOrderServiceFutureStub extends io.grpc.stub.AbstractFutureStub<StockOrderServiceFutureStub> {
    private StockOrderServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StockOrderServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StockOrderServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.trading.system.grpc.generated.StockOrderResponse> stockOrder(
        ds.trading.system.grpc.generated.StockOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStockOrderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STOCK_ORDER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StockOrderServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StockOrderServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STOCK_ORDER:
          serviceImpl.stockOrder((ds.trading.system.grpc.generated.StockOrderRequest) request,
              (io.grpc.stub.StreamObserver<ds.trading.system.grpc.generated.StockOrderResponse>) responseObserver);
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

  private static abstract class StockOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StockOrderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.trading.system.grpc.generated.TradingService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StockOrderService");
    }
  }

  private static final class StockOrderServiceFileDescriptorSupplier
      extends StockOrderServiceBaseDescriptorSupplier {
    StockOrderServiceFileDescriptorSupplier() {}
  }

  private static final class StockOrderServiceMethodDescriptorSupplier
      extends StockOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StockOrderServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (StockOrderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StockOrderServiceFileDescriptorSupplier())
              .addMethod(getStockOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
