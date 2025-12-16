import java.util.HashMap;
import java.util.Map;

public class Course {

    private String courseName;
    private String courseCode;
    private int credits;
    private Doctor doctor;

    private Map<Student, Double> grades;

    public static final double MAX_GRADE = 4.0;

    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.doctor = null;
        this.grades = new HashMap<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Map<Student, Double> getGrades() {
        return grades;
    }

    public void displayCourseInfo() {
        System.out.println("Course: " + courseName);
        System.out.println("Code: " + courseCode);
        System.out.println("Credits: " + credits);

        if (doctor != null) {
            System.out.println("Doctor: " + doctor.getName());
        } else {
            System.out.println("Doctor: Not Assigned");
        }
    }

    public void assignDoctor(Doctor d) {
        this.doctor = d;
    }

    public void setGrade(Student s, double grade) {
        grades.put(s, grade);
    }

    public Double getGrade(Student s) {
        return grades.get(s);
    }
}
