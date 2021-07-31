package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().GroupsPage();
    if (app.group().list().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testGroupDeletions() throws Exception {
    app.goTo().GroupsPage();
    List<GroupData> before = app.group().list();
    //int before = app.getGroupHelper().getGroupsCount();
    int index = before.size()-1;

    app.group().delete(index);
    app.goTo().GroupsPage();

    //int after = app.getGroupHelper().getGroupsCount();
    List<GroupData> after = app.group().list();
    //Assert.assertEquals(after,before - 1);
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before,after);
  }
}

