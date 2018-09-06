package ru.zt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.zt.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

public ContactHelper(WebDriver wd) {
  super(wd);
}

public void returnToHomePage() {

  click(By.linkText("home page"));
}

public void submitAddNewCreation() {
  click(By.xpath("//div[@id='content']/form/input[21]"));
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

public void selectContact(int index) {
  wd.findElements(By.name("selected[]")).get(index).click();
}

public void submitDeletionContact() {
  click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
}

public void completionDeletion() {
  wd.switchTo().alert().accept();
}

//исправить метод!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public void submitEditContact(int index) {  click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));}
//href="edit.php?id=112"

public void submitUpDateContact() {
  click(By.xpath("//div[@id='content']/form[1]/input[22]"));
}

public void createContact(ContactData contact, boolean b) {
  fillContactData(contact, b);
  submitAddNewCreation();
  returnToHomePage();
}

public boolean isThereAContact() {
  return isElementPresent(By.name("selected[]"));
}

//getContactCount - метод подсчета кол-ва контактов
public int getContactCount() {
  //findElements - возвращает список эл-ов
  //size - получить размер
  return wd.findElements(By.name("selected[]")).size();

}

//формирование коллекции элементов
public List<ContactData> getContactList() {
  List<ContactData> contacts = new ArrayList<ContactData>();
  List<WebElement> elements = wd.findElements(By.name("entry"));
  for (WebElement element : elements) {
    String lastname = element.getText();
    String id = element.findElement(By.tagName("td")).findElement(By.tagName("input")).getAttribute("id");
    ContactData contact = new ContactData(id, lastname, null, null, null, null, null, null, null);
    contacts.add(contact);
  }
  return contacts;
}


}

