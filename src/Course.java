public class Course {

    private String courseName;
    private String courseCode;
    private int credits;
    private Doctor doctor;

    private Student[] gradedStudents;
    private double[] gradesList;
    private int gradesCount;

    public static final double MAX_GRADE = 4.0;
    private static final int MAX_STUDENTS = 100;

    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.doctor = null;
        this.gradedStudents = new Student[MAX_STUDENTS];
        this.gradesList = new double[MAX_STUDENTS];
        this.gradesCount = 0;
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

    public Student[] getGradedStudents() {
        return gradedStudents;
    }

    public double[] getGradesList() {
        return gradesList;
    }

    public int getGradesCount() {
        return gradesCount;
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
        for (int i = 0; i < gradesCount; i++) {
            if (gradedStudents[i] == s) {
                gradesList[i] = grade;
                return;
            }
        }

        if (gradesCount < MAX_STUDENTS) {
            gradedStudents[gradesCount] = s;
            gradesList[gradesCount] = grade;
            gradesCount++;
        } else {
            System.out.println("Course is full, cannot assign grade.");
        }
    }

    public Double getGrade(Student s) {
        for (int i = 0; i < gradesCount; i++) {
            if (gradedStudents[i] == s) {
                return gradesList[i];
            }
        }
        return null;
    }
}
