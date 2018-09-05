package ru.zt.addressbook.model;

public class ContactData {
private final String firstname;
private final String middlename;
private final String lastname;

@Override
public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;

  ContactData that = (ContactData) o;

  if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
  return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
}

/*@Override
public int hashCode() {
  int result = firstname != null ? firstname.hashCode() : 0;
  result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
  return result;
}
*/
@Override
public String toString() {
  return "ContactData{" +
          "lastname='" + lastname + '\'' +
          '}';
}

private final String address;
private final String homePhone;
private final String mobilePhone;
private final String email;
private String group;

public ContactData( String lastname,String firstname, String middlename, String address, String homePhone, String mobilePhone, String email, String group) {
  this.firstname = firstname;
  this.middlename = middlename;
  this.lastname = lastname;
  this.address = address;
  this.homePhone = homePhone;
  this.mobilePhone = mobilePhone;
  this.email = email;
  this.group = group;
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

public String getGroup() {
  return group;
}
}
