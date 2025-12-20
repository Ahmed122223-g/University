# جامعة بني سويف الأهلية
## Beni Suef National University

نظام إدارة جامعي مكتوب بلغة Java يطبق مفاهيم البرمجة كائنية التوجه (OOP).

---

## هيكل المشروع

```
University/
├── src/
│   ├── Person.java      # الكلاس الأب (Abstract)
│   ├── Student.java     # كلاس الطالب
│   ├── Doctor.java      # كلاس الدكتور
│   ├── Course.java      # كلاس المادة الدراسية
│   ├── University.java  # كلاس الجامعة
│   └── Main.java        # نقطة البداية
└── README.md
```

---

## شرح الكلاسات بالتفصيل

---

## 1. Person.java (الكلاس الأب)

كلاس مجرد (Abstract) يمثل أي شخص في النظام. الطالب والدكتور يرثون منه.

### المتغيرات (Attributes):
```java
private String name;    // اسم الشخص
private String id;      // رقم الهوية
private String email;   // البريد الإلكتروني
```

### الـ Constructor:
```java
public Person(String name, String id, String email)
```
- **الوظيفة**: ينشئ شخص جديد بالاسم والـ ID والإيميل.
- **كيف يعمل**: يحفظ البيانات في المتغيرات الخاصة.

### الـ Getters (دوال القراءة):
```java
public String getName()   // يرجع الاسم
public String getId()     // يرجع الـ ID
public String getEmail()  // يرجع الإيميل
```

### الـ Setters (دوال التعديل):
```java
public void setName(String name)    // يغير الاسم
public void setId(String id)        // يغير الـ ID
public void setEmail(String email)  // يغير الإيميل
```

### الـ Abstract Method:
```java
public abstract void displayInfo();
```
- **الوظيفة**: دالة مجردة لعرض المعلومات.
- **ملاحظة**: كل كلاس يرث من Person لازم يعمل implementation لهذه الدالة.

---

## 2. Student.java (كلاس الطالب)

يرث من Person ويمثل طالب في الجامعة.

### المتغيرات:
```java
private String major;              // التخصص
private int year;                  // السنة الدراسية
private Course[] selectedCourses; // مصفوفة المواد المسجلة
private int selectedCoursesCount; // عدد المواد المسجلة حالياً
public static final int MAX_COURSES = 7; // أقصى عدد مواد للطالب
```

### الـ Constructor:
```java
public Student(String name, String id, String email, String major, int year)
```
- **الوظيفة**: ينشئ طالب جديد.
- **كيف يعمل**: 
  1. يستدعي constructor الأب (Person) بـ `super(name, id, email)`
  2. يحفظ التخصص والسنة
  3. ينشئ مصفوفة فاضية للمواد بحجم 7

### الـ Getters:
```java
public String getMajor()              // يرجع التخصص
public int getYear()                  // يرجع السنة
public Course[] getSelectedCourses() // يرجع مصفوفة المواد
public int getSelectedCoursesCount() // يرجع عدد المواد المسجلة
```

### الـ Setters:
```java
public void setMajor(String major)  // يغير التخصص
public void setYear(int year)       // يغير السنة
```

### displayInfo():
```java
public void displayInfo()
```
- **الوظيفة**: تعرض كل معلومات الطالب.
- **ماذا تطبع**: الاسم، ID، التخصص، السنة، الـ GPA، والمواد المسجلة.

### selectCourse(Course c):
```java
public void selectCourse(Course c)
```
- **الوظيفة**: تسجيل الطالب في مادة.
- **الشروط**:
  1. عدد المواد لازم يكون أقل من 7
  2. المادة لازم يكون ليها دكتور معين
  3. الطالب ما يكونش مقيّم فيها من قبل
- **كيف تعمل**: تضيف المادة للمصفوفة وتزود العداد.

### viewCourses():
```java
public void viewCourses()
```
- **الوظيفة**: تعرض كل المواد المسجلة مع الدرجات.
- **كيف تعمل**: تمر على كل مادة وتطبع اسمها، الدكتور، والدرجة (أو "Not Graded").

### calculateFinalGPA():
```java
public double calculateFinalGPA()
```
- **الوظيفة**: تحسب المعدل التراكمي.
- **كيف تعمل**: 
  1. تجمع (الدرجة × الساعات) لكل مادة
  2. تقسم على مجموع الساعات
  3. ترجع 0.0 لو مفيش درجات

### showMenu(Scanner scanner, University uni):
```java
public void showMenu(java.util.Scanner scanner, University uni)
```
- **الوظيفة**: تعرض قائمة خيارات الطالب.
- **الخيارات**:
  1. التسجيل في مادة
  2. عرض المواد والدرجات
  3. عرض الـ GPA
  4. الرجوع للقائمة الرئيسية

### enrollInCourse(Scanner scanner, University uni):
```java
private void enrollInCourse(java.util.Scanner scanner, University uni)
```
- **الوظيفة**: دالة خاصة لعملية التسجيل.
- **كيف تعمل**:
  1. تعرض كل المواد المتاحة
  2. الطالب يختار رقم المادة
  3. تستدعي selectCourse

---

## 3. Doctor.java (كلاس الدكتور)

يرث من Person ويمثل دكتور في الجامعة.

### المتغيرات:
```java
private String department;           // القسم
private Course[] assignedCourses;   // المواد اللي بيدرسها
private int assignedCoursesCount;   // عدد المواد
private static final int MAX_COURSES = 10; // أقصى عدد مواد للدكتور
```

### الـ Constructor:
```java
public Doctor(String name, String id, String email, String department)
```
- **الوظيفة**: ينشئ دكتور جديد.
- **كيف يعمل**: يستدعي constructor الأب ويحفظ القسم وينشئ مصفوفة مواد فاضية.

### الـ Getters:
```java
public String getDepartment()         // يرجع القسم
public Course[] getAssignedCourses() // يرجع المواد
public int getAssignedCoursesCount() // يرجع عدد المواد
```

### الـ Setters:
```java
public void setDepartment(String department)  // يغير القسم
```

### displayInfo():
```java
public void displayInfo()
```
- **الوظيفة**: تعرض معلومات الدكتور.
- **ماذا تطبع**: الاسم، ID، القسم، المواد، والدرجات اللي حطها.

### assignCourse(Course c):
```java
public void assignCourse(Course c)
```
- **الوظيفة**: تعيين الدكتور لتدريس مادة.
- **كيف تعمل**:
  1. تضيف المادة لمصفوفة الدكتور
  2. تستدعي `c.assignDoctor(this)` علشان تسجل الدكتور في المادة

### addGrade(Student s, Course c, double grade):
```java
public void addGrade(Student s, Course c, double grade)
```
- **الوظيفة**: إضافة درجة لطالب في مادة.
- **الشروط**:
  1. الدكتور لازم يكون بيدرس المادة
  2. الطالب لازم يكون مسجل في المادة
- **كيف تعمل**: تستدعي `c.setGrade(s, grade)`

### viewCourses():
```java
public void viewCourses()
```
- **الوظيفة**: تعرض المواد اللي الدكتور بيدرسها.

### showGrades():
```java
public void showGrades()
```
- **الوظيفة**: تعرض كل الدرجات اللي الدكتور حطها.
- **كيف تعمل**: تمر على كل مادة وتطبع درجات الطلاب فيها.

### showMenu(Scanner scanner, University uni):
```java
public void showMenu(java.util.Scanner scanner, University uni)
```
- **الوظيفة**: قائمة خيارات الدكتور.
- **الخيارات**:
  1. إضافة مادة جديدة
  2. وضع درجة لطالب
  3. عرض المواد والدرجات
  4. الرجوع للقائمة الرئيسية

### addNewCourse(Scanner scanner, University uni):
```java
private void addNewCourse(java.util.Scanner scanner, University uni)
```
- **الوظيفة**: دالة خاصة لإضافة مادة جديدة.
- **كيف تعمل**:
  1. تطلب اسم المادة، الكود، والساعات
  2. تنشئ Course جديد
  3. تضيفه للجامعة
  4. تعين الدكتور لتدريسه

### assignGrade(Scanner scanner, University uni):
```java
private void assignGrade(java.util.Scanner scanner, University uni)
```
- **الوظيفة**: دالة خاصة لوضع درجة.
- **كيف تعمل**:
  1. تعرض مواد الدكتور
  2. الدكتور يختار مادة
  3. تعرض الطلاب المسجلين في المادة
  4. الدكتور يختار طالب ويدخل الدرجة

---

## 4. Course.java (كلاس المادة)

يمثل مادة دراسية في الجامعة.

### المتغيرات:
```java
private String courseName;          // اسم المادة
private String courseCode;          // كود المادة
private int credits;                // عدد الساعات
private Doctor doctor;              // الدكتور المسؤول
private Student[] gradedStudents;   // الطلاب اللي ليهم درجات
private double[] gradesList;        // مصفوفة الدرجات
private int gradesCount;            // عدد الدرجات
public static final double MAX_GRADE = 4.0;   // أعلى درجة
private static final int MAX_STUDENTS = 50;   // أقصى عدد طلاب
```

### الـ Constructor:
```java
public Course(String courseName, String courseCode, int credits)
```
- **الوظيفة**: ينشئ مادة جديدة.
- **كيف يعمل**: يحفظ البيانات وينشئ مصفوفات فاضية للطلاب والدرجات.

### الـ Getters:
```java
public String getCourseName()      // اسم المادة
public String getCourseCode()      // كود المادة
public int getCredits()            // الساعات
public Doctor getDoctor()          // الدكتور
public Student[] getGradedStudents() // الطلاب المقيمين
public double[] getGradesList()    // الدرجات
public int getGradesCount()        // عدد الدرجات
```

### assignDoctor(Doctor d):
```java
public void assignDoctor(Doctor d)
```
- **الوظيفة**: تعيين دكتور للمادة.
- **كيف تعمل**: تحفظ الدكتور في المتغير `doctor`.

### displayCourseInfo():
```java
public void displayCourseInfo()
```
- **الوظيفة**: تعرض معلومات المادة.
- **ماذا تطبع**: الاسم، الكود، الساعات، والدكتور.

### setGrade(Student s, double grade):
```java
public void setGrade(Student s, double grade)
```
- **الوظيفة**: تسجيل درجة لطالب.
- **كيف تعمل**:
  1. تدور لو الطالب موجود → تحدث درجته
  2. لو مش موجود → تضيفه مع درجته

### getGrade(Student s):
```java
public Double getGrade(Student s)
```
- **الوظيفة**: ترجع درجة طالب معين.
- **كيف تعمل**: تدور على الطالب في المصفوفة.
- **ترجع**: الدرجة أو `null` لو مش موجود.

---

## 5. University.java (كلاس الجامعة)

يمثل الجامعة ويحتوي على كل الطلاب والدكاترة والمواد.

### المتغيرات:
```java
private Student[] students;     // مصفوفة الطلاب
private int studentCount;       // عدد الطلاب
private Doctor[] doctors;       // مصفوفة الدكاترة
private int doctorCount;        // عدد الدكاترة
private Course[] courses;       // مصفوفة المواد
private int courseCount;        // عدد المواد
private static final int MAX = 100; // السعة القصوى
```

### الـ Constructor:
```java
public University()
```
- **الوظيفة**: ينشئ جامعة جديدة.
- **كيف يعمل**: ينشئ مصفوفات فاضية (100 طالب، 10 دكاترة، 100 مادة).

### الـ Getters:
```java
public Student[] getStudents()     // مصفوفة الطلاب
public int getStudentCount()       // عدد الطلاب
public Doctor[] getDoctors()       // مصفوفة الدكاترة
public int getDoctorCount()        // عدد الدكاترة
public Course[] getCourses()       // مصفوفة المواد
public int getCourseCount()        // عدد المواد
```

### addStudent(Student s):
```java
public void addStudent(Student s)
```
- **الوظيفة**: إضافة طالب للجامعة.
- **كيف تعمل**: تضيفه للمصفوفة لو في مكان.

### addDoctor(Doctor d):
```java
public void addDoctor(Doctor d)
```
- **الوظيفة**: إضافة دكتور للجامعة.
- **كيف تعمل**: تضيفه للمصفوفة لو في مكان.

### addCourse(Course c):
```java
public void addCourse(Course c)
```
- **الوظيفة**: إضافة مادة للجامعة.
- **كيف تعمل**: تضيفها للمصفوفة لو في مكان.

### displayAllStudents():
```java
public void displayAllStudents()
```
- **الوظيفة**: تعرض كل الطلاب.
- **كيف تعمل**: تمر على كل طالب وتستدعي `displayInfo()`.

### displayAllDoctors():
```java
public void displayAllDoctors()
```
- **الوظيفة**: تعرض كل الدكاترة.
- **كيف تعمل**: تمر على كل دكتور وتستدعي `displayInfo()`.

### displayAllCourses():
```java
public void displayAllCourses()
```
- **الوظيفة**: تعرض كل المواد.
- **كيف تعمل**: تمر على كل مادة وتستدعي `displayCourseInfo()`.

---

## 6. Main.java (نقطة البداية)

الكلاس الرئيسي اللي فيه دالة `main`.

### المتغيرات الثابتة:
```java
static Scanner scanner = new Scanner(System.in); // للقراءة من المستخدم
static University uni = new University();        // كائن الجامعة
```

### main(String[] args):
```java
public static void main(String[] args)
```
- **الوظيفة**: نقطة بداية البرنامج.
- **كيف تعمل**:
  1. تطبع اسم الجامعة
  2. تدخل في loop
  3. تعرض القائمة وتنتظر اختيار المستخدم
  4. تنفذ الاختيار
  5. تتوقف لما المستخدم يختار Exit

### showMenu():
```java
static void showMenu()
```
- **الوظيفة**: تعرض القائمة الرئيسية.
- **الخيارات**:
  1. تسجيل دكتور
  2. تسجيل طالب
  3. دخول كدكتور
  4. دخول كطالب
  5. عرض الطلاب
  6. عرض الدكاترة
  7. عرض المواد
  8. خروج

### registerDoctor():
```java
static void registerDoctor()
```
- **الوظيفة**: تسجيل دكتور جديد.
- **كيف تعمل**: تطلب البيانات وتنشئ Doctor وتضيفه للجامعة.

### registerStudent():
```java
static void registerStudent()
```
- **الوظيفة**: تسجيل طالب جديد.
- **كيف تعمل**: تطلب البيانات وتنشئ Student وتضيفه للجامعة.

### doctorMenu():
```java
static void doctorMenu()
```
- **الوظيفة**: شاشة دخول الدكتور.
- **كيف تعمل**:
  1. تعرض قائمة الدكاترة
  2. الدكتور يختار
  3. تستدعي `showMenu()` للدكتور المختار

### studentMenu():
```java
static void studentMenu()
```
- **الوظيفة**: شاشة دخول الطالب.
- **كيف تعمل**:
  1. تعرض قائمة الطلاب
  2. الطالب يختار
  3. تستدعي `showMenu()` للطالب المختار

---

## مفاهيم OOP المستخدمة

| المفهوم | الشرح | المثال |
|---------|-------|--------|
| **Encapsulation** | إخفاء البيانات باستخدام private | كل المتغيرات private مع getters/setters |
| **Inheritance** | الوراثة | Student و Doctor يرثون من Person |
| **Polymorphism** | تعدد الأشكال | كل كلاس له displayInfo() مختلف |
| **Abstraction** | التجريد | Person كلاس abstract |
| **Static** | متغيرات/دوال ثابتة | MAX_COURSES, scanner |
| **Final** | قيم لا تتغير | MAX = 100 |

---

## كيفية التشغيل

```bash
cd University
javac -d bin src/*.java
java -cp bin Main
```

---

## السعات والحدود

| العنصر | الحد الأقصى |
|--------|-------------|
| الطلاب في الجامعة | 100 |
| الدكاترة في الجامعة | 10 |
| المواد في الجامعة | 100 |
| مواد الطالب | 7 |
| مواد الدكتور | 10 |
| طلاب المادة الواحدة | 50 |
