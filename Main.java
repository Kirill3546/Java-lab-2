package org.example;
import java.util.List;
import lombok.Data;

@Data
public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        UrlDataReader reader = new UrlDataReader();
        List<String> lines = reader.read(filePath);

        TransactionCSVParser parser = new TransactionCSVParser();
        List<Transaction> transactions = parser.parseTransactionCSV(lines);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();

        // Баланс
        double totalBalance = analyzer.calculateTotalBalance();
        reportGenerator.printBalanceReport(totalBalance);

        // Найбільші витрати
        List<Transaction> topExpenses = analyzer.findTopExpenses();
        reportGenerator.printTopExpensesReport(topExpenses);

        // Кількість транзакцій за конкретний місяць
        String monthYear = "01-2024";
        int transactionsCount = analyzer.countTransactionsByMonth(monthYear);
        reportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);


        reportGenerator.printExpensesByCategory(transactions);


        reportGenerator.printExpensesByMonth(transactions);


        reportGenerator.printBiggestAndSmallestExpenses(transactions);



    }
}
