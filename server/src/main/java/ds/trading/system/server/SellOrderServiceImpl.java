//package ds.trading.system.server;
//
//import ds.trading.system.grpc.generated.SellOrderResponse;
//import ds.trading.system.grpc.generated.SellOrderServiceGrpc;
//
//import java.util.Random;
//
//public class SellOrderServiceImpl extends SellOrderServiceGrpc.SellOrderServiceImplBase {
//
//    @Override
//    public void sellOrder(ds.trading.system.grpc.generated.SellOrderRequest request,
//              io.grpc.stub.StreamObserver<ds.trading.system.grpc.generated.SellOrderResponse> responseObserver) {
//
//        String orderId = request.getOrderId();
//        System.out.println("Request received..");
//        int quantity = getQuantity(orderId);
//        SellOrderResponse response = SellOrderResponse
//                .newBuilder()
//                .setQuantity(quantity)
//                .build();
//
//        System.out.println("Responding, Quantity for order " + orderId + " is " + quantity);
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//
//    }
//
//    private int getQuantity(String orderId) {
//        System.out.println("Checking Quantity for Order " + orderId);
//        return new Random().nextInt() * 10000;
//    }
//
//
//}
