//Декларация пакета
package ru.zt.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

@Test
public void testGroupCreation() {
  app.goTo().groupPage();
  Set<GroupData> before = app.group().all();//размер множеста до создания
  GroupData group = new GroupData().withName("test2");
  app.group().create(group);
  Set<GroupData> after = app.group().all();//размер множества после удаления
  Assert.assertEquals(after.size(), before.size() + 1);

  group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
  before.add(group);
  Assert.assertEquals(before,after);

}
}
