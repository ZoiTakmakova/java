package ru.zt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

public ContactHelper(WebDriver wd) {
  super(wd);
}

public void returnToHomePage() {
  click(By.linkText("home page"));
}

public void fillContactData(ContactData contactData, boolean creation) {
  type(By.name("firstname"), contactData.getFirstname());
  type(By.name("middlename"), contactData.getMiddlename());
  type(By.name("lastname"), contactData.getLastname());
  type(By.name("address"), contactData.getAddress());
  type(By.name("home"), contactData.getHomePhone());
  type(By.name("mobile"), contactData.getMobilePhone());
  type(By.name("email"), contactData.getEmail());

  //элемент из выпадающего списка
  if (creation) {
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
  } else {
    Assert.assertFalse(isElementPresent(By.name("new_group")));
  }
}

public void submitAddNewCreation() {
  click(By.xpath("//div[@id='content']/form/input[21]"));
}

public void selectContact(int index) {
  wd.findElements(By.name("selected[]")).get(index).click();
}

public void selectContactById(int id) {
  wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
}

public void submitEditContact(int id) {
  wd.findElement(By.cssSelector("a[href ='edit.php?id=" + id + "']")).click();
}

public void submitDeletionContact() {
  click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
}

public void completionDeletion() {
  wd.switchTo().alert().accept();
}

public void submitUpDateContact() {
  click(By.xpath("//div[@id='content']/form[1]/input[22]"));
}

public void create(ContactData contact, boolean b) {
  fillContactData(contact, b);
  submitAddNewCreation();
  returnToHomePage();
}

public void modify(ContactData contact) {
  selectContactById(contact.getId());
  submitEditContact(contact.getId());
  fillContactData(contact, false);
  submitUpDateContact();
}

public void delete(ContactData contact) {
  selectContactById(contact.getId());
  submitDeletionContact();
  completionDeletion();
}

//формирование коллекции элементов
public Contacts all() {
  Contacts contacts = new Contacts();
  List<WebElement> elements = wd.findElements(By.name("entry"));
  for (WebElement element : elements) {
    int id = Integer.parseInt(element.findElement(By.tagName("td")).findElement(By.tagName("input")).getAttribute("id"));
    String lastname = element.findElement(By.xpath(".//td[2]")).getText();
    String firstname = element.findElement(By.xpath(".//td[3]")).getText();
    contacts.add(new ContactData().withId(id).withLastname(lastname).withFirstName(firstname).withGroup("test1"));
  }
  return contacts;
}
}



