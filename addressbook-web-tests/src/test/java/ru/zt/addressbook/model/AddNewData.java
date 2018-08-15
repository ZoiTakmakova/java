package ru.zt.addressbook.model;

public class AddNewData {
private final String firstname;
private final String middlename;
private final String lastname;
private final String address;
private final String homePhone;
private final String mobilePhone;
private final String email;

public AddNewData(String firstname, String middlename, String lastname, String address, String homePhone, String mobilePhone, String email) {
  this.firstname = firstname;
  this.middlename = middlename;
  this.lastname = lastname;
  this.address = address;
  this.homePhone = homePhone;
  this.mobilePhone = mobilePhone;
  this.email = email;
}

public String getFirstname() {
  return firstname;
}

public String getMiddlename() {
  return middlename;
}

public String getLastname() {
  return lastname;
}

public String getAddress() {
  return address;
}

public String getHomePhone() {
  return homePhone;
}

public String getMobilePhone() {
  return mobilePhone;
}

public String getEmail() {
  return email;
}
}
