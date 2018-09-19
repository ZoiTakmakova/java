package ru.zt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;
import ru.zt.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

@BeforeMethod
public void ensurePrecondition() {
  app.goTo().groupPage();
  if (app.group().all().size() == 0) {
    app.group().create(new GroupData().withName("test1"));
  }
}

@Test

public void testGroupModification() {

  Groups before = app.group().all();//размер списка до модификации
  GroupData modifiedGroup = before.iterator().next();
  GroupData group = new GroupData()
          .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
  app.goTo().groupPage();
  app.group().modify(group);
  assertEquals(app.group().count(), before.size());
  Groups after = app.group().all();//размер списка после модификации
  assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
}
}

