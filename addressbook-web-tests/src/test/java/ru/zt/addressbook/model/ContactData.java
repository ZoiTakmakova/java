package ru.zt.addressbook.model;

import java.io.File;

public class ContactData {


private int id =  Integer.MAX_VALUE;
private String firstname;
private String middlename;


public ContactData withPhoto(File photo) {
  this.photo = photo;
  return this;
}

private String lastname;
private String address;
private String homePhone;
private String mobilePhone;
private String workPhone;
private String allPhones;
private String email_1;
private String email_2;
private String email_3;
private String allEmail;
private File photo;

public File getPhoto() {  return photo;}

public String getEmail_1() {  return email_1;}

public String getEmail_2() {  return email_2;}

public String getEmail_3() {  return email_3;}

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

public String getWorkPhone() {
  return workPhone;
}

private String group;

public String getGroup() {
  return group;
}

public String getAllPhones() {  return allPhones;}

public String getAllEmail() {  return allEmail;}

public int getId() {return id;}

public String getFirstname() {
  return firstname;
}

public String getMiddlename() {
  return middlename;
}



public ContactData withEmail_1(String email_1) {
  this.email_1 = email_1;
  return this;
}

public ContactData withEmail_2(String email_2) {
  this.email_2 = email_2;
  return this;
}

public ContactData withEmail_3(String email_3) {
  this.email_3 = email_3;
  return this;
}

public ContactData withAllPhones(String allPhones) {
  this.allPhones = allPhones;
  return this;
}

public ContactData withId(int id) {
  this.id = id;
  return this;
}

public ContactData withFirstname(String firstname) {
  this.firstname = firstname;
  return this;
}

public ContactData withMiddlename(String middlename) {
  this.middlename = middlename;
  return this;
}

public ContactData withLastname(String lastname) {
  this.lastname = lastname;
  return this;
}



public ContactData withAddress(String address) {
  this.address = address;
  return this;
}

public ContactData withHomePhone(String homePhone) {
  this.homePhone = homePhone;
  return this;
}

public ContactData withMobilePhone(String mobilePhone) {
  this.mobilePhone = mobilePhone;
  return this;
}

public ContactData withWorkPhone(String workPhone) {
  this.workPhone = workPhone;
  return this;
}

public ContactData withEmail(String allEmail) {
  this.allEmail = allEmail;
  return this;
}

public ContactData withGroup(String group) {
  this.group = group;
  return this;
}



@Override
public String toString() {
  return "ContactData{" +
          "id='" + id + '\'' +
          ", firstname='" + firstname + '\'' +
          ", lastname='" + lastname + '\'' +
          '}';
}
@Override
public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;

  ContactData that = (ContactData) o;

  if (id != that.id) return false;
  if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
  return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
}

@Override
public int hashCode() {
  int result = id;
  result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
  result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
  return result;
}

}
