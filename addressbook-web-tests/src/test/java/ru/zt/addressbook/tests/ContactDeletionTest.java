package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.List;


public class ContactDeletionTest extends TestBase {

@BeforeMethod
public void ensurePrecondition() {
  app.goTo().homePage();
  if (app.contact().list().size() == 0) {
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
  List<ContactData> before = app.contact().list();
  int index = before.size() -1;
  app.contact().delete(index);
  app.goTo().homePage();
  List<ContactData> after = app.contact().list();
  Assert.assertEquals(after.size(), before.size() - 1);//проверка: кол-во контактов после удаления должно быть равно кол-ву контактов до удаления  -1
  before.remove(index);
  Assert.assertEquals(before, after);
}
}
