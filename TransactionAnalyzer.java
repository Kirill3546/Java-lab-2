package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAnalyzer {
    private List<Transaction> transactions;
    private DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    // Метод для підрахунку транзакцій за місяць
    public int countTransactionsByMonth(String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            String transactionMonthYear = transaction.getDate().format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    // Метод для розрахунку загального балансу
    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    // Метод для пошуку 10 найбільших витрат (від’ємні суми)
    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // тільки витрати
                .sorted(Comparator.comparingDouble(Transaction::getAmount)) // від найменших (найбільші витрати)
                .limit(10)
                .collect(Collectors.toList());
    }

    // Метод для визначення найбільших і найменших витрат за період
    public Transaction[] findMinMaxExpenseByPeriod(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, dateFormatter);
        LocalDate end = LocalDate.parse(endDate, dateFormatter);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // тільки витрати
                .filter(t -> {
                    LocalDate d = t.getDate();
                    return (d.isEqual(start) || d.isAfter(start)) &&
                            (d.isEqual(end) || d.isBefore(end));
                })
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.isEmpty()) return new Transaction[]{null, null};
                            Transaction min = list.stream().min(Comparator.comparingDouble(Transaction::getAmount)).get();
                            Transaction max = list.stream().max(Comparator.comparingDouble(Transaction::getAmount)).get();
                            return new Transaction[]{min, max};
                        }
                ));
    }
}
