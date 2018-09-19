//Декларация пакета
package ru.zt.addressbook.tests;


import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;
import ru.zt.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

@Test
public void testGroupCreation() {
  app.goTo().groupPage();
  Groups before = app.group().all();//размер множеста до создания
  GroupData group = new GroupData().withName("test2");
  app.group().create(group);
  Groups after = app.group().all();//размер множества после создания
  assertThat(after.size(), equalTo(before.size() + 1));
  assertThat(after, equalTo(
          before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

}

@Test
public void testBadGroupCreation() {
  app.goTo().groupPage();
  Groups before = app.group().all();//размер множеста до создания
  GroupData group = new GroupData().withName("test2'");
  app.group().create(group);
  assertThat(app.group().count(), equalTo(before.size()));
  Groups after = app.group().all();//размер множества после создания
  assertThat(after, equalTo(before));

}
}
