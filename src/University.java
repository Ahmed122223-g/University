public class University {
    private Student[] students;
    private int studentCount;
    private Doctor[] doctors;
    private int doctorCount;
    private Course[] courses;
    private int courseCount;
    private static final int MAX = 100;

    public University() {
        students = new Student[MAX];
        doctors = new Doctor[MAX];
        courses = new Course[MAX];
        studentCount = doctorCount = courseCount = 0;
    }

    public Student[] getStudents() {
        return students;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public Doctor[] getDoctors() {
        return doctors;
    }

    public int getDoctorCount() {
        return doctorCount;
    }

    public Course[] getCourses() {
        return courses;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void addStudent(Student s) {
        if (studentCount < MAX) {
            students[studentCount++] = s;
            System.out.println("Added student: " + s.getName());
        }
    }

    public void addDoctor(Doctor d) {
        if (doctorCount < MAX) {
            doctors[doctorCount++] = d;
            System.out.println("Added doctor: Dr. " + d.getName());
        }
    }

    public void addCourse(Course c) {
        if (courseCount < MAX) {
            courses[courseCount++] = c;
            System.out.println("Added course: " + c.getCourseName());
        }
    }

    public void displayAllStudents() {
        System.out.println("\n===== ALL STUDENTS =====");
        if (studentCount == 0)
            System.out.println("No students enrolled yet.");
        else
            for (int i = 0; i < studentCount; i++) {
                students[i].displayInfo();
                System.out.println();
            }
    }

    public void displayAllDoctors() {
        System.out.println("\n===== ALL DOCTORS =====");
        if (doctorCount == 0)
            System.out.println("No doctors registered yet.");
        else
            for (int i = 0; i < doctorCount; i++) {
                doctors[i].displayInfo();
                System.out.println();
            }
    }

    public void displayAllCourses() {
        System.out.println("\n===== ALL COURSES =====");
        if (courseCount == 0)
            System.out.println("No courses added yet.");
        else
            for (int i = 0; i < courseCount; i++) {
                courses[i].displayCourseInfo();
                System.out.println();
            }
    }
}
