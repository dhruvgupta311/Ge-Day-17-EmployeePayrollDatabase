// File: EmployeePayrollTable.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EmployeePayrollTable {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Dhruv@2101041cs"; // Replace with your MySQL password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Step 1: Create the employee_payroll table
            String createTableQuery = "CREATE TABLE employee_payroll ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "salary DECIMAL(10, 2) NOT NULL, "
                    + "start_date DATE NOT NULL)";

            statement.executeUpdate(createTableQuery);
            System.out.println("Table 'employee_payroll' created successfully.");

            // Step 2: Verify table creation
            String showTablesQuery = "SHOW TABLES";
            var resultSet = statement.executeQuery(showTablesQuery);
            System.out.println("Tables in payroll_service database:");
            while (resultSet.next()) {
                System.out.println("- " + resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
