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

            // Step 1: Drop the table if it exists
            String dropTableQuery = "DROP TABLE IF EXISTS employee_payroll";
            statement.executeUpdate(dropTableQuery);
            System.out.println("Existing table 'employee_payroll' dropped (if it existed).");

            // Step 2: Create the employee_payroll table
            String createTableQuery = "CREATE TABLE employee_payroll ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "gender CHAR(1), "
                    + "salary DECIMAL(10, 2) NOT NULL, "
                    + "start_date DATE NOT NULL)";

            statement.executeUpdate(createTableQuery);
            System.out.println("Table 'employee_payroll' created successfully.");

            // Step 3: Insert data into the employee_payroll table
            String insertDataQuery = "INSERT INTO employee_payroll (name, gender, salary, start_date) "
                    + "VALUES ('John Doe', 'M', 50000.00, '2023-01-01'), "
                    + "('Jane Smith', 'F', 60000.00, '2023-02-01'), "
                    + "('Alice Johnson', 'F', 55000.00, '2023-03-01'), "
                    + "('Bill Gates', 'M', 100000.00, '2019-05-15')";

            int rowsInserted = statement.executeUpdate(insertDataQuery);
            System.out.println(rowsInserted + " rows inserted into 'employee_payroll'.");

            // Step 4: Retrieve all data from the employee_payroll table
            String selectQuery = "SELECT * FROM employee_payroll";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("Employee Payroll Data:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                double salary = resultSet.getDouble("salary");
                String startDate = resultSet.getDate("start_date").toString();
                System.out.println("ID: " + id + ", Name: " + name + ", Gender: " + gender + ", Salary: " + salary + ", Start Date: " + startDate);
            }

            // Step 5: Update gender for specific employees
            String updateGenderQuery1 = "UPDATE employee_payroll SET gender = 'M' WHERE name = 'Bill Gates'";
            String updateGenderQuery2 = "UPDATE employee_payroll SET gender = 'M' WHERE name = 'John Doe'";
            String updateGenderQuery3 = "UPDATE employee_payroll SET gender = 'F' WHERE name = 'Jane Smith' OR name = 'Alice Johnson'";

            statement.executeUpdate(updateGenderQuery1);
            statement.executeUpdate(updateGenderQuery2);
            statement.executeUpdate(updateGenderQuery3);
            System.out.println("Gender updated successfully for specific employees.");

            // Step 6: Verify updated data
            resultSet = statement.executeQuery(selectQuery);
            System.out.println("Updated Employee Payroll Data:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                double salary = resultSet.getDouble("salary");
                String startDate = resultSet.getDate("start_date").toString();
                System.out.println("ID: " + id + ", Name: " + name + ", Gender: " + gender + ", Salary: " + salary + ", Start Date: " + startDate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
