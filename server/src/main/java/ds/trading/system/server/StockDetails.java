package ds.trading.system.server;

public class StockDetails {

    private String traderId;
    private String orderSymbol;
    private int stockQuantity;
    private double stockPrice;
    private String orderType;

    public static final String SELL_ORDER_TYPE = "sell";
    public static final String BUY_ORDER_TYPE = "buy";

    public StockDetails(String traderId, int quantity, double price, String orderType) {
        this.traderId = traderId;
        this.stockQuantity = quantity;
        this.stockPrice = price;
        this.orderType = orderType;
    }

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public String getSymbol() {
        return orderSymbol;
    }

    public void setSymbol(String symbol) {
        this.orderSymbol = symbol;
    }

    public int getQuantity() {
        return stockQuantity;
    }

    public void setQuantity(int quantity) {
        this.stockQuantity = quantity;
    }

    public double getPrice() {
        return stockPrice;
    }

    public void setPrice(double price) {
        this.stockPrice = price;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
