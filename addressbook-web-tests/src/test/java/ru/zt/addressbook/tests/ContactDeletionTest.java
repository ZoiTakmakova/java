package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.List;


public class ContactDeletionTest extends TestBase {

@Test (enabled = false) /*ТЕСТ ОТКЛЮЧЕН*/
public void ContactDeletionTest() {
  app.goTo().gotoHomePage();
  if (!app.getContactHelper().isThereAContact()) {
    app.goTo().gotoAddNewPage();
    if (!app.group().isThereAGroup()) {
      app.goTo().groupPage();
      app.group().create(new GroupData("test1", "test2", "test3"));
    }
    app.goTo().gotoAddNewPage();
    app.getContactHelper().createContact(new ContactData("Ivanov23", "Ivan", "Ivanovich", null, "8956234", "89041235678", "ivanov@mail.ru", "test1"), true);
  }
  app.goTo().gotoHomePage();
  List<ContactData> before = app.getContactHelper().getContactList();
  app.getContactHelper().selectContact(before.size() - 1);
  app.getContactHelper().submitDeletionContact();
  app.getContactHelper().completionDeletion();
  app.goTo().gotoHomePage();
  List<ContactData> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size() - 1);//проверка: кол-во контактов после удаления должно быть равно кол-ву контактов до удаления  -1

  before.remove(before.size() - 1);
  Assert.assertEquals(before, after);
}
}
