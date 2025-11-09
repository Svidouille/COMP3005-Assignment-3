import java.sql.*;
import java.time.*;

public class StudentData {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Students";
    private static final String USER = "postgres";
    private static final String PASS = "rayane1201";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void getAllStudents() {
        String sql = "SELECT * FROM students ORDER BY student_id";

        // Using try to ensure connection, statement and result
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- All Students ---");
            // Loop through the result set
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s %s, Email: %s, Enrolled: %s%n",
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDate("enrollment_date").toLocalDate() // Use toLocalDate() for modern date
                );
            }
            System.out.println("----------------------");

        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addStudent(String firstName, String lastName, String email, LocalDate enrollmentDate) {
        String sql = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameter values
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setDate(4, Date.valueOf(enrollmentDate)); // Convert LocalDate to sql.Date

            // Execute the insert
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Successfully added student: " + firstName + " " + lastName);
            }

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateStudentEmail(int studentId, String newEmail) {
        String sql = "UPDATE students SET email = ? WHERE student_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, studentId);

            // Execute the update
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Successfully updated email for student ID: " + studentId);
            } else {
                System.out.println("No student found with ID: " + studentId + ". No update performed.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameter
            pstmt.setInt(1, studentId);

            // Execute the delete
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Successfully deleted student ID: " + studentId);
            } else {
                System.out.println("No student found with ID: " + studentId + ". No deletion performed.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

