import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library {
    private static final Map<Long, String> students = new HashMap<>();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    static {
        students.put(202310370311194L, "Student 1");
        students.put(202310370311172L, "Student 2");
        students.put(202310370311203L, "Student 3");
    }

    void loginAsStudent(long nim) {
        String studentName = students.getOrDefault(nim, null);
        if (studentName != null) {
            System.out.println("Successful login as Student: " + studentName);
        } else {
            System.out.println("User not found.");
        }
    }

    void loginAsAdmin(String username, String password) {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("Successful login as Admin.");
        } else {
            System.out.println("User not found.");
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== Library System ======");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");
        System.out.print("Choose option (1-3): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter your NIM: ");
                long nim = scanner.nextLong();
                library.loginAsStudent(nim);
                break;
            case 2:
                System.out.print("Enter your username: ");
                String username = scanner.next();
                System.out.print("Enter your password: ");
                String password = scanner.next();
                library.loginAsAdmin(username, password);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }
}