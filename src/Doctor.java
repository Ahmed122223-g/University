public class Doctor extends Person {

    private String department;
    private Course[] assignedCourses;
    private int assignedCoursesCount;
    private static final int MAX_COURSES = 10;

    public Doctor(String name, String id, String email, String department) {
        super(name, id, email);
        this.department = department;
        this.assignedCourses = new Course[MAX_COURSES];
        this.assignedCoursesCount = 0;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Course[] getAssignedCourses() {
        return assignedCourses;
    }

    public int getAssignedCoursesCount() {
        return assignedCoursesCount;
    }

    public void displayInfo() {
        System.out.println("---------- Doctor Info ----------");
        System.out.println("Name: Dr. " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Department: " + department);
        System.out.println("Courses: "); viewCourses();
        System.out.println("Grades: "); showGrades();
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
        for (int i = 0; i < s.getSelectedCoursesCount(); i++) {
            if (s.getSelectedCourses()[i] == c) {
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
            for (int j = 0; j < c.getGradesCount(); j++) {
                System.out.println("- " + c.getGradedStudents()[j].getName() + ": " + c.getGradesList()[j] + " in "
                        + c.getCourseName());
            }
        }
    }

    public void showMenu(java.util.Scanner scanner, University uni) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n---------- Doctor Menu: Dr. " + getName() + " ----------");
            System.out.println("1. Add new course");
            System.out.println("2. Assign grade to student");
            System.out.println("3. View courses and grades");
            System.out.println("4. Back to main menu");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1)
                addNewCourse(scanner, uni);
            else if (choice == 2)
                assignGrade(scanner, uni);
            else if (choice == 3)
                displayInfo();
            else if (choice == 4)
                inMenu = false;
            else
                System.out.println("Wrong choice!");
        }
    }

    private void addNewCourse(java.util.Scanner scanner, University uni) {
        System.out.println("\n---------- Add New Course ----------");
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = Integer.parseInt(scanner.nextLine());

        Course course = new Course(name, code, credits);
        uni.addCourse(course);
        assignCourse(course);
        System.out.println("Course added successfully!");
    }

    private void assignGrade(java.util.Scanner scanner, University uni) {
        if (assignedCoursesCount == 0) {
            System.out.println("No courses! Add a course first.");
            return;
        }

        System.out.println("\n---------- Select Course ----------");
        for (int i = 0; i < assignedCoursesCount; i++) {
            System.out.println((i + 1) + ". " + assignedCourses[i].getCourseName());
        }
        System.out.print("Select course number: ");
        int courseIdx = Integer.parseInt(scanner.nextLine()) - 1;
        if (courseIdx < 0 || courseIdx >= assignedCoursesCount) {
            System.out.println("Wrong number!");
            return;
        }
        Course selectedCourse = assignedCourses[courseIdx];

        System.out.println("\n---------- Students in " + selectedCourse.getCourseName() + " ----------");
        Student[] enrolled = new Student[100];
        int count = 0;
        for (int i = 0; i < uni.getStudentCount(); i++) {
            Student s = uni.getStudents()[i];
            for (int j = 0; j < s.getSelectedCoursesCount(); j++) {
                if (s.getSelectedCourses()[j] == selectedCourse) {
                    enrolled[count] = s;
                    System.out.println((count + 1) + ". " + s.getName());
                    count++;
                    break;
                }
            }
        }
        if (count == 0) {
            System.out.println("No students enrolled!");
            return;
        }

        System.out.print("Select student number: ");
        int studentIdx = Integer.parseInt(scanner.nextLine()) - 1;
        if (studentIdx < 0 || studentIdx >= count) {
            System.out.println("Wrong number!");
            return;
        }

        System.out.print("Enter grade (0 to 4): ");
        double grade = Double.parseDouble(scanner.nextLine());
        addGrade(enrolled[studentIdx], selectedCourse, grade);
    }
}
