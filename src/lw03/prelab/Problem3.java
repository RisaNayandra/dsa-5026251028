package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/inventory.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" ");

                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {
                    if (inventory.containsKey(product)) {
                        int currentStock = inventory.get(product);
                        inventory.put(product, currentStock + quantity);
                    } else {
                        inventory.put(product, quantity);
                    }

                } else if (type.equals("SELL")) {
                    if (inventory.containsKey(product)) {
                        int currentStock = inventory.get(product);

                        if (currentStock >= quantity) {
                            inventory.put(product, currentStock - quantity);
                        } else {
                            failedSales++;
                        }
                    } else {
                        failedSales++;
                    }
                }
            }

            scanner.close();

            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            System.out.println("Failed sales: " + failedSales);

        } catch (FileNotFoundException e) {
            System.out.println("Error: inventory.txt not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid quantity.");
        }
    }
}