package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
  Set<ContactData> before = app.contact().all();//кол-во контактов до модификации контакта
  ContactData modifiedContact = before.iterator().next();
  ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastname("Ivanov2").withFirstName("Ivan2").withGroup("test1");
  app.contact().modify(contact);
  app.goTo().homePage();
  Set<ContactData> after = app.contact().all();
  Assert.assertEquals(after.size(), before.size());//проверка: кол-во контактов после модификации должно быть равно кол-ву контактов до модификации


  before.remove(modifiedContact);
  before.add(contact);

  Assert.assertEquals(before, after);
}


}
