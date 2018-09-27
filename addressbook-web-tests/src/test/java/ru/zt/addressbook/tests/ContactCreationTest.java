package ru.zt.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
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
import java.util.stream.Collectors;

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
public Iterator<Object[]> validContactsFromXml() throws IOException {
  BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
  String xml = "";
  String line = reader.readLine();
  while (line != null) {
    xml += line;
    line = reader.readLine();
  }
  XStream xstream = new XStream();
  xstream.processAnnotations(ContactData.class);
  List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
  return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
}

@DataProvider
public Iterator<Object[]> validContactsFromJson() throws IOException {
  BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
  String json = "";
  String line = reader.readLine();
  while (line != null) {
    json += line;
    line = reader.readLine();
  }
  Gson gson = new Gson();
  List<ContactData> contacts =gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
  return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
}

@Test(dataProvider = "validContactsFromJson")/*(enabled = false)*/
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
