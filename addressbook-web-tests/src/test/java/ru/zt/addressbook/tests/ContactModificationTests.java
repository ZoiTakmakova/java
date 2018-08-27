package ru.zt.addressbook.tests;

import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
@Test
public void testContactModification() {
  app.getNavigationHelper().gotoHomePage();
  app.getContactHelper().selectContact();
  app.getContactHelper().submitEditContact();
  app.getContactHelper().fillContactData(new ContactData("Ivanov6", "Ivan6", "Ivanovich6", "Tver", "8956234", "89041235678", "ivanov@mail.ru",null),false);
  app.getContactHelper().submitUpDateContact();
}
}
