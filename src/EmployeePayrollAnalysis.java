import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeePayrollAnalysis {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Dhruv@2101041cs"; // Replace with your MySQL password

    public static void retrieveSalarySummaryByGender() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Sum of Salaries by Gender
            String sumQuery = "SELECT gender, SUM(salary) AS total_salary FROM employee_payroll GROUP BY gender";
            ResultSet resultSet = statement.executeQuery(sumQuery);
            System.out.println("Sum of Salaries by Gender:");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double totalSalary = resultSet.getDouble("total_salary");
                System.out.println("Gender: " + gender + ", Total Salary: " + totalSalary);
            }

            // Average Salary by Gender
            String avgQuery = "SELECT gender, AVG(salary) AS average_salary FROM employee_payroll GROUP BY gender";
            resultSet = statement.executeQuery(avgQuery);
            System.out.println("\nAverage Salary by Gender:");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double averageSalary = resultSet.getDouble("average_salary");
                System.out.println("Gender: " + gender + ", Average Salary: " + averageSalary);
            }

            // Minimum Salary by Gender
            String minQuery = "SELECT gender, MIN(salary) AS min_salary FROM employee_payroll GROUP BY gender";
            resultSet = statement.executeQuery(minQuery);
            System.out.println("\nMinimum Salary by Gender:");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double minSalary = resultSet.getDouble("min_salary");
                System.out.println("Gender: " + gender + ", Minimum Salary: " + minSalary);
            }

            // Maximum Salary by Gender
            String maxQuery = "SELECT gender, MAX(salary) AS max_salary FROM employee_payroll GROUP BY gender";
            resultSet = statement.executeQuery(maxQuery);
            System.out.println("\nMaximum Salary by Gender:");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double maxSalary = resultSet.getDouble("max_salary");
                System.out.println("Gender: " + gender + ", Maximum Salary: " + maxSalary);
            }

            // Number of Employees by Gender
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
