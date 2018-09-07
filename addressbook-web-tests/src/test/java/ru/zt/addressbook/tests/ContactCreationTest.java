package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {

@Test (enabled = false) /*ТЕСТ ОТКЛЮЧЕН*/
public void testAddNewCreation() {
  app.getNavigationHelper().gotoHomePage();
  List<ContactData> before = app.getContactHelper().getContactList();
  app.getNavigationHelper().gotoGroupPage();
  if (!app.getGroupHelper().isThereAGroup()) {
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }
  ContactData contact = new ContactData("Ivanov1", "Ivan1", null, null, null, null, null, "test1");
  app.getNavigationHelper().gotoAddNewPage();
  app.getContactHelper().createContact(contact, true);
  List<ContactData> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size() + 1);//проверка: кол-во контактов после добавление должно быть равно кол-ву контактов до добавления +1

  contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
  before.add(contact);
  Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(),c2.getId()) ;
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
}
}
