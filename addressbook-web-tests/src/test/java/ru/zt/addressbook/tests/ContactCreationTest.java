package ru.zt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.Contacts;
import ru.zt.addressbook.model.GroupData;

import java.io.File;

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

@Test /*(enabled = false)*/
public void contactCreationTest() {
  app.goTo().homePage();
  Contacts before = app.contact().all();
  //относительный путь
  File photo = new File("src/test/resources/image.png");
  ContactData contact = new ContactData().withLastname("Ivanov1").withFirstName("Ivan1")
          .withAddress("Tvr").withEmail_1("1@mail.ru").withEmail_2("2@mail.ru").withEmail_3("3@mail.ru")
          .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withPhoto(photo).withGroup("test1");
  app.goTo().gotoAddNewPage();
  app.contact().create(contact, true);
  Contacts after = app.contact().all();
  assertThat(after.size(), equalTo(before.size() + 1));

  assertThat(after, equalTo(
          before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
}
/*
@Test
public  void  testCurrentDir(){
  File currentDir = new File(".");
  System.out.println(currentDir.getAbsolutePath());
  File photo = new File("src/test/resources/image.png");
  System.out.println(photo.getAbsolutePath());
  System.out.println(photo.exists());
}
*/

}
