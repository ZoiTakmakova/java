package ru.zt.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;
import ru.zt.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {
@BeforeMethod
public  void  ensurePrecondition(){
  app.goTo().groupPage();

  if (app.group().all().size() == 0) {
    app.group().create(new GroupData().withName("test1"));
  }
}

//@Test (enabled = false) /*ТЕСТ ОТКЛЮЧЕН*/
@Test
public void testGroupDeletion() {
  Groups before = app.group().all();//размер списка групп  до удаления
  GroupData deletedGroup =  before.iterator().next();
  app.group().delete(deletedGroup);
  Groups after = app.group().all();//размер списка групп после удаления
  assertThat(app.group().count(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedGroup)));
}
}
