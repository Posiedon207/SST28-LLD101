import java.time.LocalDateTime;

class Transaction {
    private final String txnId;
    private final int amount;
    private final String mode;
    private TransactionState state;
    private LocalDateTime settledAt;

    Transaction(String txnId, int amount, String mode) {
        this.txnId = txnId;
        this.amount = amount;
        this.mode = mode;
        this.state = TransactionState.AWAITING;
    }

    void execute() {
        this.state = TransactionState.COMPLETED;
        this.settledAt = LocalDateTime.now();
    }

    String getTxnId() { return txnId; }
    int getAmount() { return amount; }
    String getMode() { return mode; }
    TransactionState getState() { return state; }
    LocalDateTime getSettledAt() { return settledAt; }

    @Override
    public String toString() {
        return "Txn#" + txnId + " | Rs." + amount + " via " + mode + " | " + state;
    }
}
