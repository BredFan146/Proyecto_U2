import java.util.ArrayList;

public class TransactionRepository {
    private final ArrayList<Transaction>transactions;
    public TransactionRepository(){

        this.transactions=new ArrayList<>();
    }
    public void addTransaction(Transaction transaction){

        transactions.add(transaction);
    }
    public ArrayList<Transaction>getAllTransactions(){

        return transactions;
    }
    // Método para obtener todas las transacciones asociadas con un libro específico
    public ArrayList<Transaction> getTransactionsByBook(Book book) {
        ArrayList<Transaction> transactionsByBook = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getBook().equals(book)) {
                transactionsByBook.add(transaction);
            }
        }
        return transactionsByBook;
    }
    // Método para obtener todas las transacciones asociadas con un cliente específico
    public ArrayList<Transaction> getTransactionsByClient(Client client) {
        ArrayList<Transaction> transactionsByClient = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getClient().equals(client)) {
                transactionsByClient.add(transaction);
            }
        }
        return transactionsByClient;
    }
}


