import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static University uni = new University();

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("   University Management System   ");
        System.out.println("=================================\n");

        boolean running = true;
        while (running) {
            showMainMenu();

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                registerDoctor();
            } else if (choice == 2) {
                registerStudent();
            } else if (choice == 3) {
                doctorMenu();
            } else if (choice == 4) {
                studentMenu();
            } else if (choice == 5) {
                uni.displayAllStudents();
            } else if (choice == 6) {
                uni.displayAllDoctors();
            } else if (choice == 7) {
                uni.displayAllCourses();
            } else if (choice == 8) {
                System.out.println("\nThank you for using the system!");
                running = false;
            } else {
                System.out.println("Wrong choice! Try again.");
            }

            System.out.println();
        }

        scanner.close();
    }

    public static void showMainMenu() {
        System.out.println("\n----- Main Menu -----");
        System.out.println("1. Register new Doctor");
        System.out.println("2. Register new Student");
        System.out.println("3. Login as Doctor (add course / assign grades)");
        System.out.println("4. Login as Student (select courses / view GPA)");
        System.out.println("5. Display all Students");
        System.out.println("6. Display all Doctors");
        System.out.println("7. Display all Courses");
        System.out.println("8. Exit");
        System.out.println("---------------------");
    }

    public static void registerDoctor() {
        System.out.println("\n--- Register New Doctor ---");

        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();

        System.out.print("Enter doctor ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter doctor email: ");
        String email = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter title (Professor/Lecturer): ");
        String title = scanner.nextLine();

        Doctor doctor = new Doctor(name, id, email, department, title);
        uni.addDoctor(doctor);

        System.out.println("Doctor registered successfully!");
    }

    public static void registerStudent() {
        System.out.println("\n--- Register New Student ---");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        System.out.print("Enter major: ");
        String major = scanner.nextLine();

        System.out.print("Enter year: ");
        int year = Integer.parseInt(scanner.nextLine());

        Student student = new Student(name, id, email, major, year);
        uni.addStudent(student);

        System.out.println("Student registered successfully!");
    }

    public static void doctorMenu() {
        System.out.println("\n--- Select Doctor ---");

        if (uni.getDoctorCount() == 0) {
            System.out.println("No doctors registered! Register a doctor first.");
            return;
        }

        for (int i = 0; i < uni.getDoctorCount(); i++) {
            Doctor d = uni.getDoctors()[i];
            System.out.println((i + 1) + ". Dr. " + d.getName());
        }

        System.out.print("Select doctor number: ");
        int doctorIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (doctorIndex < 0 || doctorIndex >= uni.getDoctorCount()) {
            System.out.println("Wrong number!");
            return;
        }

        Doctor currentDoctor = uni.getDoctors()[doctorIndex];

        currentDoctor.showMenu(scanner, uni);
    }

    public static void studentMenu() {
        System.out.println("\n--- Select Student ---");

        if (uni.getStudentCount() == 0) {
            System.out.println("No students registered! Register a student first.");
            return;
        }

        for (int i = 0; i < uni.getStudentCount(); i++) {
            Student s = uni.getStudents()[i];
            System.out.println((i + 1) + ". " + s.getName());
        }

        System.out.print("Select student number: ");
        int studentIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (studentIndex < 0 || studentIndex >= uni.getStudentCount()) {
            System.out.println("Wrong number!");
            return;
        }

        Student currentStudent = uni.getStudents()[studentIndex];

        currentStudent.showMenu(scanner, uni);
    }
}
