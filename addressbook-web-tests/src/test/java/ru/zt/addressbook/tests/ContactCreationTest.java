package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

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
  List<ContactData> before = app.contact().list();
  ContactData contact = new ContactData().withLastname("Ivanov1").withFirstName("Ivan1").withGroup("test1");
  app.goTo().gotoAddNewPage();
  app.contact().create(contact, true);
  List<ContactData> after = app.contact().list();
  Assert.assertEquals(after.size(), before.size() + 1);//проверка: кол-во контактов после добавление
  // должно быть равно кол-ву контактов до добавления +1

  before.add(contact);
  Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(),c2.getId()) ;
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
}
}
