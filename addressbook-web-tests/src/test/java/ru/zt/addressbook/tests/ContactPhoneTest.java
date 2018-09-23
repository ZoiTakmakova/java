package ru.zt.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTest extends TestBase {

@BeforeMethod
public void ensurePrecondition() {
  app.goTo().homePage();
  if (app.contact().all().size() == 0) {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    app.goTo().gotoAddNewPage();
    app.contact().create(new ContactData().withLastname("Ivanov1").withFirstName("Ivan1").withGroup("test1").
            withHomePhone("111111").withMobilePhone("22222").withWorkPhone("33333"), true);
  }
}

@Test
public  void  testContactPhone(){
  app.goTo().homePage();
  ContactData contact = app.contact().all().iterator().next(); //загрузка множество контактов[all()], и выбираем
  // какой-то контактслучайным[.iterator().next()]
  //infoFromEditForm(contact) - метод загрузки информации из формы редактирования контактов
  ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

  assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
  assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
  assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
}

//функция, удаляющая ненужные символы
 public  String cleaned(String phone){
  return phone.replaceAll("\\s","").replaceAll("[-()]","");
 }

}
