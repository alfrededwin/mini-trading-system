package ds.trading.system.client;

import ds.trading.system.grpc.generated.StockOrderRequest;
import ds.trading.system.grpc.generated.StockOrderResponse;
import ds.trading.system.grpc.generated.StockOrderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class CheckStockOrderServiceClient {

    private ManagedChannel channel = null;
    StockOrderServiceGrpc.StockOrderServiceBlockingStub clientStub = null;
    String host = null;
    int port = -1;

    public CheckStockOrderServiceClient (String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void initializeConnection () {
        System.out.println("Initializing Connecting to server at " + host + ":" + port);
        channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();
        clientStub = StockOrderServiceGrpc.newBlockingStub(channel);
    }
    public void closeConnection() {
        channel.shutdown();
    }

    public void processUserRequests() throws InterruptedException {
        while (true) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("\nEnter Trader ID, Quantity, Price, Order type :");
            String input[] = userInput.nextLine().trim().split(",");

            String traderId = input[0];
            int quantity = Integer.parseInt(input[1]);
            double price = Double.parseDouble(input[2]);
            String orderType = input[3];

            System.out.println("Requesting server perform stock order");
            StockOrderRequest request = StockOrderRequest
                    .newBuilder()
                    .setTraderId(traderId)
                    .setStockQuantity(quantity)
                    .setStockPrice(price)
                    .setOrderType(orderType)
                    .build();

            StockOrderResponse response = clientStub.stockOrder(request);
            System.out.printf("Trade order processed for " + response.getTraderId() + " request");
            Thread.sleep(1000);
        }
    }


}
