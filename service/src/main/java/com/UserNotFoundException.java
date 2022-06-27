package com;

/** Custom Exception throw when there is no record founded in database. */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String field, String value) {
    super(String.format("No such user with %s: %s", field, value));
  }
}
