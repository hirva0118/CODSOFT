import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> enrolledStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public void enrollStudent(String studentID) {
        enrolledStudents.add(studentID);
    }

    public void removeStudent(String studentID) {
        enrolledStudents.remove(studentID);
    }
}

class Student {
    private String studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

public class task4{
    private Map<String, Course> courseDatabase;
    private Map<String, Student> studentDatabase;

    public task4() {
        courseDatabase = new HashMap<>();
        studentDatabase = new HashMap<>();
    }

    public void addCourse(String code, String title, String description, int capacity, List<String> schedule) {
        Course course = new Course(code, title, description, capacity, schedule);
        courseDatabase.put(code, course);
    }

    public void addStudent(String studentID, String name) {
        Student student = new Student(studentID, name);
        studentDatabase.put(studentID, student);
    }

    public void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase.values()) {
            int availableSlots = course.getCapacity() - course.getEnrolledStudents().size();
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Available Slots: " + availableSlots);
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println();
        }
    }

    public void registerStudentForCourse(String studentID, String courseCode) {
        if (studentDatabase.containsKey(studentID) && courseDatabase.containsKey(courseCode)) {
            Student student = studentDatabase.get(studentID);
            Course course = courseDatabase.get(courseCode);
            if (!course.isFull() && !student.getRegisteredCourses().contains(courseCode)) {
                student.registerCourse(courseCode);
                course.enrollStudent(studentID);
                System.out.println("Student " + student.getName() + " has been registered for course " + course.getTitle());
            } else {
                System.out.println("Registration failed. Either the course is full or the student is already registered for it.");
            }
        } else {
            System.out.println("Student ID or Course Code not found.");
        }
    }

    public void dropStudentFromCourse(String studentID, String courseCode) {
        if (studentDatabase.containsKey(studentID) && courseDatabase.containsKey(courseCode)) {
            Student student = studentDatabase.get(studentID);
            Course course = courseDatabase.get(courseCode);
            if (student.getRegisteredCourses().contains(courseCode)) {
                student.dropCourse(courseCode);
                course.removeStudent(studentID);
                System.out.println("Student " + student.getName() + " has been removed from course " + course.getTitle());
            } else {
                System.out.println("Student is not registered for this course.");
            }
        } else {
            System.out.println("Student ID or Course Code not found.");
        }
    }

    public static void main(String[] args) {
        task4 system = new task4();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Course Registration System Menu:");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Display Available Courses");
            System.out.println("4. Register Student for Course");
            System.out.println("5. Drop Student from Course");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Course Code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter Course Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Course Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Course Capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Course Schedule (comma-separated): ");
                    String scheduleInput = scanner.nextLine();
                    List<String> schedule = new ArrayList<>(List.of(scheduleInput.split(",")));
                    system.addCourse(code, title, description, capacity, schedule);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    system.addStudent(studentID, studentName);
                    break;
                case 3:
                    system.displayAvailableCourses();
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    system.registerStudentForCourse(studentID, courseCode);
                    break;
                case 5:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    system.dropStudentFromCourse(studentID, courseCode);
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
