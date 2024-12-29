import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EmployeePayrollTable {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Dhruv@2101041cs"; // Replace with your MySQL password

    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS employee_payroll ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "gender VARCHAR(10), "
                    + "salary DECIMAL(10, 2) NOT NULL, "
                    + "start_date DATE NOT NULL)";
            statement.executeUpdate(createTableQuery);
            System.out.println("Table 'employee_payroll' created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertData() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String insertDataQuery = "INSERT INTO employee_payroll (name, gender, salary, start_date) "
                    + "VALUES ('John Doe', 'Male', 50000.00, '2023-01-01'), "
                    + "('Jane Smith', 'Female', 60000.00, '2023-02-01'), "
                    + "('Alice Johnson', 'Female', 55000.00, '2023-03-01'), "
                    + "('Bill Gates', 'Male', 100000.00, '2019-05-15')";
            int rowsInserted = statement.executeUpdate(insertDataQuery);
            System.out.println(rowsInserted + " rows inserted into 'employee_payroll'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
