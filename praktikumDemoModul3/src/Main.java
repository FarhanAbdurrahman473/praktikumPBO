package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Book> daftarBuku = new ArrayList<>();
    private static ArrayList<Student> daftarMahasiswa = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Library System Login");
            System.out.println("1. Login sebagai Mahasiswa");
            System.out.println("2. Login sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih antara (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan NIM : ");
                    String nimStudent = scanner.next();
                    if (nimStudent.length() != 15) {
                        System.out.println("NIM tidak valid! Harus 15 karakter.");
                        break;
                    }
                    Student student = new Student(nimStudent);
                    student.login();
                    break;
                case 2:
                    Admin admin = new Admin();
                    admin.login();
                    break;
                case 3:
                    System.out.println("Terima kasih semoga puas dengan pelayanan kami");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static abstract class User {
        private String nim;

        public User(String nim) {
            this.nim = nim;
        }

        public String getNim() {
            return nim;
        }

        public abstract void displayBooks();
    }

    public static class Student extends User {
        private String name;
        private String faculty;
        private String studyProgram;
        private ArrayList<Book> borrowedBooks = new ArrayList<>();

        public Student(String nim) {
            super(nim);
        }

        public Student(String nim, String name, String faculty, String studyProgram) {
            super(nim);
            this.name = name;
            this.faculty = faculty;
            this.studyProgram = studyProgram;
        }

        public void login() {
            if (checkNim(getNim())) {
                System.out.println("Login berhasil sebagai Mahasiswa");
                menuStudent();
            } else {
                System.out.println("NIM Mahasiswa tidak valid atau tidak ditemukan");
            }
        }

        private boolean checkNim(String nim) {
            return nim.length() == 15;
        }

        private void menuStudent() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Dashboard Mahasiswa");
                System.out.println("1. Tampilkan Buku");
                System.out.println("2. Pinjam Buku");
                System.out.println("3. Kembalikan Buku");
                System.out.println("4. Logout");
                System.out.print("Pilih antara (1-4): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayBooks();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        logout();
                        return;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        }

        @Override
        public void displayBooks() {
            // Implementasi menampilkan daftar buku
            System.out.println("Daftar Buku Tersedia:");
            System.out.println("================================================================");
            System.out.println("|| No. || Id Buku || Nama Buku || Author || Category || Stock ||");
            int index = 1;
            for (Book book : daftarBuku) {
                if (book != null) {
                    System.out.printf("|| %-4d || %-8s || %-20s || %-15s || %-10s || %-5d ||%n", index, book.getIdBuku(), book.getJudul(), book.getAuthor(), book.getCategory(), book.getStok());
                    System.out.println("================================================================");
                    index++;
                }
            }
        }

        private void borrowBook() {
            System.out.print("Masukkan ID Buku yang ingin dipinjam: ");
            String bookId = scanner.next();
            Book selectedBook = findBookById(bookId);
            if (selectedBook != null && selectedBook.getStok() > 0) {
                selectedBook.setStok(selectedBook.getStok() - 1);
                borrowedBooks.add(selectedBook);
                System.out.println("Berhasil meminjam buku: " + selectedBook.getJudul());
            } else {
                System.out.println("Buku tidak tersedia atau ID buku tidak ditemukan");
            }
        }

        private void returnBook() {
            if (borrowedBooks.isEmpty()) {
                System.out.println("Anda belum meminjam buku.");
                return;
            }
            System.out.println("Buku yang Anda pinjam:");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                System.out.println((i + 1) + ". " + borrowedBooks.get(i).getJudul());
            }
            System.out.print("Pilih buku yang akan dikembalikan (nomor): ");
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= borrowedBooks.size()) {
                Book returnedBook = borrowedBooks.remove(choice - 1);
                returnedBook.setStok(returnedBook.getStok() + 1);
                System.out.println("Buku " + returnedBook.getJudul() + " berhasil dikembalikan.");
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }

        private Book findBookById(String id) {
            for (Book book : daftarBuku) {
                if (book != null && book.getIdBuku().equals(id)) {
                    return book;
                }
            }
            return null;
        }

        private void logout() {
            System.out.println("Logout berhasil.");
        }

        public String getName() {
            return name;
        }

        public String getFaculty() {
            return faculty;
        }

        public String getStudyProgram() {
            return studyProgram;
        }

        public ArrayList<Book> getBorrowedBooks() {
            return borrowedBooks;
        }
    }

    public static class Admin extends User {
        private ArrayList<Student> studentList = new ArrayList<>();

        public Admin() {
            super("admin");
        }

        public void login() {
            System.out.print("Masukkan Username (admin): ");
            String username = scanner.next();
            System.out.print("Masukkan Password (admin): ");
            String password = scanner.next();
            if (isAdmin(username, password)) {
                System.out.println("Login berhasil sebagai Admin");
                menuAdmin();
            } else {
                System.out.println("User Admin tidak ditemukan");
            }
        }

        private boolean isAdmin(String username, String password) {
            // Implementasi verifikasi admin
            return username.equals("admin") && password.equals("admin");
        }

        private void menuAdmin() {
            while (true) {
                System.out.println("Dashboard Admin");
                System.out.println("1. Tambah Mahasiswa");
                System.out.println("2. Tampilkan Mahasiswa");
                System.out.println("3. Input Buku");
                System.out.println("4. Tampilkan Daftar Buku");
                System.out.println("5. Logout");
                System.out.print("Pilih antara (1-5): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayStudents();
                        break;
                    case 3:
                        inputBook();
                        break;
                    case 4:
                        displayBooks();
                        break;
                    case 5:
                        System.out.println("Logout berhasil.");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        }

        private void addStudent() {
            // Implementasi penambahan mahasiswa
            System.out.println("Menambahkan mahasiswa...");
            System.out.print("Masukkan Nama: ");
            scanner.nextLine(); // Menggunakan nextLine() untuk membaca nama dengan dua kata
            String name =scanner.nextLine();
            System.out.print("Masukkan NIM: ");
            String nim = scanner.next();

            if (nim.length() != 15) {
                System.out.println("NIM tidak valid! Harus 15 karakter.");
                return;
            }
            scanner.nextLine(); // Membersihkan karakter newline
            System.out.print("Masukkan Fakultas: ");
            String faculty = scanner.nextLine();
            System.out.print("Masukkan Program Studi: ");
            String studyProgram = scanner.nextLine();
            studentList.add(new Student(nim, name, faculty, studyProgram));
            System.out.println("Mahasiswa dengan NIM " + nim + " berhasil ditambahkan.");
        }




        private void inputBook() {
            // Implementasi input buku
            System.out.println("Memasukkan buku...");
            System.out.println("Pilih jenis buku:");
            System.out.println("1. History Book");
            System.out.println("2. Story Book");
            System.out.println("3. Text Book");
            System.out.print("Pilih jenis buku (1-3): ");
            int bookType = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline

            String idBuku, judul, author,category = new String();
            int stok;
            System.out.print("Masukkan judul buku: ");
            judul = scanner.nextLine();
            System.out.print("Masukkan author buku: ");
            author = scanner.nextLine();
            if (bookType == 1){
                category = "History";
            } else if (bookType == 2){
                category = "Story";
            } else if (bookType == 3){
                category = "Text";
            }
            System.out.print("Masukkan stok buku: ");
            stok = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline

            switch (bookType) {
                case 1:
                    idBuku = generateId("HB");
                    daftarBuku.add(new HistoryBook(idBuku, judul, stok,category, author));
                    break;
                case 2:
                    idBuku = generateId("SB");
                    daftarBuku.add(new StoryBook(idBuku, judul, stok ,category, author));
                    break;
                case 3:
                    idBuku = generateId("TB");
                    daftarBuku.add(new TextBook(idBuku, judul, stok ,category, author));
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    return;
            }
            System.out.println("Buku berhasil ditambahkan.");
        }

        @Override
        public void displayBooks() {
            // Implementasi menampilkan daftar buku
            System.out.println("Daftar Buku Tersedia:");
            System.out.println("==============================================================================");
            System.out.println("||\tNo.\t||\tId Buku\t||\tNama Buku\t||\tAuthor\t||\tCategory\t||\tStock\t||");
            int index = 1;
            for (Book book : daftarBuku) {
                if (book != null) {
                    System.out.println("||\t" + index + "\t||\t" + book.idBuku + "\t||\t" + book.getJudul() + "\t\t\t||\t" + book.getAuthor() + "\t\t||\t" + book.getCategory() + "\t\t||\t" + book.getStok() + "\t\t||\t");
                    System.out.println("==============================================================================");
                    index ++;
                }
            }
        }

        private void displayStudents() {
            // Implementasi menampilkan daftar mahasiswa
            System.out.println("Daftar Mahasiswa yang terdaftar:");
            for (Student student : studentList) {
                System.out.println("\nNama: " + student.getName());
                System.out.println("NIM: " + student.getNim());
                System.out.println("Fakultas: " + student.getFaculty());
                System.out.println("Program Studi: " + student.getStudyProgram());
                if (!student.getBorrowedBooks().isEmpty()) {
                    System.out.println("  Meminjam Buku:");
                    for (Book book : student.getBorrowedBooks()) {
                        System.out.println("    - " + book.getJudul());
                    }
                }
            }
        }

        private String generateId(String prefix) {
            // Implementasi pembuatan ID unik
            // Contoh: HB001, SB002, TB003, dst.
            int nextId = daftarBuku.size() + 1;
            return prefix + String.format("%03d", nextId);
        }
    }

    public static class Book {
        private String idBuku;
        private String judul;
        private int stok;
        private String author;
        private String category;

        public Book(String idBuku, String judul, int stok, String category , String author) {
            this.idBuku = idBuku;
            this.judul = judul;
            this.stok = stok;
            this.category = category;
            this.author = author;
        }

        public String getIdBuku() {
            return idBuku;
        }

        public String getJudul() {
            return judul;
        }

        public int getStok() {
            return stok;
        }

        public String getCategory() {
            return category;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }

        public String getAuthor() {
            return author;
        }
    }
}

class HistoryBook extends Main.Book {
    public HistoryBook(String idBuku, String judul, int stok, String category , String author) {
        super(idBuku, judul, stok, category, author);
    }
}

class StoryBook extends Main.Book {
    public StoryBook(String idBuku, String judul, int stok, String category , String author) {
        super(idBuku, judul, stok, category, author);
    }
}

class TextBook extends Main.Book {
    public TextBook(String idBuku, String judul, int stok, String category , String author) {
        super(idBuku, judul, stok, category, author);
    }
}



abstract class User {
    private String nim;

    public User(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public abstract void displayBooks(Main.Book[] bookList);

    public abstract void displayBooks(ArrayList<Main.Book> bookList);
}