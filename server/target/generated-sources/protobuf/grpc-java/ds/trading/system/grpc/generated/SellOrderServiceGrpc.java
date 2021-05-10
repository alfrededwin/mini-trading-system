package ds.trading.system.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: TradingService.proto")
public final class SellOrderServiceGrpc {

  private SellOrderServiceGrpc() {}

  public static final String SERVICE_NAME = "ds.trading.system.grpc.generated.SellOrderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.trading.system.grpc.generated.SellOrderRequest,
      ds.trading.system.grpc.generated.SellOrderResponse> getSellOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sellOrder",
      requestType = ds.trading.system.grpc.generated.SellOrderRequest.class,
      responseType = ds.trading.system.grpc.generated.SellOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.trading.system.grpc.generated.SellOrderRequest,
      ds.trading.system.grpc.generated.SellOrderResponse> getSellOrderMethod() {
    io.grpc.MethodDescriptor<ds.trading.system.grpc.generated.SellOrderRequest, ds.trading.system.grpc.generated.SellOrderResponse> getSellOrderMethod;
    if ((getSellOrderMethod = SellOrderServiceGrpc.getSellOrderMethod) == null) {
      synchronized (SellOrderServiceGrpc.class) {
        if ((getSellOrderMethod = SellOrderServiceGrpc.getSellOrderMethod) == null) {
          SellOrderServiceGrpc.getSellOrderMethod = getSellOrderMethod =
              io.grpc.MethodDescriptor.<ds.trading.system.grpc.generated.SellOrderRequest, ds.trading.system.grpc.generated.SellOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sellOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.trading.system.grpc.generated.SellOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.trading.system.grpc.generated.SellOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SellOrderServiceMethodDescriptorSupplier("sellOrder"))
              .build();
        }
      }
    }
    return getSellOrderMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SellOrderServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SellOrderServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SellOrderServiceStub>() {
        @java.lang.Override
        public SellOrderServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SellOrderServiceStub(channel, callOptions);
        }
      };
    return SellOrderServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SellOrderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SellOrderServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SellOrderServiceBlockingStub>() {
        @java.lang.Override
        public SellOrderServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SellOrderServiceBlockingStub(channel, callOptions);
        }
      };
    return SellOrderServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SellOrderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SellOrderServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SellOrderServiceFutureStub>() {
        @java.lang.Override
        public SellOrderServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SellOrderServiceFutureStub(channel, callOptions);
        }
      };
    return SellOrderServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SellOrderServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sellOrder(ds.trading.system.grpc.generated.SellOrderRequest request,
        io.grpc.stub.StreamObserver<ds.trading.system.grpc.generated.SellOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSellOrderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSellOrderMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.trading.system.grpc.generated.SellOrderRequest,
                ds.trading.system.grpc.generated.SellOrderResponse>(
                  this, METHODID_SELL_ORDER)))
          .build();
    }
  }

  /**
   */
  public static final class SellOrderServiceStub extends io.grpc.stub.AbstractAsyncStub<SellOrderServiceStub> {
    private SellOrderServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SellOrderServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SellOrderServiceStub(channel, callOptions);
    }

    /**
     */
    public void sellOrder(ds.trading.system.grpc.generated.SellOrderRequest request,
        io.grpc.stub.StreamObserver<ds.trading.system.grpc.generated.SellOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSellOrderMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SellOrderServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SellOrderServiceBlockingStub> {
    private SellOrderServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SellOrderServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SellOrderServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.trading.system.grpc.generated.SellOrderResponse sellOrder(ds.trading.system.grpc.generated.SellOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSellOrderMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SellOrderServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SellOrderServiceFutureStub> {
    private SellOrderServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SellOrderServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SellOrderServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.trading.system.grpc.generated.SellOrderResponse> sellOrder(
        ds.trading.system.grpc.generated.SellOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSellOrderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SELL_ORDER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SellOrderServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SellOrderServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SELL_ORDER:
          serviceImpl.sellOrder((ds.trading.system.grpc.generated.SellOrderRequest) request,
              (io.grpc.stub.StreamObserver<ds.trading.system.grpc.generated.SellOrderResponse>) responseObserver);
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

  private static abstract class SellOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SellOrderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.trading.system.grpc.generated.TradingService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SellOrderService");
    }
  }

  private static final class SellOrderServiceFileDescriptorSupplier
      extends SellOrderServiceBaseDescriptorSupplier {
    SellOrderServiceFileDescriptorSupplier() {}
  }

  private static final class SellOrderServiceMethodDescriptorSupplier
      extends SellOrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SellOrderServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SellOrderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SellOrderServiceFileDescriptorSupplier())
              .addMethod(getSellOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
