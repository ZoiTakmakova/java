package ru.zt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.Contacts;
import ru.zt.addressbook.model.GroupData;

import java.io.*;
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
public Iterator<Object[]> validContacts() throws IOException {
  List<Object[]> list = new ArrayList<Object[]>();
  BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
  String line = reader.readLine();
  while (line != null){
    String[] split = line.split(";");
    list.add(new Object[]{new ContactData().withLastname(split[0]).withFirstname(split[1]).withGroup(split[3])});
    line = reader.readLine();
  }
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
