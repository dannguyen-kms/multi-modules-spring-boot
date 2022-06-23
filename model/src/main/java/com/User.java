package com;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "dob")
    private LocalDate dob;
    @Transient
    private int age;

    public User(Long id, String name,String email, LocalDate dob) {
        this.email = email;
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public User() {
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
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }
    public static class UserBuilder {
        private String name;
        private String email;
        private LocalDate dob;

        public UserBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setEmail(final  String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setDob(final LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public User build() {
            return new User(null,this.name,this.email, this.dob);
        }
    }
}
