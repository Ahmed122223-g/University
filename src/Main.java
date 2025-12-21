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
            // استخدمنا هنا string علشان لو دخلت حروف يقبلها عادي والتطبيق ميبظش
            String choice = scanner.nextLine();

            if (choice.equals("1"))
                registerDoctor();
            else if (choice.equals("2"))
                registerStudent();
            else if (choice.equals("3"))
                doctorMenu();
            else if (choice.equals("4"))
                studentMenu();
            else if (choice.equals("5"))
                uni.displayAllStudents();
            else if (choice.equals("6"))
                uni.displayAllDoctors();
            else if (choice.equals("7"))
                uni.displayAllCourses();
            else if (choice.equals("8")) {
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
        String name = HandelError("Name: ");
        String id = HandelError("ID: ");
        String email = HandelError("Email: ");
        String dept = HandelError("Department: ");
        String title = HandelError("Title: ");
        uni.addDoctor(new Doctor(name, id, email, dept, title));
    }

    static void registerStudent() {
        System.out.println("\n--- Register Student ---");
        String name = HandelError("Name: ");
        String id = HandelError("ID: ");
        String email = HandelError("Email: ");
        String major = HandelError("Major: ");
        int year = Integer.parseInt(HandelError("Year: "));
        uni.addStudent(new Student(name, id, email, major, year));
    }

    static void doctorMenu() {
        if (uni.getDoctorCount() == 0) {
            System.out.println("No doctors!");
            return;
        }
        System.out.println("\n--- Select Doctor ---");
        for (int i = 0; i < uni.getDoctorCount(); i++)
            System.out.println((i + 1) + ". Dr. " + uni.getDoctors().get(i).getName());
        // استخدمنا String بدل int علشان لو دخل حروف ميبظش
        String choice = HandelError("Select: ");
        // نقارن الاختيار بالارقام
        for (int i = 0; i < uni.getDoctorCount(); i++) {
            if (choice.equals(String.valueOf(i + 1))) {
                uni.getDoctors().get(i).showMenu(scanner, uni);
                return;
            }
        }
        System.out.println("Wrong choice!");
    }

    static void studentMenu() {
        if (uni.getStudentCount() == 0) {
            System.out.println("No students!");
            return;
        }
        System.out.println("\n--- Select Student ---");
        for (int i = 0; i < uni.getStudentCount(); i++)
            System.out.println((i + 1) + ". " + uni.getStudents().get(i).getName());
        // استخدمنا String بدل int علشان لو دخل حروف ميبظش
        String choice = HandelError("Select: ");
        // نقارن الاختيار بالارقام
        for (int i = 0; i < uni.getStudentCount(); i++) {
            if (choice.equals(String.valueOf(i + 1))) {
                uni.getStudents().get(i).showMenu(scanner, uni);
                return;
            }
        }
        System.out.println("Wrong choice!");
    }

    // الداله دي وظيفتها في الحياه انها بتشوف لو المستخدم دخل كلام فاضي هترفضه وتجبره يدخل
    static String HandelError(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty())
                return input;
            System.out.println("Error: Input cannot be empty!");
        }
    }
}