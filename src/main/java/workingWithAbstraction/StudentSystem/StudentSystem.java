package workingWithAbstraction.StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> students;

    public StudentSystem() {
        this.students = new HashMap<>();
    }

    public String parseCommand(String[] args) {

        String command = args[0];
        String name = args[1];

        switch (command) {
            case "Create":
                int studentAge = Integer.parseInt(args[2]);
                double studentGrade = Double.parseDouble(args[3]);
                createAndRegisterStudent(name, studentAge, studentGrade);
                return null;
            case "Show":
                return getStudentInformation(name);
            default:
                throw new IllegalArgumentException("Unknown command " + command);
        }
    }

    private String getStudentInformation(String name) {
        Student student = this.students.get(name);

        if (student == null) {
            throw new IllegalArgumentException("No student with this name " + name);
        }
        return StudentInforFormatter.getFormattedInfo(student);
    }

    private void createAndRegisterStudent(String name, int age, double grade) {
        this.students.putIfAbsent(name, new Student(name, age, grade));
    }
}
