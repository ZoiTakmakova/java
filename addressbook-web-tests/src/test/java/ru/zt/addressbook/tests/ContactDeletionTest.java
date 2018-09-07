package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.List;


public class ContactDeletionTest extends TestBase {

@Test (enabled = false) /*ТЕСТ ОТКЛЮЧЕН*/
public void ContactDeletionTest() {
  app.getNavigationHelper().gotoHomePage();
  if (!app.getContactHelper().isThereAContact()) {
    app.getNavigationHelper().gotoAddNewPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().createContact(new ContactData("Ivanov23", "Ivan", "Ivanovich", null, "8956234", "89041235678", "ivanov@mail.ru", "test1"), true);
  }
  app.getNavigationHelper().gotoHomePage();
  List<ContactData> before = app.getContactHelper().getContactList();
  app.getContactHelper().selectContact(before.size() - 1);
  app.getContactHelper().submitDeletionContact();
  app.getContactHelper().completionDeletion();
  app.getNavigationHelper().gotoHomePage();
  List<ContactData> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size() - 1);//проверка: кол-во контактов после удаления должно быть равно кол-ву контактов до удаления  -1

  before.remove(before.size() - 1);
  Assert.assertEquals(before, after);
}
}
