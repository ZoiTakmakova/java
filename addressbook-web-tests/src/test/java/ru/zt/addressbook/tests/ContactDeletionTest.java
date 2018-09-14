package ru.zt.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.Contacts;
import ru.zt.addressbook.model.GroupData;
import ru.zt.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


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
 Contacts before = app.contact().all();
  ContactData deletedContact = before.iterator().next();
  app.contact().delete(deletedContact);
  app.goTo().homePage();
  assertThat(app.contact().count(),equalTo(before.size()-1));
  Contacts after = app.contact().all();
  assertThat(after, equalTo(before.without(deletedContact)));
}
}
