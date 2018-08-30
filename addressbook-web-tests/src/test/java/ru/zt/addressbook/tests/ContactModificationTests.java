package ru.zt.addressbook.tests;

import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {
@Test
public void testContactModification() {
  app.getNavigationHelper().gotoHomePage();
  if (!app.getContactHelper().isThereAContact()) {
    app.getNavigationHelper().gotoAddNewPage();
    if (!app.getGroupHelper().isThereAGroup()){
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().createContact(new ContactData("Ivanov23", "Ivan", "Ivanovich", null, "8956234", "89041235678", "ivanov@mail.ru", "test1"), true);
  }
  app.getContactHelper().selectContact();
  app.getContactHelper().submitEditContact();
  app.getContactHelper().fillContactData(new ContactData("Ivanov6", "Ivan6", "Ivanovich6", "Tver", "8956234", "89041235678", "ivanov@mail.ru",null),false);
  app.getContactHelper().submitUpDateContact();
}
}
