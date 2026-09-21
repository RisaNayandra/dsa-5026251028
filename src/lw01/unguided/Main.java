package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Rental[] rentals = null;

        try (Scanner scanner = new Scanner(new File("rentals.txt"))) {
            int rentalCount = scanner.nextInt();
            rentals = new Rental[rentalCount];

            for (int i = 0; i < rentalCount; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();

                Rental rental= null;
                if (type.equalsIgnoreCase("LAPTOP")) {
                    rentals[i] = new LaptopRental(id, days);
                } else if (type.equalsIgnoreCase("PROJECTOR")) {
                    rentals[i] = new ProjectRental(id, days);
                }

                if (rentals[i] != null) {
                    rentals[i].displayRentalInfo();
                }

                rentals[i] = rental;

                @Override
                public int calculateCharge() {
                    return totalCharge;
                }

                @Override 
                public String label() {
                    return "Rental";
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("File rentals.txt tidak ditemukan!");
            return;
        }
        if (rentals != null) {
            for (Rental rental : rentals) {
                if (rental != null) {
                    System.out.println(rental.summary());
                }
            }
        }
    }
}
