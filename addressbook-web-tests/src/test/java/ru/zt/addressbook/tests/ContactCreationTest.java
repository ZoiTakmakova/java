package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest extends TestBase {

@Test
public void testAddNewCreation() {
 app.getNavigationHelper().gotoHomePage();
 List<ContactData> before = app.getContactHelper().getContactList();
 app.getNavigationHelper().gotoGroupPage();
  if (!app.getGroupHelper().isThereAGroup()){
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }
  app.getNavigationHelper().gotoAddNewPage();
  app.getContactHelper().createContact(new ContactData("Ivanov1", "Ivan2", null, null, null, null, null, "test1"), true);
 List<ContactData> after = app.getContactHelper().getContactList();
 Assert.assertEquals(after.size(),before.size() +1);//проверка: кол-во контактов после добавление должно быть равно кол-ву контактов до добавления +1
}
}
