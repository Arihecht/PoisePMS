package poised;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The PoisePMS class is responsible for handling user interactions
 * and delegating project management operations.
 */
public class PoisePMS {

  private static final Logger logger =
      Logger.getLogger(PoisePMS.class.getName());
  private static Scanner scanner;

  public static void main(String[] args) {
    logger.info("Starting PoisePMS Application.");
    scanner = new Scanner(System.in);

    try (Connection connection = Database.getConnection()) {
      logger.info("Connected to the database successfully.");
      boolean running = true;

      while (running) {
        displayMenu();
        int option = getMenuOption();
        switch (option) {
          case 1:
            ProjectManager.displayProjects(connection);
            break;
          case 2:
            addProject(connection);
            break;
          case 3:
            updateProject(connection);
            break;
          case 4:
            deleteProject(connection);
            break;
          case 5:
            finalizeProject(connection);
            break;
          case 6:
            ProjectManager.findUnfinishedProjects(connection);
            break;
          case 7:
            ProjectManager.findPastDueProjects(connection);
            break;
          case 8:
            findProjectByIdOrName(connection);
            break;
          case 9:
            System.out.println("Exiting program.");
            running = false;
            break;
          default:
            System.out.println("Invalid option. Please try again.");
            break;
        }
        if (running) {
          promptEnterKey();
        }
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Database connection error.", e);
      System.out.println("Failed to connect to the database. "
          + "Please check the configuration.");
    } finally {
      scanner.close();
      logger.info("PoisePMS Application terminated.");
    }
  }

  /**
   * Displays the main menu with available options.
   */
  private static void displayMenu() {
    System.out.println("\n=== PoisePMS Project Management System ===");
    System.out.println("1. Display Projects");
    System.out.println("2. Add Project");
    System.out.println("3. Update Project");
    System.out.println("4. Delete Project");
    System.out.println("5. Finalize Project");
    System.out.println("6. Find Unfinished Projects");
    System.out.println("7. Find Past Due Projects");
    System.out.println("8. Find Project by ID or Name");
    System.out.println("9. Exit");
  }

  /**
   * Gets and validates the user's menu option selection.
   *
   * @return The selected menu option as an integer
   */
  private static int getMenuOption() {
    System.out.print("Select an option (1-9): ");
    while (true) {
      String input = scanner.nextLine().trim();
      try {
        int option = Integer.parseInt(input);
        if (option >= 1 && option <= 9) {
          return option;
        } else {
          System.out.print("Invalid option. Please enter a number "
              + "between 1 and 9: ");
        }
      } catch (NumberFormatException e) {
        System.out.print("Invalid input. Please enter a number between 1 and 9: ");
      }
    }
  }

  /**
   * Adds a new project by collecting user input and calling ProjectManager.
   *
   * @param connection The database connection
   */
  public static void addProject(Connection connection) {
    System.out.print("Enter project name (leave empty to auto-generate): ");
    String projectName = scanner.nextLine().trim();

    System.out.print("Enter building type (e.g., House, Apartment): ");
    String buildingType = getNonEmptyInput();

    System.out.print("Enter physical address: ");
    String physicalAddress = getNonEmptyInput();

    float totalFee = getFloatInput("Enter total fee: ");
    LocalDate deadline = getDateInput("Enter deadline (YYYY-MM-DD): ");

    int architectId = getPersonId(connection, "Architect");
    int engineerId = getPersonId(connection, "Engineer");
    int managerId = getPersonId(connection, "Project Manager");
    int customerId = getPersonId(connection, "Customer");

    String customerSurname = getCustomerSurnameById(connection, customerId);

    if (projectName.isEmpty()) {
      projectName = buildingType + " " + customerSurname;
      System.out.println("Project name auto-generated as: " + projectName);
    }

    Project project = new Project(
        projectName,
        buildingType,
        physicalAddress,
        totalFee,
        deadline,
        architectId,
        engineerId,
        managerId,
        customerId
    );

    ProjectManager.addProject(connection, project);
  }

  /**
   * Updates an existing project by collecting user input.
   *
   * @param connection The database connection
   */
  private static void updateProject(Connection connection) {
    int projectId = getProjectIdInput("Enter project ID to update: ");
    Project existingProject = ProjectManager.getProjectById(connection, projectId);
    if (existingProject == null) {
      System.out.println("No project found with ID " + projectId);
      return;
    }

    System.out.println("Leave fields blank to keep existing values.");

    System.out.print("Enter new project name (current: "
        + existingProject.getProjectName() + "): ");
    String projectName = scanner.nextLine().trim();
    if (projectName.isEmpty()) {
      projectName = existingProject.getProjectName();
    }

    System.out.print("Enter new building type (current: "
        + existingProject.getBuildingType() + "): ");
    String buildingType = scanner.nextLine().trim();
    if (buildingType.isEmpty()) {
      buildingType = existingProject.getBuildingType();
    }

    System.out.print("Enter new physical address (current: "
        + existingProject.getPhysicalAddress() + "): ");
    String physicalAddress = scanner.nextLine().trim();
    if (physicalAddress.isEmpty()) {
      physicalAddress = existingProject.getPhysicalAddress();
    }

    System.out.print("Enter new total fee (current: $"
        + existingProject.getTotalFee() + "): ");
    String feeInput = scanner.nextLine().trim();
    float totalFee = existingProject.getTotalFee();
    if (!feeInput.isEmpty()) {
      try {
        totalFee = Float.parseFloat(feeInput);
      } catch (NumberFormatException e) {
        System.out.println("Invalid fee input. Keeping existing value.");
      }
    }

    System.out.print("Enter new deadline (YYYY-MM-DD) (current: "
        + existingProject.getDeadline() + "): ");
    String deadlineInput = scanner.nextLine().trim();
    LocalDate deadline = existingProject.getDeadline();
    if (!deadlineInput.isEmpty()) {
      try {
        deadline = LocalDate.parse(deadlineInput);
      } catch (DateTimeParseException e) {
        System.out.println("Invalid date format. Keeping existing deadline.");
      }
    }

    int architectId = getPersonId(connection, "Architect");
    int engineerId = getPersonId(connection, "Engineer");
    int managerId = getPersonId(connection, "Project Manager");
    int customerId = getPersonId(connection, "Customer");

    Project updatedProject = new Project(
        projectId,
        projectName,
        buildingType,
        physicalAddress,
        totalFee,
        deadline,
        architectId,
        engineerId,
        managerId,
        customerId,
        existingProject.isFinalised(),
        existingProject.getCompletionDate()
    );

    ProjectManager.updateProject(connection, updatedProject);
  }

  /**
   * Deletes a project by ID.
   *
   * @param connection The database connection
   */
  private static void deleteProject(Connection connection) {
    int projectId = getProjectIdInput("Enter project ID to delete: ");
    ProjectManager.deleteProject(connection, projectId);
  }

  /**
   * Finalizes a project by marking it as completed.
   *
   * @param connection The database connection
   */
  private static void finalizeProject(Connection connection) {
    int projectId = getProjectIdInput("Enter project ID to finalize: ");
    ProjectManager.finalizeProject(connection, projectId);
  }

  /**
   * Finds a project by its ID or name.
   *
   * @param connection The database connection
   */
  private static void findProjectByIdOrName(Connection connection) {
    System.out.print("Enter project ID or name: ");
    String input = scanner.nextLine().trim();
    ProjectManager.findProjectByIdOrName(connection, input);
  }

  /**
   * Prompts the user to press Enter to continue after an operation.
   */
  private static void promptEnterKey() {
    System.out.println("\nPress Enter to continue...");
    scanner.nextLine();
  }

  /**
   * Retrieves the customer's surname by their ID.
   *
   * @param connection The database connection
   * @param customerId The customer ID
   * @return The customer's surname
   */
  private static String getCustomerSurnameById(Connection connection,
                                               int customerId) {
    String query = "SELECT name FROM Customer WHERE customer_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, customerId);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          String fullName = rs.getString("name");
          String[] nameParts = fullName.split(" ");
          String surname = nameParts.length > 1 ?
              nameParts[nameParts.length - 1] : fullName;
          return surname;
        } else {
          System.out.println("No customer found with ID: " + customerId);
          return "Unknown";  // Return "Unknown" if customer not found
        }
      }
    } catch (SQLException e) {
      System.out.println("An error occurred while retrieving customer surname.");
      e.printStackTrace();
      return "Unknown";  // Return "Unknown" if error occurs
    }
  }

  private static String getNonEmptyInput() {
    while (true) {
      String input = scanner.nextLine().trim();
      if (!input.isEmpty()) {
        return input;
      }
      System.out.print("Input cannot be empty. Please enter again: ");
    }
  }

  private static float getFloatInput(String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine().trim();
      try {
        return Float.parseFloat(input);
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
      }
    }
  }

  private static LocalDate getDateInput(String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine().trim();
      try {
        return LocalDate.parse(input);
      } catch (Exception e) {
        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
      }
    }
  }

  private static int getProjectIdInput(String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine().trim();
      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid project ID.");
      }
    }
  }

  private static int getPersonId(Connection connection, String personType) {
    System.out.print("Enter " + personType + " name: ");
    String personName = getNonEmptyInput();
    return ProjectManager.getPersonIdByName(connection, personType, personName);
  }
}

















