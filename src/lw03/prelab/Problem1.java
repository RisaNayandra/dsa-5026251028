package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        List<String> playlist = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/playlist.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("ADD ")) {
                    String song = line.substring(4).trim();
                    playlist.add(song);

                } else if (line.startsWith("INSERT ")) {
                    String remaining = line.substring(7).trim();

                    int spaceIndex = remaining.indexOf(' ');

                    int index = Integer.parseInt(
                        remaining.substring(0, spaceIndex)
                    );

                    String song = remaining.substring(spaceIndex + 1).trim();

                    playlist.add(index, song);

                } else if (line.startsWith("REMOVE ")) {
                    String song = line.substring(7).trim();

                    playlist.remove(song);
                }
            }

            scanner.close();

            System.out.println("Total songs: " + playlist.size());

            for (int i = 0; i < playlist.size(); i++) {
                System.out.println((i+1) + ": " + playlist.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: playlist.txt not found.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid INSERT index.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid index format.");
        }
    }
}