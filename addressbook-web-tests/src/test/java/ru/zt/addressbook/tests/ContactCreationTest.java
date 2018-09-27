package ru.zt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.Contacts;
import ru.zt.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
@DataProvider
public Iterator<Object[]> validContacts(){
  List<Object[]> list = new ArrayList<Object[]>();
  list.add(new Object[] {new ContactData().withLastname("Ivanov1").withFirstname("Ivan1").withGroup("test1")});
  list.add(new Object[] {new ContactData().withLastname("Ivanov2").withFirstname("Ivan2").withGroup("test1")});
  list.add(new Object[] {new ContactData().withLastname("Ivanov3").withFirstname("Ivan3").withGroup("test1")});
  return  list.iterator();
}

@Test (dataProvider = "validContacts")/*(enabled = false)*/
public void contactCreationTest(ContactData contact) {
    //относительный путь
    File photo = new File("src/test/resources/image.png");
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().gotoAddNewPage();
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }
}
