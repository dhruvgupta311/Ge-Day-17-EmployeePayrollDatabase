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

            // Step 1: Drop the existing table and create a new one
            String dropTableQuery = "DROP TABLE IF EXISTS employee_payroll";
            statement.executeUpdate(dropTableQuery);
            System.out.println("Existing table 'employee_payroll' dropped.");

            // Step 2: Create the employee_payroll table with new fields
            String createTableQuery = "CREATE TABLE employee_payroll ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "gender CHAR(1), "
                    + "salary DECIMAL(10, 2) NOT NULL, "
                    + "start_date DATE NOT NULL, "
                    + "phone VARCHAR(15), "
                    + "address VARCHAR(255) DEFAULT 'Not Provided', "
                    + "department VARCHAR(100) NOT NULL, "
                    + "basic_pay DECIMAL(10, 2), "
                    + "deductions DECIMAL(10, 2), "
                    + "taxable_pay DECIMAL(10, 2), "
                    + "income_tax DECIMAL(10, 2), "
                    + "net_pay DECIMAL(10, 2)"
                    + ")";
            statement.executeUpdate(createTableQuery);
            System.out.println("Table 'employee_payroll' created successfully with new fields.");

            // Step 3: Insert data into the employee_payroll table with new fields
            String insertDataQuery = "INSERT INTO employee_payroll (name, gender, salary, start_date, phone, address, department, basic_pay, deductions, taxable_pay, income_tax, net_pay) "
                    + "VALUES ('John Doe', 'M', 50000.00, '2023-01-01', '123-456-7890', '123 Main St', 'HR', 45000.00, 5000.00, 40000.00, 5000.00, 35000.00), "
                    + "('Jane Smith', 'F', 60000.00, '2023-02-01', '987-654-3210', '456 Elm St', 'IT', 55000.00, 4000.00, 51000.00, 6000.00, 45000.00), "
                    + "('Alice Johnson', 'F', 55000.00, '2023-03-01', '555-123-4567', '789 Oak St', 'Finance', 50000.00, 3000.00, 47000.00, 5500.00, 41500.00), "
                    + "('Bill Gates', 'M', 100000.00, '2019-05-15', '111-222-3333', '101 Maple St', 'Executive', 95000.00, 8000.00, 87000.00, 12000.00, 75000.00)";
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
