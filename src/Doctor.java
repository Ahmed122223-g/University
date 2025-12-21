import java.util.Scanner;
import java.util.ArrayList;

public class Doctor extends Person {

    private String department;
    private String title;
    private ArrayList<Course> assignedCourses;

    public Doctor(String name, String id, String email, String department, String title) {
        super(name, id, email);
        this.department = department;
        this.title = title;
        this.assignedCourses = new ArrayList<Course>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public int getAssignedCoursesCount() {
        return assignedCourses.size();
    }

    public void displayInfo() {
        System.out.println("---------- Doctor Info ----------");
        System.out.println("Name: Dr. " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Department: " + department);
        System.out.println("Title: " + title);
        System.out.println("Courses: ");
        viewCourses();
        System.out.println("Grades: ");
        showGrades();
    }

    public void assignCourse(Course c) {
        assignedCourses.add(c);
        c.assignDoctor(this);
        System.out.println("Dr. " + getName() + " assigned to " + c.getCourseName());
    }

    public void addGrade(Student s, Course c, double grade) {
        boolean teachesCourse = false;
        for (int i = 0; i < assignedCourses.size(); i++) {
            if (assignedCourses.get(i) == c) {
                teachesCourse = true;
                break;
            }
        }
        if (!teachesCourse) {
            System.out.println("Dr. " + getName() + " does not teach " + c.getCourseName());
            return;
        }

        boolean studentEnrolled = false;
        for (int i = 0; i < s.getSelectedCoursesCount(); i++) {
            if (s.getSelectedCourses().get(i) == c) {
                studentEnrolled = true;
                break;
            }
        }
        if (!studentEnrolled) {
            System.out.println(s.getName() + " is not enrolled in " + c.getCourseName());
            return;
        }

        c.setGrade(s, grade);
        System.out.println("Grade " + grade + " given to " + s.getName() + " in " + c.getCourseName());
    }

    public void viewCourses() {
        System.out.println("Courses Teaching:");
        for (int i = 0; i < assignedCourses.size(); i++) {
            System.out.println("- " + assignedCourses.get(i).getCourseName());
        }
    }

    public void showGrades() {
        System.out.println("Grades Assigned:");
        for (int i = 0; i < assignedCourses.size(); i++) {
            Course c = assignedCourses.get(i);
            for (int j = 0; j < c.getGradesCount(); j++) {
                System.out.println("- " + c.getGradedStudents().get(j).getName() + ": " + c.getGradesList().get(j) + " in " + c.getCourseName());
            }
        }
    }

    public void Menu(Scanner scanner, University uni) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n---------- Doctor Menu: Dr. " + getName() + " ----------");
            System.out.println("1. Add new course");
            System.out.println("2. Assign grade to student");
            System.out.println("3. View courses and grades");
            System.out.println("4. Back to main menu");
            // استخدمنا string علشان لو دخلت حروف يقبلها عادي والتطبيق ميبظش
            String choice = HandelError(scanner, "Choose: ");
            if (choice.equals("1"))
                addCourse(scanner, uni);
            else if (choice.equals("2"))
                assignGrade(scanner, uni);
            else if (choice.equals("3"))
                displayInfo();
            else if (choice.equals("4"))
                inMenu = false;
            else
                System.out.println("Wrong choice!");
        }
    }

    private void addCourse(Scanner scanner, University uni) {
        System.out.println("\n---------- Add New Course ----------");
        String name = HandelError(scanner, "Enter course name: ");
        String code = HandelError(scanner, "Enter course code: ");
        int credits = Integer.parseInt(HandelError(scanner, "Enter credits: "));

        Course course = new Course(name, code, credits);
        uni.addCourse(course);
        assignCourse(course);
        System.out.println("Course added successfully!");
    }

    private void assignGrade(Scanner scanner, University uni) {
        if (assignedCourses.size() == 0) {
            System.out.println("No courses! Add a course first.");
            return;
        }

        System.out.println("\n---------- Select Course ----------");
        for (int i = 0; i < assignedCourses.size(); i++) {
            System.out.println((i + 1) + ". " + assignedCourses.get(i).getCourseName());
        }
        // استخدمنا String علشان لو دخل حروف ميبظش
        String courseChoice = HandelError(scanner, "Select course number: ");
        Course selectedCourse = null;
        for (int i = 0; i < assignedCourses.size(); i++) {
            if (courseChoice.equals(String.valueOf(i + 1))) {
                selectedCourse = assignedCourses.get(i);
                break;
            }
        }
        if (selectedCourse == null) {
            System.out.println("Wrong number!");
            return;
        }

        System.out.println("\n---------- Students in " + selectedCourse.getCourseName() + " ----------");
        ArrayList<Student> enrolled = new ArrayList<Student>();
        for (int i = 0; i < uni.getStudentCount(); i++) {
            Student s = uni.getStudents().get(i);
            for (int j = 0; j < s.getSelectedCoursesCount(); j++) {
                if (s.getSelectedCourses().get(j) == selectedCourse) {
                    enrolled.add(s);
                    System.out.println(enrolled.size() + ". " + s.getName());
                    break;
                }
            }
        }
        if (enrolled.size() == 0) {
            System.out.println("No students enrolled!");
            return;
        }

        String studentChoice = HandelError(scanner, "Select student number: ");
        Student selectedStudent = null;
        for (int i = 0; i < enrolled.size(); i++) {
            if (studentChoice.equals(String.valueOf(i + 1))) {
                selectedStudent = enrolled.get(i);
                break;
            }
        }
        if (selectedStudent == null) {
            System.out.println("Wrong number!");
            return;
        }

        double grade = Double.parseDouble(HandelError(scanner, "Enter grade (0 to 4): "));
        addGrade(selectedStudent, selectedCourse, grade);
    }
    // الداله دي وظيفتها انها بتشوف لو المستخدم دخل كلام فاضي هترفضه وتجبره يدخل بيانات صح
    private String HandelError(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Error: Input cannot be empty!");
        }
    }
}