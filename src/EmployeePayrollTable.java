// File: EmployeePayrollTable.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class EmployeePayrollTable {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Dhruv@2101041cs"; // Replace with your MySQL password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Step 1: Create the employee_payroll table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS employee_payroll ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "salary DECIMAL(10, 2) NOT NULL, "
                    + "start_date DATE NOT NULL)";

            statement.executeUpdate(createTableQuery);
            System.out.println("Table 'employee_payroll' created successfully.");

            // Step 2: Insert data into the employee_payroll table
            String insertDataQuery = "INSERT INTO employee_payroll (name, salary, start_date) "
                    + "VALUES ('John Doe', 50000.00, '2023-01-01'), "
                    + "('Jane Smith', 60000.00, '2023-02-01'), "
                    + "('Alice Johnson', 55000.00, '2023-03-01'), "
                    + "('Bill Gates', 100000.00, '2019-05-15')";

            int rowsInserted = statement.executeUpdate(insertDataQuery);
            System.out.println(rowsInserted + " rows inserted into 'employee_payroll'.");

            // Step 3: Retrieve all data from the employee_payroll table
            String selectQuery = "SELECT * FROM employee_payroll";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("Employee Payroll Data:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                String startDate = resultSet.getDate("start_date").toString();
                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Start Date: " + startDate);
            }

            // Step 4: Retrieve salary of a particular employee (Bill)
            String selectSalaryQuery = "SELECT salary FROM employee_payroll WHERE name = 'Bill Gates'";
            resultSet = statement.executeQuery(selectSalaryQuery);
            System.out.println("Salary of Bill Gates:");
            if (resultSet.next()) {
                System.out.println("Salary: " + resultSet.getDouble("salary"));
            } else {
                System.out.println("Employee not found.");
            }

            // Step 5: Retrieve employees who joined in a particular date range
            String selectByDateRangeQuery = "SELECT * FROM employee_payroll WHERE start_date BETWEEN CAST('2018-01-01' AS DATE) AND DATE(NOW())";
            resultSet = statement.executeQuery(selectByDateRangeQuery);
            System.out.println("Employees who joined between 2018-01-01 and now:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                String startDate = resultSet.getDate("start_date").toString();
                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Start Date: " + startDate);
            }

            // Step 6: Verify table creation
            String showTablesQuery = "SHOW TABLES";
            resultSet = statement.executeQuery(showTablesQuery);
            System.out.println("Tables in payroll_service database:");
            while (resultSet.next()) {
                System.out.println("- " + resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
