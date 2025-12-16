import java.util.ArrayList;
import java.util.Map;

public class Doctor extends Person {

    private String department;
    private String title;
    private ArrayList<Course> assignedCourses;

    public Doctor(String name, String id, String email, String department, String title) {
        super(name, id, email);
        this.department = department;
        this.title = title;
        this.assignedCourses = new ArrayList<>();
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

    @Override
    public void displayInfo() {
        System.out.println("--- Doctor Info ---");
        System.out.println("Name: Dr. " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Department: " + department);
        System.out.println("Title: " + title);

        viewCourses();
        showGrades();
    }

    public void assignCourse(Course c) {
        assignedCourses.add(c);
        c.assignDoctor(this);
        System.out.println("Dr. " + getName() + " assigned to " + c.getCourseName());
    }

    public void assignCourse(Course c, int semester) {
        assignedCourses.add(c);
        c.assignDoctor(this);
        System.out.println("Dr. " + getName() + " assigned to " + c.getCourseName() + " (Semester " + semester + ")");
    }

    public void addGrade(Student s, Course c, double grade) {
        if (!assignedCourses.contains(c)) {
            System.out.println("Dr. " + getName() + " does not teach " + c.getCourseName());
            return;
        }

        if (!s.getSelectedCourses().contains(c)) {
            System.out.println(s.getName() + " is not enrolled in " + c.getCourseName());
            return;
        }

        c.setGrade(s, grade);
        System.out.println("Grade " + grade + " given to " + s.getName() + " in " + c.getCourseName());
    }

    public void viewCourses() {
        System.out.println("Courses Teaching:");
        for (Course c : assignedCourses) {
            System.out.println("- " + c.getCourseName());
        }
    }

    public void showGrades() {
        System.out.println("Grades Assigned:");

        for (Course c : assignedCourses) {
            Map<Student, Double> grades = c.getGrades();

            for (Map.Entry<Student, Double> entry : grades.entrySet()) {
                Student student = entry.getKey();
                Double grade = entry.getValue();
                System.out.println("- " + student.getName() + ": " + grade + " in " + c.getCourseName());
            }
        }
    }

    public void showMenu(java.util.Scanner scanner, University uni) {
        boolean inDoctorMenu = true;
        while (inDoctorMenu) {
            System.out.println("\n--- Doctor Menu: Dr. " + getName() + " ---");
            System.out.println("1. Add new course");
            System.out.println("2. Assign grade to student");
            System.out.println("3. View courses and grades");
            System.out.println("4. Back to main menu");

            System.out.print("Choose: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                choice = 0;
            }

            if (choice == 1) {
                addNewCourse(scanner, uni);
            } else if (choice == 2) {
                assignGrade(scanner, uni);
            } else if (choice == 3) {
                displayInfo();
            } else if (choice == 4) {
                inDoctorMenu = false;
            } else {
                System.out.println("Wrong choice!");
            }
        }
    }

    private void addNewCourse(java.util.Scanner scanner, University uni) {
        System.out.println("\n--- Add New Course ---");

        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Enter credits: ");
        int credits = 0;
        try {
            credits = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
        }

        Course course = new Course(courseName, courseCode, credits);
        uni.addCourse(course);
        this.assignCourse(course);

        System.out.println("Course added successfully!");
    }

    private void assignGrade(java.util.Scanner scanner, University uni) {
        if (getAssignedCourses().size() == 0) {
            System.out.println("No courses! Add a course first.");
            return;
        }

        System.out.println("\n--- Select Course ---");
        for (int i = 0; i < getAssignedCourses().size(); i++) {
            Course c = getAssignedCourses().get(i);
            System.out.println((i + 1) + ". " + c.getCourseName());
        }

        System.out.print("Select course number: ");
        int courseIndex = -1;
        try {
            courseIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (Exception e) {
        }

        if (courseIndex < 0 || courseIndex >= getAssignedCourses().size()) {
            System.out.println("Wrong number!");
            return;
        }

        Course selectedCourse = getAssignedCourses().get(courseIndex);

        System.out.println("\n--- Students enrolled in " + selectedCourse.getCourseName() + " ---");

        java.util.ArrayList<Student> enrolledStudents = new java.util.ArrayList<>();
        for (Student s : uni.getStudents()) {
            if (s.getSelectedCourses().contains(selectedCourse)) {
                enrolledStudents.add(s);
                System.out.println((enrolledStudents.size()) + ". " + s.getName());
            }
        }

        if (enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in this course!");
            return;
        }

        System.out.print("Select student number: ");
        int studentIndex = -1;
        try {
            studentIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (Exception e) {
        }

        if (studentIndex < 0 || studentIndex >= enrolledStudents.size()) {
            System.out.println("Wrong number!");
            return;
        }

        Student selectedStudent = enrolledStudents.get(studentIndex);

        System.out.print("Enter grade (0 to 4): ");
        double grade = 0;
        try {
            grade = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
        }

        addGrade(selectedStudent, selectedCourse, grade);
    }
}
