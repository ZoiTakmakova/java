package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;


public class ContactDeletionTest extends TestBase {

@BeforeMethod
public void ensurePrecondition() {
  app.goTo().homePage();
  if (app.contact().all().size() == 0) {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    app.goTo().gotoAddNewPage();
    app.contact().create(new ContactData().withLastname("Ivanov1").withGroup("test1"), true);
  }
}

@Test
public void ContactDeletionTest() {
  app.goTo().homePage();
  Set<ContactData> before = app.contact().all();
  ContactData deletedContact = before.iterator().next();
  app.contact().delete(deletedContact);
  app.goTo().homePage();
  Set<ContactData> after = app.contact().all();
  Assert.assertEquals(after.size(), before.size() - 1);//проверка: кол-во контактов после удаления должно быть равно кол-ву контактов до удаления  -1

  before.remove(deletedContact);
  Assert.assertEquals(before, after);
}
}
