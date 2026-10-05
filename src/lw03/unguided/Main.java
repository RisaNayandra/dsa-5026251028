package lw03.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> courses = new LinkedHashMap<>();
        List<String> checkResults = new ArrayList<>();
        int rejectedOperations = 0;

        try {
            Scanner scanner = new Scanner(new File("src/lw03/unguided/enrollment.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" ");
                String action = parts[0];
                String courseCode = parts[1];

                if (action.equals("CHECK")) {
                    if (courses.containsKey(courseCode)) {
                        checkResults.add(courseCode + ": " + courses.get(courseCode) + " students");
                    } else {
                        checkResults.add(courseCode + ": Not found");
                    }
                } else if (action.equals("REGISTER")) {
                    int count = Integer.parseInt(parts[2]);

                    if (count <= 0) {
                        rejectedOperations++;
                    } else {
                        if (courses.containsKey(courseCode)) {
                            courses.put(courseCode, courses.get(courseCode) + count);
                        } else {
                            courses.put(courseCode, count);
                        }
                    }
                } else if (action.equals("WITHDRAW")) {
                    int count = Integer.parseInt(parts[2]);

                    if (count <= 0) {
                        rejectedOperations++;
                    } else {
                        if (courses.containsKey(courseCode)) {
                            int currentEnrollment = courses.get(courseCode);
                            if (currentEnrollment >= count) {
                                courses.put(courseCode, currentEnrollment - count);
                            } else {
                                rejectedOperations++;
                            }
                        } else {
                            rejectedOperations++;
                        }
                    }
                }
            } 

            scanner.close(); 

            System.out.println("===== Enrollment Checks =====");
            for (int i = 0; i < checkResults.size(); i++) {
                System.out.println(checkResults.get(i));
            }

            System.out.println();

            System.out.println("===== Final Enrollment =====");
            for (Map.Entry<String, Integer> entry : courses.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " students");
            }

            System.out.println();

            System.out.println("Rejected operations: " + rejectedOperations);

        } catch (FileNotFoundException e) {
            System.out.println("Error: enrollment.txt not found.");
        }
    }
}