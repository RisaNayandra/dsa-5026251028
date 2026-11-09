package lw04.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void sortCards (int[] arr, int passes) {
        int n = arr.length;
        for (int i = 0; i < passes && i < n - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[idx]) {
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }

    public static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        try {
            File file = new File("src/lw04/unguided/cards.txt");
            Scanner scanner = new Scanner(file);

            int n = scanner.nextInt();
            int[] cards = new int[n];
            for (int i = 0; i < n; i++) {
                cards[i] = scanner.nextInt();
            }

            int m = scanner.nextInt();
            int[] queries = new int[m];
            for (int i = 0; i < m; i++) {
                queries[i] = scanner.nextInt();
            }
            scanner.close();

            sortCards(cards, 2);

            for (int i = 0; i < n; i++) {
                System.out.print(cards[i] + (i == cards.length - 1 ? " " : " "));
            }
            System.out.println();

            for (int i = 0; i < m; i++) {
                int target = queries[i];
                int index = findCard(cards, target);
                if (index != -1) {
                    System.out.println(target + ": FOUND at index " + index);
                } else {
                    System.out.println(target + ": NOT FOUND");
                }
            }
        } 

        catch (FileNotFoundException e) {
            System.out.println("File cards.txt not found.");
        }
    }
}
