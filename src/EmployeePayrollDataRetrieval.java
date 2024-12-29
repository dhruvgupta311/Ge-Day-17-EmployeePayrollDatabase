import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeePayrollDataRetrieval {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Dhruv@2101041cs"; // Replace with your MySQL password

    public static void retrieveAllEmployeeData() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void retrieveEmployeeSalary(String employeeName) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String selectSalaryQuery = "SELECT salary FROM employee_payroll WHERE name = '" + employeeName + "'";
            ResultSet resultSet = statement.executeQuery(selectSalaryQuery);
            if (resultSet.next()) {
                System.out.println("Salary of " + employeeName + ": " + resultSet.getDouble("salary"));
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void retrieveEmployeesByDateRange() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String selectByDateRangeQuery = "SELECT * FROM employee_payroll WHERE start_date BETWEEN CAST('2018-01-01' AS DATE) AND DATE(NOW())";
            ResultSet resultSet = statement.executeQuery(selectByDateRangeQuery);
            System.out.println("Employees who joined between 2018-01-01 and now:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                String startDate = resultSet.getDate("start_date").toString();
                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Start Date: " + startDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
