package poised;

/**
 * Represents a person involved in the project (e.g., Architect, Structural
 * Engineer, Project Manager, Customer).
 */
public class Person {
  private String name;
  private String phone;
  private String email;
  private String address;

  // Constructor
  public Person(String name, String phone, String email, String address) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.address = address;
  }

  // Getters
  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  // toString method
  @Override
  public String toString() {
    return name + " (" + phone + ", " + email + ", " + address + ")";
  }
}


