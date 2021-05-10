package ds.trading.system.client;

import ds.trading.system.grpc.generated.SellOrderRequest;
import ds.trading.system.grpc.generated.SellOrderResponse;
import ds.trading.system.grpc.generated.SellOrderServiceGrpc;
import io.grpc.ManagedChannel;
import ds.trading.system.client.CheckSellOrderServiceClient;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class CheckSellOrderServiceClient {

    private ManagedChannel channel = null;
    SellOrderServiceGrpc.SellOrderServiceBlockingStub clientStub = null;
    String host = null;
    int port = -1;

    public static void main(String[] args) throws InterruptedException {
        String host = null;
        int port = -1;
        if (args.length != 2) {
            System.out.println("Usage Check Sell Order Service <host> <port>");
            System.exit(1);
        }
        host = args[0];
        port = Integer.parseInt(args[1].trim());
        CheckSellOrderServiceClient client = new CheckSellOrderServiceClient(host, port);
        client.initializeConnection();
        client.processUserRequests();
        client.closeConnection();
    }

    public CheckSellOrderServiceClient (String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void initializeConnection () {
        System.out.println("Initializing Connecting to server at " + host + ":" +
                port);
        channel = ManagedChannelBuilder.forAddress("localhost", 11436)
                .usePlaintext()
                .build();
        clientStub = SellOrderServiceGrpc.newBlockingStub(channel);
    }

    private void closeConnection() {
        channel.shutdown();
    }

    private void processUserRequests() throws InterruptedException {
        while (true) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("\nEnter Order ID to check the quantity :");
            String orderId = userInput.nextLine().trim();
            System.out.println("Requesting server to check the quantity for "
                    + orderId.toString());
            SellOrderRequest request = SellOrderRequest
                    .newBuilder()
                    .setOrderId(orderId)
                    .build();
            SellOrderResponse response = clientStub.sellOrder(request);
            System.out.printf("My Quantity is " + response.getQuantity());
            Thread.sleep(1000);
        }
    }




}
