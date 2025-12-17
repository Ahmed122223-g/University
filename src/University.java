public class University {

    private Student[] students;
    private int studentCount;
    private Doctor[] doctors;
    private int doctorCount;
    private Course[] courses;
    private int courseCount;

    private static final int MAX_LIMIT = 100;

    public University() {
        this.students = new Student[MAX_LIMIT];
        this.studentCount = 0;
        this.doctors = new Doctor[MAX_LIMIT];
        this.doctorCount = 0;
        this.courses = new Course[MAX_LIMIT];
        this.courseCount = 0;
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
        if (studentCount < MAX_LIMIT) {
            students[studentCount] = s;
            studentCount++;
            System.out.println("Added student: " + s.getName());
        } else {
            System.out.println("Cannot add more students.");
        }
    }

    public void addDoctor(Doctor d) {
        if (doctorCount < MAX_LIMIT) {
            doctors[doctorCount] = d;
            doctorCount++;
            System.out.println("Added doctor: Dr. " + d.getName());
        } else {
            System.out.println("Cannot add more doctors.");
        }
    }

    public void addCourse(Course c) {
        if (courseCount < MAX_LIMIT) {
            courses[courseCount] = c;
            courseCount++;
            System.out.println("Added course: " + c.getCourseName());
        } else {
            System.out.println("Cannot add more courses.");
        }
    }

    public void displayAllStudents() {
        System.out.println("\n===== ALL STUDENTS =====");
        if (studentCount == 0) {
            System.out.println("No students enrolled yet.");
        } else {
            for (int i = 0; i < studentCount; i++) {
                students[i].displayInfo();
                System.out.println();
            }
        }
    }

    public void displayAllDoctors() {
        System.out.println("\n===== ALL DOCTORS =====");
        if (doctorCount == 0) {
            System.out.println("No doctors registered yet.");
        } else {
            for (int i = 0; i < doctorCount; i++) {
                doctors[i].displayInfo();
                System.out.println();
            }
        }
    }

    public void displayAllCourses() {
        System.out.println("\n===== ALL COURSES =====");
        if (courseCount == 0) {
            System.out.println("No courses added yet.");
        } else {
            for (int i = 0; i < courseCount; i++) {
                courses[i].displayCourseInfo();
                System.out.println();
            }
        }
    }

    public void displayAllPeople() {
        System.out.println("\n===== POLYMORPHISM EXAMPLE =====");

        if (studentCount == 0 && doctorCount == 0) {
            System.out.println("No people in the university yet.");
        } else {
            for (int i = 0; i < studentCount; i++) {
                students[i].displayInfo();
                System.out.println();
            }
            for (int i = 0; i < doctorCount; i++) {
                doctors[i].displayInfo();
                System.out.println();
            }
        }
    }
}
