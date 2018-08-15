package ru.zt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.zt.addressbook.model.AddNewData;

public class ContactHelper {
private FirefoxDriver wd;

public ContactHelper(FirefoxDriver wd) {
  this.wd = wd;
}

public void returnToHomePage() {
  wd.findElement(By.linkText("home page")).click();
}

public void submitAddNewCreation() {
  wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
}

public void fillAddNewForm(AddNewData addNewData) {
  wd.findElement(By.name("firstname")).click();
  wd.findElement(By.name("firstname")).clear();
  wd.findElement(By.name("firstname")).sendKeys(addNewData.getFirstname());
  wd.findElement(By.name("middlename")).click();
  wd.findElement(By.name("middlename")).clear();
  wd.findElement(By.name("middlename")).sendKeys(addNewData.getMiddlename());
  wd.findElement(By.name("lastname")).click();
  wd.findElement(By.name("lastname")).clear();
  wd.findElement(By.name("lastname")).sendKeys(addNewData.getLastname());
  wd.findElement(By.name("company")).click();
  wd.findElement(By.name("address")).click();
  wd.findElement(By.name("address")).clear();
  wd.findElement(By.name("address")).sendKeys(addNewData.getAddress());
  wd.findElement(By.name("home")).click();
  wd.findElement(By.name("home")).clear();
  wd.findElement(By.name("home")).sendKeys(addNewData.getHomePhone());
  wd.findElement(By.name("mobile")).click();
  wd.findElement(By.name("mobile")).clear();
  wd.findElement(By.name("mobile")).sendKeys(addNewData.getMobilePhone());
  wd.findElement(By.name("email")).click();
  wd.findElement(By.name("email")).clear();
  wd.findElement(By.name("email")).sendKeys(addNewData.getEmail());
}
}
