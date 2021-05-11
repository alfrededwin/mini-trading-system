package ds.trading.system.server;

import ds.trading.system.grpc.generated.StockOrderRequest;
import ds.trading.system.grpc.generated.StockOrderResponse;
import ds.trading.system.grpc.generated.StockOrderServiceGrpc;
import ds.trading.systems.syncronization.lock.tx.DistributedTx;
import ds.trading.systems.syncronization.lock.tx.DistributedTxCoordinator;
import ds.trading.systems.syncronization.lock.tx.DistributedTxListener;
import ds.trading.systems.syncronization.lock.tx.DistributedTxParticipant;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl extends StockOrderServiceGrpc.StockOrderServiceImplBase implements DistributedTxListener {

    private ManagedChannel channel = null;
    StockOrderServiceGrpc.StockOrderServiceBlockingStub clientStub = null;
    private TradingServer server;

    private StockDetails holdStockDetails;
    private boolean transactionState = false;


    public OrderServiceImpl(TradingServer server) {
        this.server = server;
    }

    private void startDistributedTx(String traderId, double price, int quantity, String orderType) {
        try {
            server.getTransaction().start(traderId, String.valueOf(UUID.randomUUID()));
            holdStockDetails = new StockDetails(traderId,quantity,price,orderType);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stockOrder(StockOrderRequest request, StreamObserver<StockOrderResponse> responseObserver) {

        String traderId = request.getTraderId();
        String symbol = "Rice";
        int quantity = request.getStockQuantity();
        double price = request.getStockPrice();
        String orderType = request.getOrderType();
        boolean status = false;

        if (server.isLeader()) {
            // Act as primary
            try {
                System.out.println("Updating order book as Primary");
//                updateOrderBook(traderId, price, quantity, orderType);
                startDistributedTx(traderId, price, quantity, orderType);
                updateSecondaryServers(traderId, price, quantity, orderType);
                System.out.println("Going To Start Performing");

                if (quantity >0 || price > 0.0) {
                    ((DistributedTxCoordinator) server.getTransaction()).perform();

                } else {
                    ((DistributedTxCoordinator) server.getTransaction()).sendGlobalAbort();
                }

//                status = true;
            } catch (Exception e) {
                System.out.println("Error while updating the order book" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            // Act As Secondary
            if (request.getIsSentByPrimary()) {
                System.out.println("Updating order book on secondary, on Primary's command");
//                updateOrderBook(traderId, price, quantity, orderType);
                startDistributedTx(traderId, price, quantity, orderType);
                if (quantity >0 || price > 0.0) {
                    ((DistributedTxParticipant) server.getTransaction()).voteCommit();

                } else {
                    ((DistributedTxParticipant) server.getTransaction()).voteAbort();
                }

            } else {
                StockOrderResponse response = callPrimary(traderId, price, quantity, orderType);
                if (response.getOrderStatus()) {
                    transactionState = true;
                }
            }
        }
        StockOrderResponse response = StockOrderResponse.newBuilder()
                .setOrderStatus(transactionState)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private void updateOrderBook() {
        if (holdStockDetails != null) {
            String traderId = holdStockDetails.getTraderId();
            double price = holdStockDetails.getPrice();
            int quantity = holdStockDetails.getQuantity();
            String orderType = holdStockDetails.getOrderType();
            server.setOrderBook(traderId, price, quantity, orderType);
            System.out.println("Trader ID: " + traderId + " placed a " + orderType + " order.");
            holdStockDetails = null;
        }

    }

    private StockOrderResponse callServer(String traderId, double price, int quantity, String orderType, boolean isSentByPrimary, String IPAddress, int port) {
        System.out.println("Call Server " + IPAddress + ":" + port);
        channel = ManagedChannelBuilder.forAddress(IPAddress, port)
                .usePlaintext()
                .build();

        clientStub = StockOrderServiceGrpc.newBlockingStub(channel);
        StockOrderRequest request = StockOrderRequest.newBuilder()
                .setTraderId(traderId)
                .setStockPrice(price)
                .setOrderType(orderType)
                .setOrderSymbol("Rice")
                .setStockQuantity(quantity)
                .setIsSentByPrimary(isSentByPrimary)
                .build();
        StockOrderResponse response = clientStub.stockOrder(request);
        return response;
    }

    private StockOrderResponse callPrimary(String traderId, double price, int quantity, String orderType) {
        System.out.println("Calling Primary server");
        String[] currentLeaderData = server.getCurrentLeaderData();
        String IPAddress = currentLeaderData[0];
        int port = Integer.parseInt(currentLeaderData[1]);
        return callServer(traderId, price, quantity, orderType, false, IPAddress, port);
    }


    private void updateSecondaryServers(String traderId, double price, int quantity, String orderType) throws KeeperException, InterruptedException {
        System.out.println("Updating Secondary Servers");
        List<String[]> othersData = server.getOthersData();

        for (String[] data : othersData) {
            String IPAddress = data[0];
            int port = Integer.parseInt(data[1]);
            callServer(traderId, price, quantity,orderType,true, IPAddress, port);
        }
    }

    @Override
    public void onGlobalCommit() {
        updateOrderBook();
    }

    @Override
    public void onGlobalAbort() {
        holdStockDetails = null;
        System.out.println("Transaction Aborted by the Coordinator");
    }
}
