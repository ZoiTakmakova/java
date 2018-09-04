//Декларация пакета
package ru.zt.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

@Test
public void testGroupCreation() {
  app.getNavigationHelper().gotoGroupPage();
  List<GroupData> before = app.getGroupHelper().getGroupList();//размер списка до создания
  GroupData group = new GroupData("test1", "test2", "test3");
  app.getGroupHelper().createGroup(group);
  List<GroupData> after = app.getGroupHelper().getGroupList();//размер списка после удаления
  Assert.assertEquals(after.size(), before.size() + 1);


//список превращаем в поток, по этому потоку пробегает функция сравниватель, находит максимальный эл-т,
// сравниваются объекты GroupData, путем сравнения их Ид, на выходе функции будем объект с максимальным Ид
  group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
  before.add(group);
  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
}
}
