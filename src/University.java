import java.util.ArrayList;

public class University {

    private ArrayList<Student> students;
    private ArrayList<Doctor> doctors;
    private ArrayList<Course> courses;

    public University() {
        this.students = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
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
        System.out.println("\n===== ALL STUDENTS =====");
        if (students.isEmpty()) {
            System.out.println("No students enrolled yet.");
        } else {
            for (Student s : students) {
                s.displayInfo();
                System.out.println();
            }
        }
    }

    public void displayAllDoctors() {
        System.out.println("\n===== ALL DOCTORS =====");
        if (doctors.isEmpty()) {
            System.out.println("No doctors registered yet.");
        } else {
            for (Doctor d : doctors) {
                d.displayInfo();
                System.out.println();
            }
        }
    }

    public void displayAllCourses() {
        System.out.println("\n===== ALL COURSES =====");
        if (courses.isEmpty()) {
            System.out.println("No courses added yet.");
        } else {
            for (Course c : courses) {
                c.displayCourseInfo();
                System.out.println();
            }
        }
    }

    public void displayAllPeople() {
        System.out.println("\n===== POLYMORPHISM EXAMPLE =====");

        ArrayList<Person> people = new ArrayList<>();
        people.addAll(students);
        people.addAll(doctors);

        if (people.isEmpty()) {
            System.out.println("No people in the university yet.");
        } else {
            for (Person p : people) {
                p.displayInfo();
                System.out.println();
            }
        }
    }
}
