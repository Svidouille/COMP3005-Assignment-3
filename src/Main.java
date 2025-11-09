//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        StudentData data = new StudentData();
        System.out.println("--- Showing Initial Data ---");
        data.getAllStudents();

        System.out.println("\n--- Adding Student 'Alice Wonder' ---");
        data.addStudent("Alice", "Wonder", "alice.wonder@example.com", LocalDate.parse("2023-09-03"));
        data.getAllStudents(); // Show table after addition

        System.out.println("\n--- Updating Student Email (ID 2) ---");
        data.updateStudentEmail(2, "jane.s.newemail@example.com");
        data.getAllStudents(); // Show table after update

        System.out.println("\n--- Deleting Student (ID 3) ---");
        data.deleteStudent(3);
        data.getAllStudents(); // Show table after deletion

        System.out.println("\n--- Attempting to Delete Non-Existent Student (ID 99) ---");
        data.deleteStudent(99); // Try to delete a non existing student
    }
}
