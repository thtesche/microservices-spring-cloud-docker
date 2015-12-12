package de.thtesche.user;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author thtesche
 */
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Version
  private long version;
  private String firstName;
  private String nickName;
  private String lastName;
  private String email;

  /**
   *
   * @param firstName
   * @param lastName
   * @param nickName
   * @param email
   */
  public User(String firstName, String lastName, String nickName, String email) {
    this.firstName = firstName;
    this.nickName = nickName;
    this.lastName = lastName;
    this.email = email;
  }

  public User() {
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the nickName
   */
  public String getNickName() {
    return nickName;
  }

  /**
   * @param nickName the nickName to set
   */
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return the version
   */
  public long getVersion() {
    return version;
  }

  /**
   * @param version the version to set
   */
  public void setVersion(long version) {
    this.version = version;
  }
}
