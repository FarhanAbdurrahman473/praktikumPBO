import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("Choose user type:");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Admin admin = new Admin();
                    admin.adminMenu();
                    break;
                case 2:
                    Student student = new Student();
                    student.studentMenu();
                    break;
                case 3:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 3);

        scanner.close();
    }


}

class Book {
    private final String id;
    private final String title;
    private final String author;
    private int stock;

    public Book(String id, String title, String author, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Student {
    private final Scanner scanner = new Scanner(System.in);

    private String Name;
    private String Faculty;
    private String Program;
    private String Id;

    private final ArrayList<Book> bookList = new ArrayList<>();


    public void showBookList() {
        System.out.println("Book List:");
        for (Book book : bookList) {
            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Stock: " + book.getStock());
            System.out.println();
        }
    }

    public void borrowBook() {
        System.out.println("Enter the ID of the book you want to borrow: ");
        String bookId = scanner.nextLine();

        Book borrowedBook = null;
        for (Book book : bookList) {
            if (book.getId().equals(bookId)) {
                borrowedBook = book;
                break;
            }
        }

        if (borrowedBook != null) {
            if (borrowedBook.getStock() > 0) {
                borrowedBook.setStock(borrowedBook.getStock() - 1);
                System.out.println("Book successfully borrowed.");
            } else {
                System.out.println("Sorry, the book is out of stock.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void logout() {
        System.out.println("Logout successful.");
    }

    public void studentMenu() {
        int choice;
        do {
            System.out.println("Student Menu:");
            System.out.println("1. View Book List");
            System.out.println("2. Borrow Book");
            System.out.println("3. Logout");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showBookList();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 3);
    }

    public String getName() {
        return Name;
    }
    public String getId() {
        return Id;
    }
    public String getFaculty() {
        return Faculty;
    }
    public String getProgram() {
        return Program;
    }

    public void setName(String name) {
        Name = name;
    }
    public void setId(String id) {
        Id = id;
    }
    public void setFaculty(String faculty) {
        Faculty = faculty;
    }
    public void setProgram(String program) {
        Program = program;
    }
}

class Admin {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Student> studentList = new ArrayList<>();


    public void adminMenu() {
        int choice;
        do {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. View Student List");
            System.out.println("3. Logout");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudentList();
                    break;
                case 3:
                    System.out.println("Logout successful.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 3);
    }

    private void addStudent() {
        System.out.println("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Student ID (must be 15 characters): ");
        String id = scanner.nextLine();
        checkNim(id);
        System.out.println("Enter Student Faculty: ");
        String faculty = scanner.nextLine();
        System.out.println("Enter Student Program: ");
        String program = scanner.nextLine();

        if (id.length() == 15) {
            Student student = new Student();
            student.setName(name);
            student.setId(id);
            student.setFaculty(faculty);
            student.setProgram(program);
            studentList.add(student);

            System.out.println("Student added successfully.");
        } else {
            System.out.println("Failed to add student. ID length must be 15 characters.");
        }
    }


    private Object checkNim(String id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                System.out.println("Your id had been added yet");
                adminMenu();
                return student;
            }
        }
        return null;
    }

    private void viewStudentList() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Student List:");
            for (Student student : studentList) {
                System.out.println("Name: " + student.getName());
                System.out.println("ID: " + student.getId());
                System.out.println("Faculty: " + student.getFaculty());
                System.out.println("Program: " + student.getProgram());
                System.out.println();
            }
        }
    }
}

