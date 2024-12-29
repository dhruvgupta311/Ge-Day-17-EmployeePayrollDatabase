import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Dhruv@2101041cs"; // Replace with your MySQL password
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD) ;
             Statement statement = connection.createStatement()) {

            // Step 1: Create the database
            String createDatabaseQuery = "CREATE DATABASE payroll_service";
            statement.executeUpdate(createDatabaseQuery);
            System.out.println("Database 'payroll_service' created successfully.");

            // Step 2: Verify the database creation
            String showDatabasesQuery = "SHOW DATABASES";
            var resultSet = statement.executeQuery(showDatabasesQuery);
            System.out.println("Databases:");
            while (resultSet.next()) {
                System.out.println("- " + resultSet.getString(1));
            }

            // Step 3: Use the created database
            String useDatabaseQuery = "USE payroll_service";
            statement.execute(useDatabaseQuery);
            System.out.println("Using database 'payroll_service'.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
