package ru.zt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.zt.addressbook.model.AddNewData;

public class ContactHelper extends HelperBase {

public ContactHelper(FirefoxDriver wd) {
  super(wd);
}

public void returnToHomePage() {

  click(By.linkText("home page"));
}

public void submitAddNewCreation() {
  click(By.xpath("//div[@id='content']/form/input[21]"));
}

public void fillAddNewForm(AddNewData addNewData) {
  type(By.name("firstname"), addNewData.getFirstname());
  type(By.name("middlename"), addNewData.getMiddlename());
  type(By.name("lastname"), addNewData.getLastname());
  type(By.name("address"), addNewData.getAddress());
  type(By.name("home"), addNewData.getHomePhone());
  type(By.name("mobile"), addNewData.getMobilePhone());
  type(By.name("email"), addNewData.getEmail());
}

public void selectContact() {
  click(By.name("selected[]"));
}

public void submitDeletionContact() {
  click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
}

public void completionDeletion() {
  wd.switchTo().alert().accept();
}

public void submitEditContact() {
  click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
}

public void submitUpDateContact() {
  click(By.xpath("//div[@id='content']/form[1]/input[22]"));
}

}
