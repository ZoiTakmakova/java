package ru.zt.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.Contacts;
import ru.zt.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

//проверка предусловий
@BeforeMethod
public void ensurePrecondition() {
  app.goTo().homePage();
  if (app.contact().all().size() == 0) {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    app.goTo().gotoAddNewPage();
    app.contact().create(new ContactData().withLastname("Ivanov1").withFirstName("Ivan1").
            withHomePhone("111111").withMobilePhone("22222").withWorkPhone("33333").withGroup("test1"), true);
  }
}

@Test
public void testContactModification() {

  app.goTo().homePage();
  Contacts before = app.contact().all();
  ContactData modifiedContact = before.iterator().next();
  ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastname("Ivanov2").withFirstName("Ivan2").
          withHomePhone("111111").withMobilePhone("22222").withWorkPhone("33333").withGroup("test1");
  app.contact().modify(contact);
  app.goTo().homePage();
  assertThat(app.contact().count(),equalTo(before.size()));
  Contacts after = app.contact().all();
  assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
}
}
