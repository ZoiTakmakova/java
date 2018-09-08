package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;

import java.util.Set;

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

  Set<GroupData> before = app.group().all();//размер списка до модификации
  GroupData modifiedGroup =  before.iterator().next();
  GroupData group = new GroupData()
          .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
  app.goTo().groupPage();
  app.group().modify(group);
  Set<GroupData> after = app.group().all();//размер списка после модификации
  Assert.assertEquals(after.size(), before.size());

  before.remove(modifiedGroup);
  before.add(group);
   Assert.assertEquals(before, after);
}
}

