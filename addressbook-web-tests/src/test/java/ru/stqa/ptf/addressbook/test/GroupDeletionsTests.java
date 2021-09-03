package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().GroupsPage();
    if (app.db().groups().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testGroupDeletions() throws Exception {
    app.goTo().GroupsPage();
    Groups before = app.db().groups();

    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    app.goTo().GroupsPage();

    Assert.assertEquals(app.group().count(),before.size() - 1);
    Groups after = app.db().groups();

    assertThat(after, equalTo(before.without(deletedGroup)));
  }
}

