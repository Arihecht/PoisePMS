package poised;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * The ProjectManager class handles project management operations
 * including adding, updating, deleting projects and managing persons.
 */
public class ProjectManager {
  private static final Logger logger = Logger.getLogger(ProjectManager.class.getName());

  /**
   * Displays all projects from the database.
   *
   * @param connection The database connection
   */
  public static void displayProjects(Connection connection) {
    String query = "SELECT * FROM Project";
    try (PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      System.out.println("\n=== Projects ===");
      while (rs.next()) {
        System.out.printf("ID: %d, Name: %s, Type: %s, Address: %s, Fee: %.2f, "
            + "Deadline: %s, Finalized: %s\n",
            rs.getInt("project_id"),
            rs.getString("project_name"),
            rs.getString("building_type"),
            rs.getString("physical_address"),
            rs.getFloat("total_fee"),
            rs.getDate("deadline").toLocalDate(),
            rs.getInt("is_finalised") == 1 ? "Yes" : "No");
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving projects.");
      logger.severe("Error retrieving projects: " + e.getMessage());
    }
  }

  /**
   * Finalizes a project by marking it as completed.
   *
   * @param connection The database connection
   * @param projectId  The ID of the project to be finalized
   */
  public static void finalizeProject(Connection connection, int projectId) {
    String query = "UPDATE Project SET is_finalised = 1, completion_date = ? "
        + "WHERE project_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
      pstmt.setInt(2, projectId);
      pstmt.executeUpdate();
      System.out.println("Project finalized successfully.");
    } catch (SQLException e) {
      System.out.println("Error finalizing project.");
      logger.severe("Error finalizing project: " + e.getMessage());
    }
  }

  /**
   * Adds a new project to the database.
   *
   * @param connection The database connection
   * @param project    The project to be added
   */
  public static void addProject(Connection connection, Project project) {
    String query = "INSERT INTO Project (project_name, building_type, "
        + "physical_address, total_fee, deadline, erf_number, architect_id, "
        + "engineer_id, manager_id, contractor_id, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setString(1, project.getProjectName());
      pstmt.setString(2, project.getBuildingType());
      pstmt.setString(3, project.getPhysicalAddress());
      pstmt.setFloat(4, project.getTotalFee());
      pstmt.setDate(5, java.sql.Date.valueOf(project.getDeadline()));
      pstmt.setString(6, project.getErfNumber());
      pstmt.setInt(7, project.getArchitectId());
      pstmt.setInt(8, project.getEngineerId());
      pstmt.setInt(9, project.getManagerId());
      pstmt.setInt(10, project.getContractorId());
      pstmt.setInt(11, project.getCustomerId());
      pstmt.executeUpdate();
      System.out.println("Project added successfully.");
    } catch (SQLException e) {
      System.out.println("Error adding project.");
      logger.severe("Error adding project: " + e.getMessage());
    }
  }

  /**
   * Updates an existing project in the database.
   *
   * @param connection The database connection
   * @param project    The updated project information
   */
  public static void updateProject(Connection connection, Project project) {
    String query = "UPDATE Project SET project_name = ?, building_type = ?, "
        + "physical_address = ?, total_fee = ?, deadline = ?, architect_id = ?, "
        + "engineer_id = ?, manager_id = ?, contractor_id = ?, customer_id = ? "
        + "WHERE project_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setString(1, project.getProjectName());
      pstmt.setString(2, project.getBuildingType());
      pstmt.setString(3, project.getPhysicalAddress());
      pstmt.setFloat(4, project.getTotalFee());
      pstmt.setDate(5, java.sql.Date.valueOf(project.getDeadline()));
      pstmt.setInt(6, project.getArchitectId());
      pstmt.setInt(7, project.getEngineerId());
      pstmt.setInt(8, project.getManagerId());
      pstmt.setInt(9, project.getContractorId());
      pstmt.setInt(10, project.getCustomerId());
      pstmt.setInt(11, project.getId());
      pstmt.executeUpdate();
      System.out.println("Project updated successfully.");
    } catch (SQLException e) {
      System.out.println("Error updating project.");
      logger.severe("Error updating project: " + e.getMessage());
    }
  }

  /**
   * Deletes a project by ID from the database and removes associated people.
   *
   * @param connection The database connection
   * @param projectId  The ID of the project to be deleted
   */
  public static void deleteProjectAndAssociatedPersons(Connection connection, int projectId) {
    deleteAssociatedPersons(connection, projectId);
    String query = "DELETE FROM Project WHERE project_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, projectId);
      pstmt.executeUpdate();
      System.out.println("Project deleted successfully.");
    } catch (SQLException e) {
      System.out.println("Error deleting project.");
      logger.severe("Error deleting project: " + e.getMessage());
    }
  }

  /**
   * Retrieves a project by its ID.
   *
   * @param connection The database connection
   * @param projectId  The ID of the project
   * @return The Project object if found, null otherwise
   */
  public static Project getProjectById(Connection connection, int projectId) {
    String query = "SELECT * FROM Project WHERE project_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, projectId);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return new Project(
            rs.getInt("project_id"),
            rs.getString("project_name"),
            rs.getString("building_type"),
            rs.getString("physical_address"),
            rs.getFloat("total_fee"),
            rs.getDate("deadline").toLocalDate(),
            rs.getString("erf_number"),
            rs.getInt("architect_id"),
            rs.getInt("engineer_id"),
            rs.getInt("manager_id"),
            rs.getInt("contractor_id"),
            rs.getInt("customer_id"),
            rs.getInt("is_finalised"),
            rs.getDate("completion_date") != null ? rs.getDate("completion_date").toLocalDate() : null
        );
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving project.");
      logger.severe("Error retrieving project: " + e.getMessage());
    }
    return null;
  }

  /**
   * Finds a project by its ID or name.
   *
   * @param connection The database connection
   * @param input      The project ID or name
   */
  public static void findProjectByIdOrName(Connection connection, String input) {
    String query = "SELECT * FROM Project WHERE project_id = ? OR project_name = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      try {
        pstmt.setInt(1, Integer.parseInt(input));
      } catch (NumberFormatException e) {
        pstmt.setNull(1, java.sql.Types.INTEGER); // Set to null if parsing fails
      }
      pstmt.setString(2, input);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        System.out.printf("ID: %d, Name: %s, Type: %s, Address: %s, Fee: %.2f, Deadline: %s\n",
            rs.getInt("project_id"),
            rs.getString("project_name"),
            rs.getString("building_type"),
            rs.getString("physical_address"),
            rs.getFloat("total_fee"),
            rs.getDate("deadline").toLocalDate());
      } else {
        System.out.println("No project found with ID or name: " + input);
      }
    } catch (SQLException e) {
      System.out.println("Error finding project.");
      logger.severe("Error finding project: " + e.getMessage());
    }
  }

  /**
   * Finds past due projects.
   *
   * @param connection The database connection
   */
  public static void findPastDueProjects(Connection connection) {
    String query = "SELECT * FROM Project WHERE deadline < CURRENT_DATE AND is_finalised = 0";
    try (PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      System.out.println("\n=== Past Due Projects ===");
      while (rs.next()) {
        System.out.printf("ID: %d, Name: %s, Deadline: %s\n",
            rs.getInt("project_id"),
            rs.getString("project_name"),
            rs.getDate("deadline").toLocalDate());
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving past due projects.");
      logger.severe("Error retrieving past due projects: " + e.getMessage());
    }
  }

  /**
   * Finds unfinished projects.
   *
   * @param connection The database connection
   */
  public static void findUnfinishedProjects(Connection connection) {
    String query = "SELECT * FROM Project WHERE is_finalised = 0";
    try (PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      System.out.println("\n=== Unfinished Projects ===");
      while (rs.next()) {
        System.out.printf("ID: %d, Name: %s, Deadline: %s\n",
            rs.getInt("project_id"),
            rs.getString("project_name"),
            rs.getDate("deadline").toLocalDate());
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving unfinished projects.");
      logger.severe("Error retrieving unfinished projects: " + e.getMessage());
    }
  }

  /**
   * Lists all persons of a specified type.
   *
   * @param connection The database connection
   * @param personType The type of person (e.g., Architect, Engineer)
   */
  public static void listPersons(Connection connection, String personType) {
    String query = "";
    switch (personType) {
      case "Architect":
        query = "SELECT architect_id, name FROM Architect";
        break;
      case "Engineer":
        query = "SELECT engineer_id, name FROM StructuralEngineer";
        break;
      case "Contractor":
        query = "SELECT contractor_id, name FROM Contractor";
        break;
      case "Project Manager":
        query = "SELECT manager_id, name FROM ProjectManager";
        break;
      case "Customer":
        query = "SELECT customer_id, name FROM Customer";
        break;
      default:
        System.out.println("Unknown person type.");
        return;
    }

    try (PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        System.out.printf("ID: %d, Name: %s\n",
            rs.getInt(1), // Column 1: ID
            rs.getString("name")); // Column 2: Name
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving persons.");
      logger.severe("Error retrieving persons: " + e.getMessage());
    }
  }

  /**
   * Adds a new person to the database.
   *
   * @param connection The database connection
   * @param person     The person to be added
   */
  public static void addPerson(Connection connection, Person person) throws SQLException {
    String query = "INSERT INTO Person (name, phone, email) VALUES (?, ?, ?)";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setString(1, person.getName());
      pstmt.setString(2, person.getPhoneNumber());
      pstmt.setString(3, person.getEmail());
      pstmt.executeUpdate();
      System.out.println("Person added successfully.");
    } catch (SQLException e) {
      System.out.println("Error adding person.");
      logger.severe("Error adding person: " + e.getMessage());
      throw e; 
    }
  }

  /**
   * Updates an existing person's information in the database.
   *
   * @param connection The database connection
   * @param person     The updated person information
   */
  public static void updatePerson(Connection connection, Person person) {
    String query = "UPDATE Person SET name = ?, phone = ?, email = ? WHERE person_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setString(1, person.getName());
      pstmt.setString(2, person.getPhoneNumber());
      pstmt.setString(3, person.getEmail());
      pstmt.setInt(4, person.getId());
      pstmt.executeUpdate();
      System.out.println("Person updated successfully.");
    } catch (SQLException e) {
      System.out.println("Error updating person.");
      logger.severe("Error updating person: " + e.getMessage());
    }
  }

  /**
   * Deletes a person from the database by ID.
   *
   * @param connection The database connection
   * @param personId   The ID of the person to be deleted
   */
  public static void deletePerson(Connection connection, int personId) {
    String query = "DELETE FROM Person WHERE person_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, personId);
      pstmt.executeUpdate();
      System.out.println("Person deleted successfully.");
    } catch (SQLException e) {
      System.out.println("Error deleting person.");
      logger.severe("Error deleting person: " + e.getMessage());
    }
  }

  /**
   * Deletes all persons associated with a given project ID.
   *
   * @param connection The database connection
   * @param projectId  The project ID for which associated persons should be deleted
   */
  private static void deleteAssociatedPersons(Connection connection, int projectId) {
    String query = "DELETE FROM Person WHERE project_id = ?"; // Ensure this column exists in the database
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, projectId);
      pstmt.executeUpdate();
      System.out.println("Associated persons deleted successfully.");
    } catch (SQLException e) {
      System.out.println("Error deleting associated persons.");
      logger.severe("Error deleting associated persons: " + e.getMessage());
    }
  }

  /**
   * Retrieves a person's ID based on their name from the database.
   *
   * @param connection The database connection
   * @param personType The type of person (e.g., Architect, Engineer)
   * @param personName The name of the person to look for
   * @return The person's ID or -1 if not found
   */
  public static int getPersonIdByName(Connection connection, String personType, String personName) {
    String query = "";
    switch (personType) {
      case "Architect":
        query = "SELECT architect_id FROM Architect WHERE name = ?";
        break;
      case "Engineer":
        query = "SELECT engineer_id FROM StructuralEngineer WHERE name = ?"; 
        break;
      case "Contractor":
        query = "SELECT contractor_id FROM Contractor WHERE name = ?";
        break;
      case "Project Manager":
        query = "SELECT manager_id FROM ProjectManager WHERE name = ?"; 
        break;
      case "Customer":
        query = "SELECT customer_id FROM Customer WHERE name = ?";
        break;
      default:
        System.out.println("Unknown person type.");
        return -1;
    }

    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setString(1, personName);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving person ID.");
      logger.severe("Error retrieving person ID: " + e.getMessage());
    }
    return -1; // Return -1 if not found
  }

  /**
   * Retrieves a person by their ID.
   *
   * @param connection The database connection
   * @param personId   The ID of the person
   * @return The Person object if found, null otherwise
   */
  public static Person getPersonById(Connection connection, int personId) {
    String query = "SELECT * FROM Person WHERE person_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, personId);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return new Person(
            rs.getInt("person_id"),
            rs.getString("name"),
            rs.getString("phone"),
            rs.getString("email")
        );
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving person.");
      logger.severe("Error retrieving person: " + e.getMessage());
    }
    return null;
  }
}



