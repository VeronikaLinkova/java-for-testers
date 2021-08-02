package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().GroupsPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testGroupDeletions() throws Exception {
    app.goTo().GroupsPage();
    Set<GroupData> before = app.group().all();
    //List<GroupData> before = app.group().list();
    //int before = app.getGroupHelper().getGroupsCount();
    //int index = before.size()-1;

    GroupData deletedGroup = before.iterator().next();
    //app.group().delete(index);
    app.group().delete(deletedGroup);
    app.goTo().GroupsPage();

    //int after = app.getGroupHelper().getGroupsCount();
    Set<GroupData> after = app.group().all();
    //List<GroupData> after = app.group().list();
    //Assert.assertEquals(after,before - 1);
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(deletedGroup);
    Assert.assertEquals(before,after);
  }
}

