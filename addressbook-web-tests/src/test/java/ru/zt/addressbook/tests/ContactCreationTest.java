package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.Set;

public class ContactCreationTest extends TestBase {
@BeforeMethod
public void ensurePrecondition() {
  app.goTo().groupPage();
  if (!app.group().isThereAGroup()) {
    app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
  }
}

@Test
public void testAddNewCreation() {
  app.goTo().homePage();
  Set<ContactData> before = app.contact().all();
  ContactData contact = new ContactData().withLastname("Ivanov1").withFirstName("Ivan1").withGroup("test1");
  app.goTo().gotoAddNewPage();
  app.contact().create(contact, true);
  Set<ContactData> after = app.contact().all();
  Assert.assertEquals(after.size(), before.size() + 1);//проверка: кол-во контактов после добавление
  // должно быть равно кол-ву контактов до добавления +1

  contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
  before.add(contact);
  Assert.assertEquals(before, after);
}
}
