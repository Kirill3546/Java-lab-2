package org.example;

import java.util.ArrayList;
import java.util.List;

public class TransactionCSVParser {
    public List<Transaction> parseTransactionCSV(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();
        for(String line : lines){
            String[] values = line.split(",");
            Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2]);
            transactions.add(transaction);
        }
        return transactions;
    }
}
