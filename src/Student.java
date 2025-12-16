import java.util.ArrayList;

public class Student extends Person {

    private String major;
    private int year;
    private ArrayList<Course> selectedCourses;

    public static final int MAX_COURSES = 7;

    public Student(String name, String id, String email, String major, int year) {
        super(name, id, email);
        this.major = major;
        this.year = year;
        this.selectedCourses = new ArrayList<>();
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
        if (selectedCourses.size() >= MAX_COURSES) {
            System.out.println("Cannot add more courses!");
            return;
        }
        selectedCourses.add(c);
        System.out.println(getName() + " enrolled in " + c.getCourseName());
    }

    public void selectCourse(Course c, boolean notify) {
        if (selectedCourses.size() >= MAX_COURSES) {
            System.out.println("Cannot add more courses!");
            return;
        }
        selectedCourses.add(c);

        if (notify) {
            System.out.println("NOTIFICATION: " + getName() + " enrolled in " + c.getCourseName());
        } else {
            System.out.println(getName() + " enrolled in " + c.getCourseName());
        }
    }

    public void viewCourses() {
        System.out.println("Selected Courses:");

        for (Course c : selectedCourses) {
            Double grade = c.getGrade(this);
            String doctorName = "Not Assigned";

            if (c.getDoctor() != null) {
                doctorName = "Dr. " + c.getDoctor().getName();
            }

            String gradeText = "Not Graded";
            if (grade != null) {
                gradeText = "" + grade;
            }

            System.out.println("- " + c.getCourseName() + " (Doctor: " + doctorName + ", Grade: " + gradeText + ")");
        }
    }

    public double calculateGPA(Course c) {
        Double grade = c.getGrade(this);
        if (grade != null) {
            return grade;
        }
        return 0.0;
    }

    public double calculateFinalGPA() {
        double totalGrade = 0;
        int totalCredits = 0;

        for (Course c : selectedCourses) {
            Double grade = c.getGrade(this);
            if (grade != null) {
                totalGrade = totalGrade + (grade * c.getCredits());
                totalCredits = totalCredits + c.getCredits();
            }
        }

        if (totalCredits == 0) {
            return 0.0;
        }

        return totalGrade / totalCredits;
    }

    public void showMenu(java.util.Scanner scanner, University uni) {
        boolean inStudentMenu = true;
        while (inStudentMenu) {
            System.out.println("\n--- Student Menu: " + getName() + " ---");
            System.out.println("1. Enroll in course");
            System.out.println("2. View courses and grades");
            System.out.println("3. View Final GPA");
            System.out.println("4. Back to main menu");

            System.out.print("Choose: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                choice = 0;
            }

            if (choice == 1) {
                enrollInCourse(scanner, uni);
            } else if (choice == 2) {
                viewCourses();
            } else if (choice == 3) {
                System.out.println("\nFinal GPA: " + calculateFinalGPA());
            } else if (choice == 4) {
                inStudentMenu = false;
            } else {
                System.out.println("Wrong choice!");
            }
        }
    }

    private void enrollInCourse(java.util.Scanner scanner, University uni) {
        System.out.println("\n--- Available Courses ---");

        if (uni.getCourses().isEmpty()) {
            System.out.println("No courses available! Doctor must add courses first.");
            return;
        }

        for (int i = 0; i < uni.getCourses().size(); i++) {
            Course c = uni.getCourses().get(i);
            String doctorName = "No Doctor";
            if (c.getDoctor() != null) {
                doctorName = "Dr. " + c.getDoctor().getName();
            }
            System.out.println((i + 1) + ". " + c.getCourseName() + " - " + c.getCourseCode() + " (" + c.getCredits()
                    + " credits) - " + doctorName);
        }

        System.out.print("Select course number: ");
        int courseIndex = -1;
        try {
            courseIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (Exception e) {
        }

        if (courseIndex < 0 || courseIndex >= uni.getCourses().size()) {
            System.out.println("Wrong number!");
            return;
        }

        Course selectedCourse = uni.getCourses().get(courseIndex);

        System.out.print("Want notification? (1 = yes / 0 = no): ");
        int notify = 0;
        try {
            notify = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
        }

        if (notify == 1) {
            selectCourse(selectedCourse, true);
        } else {
            selectCourse(selectedCourse);
        }
    }
}
