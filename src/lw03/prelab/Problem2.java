package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem2 {
    public static void main(String[] args) {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/participants.txt"));

            while (scanner.hasNextLine()) {
                String name = scanner.nextLine().trim();

                if (!name.isEmpty()) {
                    if (!participants.add(name)) {
                        duplicateCount++;
                    }
                }
            }

            scanner.close();

            System.out.println("Unique participants: " + participants.size());

            int num = 1;

            for (String participant : participants) {
                System.out.println(num + ". " + participant);
                num++;
            }

            System.out.println("Duplicate registrations: " + duplicateCount);

        } catch (FileNotFoundException e) {
            System.out.println("Error: participants.txt not found.");
        }
    }
}