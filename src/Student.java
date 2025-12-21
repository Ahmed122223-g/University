import java.util.Scanner;
import java.util.ArrayList;

public class Student extends Person {
    private String major;
    private int year;
    private ArrayList<Course> selectedCourses;

    public Student(String name, String id, String email, String major, int year) {
        super(name, id, email);
        this.major = major;
        this.year = year;
        this.selectedCourses = new ArrayList<Course>();
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

    public ArrayList<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public int getSelectedCoursesCount() {
        return selectedCourses.size();
    }

    public void displayInfo() {
        System.out.println("------------- Student Info -------------");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Major: " + major);
        System.out.println("Year: " + year);
        System.out.println("GPA: " + calculateFinalGPA());
        System.out.println("Courses: ");
        viewCourses();
    }

    public void selectCourse(Course c) {
        if (c.getDoctor() == null) {
            System.out.println("Course not assigned to a doctor");
            return;
        }

        if (c.getGrade(this) != null) {
            System.out.println("Cannot enroll in a course that you have already graded");
            return;
        }
        selectedCourses.add(c);
        System.out.println(getName() + " enrolled in " + c.getCourseName());
    }

    public void viewCourses() {
        System.out.println("------------- Selected Courses -------------");
        for (int i = 0; i < selectedCourses.size(); i++) {
            Course c = selectedCourses.get(i);
            Double grade = c.getGrade(this);
            String doc;
            if (c.getDoctor() != null) {
                doc = "Dr. " + c.getDoctor().getName();
            } else {
                doc = "Not Assigned";
            }

            String gr;
            if (grade != null) {
                gr = String.valueOf(grade);
            } else {
                gr = "Not Graded";
            }
            System.out.println(c.getCourseName() + " (Doctor: " + doc + ", Grade: " + gr + ")");
        }
    }

    public double calculateFinalGPA() {
        double totalGrade = 0;
        int totalCredits = 0;
        double gpa;
        for (int i = 0; i < selectedCourses.size(); i++) {
            Double grade = selectedCourses.get(i).getGrade(this);
            if (grade != null) {
                totalGrade += grade * selectedCourses.get(i).getCredits();
                totalCredits += selectedCourses.get(i).getCredits();
            }
        }
        if (totalCredits == 0) {
            return 0.0;
        }
        gpa = totalGrade / totalCredits;
        return gpa;
    }

    public void showMenu(Scanner scanner, University uni) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n------------- Student Menu: " + getName() + " -------------");
            System.out.println("1. Enroll in course");
            System.out.println("2. View courses and grades");
            System.out.println("3. View GPA");
            System.out.println("4. Back to main menu");
            // استخدمنا string علشان لو دخلت حروف يقبلها عادي والتطبيق ميبظش
            String choice = HandelError(scanner, "Choose: ");
            if (choice.equals("1"))
                enrollInCourse(scanner, uni);
            else if (choice.equals("2"))
                viewCourses();
            else if (choice.equals("3"))
                System.out.println("GPA: " + calculateFinalGPA());
            else if (choice.equals("4"))
                inMenu = false;
            else
                System.out.println("Wrong choice");
        }
    }

    private void enrollInCourse(Scanner scanner, University uni) {
        System.out.println("\n------------- Available Courses -------------");
        if (uni.getCourseCount() == 0) {
            System.out.println("No courses available");
            return;
        }
        for (int i = 0; i < uni.getCourseCount(); i++) {
            Course c = uni.getCourses().get(i);
            String doc;
            if (c.getDoctor() != null) {
                doc = "Dr. " + c.getDoctor().getName();
            } else {
                doc = "No Doctor";
            }
            System.out.println((i + 1) + ". " + c.getCourseName() + " - " + c.getCourseCode() + " (" + c.getCredits()
                    + " Credits) - " + doc);
        }
        // استخدمنا String علشان لو دخل حروف ميبظش
        String choice = HandelError(scanner, "Select course number: ");
        for (int i = 0; i < uni.getCourseCount(); i++) {
            if (choice.equals(String.valueOf(i + 1))) {
                selectCourse(uni.getCourses().get(i));
                return;
            }
        }
        System.out.println("Wrong number");
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
                