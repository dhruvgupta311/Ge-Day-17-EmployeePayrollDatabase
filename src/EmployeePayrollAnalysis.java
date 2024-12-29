// File: EmployeePayrollAnalysis.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class EmployeePayrollAnalysis {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service"; // Change the URL for your MSSQL Server
    private static final String USER = "root"; // Replace with your MSSQL username
    private static final String PASSWORD = "Dhruv@2101041cs"; // Replace with your MSSQL password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Query to get the sum of salaries grouped by gender
            String sumQuery = "SELECT gender, SUM(salary) AS total_salary FROM employee_payroll GROUP BY gender";
            ResultSet resultSet = statement.executeQuery(sumQuery);
            System.out.println("Sum of Salaries by Gender:");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double totalSalary = resultSet.getDouble("total_salary");
                System.out.println("Gender: " + gender + ", Total Salary: " + totalSalary);
            }

            // Query to get the average salary grouped by gender
            String avgQuery = "SELECT gender, AVG(salary) AS average_salary FROM employee_payroll GROUP BY gender";
            resultSet = statement.executeQuery(avgQuery);
            System.out.println("\nAverage Salary by Gender:");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double averageSalary = resultSet.getDouble("average_salary");
                System.out.println("Gender: " + gender + ", Average Salary: " + averageSalary);
            }

            // Query to get the minimum salary grouped by gender
            String minQuery = "SELECT gender, MIN(salary) AS min_salary FROM employee_payroll GROUP BY gender";
            resultSet = statement.executeQuery(minQuery);
            System.out.println("\nMinimum Salary by Gender:");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double minSalary = resultSet.getDouble("min_salary");
                System.out.println("Gender: " + gender + ", Minimum Salary: " + minSalary);
            }

            // Query to get the maximum salary grouped by gender
            String maxQuery = "SELECT gender, MAX(salary) AS max_salary FROM employee_payroll GROUP BY gender";
            resultSet = statement.executeQuery(maxQuery);
            System.out.println("\nMaximum Salary by Gender:");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double maxSalary = resultSet.getDouble("max_salary");
                System.out.println("Gender: " + gender + ", Maximum Salary: " + maxSalary);
            }

            // Query to count the number of employees grouped by gender
            String countQuery = "SELECT gender, COUNT(*) AS num_employees FROM employee_payroll GROUP BY gender";
            resultSet = statement.executeQuery(countQuery);
            System.out.println("\nNumber of Employees by Gender:");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                int numEmployees = resultSet.getInt("num_employees");
                System.out.println("Gender: " + gender + ", Number of Employees: " + numEmployees);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
