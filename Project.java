package poised;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Represents a Project entity with project details and associated
 * foreign keys for people.
 */
public class Project {
  private int projectId;
  private String projectName;
  private String buildingType;
  private String physicalAddress;
  private float totalFee;
  private LocalDate deadline;
  private boolean isFinalised;
  private LocalDate completionDate;
  private int architectId;
  private int structuralEngineerId;
  private int projectManagerId;
  private int customerId;

  // Constructor for adding a new project (no projectId needed since
  // it's auto-generated in the database)
  public Project(String projectName, String buildingType,
      String physicalAddress, float totalFee, LocalDate deadline,
      int architectId, int structuralEngineerId, int projectManagerId,
      int customerId) {
    this.projectName = projectName;
    this.buildingType = buildingType;
    this.physicalAddress = physicalAddress;
    this.totalFee = totalFee;
    this.deadline = deadline;
    this.isFinalised = false;  // Default is not finalized
    this.architectId = architectId;
    this.structuralEngineerId = structuralEngineerId;
    this.projectManagerId = projectManagerId;
    this.customerId = customerId;
  }

  // Constructor for updating or fetching an existing project with
  // all fields
  public Project(int projectId, String projectName, String buildingType,
      String physicalAddress, float totalFee, LocalDate deadline,
      int architectId, int structuralEngineerId, int projectManagerId,
      int customerId, boolean isFinalised, LocalDate completionDate) {
    this.projectId = projectId;
    this.projectName = projectName;
    this.buildingType = buildingType;
    this.physicalAddress = physicalAddress;
    this.totalFee = totalFee;
    this.deadline = deadline;
    this.isFinalised = isFinalised;
    this.completionDate = completionDate;
    this.architectId = architectId;
    this.structuralEngineerId = structuralEngineerId;
    this.projectManagerId = projectManagerId;
    this.customerId = customerId;
  }

  /**
   * Extracts a Project object from the current row of the ResultSet.
   *
   * @param rs The ResultSet object containing the data
   * @return A Project object
   * @throws SQLException If there is an issue accessing the database
   */
  public static Project extractProjectFromResultSet(ResultSet rs)
      throws SQLException {
    int projectId = rs.getInt("project_id");
    String projectName = rs.getString("project_name");
    String buildingType = rs.getString("building_type");
    String physicalAddress = rs.getString("physical_address");
    float totalFee = rs.getFloat("total_fee");
    LocalDate deadline = rs.getDate("deadline").toLocalDate();
    boolean isFinalised = rs.getBoolean("is_finalised");
    LocalDate completionDate = (rs.getDate("completion_date") != null)
        ? rs.getDate("completion_date").toLocalDate() : null;

    // Foreign key IDs for related people
    int architectId = rs.getInt("architect_id");
    int structuralEngineerId = rs.getInt("engineer_id");
    int projectManagerId = rs.getInt("manager_id");
    int customerId = rs.getInt("customer_id");

    // Create and return a new Project object
    return new Project(projectId, projectName, buildingType,
        physicalAddress, totalFee, deadline, architectId,
        structuralEngineerId, projectManagerId, customerId,
        isFinalised, completionDate);
  }

  // Getters for accessing the project details
  public int getProjectId() { return projectId; }
  public String getProjectName() { return projectName; }
  public String getBuildingType() { return buildingType; }
  public String getPhysicalAddress() { return physicalAddress; }
  public float getTotalFee() { return totalFee; }
  public LocalDate getDeadline() { return deadline; }
  public boolean isFinalised() { return isFinalised; }
  public LocalDate getCompletionDate() { return completionDate; }
  public int getArchitectId() { return architectId; }
  public int getStructuralEngineerId() { return structuralEngineerId; }
  public int getProjectManagerId() { return projectManagerId; }
  public int getCustomerId() { return customerId; }

  @Override
  public String toString() {
    return "Project ID: " + projectId +
        "\nProject Name: " + projectName +
        "\nBuilding Type: " + buildingType +
        "\nPhysical Address: " + physicalAddress +
        "\nTotal Fee: $" + totalFee +
        "\nDeadline: " + deadline +
        "\nFinalised: " + (isFinalised ? "Yes" : "No") +
        (isFinalised ? "\nCompletion Date: " + completionDate : "") +
        "\nArchitect ID: " + architectId +
        "\nStructural Engineer ID: " + structuralEngineerId +
        "\nProject Manager ID: " + projectManagerId +
        "\nCustomer ID: " + customerId +
        "\n---------------------------------------";
  }
}







