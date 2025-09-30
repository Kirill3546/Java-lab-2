package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionCSVParserTest {

    @Test
    void testParseTransactionCSV() {

        List<String> lines = Arrays.asList(
                "01-01-2024,100.0,Coffee",
                "05-01-2024,-250.0,Rent"
        );


        TransactionCSVParser parser = new TransactionCSVParser();


        List<Transaction> transactions = parser.parseTransactionCSV(lines);


        assertEquals(2, transactions.size(), "Повинно бути 2 транзакції");

        assertEquals("01-01-2024", transactions.get(0).getDate());
        assertEquals(100.0, transactions.get(0).getAmount());
        assertEquals("Coffee", transactions.get(0).getDescription());

        assertEquals("05-01-2024", transactions.get(1).getDate());
        assertEquals(-250.0, transactions.get(1).getAmount());
        assertEquals("Rent", transactions.get(1).getDescription());
    }
}
