import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nEmployee Payroll System Menu:");
            System.out.println("1. Create employee_payroll Table");
            System.out.println("2. Insert Data into employee_payroll Table");
            System.out.println("3. Retrieve All Employee Payroll Data");
            System.out.println("4. Retrieve Salary of a Specific Employee");
            System.out.println("5. Retrieve Employees by Joining Date Range");
            System.out.println("6. Retrieve Salary Summary (Sum, Avg, Min, Max) by Gender");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    EmployeePayrollTable.createTable();
                    break;
                case 2:
                    EmployeePayrollTable.insertData();
                    break;
                case 3:
                    EmployeePayrollDataRetrieval.retrieveAllEmployeeData();
                    break;
                case 4:
                    System.out.print("Enter employee name to retrieve salary: ");
                    String employeeName = scanner.nextLine();
                    EmployeePayrollDataRetrieval.retrieveEmployeeSalary(employeeName);
                    break;
                case 5:
                    EmployeePayrollDataRetrieval.retrieveEmployeesByDateRange();
                    break;
                case 6:
                    EmployeePayrollAnalysis.retrieveSalarySummaryByGender();
                    break;
                case 7:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);
    }
}
