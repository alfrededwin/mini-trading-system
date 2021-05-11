package ds.trading.systems.syncronization.lock.tx;

public interface DistributedTxListener {
    void onGlobalCommit();
    void onGlobalAbort();
}
