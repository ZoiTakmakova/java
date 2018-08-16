package ru.zt.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {


@Test
public void ContactDeletionTest() {
  app.getNavigationHelper().gotoHomePage();
  app.getContactHelper().selectContact();
  app.getContactHelper().submitDeletionContact();
}


}
