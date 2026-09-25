package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Data Structures
        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>(); // [Name, Balance]
        Queue<String[]> transactionQueue = new LinkedList<>();
        Stack<String[]> failedTransactionsStack = new Stack<>();

        // 1. Read transactions from transactions.txt and populate customerList
        try {
            File file = new File("src/lw02/prelab/transaction.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                String name = parts[0];
                String type = parts[1];
                String amount = parts[2];

                // Store transaction as String[] -> [NAME, TYPE, AMOUNT]
                transactionList.add(new String[]{name, type, amount});

                // Add customer to customerList if appearing for the first time
                boolean exists = false;
                for (String[] customer : customerList) {
                    if (customer[0].equals(name)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    customerList.add(new String[]{name, "0"});
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: transactions.txt file not found.");
            return;
        }

        // 2. Move transactions from LinkedList into Queue
        while (!transactionList.isEmpty()) {
            transactionQueue.add(transactionList.poll());
        }

        // 3. Process transactions using Queue (FIFO)
        while (!transactionQueue.isEmpty()) {
            String[] currentTx = transactionQueue.poll();
            String name = currentTx[0];
            String type = currentTx[1];
            int amount = Integer.parseInt(currentTx[2]);

            // Find matching customer record
            String[] targetCustomer = null;
            for (String[] customer : customerList) {
                if (customer[0].equals(name)) {
                    targetCustomer = customer;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);

                if (type.equals("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equals("WITHDRAW")) {
                    if (amount > currentBalance) {
                        // Failed transaction -> push onto Stack
                        failedTransactionsStack.push(currentTx);
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        // 4. Display Final Balances
        System.out.println("=== Final Balances ===");
        for (String[] customer : customerList) {
            System.out.println(customer[0] + ": " + customer[1]);
        }

        // 5. Display Failed Transactions (LIFO)
        System.out.println("=== Failed Transactions ===");
        while (!failedTransactionsStack.isEmpty()) {
            String[] failedTx = failedTransactionsStack.pop();
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}
