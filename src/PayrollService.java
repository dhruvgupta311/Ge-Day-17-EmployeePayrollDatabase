import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayrollService {

    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Dhruv@2101041cs"; // Replace with your MySQL password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Step 1: Create Tables
            createTables(statement);

            // Step 2: Insert Sample Data
            insertSampleData(statement);

            // Step 3: Retrieve and Display Data (Optional)
            displayEmployeePayroll(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create tables
    private static void createTables(Statement statement) throws SQLException {
        // Create Department Table
        String createDepartmentTable = "CREATE TABLE IF NOT EXISTS Department (" +
                "department_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "department_name VARCHAR(100), " +
                "deductions DECIMAL(10, 2))";
        statement.executeUpdate(createDepartmentTable);

        // Create Employee Table
        String createEmployeeTable = "CREATE TABLE IF NOT EXISTS Employee (" +
                "employee_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "gender VARCHAR(10), " +
                "start_date DATE, " +
                "phone VARCHAR(15), " +
                "address VARCHAR(255), " +
                "department_id INT, " +
                "FOREIGN KEY (department_id) REFERENCES Department(department_id))";
        statement.executeUpdate(createEmployeeTable);

        // Create Payroll Table
        String createPayrollTable = "CREATE TABLE IF NOT EXISTS Payroll (" +
                "payroll_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "employee_id INT, " +
                "basic_pay DECIMAL(10, 2), " +
                "deductions DECIMAL(10, 2), " +
                "taxable_pay DECIMAL(10, 2), " +
                "income_tax DECIMAL(10, 2), " +
                "net_pay DECIMAL(10, 2), " +
                "salary DECIMAL(10, 2), " +
                "FOREIGN KEY (employee_id) REFERENCES Employee(employee_id))";
        statement.executeUpdate(createPayrollTable);

        // Create Deductions Table
        String createDeductionsTable = "CREATE TABLE IF NOT EXISTS Deductions (" +
                "deduction_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "payroll_id INT, " +
                "deduction_type VARCHAR(100), " +
                "amount DECIMAL(10, 2), " +
                "FOREIGN KEY (payroll_id) REFERENCES Payroll(payroll_id))";
        statement.executeUpdate(createDeductionsTable);

        // Create Tax Table
        String createTaxTable = "CREATE TABLE IF NOT EXISTS Tax (" +
                "tax_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "payroll_id INT, " +
                "tax_type VARCHAR(100), " +
                "tax_amount DECIMAL(10, 2), " +
                "FOREIGN KEY (payroll_id) REFERENCES Payroll(payroll_id))";
        statement.executeUpdate(createTaxTable);

        // Create Employee_Department Table
        String createEmployeeDepartmentTable = "CREATE TABLE IF NOT EXISTS Employee_Department (" +
                "employee_id INT, " +
                "department_id INT, " +
                "PRIMARY KEY (employee_id, department_id), " +
                "FOREIGN KEY (employee_id) REFERENCES Employee(employee_id), " +
                "FOREIGN KEY (department_id) REFERENCES Department(department_id))";
        statement.executeUpdate(createEmployeeDepartmentTable);
    }

    // Method to insert sample data
    private static void insertSampleData(Statement statement) throws SQLException {
        // Insert into Department Table
        String insertDepartment = "INSERT INTO Department (department_name, deductions) VALUES " +
                "('Sales', 1000), " +
                "('Marketing', 1500)";
        statement.executeUpdate(insertDepartment);

        // Insert into Employee Table
        String insertEmployee = "INSERT INTO Employee (name, gender, start_date, phone, address, department_id) VALUES " +
                "('Terissa', 'Female', '2022-01-01', '1234567890', '123 Street, City', 1)";
        statement.executeUpdate(insertEmployee);

        // Insert into Payroll Table
        String insertPayroll = "INSERT INTO Payroll (employee_id, basic_pay, deductions, taxable_pay, income_tax, net_pay, salary) VALUES " +
                "(1, 50000.00, 2000.00, 48000.00, 5000.00, 43000.00, 55000.00)";
        statement.executeUpdate(insertPayroll);

        // Insert into Deductions Table
        String insertDeductions = "INSERT INTO Deductions (payroll_id, deduction_type, amount) VALUES " +
                "(1, 'Health Insurance', 500.00), " +
                "(1, 'Retirement Contribution', 1500.00)";
        statement.executeUpdate(insertDeductions);

        // Insert into Tax Table
        String insertTax = "INSERT INTO Tax (payroll_id, tax_type, tax_amount) VALUES " +
                "(1, 'Income Tax', 5000.00)";
        statement.executeUpdate(insertTax);

        // Assign Employee to Multiple Departments (Many-to-Many relationship)
        String assignDepartments = "INSERT INTO Employee_Department (employee_id, department_id) VALUES " +
                "(1, 1), (1, 2)"; // Terissa is in both Sales and Marketing
        statement.executeUpdate(assignDepartments);
    }

    // Method to display Employee Payroll Data
    private static void displayEmployeePayroll(Statement statement) throws SQLException {
        String selectQuery = "SELECT e.name, p.basic_pay, p.deductions, p.taxable_pay, p.income_tax, p.net_pay, p.salary, " +
                "d.department_name FROM Employee e " +
                "JOIN Payroll p ON e.employee_id = p.employee_id " +
                "JOIN Employee_Department ed ON e.employee_id = ed.employee_id " +
                "JOIN Department d ON ed.department_id = d.department_id " +
                "WHERE e.name = 'Terissa'";

        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            double basicPay = resultSet.getDouble("basic_pay");
            double deductions = resultSet.getDouble("deductions");
            double taxablePay = resultSet.getDouble("taxable_pay");
            double incomeTax = resultSet.getDouble("income_tax");
            double netPay = resultSet.getDouble("net_pay");
            double salary = resultSet.getDouble("salary");
            String departmentName = resultSet.getString("department_name");

            System.out.println("Employee: " + name);
            System.out.println("Department: " + departmentName);
            System.out.println("Basic Pay: " + basicPay);
            System.out.println("Deductions: " + deductions);
            System.out.println("Taxable Pay: " + taxablePay);
            System.out.println("Income Tax: " + incomeTax);
            System.out.println("Net Pay: " + netPay);
            System.out.println("Salary: " + salary);
        }
    }
}
