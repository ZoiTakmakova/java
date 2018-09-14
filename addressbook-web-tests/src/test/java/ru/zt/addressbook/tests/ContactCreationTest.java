package ru.zt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.Contacts;
import ru.zt.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {
@BeforeMethod
public void ensurePrecondition() {
  app.goTo().groupPage();
  if (!app.group().isThereAGroup()) {
    app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
  }
}

@Test
public void contactCreationTest() {
  app.goTo().homePage();
  Contacts before = app.contact().all();
  ContactData contact = new ContactData().withLastname("Ivanov1").withFirstName("Ivan1").withGroup("test1");
  app.goTo().gotoAddNewPage();
  app.contact().create(contact, true);
  assertThat(app.contact().count(),equalTo(before.size()+1));
  Contacts after = app.contact().all();
  assertThat(after.size(), equalTo(before.size() + 1));

  assertThat(after, equalTo(
          before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
}

@Test
public void contactBadCreationTest() {
  app.goTo().homePage();
  Contacts before = app.contact().all();
  ContactData contact = new ContactData().withLastname("Ivanov2'").withFirstName("Ivan1").withGroup("test1");
  app.goTo().gotoAddNewPage();
  app.contact().create(contact,true);
  assertThat(app.contact().count(),equalTo(before.size()));
  Contacts after = app.contact().all();
  assertThat(after, equalTo(before));
}
}
