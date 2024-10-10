package poised;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database utility class for managing database connections.
 */
public class Database {
  private static final Logger logger =
      Logger.getLogger(Database.class.getName());
  private static final String URL = 
      "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false"
      + "&allowPublicKeyRetrieval=true";
  private static final String USER = "otheruser"; 
  private static final String PASSWORD = "swordfish"; 

  /**
   * Establishes and returns a connection to the database.
   *
   * @return Connection object
   * @throws SQLException if a database access error occurs
   */
  public static Connection getConnection() throws SQLException {
    try {
      // Load the JDBC driver (optional for newer versions)
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      logger.log(Level.SEVERE, "JDBC Driver not found.", e);
      throw new SQLException("JDBC Driver not found.", e);
    }
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}




