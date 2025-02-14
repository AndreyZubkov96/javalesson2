package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public record StudentGrade(String studentName, String school, String subject, double score) {}

    public static void main(String[] args) {

        ArrayList<Student> studentList = new ArrayList<Student>();
        ArrayList<Grade> grades = new ArrayList<Grade>();
        grades.add(new Grade("Test", 52.0));
        grades.add(new Grade("Test2", 54.0));
        studentList.add(new Student("Bob", 17, grades, "School1", new Address("New York", "street1")));

        ArrayList<Grade> grades2 = new ArrayList<Grade>();
        grades2.add(new Grade("Test3", 32.2));
        grades2.add(new Grade("Test4", 38.5));

        studentList.add(new Student("Bob2", 14, grades2, "School2", new Address("New York", "street2")));

        ArrayList<Grade> grades3 = new ArrayList<Grade>();
        grades3.add(new Grade("Test5", 43.5));
        grades3.add(new Grade("Test7", 95.2));
        grades3.add(new Grade("Test8", 43.7));

        studentList.add(new Student("Bob3", 20, grades3, "School3", new Address("New York", "street3")));

        List<StudentGrade> studentGrades = studentList.stream()
            .filter(student -> student.age > 15)
            .filter(student -> student.address.city.equals("New York"))
            .flatMap(student -> student.grades.stream()
                .map(grade -> new StudentGrade(
                    student.name,
                    student.school,
                    grade.subject,
                    grade.score
                ))
            )
            .collect(Collectors.toList());

        List<StudentGrade> studentGrades2 = studentGrades.stream()
            .sorted((studentGrade1, studentGrade2) -> Double.compare(studentGrade2.score, studentGrade1.score))
            .limit(3)
            .collect(Collectors.toList());

        studentGrades2.forEach(System.out::println);
    }
}