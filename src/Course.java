import java.util.ArrayList;

public class Course {
    private String courseName;
    private String courseCode;
    private int credits;
    private Doctor doctor;
    private ArrayList<Student> gradedStudents;
    private ArrayList<Double> gradesList;

    public static final double MAX_GRADE = 4.0;

    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.doctor = null;
        this.gradedStudents = new ArrayList<Student>();
        this.gradesList = new ArrayList<Double>();
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

    public ArrayList<Student> getGradedStudents() {
        return gradedStudents;
    }

    public ArrayList<Double> getGradesList() {
        return gradesList;
    }

    public int getGradesCount() {
        return gradedStudents.size();
    }

    public void assignDoctor(Doctor d) {
        this.doctor = d;
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

    public void setGrade(Student s, double grade) {
        // لو الطالب موجود نحدث درجته
        for (int i = 0; i < gradedStudents.size(); i++) {
            if (gradedStudents.get(i) == s) {
                gradesList.set(i, grade);
                return;
            }
        }
        // لو مش موجود نضيفه
        gradedStudents.add(s);
        gradesList.add(grade);
    }

    public Double getGrade(Student s) {
        for (int i = 0; i < gradedStudents.size(); i++) {
            if (gradedStudents.get(i) == s)
                return gradesList.get(i);
        }
        return null;
    }
}
