package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // Problem 1
        List<String> daftarLagu = new ArrayList<>();
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (scanner.hasNextLine()) {
            String baris = scanner.nextLine().trim();
            if (baris.isEmpty()) {
                continue;
            }

            String[] perintah = baris.split(" ", 2);
            String komando = perintah[0];

            if (komando.equals("ADD")) {
                daftarLagu.add(perintah[1]);
            } else if (komando.equals("INSERT")) {
                String[] rincian = perintah[1].split(" ", 2);
                int pos = Integer.parseInt(rincian[0]);
                daftarLagu.add(pos, rincian[1]);
            } else if (komando.equals("REMOVE")) {
                daftarLagu.remove(perintah[1]);
            }
        }
        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + daftarLagu.size());
        for (int idx = 0; idx < daftarLagu.size(); idx++) {
            System.out.println((idx + 1) + ": " + daftarLagu.get(idx));
        }
        System.out.println();


        // Problem 2
        Set<String> daftarPeserta = new LinkedHashSet<>();
        int counterDuplikat = 0;

        scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while (scanner.hasNextLine()) {
            String namaPeserta = scanner.nextLine().trim();
            if (namaPeserta.isEmpty()) {
                continue;
            }

            if (daftarPeserta.contains(namaPeserta)) {
                counterDuplikat++;
            } else {
                daftarPeserta.add(namaPeserta);
            }
        }
        scanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + daftarPeserta.size());
        int nomorUrut = 1;
        for (String p : daftarPeserta) {
            System.out.println(nomorUrut + ". " + p);
            nomorUrut++;
        }
        System.out.println("Duplicate registrations: " + counterDuplikat);
        System.out.println();


        // Problem 3
        Map<String, Integer> stokBarang = new LinkedHashMap<>();
        int penjaulanGagal = 0;

        scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (scanner.hasNextLine()) {
            String baris = scanner.nextLine().trim();
            if (baris.isEmpty()) {
                continue;
            }

            String[] komponen = baris.split(" ");
            String tipeAksi = komponen[0];
            String item = komponen[1];
            int qty = Integer.parseInt(komponen[2]);

            int sisaStok = 0;
            if (stokBarang.containsKey(item)) {
                sisaStok = stokBarang.get(item);
            }

            if (tipeAksi.equals("ADD")) {
                stokBarang.put(item, sisaStok + qty);
            } else if (tipeAksi.equals("SELL")) {
                if (stokBarang.containsKey(item) && sisaStok >= qty) {
                    stokBarang.put(item, sisaStok - qty);
                } else {
                    penjaulanGagal++;
                }
            }
        }
        scanner.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : stokBarang.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + penjaulanGagal);
    }
}