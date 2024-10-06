package poised;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Represents a Project entity with project details.
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

  /**
   * Constructs a Project object with all attributes.
   *
   * @param projectId      The project ID
   * @param projectName    The name of the project
   * @param buildingType   The type of building
   * @param physicalAddress The physical address of the project
   * @param totalFee       The total fee for the project
   * @param deadline       The project deadline
   * @param isFinalised    Whether the project is finalised
   * @param completionDate The project's completion date (if finalised)
   */
  public Project(int projectId, String projectName, String buildingType,
                 String physicalAddress, float totalFee, LocalDate deadline,
                 boolean isFinalised, LocalDate completionDate) {
    this.projectId = projectId;
    this.projectName = projectName;
    this.buildingType = buildingType;
    this.physicalAddress = physicalAddress;
    this.totalFee = totalFee;
    this.deadline = deadline;
    this.isFinalised = isFinalised;
    this.completionDate = completionDate;
  }

  /**
   * Constructs a Project object for new projects without an ID.
   *
   * @param projectName     The name of the project
   * @param buildingType    The type of building
   * @param physicalAddress The physical address of the project
   * @param totalFee        The total fee for the project
   * @param deadline        The project deadline
   */
  public Project(String projectName, String buildingType, String physicalAddress,
                 float totalFee, LocalDate deadline) {
    this.projectName = projectName;
    this.buildingType = buildingType;
    this.physicalAddress = physicalAddress;
    this.totalFee = totalFee;
    this.deadline = deadline;
    this.isFinalised = false;
    this.completionDate = null;
  }

  // Getters and Setters

  /**
   * Gets the project ID.
   *
   * @return The project ID
   */
  public int getProjectId() {
    return projectId;
  }

  /**
   * Gets the project name.
   *
   * @return The project name
   */
  public String getProjectName() {
    return projectName;
  }

  /**
   * Gets the type of building for the project.
   *
   * @return The building type
   */
  public String getBuildingType() {
    return buildingType;
  }

  /**
   * Gets the physical address of the project.
   *
   * @return The physical address
   */
  public String getPhysicalAddress() {
    return physicalAddress;
  }

  /**
   * Gets the total fee of the project.
   *
   * @return The total fee
   */
  public float getTotalFee() {
    return totalFee;
  }

  /**
   * Gets the deadline of the project.
   *
   * @return The project deadline
   */
  public LocalDate getDeadline() {
    return deadline;
  }

  /**
   * Checks if the project is finalised.
   *
   * @return True if finalised, otherwise false
   */
  public boolean isFinalised() {
    return isFinalised;
  }

  /**
   * Gets the completion date of the project.
   *
   * @return The completion date, or null if not finalised
   */
  public LocalDate getCompletionDate() {
    return completionDate;
  }

  /**
   * Converts the project details to a string format.
   *
   * @return A string representation of the project
   */
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
           "\n---------------------------------------";
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
    java.sql.Date completionDateSql = rs.getDate("completion_date");
    LocalDate completionDate = (completionDateSql != null)
        ? completionDateSql.toLocalDate() : null;

    return new Project(projectId, projectName, buildingType, physicalAddress,
                       totalFee, deadline, isFinalised, completionDate);
  }
}

