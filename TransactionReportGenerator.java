package org.example;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionReportGenerator {

    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public void printExpensesByCategory(List<Transaction> transactions) {
        System.out.println("Витрати по категоріях:");
        Map<String, Double> expensesByCategory = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void printExpensesByMonth(List<Transaction> transactions) {
        System.out.println("Витрати по місяцях:");
        Map<String, Double> expensesByMonth = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> t.getDate().format(java.time.format.DateTimeFormatter.ofPattern("MM-yyyy")),
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        for (Map.Entry<String, Double> entry : expensesByMonth.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    public void printMinMaxExpenseReport(Transaction[] minMax, String startDate, String endDate) {
        System.out.println("Найбільші та найменші витрати за період " + startDate + " - " + endDate + ":");
        if (minMax[0] == null && minMax[1] == null) {
            System.out.println("   Витрат у цьому періоді не знайдено.");
            return;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("Мінімальні витрати:");
        if (minMax[0] != null) {
            System.out.println("   " + minMax[0].getDate().format(df) + " (" + minMax[0].getAmount() + " грн) [" + minMax[0].getDescription() + "]");
        }

        System.out.println("Максимальні витрати:");
        if (minMax[1] != null) {
            System.out.println("   " + minMax[1].getDate().format(df) + " (" + minMax[1].getAmount() + " грн) [" + minMax[1].getDescription() + "]");
        }
    }


    public void printExpensesByCategoryChart(List<Transaction> transactions) {
        System.out.println("=== Витрати по категоріях ===");
        Map<String, Double> expensesByCategory = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
            int stars = (int) Math.round(Math.abs(entry.getValue()) / 1000.0);
            System.out.printf("%-10s : %.2f грн  %s%n", entry.getKey(), entry.getValue(), "*".repeat(stars));
        }
    }


    public void printExpensesByMonthChart(List<Transaction> transactions) {
        System.out.println("=== Витрати по місяцях ===");
        Map<String, Double> expensesByMonth = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> t.getDate().format(java.time.format.DateTimeFormatter.ofPattern("MM-yyyy")),
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        for (Map.Entry<String, Double> entry : expensesByMonth.entrySet()) {
            int stars = (int) Math.round(Math.abs(entry.getValue()) / 1000.0);
            System.out.printf("%-7s : %.2f грн  %s%n", entry.getKey(), entry.getValue(), "*".repeat(stars));
        }
    }
    public void printBiggestAndSmallestExpenses(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("Немає транзакцій для аналізу.");
            return;
        }

        // тільки витрати
        List<Transaction> expenses = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .toList();

        if (expenses.isEmpty()) {
            System.out.println("Витрат немає.");
            return;
        }

        Transaction maxExpense = expenses.stream()
                .min((t1, t2) -> Double.compare(t1.getAmount(), t2.getAmount()))
                .orElse(null);

        Transaction minExpense = expenses.stream()
                .max((t1, t2) -> Double.compare(t1.getAmount(), t2.getAmount()))
                .orElse(null);

        System.out.println("Найбільша витрата: "
                + maxExpense.getDescription() + " = " + maxExpense.getAmount());
        System.out.println("Найменша витрата: "
                + minExpense.getDescription() + " = " + minExpense.getAmount());
    }

}
