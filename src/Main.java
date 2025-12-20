import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static University uni = new University();

    public static void main(String[] args) {
        System.out.println("            Beni Suef National University");

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1)
                registerDoctor();
            else if (choice == 2)
                registerStudent();
            else if (choice == 3)
                doctorMenu();
            else if (choice == 4)
                studentMenu();
            else if (choice == 5)
                uni.displayAllStudents();
            else if (choice == 6)
                uni.displayAllDoctors();
            else if (choice == 7)
                uni.displayAllCourses();
            else if (choice == 8) {
                System.out.println("\nGoodbye!");
                running = false;
            } else
                System.out.println("Wrong choice!");
            System.out.println();
        }
        scanner.close();
    }

    static void showMenu() {
        System.out.println("\n----- Main Menu -----");
        System.out.println("1. Register Doctor");
        System.out.println("2. Register Student");
        System.out.println("3. Doctor Login");
        System.out.println("4. Student Login");
        System.out.println("5. Show Students");
        System.out.println("6. Show Doctors");
        System.out.println("7. Show Courses");
        System.out.println("8. Exit");
    }

    static void registerDoctor() {
        System.out.println("\n--- Register Doctor ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Department: ");
        String dept = scanner.nextLine();
        uni.addDoctor(new Doctor(name, id, email, dept));
    }

    static void registerStudent() {
        System.out.println("\n--- Register Student ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Major: ");
        String major = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        uni.addStudent(new Student(name, id, email, major, year));
    }

    static void doctorMenu() {
        if (uni.getDoctorCount() == 0) {
            System.out.println("No doctors!");
            return;
        }
        System.out.println("\n--- Select Doctor ---");
        for (int i = 0; i < uni.getDoctorCount(); i++)
            System.out.println((i + 1) + ". Dr. " + uni.getDoctors()[i].getName());
        System.out.print("Select: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        if (idx >= 0 && idx < uni.getDoctorCount())
            uni.getDoctors()[idx].showMenu(scanner, uni);
    }

    static void studentMenu() {
        if (uni.getStudentCount() == 0) {
            System.out.println("No students!");
            return;
        }
        System.out.println("\n--- Select Student ---");
        for (int i = 0; i < uni.getStudentCount(); i++)
            System.out.println((i + 1) + ". " + uni.getStudents()[i].getName());
        System.out.print("Select: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        if (idx >= 0 && idx < uni.getStudentCount())
            uni.getStudents()[idx].showMenu(scanner, uni);
    }
}
