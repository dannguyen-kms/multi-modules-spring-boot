package com;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/** Entity User. */
@Entity
@Table(name = "user")
public class User {
  @Id
  @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "dob")
  private LocalDate dob;

  @Transient private int age;

  /**
   * Constructor.
   *
   * @param id User's identity
   * @param name User's full name
   * @param email User's email
   * @param dob User's BirthDate
   */
  public User(Long id, String name, String email, LocalDate dob) {
    this.email = email;
    this.id = id;
    this.name = name;
    this.dob = dob;
  }

  public User() {}

  public static UserBuilder builder() {
    return new UserBuilder();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public int getAge() {
    return Period.between(this.dob, LocalDate.now()).getYears();
  }

  /** nested class to use Builder pattern. */
  public static class UserBuilder {
    private String name;
    private String email;
    private LocalDate dob;

    public UserBuilder setName(final String name) {
      this.name = name;
      return this;
    }

    public UserBuilder setEmail(final String email) {
      this.email = email;
      return this;
    }

    public UserBuilder setDob(final LocalDate dob) {
      this.dob = dob;
      return this;
    }

    public User build() {
      return new User(null, this.name, this.email, this.dob);
    }
  }
}
