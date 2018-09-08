package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTest extends TestBase {
@BeforeMethod
public  void  ensurePrecondition(){
  app.goTo().groupPage();

  if (app.group().all().size() == 0) {
    app.group().create(new GroupData().withName("test1"));
  }
}

@Test
public void testGroupDeletion() {
  Set<GroupData> before = app.group().all();//размер списка групп  до удаления
  GroupData deletionGroup =  before.iterator().next();
  app.group().delete(deletionGroup);
  Set<GroupData> after = app.group().all();//размер списка групп после удаления
  Assert.assertEquals(after.size(), before.size() - 1);

  before.remove(deletionGroup);
  Assert.assertEquals(before, after);

}



}
