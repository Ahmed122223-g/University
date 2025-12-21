import java.util.ArrayList;

public class University {
    private ArrayList<Student> students;
    private ArrayList<Doctor> doctors;
    private ArrayList<Course> courses;

    public University() {
        students = new ArrayList<Student>();
        doctors = new ArrayList<Doctor>();
        courses = new ArrayList<Course>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int getStudentCount() {
        return students.size();
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public int getDoctorCount() {
        return doctors.size();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public int getCourseCount() {
        return courses.size();
    }

    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Added student: " + s.getName());
    }

    public void addDoctor(Doctor d) {
        doctors.add(d);
        System.out.println("Added doctor: Dr. " + d.getName());
    }

    public void addCourse(Course c) {
        courses.add(c);
        System.out.println("Added course: " + c.getCourseName());
    }

    public void displayAllStudents() {
        System.out.println("\n========== ALL STUDENTS ==========\n");
        if (students.size() == 0)
            System.out.println("No students enrolled yet.");
        else
            for (int i = 0; i < students.size(); i++) {
                students.get(i).displayInfo();
                System.out.println();
            }
    }

    public void displayAllDoctors() {
        System.out.println("\n========== ALL DOCTORS ==========");
        if (doctors.size() == 0)
            System.out.println("No doctors registered yet.");
        else
            for (int i = 0; i < doctors.size(); i++) {
                doctors.get(i).displayInfo();
                System.out.println();
            }
    }

    public void displayAllCourses() {
        System.out.println("\n========== ALL COURSES ==========");
        if (courses.size() == 0)
            System.out.println("No courses added yet.");
        else
            for (int i = 0; i < courses.size(); i++) {
                courses.get(i).displayCourseInfo();
                System.out.println();
            }
    }
}
