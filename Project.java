package poised;

import java.time.LocalDate;

/**
 * The Project class represents a project in the project management system.
 */
public class Project {
  private int id;  
  private String projectName;  
  private String buildingType;  
  private String physicalAddress;  
  private float totalFee;  
  private LocalDate deadline;  
  private String erfNumber;  
  private int architectId; 
  private int engineerId; 
  private int managerId;  
  private int contractorId;  
  private int customerId;  
  private int isFinalised;  
  private LocalDate completionDate;  

  /**
   * Constructor for creating a new project.
   *
   * @param projectName    The name of the project
   * @param buildingType   The type of building (e.g., House, Apartment)
   * @param physicalAddress The physical address of the project
   * @param totalFee       The total fee for the project
   * @param deadline       The deadline for the project
   * @param erfNumber      The ERF number associated with the project
   * @param architectId    The ID of the architect
   * @param engineerId     The ID of the engineer
   * @param managerId      The ID of the project manager
   * @param contractorId   The ID of the contractor
   * @param customerId     The ID of the customer
   */
  public Project(String projectName, String buildingType, String physicalAddress,
      float totalFee, LocalDate deadline, String erfNumber,
      int architectId, int engineerId, int managerId,
      int contractorId, int customerId) {
    this.projectName = projectName;
    this.buildingType = buildingType;
    this.physicalAddress = physicalAddress;
    this.totalFee = totalFee;
    this.deadline = deadline;
    this.erfNumber = erfNumber;
    this.architectId = architectId;
    this.engineerId = engineerId;
    this.managerId = managerId;
    this.contractorId = contractorId;
    this.customerId = customerId;
    this.isFinalised = 0;  // Default to not finalized
    this.completionDate = null;  // No completion date initially
  }

  /**
   * Constructor for updating an existing project.
   *
   * @param id             The ID of the project
   * @param projectName    The name of the project
   * @param buildingType   The type of building (e.g., House, Apartment)
   * @param physicalAddress The physical address of the project
   * @param totalFee       The total fee for the project
   * @param deadline       The deadline for the project
   * @param erfNumber      The ERF number associated with the project
   * @param architectId    The ID of the architect
   * @param engineerId     The ID of the engineer
   * @param managerId      The ID of the project manager
   * @param contractorId   The ID of the contractor
   * @param customerId     The ID of the customer
   * @param isFinalised    The finalized status of the project (1 for finalized, 0 otherwise)
   * @param completionDate  The completion date of the project
   */
  public Project(int id, String projectName, String buildingType,
      String physicalAddress, float totalFee, LocalDate deadline,
      String erfNumber, int architectId, int engineerId, int managerId,
      int contractorId, int customerId, int isFinalised,
      LocalDate completionDate) {
    this.id = id;
    this.projectName = projectName;
    this.buildingType = buildingType;
    this.physicalAddress = physicalAddress;
    this.totalFee = totalFee;
    this.deadline = deadline;
    this.erfNumber = erfNumber;
    this.architectId = architectId;
    this.engineerId = engineerId;
    this.managerId = managerId;
    this.contractorId = contractorId;
    this.customerId = customerId;
    this.isFinalised = isFinalised;
    this.completionDate = completionDate;
  }

  // Getter and Setter methods

  /**
   * Gets the ID of the project.
   *
   * @return The project ID
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the ID of the project.
   *
   * @param id The project ID
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the name of the project.
   *
   * @return The project name
   */
  public String getProjectName() {
    return projectName;
  }

  /**
   * Sets the name of the project.
   *
   * @param projectName The project name
   */
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  /**
   * Gets the building type.
   *
   * @return The building type
   */
  public String getBuildingType() {
    return buildingType;
  }

  /**
   * Sets the building type.
   *
   * @param buildingType The building type
   */
  public void setBuildingType(String buildingType) {
    this.buildingType = buildingType;
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
   * Sets the physical address of the project.
   *
   * @param physicalAddress The physical address
   */
  public void setPhysicalAddress(String physicalAddress) {
    this.physicalAddress = physicalAddress;
  }

  /**
   * Gets the total fee for the project.
   *
   * @return The total fee
   */
  public float getTotalFee() {
    return totalFee;
  }

  /**
   * Sets the total fee for the project.
   *
   * @param totalFee The total fee
   */
  public void setTotalFee(float totalFee) {
    this.totalFee = totalFee;
  }

  /**
   * Gets the deadline of the project.
   *
   * @return The deadline
   */
  public LocalDate getDeadline() {
    return deadline;
  }

  /**
   * Sets the deadline for the project.
   *
   * @param deadline The deadline
   */
  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  /**
   * Gets the ERF number associated with the project.
   *
   * @return The ERF number
   */
  public String getErfNumber() {
    return erfNumber;
  }

  /**
   * Sets the ERF number for the project.
   *
   * @param erfNumber The ERF number
   */
  public void setErfNumber(String erfNumber) {
    this.erfNumber = erfNumber;
  }

  /**
   * Gets the ID of the architect.
   *
   * @return The architect ID
   */
  public int getArchitectId() {
    return architectId;
  }

  /**
   * Sets the ID of the architect.
   *
   * @param architectId The architect ID
   */
  public void setArchitectId(int architectId) {
    this.architectId = architectId;
  }

  /**
   * Gets the ID of the engineer.
   *
   * @return The engineer ID
   */
  public int getEngineerId() {
    return engineerId;
  }

  /**
   * Sets the ID of the engineer.
   *
   * @param engineerId The engineer ID
   */
  public void setEngineerId(int engineerId) {
    this.engineerId = engineerId;
  }

  /**
   * Gets the ID of the project manager.
   *
   * @return The project manager ID
   */
  public int getManagerId() {
    return managerId;
  }

  /**
   * Sets the ID of the project manager.
   *
   * @param managerId The project manager ID
   */
  public void setManagerId(int managerId) {
    this.managerId = managerId;
  }

  /**
   * Gets the ID of the contractor.
   *
   * @return The contractor ID
   */
  public int getContractorId() {
    return contractorId;
  }

  /**
   * Sets the ID of the contractor.
   *
   * @param contractorId The contractor ID
   */
  public void setContractorId(int contractorId) {
    this.contractorId = contractorId;
  }

  /**
   * Gets the ID of the customer.
   *
   * @return The customer ID
   */
  public int getCustomerId() {
    return customerId;
  }

  /**
   * Sets the ID of the customer.
   *
   * @param customerId The customer ID
   */
  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  /**
   * Checks if the project is finalized.
   *
   * @return 1 if finalized, 0 otherwise
   */
  public int isFinalised() {
    return isFinalised;
  }

  /**
   * Sets the finalized status of the project.
   *
   * @param isFinalised The finalized status
   */
  public void setFinalised(int isFinalised) {
    this.isFinalised = isFinalised;
  }

  /**
   * Gets the completion date of the project.
   *
   * @return The completion date
   */
  public LocalDate getCompletionDate() {
    return completionDate;
  }

  /**
   * Sets the completion date for the project.
   *
   * @param completionDate The completion date
   */
  public void setCompletionDate(LocalDate completionDate) {
    this.completionDate = completionDate;
  }
}







