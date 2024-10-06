package poised;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ProjectManager is responsible for managing projects and
 * interacting with the database to perform CRUD operations.
 */
public class ProjectManager {
  private static final Logger logger =
      Logger.getLogger(ProjectManager.class.getName());

  /**
   * Displays all projects in the database.
   *
   * @param connection the database connection
   */
  public static void displayProjects(Connection connection) {
    String query = "SELECT * FROM Project";
    try (PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

      List<Project> projects = new ArrayList<>();
      while (rs.next()) {
        Project project = Project.extractProjectFromResultSet(rs);
        projects.add(project);
      }

      if (projects.isEmpty()) {
        System.out.println("No projects found.");
      } else {
        projects.forEach(System.out::println);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error displaying projects.", e);
      System.out.println("An error occurred while displaying projects.");
    }
  }

  /**
   * Adds a new project to the database.
   *
   * @param connection the database connection
   * @param project    The project details to add
   */
  public static void addProject(Connection connection, Project project) {
    String query = "INSERT INTO Project (project_name, building_type, "
        + "physical_address, total_fee, deadline, is_finalised) "
        + "VALUES (?, ?, ?, ?, ?, 0)";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setString(1, project.getProjectName());
      pstmt.setString(2, project.getBuildingType());
      pstmt.setString(3, project.getPhysicalAddress());
      pstmt.setFloat(4, project.getTotalFee());
      pstmt.setDate(5, Date.valueOf(project.getDeadline()));

      int rowsInserted = pstmt.executeUpdate();
      if (rowsInserted > 0) {
        System.out.println("Project added successfully!");
        logger.info("Added new project: " + project.getProjectName());
      } else {
        System.out.println("Failed to add the project.");
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error adding project.", e);
      System.out.println("An error occurred while adding the project.");
    }
  }

  /**
   * Updates an existing project in the database.
   *
   * @param connection the database connection
   * @param project    The updated project details
   */
  public static void updateProject(Connection connection, Project project) {
    String query = "UPDATE Project SET project_name = ?, building_type = ?, "
        + "physical_address = ?, total_fee = ?, deadline = ? "
        + "WHERE project_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setString(1, project.getProjectName());
      pstmt.setString(2, project.getBuildingType());
      pstmt.setString(3, project.getPhysicalAddress());
      pstmt.setFloat(4, project.getTotalFee());
      pstmt.setDate(5, Date.valueOf(project.getDeadline()));
      pstmt.setInt(6, project.getProjectId());

      int rowsUpdated = pstmt.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Project updated successfully!");
        logger.info("Updated project ID: " + project.getProjectId());
      } else {
        System.out.println("Failed to update the project.");
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error updating project.", e);
      System.out.println("An error occurred while updating the project.");
    }
  }

  /**
   * Deletes a project from the database.
   *
   * @param connection the database connection
   * @param projectId  The ID of the project to delete
   */
  public static void deleteProject(Connection connection, int projectId) {
    String query = "DELETE FROM Project WHERE project_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, projectId);

      int rowsDeleted = pstmt.executeUpdate();
      if (rowsDeleted > 0) {
        System.out.println("Project deleted successfully!");
        logger.info("Deleted project ID: " + projectId);
      } else {
        System.out.println("Failed to delete the project.");
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error deleting project.", e);
      System.out.println("An error occurred while deleting the project.");
    }
  }

  /**
   * Finalizes a project by setting its status and completion date.
   *
   * @param connection the database connection
   * @param projectId  The ID of the project to finalize
   */
  public static void finalizeProject(Connection connection, int projectId) {
    String query = "UPDATE Project SET is_finalised = 1, "
        + "completion_date = ? WHERE project_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setDate(1, Date.valueOf(LocalDate.now()));
      pstmt.setInt(2, projectId);

      int rowsUpdated = pstmt.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Project finalized successfully!");
        logger.info("Finalized project ID: " + projectId);
      } else {
        System.out.println("Failed to finalize the project.");
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error finalizing project.", e);
      System.out.println("An error occurred while finalizing the project.");
    }
  }

  /**
   * Finds and displays all unfinished projects.
   *
   * @param connection the database connection
   */
  public static void findUnfinishedProjects(Connection connection) {
    String query = "SELECT * FROM Project WHERE is_finalised = 0";
    try (PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

      List<Project> projects = new ArrayList<>();
      while (rs.next()) {
        Project project = Project.extractProjectFromResultSet(rs);
        projects.add(project);
      }

      if (projects.isEmpty()) {
        System.out.println("No unfinished projects found.");
      } else {
        projects.forEach(System.out::println);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error finding unfinished projects.", e);
      System.out.println("An error occurred while retrieving unfinished "
          + "projects.");
    }
  }

  /**
   * Finds and displays all past due projects.
   *
   * @param connection the database connection
   */
  public static void findPastDueProjects(Connection connection) {
    String query = "SELECT * FROM Project WHERE deadline < CURDATE() "
        + "AND is_finalised = 0";
    try (PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

      List<Project> projects = new ArrayList<>();
      while (rs.next()) {
        Project project = Project.extractProjectFromResultSet(rs);
        projects.add(project);
      }

      if (projects.isEmpty()) {
        System.out.println("No past due projects found.");
      } else {
        projects.forEach(System.out::println);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error finding past due projects.", e);
      System.out.println("An error occurred while retrieving past due "
          + "projects.");
    }
  }

  /**
   * Finds and displays a project by its ID or name.
   *
   * @param connection the database connection
   * @param input      The project ID or name to search for
   */
  public static void findProjectByIdOrName(Connection connection, String input) {
    String query = "SELECT * FROM Project WHERE project_id = ? "
        + "OR project_name LIKE ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      if (input.matches("\\d+")) {
        pstmt.setInt(1, Integer.parseInt(input));
      } else {
        pstmt.setNull(1, Types.INTEGER);
      }
      pstmt.setString(2, "%" + input + "%");

      try (ResultSet rs = pstmt.executeQuery()) {
        List<Project> projects = new ArrayList<>();
        while (rs.next()) {
          Project project = Project.extractProjectFromResultSet(rs);
          projects.add(project);
        }

        if (projects.isEmpty()) {
          System.out.println("No project found with ID or name: " + input);
        } else {
          projects.forEach(System.out::println);
        }
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error finding project by ID or name.", e);
      System.out.println("An error occurred while searching for the project.");
    }
  }

  /**
   * Retrieves a Project by its ID.
   *
   * @param connection the database connection
   * @param projectId  the project ID
   * @return a Project object or null if not found
   */
  public static Project getProjectById(Connection connection, int projectId) {
    String query = "SELECT * FROM Project WHERE project_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, projectId);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return Project.extractProjectFromResultSet(rs);
        }
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error retrieving project by ID.", e);
      System.out.println("An error occurred while retrieving the project.");
  }
  return null;
}
}

