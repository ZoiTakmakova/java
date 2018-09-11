package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

//проверка предусловий
@BeforeMethod
public void ensurePrecondition() {
  app.goTo().homePage();
  if (app.contact().list().size() == 0) {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    app.goTo().gotoAddNewPage();
    app.contact().create(new ContactData().withLastname("Ivanov1").withFirstName("Ivan1").withGroup("test1"), true);
  }
}

@Test
public void testContactModification() {

  app.goTo().homePage();
  List<ContactData> before = app.contact().list();//кол-во контактов до модификации контакта
  int index = before.size() - 1;
//  ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withLastname("Ivanov2").withFirstName("Ivan2").withGroup("test1");
  ContactData contact = new ContactData().withId(before.get(index).getId()).withLastname("Ivanov2").withFirstName("Ivan2").withGroup("test1");

  app.contact().modify(contact, index);
  app.goTo().homePage();
  List<ContactData> after = app.contact().list();
  Assert.assertEquals(after.size(), before.size());//проверка: кол-во контактов после модификации должно быть равно кол-ву контактов до модификации


  before.remove(index);
  before.add(contact);
  Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(before, after);
}


}
