import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kroman.practice2.Transaction;
import org.kroman.practice2.TransactionCSVReader;

import java.util.List;

class TransactionCSVReaderTest {

    @Test
    void testReadTransactions() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій порожній");

        Transaction firstTransaction = transactions.getFirst();
        Assertions.assertEquals("05-12-2023", firstTransaction.getDate());
        Assertions.assertEquals(-7850, firstTransaction.getAmount());
        Assertions.assertEquals("Сільпо", firstTransaction.getDescription());
    }
}