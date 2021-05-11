package ds.trading.system.server;


import ds.trading.systems.syncronization.lock.DistributedLock;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TradingServer {
    private int serverPort;
    private DistributedLock leaderLock;
    private AtomicBoolean isLeader = new AtomicBoolean(false);
    private byte[] leaderData;
    //    private Map<String, Stock> orderBook = new HashMap();
    private ArrayList<StockDetails> orderBook = new ArrayList<>();

    public TradingServer(String host, int port) throws InterruptedException, IOException, KeeperException {
        this.serverPort = port;
        leaderLock = new DistributedLock("Trading Server Test Cluster", buildServerData(host, port));
    }

    public boolean isLeader() {
        return isLeader.get();
    }

    private synchronized void setCurrentLeaderData(byte[] leaderData) {
        this.leaderData = leaderData;
    }

    private void tryToBeLeader() throws KeeperException, InterruptedException {
        Thread leaderCampaignThread = new Thread(new LeaderCampaignThread());
        leaderCampaignThread.start();
    }

    public void startServer() throws IOException, InterruptedException, KeeperException {
        Server server = ServerBuilder
                .forPort(serverPort)
                .addService(new OrderServiceImpl(this))
                .build();
        server.start();
        System.out.println("Trading Server Started and ready to accept requests on port " + serverPort);

        tryToBeLeader();
        server.awaitTermination();
    }

    public static String buildServerData(String IP, int port) {
        StringBuilder builder = new StringBuilder();
        builder.append(IP).append(":").append(port);
        return builder.toString();
    }

    public void setOrderBook(String traderId, double price, int quantity, String orderType) {
        StockDetails stock = new StockDetails(traderId, quantity, price, orderType);

        if (orderType.equals("sell")) {
            SellOrderTransaction(stock);
        } else {
            BuyOrderTransaction(stock);
        }
    }

    public void SellOrderTransaction(StockDetails stock) {
        System.out.println("Sell Order Requested, Checking Order Book for a match");
        orderBook.add(stock);
        boolean transactionPerformed = false;

        for (StockDetails orderItem: this.orderBook) {
            // Checking whether any traders are ready to sell
            if (orderItem.getOrderType().equals(StockDetails.BUY_ORDER_TYPE)) {
                if (orderItem.getPrice() == stock.getPrice()
                        && orderItem.getQuantity() == stock.getQuantity()
                        && !orderItem.getTraderId().equals(stock.getTraderId())) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Match Found. Orders are Executed and Trade takes Place");
                    System.out.println("Seller Id : " + stock.getTraderId());
                    System.out.println("Buyer  Id :" + orderItem.getTraderId());

                    orderBook.remove(orderItem);
                    transactionPerformed = true;
                    System.out.println("Transaction Completed for Sell Order");
                    System.out.println("------------------------------------------------------");
                    break;
                }
            }
        }

        if (!transactionPerformed) {
            System.out.println("No Match for Buy Orders");
        }
    }

    private void BuyOrderTransaction(StockDetails stock) {
        System.out.println("Buy Order Requested, Checking Order Book for a match");
        orderBook.add(stock);

        boolean transactionPerformed = false;

        for (StockDetails orderItem: this.orderBook) {
            // Checking whether any traders are ready to buy
            if (orderItem.getOrderType().equals(StockDetails.SELL_ORDER_TYPE)) {

                System.out.println("---------------------Buy Order------------------------");

                System.out.println(stock.getTraderId()+"-"+stock.getQuantity()+"-"+stock.getPrice()+"-"+stock.getOrderType());
                System.out.println(orderItem.getTraderId()+"-"+orderItem.getQuantity()+"-"+orderItem.getPrice()+"-"+orderItem.getOrderType());

                // check for only buy orders
                if (orderItem.getPrice() == stock.getPrice()
                        && orderItem.getQuantity() == stock.getQuantity()
                        && !orderItem.getTraderId().equals(stock.getTraderId())) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Match Found. Orders are Executed and Trade takes Place");
                    System.out.println("Buyer Id :" + stock.getTraderId());
                    System.out.println("Seller Id :" + orderItem.getTraderId());

                    orderBook.remove(orderItem);
                    transactionPerformed = true;
                    System.out.println("Transaction Completed for Buy Order");
                    System.out.println("------------------------------------------------------");
                    break;
                }
            }
        }

        if (!transactionPerformed) {
            System.out.println("No Match for Sell Orders");
        }
    }

    public synchronized String[] getCurrentLeaderData() {
        return new String(leaderData).split(":");
    }

    public List<String[]> getOthersData() throws KeeperException, InterruptedException {
        List<String[]> result = new ArrayList<>();
        List<byte[]> othersData = leaderLock.getOthersData();
        for (byte[] data : othersData) {
            String[] dataStrings = new
                    String(data).split(":");
            result.add(dataStrings);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        DistributedLock.setZooKeeperURL("localhost:2181");

        if (args.length != 1) {
            System.out.println("Usage executable-name <port>");
        }

        int serverPort = Integer.parseInt(args[0]);

        TradingServer server = new TradingServer("localhost", serverPort);
        server.startServer();
    }

    class LeaderCampaignThread implements Runnable {
        private byte[] currentLeaderData = null;

        @Override
        public void run() {
            System.out.println("Starting the leader Campaign");
            try {
                boolean leader = leaderLock.tryAcquireLock();
                while (!leader) {
                    byte[] leaderData =
                            leaderLock.getLockHolderData();
                    if (currentLeaderData != leaderData) {
                        currentLeaderData = leaderData;
                        setCurrentLeaderData(currentLeaderData);
                    }
                    Thread.sleep(10000);
                    leader = leaderLock.tryAcquireLock();
                }
                System.out.println("I got the leader lock. Now acting as primary");
                isLeader.set(true);
                currentLeaderData = null;
            } catch (Exception e) {
            }
        }
    }
}