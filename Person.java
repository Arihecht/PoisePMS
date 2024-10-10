package poised;

/**
 * The Person class represents an individual involved in a project,
 * such as an architect, engineer, project manager, contractor, or
 * customer.
 */
public class Person {
  private int id;  
  private String name;  
  private String phoneNumber; 
  private String email;  

  /**
   * Constructor for creating a new person.
   *
   * @param name        The name of the person
   * @param phoneNumber The phone number of the person
   * @param email       The email address of the person
   */
  public Person(String name, String phoneNumber, String email) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  /**
   * Constructor for updating an existing person.
   *
   * @param id          The ID of the person
   * @param name        The name of the person
   * @param phoneNumber The phone number of the person
   * @param email       The email address of the person
   */
  public Person(int id, String name, String phoneNumber, String email) {
    this.id = id;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  // Getter and Setter methods

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

