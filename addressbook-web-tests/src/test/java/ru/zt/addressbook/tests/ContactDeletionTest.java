package ru.zt.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

@Test
public void ContactDeletionTest() {
  app.getNavigationHelper().gotoHomePage();
  app.getContactHelper().selectContact();
  app.getContactHelper().submitDeletionContact();
  app.getContactHelper().completionDeletion();
}
}
