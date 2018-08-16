package ru.zt.addressbook.tests;

import org.testng.annotations.Test;
import ru.zt.addressbook.model.AddNewData;

public class ContactModificationTests extends TestBase {
@Test
public void testContactModification() {
  app.getNavigationHelper().gotoHomePage();
  app.getContactHelper().selectContact();
  app.getContactHelper().submitEditContact();
  app.getContactHelper().fillAddNewForm(new AddNewData("Ivanov6", "Ivan6", "Ivanovich6", "Tver", "8956234", "89041235678", "ivanov@mail.ru"));
  app.getContactHelper().submitUpDateContact();
}
}
