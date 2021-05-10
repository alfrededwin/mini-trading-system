package ds.trading.system.server;

import ds.trading.system.server.SellOrderServiceImpl;
import ds.trading.systems.syncronization.lock.DistributedLock;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class TradingServer {
//    public static void main (String[] args) throws Exception{
//        int serverPort = 11436;
//        Server server = ServerBuilder
//                .forPort(serverPort)
//                .addService(new SellOrderServiceImpl())
//                .build();
//        server.start();
//        System.out.println("Trading Server Started and ready to accept requests on port " + serverPort);
//        server.awaitTermination();
//    }


    private int serverPort;
    private DistributedLock leaderLock;
    private AtomicBoolean isLeader = new AtomicBoolean(false);
    private byte[] leaderData;
    private Map<String, Double> accounts = new HashMap();

    public TradingServer(String host, int port) throws
            InterruptedException, IOException, KeeperException {
        this.serverPort = port;
        leaderLock = new DistributedLock("BankServerTestCluster", buildServerData(host, port));
    }

    public void startServer() throws IOException, InterruptedException, KeeperException {
        Server server = ServerBuilder
                .forPort(serverPort)
                .addService(new SellOrderServiceImpl(this))
                .addService(new BuyOrderServiceImpl(this))
                .build();
        server.start();
        System.out.println("Trading Server Started and ready to accept requests on port " + serverPort);

        tryToBeLeader();
        server.awaitTermination();
    }

    public synchronized String[] getCurrentLeaderData() {
        return new String(leaderData).split(":");
    }
    public List<String[]> getOthersData() throws KeeperException, InterruptedException {
        List<String[]> result = new ArrayList<>();
        List<byte[]> othersData = leaderLock.getOthersData();
        for (byte[] data : othersData) {
            String[] dataStrings = new String(data).split(":");
            result.add(dataStrings);
        }
        return result;
    }

    public void setAccountBalance(String accountId, double value) {
        accounts.put(accountId, value);
    }
    public double getAccountBalance(String accountId) {
        Double value = accounts.get(accountId);
        return (value != null) ? value : 0.0;
    }

    public static String buildServerData(String IP, int port) {
        StringBuilder builder = new StringBuilder();
        builder.append(IP).append(":").append(port);
        return builder.toString();
    }

    public boolean isLeader() {
        return isLeader.get();
    }
    private synchronized void setCurrentLeaderData(byte[] leaderData) {
        this.leaderData = leaderData;
    }

    public static void main (String[] args) throws Exception{
        if (args.length != 1) {
            System.out.println("Usage executable-name <port>");
        }

        int serverPort = Integer.parseInt(args[0]);
        DistributedLock.setZooKeeperURL("localhost:2181");

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
            } catch (Exception e){
            }
        } }


    private void tryToBeLeader() throws KeeperException,
            InterruptedException {
        Thread leaderCampaignThread = new Thread(new
                LeaderCampaignThread());
        leaderCampaignThread.start();
    }





}

