import java.io.*;
import java.util.*;
import java.util.stream.*;

public class StudentReport {
    
    public static void main(String[] args) throws IOException {
        List<Student> students = readStudents("students.csv");
        
        // Use map to transform Student objects into GradeInfo objects
        List<GradeInfo> gradeInfos = students.stream()
            .collect(Collectors.groupingBy(s -> s.course))
            .entrySet().stream()
            .map(e -> {
                String course = e.getKey();
                List<Integer> grades = e.getValue().stream()
                    .map(s -> s.grade)
                    .collect(Collectors.toList());
                double avg = grades.stream()
                    .mapToDouble(Integer::doubleValue)
                    .average()
                    .getAsDouble();
                int count = e.getValue().size();
                return new GradeInfo(course, avg, count);
            })
            .collect(Collectors.toList());

        
        // Use filter to remove courses with less than 5 students
        gradeInfos = gradeInfos.stream()
            .filter(g -> g.count >= 5)
            .collect(Collectors.toList());
        
        // Use fold to calculate the total number of students in each grade category
        Map<String, Integer> gradeCounts = students.stream()
            .map(s -> {
                if (s.grade >= 90) return "A";
                else if (s.grade >= 80) return "B";
                else if (s.grade >= 70) return "C";
                else if (s.grade >= 60) return "D";
                else return "F";
            })
            .collect(Collectors.groupingBy(g -> g, Collectors.summingInt(g -> 1)));
        
        // Generate report
        System.out.println("Course Report:");
        System.out.println("--------------");
        for (GradeInfo grade : gradeInfos) {
            System.out.printf("%s: avg=%.2f, count=%d%n", grade.course, grade.avg, grade.count);
        }
        System.out.println();
        System.out.println("Grade Distribution:");
        System.out.println("-------------------");
        System.out.printf("A: %d%n", gradeCounts.getOrDefault("A", 0));
        System.out.printf("B: %d%n", gradeCounts.getOrDefault("B", 0));
        System.out.printf("C: %d%n", gradeCounts.getOrDefault("C", 0));
        System.out.printf("D: %d%n", gradeCounts.getOrDefault("D", 0));
        System.out.printf("F: %d%n", gradeCounts.getOrDefault("F", 0));
    }
    
    public static List<Student> readStudents(String filename) throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String course = fields[1];
                int grade = Integer.parseInt(fields[2]);
                students.add(new Student(name, course, grade));
            }
        }
        return students;
    }
    
    public static class Student {
        public final String name;
        public final String course;
        public final int grade;
        
        public Student(String name, String course, int grade) {
            this.name = name;
            this.course = course;
            this.grade = grade;
        }
    }
    
    public static class GradeInfo {
        public final String course;
        public final double avg;
        public final int count;
        
        public GradeInfo(String course, double avg, int count) {
            this.course = course;
            this.avg = avg;
            this.count = count;
        }
    }
}
