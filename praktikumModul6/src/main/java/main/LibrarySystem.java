package main;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import data.Admin;
import data.Student;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LibrarySystem {

    // menyimpan semua object dalam satu array dengan teknik polymorphism
    public static Book[] daftarBuku = new Book[100]; // array satu dimensi untuk menyimpan buku
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static int i = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        daftarBuku[i++] = new StoryBook("SB32F", "The Eminancse", 17, "Story", "Cid");
        daftarBuku[i++] = new HistoryBook("HB324", "The Book", 2, "History", "Tatalov");
        daftarBuku[i++] = new TextBook("TB323", "The Trash", 4, "Text", "Stelle");

        // Mengisi daftar mahasiswa
        studentList.add(new Student("202310370311176", "Farhan Abdurrahman", "Teknik", "Ninja"));
        studentList.add(new Student("202310370311111", "Alex", "Teknik", "Informatika"));
        studentList.add(new Student("202310370311177", "Tono", "Teknik", "Kedokteran"));


        boolean isRunning = true;
        while (isRunning) {
            try {
                System.out.println("===== Library System =====");
                System.out.println("1. Login sebagai Mahasiswa");
                System.out.println("2. Login sebagai Admin");
                System.out.println("3. Keluar");
                System.out.print("Pilih antara (1-3): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Masukkan NIM: ");
                        String nimStudent = scanner.next();
                        scanner.nextLine();
                        while (true) {
                            if (nimStudent.length() != 15) {
                                System.out.print("NIM harus 15 digit!\n");
                                System.out.print("Masukkan NIM: ");
                                nimStudent = scanner.nextLine();
                            } else if (checkNim(nimStudent)) {
                                Student student = new Student(nimStudent);
                                student.login();
                                break;
                            } else {
                                System.out.println("NIM tidak terdaftar!");
                                break;
                            }
                        }
                        break;
                    case 2:
                        Admin admin = new Admin();
                        admin.login();
                        break;
                    case 3:
                        System.out.println("Terima kasih, semoga puas dengan pelayanan kami.");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (InputMismatchException e) {
                System.err.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine();
            }
        }
    }

    public static boolean checkNim(String nim) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }
}
