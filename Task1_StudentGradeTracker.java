import java.util.*;

public class Task1_StudentGradeTracker {
    static class Student {
        String name;
        int score;

        Student(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < count; i++) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter score for " + name + ": ");
            int score = scanner.nextInt();
            scanner.nextLine(); // consume newline
            students.add(new Student(name, score));
        }

        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        for (Student s : students) {
            total += s.score;
            if (s.score > highest) highest = s.score;
            if (s.score < lowest) lowest = s.score;
        }

        double average = (double) total / students.size();

        System.out.println("\n--- Student Summary Report ---");
        for (Student s : students) {
            System.out.println(s.name + ": " + s.score);
        }
        System.out.println("\nAverage Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);
        scanner.close();
    }
}
