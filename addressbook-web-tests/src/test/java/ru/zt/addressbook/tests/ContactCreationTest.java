package ru.zt.addressbook.tests;

import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

@Test
public void testAddNewCreation() {
  app.getNavigationHelper().gotoAddNewPage();
  app.getContactHelper().fillContactData(new ContactData("Ivanov23", "Ivan", "Ivanovich", null, "8956234", "89041235678", "ivanov@mail.ru", "test1"), true);
  app.getContactHelper().submitAddNewCreation();
  app.getContactHelper().returnToHomePage();
}

}
