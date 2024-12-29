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

            // Step 1: Update Terissa's department to 'Sales and Marketing'
            String updateDepartmentQuery = "UPDATE employee_payroll SET department = 'Sales and Marketing' WHERE name = 'Terissa'";
            int departmentRowsUpdated = statement.executeUpdate(updateDepartmentQuery);
            System.out.println(departmentRowsUpdated + " rows updated for Terissa's department.");

            // Step 2: Update Terissa's salary if necessary (Example: updating salary for both entries of Terissa)
            String updateSalaryQuery = "UPDATE employee_payroll SET salary = 55000.00 WHERE name = 'Terissa'";
            int salaryRowsUpdated = statement.executeUpdate(updateSalaryQuery);
            System.out.println(salaryRowsUpdated + " rows updated for Terissa's salary.");

            // Step 3: Retrieve and display updated employee payroll data
            String selectQuery = "SELECT * FROM employee_payroll WHERE name = 'Terissa'";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("Updated Employee Payroll Data for Terissa:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                double salary = resultSet.getDouble("salary");
                String startDate = resultSet.getDate("start_date").toString();
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String department = resultSet.getString("department");
                double basicPay = resultSet.getDouble("basic_pay");
                double deductions = resultSet.getDouble("deductions");
                double taxablePay = resultSet.getDouble("taxable_pay");
                double incomeTax = resultSet.getDouble("income_tax");
                double netPay = resultSet.getDouble("net_pay");
                System.out.println("ID: " + id + ", Name: " + name + ", Gender: " + gender
                        + ", Salary: " + salary + ", Start Date: " + startDate
                        + ", Phone: " + phone + ", Address: " + address
                        + ", Department: " + department + ", Basic Pay: " + basicPay
                        + ", Deductions: " + deductions + ", Taxable Pay: " + taxablePay
                        + ", Income Tax: " + incomeTax + ", Net Pay: " + netPay);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
