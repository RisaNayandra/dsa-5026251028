package lw02.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> requestList = new LinkedList<>();
        LinkedList<String[]> bookList = new LinkedList<>();
        LinkedList<String[]> memberList = new LinkedList<>();
        LinkedList<String[]> successList = new LinkedList<>();

        Queue<String[]> requestQueue = new LinkedList<>();
        Stack<String[]> failedstack = new Stack<>();

        int MAX_BORROW = 2;

        //stock buku
        bookList.add(new String[]{"Kalkulus", "2"});
        bookList.add(new String[]{"Fisika", "1"});
        bookList.add(new String[]{"Statistika", "2"});

        //baca file borrowing.txt
        try {
            File file = new File("src/lw02/unguided/borrowing.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                String name = parts[0];
                String bookTitle = parts[1];

                //request dari store adalah name dan bookTitle
                requestList.add(new String[]{name, bookTitle});

                //tambahkan member apabila belum ada di memberList
                boolean exists = false;
                for (String[] member : memberList) {
                    if (member[0].equals(name)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    memberList.add(new String[]{name, "0"});
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: borrowing.txt file not found.");
            return;
        }

        //pinamkan request ke dalam queue
            while (!requestList.isEmpty()) {
                String[] request = requestList.poll();
                String name = request[0];
                String bookTitle = request[1];

                //temukan buku yang sesuai
                String[] targetBook = null;
                for (String[] book : bookList) {
                    if (book[0].equals(bookTitle)) {
                        targetBook = book;
                            break;
                    }
                }

                //temukan member yang sesuai
                String[] targetMember = null;
                for (String[] member : memberList) {
                    if (member[0].equals(name)) {
                        targetMember = member;
                        break;
                    }
                }

                if (targetBook != null && targetMember != null) {
                    int stock = Integer.parseInt(targetBook[1]);
                    int borrowed = Integer.parseInt(targetMember[1]);

                    //cek apakah buku tersedia dan member belum melebihi batas pinjaman
                    if (stock > 0 && borrowed < MAX_BORROW) {
                        //sukses
                        targetBook[1] = String.valueOf(stock - 1);
                        targetMember[1] = String.valueOf(borrowed + 1);
                        successList.add(request);
                    } else {
                        //gagal
                        failedstack.push(request);
                    }
                }
            }
            
            //output hasil
            System.out.println("=== Successfully Processed Requests ===");
            for (String[] req : successList) {
                System.out.println(req[0] + " borrowed " + req[1]);
            }

            System.out.println("=== Remaining Book Stock ===");
            for (String[] book : bookList) {
                System.out.println(book[0] + ": " + book[1]);
            }

            System.out.println("=== Failed Request ===");
            while (!failedstack.isEmpty()) {
                String[] req = failedstack.pop();
                System.out.println(req[0] + " failed to borrow " + req[1]);
            }
        }
}
