public class Doctor extends Person {

    private String department;
    private String title;
    private Course[] assignedCourses;
    private int assignedCoursesCount;
    private static final int MAX_COURSES = 10;

    public Doctor(String name, String id, String email, String department, String title) {
        super(name, id, email);
        this.department = department;
        this.title = title;
        this.assignedCourses = new Course[MAX_COURSES];
        this.assignedCoursesCount = 0;
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

    public Course[] getAssignedCourses() {
        return assignedCourses;
    }

    public int getAssignedCoursesCount() {
        return assignedCoursesCount;
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
        if (assignedCoursesCount < MAX_COURSES) {
            assignedCourses[assignedCoursesCount] = c;
            assignedCoursesCount++;
            c.assignDoctor(this);
            System.out.println("Dr. " + getName() + " assigned to " + c.getCourseName());
        } else {
            System.out.println("Cannot assign more courses.");
        }
    }

    public void assignCourse(Course c, int semester) {
        if (assignedCoursesCount < MAX_COURSES) {
            assignedCourses[assignedCoursesCount] = c;
            assignedCoursesCount++;
            c.assignDoctor(this);
            System.out
                    .println("Dr. " + getName() + " assigned to " + c.getCourseName() + " (Semester " + semester + ")");
        } else {
            System.out.println("Cannot assign more courses.");
        }
    }

    public void addGrade(Student s, Course c, double grade) {
        boolean teachesCourse = false;
        for (int i = 0; i < assignedCoursesCount; i++) {
            if (assignedCourses[i] == c) {
                teachesCourse = true;
                break;
            }
        }

        if (!teachesCourse) {
            System.out.println("Dr. " + getName() + " does not teach " + c.getCourseName());
            return;
        }

        boolean studentEnrolled = false;
        Course[] studentCourses = s.getSelectedCourses();
        for (int i = 0; i < s.getSelectedCoursesCount(); i++) {
            if (studentCourses[i] == c) {
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
        for (int i = 0; i < assignedCoursesCount; i++) {
            System.out.println("- " + assignedCourses[i].getCourseName());
        }
    }

    public void showGrades() {
        System.out.println("Grades Assigned:");

        for (int i = 0; i < assignedCoursesCount; i++) {
            Course c = assignedCourses[i];
            Student[] students = c.getGradedStudents();
            double[] grades = c.getGradesList();
            int count = c.getGradesCount();

            for (int j = 0; j < count; j++) {
                System.out.println("- " + students[j].getName() + ": " + grades[j] + " in " + c.getCourseName());
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
            int choice = Integer.parseInt(scanner.nextLine());

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
        int credits = Integer.parseInt(scanner.nextLine());

        Course course = new Course(courseName, courseCode, credits);
        uni.addCourse(course);
        this.assignCourse(course);

        System.out.println("Course added successfully!");
    }

    private void assignGrade(java.util.Scanner scanner, University uni) {
        if (assignedCoursesCount == 0) {
            System.out.println("No courses! Add a course first.");
            return;
        }

        System.out.println("\n--- Select Course ---");
        for (int i = 0; i < assignedCoursesCount; i++) {
            System.out.println((i + 1) + ". " + assignedCourses[i].getCourseName());
        }

        System.out.print("Select course number: ");
        int courseIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (courseIndex < 0 || courseIndex >= assignedCoursesCount) {
            System.out.println("Wrong number!");
            return;
        }

        Course selectedCourse = assignedCourses[courseIndex];

        System.out.println("\n--- Students enrolled in " + selectedCourse.getCourseName() + " ---");

        Student[] enrolledStudents = new Student[100];
        int enrolledCount = 0;

        Student[] uniStudents = uni.getStudents();
        int uniStudentCount = uni.getStudentCount();

        for (int i = 0; i < uniStudentCount; i++) {
            Student s = uniStudents[i];
            boolean isEnrolled = false;
            Course[] sCourses = s.getSelectedCourses();
            for (int k = 0; k < s.getSelectedCoursesCount(); k++) {
                if (sCourses[k] == selectedCourse) {
                    isEnrolled = true;
                    break;
                }
            }

            if (isEnrolled) {
                enrolledStudents[enrolledCount] = s;
                System.out.println((enrolledCount + 1) + ". " + s.getName());
                enrolledCount++;
            }
        }

        if (enrolledCount == 0) {
            System.out.println("No students enrolled in this course!");
            return;
        }

        System.out.print("Select student number: ");
        int studentIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (studentIndex < 0 || studentIndex >= enrolledCount) {
            System.out.println("Wrong number!");
            return;
        }

        Student selectedStudent = enrolledStudents[studentIndex];

        System.out.print("Enter grade (0 to 4): ");
        double grade = Double.parseDouble(scanner.nextLine());

        addGrade(selectedStudent, selectedCourse, grade);
    }
}
