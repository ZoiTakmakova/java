package ru.zt.addressbook.tests;

import org.testng.annotations.Test;
import ru.zt.addressbook.model.AddNewData;

public class ContactCreationTest extends TestBase {

@Test
public void testAddNewCreation() {
  app.getNavigationHelper().gotoAddNewPage();
  app.getContactHelper().fillAddNewForm(new AddNewData("Ivanov23", "Ivan", "Ivanovich", null, "8956234", "89041235678", "ivanov@mail.ru"));
  app.getContactHelper().submitAddNewCreation();
  app.getContactHelper().returnToHomePage();
}

}
