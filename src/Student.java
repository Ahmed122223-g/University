public class Student extends Person {
    private String major;
    private int year;
    private Course[] selectedCourses;
    private int selectedCoursesCount;
    public static final int MAX_COURSES = 7;

    public Student(String name, String id, String email, String major, int year) {
        super(name, id, email);
        this.major = major;
        this.year = year;
        this.selectedCourses = new Course[MAX_COURSES];
        this.selectedCoursesCount = 0;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Course[] getSelectedCourses() {
        return selectedCourses;
    }

    public int getSelectedCoursesCount() {
        return selectedCoursesCount;
    }

    @Override
    public void displayInfo() {
        System.out.println("--- Student Info ---");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Major: " + major);
        System.out.println("Year: " + year);
        System.out.println("Final GPA: " + calculateFinalGPA());
        viewCourses();
    }

    public void selectCourse(Course c) {
        if (selectedCoursesCount >= MAX_COURSES) {
            System.out.println("Cannot add more courses!");
            return;
        }
        selectedCourses[selectedCoursesCount] = c;
        selectedCoursesCount++;
        System.out.println(getName() + " enrolled in " + c.getCourseName());
    }

    public void viewCourses() {
        System.out.println("Selected Courses:");
        for (int i = 0; i < selectedCoursesCount; i++) {
            Course c = selectedCourses[i];
            Double grade = c.getGrade(this);
            String doc = c.getDoctor() != null ? "Dr. " + c.getDoctor().getName() : "Not Assigned";
            String gr = grade != null ? "" + grade : "Not Graded";
            System.out.println("- " + c.getCourseName() + " (Doctor: " + doc + ", Grade: " + gr + ")");
        }
    }

    public double calculateFinalGPA() {
        double totalGrade = 0;
        int totalCredits = 0;
        for (int i = 0; i < selectedCoursesCount; i++) {
            Double grade = selectedCourses[i].getGrade(this);
            if (grade != null) {
                totalGrade += grade * selectedCourses[i].getCredits();
                totalCredits += selectedCourses[i].getCredits();
            }
        }
        return totalCredits == 0 ? 0.0 : totalGrade / totalCredits;
    }

    public void showMenu(java.util.Scanner scanner, University uni) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Student Menu: " + getName() + " ---");
            System.out.println("1. Enroll in course");
            System.out.println("2. View courses and grades");
            System.out.println("3. View Final GPA");
            System.out.println("4. Back to main menu");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1)
                enrollInCourse(scanner, uni);
            else if (choice == 2)
                viewCourses();
            else if (choice == 3)
                System.out.println("\nFinal GPA: " + calculateFinalGPA());
            else if (choice == 4)
                inMenu = false;
            else
                System.out.println("Wrong choice!");
        }
    }

    private void enrollInCourse(java.util.Scanner scanner, University uni) {
        System.out.println("\n--- Available Courses ---");
        if (uni.getCourseCount() == 0) {
            System.out.println("No courses available!");
            return;
        }
        for (int i = 0; i < uni.getCourseCount(); i++) {
            Course c = uni.getCourses()[i];
            String doc = c.getDoctor() != null ? "Dr. " + c.getDoctor().getName() : "No Doctor";
            System.out.println((i + 1) + ". " + c.getCourseName() + " - " + c.getCourseCode() + " (" + c.getCredits()
                    + " cr) - " + doc);
        }
        System.out.print("Select course number: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        if (idx < 0 || idx >= uni.getCourseCount()) {
            System.out.println("Wrong number!");
            return;
        }
        selectCourse(uni.getCourses()[idx]);
    }
}
