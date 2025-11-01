import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.kroman.practice2.Transaction;
import org.kroman.practice2.TransactionAnalyzer;

import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    private List<Transaction> testTransactions;

    @BeforeEach
    void setUp() {
        Transaction t1 = new Transaction("01-02-2023", 100.0, "Дохід 1");
        Transaction t2 = new Transaction("15-02-2023", -50.0, "Витрата 1");
        Transaction t3 = new Transaction("05-03-2023", 150.0, "Дохід 2");
        Transaction t4 = new Transaction("20-02-2023", -30.0, "Витрата 2");
        testTransactions = Arrays.asList(t1, t2, t3, t4);
    }

    @Test
    void testCalculateTotalBalance() {
        double expectedBalance = 170.0;
        double actualBalance = TransactionAnalyzer.calculateTotalBalance(testTransactions);
        Assertions.assertEquals(expectedBalance, actualBalance, "Розрахунок загального балансу неправильний");
    }

    @Test
    void testCountTransactionsByMonth() {
        int expectedFebCount = 3;
        int actualFebCount = TransactionAnalyzer.countTransactionsByMonth(testTransactions, "02-2023");
        Assertions.assertEquals(expectedFebCount, actualFebCount, "Кількість транзакцій за лютий неправильна");

        int expectedMarCount = 1;
        int actualMarCount = TransactionAnalyzer.countTransactionsByMonth(testTransactions, "03-2023");
        Assertions.assertEquals(expectedMarCount, actualMarCount, "Кількість транзакцій за березень неправильна");
    }

    @Test
    void testFindTopExpenses() {
        Transaction t1 = new Transaction("01-01-2023", -100.0, "A");
        Transaction t2 = new Transaction("02-01-2023", -500.0, "B");
        Transaction t3 = new Transaction("03-01-2023", 1000.0, "C");
        Transaction t4 = new Transaction("04-01-2023", -1000.0, "D");
        Transaction t5 = new Transaction("05-01-2023", -50.0, "E");
        List<Transaction> txs = Arrays.asList(t1, t2, t3, t4, t5);
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(txs);

        // 4 витрати та 1 дохід, тому true
        Assertions.assertEquals(4, topExpenses.size());
        Assertions.assertEquals(-1000.0, topExpenses.get(0).getAmount());
        Assertions.assertEquals("D", topExpenses.get(0).getDescription());
        Assertions.assertEquals(-50.0, topExpenses.get(3).getAmount());
        Assertions.assertEquals("E", topExpenses.get(3).getDescription());
    }
}