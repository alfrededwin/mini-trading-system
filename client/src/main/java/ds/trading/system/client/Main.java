package ds.trading.system.client;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String host = args[0];
        int port = Integer.parseInt(args[1].trim());

        if (args.length != 2) {
            System.out.println("Usage Check Stock Order Service Client <host> <port> ");
            System.exit(1);
        }

        CheckStockOrderServiceClient client = new CheckStockOrderServiceClient(host, port);
        client.initializeConnection();
        client.processUserRequests();
        client.closeConnection();
    }
}
